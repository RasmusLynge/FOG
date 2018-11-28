/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;
import static FunctionLayer.Rules.*;
/**
 *
 * @author Mathias
 */
public class CarportCalculator {

    public Carport calculateAll(int length, int width, boolean roof, boolean shed) {
        Carport c = new Carport(length, width, false, false);
        RoofCalculator rc = new RoofCalculator(c);
        outerMessurement(length, width, c);
        calculatePosts(length, width, c);
        beamLengthCalculator(length, roof, c);

        //skal laves til i JSP
        int degree = 90;
        if (roof == true) {
            System.out.println("true med rejst tag");
            calculateRafters(length, width, roof, c);
            rc.topRoof(width, length, degree);
        } else {
            System.out.println("false med flat tag");
            calculateRafters(length, width, roof, c);
            rc.flatRoof();
        }
        int lHinges = totalLHinges(c.getRafter(), HINGESPERRAFTER, c);
        totalScrews(lHinges, c);
        screwBoxes(c);
        return c;
    }

    private void outerMessurement(int length, int width, Carport carport) {
        carport.setOuterLength(length + ENTRANCEHANGOUT + ROOFHANGOUTTWOSIDES);
        carport.setOuterWidth(width + ROOFHANGOUTTWOSIDES);
    }

    private void calculatePosts(int length, int width, Carport carport) {
        int restLength = length - TWOPOSTLENGTH;
        int restWidth = width - TWOPOSTLENGTH;

        // int widthPosts = calcWidthPosts(restWidth);
        int lengthPosts = calcLengthPosts(restLength);
        //widthposts til skur
        int totalPosts = MINPOSTS + BOTHSIDES * lengthPosts;
        carport.setPostLength(POSTSLENGTH);
        carport.setPost(totalPosts);
    }

    private void calculateRafters(int length, int width, boolean roof, Carport carport) {
        int roofLength = length + ROOFHANGOUTTWOSIDES + ENTRANCEHANGOUT;
        int rafterLength = width + ROOFHANGOUTTWOSIDES;

        if (roof == true) {
            int totalRafters = carport.getPost();
            int newRafterSpacing = length / (carport.getPost() / BOTHSIDES);
            carport.setRafter(totalRafters);
            carport.setRafterSpacing(newRafterSpacing);
        } else {
            int totalRafters = roofLength / RAFTERSPACING;
            int newRafterSpacing = roofLength / totalRafters;
            carport.setRafterSpacing(newRafterSpacing);
            carport.setRafter(totalRafters);
        }
        carport.setRafterLength(rafterLength);

    }

    private void beamLengthCalculator(int length, boolean roof, Carport carport) {
        int toalBeamLength = length + ENTRANCEHANGOUT + ROOFHANGOUTTWOSIDES;
        carport.setBeamLength(toalBeamLength);
        if (roof) {
            carport.setBeam(BEAMS + 1);
        } else {
            carport.setBeam(BEAMS);
        }
    }

    private int calcWidthPosts(int restWidth) {
        int amount = restWidth / NEWPOSTLENGTH;
        return amount;

    }

    private int calcLengthPosts(int restLength) {
        int amount = restLength / NEWPOSTLENGTH;
        return amount;

    }

    private int totalLHinges(int rafters, int posts, Carport c) {
        int totalLHinges = rafters * HINGESPERRAFTER;
        totalLHinges += posts * HINGESPERPOST;
        c.setLHinges(totalLHinges);
        return totalLHinges;
    }

    private void totalScrews(int hinges, Carport c) {
        int totalScrews = hinges * SCREWSPERLHINGES;
        c.setScrews(c.getScrews() + totalScrews);
    }

    private void screwBoxes(Carport c) {
        double boxesOfScrews = c.getScrews() / SCREWSINABOX;
        double boxesRoundedUp = Math.ceil(boxesOfScrews);
        int boxes = (int) boxesRoundedUp;
        c.setScrewBoxes(boxes);
    }

}
