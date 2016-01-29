/**
 * Created by asherfischbaum on 21/11/15.
 */
public class Lion extends BigCat {
    /**
     *
     * The Lion constructor actually creates a blue print of a Lion.
     *
     * the constructor for the Lion takes in parameter such as the health, enclosure and gender of the animal. some
     * parameters of the superclass such as what it eats and life expectancy that do not change for different copies of
     * the same animal.
     *
     * @param animalAge the age of the animal to be passed in at creation
     * @param animalGender this is specified when a new animal is instantiated.
     * @param animalHealth specified when each new animal is instantiated
     * @param thisAnimalsEnclosure this must be passed in when a new animal is instantiated
     */
    public Lion(int animalAge, char animalGender, int animalHealth, Enclosure thisAnimalsEnclosure) {
        super(animalAge, animalGender, animalHealth, thisAnimalsEnclosure);
        this.animalType = "lion";
    }

    /**
     * if this method is called the animal is treated. in this case it means that the lion get stroked
     */
    public void treat(){
        stroked();
    }

    /**
     /**
     * when this method is called because the animal is treated then its health goes up by 2.
     */
    public void stroked(){
        setHealth(getHealth() + 2);
    }
}
