package models.form;

import java.util.Optional;
import java.util.regex.Pattern;
import utils.Accounts;

public class AccountData {
  private static final Pattern patternNumber = Pattern.compile("^[1-9][0-9]{5,15}");

  private String number;
  private String password;

  public AccountData() {
  }

  public Optional<String> checkError() {
    if (number == null || number.isEmpty()) {
      return Optional.of("号码为空！");
    }

    if (!patternNumber.matcher(number).matches()) {
      return Optional.of("号码有误！");
    }

    if (password == null || password.isEmpty()) {
      return Optional.of("请输入有效的密码！");
    }

    if (!Accounts.verify(number, password)) {
      return Optional.of("登陆失败，不存在的账号或密码错误！");
    }

    return Optional.empty();
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
