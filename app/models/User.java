package models;

import io.ebean.Finder;
import io.ebean.annotation.EnumValue;
import io.ebean.annotation.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import play.data.format.Formats;

@Entity
@Table(name = "user")
public class User extends BaseModel {

  public enum Sex {
    @EnumValue("M")
    MALE,
    @EnumValue("F")
    FEMALE,
    @EnumValue("O")
    OTHER
  }

  @Size(max = 24)
  public String nickname;
  @NotNull
  public Sex sex = Sex.MALE;
  @Size(max = 3)
  public int age;
  @Formats.DateTime(pattern = "yyyy-MM-dd")
  public Timestamp birthday;
  @Size(max = 6)
  public String blood;
  @Size(max = 32)
  public String profession;
  @Size(max = 128)
  public String location;
  @Size(max = 32)
  public String school;
  @Size(max = 32)
  public String company;
  @Size(max = 24)
  public String phone;
  @Size(max = 128)
  public String email;
  @Size(max = 140)
  public String signature;
  @Size(max = 200)
  public String description;

  public static final Finder<Long, User> find = new Finder<>(User.class);

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

  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), nickname, sex, age, birthday, blood
        , profession, location, school, company, phone, email, signature, description);
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
}
