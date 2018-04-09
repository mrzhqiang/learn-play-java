package db;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import db.util.CustomConfiguration;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
public final class CassandraManager {

  private static final String CASSANDRA_URL = "cassandra.url";
  private static final String CASSANDRA_PORT = "cassandra.port";

  private final Cluster cluster;
  private final Session session;
  private final MappingManager mappingManager;

  public CassandraManager() {
    Map<String, String> cassandraConfig = CustomConfiguration.readOf(CASSANDRA_URL, CASSANDRA_PORT);
    PoolingOptions options = new PoolingOptions();
    options.setCoreConnectionsPerHost(HostDistance.LOCAL, 4)
        .setMaxConnectionsPerHost(HostDistance.LOCAL, 10)
        .setCoreConnectionsPerHost(HostDistance.REMOTE, 2)
        .setMaxConnectionsPerHost(HostDistance.REMOTE, 4)
        .setMaxRequestsPerConnection(HostDistance.LOCAL, 32);

    cluster = Cluster.builder().withPoolingOptions(options)
        .addContactPoint(cassandraConfig.get(CASSANDRA_URL))
        .build();
    session = cluster.connect();
    mappingManager = new MappingManager(session);
  }

  public Session getSession() {
    return session;
  }

  public MappingManager getMappingManager() {
    return mappingManager;
  }
}
