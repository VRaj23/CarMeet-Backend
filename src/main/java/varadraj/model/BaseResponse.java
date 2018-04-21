package varadraj.model;

public class BaseResponse {

	private int status;
	private Object message;
	
	public BaseResponse() {}
	
	
	public BaseResponse(int status, Object message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	
}
