package util;

import java.security.SecureRandom;
import java.util.Random;
import models.Account;
import models.User;

public final class Accounts {
  private Accounts() {
    // no instance
  }

  private static final String CHAR =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static final String NUMBER = "0123456789";
  private static final Random RANDOM = new SecureRandom();

  private static String randomNumberOf(int length) {
    if (length < 6) {
      length = 6 + (int) (Math.random() * 10);
    }
    StringBuilder builder = new StringBuilder(length);
    String chars = NUMBER;
    for (int i = 0; i < length; i++) {
      int index = RANDOM.nextInt(chars.length());
      if (i == 0) {
        index = (int) (1 + Math.random() * 9);
      }
      builder.append(chars.charAt(index));
    }
    return builder.toString();
  }

  private static String randomPasswordOf(int length) {
    if (length < 6) {
      length = 6 + (int) (Math.random() * 10);
    }
    StringBuilder builder = new StringBuilder(length);
    String chars = CHAR + NUMBER;
    for (int i = 0; i < length; i++) {
      builder.append(chars.charAt(RANDOM.nextInt(chars.length())));
    }
    return builder.toString();
  }

  public static Account randomOf(User user) {
    Account account = new Account();
    account.number = randomNumberOf(user.hashCode() & 0xF);
    account.password = randomPasswordOf(user.hashCode() & 0xF);
    account.user = user;
    return account;
  }
}
