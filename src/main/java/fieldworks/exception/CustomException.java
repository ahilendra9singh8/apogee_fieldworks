package fieldworks.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	private final String title;
	private final String errorCode;
	private final HttpStatus status;

	public CustomException(String title, String message, String errorCode, HttpStatus status) {
		super(message);
		this.title = title;
		this.errorCode = errorCode;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	// ✅ Static helper methods for easy creation (future reusability)
	public static CustomException badRequest(String message) {
		return new CustomException("Bad Request", message, "BAD_REQUEST", HttpStatus.BAD_REQUEST);
	}

	public static CustomException notFound(String message) {
		return new CustomException("Not Found", message, "NOT_FOUND", HttpStatus.NOT_FOUND);
	}

	public static CustomException internalError(String message) {
		return new CustomException("Internal Error", message, "INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static CustomException invalidData(String message) {
		return new CustomException("Invalid Data", message, "INVALID_DATA", HttpStatus.UNPROCESSABLE_ENTITY);
	}

	public static CustomException forbidden(String message) {
		return new CustomException("Forbidden", message, "FORBIDDEN", HttpStatus.FORBIDDEN);
	}
	
	public static CustomException unauthorized(String message) {
	    return new CustomException("Unauthorized", message, "UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
	}

	public static CustomException conflict(String message) {
	    return new CustomException("Conflict", message, "CONFLICT", HttpStatus.CONFLICT);
	}

}
