package gift.customException;

public class NotFoundSuchProductIdException extends RuntimeException implements CustomException{
    public NotFoundSuchProductIdException(){
        super();
    }
    public NotFoundSuchProductIdException(String message){
        super(message);
    }

}
