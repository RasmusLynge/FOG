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

/**
 *
 * @author Magnus
 */
public class MaterialCalculator {

    public void materialList(Carport c) throws GeneralException {
        DataMapper dm = new DataMapper();
        ArrayList<Material> list = dm.getMaterials();
        
        rafter(c, list);
        roofRafter(c, list);
        beam(c, list);
        roofBeam(c, list);
        c.setList(list);
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
                list.get(i).setAmount(counterRafterSmall);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == 600) {
                list.get(i).setAmount(counterRafterLong);
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
            c.setFlatHinges(c.getFlatHinges() + counterRafterSmall * 2);
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == 480) {
                 list.get(i).setAmount(counterRafterSmall);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("45x195	spærtræ	ubh.") && list.get(i).getLength() == 600) {
                list.get(i).setAmount(counterRafterLong);
            }
        }
    }

    private void beam(Carport c, ArrayList<Material> list) {
        int counterBeamLong = 0;
        int counterBeamSmall = 0;

        if (c.getBeamLength()<= 480) {
            counterBeamSmall += c.getBeam();
        } else if (c.getBeamLength() <= 600) {
            counterBeamLong += c.getBeam();
        } else if (c.getBeamLength() > 600) {
            int restLength = (int) (c.getBeamLength()- 600);
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
            c.setFlatHinges(c.getFlatHinges() + counterBeamSmall * 2);
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == 480) {
                list.get(i).setAmount(counterBeamSmall);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == 600) {
                list.get(i).setAmount(counterBeamLong);
            }
        }
    }

    private void roofBeam(Carport c, ArrayList<Material> list) {
                int counterBeamLong = 0;
        int counterBeamSmall = 0;

        if (c.getBeamLength()<= 480) {
            counterBeamSmall += c.getRoofBeams();
        } else if (c.getBeamLength() <= 600) {
            counterBeamLong += c.getRoofBeams();
        } else if (c.getBeamLength() > 600) {
            int restLength = (int) (c.getBeamLength()- 600);
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
                list.get(i).setAmount(counterBeamSmall);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals("200x200 mm. bjælke") && list.get(i).getLength() == 600) {
                list.get(i).setAmount(counterBeamLong);
            }
        }
    }

}
