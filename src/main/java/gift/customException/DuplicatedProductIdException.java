package gift.customException;

public class DuplicatedProductIdException extends RuntimeException implements CustomException {
    public DuplicatedProductIdException() {
        super();
    }

    public DuplicatedProductIdException(String message) {
        super(message);
    }
}
