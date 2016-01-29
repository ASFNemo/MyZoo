import java.util.Random;
/**
 * Created by asherfischbaum on 21/11/15.
 */
public abstract class Animal {

    int age;
    char gender;
    String[] eats;
    int health;
    int lifeExpectancy;
    String favouriteTreat;
    protected String animalType;
    Random random;
    Enclosure animalsEnclosure;


    /**
     *
     * In the Animal constructor we are creating all the properties a animal must have. Thus whenever someoneone
     * instantiates a new animal they must tell the programme the animals age, it's lif expectancy, what it eats, it's
     * gender, it's health and what enclosure it belongs to.
     *
     * @param animalAge the age of the animal to be passed in at creation
     * @param animalLifeExpectancy this is set in each specific animals constructor
     * @param animalEats this is also specified in subclass constructors, exactly what the animal eats
     * @param animalGender this is specified when a new animal is instantiated.
     * @param animalHealth specified when each new animal is instantiated
     * @param thisAnimalsEnclosure this must be passed in when a new animal is instantiated
     */
    public Animal(int animalAge, Integer animalLifeExpectancy, String[] animalEats, char animalGender, int animalHealth,
                  Enclosure thisAnimalsEnclosure){
        age = animalAge;
        lifeExpectancy = animalLifeExpectancy;
        eats = animalEats;
        gender = animalGender;
        health = animalHealth;
        animalsEnclosure = thisAnimalsEnclosure;
    }


    /**
     * @return the animal type
     */
    public String getAnimalType(){
        return animalType;
    }

    /**
     * @return the animals age
     */
    public int getAge(){
        // returns the animals age
        return age;
    }

    /**
     * increments the animals age
     */
    public void increaseAge(){
        age++;
    }

    /**
     * @return the animals age
     */
    public char getGender(){
        // returns the animals gender
        return gender;
    }

    /**
     * @return returns the life expectancy
     */
    public int getLifeExpectancy(){
        // returns the animals life expectancy
        return lifeExpectancy;
    }

    public void setLifeExpectancy(int lifeExpectance){
        /**
         *
         * this sets the animals new life expectancy. every month, each animals life expectancy is reduced by 1 as they
         * are have one month less left alive.
         *
         */
        lifeExpectancy = lifeExpectance;
    }

    /**
     *
     * in this method we are taking in a food as an arguement. we then check whether the animal can eat this food. if
     * this food can be eaten by the animal then it will return true otherwise it will return false.
     *
     * @param  food is the food to be the we are trying to feed the animal
     * @return this returns a boolean if they can or cannot eat this food
     */
    public boolean canEat(String food){

        boolean foodCanBeEaten = false;

        for (int i = 0; i <= eats.length - 1; i++){
            if(eats[i].equals(food)){
                foodCanBeEaten = true;
            }
        }

        return foodCanBeEaten;
    }

    /**
     *
     * in this method we are eating getting the animal to eat. this first loops through the array of foods that can be
     * eaten. If the animal has not yet eaten and there is some of that  food in the enclosures food store then the
     * animal will eat. if there isn't of this particular food then the next will be tried.
     *
     * @return the method will return what food will be eaten
     */
    public String eat(){

        String foodEaten = null;
        int amountEaten = 0;
        int i = 0;

        while (i < eats.length){

            while (amountEaten < 1 && animalsEnclosure.thisEnclosuresFoodStore.foodTypes.get(eats[i]) > 0) {

                if (canEat(eats[i])) {

                    animalsEnclosure.thisEnclosuresFoodStore.takeFood(eats[i]);
                    amountEaten++;
                    foodEaten = eats[i];

                }

            }
            i++;
        }
        return foodEaten;
    }

    /**
     *
     * in this method we are allowing the new health of an animal to be set. for example if the animal is treated we
     * will add however much health is gained from a treat to there current health.
     *
     * In the spec however it does say that an animal can have a max health of 10. So we check, if the current health +
     * the health gained is greater or = 10 we set it to 10. otherwise the health is set to the addition/subtraction of
     * the health and the health gained.
     *
     * @param newHealth is the new health of the animal
     */
    public void setHealth(int newHealth){

        if (newHealth >= 10){
            health = 10;
        } else if (newHealth <= 0){
            health = 0;
        } else {
            health = newHealth;
        }
    }

    /**
     * @return the health of the animal
     */
    public int getHealth(){
        return health;
    }

    /**
     * decrements the animals health
     */
    public void decreaseHealth(){
        health--;
    }

     /**
     * @return a string that is the animals favourite food.
     */
    public String getFavouriteTreat(){
        return favouriteTreat;
    }

    public void treat(){
        // this is mmodified in each animal depending on what treat they like.
    }

    /**
     *sets the enclosure the animal belongs to.
     * @param enclosureToLiveIn is the enclosure that the animal will be assigne to
     */
    public void setEnclosure(Enclosure enclosureToLiveIn){
        animalsEnclosure = enclosureToLiveIn;
    }

    public abstract boolean aMonthPasses(); // an abstract method that is explained in subclasses.

    /**
     *
     * this method is called on each animal everymonth and gives the animal a random probability of getting an ailment
     *
     * @return the disease that the animal has.
     */
    public String disease(){

        String animalsAilment = "allGood";
        random = new Random();
        double randNumber = random.nextDouble();


        if (randNumber < 0.01){
            animalsAilment = "Black plague";
        } else if (0.01 <= randNumber && 0.09 > randNumber){
            animalsAilment = "Heart attack";
        } else if (0.09 <= randNumber && randNumber < 0.21){
            animalsAilment = "TB";
        } else if (0.21 <= randNumber && randNumber < 0.35){
            animalsAilment = "respiratory virus";
        } else if (0.35 <= randNumber && randNumber < 0.52){
            animalsAilment = "broken leg";;
        }

        return animalsAilment;
    }

    /**
     * Here we are checking whether the animal is still alive.
     *
     * @return whether the animal is alive
     */

    public boolean isAlive(){

        boolean isAlive = true;

        if (getHealth() <= 0 || getAge() == lifeExpectancy){
            isAlive = false;
        }

        return isAlive;
    }
}
