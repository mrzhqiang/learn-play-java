package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account")
public class Account extends BaseModel {
  @NotNull
  @Size(min = 6, max = 16)
  public String number;
  @NotNull
  @Size(min = 6, max = 16)
  public String password;
  public int level = 1;
  @ManyToOne(optional = false)
  public User user;

  public static final Finder<Long, Account> find = new Finder<>(Account.class);

  @Override public String toString() {
    return baseStringHelper()
        .add("number", number)
        .add("password", password)
        .add("level", level)
        .add("user", user)
        .toString();
  }

  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), number, password, level, user);
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
        && Objects.equals(level, other.level)
        && Objects.equals(user, other.user);
  }
}
