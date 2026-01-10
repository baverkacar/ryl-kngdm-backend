package com.baver.app.api.rest.advice;


import com.baver.app.domain.user.UserExistsException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(UserExistsException.class)
    public ProblemDetail handleUserExistsException(UserExistsException ex) {
        log.warn("BUSINESS_EXCEPTION type=USER_EXISTS message={}", ex.getMessage());

        return buildCustomProblemDetail(
                HttpStatus.CONFLICT,
                "USER_ALREADY_EXISTS",
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnhandled(Exception ex) {
        log.error("UNHANDLED_EXCEPTION message={}", ex.getMessage(), ex);

        return buildCustomProblemDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "An unexpected error occurred."
        );
    }

    private ProblemDetail buildCustomProblemDetail(HttpStatus status, String customStatusCode, String message) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, message);

        pd.setTitle(customStatusCode);
        pd.setType(URI.create("about:blank"));

        pd.setProperty("httpStatus", status.name());
        pd.setProperty("statusCode", customStatusCode);
        pd.setProperty("message", message);
        pd.setProperty("timeStamp", Instant.now());

        String requestId = MDC.get("requestId");
        if (requestId != null) {
            pd.setProperty("requestId", requestId);
        }

        return pd;
    }
}
