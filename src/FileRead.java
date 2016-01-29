import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileRead {

    Zoo zoo;
    //Scanner scanner = new Scanner(System.in);

    BufferedReader reader;
    BufferedReader reader2;

    /**
     *
     * @param fileToRead we take the configuration file as the parameter here.
     */
    public FileRead(String fileToRead) {

        try {
            reader = new BufferedReader(new FileReader(fileToRead));
            reader2 = new BufferedReader(new FileReader(fileToRead));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    /**
     *
     * in this method we are reading the configuration file and assigning everything to the zoo and its values.
     *
     * the first thing we do is read through the configuration file once looking for the word enclosure as this
     * signifies the start of an enclosure being defined. once we know how many enclosures there are we create a new
     * array of enclosures.
     *
     * we then loop through the configuration file a second time. when we read in a line the first thing we do is split
     * it into an array at the colon. the thing we want to add and then some information about it. we then split the
     * second elements in an array into another array calling it the info array as this holds the data such as the age,
     * gender or other things.
     *
     * next we loop through each line until we find the line that has "zoo" in it as this signifies the start of the zoo
     * when it is found we set zooInfoStarted to true, we instantiate a new zoo and create an array of enclosures.
     *
     * once the zoo information has started we loop through the possible things that can be written. we check if the
     * config file is specifying a new enclosure, zookeeper, animal or food.
     *
     * if the config file is telling us to create a new enclosure. we check whether they have specified how much waste
     * the enclosure should start with. if it is then we pass in that amount to the enclosure constructor, for any other
     * option we call the normal constructor that sets the waste level to 0. we also increment the enclosure we are on
     * so we know what enclosure to add subsequent objects to.
     *
     * if the first word in the line is a type of zookeeper we call the addZookeeper method passing in the type of
     * zookeeper and what enclosure they are for.
     *
     * if its none of the above we check if it is an animal. if it is, we check that all the information we need (4
     * pieces) is provided. if it is all provided we call the addAnimal method passing in the information it needs +
     * which enclosure it is a member of. if not enough info is provided we print out the information is missing and
     * move onto the next line of th configuration. additionaly before we add an animal to an enclosure we make sure
     * there is enough space in the enclosure. if there is the animal is added, if not we print out a message telling
     * telling us that there is not enough spave in the enclosure
     *
     * finaly we check if this line of the config file is a type of food. if it is and no enclosure has been created yet
     * then we add this food + however much of it specified to the zoo foodstore. if a enclosure has been created the
     * food is added to the most recent foodstore created.
     *
     * we have a try/catch block in case we do not find a configuration file we tell the user that there are "no lines
     * to be read."
     *
     * @return this returns the zoo it is a member of.
     */
    public Zoo readTheConfig() {

        String[] thingInArray;
        String[] infoArray = new String[4];
        String[] lookingForEnclosure;
        String line;

        int amountOfEnclosures = 0;
        int enclosureWeAreOn = 0;

        boolean zooInfoStarted = false;


        try {

            while ((line = reader.readLine()) != null) {
                line.toLowerCase();
                lookingForEnclosure = line.split(":");

                if (lookingForEnclosure[0].equals("enclosure")){
                    amountOfEnclosures++;
                }

            }

            System.out.println("amount of enclosures:" + amountOfEnclosures);

            while ((line = reader2.readLine()) != null) {

                String lowerCaseLine = line.toLowerCase();
                thingInArray = lowerCaseLine.split(":");

                if (thingInArray.length == 2){
                    infoArray = thingInArray[1].split(",");
                }


                if (zooInfoStarted){


                    if (thingInArray[0].equals("enclosure")) {

                        enclosureWeAreOn++;
                        if (Integer.parseInt(thingInArray[1]) > 0) {
                            zoo.enclosuresInTheZoo[enclosureWeAreOn - 1] = new
                                    Enclosure(Integer.parseInt(thingInArray[1]));
                        } else {
                            zoo.enclosuresInTheZoo[enclosureWeAreOn - 1] = new Enclosure(0);
                        }

                     } else if (thingInArray[0].equals("playzookeeper") || thingInArray[0].equals("physiozookeeper") ||
                            thingInArray[0].equals("zookeeper")) {

                        addZookeeper(thingInArray[0], (enclosureWeAreOn - 1));

                    } else if (thingInArray[0].equals("tiger") || thingInArray[0].equals("lion") ||
                            thingInArray[0].equals("penguin") || thingInArray[0].equals("bear") ||
                            thingInArray[0].equals("gorilla") || thingInArray[0].equals("giraffe") ||
                            thingInArray[0].equals("chimpanzee") || thingInArray[0].equals("elephant")) {

                        if (zoo.enclosuresInTheZoo[Integer.parseInt(infoArray[3]) - 1].size() < 20) {

                            if (infoArray.length == 4) {

                                addAnimal(thingInArray[0], infoArray[1], infoArray[0], infoArray[2],
                                        (Integer.parseInt(infoArray[3]) - 1));
                            } else {

                                System.out.println("not enough information is given about this " + thingInArray[0]);
                            }
                        } else {

                            System.out.println("there are to many animals in this enclosure");
                        }

                    } else if (thingInArray[0].equals("celery") || thingInArray[0].equals("steak") ||
                            thingInArray[0].equals("fruit") || thingInArray[0].equals("celery") ||
                            thingInArray[0].equals("ice cream") || thingInArray[0].equals("hay")) {

                        if ((enclosureWeAreOn - 1) < 0 ) {

                            addFoodToZooFoodStore(thingInArray[0], Integer.parseInt(infoArray[0]));
                        } else {

                            addFoodToEnclosureFoodStore(thingInArray[0], Integer.parseInt(infoArray[0]),
                                    (enclosureWeAreOn-1));
                        }
                    }

                } else if (thingInArray[0].equals("zoo")) {
                    zooInfoStarted = true;
                    zoo = new Zoo();
                    zoo.enclosuresInTheZoo = new Enclosure[amountOfEnclosures];
                }

            }
        } catch (IOException e) {

            System.err.println("no lines to be read");
        }


        return zoo;
    }

    /**
     *
     * here we are checking wich type of zookeeper to  create, creating it and then adding them to the specific
     * enclosure.
     *
     * @param zookeeperToAdd this is the type of zookeeper to be created.
     * @param enclosureToAddTo the enclosure they are to be added to.
     */
    public void addZookeeper(String zookeeperToAdd, int enclosureToAddTo) {

        switch (zookeeperToAdd){
            case "playzookeeper":
                zoo.zooKeepers.add(new PlayZookeeper(zoo.enclosuresInTheZoo[enclosureToAddTo], this.zoo));
                break;
            case "physiozookeeper":
                zoo.zooKeepers.add(new PhysioZookeeper(zoo.enclosuresInTheZoo[enclosureToAddTo], this.zoo));
                break;
            case "zookeeper":
                zoo.zooKeepers.add(new Zookeeper(zoo.enclosuresInTheZoo[enclosureToAddTo], this.zoo));
                break;
        }

    }

    /**
     *
     * when this method is called we are looping through all the animals to find the one that is to be created. we then
     * create it and add it to the specified enclosure.
     *
     * @param animalToAdd the type of animal to add
     * @param animalsAge the animals age
     * @param animalGender the animals gender
     * @param animalHealth the animals health
     * @param animalEnclosure the animals enclosure
     */
    public void addAnimal(String animalToAdd, String animalsAge, String animalGender, String animalHealth,
                          int animalEnclosure) {

        switch (animalToAdd) {
            case "tiger":
                Tiger tiger = new Tiger(Integer.parseInt(animalsAge), animalGender.charAt(0),
                        Integer.parseInt(animalHealth), zoo.enclosuresInTheZoo[animalEnclosure]);
                tiger.setEnclosure(zoo.enclosuresInTheZoo[animalEnclosure]);
                break;
            case "lion":
                Lion lion = new Lion(Integer.parseInt(animalsAge), animalGender.charAt(0),
                        Integer.parseInt(animalHealth), zoo.enclosuresInTheZoo[animalEnclosure]);
                zoo.enclosuresInTheZoo[animalEnclosure].animalsInTheEnclosure.add(lion);
                break;
            case "penguin":
                Penguin penguin = new Penguin(Integer.parseInt(animalsAge), animalGender.charAt(0),
                        Integer.parseInt(animalHealth), zoo.enclosuresInTheZoo[animalEnclosure]);
                zoo.enclosuresInTheZoo[animalEnclosure].animalsInTheEnclosure.add(penguin);
                break;
            case "bear":
                Bear bear = new Bear(Integer.parseInt(animalsAge), animalGender.charAt(0),
                        Integer.parseInt(animalHealth), zoo.enclosuresInTheZoo[animalEnclosure]);
                zoo.enclosuresInTheZoo[animalEnclosure].animalsInTheEnclosure.add(bear);
                break;
            case "gorilla":
                Gorilla gorilla = new Gorilla(Integer.parseInt(animalsAge), animalGender.charAt(0),
                        Integer.parseInt(animalHealth), zoo.enclosuresInTheZoo[animalEnclosure]);
                zoo.enclosuresInTheZoo[animalEnclosure].animalsInTheEnclosure.add(gorilla);
                break;
            case "giraffe":
                Giraffe giraffe = new Giraffe(Integer.parseInt(animalsAge), animalGender.charAt(0),
                        Integer.parseInt(animalHealth), zoo.enclosuresInTheZoo[animalEnclosure]);
                zoo.enclosuresInTheZoo[animalEnclosure].animalsInTheEnclosure.add(giraffe);
                break;
            case "chimpanzee":
                Chimpanzee chimpanzee = new Chimpanzee(Integer.parseInt(animalsAge), animalGender.charAt(0),
                        Integer.parseInt(animalHealth), zoo.enclosuresInTheZoo[animalEnclosure]);
                zoo.enclosuresInTheZoo[animalEnclosure].animalsInTheEnclosure.add(chimpanzee);
                break;
            case "elephant":
                Elephant elephant = new Elephant(Integer.parseInt(animalsAge), animalGender.charAt(0),
                        Integer.parseInt(animalHealth), zoo.enclosuresInTheZoo[animalEnclosure]);
                zoo.enclosuresInTheZoo[animalEnclosure].animalsInTheEnclosure.add(elephant);
                break;
        }

    }

    /**
     *
     * this adds food to the zoo foodstore
     *
     * @param foodToAdd the type of food to add
     * @param foodAmount the amount of food to add.
     */
    public void addFoodToZooFoodStore(String foodToAdd, int foodAmount){
        switch (foodToAdd){
            case "hay":
                zoo.zooFoodStore.addFood("hay", foodAmount);
                break;
            case "steak":
                zoo.zooFoodStore.addFood("steak", foodAmount);
                break;
            case "fruit":
                zoo.zooFoodStore.addFood("fruit", foodAmount);
                break;
            case "celery":
                zoo.zooFoodStore.addFood("celery", foodAmount);
                break;
            case "ice cream":
                zoo.zooFoodStore.addFood("ice cream", foodAmount);
                break;
            case "fish":
                zoo.zooFoodStore.addFood("fish", foodAmount);
                break;
        }


    }

    /**
     *
     * this adds food to a specific enclosures foodstore
     *
     * @param foodToAdd the type of food to add
     * @param foodAmount the amout of food to add
     * @param enclosureNumber the enclosure whose food store to add this food to.
     */
    public void addFoodToEnclosureFoodStore(String foodToAdd, int foodAmount, int enclosureNumber){

            switch (foodToAdd){
                case "hay":
                    zoo.enclosuresInTheZoo[enclosureNumber].thisEnclosuresFoodStore.addFood("hay", foodAmount);
                    break;
                case "steak":
                    zoo.enclosuresInTheZoo[enclosureNumber].thisEnclosuresFoodStore.addFood("steak", foodAmount);
                    break;
                case "fruit":
                    zoo.enclosuresInTheZoo[enclosureNumber].thisEnclosuresFoodStore.addFood("fruit", foodAmount);
                    break;
                case "celery":
                    zoo.enclosuresInTheZoo[enclosureNumber].thisEnclosuresFoodStore.addFood("celery", foodAmount);
                    break;
                case "ice cream":
                    zoo.enclosuresInTheZoo[enclosureNumber].thisEnclosuresFoodStore.addFood("celery", foodAmount);
                    break;
                case "fish":
                    zoo.enclosuresInTheZoo[enclosureNumber].thisEnclosuresFoodStore.addFood("fish", foodAmount);
                    break;
            }
    }
}