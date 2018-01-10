package models;

public class Person {
  private static long LAST_ID = 1;

  private long id;
  private String name;
  private int level;
  private String occupation;

  public Person(long id, String name, int level, String occupation) {
    this.id = id;
    this.name = name;
    this.level = level;
    this.occupation = occupation;
  }

  public static Person of(String name, String occupation) {
    return new Person(LAST_ID++, name, 1, occupation);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }
}
