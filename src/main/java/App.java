import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    /******************************************************
      Students: TODO: Display all restaurants on main page
    *******************************************************/
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("cuisines", Cuisine.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputtedCuisine = request.queryParams("cuisine");
      Cuisine newCuisine = new Cuisine(inputtedCuisine);
      newCuisine.save();
      model.put("restaurants", Restaurant.all());
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String inputtedName = request.queryParams("name");
      String inputtedDescription = request.queryParams("description");
      int inputtedCuisineId = Integer.parseInt(request.queryParams("cuisine_id"));

      Restaurant newRestaurant = new Restaurant(inputtedName, inputtedCuisineId, inputtedDescription);
      newRestaurant.save();

      model.put("inputtedCuisineId", inputtedCuisineId);
      model.put("restaurants", Restaurant.all());
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));

      model.put("restaurant", restaurant);
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurant/:id/updateName", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      String inputtedName = request.queryParams("name");
      restaurant.updateName(inputtedName);

      model.put("restaurant", restaurant);
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurant/:id/updateDescription", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      String inputtedDescription = request.queryParams("description");
      restaurant.updateDescription(inputtedDescription);

      model.put("restaurant", restaurant);
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/restaurant/:id/updateCuisine", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Restaurant restaurant = Restaurant.find(Integer.parseInt(request.params(":id")));
      int inputtedCuisineId = Integer.parseInt(request.queryParams("cuisine_id"));
      restaurant.updateCuisine(inputtedCuisineId);

      model.put("restaurant", restaurant);
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /******************************************************
    STUDENTS:
    TODO: Create page to display information about the selected restaurant
    TODO: Create page to display restaurants by cuisine type
    *******************************************************/

  }
}
