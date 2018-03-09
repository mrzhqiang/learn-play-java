package models;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends BaseModel {

  @Column(unique = true, nullable = false, length = 24)
  public String name;
  @Column(unique = true, nullable = false)
  public UUID apikey;
  public String description;

  @Override public int hashCode() {
    return Objects.hash(super.hashCode(), name, apikey, description);
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
        && Objects.equals(name, other.name)
        && Objects.equals(apikey, other.apikey)
        && Objects.equals(description, other.description);
  }

  @Override public String toString() {
    return baseStringHelper()
        .add("name", name)
        .add("apikey", apikey)
        .add("description", description)
        .toString();
  }
}
