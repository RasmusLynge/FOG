package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import DBAccess.DataMapper;
import static FunctionLayer.Rule.Rules.*;

public class RoofCalculator {

    DataMapper db = new DataMapper();
    Carport carport;

    /**
     * 
     * @param carport
     */
    public RoofCalculator(Carport carport) {
        this.carport = carport;
    }

    /**
     *
     */
    public void flatRoof() {
        flatRoofPlastmo();
        flatRoofScrews();
    }

    private void flatRoofScrews() {
        int screwsEachRafter = carport.getRafterLength() / SCREWSEACHRAFTER;
        int totalScrews = carport.getRafter() * screwsEachRafter;
        carport.setScrews(carport.getScrews() + totalScrews);
    }

    private void flatRoofPlastmo() {
        int counterPlastmoSmall = 0;
        int counterPlastmoLong = 0;
        int counterPlastmoWidth = (int) Math.ceil(carport.getOuterWidth() / (double) (PLASTMOWIDTH - OVERLAP));

        if (carport.getOuterLength() <= PLASTMOLENGTHSMALL) {
            counterPlastmoSmall += counterPlastmoWidth;
        } else {
            counterPlastmoLong += Math.ceil(((double) carport.getOuterLength() / (double) PLASTMOLENGTHLONG)) * counterPlastmoWidth;

            if (carport.getOuterLength() > PLASTMOLENGTHLONG) {
                int restWithOverlap = carport.getOuterLength() - PLASTMOLENGTHLONG + OVERLAP;
                int restInSmallLength = PLASTMOLENGTHSMALL / restWithOverlap;
                counterPlastmoSmall += (counterPlastmoWidth / restInSmallLength);
            }
        }
        carport.setPlastmoLong(counterPlastmoLong);
        carport.setPlastmoSmall(counterPlastmoSmall);
    }

    /**
     *
     * @param width
     * @param length
     * @param degree
     */
    public void topRoof(int width, int length, int degree) {
        double sideB = carport.getOuterWidth() / 2;
        double angleA = Math.toRadians(degree);
        double angleB = Math.toRadians(180 - 90 - degree);
        double sineA = Math.sin(angleA);
        double sineB = Math.sin(angleB);
        double roofPostHeight = (sineA * sideB) / sineB;

        System.out.println(" roof post height ++++++++++++++" + roofPostHeight);
        int roofPost = carport.getRafter();

        carport.setRoofPostHeight(roofPostHeight);
        carport.setRoofPost(roofPost);

        if (roofPostHeight < 0) {
            carport.setRoofPostHeight(roofPostHeight * CONVERTTOPOSITIVE);
        }

        double roofRafterLength = Math.sqrt((roofPostHeight * roofPostHeight) + (carport.getOuterWidth() / 2) * (carport.getOuterWidth() / 2));
        int roofRafter = carport.getRafter() * BOTHSIDES;
        carport.setRoofRafterLength((int) roofRafterLength);

        carport.setRoofRafter(roofRafter);

        int roofBeams = (int) (roofRafterLength / ROOFBEAMSPACING);
        carport.setRoofBeams(roofBeams);

        int roofTiles = (int) ((carport.getRoofRafter() - MIDDLEROOFBEAM) * (carport.getRoofRafterLength() / (TILESWIDTH - OVERLAPTILES)));
        carport.setRoofTiles(roofTiles);

        int flatHinges = carport.getRoofPost() * BOTHSIDES;
        carport.setFlatHinges(carport.getFlatHinges() + flatHinges);

        int screwsEachRafter = (carport.getBeam() * carport.getRoofRafter()) * BOTHSIDES;
        int screwsEachFlatHinge = flatHinges * SCREWSPERLHINGES;
        int totalScrews = screwsEachRafter + screwsEachFlatHinge;
        carport.setScrews(carport.getScrews() + totalScrews);
    }
}
