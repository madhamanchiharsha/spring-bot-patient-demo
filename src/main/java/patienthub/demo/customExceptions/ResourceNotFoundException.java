package patienthub.demo.customExceptions;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
