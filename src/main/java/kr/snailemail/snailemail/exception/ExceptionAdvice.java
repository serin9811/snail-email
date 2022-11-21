package kr.snailemail.snailemail.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({CognitoUserException.class})
    ResponseEntity<CustomError> cognitoExceptionHandler(CognitoUserException ex) {
        log.error(ex.getMessage());
        CustomError customError = ex.getCustomError();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(customError.getStatus())
                .message(customError.getMessage())
                .data(customError.getData())
                .build();

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }
}
