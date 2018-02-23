package models;

import io.ebean.Finder;
import io.ebean.Model;
import java.security.SecureRandom;
import java.util.Random;
import javax.persistence.*;
import play.data.validation.Constraints;

@Entity(name = "account")
public class Account extends Model {
  @Id
  @Constraints.Min(6)
  public String username;
  @Constraints.Min(6)
  public String password;
  public long u_id;
  public int type;

  public static final Finder<String, Account> find = new Finder<>(Account.class);

  @Override public String toString() {
    return "(["
        + this.getClass().getSimpleName()
        + "] username="
        + username
        + ", password="
        + password
        + ", u_id="
        + u_id
        + ", type="
        + type
        + ")";
  }

  private static final String CHARS =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final Random RANDOM = new SecureRandom();

  private static String randomString(int length) {
    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      builder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
    }
    return builder.toString();
  }

  public static Account randomCreate(int userId) {
    Account account = new Account();
    account.username = randomString(6);
    account.password = randomString(6);
    account.u_id = userId;
    account.type = RANDOM.nextInt(2);
    return account;
  }
}
