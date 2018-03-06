package models;

import io.ebean.annotation.NotNull;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client")
public class Client extends BaseModel {
  @NotNull
  @Size(max = 32)
  @Column(unique = true)
  public String username;
  @NotNull
  @Column(unique = true)
  public UUID password;
  public String description;

  @Override public String toString() {
    return baseStringHelper()
        .add("name", username)
        .add("password", password)
        .add("description", description)
        .toString();
  }

  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), username, password, description);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Client)) {
      return false;
    }

    Client other = (Client) obj;
    return super.equals(obj)
        && Objects.equals(username, other.username)
        && Objects.equals(password, other.password)
        && Objects.equals(description, other.description);
  }
}
