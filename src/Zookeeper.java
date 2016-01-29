/**
 * Created by asherfischbaum on 21/11/15.
 */
public class Zookeeper {

    public Zookeeper(){
        //Default constructor...
    }

    /**
     *
     * @param enclosureToAssign this is the enclosure the zookeeper is assigned to
     * @param zoo this is the zoo it works at
     */
    public Zookeeper(Enclosure enclosureToAssign, Zoo zoo) {
        assignKeeper(enclosureToAssign);
        this.zoo = zoo;
        this.type =  "zookeeper";
    }

    Enclosure enclosure;
    Zoo zoo;
    private String type = "Zookeeper";

    /**
     *
     * @return returns the type of zookeeper it is.
     */
    public String getType(){
        return type;
    }

    /**
     *
     * the aMonthPasses() method is all the things that the zookeeper must do every month inclusing moving food, giving
     * animals treat and removing waste among other things.
     *
     * the first thing that must be done in a month by the zookeepers is making sure there is enough food in the zoo
     * food store that supplies the rest of the zoo. if there are less than 5 items of one particular food an additional
     * 25 pieces of that food should be added.
     *
     * we then check how much food is in the enclosure the zookeeper is assigned to. if their is less than 3 of a
     * certain type of food then an extra 5 is added. this takes 5 away from the zoofoodstore and adds 5 to this
     * enclosure foodstore. additionaly we make sure that a zookeeper does not transfer more than 20 pieces of food.
     *
     * we then get the zookeeper to check the waste level in the enclosure. if there are more than 0 but less than 21
     * pieces of waste the zoo keeper will remove it all. if however there are more than 20 pieces, the zookeeper will
     * remove 20 pieces.
     *
     * finaly the zookeeper checks whether there are any dead animals in the zoo. the zookeeper checks the health and
     * life expectancy, if either are 0 the animal is proclaimed dead and are removed from the enclosure.
     *
     */
    public void aMonthPasses() {


        for (String foodTypeKey : zoo.zooFoodStore.foodTypes.keySet()) {
            if (zoo.zooFoodStore.foodTypes.get(foodTypeKey) <= 5) {
                zoo.zooFoodStore.foodTypes.put(foodTypeKey, zoo.zooFoodStore.foodTypes.get(foodTypeKey) + 25);
                System.out.println(foodTypeKey + ":" + zoo.zooFoodStore.foodTypes.get(foodTypeKey));
            }
        }

        int foodAdded = 0;

        for (String foodTypeKey : enclosure.thisEnclosuresFoodStore.foodTypes.keySet()) {
            System.out.println(foodTypeKey + ":" + enclosure.thisEnclosuresFoodStore.foodTypes.get(foodTypeKey));

            while (foodAdded < 20 && enclosure.thisEnclosuresFoodStore.foodTypes.get(foodTypeKey) < 3) {
                zoo.zooFoodStore.takeFood(foodTypeKey, 5);
                enclosure.thisEnclosuresFoodStore.addFood(foodTypeKey, 5);
                foodAdded = foodAdded + 5;

            }
        }

        System.out.println("current waste level: " + enclosure.getWasteSize());

        for (int wasteRemoved = 0; wasteRemoved < 20; wasteRemoved++) {

            if (enclosure.getWasteSize() > 0) {
                enclosure.removeWaste(1);
            }
        }
        System.out.println("new waste size: " +enclosure.getWasteSize());

        System.out.println("giving the animals there treat");
        giveTreat();


        for (int i = 0; i < enclosure.animalsInTheEnclosure.size() - 1; i++) {

            if (enclosure.animalsInTheEnclosure.get(i).getHealth() <= 0 ||
                    enclosure.animalsInTheEnclosure.get(i).getLifeExpectancy() <= 0) {

                System.out.println(enclosure.animalsInTheEnclosure.get(i) + "\t health: " +
                enclosure.animalsInTheEnclosure.get(i).getHealth() + "\t life expectancy" +
                enclosure.animalsInTheEnclosure.get(i).getLifeExpectancy());

                enclosure.removeAnimal(enclosure.animalsInTheEnclosure.get(i));
            }
        }
    }

    /**
     * in this method we are getting this zookeeper to treat two animals.
     *
     * we loop through all the animals in the enclosure and give a treat to those that this zookeeper can treat and
     * first give a treat to the animals with the lowest health. the zookeeper can give up to 2 treats per month.
     */
    public void giveTreat(){

        int treatsGiven = 0;
        int i = 1;

        System.out.println("checking taht it got here");

        for (Animal animalCheck : enclosure.animalsInTheEnclosure){

            while (i <= animalCheck.getHealth()  && treatsGiven <2 && i < 11){

                switch (animalCheck.getFavouriteTreat()){
                    case "Stroke":
                        animalCheck.treat();
                        treatsGiven++;
                        System.out.println(animalCheck + " was treated");
                        break;
                    case "Hugs":
                        animalCheck.treat();
                        treatsGiven++;
                        System.out.println(animalCheck + " was treated");
                        break;
                }
                i++;
            }

        }
        System.out.println("Physio zookeeper treats given = " + treatsGiven + " and i = " + i);
    }

    /**
     * Here we are adding the zooKeeper into the arraylist of zookeepers in an enclosure
     * @param e represents the enclosure this zookeeper is is a member of
     */
    public void assignKeeper(Enclosure e){
        this.enclosure = e;
        e.addKeeper(this);
    }

}
