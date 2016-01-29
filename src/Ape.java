/**
 * Created by asherfischbaum on 21/11/15.
 */
public abstract class Ape extends Animal {
    /**
     *
     * The ape constructor doesn't actually create an animal, rather is used as a super class that is extended by the
     * two apes in our zoo: Chimpanzee and gorilla
     *
     * the constructor for the Ape class takes in all the same parameters as the animal class and then passes them into
     * the animal constructor using the super call.
     *
     * @param animalAge the age of the animal to be passed in at creation
     * @param animalLifeExpectancy this is set in each specific animals constructor
     * @param animalEats this is also specified in subclass constructors, exactly what the animal eats
     * @param animalGender this is specified when a new animal is instantiated.
     * @param animalHealth specified when each new animal is instantiated
     * @param thisAnimalsEnclosure this must be passed in when a new animal is instantiated
     */
    public Ape(int animalAge, int animalLifeExpectancy, String[] animalEats, char animalGender, int animalHealth,
               Enclosure thisAnimalsEnclosure) {
        super(animalAge, animalLifeExpectancy, animalEats, animalGender, animalHealth, thisAnimalsEnclosure);
    }

    /**
     *
     * a month passes is the method in the animals that calls the processes that an animal fulfils every month. this is
     * filling the abstract method in the animal class.
     *
     * first thing we do is decrement the health by 2. this is because the spec tells us that each animal goes down by 2
     * health points each month.
     *
     * now we are dealing with the life expectancy. each animal has a different life expectancy and that drops by one
     * every month.
     *
     * next we are making the animal eat. first we check whether there is enough of the specific foods that this animal
     * can eat. if so we call animal.eat() that also checks whether the animal has already eaten. Final when the animal
     * eats it produces waste depends on what food was eaten.
     *
     * we then check whether the animal got a disease this month. if it does the health will be affected or they may die
     *
     * finally we check if the animal is still live by making sure there health and life expectancy are greater than 0.
     * if they are both greater than 0 this method will return true, indicating that the animal is alive. otherwise it
     * returns false indicating the animal has died.
     *
     * @return if the animal is alive or not
     */
    public boolean aMonthPasses(){

        System.out.println("Ape a month passes");
        String foodEaten = "";
        decreaseHealth(); // check if you can't pass in parameters
        decreaseHealth();

        increaseAge();



        if (animalsEnclosure.thisEnclosuresFoodStore.foodTypes.get(eats[0]) > 0 ||
                animalsEnclosure.thisEnclosuresFoodStore.foodTypes.get(eats[1]) > 0){
            foodEaten = eat();
        }

        switch (foodEaten){
            case "fruit":
                animalsEnclosure.addWaste(3);
                setHealth(getHealth()+2);
                break;
            case "ice cream":
                animalsEnclosure.addWaste(3);
                setHealth(getHealth() + 1);
                break;
        }


        boolean isAlive = true;

        String animalsAilement = disease();

        switch (animalsAilement){
            case "Black plague":
                isAlive = false;
                break;
            case "Heart attack":
                setHealth(getHealth() - 8);
                break;
            case "TB":
                setHealth(getHealth() - 5);
                break;
            case "respiratory virus":
                setHealth(getHealth() - 3);
                break;
            case "broken leg":
                setHealth(getHealth() - 2);
                break;
            case "allGood":
                break;
        }

        if (getLifeExpectancy() == getAge()|| getHealth() <= 0){
            isAlive = false;
        }

        return isAlive;
    }

    public abstract void treat();
}
