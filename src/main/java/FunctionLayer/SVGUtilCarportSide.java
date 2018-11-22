/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.HashMap;

/**
 *
 * @author Magnus
 */
public class SVGUtilCarportSide {

    int width = 300;
    int height = 300;
    private static final int OUTERFRAMEXPOS = 100;
    private static final int OUTERFRAMEYPOS = 100;
    private static final int HANGOUTONESIDE = 35;
    private static final int ENTRANCEHANGOUT = 65;
    private static final int BOTHSIDES = 2;
    private static final int WOODWIDTH = 5;
    private static final int POSTWIDTH = 15;
    private static final int EXTRAPOSTSPACING = 10;
    private static final int EXTRARAFTER = 1;
    private static final int MINIMUMPOSTS = 4;
    private static final int MAXPOSTS = 8;
    private static final double POSTPOSITIONONEHALF = 1.5;
    private static final int POSTPOSITIONTWO = 2;
    private static final int POSTPOSITIONTHREE = 3;
    private static final int LINESPACINGOUTERLAYER = 35;
    private static final int LINESPACINGINNERLAYER = 10;
    private static final int TEXTSPACINGOUTERLAYER = 40;
    private static final int TEXTSPACINGINNERLAYER = 15;
    CarportCalculator carportcalculator = new CarportCalculator();
    HashMap<String, Integer> mapCarport = carportcalculator.calculateAll(width, height);

    public String caportFromSide(){
        String res = "";
        
        return res;
    }
    private String background() {
        String res = "<rect x=\"0\" y=\"0\" height=\"1000\" width=\"1000\"\n"
                + "              style=\"stroke:#000000; fill: #f9f9f9\"/>";
        return res;
    }

    private String square(int height, int width, int xPos, int yPos) {
        String res = "<rect x=\"" + xPos + "\" y=\"" + yPos + "\" height=\"" + height + "\" width=\"" + width + "\"\n"
                + "              style=\"stroke:#000000; fill: #efede8\"/>";
        return res;
    }

    private String line(int xPos1, int yPos1, int xPos2, int yPos2) {
        String res = "<line x1=\"" + xPos1 + "\" y1=\"" + yPos1 + "\" x2=\"" + xPos2 + "\" y2=\"" + yPos2 + "\" "
                + "style=\"stroke:rgb(255,0,0);stroke-width:2\" />";
        return res;
    }

    private String text(int xPos, int yPos, int messurement) {
        String res = "<text x=\"" + xPos + "\" y=\"" + yPos + "\" fill=\"red\">" + messurement + "cm</text>";
        return res;
    }

    private String textRotated(int xPos, int yPos, int messurement) {
        String res = "<text transform=\"translate(" + xPos + "," + yPos + ")rotate(270)\" fill=\"red\">" + messurement + "cm</text>";
        return res;
    }
}
