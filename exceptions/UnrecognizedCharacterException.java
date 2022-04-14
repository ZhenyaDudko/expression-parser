package expression.exceptions;

public class UnrecognizedCharacterException extends ParsingException {
    public UnrecognizedCharacterException(String message) {
        super(message);
    }
}
