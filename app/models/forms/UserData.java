package models.forms;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import models.User;

public class UserData {

  private String nickname;
  private String sex;
  private String age;
  private Date birthday;
  private String blood;
  private String profession;
  private String location;
  private String school;
  private String company;
  private String phone;
  private String email;
  private String signature;
  private String description;

  public Optional<String> checkError() {
    if (nickname == null || nickname.isEmpty()) {
      return Optional.of("昵称为空！");
    }

    if (sex == null) {
      sex = "男";
    }

    if (birthday == null) {
      birthday = new Date();
      age = "1";
    }

    return Optional.empty();
  }

  public User toUser() {
    User user = new User();
    user.nickname = nickname;
    user.sex = User.Sex.of(sex);
    user.birthday = new Timestamp(birthday.getTime());
    user.age = Integer.valueOf(age);
    user.blood = blood;
    user.profession = profession;
    user.location = location;
    user.school = school;
    user.company = company;
    user.phone = phone;
    user.email = email;
    user.signature = signature;
    user.description = description;
    return user;
  }

  public void countAge() {
    if (birthday != null) {
      Calendar calendar = Calendar.getInstance();
      int year = calendar.get(Calendar.YEAR);
      calendar.setTime(birthday);
      int age = year - calendar.get(Calendar.YEAR);
      this.age = String.valueOf(age > 0 ? age : "1");
    }
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
    countAge();
  }

  public String getBlood() {
    return blood;
  }

  public void setBlood(String blood) {
    this.blood = blood;
  }

  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
