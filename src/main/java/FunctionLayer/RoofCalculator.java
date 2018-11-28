package FunctionLayer;

import DBAccess.DataMapper;
import static FunctionLayer.Rules.*;
public class RoofCalculator {

    DataMapper db = new DataMapper();
    CarportCalculator carportcalculator = new CarportCalculator();

    public void flatRoof(int width, int length) {
        Carport c = carportcalculator.calculateAll(length, width, true, true);
        flatRoofPlastmo(c);
        flatRoofScrews(c);
    }

    private void flatRoofScrews(Carport c) {
        int screwsEachRafter = c.getRafterLength() / 12;
        int totalScrews = c.getRafter() * screwsEachRafter;
        c.setScrews(c.getScrews() + totalScrews);
    }

    private void flatRoofPlastmo(Carport c) {
        int counterPlastmoSmall = 0;
        int counterPlastmoLong = 0;
        int counterPlastmoWidth = (int) Math.ceil(c.getOuterWidth() / (double) (PLASTMOWIDTH - OVERLAP));

        System.out.println("+++++++++ antal " + counterPlastmoWidth);
        System.out.println("outerlength" + c.getOuterLength());

        //If the outerlength is shorter than the length of the small plastmo plate there is 1 small for each counterWidth
        if (c.getOuterLength() <= PLASTMOLENGTHSMALL) {
            counterPlastmoSmall = counterPlastmoWidth;

            //Otherwise a big plate is used, 
        } else {
            counterPlastmoLong += (c.getOuterLength() / PLASTMOLENGTHLONG) * counterPlastmoWidth;

            //If the long plate isnt long enough, the rest is calculated together with the amount of small plates needed 
            if (c.outerLength > PLASTMOLENGTHLONG) {
                int restWithOverlap = c.getOuterLength() - PLASTMOLENGTHLONG + OVERLAP;
                System.out.println("rest overlap +++++" + restWithOverlap);

                int restInSmallLength = PLASTMOLENGTHSMALL / restWithOverlap;
                System.out.println("restInSmallLength " + restInSmallLength);
                counterPlastmoSmall += (counterPlastmoWidth / restInSmallLength);
                System.out.println("small = " + counterPlastmoSmall);
                System.out.println("long = " + counterPlastmoLong);
            }
        }
    }

    public void topRoof(int width, int length, int degree) {
        Carport c = carportcalculator.calculateAll(length, width, true, true);

        double roofPostHeight = Math.tan(degree) * (c.getOuterWidth() / 2);
        int roofPost = c.getRafter();
        c.setRoofPostHeight(roofPostHeight);
        c.setRoofPost(roofPost);
        
        double roofRafterLength = Math.sqrt((roofPostHeight * roofPostHeight) + (c.getOuterWidth() / 2) * (c.getOuterWidth() / 2));
        int roofRafter = c.getRafter() * BOTHSIDES;
        c.setRoofRafterLength(roofRafterLength);
        c.setRoofRafter(roofRafter);
        
        
        int roofBeams = (int) (roofRafterLength/ROOFBEAMSPACING);
        c.setRoofBeams(roofBeams);
        System.out.println("roofBeams " + c.getRoofBeams());
    }
}
