/**
 * Created by asherfischbaum on 26/11/15.
 */
public class PhysioZookeeper extends Zookeeper {
    /**
     *
     * @param enclosureToAssign the enclosuret this zookeeper is assigned to
     * @param zoo the zoo that it is a member of
     */
    public PhysioZookeeper(Enclosure enclosureToAssign, Zoo zoo) {
        assignKeeper(enclosureToAssign);
        this.zoo = zoo;
    }

    private String type = "Physio Zookeeper";

    /**
     * @return the type of zookeeper it is.
     */
    public String getType(){
        return type;
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

            for (Animal animalCheck : enclosure.animalsInTheEnclosure){

                while ( animalCheck.getHealth() <= i && treatsGiven <2 && i < 11){

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
                        case "Bath":
                            animalCheck.treat();
                            treatsGiven++;
                            System.out.println(animalCheck + " was treated");
                            break;
                        case "Neck Massage":
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
}
