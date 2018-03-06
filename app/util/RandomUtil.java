package util;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
import javax.annotation.Nonnull;

public class RandomUtil {
  private RandomUtil() {
    // no instance
  }

  private static final String LOWER_CASE = "qwertyuiopasdfghjklzxcvbnm";
  private static final String UPPER_CASE = LOWER_CASE.toUpperCase(Locale.ROOT);
  private static final String NUMBER = "1234567890";
  private static final String CHARS = LOWER_CASE + UPPER_CASE + NUMBER;
  private static final Random RANDOM = new SecureRandom();

  /** 通过指定数字字符组合的最小长度以及增长范围，从中随机一个长度位数，并随机地生成一组账号 */
  @Nonnull
  public static String numberOf(int minLength, int range) {
    if (minLength <= 0) {
      throw new IllegalArgumentException("minLength should greater than 0: " + minLength);
    }
    if (range <= 0) {
      throw new IllegalArgumentException("range should greater than 0: " + range);
    }

    int length = minLength + (int) (Math.random() * range);
    return numberOf(length);
  }

  /** 生成纯数字字符串，基本按 ^[1-9][0-9]* 的规则执行 */
  @Nonnull
  public static String numberOf(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("length should greater than 0: " + length);
    }

    StringBuilder builder = new StringBuilder(length);
    String chars = NUMBER;
    for (int i = 0; i < length; i++) {
      int index;
      if (i == 0) {
        // 首个数字字符为0则没有意义
        index = (int) (1 + Math.random() * 9);
      } else {
        index = RANDOM.nextInt(chars.length());
      }
      builder.append(chars.charAt(index));
    }
    return builder.toString();
  }

  /** 通过指定字符串的最小长度以及增长范围，从中随机一个长度位数，并随机地生成一组密码 */
  @Nonnull
  public static String stringOf(int minLength, int range) {
    if (minLength <= 0) {
      throw new IllegalArgumentException("minLength should greater than 0: " + minLength);
    }
    if (range <= 0) {
      throw new IllegalArgumentException("range should greater than 0: " + range);
    }

    int length = minLength + (int) (Math.random() * range);
    return stringOf(length);
  }

  /** 生成指定长度的随机字符串 */
  @Nonnull
  public static String stringOf(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("length should greater than 0: " + length);
    }

    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      builder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
    }
    return builder.toString();
  }
}
