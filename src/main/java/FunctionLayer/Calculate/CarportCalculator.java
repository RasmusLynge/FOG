package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.*;

public class CarportCalculator {

    /**
     *
     * @param length
     * @param width
     * @param roof
     * @param shed
     * @return
     * @throws DMException
     * @throws MakeOrderException
     */
    public Carport calculateAll(int length, int width, boolean roof, boolean shed) throws DMException, MakeOrderException {
        if (length < MINLENGTH || length > MAXLENGTH || width < MINLENGTH || width > MAXLENGTH) {
            throw new MakeOrderException("Measurements contain values below or above the limit values");
        }
        Carport carport = new Carport(length, width, roof, false);
        RoofCalculator roofCalc = new RoofCalculator(carport);
        CoverCalculator coverCalc = new CoverCalculator(carport);
        outerMessurement(length, width, carport);
        calculatePosts(length, carport);
        beamLengthCalculator(length, roof, carport);
        coverCalc.setRoofCover();

        if (roof) {
            calculateRafters(length, width, roof, carport);
            roofCalc.topRoof(width, length, DEGREE);

        } else {
            calculateRafters(length, width, roof, carport);
            roofCalc.flatRoof();
        }

        if (shed) {
            shedPosts(carport);
            coverStabilizerPlanks(length, width, carport);
            carport.setDoorHinge(DOORHINGES);
            carport.setDoorKnob(DOORKNOB);
        }

        int lHinges = totalLHinges(carport.getRafter(), HINGESPERRAFTER, carport);
        totalScrews((lHinges + carport.getFlatHinges()), carport);
        screwBoxes(carport);

        return carport;
    }

    private void outerMessurement(int length, int width, Carport carport) {
        carport.setOuterLength(length + ENTRANCEHANGOUT + ROOFHANGOUTTWOSIDES);
        carport.setOuterWidth(width + ROOFHANGOUTTWOSIDES);
    }

    private void calculatePosts(int length, Carport carport) {
        int restLength = length - TWOPOSTLENGTH;
        int lengthPosts = calcLengthPosts(restLength);
        int totalPosts = MINPOSTS + BOTHSIDES * lengthPosts;
        int postSpacing = length / ((totalPosts / BOTHSIDES) - SPACINGCONVERTEDTOAMOUNT);
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
            carport.setRafter(totalRafters + SPACINGCONVERTEDTOAMOUNT);
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
            carport.setBeam(BEAMS + MIDDLEROOFBEAM);
        } else {
            carport.setBeam(BEAMS);
        }
    }

    private int calcLengthPosts(int restLength) {
        int amount = restLength / NEWPOSTLENGTH;
        return amount;
    }

    private int totalLHinges(int rafters, int posts, Carport carport) {
        int totalLHinges = rafters * HINGESPERRAFTER;
        totalLHinges += posts * HINGESPERPOST;
        carport.setLHinges(totalLHinges);
        return totalLHinges;
    }

    private void totalScrews(int hinges, Carport carport) {
        int totalScrews = hinges * SCREWSPERLHINGES;
        carport.setScrews(carport.getScrews() + totalScrews);
    }

    private void screwBoxes(Carport carport) {
        double boxesOfScrews = (double) carport.getScrews() / SCREWSINABOX;
        double boxesRoundedUp = (double) Math.ceil(boxesOfScrews);
        int boxes = (int) boxesRoundedUp;
        carport.setScrewBoxes(boxes);
    }

    private void coverStabilizerPlanks(int length, int width, Carport carport) {
        int counterStabilizerSmall = 0;
        int counterStabilizerLong = 0;
        stabilizerPlanksWidth(width, counterStabilizerSmall, counterStabilizerLong, carport);
        stabilizerPlanksLength(length, counterStabilizerSmall, counterStabilizerLong, carport);

    }

    private void stabilizerPlanksLength(int length, int counterStabilizerSmall, int counterStabilizerLong, Carport carport) {
        if (length <= STABILIZERPLANKSSMALL) {
            counterStabilizerSmall += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (length <= STABILIZERPLANKSLONG) {
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (length > STABILIZERPLANKSLONG) {

            int restLength = (int) (length - STABILIZERPLANKSLONG);
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;

            counterStabilizerSmall += MINIMUMHAVEONEPEICE;
            int currentLength = STABILIZERPLANKSSMALL;
            for (int i = 0; i < BOTHSIDES * STABILIZERPLANKPRWALL; i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterStabilizerSmall += MINIMUMHAVEONEPEICE;
                    currentLength = counterStabilizerSmall - restLength;
                }
            }
        }
        carport.setCoverStabilizerPlankSmall(counterStabilizerSmall);
        carport.setCoverStabilizerPlanksLong(counterStabilizerLong);
        if (!(length <= STABILIZERPLANKSSMALL && length <= STABILIZERPLANKSLONG)) {
            carport.setFlatHinges(carport.getFlatHinges() + counterStabilizerSmall);
        }
        carport.setLHinges(carport.getLHinges() + BOTHSIDES * STABILIZERPLANKPRWALL * LHINGEPRSTABILIZER);

    }

    private void stabilizerPlanksWidth(int width, int counterStabilizerSmall, int counterStabilizerLong, Carport carport) {
        if (width <= STABILIZERPLANKSSMALL) {
            counterStabilizerSmall += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (width <= STABILIZERPLANKSLONG) {
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (width > STABILIZERPLANKSLONG) {

            int restLength = (int) (width - STABILIZERPLANKSLONG);
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;

            counterStabilizerSmall += MINIMUMHAVEONEPEICE;
            int currentLength = STABILIZERPLANKSSMALL;
            for (int i = 0; i < BOTHSIDES * STABILIZERPLANKPRWALL; i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterStabilizerSmall += MINIMUMHAVEONEPEICE;
                    currentLength = counterStabilizerSmall - restLength;
                }
            }
        }
        if (!(width <= STABILIZERPLANKSSMALL && width <= STABILIZERPLANKSLONG)) {
            carport.setFlatHinges(carport.getFlatHinges() + counterStabilizerSmall);
        }
        carport.setLHinges(carport.getLHinges() + BOTHSIDES * STABILIZERPLANKPRWALL * LHINGEPRSTABILIZER);
    }

    private void shedPosts(Carport carport) {
        carport.setPost(carport.getPost() + SHEDPOSTS);
    }
}
