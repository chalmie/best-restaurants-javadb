import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int id;
  private String type;

  public Cuisine (String type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }


  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
        this.getId() == newCuisine.getId();
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cuisine (type) VALUES (:type)";
      this.id = (int) con.createQuery(sql,true)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Cuisine> all() {
    String sql = "SELECT * FROM cuisine";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  //UPDATE
  // public void update(String newType) {
  //   this.mType = newType;
  //   try(Connection con = DB.sql2o.open()) {
      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
  //   }
  // }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cuisine WHERE id=:id";
        con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
    }
  }

  public static Cuisine find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cuisine WHERE id=:id";
      Cuisine cuisine = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
        return cuisine;
    }
  }

  public List<Restaurant> getRestaurants() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE cuisine_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Restaurant.class);
    }
  }



}
