package models;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;
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
  @ManyToOne
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
}
