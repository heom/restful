package me.study.restful.Exception;

import me.study.restful.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @Description [User service API 구현] Exception Handling - 공통 Exception Handling
 **/
@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = getDefaultExceptionResponse(ex, request);
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = getDefaultExceptionResponse(ex, request);
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @Description [RESTful Service 기능 확장] Validation
     **/
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
                                                                    , HttpHeaders headers
                                                                    , HttpStatus status
                                                                    , WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date()
                                                                    , "Validation Failed"
                                                                    , ex.getBindingResult().toString());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse getDefaultExceptionResponse(Exception ex, WebRequest request) {
        return new ExceptionResponse(new Date()
                , ex.getMessage()
                , request.getDescription(false));
    }
}
