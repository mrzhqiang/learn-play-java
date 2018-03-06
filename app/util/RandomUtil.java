package util;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
import javax.annotation.Nonnull;

public class RandomUtil {
  private RandomUtil() {
    // no instance
  }

  private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String UPPER_CASE = LOWER_CASE.toUpperCase(Locale.ROOT);
  private static final String NUMBER = "0123456789";
  private static final String CHARS = LOWER_CASE + UPPER_CASE + NUMBER;
  private static final Random RANDOM = new SecureRandom();

  /** 生成纯数字字符串，基本按 ^[1-9]* 的规则执行 */
  @Nonnull
  public static String numberOf(int minLength, int range) {
    if (minLength <= 0) {
      throw new IllegalArgumentException("minLength should greater than 0: " + minLength);
    }
    if (range <= 0) {
      throw new IllegalArgumentException("range should greater than 0: " + range);
    }
    int length = minLength + (int) (Math.random() * range);
    StringBuilder builder = new StringBuilder(length);
    String chars = NUMBER;
    for (int i = 0; i < length; i++) {
      int index;
      if (i == 0) {
        index = (int) (1 + Math.random() * 9);
      } else {
        index = RANDOM.nextInt(chars.length());
      }
      builder.append(chars.charAt(index));
    }
    return builder.toString();
  }

  /** 生成完全随机的字符串 */
  @Nonnull
  public static String stringOf(int minLength, int range) {
    if (minLength <= 0) {
      throw new IllegalArgumentException("minLength should greater than 0: " + minLength);
    }
    if (range <= 0) {
      throw new IllegalArgumentException("range should greater than 0: " + range);
    }
    int length = minLength + (int) (Math.random() * range);
    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      builder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
    }
    return builder.toString();
  }
}
