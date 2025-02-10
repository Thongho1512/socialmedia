package com.oss.socialmedia.exception;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.oss.socialmedia.controller.response.ErrorResponse;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<Object>> handleAllException(Exception ex, WebRequest request)
    {
    ErrorResponse<Object> res = new ErrorResponse<Object>();
    res.setTimestamp(Instant.now());
    res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    res.setPath(request.getDescription(false).replace("uri=", ""));
    res.setMessage(ex.getMessage());
    res.setError("Internal Server Error");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }


    // handle for @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Object>> handleInvalidPayload(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorResponse<Object> res = new ErrorResponse<Object>();
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setError(ex.getBody().getDetail());
        List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toList());
        res.setMessage(errors.size() > 1 ? errors : errors.get(0));
        res.setTimestamp(Instant.now());
        res.setPath(request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    // handle for id not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        
        ErrorResponse<Object> res = new ErrorResponse<>();
        res.setTimestamp(Instant.now());
        res.setPath(request.getDescription(false).replace("uri=", ""));
        res.setStatus(HttpStatus.NOT_FOUND.value());
        res.setError("Resource not found");
        res.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }


    // Option 2
    // @ExceptionHandler(ResourceNotFoundException.class)
    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // @ApiResponses(val    ue = {
    //         @ApiResponse(responseCode = "404", description = "RESOURCE_NOT_FOUND",
    //                 content = @Content(mediaType = "application/json", examples = @ExampleObject(
    //                         name = "404 Response",
    //                         summary = "Handle resource not found",
    //                         value = """
    //                                 {
    //                                     "timestamp": "2023-10-19T06:07:35.321+00:00",
    //                                     "status": 404,
    //                                     "path": "/api/v1/...",
    //                                     "error": "Not Found",
    //                                     "message": "{data} not found"
    //                                 }
    //                                 """
    //                 )))
    // })
    // public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    //     ErrorResponse errorResponse = new ErrorResponse();
    //     errorResponse.setTimestamp(Instant.now());
    //     errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
    //     errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    //     errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
    //     errorResponse.setMessage(ex.getMessage());

    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    // }
}
