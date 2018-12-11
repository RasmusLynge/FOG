package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import DBAccess.DataMapper;
import static FunctionLayer.Rule.Rules.*;

public class RoofCalculator {

    DataMapper db = new DataMapper();
    Carport c;

    public RoofCalculator(Carport c) {
        this.c = c;
    }

    public void flatRoof() {
        flatRoofPlastmo();
        flatRoofScrews();
    }

    private void flatRoofScrews() {
        int screwsEachRafter = c.getRafterLength() / 12;
        int totalScrews = c.getRafter() * screwsEachRafter;
        c.setScrews(c.getScrews() + totalScrews);
    }

    private void flatRoofPlastmo() {
        int counterPlastmoSmall = 0;
        int counterPlastmoLong = 0;
        int counterPlastmoWidth = (int) Math.ceil(c.getOuterWidth() / (double) (PLASTMOWIDTH - OVERLAP));

//If the outerlength is shorter than the length of the small plastmo plate there is 1 small for each counterWidth
        if (c.getOuterLength() <= PLASTMOLENGTHSMALL) {
            counterPlastmoSmall += counterPlastmoWidth;

//Otherwise a big plate is used,
        } else {
            counterPlastmoLong += Math.ceil(((double) c.getOuterLength() / (double) PLASTMOLENGTHLONG)) * counterPlastmoWidth;

//If the long plate isnt long enough, the rest is calculated together with the amount of small plates needed 
            if (c.getOuterLength() > PLASTMOLENGTHLONG) {
                int restWithOverlap = c.getOuterLength() - PLASTMOLENGTHLONG + OVERLAP;
                int restInSmallLength = PLASTMOLENGTHSMALL / restWithOverlap;
                counterPlastmoSmall += (counterPlastmoWidth / restInSmallLength);
            }
        }
        c.setPlastmoLong(counterPlastmoLong);
        c.setPlastmoSmall(counterPlastmoSmall);
    }

    public void topRoof(int width, int length, int degree) {
        double sideB = c.getOuterWidth() / 2; 
        double angleA = Math.toRadians(degree);
        double angleB = Math.toRadians(180-90-degree);
        double sineA = Math.sin(angleA);
        double sineB = Math.sin(angleB);
        double roofPostHeight = (sineA * sideB) / sineB;
        
        System.out.println(" roof post height ++++++++++++++"+ roofPostHeight);
        int roofPost = c.getRafter();   
        
        c.setRoofPostHeight(roofPostHeight);
        c.setRoofPost(roofPost);
        if (roofPostHeight < 0) {
            c.setRoofPostHeight(roofPostHeight * -1);
        }

        //length each side
        double roofRafterLength = Math.sqrt((roofPostHeight * roofPostHeight) + (c.getOuterWidth() / 2) * (c.getOuterWidth() / 2));
        int roofRafter = c.getRafter() * BOTHSIDES;
        c.setRoofRafterLength((int) roofRafterLength);

        //each side
        c.setRoofRafter(roofRafter);

        //pr sides
        int roofBeams = (int) (roofRafterLength / ROOFBEAMSPACING);
        c.setRoofBeams(roofBeams);

        int roofTiles = (int) ((c.getRoofRafter() - 1) * (c.getRoofRafterLength() / (TILESWIDTH - OVERLAPTILES)));
        c.setRoofTiles(roofTiles);

        int flatHinges = c.getRoofPost() * 2;
        c.setFlatHinges(c.getFlatHinges() + flatHinges);

        int screwsEachRafter = (c.getBeam() * c.getRoofRafter()) * 2;
        int screwsEachFlatHinge = flatHinges * 4;
        int totalScrews = screwsEachRafter + screwsEachFlatHinge;
        c.setScrews(c.getScrews() + totalScrews);
    }
}
