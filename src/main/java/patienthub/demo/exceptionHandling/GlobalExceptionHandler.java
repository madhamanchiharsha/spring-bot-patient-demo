package patienthub.demo.exceptionHandling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Configuration
public class GlobalExceptionHandler {
    private Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value= {NoHandlerFoundException.class})
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("Error","404 Not Found - The requested resource was not found.");

    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleBadRequestException(HttpRequestMethodNotSupportedException ex) {
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("Error","405 Method"+ex.getMethod()+" not supported.");

        return new ResponseEntity<>(result,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}
