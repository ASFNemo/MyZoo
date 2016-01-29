import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by asherfischbaum on 21/11/15.
 */
public class Enclosure {

    ArrayList<Animal> animalsInTheEnclosure;
    ArrayList<Zookeeper> zookeeperArrayList;
    FoodStore thisEnclosuresFoodStore;
    int waste;


    /**
     *
     * the constructor of the enclosure class takes an amount of waste as a parameter. thus when we create a enclosure
     * we can specify how much waste it starts with.
     *
     * when we create an enclosure we automatically create an arraylist of animals in the enclosure. we instantiate a
     * new arraylist.
     *
     * @param amountOfWaste the amount of waste the enclosure should start with.
     */
    public Enclosure(int amountOfWaste){
        animalsInTheEnclosure = new ArrayList<>();
        thisEnclosuresFoodStore = new FoodStore();
        waste = amountOfWaste;
        zookeeperArrayList = new ArrayList<>();
    }

    /**
     *
     * we also provide a default constructor for the enclosure class that does not take any parameters, thus
     * automatically setting the waste level to 50.
     *
     * as with the other constructer this creates an arraylists of animals in the enclosure and instantiates a new
     * foodstore.
     *
     */


    /**
     *
     * what we are aiming to do here is check whether there already is 20 animals in this enclosure. if there is
     * not we add an animal. if there is we throw an exception and do not allow it in. maybe we start another enclosure
     *
     * @param animalToAdd the animal that should be added to the enclosure.
     * @throws Exception throws an exception should to many animals be added to the enclosure
     */
    public void addAnimal(Animal animalToAdd) throws Exception{

        if (size() < 20){
            animalsInTheEnclosure.add(animalToAdd);
        } else {
            throw new Exception("There are too many animals in this enclosure!!");
        }
    }

    /**
     *
     * here we are removing animals from an enclosure, in our simulation this would be because an animal died.
     *
     * @param animalToremove the animal that should be removed is taken as a parameter
     */
    public void removeAnimal(Animal animalToremove){
        animalsInTheEnclosure.remove(animalToremove);
    }

    /**
     *
     * here we are managing how much waste there is. in this method we are adding waste to an enclosure. this is done by
     * animals after they have eaten
     *
     * @param amountOfWasteToAdd the amount of waste that should be added to the current pile as this is different
     *                           dependant on what food was eaten by the animal
     */
    public void addWaste(int amountOfWasteToAdd){
        waste = waste + amountOfWasteToAdd;
    }

    /**
     *
     * here we are managing how much food to remove. the amount of waste to remove is passed in as a zookeeper can only
     * take out so much waste.
     *
     * @param amountOfWasteToRemove the amount of waste to remove
     */
    public void removeWaste(int amountOfWasteToRemove){
        waste = waste - amountOfWasteToRemove;
    }

    /**
     * @return the amount of waste
     */
    public int getWasteSize(){
        return waste;
    }

    /**
     * @return the enclosure an animal is a member of.
     */
    public FoodStore getThisEnclosuresFoodStore(){
        return thisEnclosuresFoodStore;
    }

    /**
     * @return the amount of animals in this enclosure
     */
    public int size(){
        return animalsInTheEnclosure.size();
    }

    /**
     * this loops through all the animals in the enclosure and calls a month passes on them.
     */
    public void aMonthPasses(){


    }

    /**
     *
     * this puts the zookeeper taht is being created into this enclosures zooKeeper arraylist
     *
     * @param keeper this takes a zookeeper as a parameter
     */

    public void addKeeper(Zookeeper keeper){
        this.zookeeperArrayList.add(keeper);
    }
}
