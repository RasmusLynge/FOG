package FunctionLayer.Calculate;

import DBAccess.DataFacade;
import FunctionLayer.Entity.Material;
import FunctionLayer.Entity.Carport;
import java.util.ArrayList;
import FunctionLayer.Exception.DMException;
import static FunctionLayer.Rule.Rules.*;

public class MaterialCalculator {

    /**
     * This method makes a list of the materials in our database. 
     * it calls other methods that lookst at a specific material and calculates howmany we should use of it.
     * @param carport this is the carport that we need to get the specific materials for
     * @return
     * @throws DMException
     */
    public ArrayList<Material> materialList(Carport carport) throws DMException {
        DataFacade df = new DataFacade(); // BURDE DENNE HER IKKE KALDE LOGIC FACADEN??
        ArrayList<Material> list = df.getMaterials();

        rafter(carport, list);
        roofRafter(carport, list);
        beam(carport, list);
        roofBeam(carport, list);
        addRest(carport, list);
        carport.setList(list);
        return list;
    }

    private void rafter(Carport carport, ArrayList<Material> list) {
        int counterRafterLong = 0;
        int counterRafterSmall = 0;

        if (carport.getRafterLength() <= RAFTERSMALL) {
            counterRafterSmall += carport.getRafter();
        } else if (carport.getRafterLength() <= RAFTERLONG) {
            counterRafterLong += carport.getRafter();
        } else if (carport.getRafterLength() > RAFTERLONG) {
            int restLength = carport.getRafterLength() - RAFTERLONG;
            counterRafterLong += carport.getRafter();
            counterRafterSmall += MINIMUMHAVEONEPEICE;
            int currentLength = RAFTERSMALL;
            for (int i = 0; i < carport.getRafter(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterRafterSmall += MINIMUMHAVEONEPEICE;
                    currentLength = RAFTERSMALL - restLength;
                }
            }
            if (!(carport.getRafterLength() <= RAFTERSMALL && carport.getRafterLength() <= RAFTERLONG)) {
                carport.setFlatHinges(carport.getFlatHinges() + counterRafterSmall * BOTHSIDES);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == RAFTERSMALL) {
                list.get(i).setAmount(list.get(i).getAmount() + counterRafterSmall);
                System.out.println("amount after set small " + list.get(i).getAmount());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == RAFTERLONG) {
                list.get(i).setAmount(counterRafterLong);
                System.out.println("amount after set big " + list.get(i).getAmount());
            }
        }
    }

    private void roofRafter(Carport carport, ArrayList<Material> list) {
        int counterRafterLong = 0;
        int counterRafterSmall = 0;

        if (carport.getRoofRafterLength() <= RAFTERSMALL) {
            counterRafterSmall += carport.getRoofRafter();
        } else if (carport.getRoofRafterLength() <= RAFTERLONG) {
            counterRafterLong += carport.getRoofRafter();
        } else if (carport.getRoofRafterLength() > RAFTERLONG) {
            int restLength = (int) (carport.getRoofRafterLength() - RAFTERLONG);
            counterRafterLong += carport.getRoofRafter();
            counterRafterSmall += MINIMUMHAVEONEPEICE;
            int currentLength = RAFTERSMALL;
            for (int i = 0; i < carport.getRoofRafter(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterRafterSmall += MINIMUMHAVEONEPEICE;
                    currentLength = RAFTERSMALL - restLength;
                }
            }
            if (!(carport.getRoofRafterLength() <= RAFTERSMALL && carport.getRoofRafterLength() <= RAFTERLONG)) {
                carport.setFlatHinges(carport.getFlatHinges() + counterRafterSmall * BOTHSIDES);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195spærtræubh.") && list.get(i).getLength() == RAFTERSMALL) {
                list.get(i).setAmount(counterRafterSmall);
                list.get(i).setAmount(counterRafterSmall + list.get(i).getAmount());
                list.get(i).setAmount(counterRafterSmall * BOTHSIDES + list.get(i).getAmount());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195spærtræubh.") && list.get(i).getLength() == RAFTERLONG) {
                list.get(i).setAmount(counterRafterLong * BOTHSIDES + list.get(i).getAmount());
            }
        }
    }

