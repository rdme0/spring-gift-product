package gift.exception;

public class NullContentException extends RuntimeException implements CustomException {

    public NullContentException() {
        super();
    }

    public NullContentException(String message) {
        super(message);
    }
}
