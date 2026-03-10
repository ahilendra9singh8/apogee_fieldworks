package fieldworks.exception;

import fieldworks.dto.ApiResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

//@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

	// Custom unified exception handler
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException ex) {
		ApiResponse<Object> response = new ApiResponse<>(ex.getTitle(), ex.getMessage(), null, ex.getStatus().value());
		return ResponseEntity.status(ex.getStatus()).body(response);
	}

	// File upload size exception
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ApiResponse<Object>> handleMaxUpload(MaxUploadSizeExceededException ex) {
		ApiResponse<Object> response = new ApiResponse<>("Upload Failed", "Uploaded file is too large.", null,
				HttpStatus.PAYLOAD_TOO_LARGE.value());
		return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(response);
	}
	
    // Handle Validation Error
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidationErrors(
	        MethodArgumentNotValidException ex) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors()
	            .forEach(err ->
	                errors.put(err.getField(), err.getDefaultMessage())
	            );

	    ApiResponse<Object> response =
	            new ApiResponse<>("Validation Failed",
	                    "Invalid request data",
	                    errors,
	                    HttpStatus.BAD_REQUEST.value());

	    return ResponseEntity.badRequest().body(response);
	}

	// Fallback for uncaught exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleAll(Exception ex) {
		 ex.printStackTrace(); // or use logger.error(...)
		ApiResponse<Object> response = new ApiResponse<>("Internal Server Error", ex.getMessage(), null,
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
}
