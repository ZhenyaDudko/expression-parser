package expression.operations;

import expression.Expression;

public class Variable<T> implements Expression<T> {

    public static final int priority = 10;
    public static final int specialType = 0;

    protected final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return switch (value) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int getSpecialType() {
        return specialType;
    }
}
