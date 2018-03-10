package models;

import io.ebean.annotation.EnumValue;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import play.data.format.Formats;

@Entity
@Table(name = "user")
public class User extends BaseModel {

  public enum Sex {
    @EnumValue("M")
    MALE,
    @EnumValue("F")
    FEMALE
  }

  @Column(nullable = false, length = 16)
  public String nickname;
  @Column(nullable = false)
  public Sex sex = Sex.MALE;
  public int age;
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public Timestamp birthday;
  @Column(length = 16)
  public String blood;
  @Column(length = 16)
  public String profession;
  public String location;
  @Column(length = 32)
  public String school;
  @Column(length = 32)
  public String company;
  @Column(length = 11)
  public String phone;
  @Column(length = 64)
  public String email;
  @Column(length = 140)
  public String signature;
  @Column(length = 128)
  public String description;
  
  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), nickname, sex, age, birthday, blood, profession,
        location, school, company, phone, email, signature, description);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof User)) {
      return false;
    }

    User other = (User) obj;
    return super.equals(obj)
        && Objects.equals(nickname, other.nickname)
        && Objects.equals(sex, other.sex)
        && Objects.equals(age, other.age)
        && Objects.equals(birthday, other.birthday)
        && Objects.equals(blood, other.blood)
        && Objects.equals(profession, other.profession)
        && Objects.equals(location, other.location)
        && Objects.equals(school, other.school)
        && Objects.equals(company, other.company)
        && Objects.equals(phone, other.phone)
        && Objects.equals(email, other.email)
        && Objects.equals(signature, other.signature)
        && Objects.equals(description, other.description);
  }

  @Override public String toString() {
    return baseStringHelper()
        .add("nickname", nickname)
        .add("sex", sex)
        .add("age", age)
        .add("birthday", birthday)
        .add("blood", blood)
        .add("profession", profession)
        .add("location", location)
        .add("school", school)
        .add("company", company)
        .add("phone", phone)
        .add("email", email)
        .add("signature", signature)
        .add("description", description)
        .toString();
  }
}
