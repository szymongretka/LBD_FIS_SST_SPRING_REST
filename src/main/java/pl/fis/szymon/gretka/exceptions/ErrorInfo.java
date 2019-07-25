package pl.fis.szymon.gretka.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorInfo {
	
	/*private int status;
    private String message;
    private List<FieldError> fieldErrors = new ArrayList<>();
    

    ErrorInfo(int status, String message) {
        this.status = status;
        this.message = message;
    }


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void addFieldError(String name, String path, String message) {
        FieldError error = new FieldError(name, path, message);
        fieldErrors.add(error);
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }*/
	
	 private HttpStatus status;
	 
	 @JsonInclude(JsonInclude.Include.NON_NULL)
	 private String message;
	 
	 @JsonInclude(JsonInclude.Include.NON_NULL)
	 private List<Map<String, String>> errors;

	    public ErrorInfo(HttpStatus status, List<Map<String, String>> errors) {
	        super();
	        this.status = status;
	        this.errors = errors;
	    }
	    

	    public ErrorInfo(HttpStatus status, String message) {
	        super();
	        this.status = status;
	        this.message = message;
	    }

		public HttpStatus getStatus() {
			return status;
		}

		public void setStatus(HttpStatus status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public List<Map<String, String>> getErrors() {
			return errors;
		}

		public void setErrors(List<Map<String, String>> errors) {
			this.errors = errors;
		}
	    
	    
	

}
