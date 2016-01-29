import java.io.*;
import java.util.ArrayList;

public class Zoo {

    ArrayList<Zookeeper> zooKeepers;
    Enclosure[] enclosuresInTheZoo;
    FoodStore zooFoodStore;
    PrintStream saveCurrentState;


    /**
     * whenever we instantiate a new instance of a zoo we automatically create a foodstore that will supply all other
     * foodstores in the zoo and we create an arraylist of zookeepers that will look after the animals.
     */
    public Zoo() {
        zooFoodStore = new FoodStore();
        zooKeepers = new ArrayList<>();
    }

    /**
     *this takes in a config file from the command line and reads it to populate the zoo.
     *
     * @param args a configuration file passed by the user through the command line
     */
    public static void main(String[] args) {

        FileRead reader = new FileRead(args[0]);
        reader.readTheConfig().go();

    }

    /**
     * this will loop through ten months of zoo lifecycle before coming to a stop
     */
    public void go() {

        for (int i = 0; i < 20; i++) {
            System.out.println("============================================  month" + " " + i + "  =========================================");

            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
            }

            this.aMonthPasses();
        }
    }


    /**
     * by the end of the aMonthPasses method, aMonthPasses will be called on everything in the zoo.
     *
     * first we loop through all the zookeepers calling aMonth passes on them
     *
     * then we loop through all the enclosures and call aMonthPasses on them
     *
     * after all the actions of the month have been completed we want to save the current state of the zoo so if the
     * simulation is stopped we can pass it in as a config file and start from where we left of.
     *
     * first we print zoo. this tells the programme when reading the config file that we are starting a new zoo and that
     * whatever is written below is part of the zoo.
     *
     * we then loop through all the food in the zoo food store printing out what food and how much of it is available.
     *
     * we then start looping through all the enclosures in the zoo, print out the enclosure and the amount of waste in
     * it. for every enclosure we then loop through all the animals, print out the type, gender, age, health and its
     * enclosure. we then loop through all the food in the foodstore and the amount of it htere is available. finaly we
     * loop through the zookeepers that belong to this enclosure.
     */
    public void aMonthPasses() {


        // monthly zoo processes

        System.out.println("calling a month passes on teh zookeepers");
        for (Zookeeper zookeeper : zooKeepers) {
            System.out.println("calling a month passes on " + zookeeper.getType());
            zookeeper.aMonthPasses();
            // how to print the name rather than the memory adress?
        }

        for (int i = 0; i < (enclosuresInTheZoo.length); i++) {
            enclosuresInTheZoo[i].aMonthPasses();

            for (int j = 0; j < enclosuresInTheZoo[i].animalsInTheEnclosure.size(); j++){
                enclosuresInTheZoo[i].animalsInTheEnclosure.get(j).aMonthPasses();
            }

            for (int j = 0; j < enclosuresInTheZoo[i].animalsInTheEnclosure.size(); j++){
                if (!enclosuresInTheZoo[i].animalsInTheEnclosure.get(j).isAlive()){
                    enclosuresInTheZoo[i].animalsInTheEnclosure.remove(j);
                }
            }

        }


        // print out the current state of the zoo

        try {
            saveCurrentState = new PrintStream(new File("zooCurrentState.txt"));

        } catch (FileNotFoundException e) {
            System.err.println("This file has not been found");
        }

        saveCurrentState.println("zoo:");

        for (String foodTypeKey : zooFoodStore.foodTypes.keySet()) {
            saveCurrentState.println(foodTypeKey + ":" + zooFoodStore.foodTypes.get(foodTypeKey));
        }

        for (int i = 0; i < enclosuresInTheZoo.length; i++) {

            saveCurrentState.println("enclosure" + ":" + enclosuresInTheZoo[i].getWasteSize());
            for (int j = 0; j < enclosuresInTheZoo[i].animalsInTheEnclosure.size(); j++) {

                saveCurrentState.println(enclosuresInTheZoo[i].animalsInTheEnclosure.get(j).getAnimalType() + ":" +
                enclosuresInTheZoo[i].animalsInTheEnclosure.get(j).getGender() + "," +
                enclosuresInTheZoo[i].animalsInTheEnclosure.get(j).getAge() + "," +
                enclosuresInTheZoo[i].animalsInTheEnclosure.get(j).getHealth() + "," + (i+1));
            }


            for (String foodTypeKey : enclosuresInTheZoo[i].thisEnclosuresFoodStore.foodTypes.keySet()) {
                saveCurrentState.println(foodTypeKey + ":" +
                        enclosuresInTheZoo[i].thisEnclosuresFoodStore.foodTypes.get(foodTypeKey));
            }

            for(Zookeeper keeper : enclosuresInTheZoo[i].zookeeperArrayList){
                saveCurrentState.println(keeper.getType() + ":" + i);
            }

        }


            System.out.println("FINISHED this month");
        }

    }

