/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Entity.Material;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.*;
import java.util.ArrayList;

/**
 *
 * @author Mathias
 */
public class CarportCalculator {

    public Carport calculateAll(int length, int width, boolean roof, boolean shed) throws DMException, MakeOrderException {
        if (length < 240 || length > 720 || width < 240 || width > 720) {
            throw new MakeOrderException("Measurements contain values below or above the limit values");
        }
        Carport c = new Carport(length, width, roof, false);
        RoofCalculator rc = new RoofCalculator(c);
        CoverCalculator cc = new CoverCalculator(c);
        outerMessurement(length, width, c);
        calculatePosts(length, width, c);
        beamLengthCalculator(length, roof, c);
        cc.setRoofCover();

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

        if (shed) {
            shedPosts(c);
            coverStabilizerPlanks(length, width, c);
            c.setDoorHinge(2);
            c.setDoorKnob(1);
        }

        int lHinges = totalLHinges(c.getRafter(), HINGESPERRAFTER, c);
        totalScrews((lHinges + c.getFlatHinges()), c);
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
        int postSpacing = length / ((totalPosts / BOTHSIDES) - 1);
        carport.setPostSpacing(postSpacing);
        carport.setPostLength(POSTSLENGTH);
        carport.setPost(totalPosts);
    }

    private void calculateRafters(int length, int width, boolean roof, Carport carport) {
        int roofLength = length + ROOFHANGOUTTWOSIDES + ENTRANCEHANGOUT;
        int rafterLength = width + ROOFHANGOUTTWOSIDES;

        if (roof) {
            int totalRafters = carport.getPost() / BOTHSIDES;
            int newRafterSpacing = length / (carport.getPost() / BOTHSIDES);
            carport.setRafter(totalRafters + 1);
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
        double boxesOfScrews = (double) c.getScrews() / SCREWSINABOX;
        double boxesRoundedUp = (double) Math.ceil(boxesOfScrews);
        int boxes = (int) boxesRoundedUp;
        c.setScrewBoxes(boxes);
    }

    private void coverStabilizerPlanks(int length, int width, Carport c) {
        int counterStabilizerSmall = 0;
        int counterStabilizerLong = 0;
        stabilizerWidth(width, counterStabilizerSmall, counterStabilizerLong, c);
        stabilizerLength(length, counterStabilizerSmall, counterStabilizerLong, c);

    }

    private void stabilizerLength(int length, int counterStabilizerSmall, int counterStabilizerLong, Carport c) {
        if (length <= STABILIZERPLANKSSMALL) {
            counterStabilizerSmall += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (length <= STABILIZERPLANKSLONG) {
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (length > STABILIZERPLANKSLONG) {

            int restLength = (int) (length - STABILIZERPLANKSLONG);
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;

            counterStabilizerSmall += 1;
            int currentLength = STABILIZERPLANKSSMALL;
            for (int i = 0; i < BOTHSIDES * STABILIZERPLANKPRWALL; i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterStabilizerSmall += 1;
                    currentLength = counterStabilizerSmall - restLength;
                }
            }
        }
        c.setCoverStabilizerPlankSmall(counterStabilizerSmall);
        c.setCoverStabilizerPlanksLong(counterStabilizerLong);
        if (!(length <= STABILIZERPLANKSSMALL && length <= STABILIZERPLANKSLONG)) {
            c.setFlatHinges(c.getFlatHinges() + counterStabilizerSmall);
        }
        c.setLHinges(c.getLHinges() + BOTHSIDES * STABILIZERPLANKPRWALL * LHINGEPRSTABILIZER);

    }

    private void stabilizerWidth(int width, int counterStabilizerSmall, int counterStabilizerLong, Carport c) {
        if (width <= STABILIZERPLANKSSMALL) {
            counterStabilizerSmall += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (width <= STABILIZERPLANKSLONG) {
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (width > STABILIZERPLANKSLONG) {

            int restLength = (int) (width - STABILIZERPLANKSLONG);
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;

            counterStabilizerSmall += 1;
            int currentLength = STABILIZERPLANKSSMALL;
            for (int i = 0; i < BOTHSIDES * STABILIZERPLANKPRWALL; i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterStabilizerSmall += 1;
                    currentLength = counterStabilizerSmall - restLength;
                }
            }
        }
        if (!(width <= STABILIZERPLANKSSMALL && width <= STABILIZERPLANKSLONG)) {
            c.setFlatHinges(c.getFlatHinges() + counterStabilizerSmall);
        }
        c.setLHinges(c.getLHinges() + BOTHSIDES * STABILIZERPLANKPRWALL * LHINGEPRSTABILIZER);
    }

    private void shedPosts(Carport c) {
        c.setPost(c.getPost() + 3);
    }
}
