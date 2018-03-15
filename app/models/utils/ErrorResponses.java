package models.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import models.ErrorResponse;
import exceptions.ApplicationException;

public class ErrorResponses {
  private ErrorResponses() {
    // no instance
  }

  private static final int BASE_CODE = 100000;
  private static final String APP_PACKAGE_REGEX = "^(?:controllers|models)";

  public static final int UNKNOWN_ERROR = BASE_CODE + 1;
  public static final int DB_ERROR = BASE_CODE + 2;
  public static final int NET_ERROR = BASE_CODE + 3;
  public static final int AUTHENTICATOR_ERROR = BASE_CODE + 4;

  @Nonnull
  public static ErrorResponse of(@Nonnull Throwable e) {
    if (e instanceof ApplicationException) {
      return of((ApplicationException) e);
    }
    return of(UNKNOWN_ERROR, e.getMessage(), findDevelopMsg(e));
  }

  @Nonnull
  public static ErrorResponse of(@Nonnull ApplicationException appException) {
    return of(appException.errorCode, appException.getMessage(),
        appException.developMsg == null ? findDevelopMsg(appException) : appException.developMsg);
  }

  @Nonnull
  public static ErrorResponse of(int code, @Nonnull String message, @Nullable String developMsg) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.code = code;
    errorResponse.message = message;
    errorResponse.developMsg = developMsg == null ? "" : developMsg;
    errorResponse.moreInfo = "/error/" + code;
    return errorResponse;
  }

  @Nonnull
  private static String findDevelopMsg(@Nonnull Throwable e) {
    StringBuilder builder = new StringBuilder();
    for (StackTraceElement element : e.getStackTrace()) {
      String fileName = element.getFileName();
      String className = element.getClassName();
      String methodName = element.getMethodName();
      int lineNumber = element.getLineNumber();
      if (className.matches(APP_PACKAGE_REGEX)) {
        return builder.append("Exception: ")
            .append(e.getMessage())
            .append(". ")
            .append("at ")
            .append(className)
            .append(".")
            .append(methodName)
            .append("(")
            .append(fileName)
            .append(":")
            .append(lineNumber)
            .append(")")
            .toString();
      }
    }
    return e.toString();
  }
}
