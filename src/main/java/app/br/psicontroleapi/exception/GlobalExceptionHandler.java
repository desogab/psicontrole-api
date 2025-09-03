package app.br.psicontroleapi.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 * Centralised exception handler that still returns the **default**
 * Spring-Boot error payload.
 *
 * <p>How it works:</p>
 * 1. Any exception thrown by a controller is intercepted here.<br>
 * 2. We ask the configured {@link ErrorAttributes} to build the same map
 * that {@code BasicErrorController} would create.<br>
 * 3. The map is returned with the proper HTTP status.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorAttributes errorAttributes;

    public GlobalExceptionHandler(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * Business / validation problems.
     */
    @ExceptionHandler({IllegalArgumentException.class, ResponseStatusException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex, WebRequest request) {
        return buildResponseEntity(request, ex, HttpStatus.BAD_REQUEST);
    }

    /**
     * Fallback – anything not matched above.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleInternal(Exception ex, WebRequest request) {
        return buildResponseEntity(request, ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> buildResponseEntity(WebRequest request, Exception ex, HttpStatus status) {
        request.setAttribute("jakarta.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);

        final ErrorAttributeOptions opts = ErrorAttributeOptions
                .defaults()
                .including(Include.MESSAGE);

        final Map<String, Object> body = errorAttributes
                .getErrorAttributes(request, opts);

        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());

        return ResponseEntity.status(status).body(body);
    }
}
