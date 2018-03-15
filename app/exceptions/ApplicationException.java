package exceptions;

public class ApplicationException extends RuntimeException {
  public int errorCode;
  public String developMsg;

  public ApplicationException(String message, int errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public ApplicationException(String message, int errorCode, String developMsg) {
    super(message);
    this.errorCode = errorCode;
    this.developMsg = developMsg;
  }
}
