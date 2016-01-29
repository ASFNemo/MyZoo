/**
 * Created by asherfischbaum on 21/11/15.
 */
public class Penguin extends Animal {
    /**
     *
     * The Penguin constructor actually creates a blue print of a penguin.
     *
     * the constructor for the penguin takes in parameter such as the health, enclosure and gender of the animal. some
     * parameters of the superclass such as what it eats and life expectancy that do not change for different copies of
     * the same animal.
     *
     * @param animalAge the age of the animal to be passed in at creation
     * @param animalGender this is specified when a new animal is instantiated.
     * @param animalHealth specified when each new animal is instantiated
     * @param thisAnimalsEnclosure this must be passed in when a new animal is instantiated
     */
    public Penguin(int animalAge, char animalGender, int animalHealth, Enclosure thisAnimalsEnclosure) {
        super(animalAge, 15, new String[]{"fish", "ice cream"}, animalGender, animalHealth, thisAnimalsEnclosure);
        this.animalType = "penguin";
        favouriteTreat = "Watch A Movie";
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
     * finaly we check if the animal is still live by making sure there health and life expectancy are greater than 0.
     * if they are both greater than 0 this method will return true, indicating that the animal is alive. otherwise it
     * returns false indicating the animal has died.
     *
     * @return if the animal is alive or not
     */
    public boolean aMonthPasses(){
        System.out.println("penguin a  month passes");
        String foodEaten = "";
        decreaseHealth();
        decreaseHealth();
        increaseAge();

        if (animalsEnclosure.thisEnclosuresFoodStore.foodTypes.get(eats[0]) > 0 ||
                animalsEnclosure.thisEnclosuresFoodStore.foodTypes.get(eats[1]) > 0){
            foodEaten = eat();
        }

        switch (foodEaten){
            case "Fish":
                animalsEnclosure.addWaste(2);
                setHealth(getHealth()+3);
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


        if (getLifeExpectancy() == getAge() || getLifeExpectancy() <= 0){
            isAlive = false;
        }
        return isAlive;
    }

    /**
     * If this method is called the animal is treated. In this case it means that the penguin watches a film.
     *
     * the zookeepers know not to let them watch madagascar. We don't want a plot to escape!!
     */
    public void treat(){
        watchAFilm();
    }

    /**
     * when this method is called because the animal is treated then its health goes up by 2.
     */
    public void watchAFilm(){
        setHealth(getHealth() +2);
    }
}
