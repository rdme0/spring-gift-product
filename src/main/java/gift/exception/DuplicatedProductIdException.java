package gift.exception;

public class DuplicatedProductIdException extends CustomException {
    public DuplicatedProductIdException() {
        super();
    }

    public DuplicatedProductIdException(String message) {
        super(message);
    }
}
