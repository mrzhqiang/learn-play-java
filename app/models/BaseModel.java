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
  public Long id;
  @Version
  public Long version;
  @WhenCreated
  public Timestamp created;
  @WhenModified
  public Timestamp modified;

  public MoreObjects.ToStringHelper baseStringHelper() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("version", version)
        .add("created", created)
        .add("modified", modified);
  }

  @Override public int hashCode() {
    return Objects.hash(id, version, created, modified);
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
        && Objects.equals(modified, other.modified);
  }
}
