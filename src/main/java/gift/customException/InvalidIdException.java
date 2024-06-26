package gift.customException;

public class InvalidIdException extends RuntimeException implements CustomException {
    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }
}
