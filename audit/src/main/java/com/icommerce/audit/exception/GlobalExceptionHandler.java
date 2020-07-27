package com.icommerce.audit.exception;

import com.icommerce.audit.constant.ApplicationConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
        ObjectError objectError = exception.getBindingResult().getAllErrors()
                .stream().findFirst().orElse(null);
        String errorMsg = exception.getMessage();
        if (objectError != null) {
            errorMsg = (objectError instanceof FieldError)
                    ? String.format("%s %s", ((FieldError) objectError).getField(), objectError.getDefaultMessage())
                    : String.format("%s %s", "Audit",  objectError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomError(ApplicationConstants.BAD_REQUEST, errorMsg));
    }

}
