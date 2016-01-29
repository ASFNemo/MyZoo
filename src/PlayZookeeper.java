/**
 * Created by asherfischbaum on 26/11/15.
 */
public class PlayZookeeper extends Zookeeper {
    /**
     * @param enclosureToAssign the enclosure this animal is assigned to
     * @param zoo the zoo this animal is assigned to
     */
    public PlayZookeeper(Enclosure enclosureToAssign, Zoo zoo) {
        assignKeeper(enclosureToAssign);
        this.zoo = zoo;
    }

    private String type = "Play Zookeeper";

    /**
     * @return the type of zookeeper this is
     */
    public String getType() {
        return type;
    }

    /**
     * in this method we are getting this zookeeper to treat two animals.
     *
     * we loop through all the animals in the enclosure and give a treat to those that this zookeeper can treat and
     * first give a treat to the animals with the lowest health. the zookeeper can give up to 2 treats per month.
     */
    public void giveTreat() {

        int treatsGiven = 0;
        int i = 1;


        for (Animal animalCheck : enclosure.animalsInTheEnclosure) {

            while (animalCheck.getHealth() <= i && treatsGiven < 2 && i < 11) {

                switch (animalCheck.getFavouriteTreat()) {
                    
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
                    case "Watch A film":
                        animalCheck.treat();
                        treatsGiven++;
                        System.out.println(animalCheck + " was treated");
                        break;
                    case "Play Chase":
                        animalCheck.treat();
                        treatsGiven++;
                        System.out.println(animalCheck + " was treated");
                        break;
                    case "Painting":
                        animalCheck.treat();
                        treatsGiven++;
                        System.out.println(animalCheck + " was treated");
                        break;
                }

                i++;
            }

        }
    }
}

