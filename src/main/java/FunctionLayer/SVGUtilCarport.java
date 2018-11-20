/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.util.HashMap;
import static javafx.beans.binding.Bindings.length;

/**
 *
 * @author Magnus
 */
public class SVGUtilCarport {

    int width = 600;
    int height = 450;
    private static final int OUTERFRAMEXPOS = 140;
    private static final int OUTERFRAMEYPOS = 50;
    private static final int HANGOUTONESIDE = 35;
    private static final int ENTRANCEHANGOUT = 65;
    private static final int BOTHSIDES = 2;
    private static final int WOODWIDTH = 5;

    CarportCalculator carportcalculator = new CarportCalculator();
    HashMap<String, Integer> mapCarport = carportcalculator.calculateAll(width, height);

    public String caport() {
        int xPos = 0;
        int yPos = 0;
        int outerFrameHeight = height + HANGOUTONESIDE * BOTHSIDES;
        System.out.println("outerhieght util " + outerFrameHeight);
        int outerFrameWidth = width + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        System.out.println("outerwidth util" + outerFrameWidth);
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int innerFrameYPos = OUTERFRAMEYPOS + HANGOUTONESIDE;
        int innerLayerBottom = innerFrameYPos + height - WOODWIDTH;
        int rafterSpaceing = OUTERFRAMEXPOS;

        String res = "";
        res += background();
        res += outerFrame(outerFrameHeight, outerFrameWidth, OUTERFRAMEXPOS, OUTERFRAMEYPOS);
        res += innerFrame(height, width, innerFrameXPos, innerFrameYPos);
        res += beam(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, innerFrameYPos);
        res += beam(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, innerLayerBottom);
//        res += post(height, width, xPos, yPos);
        System.out.println("toatlrafter" + mapCarport.get("totalRafters"));
        System.out.println("rafterspacing --------- " + mapCarport.get("newRafterSpacing"));
        for (int i = 0; i < mapCarport.get("totalRafters"); i++) {
            System.out.println("rafter in loop" + i);
            System.out.println("rafterspacing in loop " + rafterSpaceing);
            res += rafter(outerFrameHeight, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS);
            rafterSpaceing += mapCarport.get("newRafterSpacing");
        }
        return res;
    }

    public String background() {
        String res = "<rect x=\"0\" y=\"0\" height=\"1000\" width=\"1000\"\n"
                + "              style=\"stroke:#000000; fill: #f9f9f9\"/>";
        return res;
    }

    public String outerFrame(int height, int width, int xPos, int yPos) {
        String res = "";
        res += "<rect x=\"" + xPos + "\" y=\"" + yPos + "\" height=\"" + height + "\" width=\"" + width + "\"\n"
                + "              style=\"stroke:#000000; fill: #efede8\"/>";
        return res;
    }

    public String innerFrame(int height, int width, int xPos, int yPos) {
        String res = "";
        res += "<rect x=\"" + xPos + "\" y=\"" + yPos + "\" height=\"" + height + "\" width=\"" + width + "\"\n"
                + "              style=\"stroke:#000000; fill: #efede8\"/>";
        return res;
    }

    public String beam(int height, int width, int xPos, int yPos) {
        String res = "";
        res += "<rect x=\"" + xPos + "\" y=\"" + yPos + "\" height=\"" + height + "\" width=\"" + width + "\"\n"
                + "              style=\"stroke:#000000; fill: #efede8\"/>";
        return res;
    }

    public String post(int height, int width, int xPos, int yPos) {
        String res = "";
        return res;
    }

    public String rafter(int height, int width, int xPos, int yPos) {
        String res = "";
        res += "<rect x=\"" + xPos + "\" y=\"" + yPos + "\" height=\"" + height + "\" width=\"" + width + "\"\n"
                + "              style=\"stroke:#000000; fill: #efede8\"/>";
        return res;
    }

    /*
     <rect x="0" y="0" height="1000" width="1000"
              style="stroke:#000000; fill: #f9f9f9"/>

        <!-- Meassurements -->
        <text x="140" y="35" fill="red">720cm</text>
        <line x1="140" y1="40" x2="860" y2="40" style="stroke:rgb(255,0,0);stroke-width:2" />
        <text x="85" y="65" fill="red">570cm</text>
        <line x1="130" y1="50" x2="130" y2="570" style="stroke:rgb(255,0,0);stroke-width:2" />

        <!-- "inside and outside meassure" -->
        
        
<!-- "beams" -->
        <rect x="140" y="85" height="5" width="720"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="140" y="530" height="5" width="720"
              style="stroke:#000000; fill: #efede8"/>

        <!-- "posts" -->
        <rect x="175" y="85" height="15" width="15"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="740" y="85" height="15" width="15"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="175" y="520" height="15" width="15"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="740" y="520" height="15" width="15"
              style="stroke:#000000; fill: #efede8"/>

        <!-- "Rafter" -->
        <text x="140" y="600" fill="red">60cm</text>
        <line x1="140" y1="580" x2="200" y2="580" style="stroke:rgb(255,0,0);stroke-width:2" />

        <rect x="140" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="200" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="260" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="320" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="380" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="440" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="500" y="50" height="520" width="5"
              style="stroke
<rect x="560" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="620" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="680" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="740" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="800" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
        <rect x="855" y="50" height="520" width="5"
              style="stroke:#000000; fill: #efede8"/>
     */
}
