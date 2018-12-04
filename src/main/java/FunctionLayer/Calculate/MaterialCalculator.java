/*
 * To change this license header, choose License Headers in Project Properties.
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
        

        System.out.println("c get beam " + c.getBeamLength());
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

        if (c.getRafterLength() <= 480) {
            counterRafterSmall += c.getRafter();
        } else if (c.getRafterLength() <= 600) {
            counterRafterLong += c.getRafter();
        } else if (c.getRafterLength() > 600) {
            int restLength = c.getRafterLength() - 600;
            counterRafterLong += c.getRafter();
            counterRafterSmall += 1;
            int currentLength = 480;
            for (int i = 0; i < c.getRafter(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterRafterSmall += 1;
                    currentLength = 480 - restLength;
                }
            }
            c.setFlatHinges(c.getFlatHinges() + counterRafterSmall * 2);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == 480) {
                list.get(i).setAmount(list.get(i).getAmount() + counterRafterSmall);
                System.out.println("amount after set small " + list.get(i).getAmount());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == 600) {
                list.get(i).setAmount(counterRafterLong);
                System.out.println("amount after set big " + list.get(i).getAmount());
            }
        }
    }

    private void roofRafter(Carport c, ArrayList<Material> list) {
        int counterRafterLong = 0;
        int counterRafterSmall = 0;

        if (c.getRoofRafterLength() <= 480) {
            counterRafterSmall += c.getRoofRafter();
        } else if (c.getRoofRafterLength() <= 600) {
            counterRafterLong += c.getRoofRafter();
        } else if (c.getRoofRafterLength() > 600) {
            int restLength = (int) (c.getRoofRafterLength() - 600);
            counterRafterLong += c.getRoofRafter();
            counterRafterSmall += 1;
            int currentLength = 480;
            for (int i = 0; i < c.getRoofRafter(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterRafterSmall += 1;
                    currentLength = 480 - restLength;
                }
            }
            c.setFlatHinges(c.getFlatHinges() + counterRafterSmall * BOTHSIDES);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == 480) {
                 list.get(i).setAmount(counterRafterSmall);
                list.get(i).setAmount(counterRafterSmall + list.get(i).getAmount());
                list.get(i).setAmount(counterRafterSmall *BOTHSIDES + list.get(i).getAmount());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == 600) {
                list.get(i).setAmount(counterRafterLong *BOTHSIDES+ list.get(i).getAmount());
            }
        }
    }

    private void beam(Carport c, ArrayList<Material> list) {
        int counterBeamLong = 0;
        int counterBeamSmall = 0;
        System.out.println("beaaaaaaaaaaaaaaaaaam " + c.getBeamLength());
        if (c.getBeamLength() <= 480) {
            counterBeamSmall += c.getBeam();
        } else if (c.getBeamLength() <= 600) {
            counterBeamLong += c.getBeam();
        } else if (c.getBeamLength() > 600) {
            int restLength = (int) (c.getBeamLength() - 600);
            counterBeamLong += c.getBeam();
            counterBeamSmall += 1;
            int currentLength = 480;
            for (int i = 0; i < c.getBeam(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterBeamSmall += 1;
                    currentLength = 480 - restLength;
                }
            }
            System.out.println("counterBeamSmall  " + counterBeamSmall);
            System.out.println("counterbeamLong  " + counterBeamLong);
            c.setFlatHinges(c.getFlatHinges() + counterBeamSmall * BOTHSIDES);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == 480) {
                System.out.println("sæt bjælker --------------- kort");
                list.get(i).setAmount(counterBeamSmall*BOTHSIDES);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == 600) {
                System.out.println("sæt bjælker --------------- lang");
                list.get(i).setAmount(counterBeamLong*BOTHSIDES);
            }
        }
    }

    private void roofBeam(Carport c, ArrayList<Material> list) {
        int counterBeamLong = 0;
        int counterBeamSmall = 0;

        if (c.getBeamLength() <= 480) {
            counterBeamSmall += c.getRoofBeams();
        } else if (c.getBeamLength() <= 600) {
            counterBeamLong += c.getRoofBeams();
        } else if (c.getBeamLength() > 600) {
            int restLength = (int) (c.getBeamLength() - 600);
            counterBeamLong += c.getRoofBeams();
            counterBeamSmall += 1;
            int currentLength = 480;
            for (int i = 0; i < c.getRoofBeams(); i++) {
                if (restLength < currentLength) {
                    currentLength -= restLength;
                } else {
                    counterBeamSmall += 1;
                    currentLength = 480 - restLength;
                }
            }
            c.setFlatHinges(c.getFlatHinges() + counterBeamSmall * 2);
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == 480) {
                list.get(i).setAmount(counterBeamSmall* + list.get(i).getAmount());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == 600) {
                list.get(i).setAmount(counterBeamLong*2 + list.get(i).getAmount());
            }
        }
    }

    private void addRest(Carport c, ArrayList<Material> list) {
        System.out.println(" roof tiles amount ++"+c.getRoofTiles());
        System.out.println(" roof rafter amount ++"+c.getRoofRafter());
        System.out.println(" roof beams amount ++"+c.getRoofBeams());
        System.out.println(" beams amount  ++ "+ c.getBeam()    );
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("Plastmo Ecolite blåtonet") && list.get(i).getLength() == 600) {
                list.get(i).setAmount(c.getPlastmoLong());
            }
            if (list.get(i).getName().equals("Plastmo Ecolite blåtonet") && list.get(i).getLength() == 360) {
                list.get(i).setAmount(c.getPlastmoSmall());
            }
            if (list.get(i).getName().equals("97x97	mm.	trykimp. Stolpe") && list.get(i).getLength() == 300) {
                list.get(i).setAmount(c.getPost());
            }
            if (list.get(i).getName().equals("Skruer 200 stk.") && list.get(i).getLength() == 0) {
                list.get(i).setAmount(c.getScrewBoxes());
            }
            if (list.get(i).getName().equals("FladtBeslag") && list.get(i).getLength() == 0) {
                list.get(i).setAmount(c.getFlatHinges());
            }
            if (list.get(i).getName().equals("Tegl")){
                System.out.println("tegl tilføj til pris++");
                list.get(i).setAmount(c.getRoofTiles());
            }
            if (list.get(i).getName().equals("LBeslag") && list.get(i).getLength() == 0) {
                list.get(i).setAmount(c.getLHinges());
            }
        }
    }

}
