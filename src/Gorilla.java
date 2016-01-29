/**
 * Created by asherfischbaum on 21/11/15.
 */
public class Gorilla extends Ape {
    /**
     *
     * The Gorrilla constructor actually creates a blue print of a gorilla.
     *
     * the constructor for the gorilla takes in parameter such as the health, enclosure and gender of the animal. some
     * parameters of the superclass such as what it eats and life expectancy that do not change for different copies of
     * the same animal.
     *
     * @param animalAge the age of the animal to be passed in at creation
     * @param animalGender this is specified when a new animal is instantiated.
     * @param animalHealth specified when each new animal is instantiated
     * @param thisAnimalsEnclosure this must be passed in when a new animal is instantiated
     */
    public Gorilla(int animalAge, char animalGender, int animalHealth, Enclosure thisAnimalsEnclosure) {
        super(animalAge, 32, new String[]{"fruit", "ice cream"}, animalGender, animalHealth, thisAnimalsEnclosure);
        this.animalType = "gorilla";
        favouriteTreat = "Painting";
    }
    /**
     * if this method is called the animal is treated. in this case it means that the gorilla does some painting
     */
    public void treat(){
        painting();
    }

    /**
     * when this method is called because the animal is treated then its health goes up by 4.
     */
    public void painting(){
        setHealth(getHealth() + 4);
    }
}




