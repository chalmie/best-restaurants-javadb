import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void getType_returnTypeOfCuisine() {
    Cuisine newCuisine = new Cuisine("Lean");
    assertEquals(newCuisine.getType(), "Lean");
  }

  @Test
  public void equals_returnsTrueIfTypesAreTheSame() {
    Cuisine firstCuisine = new Cuisine("Lean");
    Cuisine secondCuisine = new Cuisine("Lean");
    assertTrue(firstCuisine.equals(secondCuisine));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Cuisine newCuisine = new Cuisine("Lean");
    newCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(newCuisine));
  }

  @Test
  public void find_findCuisineInDatabase_true() {
    Cuisine newCuisine = new Cuisine("Lean");
    newCuisine.save();
    Cuisine savedCuisine = Cuisine.find(newCuisine.getId());
    assertTrue(newCuisine.equals(savedCuisine));
  }


}
