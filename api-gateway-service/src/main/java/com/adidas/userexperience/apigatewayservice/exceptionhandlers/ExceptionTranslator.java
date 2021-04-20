package com.adidas.userexperience.apigatewayservice.exceptionhandlers;


import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

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

    private ErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
        ErrorDTO dto = new ErrorDTO(ErrorConstants.ERR_VALIDATION);

        for (FieldError fieldError : fieldErrors) {
            dto.add(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
        }

        return dto;
    }


    @ExceptionHandler(Exception.class)
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

    // handling specific exception
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> unAuthorizedHandling(UnauthorizedException exception, WebRequest request){
        BodyBuilder builder;
        ErrorDTO errorDTO;
        errorDTO = new ErrorDTO("error." + HttpStatus.NOT_FOUND, exception.getMessage());
        builder = ResponseEntity.status(HttpStatus.NOT_FOUND);
        return builder.body(errorDTO);
    }

}
