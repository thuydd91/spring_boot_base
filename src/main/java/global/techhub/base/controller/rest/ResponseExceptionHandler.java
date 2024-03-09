package global.techhub.base.controller.rest;

import global.techhub.base.exception.BadRequestException;
import global.techhub.base.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleCommonError(Exception e, WebRequest webRequest) {

        String responseBody = e.getMessage();

        return handleExceptionInternal(e, responseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }


    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest webRequest) {

        String message;

        if (StringUtils.isEmpty(e.getMessage())) {
            message = messageSource.getMessage("exception.not_found", new String[]{}, Locale.getDefault());;
        } else {
            message = e.getMessage();
        }

        return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(NotFoundException e, WebRequest webRequest) {

        String message;

        if (StringUtils.isEmpty(e.getMessage())) {
            message = messageSource.getMessage("exception.bad_request", new String[]{}, Locale.getDefault());
        } else {
            message = e.getMessage();
        }

        return handleExceptionInternal(e, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
