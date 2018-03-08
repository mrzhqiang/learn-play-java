package models;

import io.ebean.Finder;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import util.RandomUtil;

@Entity
@Table(name = "account")
public class Account extends BaseModel {
  public static final Finder<Long, Account> find = new Finder<>(Account.class);
  private static final int MIN_LENGTH = 6;
  private static final int MAX_LENGTH = 16;

  /** 通过新用户资料申请账户 */
  public static Account of(User newUser) {
    Account account = new Account();
    int minLength = Math.max(MIN_LENGTH, newUser.hashCode() & 0xF);
    account.number = RandomUtil.numberOf(minLength, MAX_LENGTH - minLength);
    account.password = RandomUtil.stringOf(minLength, MAX_LENGTH - minLength);
    account.level = 1;
    newUser.account = account;
    return account;
  }

  @Column(unique = true, nullable = false, length = 16)
  @Size(min = 6, max = 16)
  public String number;
  @NotNull
  @Size(min = 6, max = 16)
  public String password;
  public int level = 1;

  @ManyToOne
  public List<Client> clients;

  @Override public String toString() {
    return baseStringHelper()
        .add("number", number)
        .add("password", password)
        .add("level", level)
        .toString();
  }

  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), number, password, level);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Account)) {
      return false;
    }

    Account other = (Account) obj;
    return super.equals(obj)
        && Objects.equals(number, other.number)
        && Objects.equals(password, other.password)
        && Objects.equals(level, other.level);
  }
}
