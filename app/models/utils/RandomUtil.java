package models.utils;

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
  private static final String SPECIAL = "-._~+/";
  private static final String CHARS = LOWER_CASE + UPPER_CASE + NUMBER + SPECIAL;
  private static final Random RANDOM = new SecureRandom();

  /** 根据指定长度，取得一个随机数字序列 */
  @Nonnull
  public static String numberOf(int length) {
    return numberOf(length, length);
  }

  /** 通过指定的最小和最大长度范围，随机取得一个字符长度数，然后按照此长度生成一个随机的数字账号 */
  @Nonnull
  public static String numberOf(int minLength, int maxLength) {
    if (minLength <= 0) {
      throw new IllegalArgumentException("minLength should greater than 0: " + minLength);
    }
    if (maxLength <= 0) {
      throw new IllegalArgumentException("maxLength should greater than 0: " + maxLength);
    }
    if (minLength > maxLength) {
      throw new IllegalArgumentException("maxLength should greater than minLength!");
    }

    int length = Math.max(minLength, RANDOM.nextInt(maxLength));
    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      String chars = NUMBER;
      if (i == 0) {
        // 首个数字字符为0则没有意义
        chars = NUMBER.replace("0", "");
      }
      builder.append(chars.charAt(RANDOM.nextInt(chars.length())));
    }
    return builder.toString();
  }

  /** 通过指定长度，生成随机字符序列 */
  @Nonnull
  public static String stringOf(int length) {
    return stringOf(length, length);
  }

  /** 通过指定的最小和最大长度范围，随机取得一个字符长度数，然后按照此长度随机地生成一组密码 */
  @Nonnull
  public static String stringOf(int minLength, int maxLength) {
    if (minLength <= 0) {
      throw new IllegalArgumentException("minLength should greater than 0: " + minLength);
    }
    if (maxLength <= 0) {
      throw new IllegalArgumentException("maxLength should greater than 0: " + maxLength);
    }
    if (minLength > maxLength) {
      throw new IllegalArgumentException("maxLength should greater than minLength!");
    }

    int length = Math.max(minLength, RANDOM.nextInt(maxLength));
    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      builder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
    }
    return builder.toString();
  }
}
