package fieldworks.dto;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
	private String title;
	private String message;
	private T payload;
	private int responseCode;
	private Instant timestamp;

	public ApiResponse() {
		this.timestamp = Instant.now();
	}

	public ApiResponse(String title, String message, T payload, int responseCode) {
		this.title = title;
		this.message = message;
		this.payload = payload;
		this.responseCode = responseCode;
		this.timestamp = Instant.now();

	}

	// getters + setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public static <T> ApiResponse<T> success(String message, T payload) {
		return new ApiResponse<>("Success", message, payload, HttpStatus.OK.value());
	}

	public static <T> ApiResponse<T> created(String message, T payload) {
		return new ApiResponse<>("Created", message, payload, HttpStatus.CREATED.value());
	}

	public static <T> ApiResponse<T> error(String title, String message, HttpStatus status) {
		return new ApiResponse<>(title, message, null, status.value());
	}

}