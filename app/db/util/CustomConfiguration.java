package db.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Nonnull;

public final class CustomConfiguration {
  private CustomConfiguration() {
    // no instance
  }

  private final static File file = new File("./conf/application.conf");

  @Nonnull
  public static Properties readProperties() {
    Properties properties = new Properties();
    try (Reader reader = new FileReader(file)) {
      properties.load(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties;
  }

  @Nonnull
  public static Map<String, String> readOf(@Nonnull String... propertyNames) {
    Properties properties = readProperties();
    Map<String, String> maps = new HashMap<>(properties.size());
    for (String name : propertyNames) {
      maps.put(name, properties.getProperty(name));
    }
    return maps;
  }
}
