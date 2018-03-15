package models.forms;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import models.utils.ObjectUtil;

public class AccountData {
  private static final Pattern patternNumber = Pattern.compile("^[1-9][0-9]{5,15}");

  private String number;
  private String password;

  public Optional<String> checkError() {
    if (number == null || number.isEmpty()) {
      return Optional.of("登陆失败，号码为空！");
    }

    if (!patternNumber.matcher(number).matches()) {
      return Optional.of("登陆失败，号码格式不正确！");
    }

    if (password == null || password.isEmpty()) {
      return Optional.of("登陆失败，请输入密码！");
    }
    return Optional.empty();
  }

  public String getNumber() {
    return ObjectUtil.nullToDefault(number, "");
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getPassword() {
    return ObjectUtil.nullToDefault(password, "");
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override public int hashCode() {
    return Objects.hash(number, password);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof AccountData)) {
      return false;
    }

    AccountData other = (AccountData) obj;
    return Objects.equals(number, other.number)
        && Objects.equals(password, other.password);
  }
}
