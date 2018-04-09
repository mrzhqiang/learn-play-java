package db;

import com.datastax.driver.mapping.MappingManager;
import javax.inject.Inject;
import models.User;
import org.junit.Before;
import org.junit.Test;

public class CassandraManagerTest {

  private CassandraManager manager;

  @Before
  @Inject
  public void generateManager() {
    this.manager = new CassandraManager();
  }

  @Test
  public void testCassandraMapping() {
    MappingManager mappingManager = manager.getMappingManager();
    User accessor = mappingManager.createAccessor(User.class);
    System.out.println(accessor);
  }

  @Test
  public void testCassandraSession() {

  }
}
