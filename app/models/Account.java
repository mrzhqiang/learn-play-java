package models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends BaseModel {

  @Column(unique = true, nullable = false, length = 16)
  public String number;
  @Column(nullable = false, length = 16)
  public String password;
  public int level = 1;

  @OneToOne(optional = false)
  public User user;

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

  @Override public String toString() {
    return baseStringHelper()
        .add("number", number)
        .add("password", password)
        .add("level", level)
        .add("user", user)
        .toString();
  }
}
