package models;

import com.google.common.base.MoreObjects;
import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
abstract class BaseModel extends Model {
  @Id
  Long id;
  @Version
  Long version;
  @WhenCreated
  Timestamp created;
  @WhenModified
  Timestamp updated;

  MoreObjects.ToStringHelper baseStringHelper() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("version", version)
        .add("created", created)
        .add("updated", updated);
  }

  @Override public int hashCode() {
    return Objects.hash(id, version, created, updated);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof BaseModel)) {
      return false;
    }

    BaseModel other = (BaseModel) obj;
    return Objects.equals(id, other.id)
        && Objects.equals(version, other.version)
        && Objects.equals(created, other.created)
        && Objects.equals(updated, other.updated);
  }
}
