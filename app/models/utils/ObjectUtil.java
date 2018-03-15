package models.utils;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ObjectUtil {
  private ObjectUtil() {
    // no instance
  }

  @CheckReturnValue
  @Nonnull
  public static <T> T nullToDefault(@Nullable T obj, @Nonnull T defaultObj) {
    return obj != null ? obj : defaultObj;
  }
}