    private void beam(Carport carport, ArrayList<Material> list) {
        int counterBeamLong = 0;
        int counterBeamSmall = 0;
        if (carport.getBeamLength() <= BEAMSMALL) {
            counterBeamSmall += carport.getBeam();
        } else if (carport.getBeamLength() <= BEAMLONG) {
            counterBeamLong += carport.getBeam();
        } else if (carport.getBeamLength() > BEAMLONG) {
            int restLength = (int) (carport.getBeamLength() - BEAMLONG);
            counterBeamLong += carport.getBeam();
            counterBeamSmall += MINIMUMHAVEONEPEICE;
            int currentLength = BEAMSMALL;
            for (int i = 0; i < carport.getBeam(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterBeamSmall += MINIMUMHAVEONEPEICE;
                    currentLength = BEAMSMALL - restLength;
                }
            }
            if (!(carport.getBeamLength() <= BEAMSMALL && carport.getBeamLength() <= BEAMLONG)) {
                carport.setFlatHinges(carport.getFlatHinges() + counterBeamSmall * BOTHSIDES);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == BEAMSMALL) {
                list.get(i).setAmount(counterBeamSmall * BOTHSIDES);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == BEAMLONG) {
                list.get(i).setAmount(counterBeamLong * BOTHSIDES);
            }
        }
    }

    private void roofBeam(Carport carport, ArrayList<Material> list) {
        int counterBeamLong = 0;
        int counterBeamSmall = 0;

        if (carport.getBeamLength() <= BEAMSMALL) {
            counterBeamSmall += carport.getRoofBeams();
        } else if (carport.getBeamLength() <= BEAMLONG) {
            counterBeamLong += carport.getRoofBeams();
        } else if (carport.getBeamLength() > BEAMLONG) {
            int restLength = (int) (carport.getBeamLength() - BEAMLONG);
            counterBeamLong += carport.getRoofBeams();
            counterBeamSmall += MINIMUMHAVEONEPEICE;
            int currentLength = BEAMSMALL;
            for (int i = 0; i < carport.getRoofBeams(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterBeamSmall += MINIMUMHAVEONEPEICE;
                    currentLength = BEAMSMALL - restLength;
                }
            }
            if (!(carport.getBeamLength() <= BEAMSMALL && carport.getBeamLength() <= BEAMLONG)) {
                carport.setFlatHinges(carport.getFlatHinges() + counterBeamSmall * 2);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == BEAMSMALL) {
                list.get(i).setAmount(counterBeamSmall * +list.get(i).getAmount());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == BEAMLONG) {
                list.get(i).setAmount(counterBeamLong * 2 + list.get(i).getAmount());
            }
        }
    }

    private void addRest(Carport carport, ArrayList<Material> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("Plastmo Ecolite blåtonet") && list.get(i).getLength() == PLASTMOLENGTHLONG) {
                list.get(i).setAmount(carport.getPlastmoLong());
            }
            if (list.get(i).getName().equals("Plastmo Ecolite blåtonet") && list.get(i).getLength() == PLASTMOLENGTHSMALL) {
                list.get(i).setAmount(carport.getPlastmoSmall());
            }
            if (list.get(i).getName().equals("97x97	mm.	trykimp. Stolpe")) {
                list.get(i).setAmount(carport.getPost());
            }
            if (list.get(i).getName().equals("Skruer 200 stk.")) {
                list.get(i).setAmount(carport.getScrewBoxes());
            }
            if (list.get(i).getName().equals("FladtBeslag")) {
                list.get(i).setAmount(carport.getFlatHinges());
            }
            if (list.get(i).getName().equals("Tegl")) {
                list.get(i).setAmount(carport.getRoofTiles());
            }
            if (list.get(i).getName().equals("LBeslag")) {
                list.get(i).setAmount(carport.getLHinges());
            }
            if (list.get(i).getName().equals("25x150	mm.	trykimp. Bræt") && list.get(i).getLength() == PLANKLENGTH) {
                list.get(i).setAmount(carport.getPlanks() + carport.getCoverPlanks());
            }
            if (list.get(i).getName().equals("45x95 Reglar ubh.") && list.get(i).getLength() == 240) {
                list.get(i).setAmount(carport.getCoverStabilizerPlankSmall());
            }
            if (list.get(i).getName().equals("45x95 Reglar ubh.") && list.get(i).getLength() == 360) {
                list.get(i).setAmount(carport.getCoverStabilizerPlanksLong());
            }
            if (list.get(i).getName().equals("Dørhåndtag")) {
                list.get(i).setAmount(carport.getDoorKnob());
            }
            if (list.get(i).getName().equals("Dør hængsel")) {
                list.get(i).setAmount(carport.getDoorHinge());
            }
        }
    }

}
