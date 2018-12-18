package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import DBAccess.DataMapper;
import static FunctionLayer.Rule.Rules.*;

public class RoofCalculator {

    DataMapper db = new DataMapper();
    Carport carport;

    /**
     * This method simply sets a given carport to this classes empty carport
     * object
     *
     * @param carport takes the carport object as a parameter
     */
    public RoofCalculator(Carport carport) {
        this.carport = carport;
    }

    /**
     * This method is run if the carport ordered has a flatroof. it then
     * calculates how many screws and how much plastic we need for the roof and
     * sets them on the carport object
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
     * This method is run if the carport ordered has a top roof it then
     * calculates everything listed under here and sets them on the carport
     * object:
     *
     * roofPostHeight, amount of roof posts, roof rafter length, amount of
     * rafters, amount of roof beams, amount of roof tiles, amount of flat
     * hinges and lastly amount of screws
     *
     * @param width the width of the carport
     * @param length the width of the carport
     * @param degree the chosen degree for the top roof
     */
    public void topRoof(int degree) {
        double sideB = carport.getOuterWidth() / 2;
        double angleA = Math.toRadians(degree);
        double angleB = Math.toRadians(180 - 90 - degree);
        double sineA = Math.sin(angleA);
        double sineB = Math.sin(angleB);
        double roofPostHeight = (sineA * sideB) / sineB;
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
