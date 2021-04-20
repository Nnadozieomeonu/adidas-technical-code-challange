package com.adidas.userexperience.emailservice.exceptionhandlers;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.net.ConnectException;
import java.rmi.ConnectIOException;
import java.util.List;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(CustomParameterizedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ParameterizedErrorDTO processParameterizedValidationError(CustomParameterizedException ex) {
        return ex.getErrorDTO();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorDTO processAccessDeniedExcpetion(AccessDeniedException e) {
        return new ErrorDTO(ErrorConstants.ERR_ACCESS_DENIED, e.getMessage());
    }

    private ErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ErrorDTO dto = new ErrorDTO(ErrorConstants.ERR_VALIDATION);

        for (FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
        }

        return dto;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDTO processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ErrorDTO(ErrorConstants.ERR_METHOD_NOT_SUPPORTED, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> processRuntimeException(Exception ex) throws Exception {
        BodyBuilder builder;
        ErrorDTO errorDTO;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            builder = ResponseEntity.status(responseStatus.value());
            errorDTO = new ErrorDTO("error." + responseStatus.value().value(), responseStatus.reason());
        } else {
            builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            errorDTO = new ErrorDTO(ErrorConstants.ERR_INTERNAL_SERVER_ERROR, "Internal server error");
        }
        return builder.body(errorDTO);
    }

    // handling specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
        BodyBuilder builder;
        ErrorDTO errorDTO;
        errorDTO = new ErrorDTO("error." + HttpStatus.NOT_FOUND, exception.getMessage());
        builder = ResponseEntity.status(HttpStatus.NOT_FOUND);
        return builder.body(errorDTO);
    }

    @ExceptionHandler(ConnectIOException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> connectionExecptionHandling(ConnectIOException exception, WebRequest request){
        BodyBuilder builder;
        ErrorDTO errorDTO;
        errorDTO = new ErrorDTO("error." + HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        return builder.body(errorDTO);
    }

    @ExceptionHandler(ConnectException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> connectionRefusedExecptionHandling(ConnectException exception, WebRequest request){
        BodyBuilder builder;
        ErrorDTO errorDTO;
        errorDTO = new ErrorDTO("error." + HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        return builder.body(errorDTO);
    }


}
