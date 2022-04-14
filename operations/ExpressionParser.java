package expression.operations;

import expression.Expression;
import expression.exceptions.*;
import expression.types.GenericType;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExpressionParser<T> {

    private final Map<String, Class<?>> binaryOperations = Map.of(
            "**", Pow.class,
            "//", Log.class,
            "+", Add.class,
            "-", Subtract.class,
            "*", Multiply.class,
            "/", Divide.class,
            "min", Min.class,
            "max", Max.class
    );

    private final Map<String, Class<?>> unaryOperations = Map.of(
            "l0", LZeroes.class,
            "t0", TZeroes.class,
            "abs", Abs.class,
            "count", Count.class
    );
    private final List<String> variables = List.of("x", "y", "z");
    private final GenericType<T> typeObject;

    public ExpressionParser(final GenericType<T> typeObject) {
        this.typeObject = typeObject;
    }

    private String expression;
    private int pos = 0;

    public Expression<T> parseGeneric(final String expression) throws ParsingException {
        this.expression = expression;
        pos = 0;
        return parseExpression(-1, false);
    }

    private Expression<T> parseExpression(final int operationPriority,
                                                      final boolean openingParenthesis
    ) throws ParsingException {
        Expression<T> first = parseElement(openingParenthesis);
        while (true) {
            skipWhiteSpaces();
            if (getChar() == ')' && !openingParenthesis) {
                throw new UnrecognizedCharacterException("Extra closing parenthesis in position " + (pos + 1));
            }
            if (getChar() == ')' || getChar() == 0) {
                return first;
            }
            final String operation = parseBinaryOperation();
            int priority = getPriority(binaryOperations.get(operation));
            if (priority <= operationPriority) {
                pos -= operation.length();
                return first;
            }
            first = newBinaryOperation(operation, first, parseExpression(priority, openingParenthesis));
        }
    }

    private Expression<T> parseElement(final boolean openingParenthesis) throws ParsingException {
        skipWhiteSpaces();
        if (getChar() == 0) {
            throw new UnexpectedEndOfInputException("Missing operand in position: " + (pos + 1));
        }
        if (fitString("-")) {
            if (getChar() == 0) {
                throw new UnexpectedEndOfInputException("Missing argument for '-'");
            }
            if (Character.isDigit(getChar())) {
                pos--;
                return new Const<>(parseNumber(), typeObject);
            }
            return new Negate<>(parseElement(openingParenthesis), typeObject);
        } else if (Character.isDigit(getChar())) {
            return new Const<>(parseNumber(), typeObject);
        } else if (Character.isLetter(getChar())) {
            final Expression<T> result = parseVariable();
            if (result != null) {
                return result;
            }
            String operation = parseUnaryOperation();
            return newUnaryOperation(
                    operation,
                    parseExpression(getPriority(unaryOperations.get(operation)), openingParenthesis)
            );
        } else if (fitString("(")) {
            final Expression<T> result = parseExpression(-1, true);
            if (getChar() == 0) {
                throw new UnexpectedEndOfInputException("Missing closing parenthesis in position " + (pos + 1));
            }
            if (fitString(")")) {
                return result;
            } else {
                throw new UnrecognizedCharacterException("Expected closing parenthesis in position " + (pos + 1));
            }
        }
        throw new UnrecognizedCharacterException("Unrecognized character in position " + (pos + 1));
    }

    private T parseNumber() throws ParsingException {
        skipWhiteSpaces();
        final int startPosition = pos;
        final StringBuilder sb = new StringBuilder();
        do {
            sb.append(getChar());
            pos++;
        } while (Character.isDigit(getChar()));
        try {
            return typeObject.parseValue(sb.toString());
        } catch (final NumberFormatException e) {
            throw new ConstFormatException("Illegal const size in position: " + (startPosition + 1));
        }
    }

    private Expression<T> parseVariable() {
        skipWhiteSpaces();
        for (final String variable : variables) {
            if (fitString(variable)) {
                return new Variable<>(variable);
            }
        }
        return null;
    }

    private String parseUnaryOperation() throws ParsingException {
        for (final Map.Entry<String, Class<?>> entry : unaryOperations.entrySet()) {
            if (fitString(entry.getKey())) {
                if (Character.isLetterOrDigit(getChar())) {
                    throw new UnrecognizedCharacterException("Invalid operation argument in position " + (pos + 1));
                }
                return entry.getKey();
            }
        }
        throw new UnrecognizedCharacterException("Missing operation in position " + (pos + 1));
    }

    private String parseBinaryOperation() throws ParsingException {
        for (int i = 3; i > 0; i--) {
            for (final Map.Entry<String, Class<?>> entry : binaryOperations.entrySet()) {
                if (entry.getKey().length() != i) {
                    continue;
                }
                if (fitString(entry.getKey())) {
                    if (Objects.equals(entry.getKey(), "min") || Objects.equals(entry.getKey(), "max")) {
                        if (pos - 4 < 0 ||
                                (!Character.isWhitespace(expression.charAt(pos - 4)) && expression.charAt(pos - 4) != ')') ||
                                (!Character.isWhitespace(getChar()) && getChar() != '(' && getChar() != '-')) {
                            throw new UnrecognizedCharacterException("Unrecognized character in position " + (pos - 2));
                        }
                    }
                    return entry.getKey();
                }
            }
        }
        throw new UnrecognizedCharacterException("Missing operation in position " + (pos + 1));
    }

    private void skipWhiteSpaces() {
        while (Character.isWhitespace(getChar())) {
            pos++;
        }
    }

    private char getChar() {
        if (pos < expression.length())
            return expression.charAt(pos);
        return 0;
    }

    private boolean fitString(final String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != getChar()) {
                pos -= i;
                return false;
            }
            pos++;
        }
        return true;
    }

    private int getPriority(Class<?> type) {
        try {
            return (type.getField("priority")).getInt(type);
        } catch (final NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Expression<T> newBinaryOperation(
            final String operation,
            final Expression<T> first,
            final Expression<T> second
    ) {
        return switch (operation) {
            case ("**") -> new Pow<>(first, second, typeObject);
            case ("//") -> new Log<>(first, second, typeObject);
            case ("+") -> new Add<>(first, second, typeObject);
            case ("-") -> new Subtract<>(first, second, typeObject);
            case ("*") -> new Multiply<>(first, second, typeObject);
            case ("/") -> new Divide<>(first, second, typeObject);
            case ("min") -> new Min<>(first, second, typeObject);
            case ("max") -> new Max<>(first, second, typeObject);
            default -> null;
        };
    }

    private Expression<T> newUnaryOperation(
            final String operation,
            final Expression<T> first
    ) {
        return switch (operation) {
            case ("l0") -> new LZeroes<>(first, typeObject);
            case ("t0") -> new TZeroes<>(first, typeObject);
            case ("abs") -> new Abs<>(first, typeObject);
            case ("count") -> new Count<>(first, typeObject);
            default -> null;
        };
    }
}
