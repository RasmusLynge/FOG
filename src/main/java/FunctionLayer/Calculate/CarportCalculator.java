package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.*;

public class CarportCalculator {

    /**
     * This method creates and calculates all the different parts of the carport
     * by checking the 4 given parameters and using them to decide the
     * measurements.
     *
     * @param length int: the length of the carport
     * @param width int: the width of the carport
     * @param roof boolean: if true the carport needs a top roof
     * @param shed boolean: if true the carport needs a shed
     * @return in the end the method returns a finished carport object to the
     * pricecalculator, so it can figure out the price
     * @throws DMException
     * @throws MakeOrderException if input is out of bounds to the set value
     * limits
     */
    public Carport calculateAll(int length, int width, boolean roof, boolean shed) throws DMException, MakeOrderException {
        if (length < MINLENGTH || length > MAXLENGTH || width < MINLENGTH || width > MAXLENGTH) {
            throw new MakeOrderException("Measurements contain values below or above the limit values");
        }

        Carport carport = new Carport(length, width, roof, shed);
        RoofCalculator roofCalc = new RoofCalculator(carport);
        CoverCalculator coverCalc = new CoverCalculator(carport);
        outerMessurement(carport);
        calculatePosts(carport);
        beamLengthCalculator(carport);
        coverCalc.setRoofCover();

        if (roof) {
            calculateRafters(carport);
            roofCalc.topRoof(DEGREE);

        } else {
            calculateRafters(carport);
            roofCalc.flatRoof();
        }

        if (shed) {
            shedPosts(carport);
            coverStabilizerPlanks(carport);
            carport.setDoorHinge(DOORHINGES);
            carport.setDoorKnob(DOORKNOB);
        }

        int lHinges = totalLHinges(carport.getRafter(), HINGESPERRAFTER, carport);
        totalScrews((lHinges + carport.getFlatHinges()), carport);
        screwBoxes(carport);

        return carport;
    }

    private void outerMessurement(Carport carport) {
        carport.setOuterLength(carport.getLength() + ENTRANCEHANGOUT + ROOFHANGOUTTWOSIDES);
        carport.setOuterWidth(carport.getWidth() + ROOFHANGOUTTWOSIDES);
    }

    private void calculatePosts(Carport carport) {
        int restLength = carport.getLength() - TWOPOSTLENGTH;
        int lengthPosts = calcLengthPosts(restLength);
        int totalPosts = MINPOSTS + BOTHSIDES * lengthPosts;
        int postSpacing = carport.getLength() / ((totalPosts / BOTHSIDES) - SPACINGCONVERTEDTOAMOUNT);
        carport.setPostSpacing(postSpacing);
        carport.setPostLength(POSTSLENGTH);
        carport.setPost(totalPosts);
    }

    private void calculateRafters(Carport carport) {
        int roofLength = carport.getLength() + ROOFHANGOUTTWOSIDES + ENTRANCEHANGOUT;
        int rafterLength = carport.getWidth() + ROOFHANGOUTTWOSIDES;

        if (carport.isRoof()) {
            int totalRafters = carport.getPost() / BOTHSIDES;
            int newRafterSpacing = carport.getLength() / (carport.getPost() / BOTHSIDES);
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

    private void beamLengthCalculator(Carport carport) {
        int toalBeamLength = carport.getLength() + ENTRANCEHANGOUT + ROOFHANGOUTTWOSIDES;
        carport.setBeamLength(toalBeamLength);
        if (carport.isRoof()) {
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

    private void coverStabilizerPlanks(Carport carport) {
        int counterStabilizerSmall = 0;
        int counterStabilizerLong = 0;
        stabilizerPlanksWidth(counterStabilizerSmall, counterStabilizerLong, carport);
        stabilizerPlanksLength(counterStabilizerSmall, counterStabilizerLong, carport);

    }

    private void stabilizerPlanksLength(int counterStabilizerSmall, int counterStabilizerLong, Carport carport) {
        if (carport.getLength() <= STABILIZERPLANKSSMALL) {
            counterStabilizerSmall += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (carport.getLength() <= STABILIZERPLANKSLONG) {
            counterStabilizerLong += BOTHSIDES * STABILIZERPLANKPRWALL;
        } else if (carport.getLength() > STABILIZERPLANKSLONG) {

            int restLength = (int) (carport.getLength() - STABILIZERPLANKSLONG);
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

        if (!(carport.getLength() <= STABILIZERPLANKSSMALL && carport.getLength() <= STABILIZERPLANKSLONG)) {
            carport.setFlatHinges(carport.getFlatHinges() + counterStabilizerSmall);
        }
        carport.setLHinges(carport.getLHinges() + BOTHSIDES * STABILIZERPLANKPRWALL * LHINGEPRSTABILIZER);

    }

    private void stabilizerPlanksWidth(int counterStabilizerSmall, int counterStabilizerLong, Carport carport) {
        int width = carport.getWidth();
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
