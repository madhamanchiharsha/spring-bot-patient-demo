package patienthub.demo.customExceptions;

public class CustomException extends Exception{

    public CustomException (String errorMessage){
        super(errorMessage);
    }

}
