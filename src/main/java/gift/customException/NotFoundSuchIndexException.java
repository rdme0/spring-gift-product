package gift.customException;

public class NotFoundSuchIndexException extends RuntimeException implements CustomException{
    public NotFoundSuchIndexException(){
        super();
    }
    public NotFoundSuchIndexException(String message){
        super(message);
    }

}
