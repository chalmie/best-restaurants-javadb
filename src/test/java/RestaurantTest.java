import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfSame(){
    Restaurant firstRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    Restaurant secondRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_returnsListOfRestaurants() {
    Restaurant newRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    newRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(newRestaurant));
  }

  @Test
  public void save_assignsIdToObject() {
    Restaurant newRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(newRestaurant.getId(), savedRestaurant.getId());
  }

  @Test
  public void find_findsRestaurantinDatabase_true() {
    Restaurant newRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(newRestaurant.getId());
    assertTrue(newRestaurant.equals(savedRestaurant));
  }

  @Test
  public void delete_deletesRestaurantFromDatabase_true() {
    Restaurant newRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    newRestaurant.save();
    newRestaurant.delete();
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void updateName_updatesRestaurantName_true() {
    Restaurant newRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    newRestaurant.save();
    newRestaurant.updateName("Porch Door");
    assertEquals("Porch Door", newRestaurant.getName());
  }

  @Test
  public void updateDescription_updatesRestaurantDescription_true() {
    Restaurant newRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    newRestaurant.save();
    newRestaurant.updateDescription("Drafty");
    assertEquals("Drafty", newRestaurant.getDescription());
  }

  @Test
  public void updateCuisine_updatesCuisineId_true() {
    Restaurant newRestaurant = new Restaurant("Screen Door", 1, "A little stuffy");
    newRestaurant.save();
    newRestaurant.updateCuisine(3);
    assertEquals(newRestaurant.getCuisineId(), 3);
  }
}
