import java.util.Hashtable;

/**
 * Created by asherfischbaum on 21/11/15.
 */
public class FoodStore { //instantiate a new foodStore for every enclosure and the zoo as a whole


    Hashtable<String, Integer> foodTypes;

    /**
     *  in the constructor we create a new hashtable and initially put in the foods that can be in it.
     */
    public FoodStore(){
        foodTypes = new Hashtable();
        foodTypes.put("hay", 0);
        foodTypes.put("steak", 0);
        foodTypes.put("fruit", 0);
        foodTypes.put("celery", 0);
        foodTypes.put("fish", 0);
        foodTypes.put("ice cream", 0);
    }


    /**
     *
     * when this method is called the amount of the food called is incremented
     *
     * @param foodToAdd takes which food to add as a parameter
     */
    public void addFood(String foodToAdd){
        foodTypes.put(foodToAdd, (foodTypes.get(foodToAdd) + 1));
    }

    /**
     *
     * here we are adding a specific amount of a specific food.
     *
     * @param foodToAdd the key of the food to add
     * @param amountOfFood the amount of that food to add
     */
    public void addFood(String foodToAdd, int amountOfFood){
        foodTypes.put(foodToAdd, amountOfFood);
    }

    /**
     * @param foodToTake is the amount of food taken out of the foodStore when an animal eats
     */
    public void takeFood(String foodToTake){
        foodTypes.put(foodToTake, foodTypes.get(foodToTake) - 1);
    }

    /**
     * @param foodToTake the food key that we want to remove from
     * @param howMuch how much of that food to remove
     */
    public void takeFood(String foodToTake, int howMuch){
        foodTypes.put(foodToTake, foodTypes.get(foodToTake) - howMuch);
    }


}

