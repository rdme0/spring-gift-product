package gift.customException;

public class NoSuchProductIdException extends RuntimeException implements CustomException{
    public NoSuchProductIdException(){
        super();
    }
    public NoSuchProductIdException(String message){
        super(message);
    }

}
