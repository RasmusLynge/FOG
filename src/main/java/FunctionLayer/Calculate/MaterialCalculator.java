/*
 * To change this license header, choose Limycense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import java.util.List;
import FunctionLayer.Entity.Material;
import FunctionLayer.Entity.Carport;
import java.util.ArrayList;
import DBAccess.DataMapper;
import FunctionLayer.Exception.GeneralException;
import static FunctionLayer.Rule.Rules.*;

/**
 *
 * @author Magnus
 */
public class MaterialCalculator {

    public ArrayList<Material> materialList(Carport c) throws GeneralException {
        DataMapper dm = new DataMapper();
        ArrayList<Material> list = dm.getMaterials();

        rafter(c, list);
        roofRafter(c, list);
        beam(c, list);
        roofBeam(c, list);
        addRest(c, list);
        c.setList(list);
        return list;
    }

    private void rafter(Carport c, ArrayList<Material> list) {
        int counterRafterLong = 0;
        int counterRafterSmall = 0;

        if (c.getRafterLength() <= RAFTERSMALL) {
            counterRafterSmall += c.getRafter();
        } else if (c.getRafterLength() <= RAFTERLONG) {
            counterRafterLong += c.getRafter();
        } else if (c.getRafterLength() > RAFTERLONG) {
            int restLength = c.getRafterLength() - RAFTERLONG;
            counterRafterLong += c.getRafter();
            counterRafterSmall += 1;
            int currentLength = RAFTERSMALL;
            for (int i = 0; i < c.getRafter(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterRafterSmall += 1;
                    currentLength = RAFTERSMALL - restLength;
                }
            }
            if (!(c.getRafterLength() <= RAFTERSMALL && c.getRafterLength() <= RAFTERLONG)) {
                c.setFlatHinges(c.getFlatHinges() + counterRafterSmall * BOTHSIDES);
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

    private void roofRafter(Carport c, ArrayList<Material> list) {
        int counterRafterLong = 0;
        int counterRafterSmall = 0;

        if (c.getRoofRafterLength() <= RAFTERSMALL) {
            counterRafterSmall += c.getRoofRafter();
        } else if (c.getRoofRafterLength() <= RAFTERLONG) {
            counterRafterLong += c.getRoofRafter();
        } else if (c.getRoofRafterLength() > RAFTERLONG) {
            int restLength = (int) (c.getRoofRafterLength() - RAFTERLONG);
            counterRafterLong += c.getRoofRafter();
            counterRafterSmall += 1;
            int currentLength = RAFTERSMALL;
            for (int i = 0; i < c.getRoofRafter(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterRafterSmall += 1;
                    currentLength = RAFTERSMALL - restLength;
                }
            }
            if (!(c.getRoofRafterLength() <= RAFTERSMALL && c.getRoofRafterLength() <= RAFTERLONG)) {
                c.setFlatHinges(c.getFlatHinges() + counterRafterSmall * BOTHSIDES);
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

    private void beam(Carport c, ArrayList<Material> list) {
        int counterBeamLong = 0;
        int counterBeamSmall = 0;
        if (c.getBeamLength() <= BEAMSMALL) {
            counterBeamSmall += c.getBeam();
        } else if (c.getBeamLength() <= BEAMLONG) {
            counterBeamLong += c.getBeam();
        } else if (c.getBeamLength() > BEAMLONG) {
            int restLength = (int) (c.getBeamLength() - BEAMLONG);
            counterBeamLong += c.getBeam();
            counterBeamSmall += 1;
            int currentLength = BEAMSMALL;
            for (int i = 0; i < c.getBeam(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterBeamSmall += 1;
                    currentLength = BEAMSMALL - restLength;
                }
            }
            if (!(c.getBeamLength() <= BEAMSMALL && c.getBeamLength() <= BEAMLONG)) {
                c.setFlatHinges(c.getFlatHinges() + counterBeamSmall * BOTHSIDES);
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

    private void roofBeam(Carport c, ArrayList<Material> list) {
        int counterBeamLong = 0;
        int counterBeamSmall = 0;

        if (c.getBeamLength() <= BEAMSMALL) {
            counterBeamSmall += c.getRoofBeams();
        } else if (c.getBeamLength() <= BEAMLONG) {
            counterBeamLong += c.getRoofBeams();
        } else if (c.getBeamLength() > BEAMLONG) {
            int restLength = (int) (c.getBeamLength() - BEAMLONG);
            counterBeamLong += c.getRoofBeams();
            counterBeamSmall += 1;
            int currentLength = BEAMSMALL;
            for (int i = 0; i < c.getRoofBeams(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterBeamSmall += 1;
                    currentLength = BEAMSMALL - restLength;
                }
            }
            if (!(c.getBeamLength() <= BEAMSMALL && c.getBeamLength() <= BEAMLONG)) {
                c.setFlatHinges(c.getFlatHinges() + counterBeamSmall * 2);
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

    private void addRest(Carport c, ArrayList<Material> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("Plastmo Ecolite blåtonet") && list.get(i).getLength() == PLASTMOLENGTHLONG) {
                list.get(i).setAmount(c.getPlastmoLong());
            }
            if (list.get(i).getName().equals("Plastmo Ecolite blåtonet") && list.get(i).getLength() == PLASTMOLENGTHSMALL) {
                list.get(i).setAmount(c.getPlastmoSmall());
            }
            if (list.get(i).getName().equals("97x97	mm.	trykimp. Stolpe")) {
                list.get(i).setAmount(c.getPost());
            }
            if (list.get(i).getName().equals("Skruer 200 stk.")) {
                list.get(i).setAmount(c.getScrewBoxes());
            }
            if (list.get(i).getName().equals("FladtBeslag")) {
                list.get(i).setAmount(c.getFlatHinges());
            }
            if (list.get(i).getName().equals("Tegl")) {
                list.get(i).setAmount(c.getRoofTiles());
            }
            if (list.get(i).getName().equals("LBeslag")) {
                list.get(i).setAmount(c.getLHinges());
            }
            if (list.get(i).getName().equals("25x150	mm.	trykimp. Bræt") && list.get(i).getLength() == PLANKLENGTH) {
                list.get(i).setAmount(c.getPlanks() + c.getCoverPlanks());
            }
            if (list.get(i).getName().equals("45x95 Reglar ubh.") && list.get(i).getLength() == 240) {
                list.get(i).setAmount(c.getCoverStabilizerPlankSmall());
            }
            if (list.get(i).getName().equals("45x95 Reglar ubh.") && list.get(i).getLength() == 360) {
                list.get(i).setAmount(c.getCoverStabilizerPlanksLong());
            }
            if (list.get(i).getName().equals("Dørhåndtag")) {
                list.get(i).setAmount(c.getDoorKnob());
            }
            if (list.get(i).getName().equals("Dør hængsel")) {
                list.get(i).setAmount(c.getDoorHinge());
            }
        }
    }

}
