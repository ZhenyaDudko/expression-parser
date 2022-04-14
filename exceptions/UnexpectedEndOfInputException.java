package expression.exceptions;

public class UnexpectedEndOfInputException extends ParsingException {
    public UnexpectedEndOfInputException(String message) {
        super(message);
    }
}
