package lk.rcu.rcg2000.memberdirectory.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiError handleMethodArgumentTypeMismatchException(final HttpServletRequest request,
                                                              final NotFoundException ex) {
        logError(request, ex);
        return ApiError.builder()
                .message(ex.getId() + " not found.")
                .build();
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler({ProblemAccessException.class, ComplimentAccessException.class, AccessDeniedException.class})
    public ApiError handleProblemAccessException(final HttpServletRequest request,
                                                 final Exception ex) {
        logError(request, ex);
        return ApiError.builder()
                .message(ex.getMessage())
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ApiError handleInvalidDataAccessApiUsageException(final HttpServletRequest request,
            final InvalidDataAccessApiUsageException ex) {
        logError(request, ex);
        return ApiError.builder()
                .message("Invalid request object")
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiError handleDataIntegrityViolationException(final HttpServletRequest request,
                                              final DataIntegrityViolationException ex) {
        logError(request, ex);
        return ApiError.builder()
                .message("Other resources depend on this, delete them first.")
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        final List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        final ModelMap errorMap = new ModelMap();
        for (FieldError fieldError : errors) {
            errorMap.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ApiError.builder()
                .errorMap(errorMap)
                .build();
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiError handleHttpMessageNotReadableException(final HttpServletRequest request,
            final HttpMessageNotReadableException ex) {
        logError(request, ex);
        return ApiError.builder()
                .message("Unrecognised field included in request")
                .build();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiError handleException(final HttpServletRequest request,
            final Exception ex) {
        logError(request, ex);
        return ApiError.builder()
                .message("An internal service error has occurred. Resend the request at another time.")
                .build();
    }

    private void logError(final HttpServletRequest request, final Exception exception) {
//        logger.error("API exception {} occurred for URL [{}]", exception.getClass().getName(), request.getRequestURL());
//        logger.error("Caught exception: {}", exception.getMessage());
        logger.error("API exception {} occurred for URL [{}] Query [{}] for User Id [{}]", exception.getClass().getName(), request.getMethod() + " " + request.getRequestURL(), request.getQueryString(), request.getUserPrincipal().getName());
        logger.error("Caught exception: {}", exception.getMessage(), exception);
    }
}
