package gift.customException;

public class NullContentException extends RuntimeException implements CustomException {

    NullContentException() {
        super();
    }

    NullContentException(String message) {
        super(message);
    }
}
