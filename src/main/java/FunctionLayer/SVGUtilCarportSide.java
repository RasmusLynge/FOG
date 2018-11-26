/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;
import FunctionLayer.Carport;

/**
 *
 * @author Magnus
 */
public class SVGUtilCarportSide {

//    int width = 720;
//    int height = 720;
    private static final int OUTERFRAMEXPOS = 100;
    private static final int OUTERFRAMEYPOS = 100;
    private static final int HANGOUTONESIDE = 35;
    private static final int ENTRANCEHANGOUT = 65;
    private static final int BOTHSIDES = 2;
    private static final int WOODWIDTH = 5;
    private static final int POSTWIDTH = 15;
    private static final int MINIMUMPOSTS = 4;
    private static final int MAXPOSTS = 8;
    private static final double POSTPOSITIONONEHALF = 1.5;
    private static final int POSTPOSITIONTWO = 2;
    private static final int POSTPOSITIONTHREE = 3;
    private static final int LINESPACINGOUTERLAYER = 35;
    private static final int LINESPACINGINNERLAYER = 10;
    private static final int TEXTSPACINGINNERLAYER = 15;
    private static final int TEXTBOTTOMLAYER = 30;
    CarportCalculator carportcalculator = new CarportCalculator();
//skriv i calc tilføj til hashmap
    private static final int POSTHEIGHT = 200;
    private static final int CENTEROFPOSTMEASSURE = 7;

    public String printCarportSide(int width, int height, boolean roof, boolean shed) {
        String res = "<SVG width=\"1000\" height=\"1000\">" + caportFromSide(width, height, roof, shed) + "</SVG>";
        return res;
    }

    public String caportFromSide(int width, int height, boolean roof, boolean shed) {
        Carport c = new CarportCalculator().calculateAll(height, width, roof, shed);
        
        int outerFrameWidth = width + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int rafterSpaceing = OUTERFRAMEXPOS;

        int postYPos = OUTERFRAMEYPOS + WOODWIDTH;
        int postSpacing = OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGOUTERLAYER;

        String res = beamSVG(outerFrameWidth);
        res = postsSVG(res, width, innerFrameXPos, postYPos, c);
        res = raftersSvg(res, rafterSpaceing, outerFrameWidth, c);
        res = linesSVG(res, width, innerFrameXPos, c);
        res = textSVG(res, innerFrameXPos, postSpacing);
        return res;

    }

    private String textSVG(String res, int innerFrameXPos, int postSpacing) {
        //Post spacing text
        res += text(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + TEXTBOTTOMLAYER, postSpacing);
        //Post height text
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGINNERLAYER, OUTERFRAMEYPOS + POSTHEIGHT + WOODWIDTH, POSTHEIGHT);
        return res;
    }

    private String linesSVG(String res, int width, int innerFrameXPos, Carport c) {
        //Post spacing lines with 4 posts
        if (c.getPost() == MINIMUMPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, width + innerFrameXPos - CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
        }
        //Post spacing lines with 6 posts
        if (c.getPost() > MINIMUMPOSTS && c.getPost() < MAXPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, width / POSTPOSITIONTWO + innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
        }
        //Post spacing lines with 8 posts
        if (c.getPost() >= MAXPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, width / POSTPOSITIONTHREE + innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
        }
        //Post height lines
        res += line(OUTERFRAMEXPOS - LINESPACINGINNERLAYER, OUTERFRAMEYPOS + WOODWIDTH, OUTERFRAMEXPOS - LINESPACINGINNERLAYER, OUTERFRAMEYPOS + POSTHEIGHT + WOODWIDTH);
        return res;
    }

    private String raftersSvg(String res, int rafterSpaceing, int outerFrameWidth, Carport c) {
        //rafter
        for (int i = 0; i < c.getRafter(); i++) {
            res += square(WOODWIDTH, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS - WOODWIDTH);
            rafterSpaceing += c.getRafterSpacing();
        }
        res += square(WOODWIDTH, WOODWIDTH, OUTERFRAMEXPOS + outerFrameWidth - WOODWIDTH, OUTERFRAMEYPOS - WOODWIDTH);
        return res;
    }

    private String postsSVG(String res, int width, int innerFrameXPos, int postYPos, Carport c) {
        //Post
        res += square(POSTHEIGHT, POSTWIDTH, innerFrameXPos, postYPos);
        res += square(POSTHEIGHT, POSTWIDTH, innerFrameXPos + width - POSTWIDTH, postYPos);
        //carport with 6 posts
        if (c.getPost() > MINIMUMPOSTS && c.getPost() < MAXPOSTS) {
            res += square(POSTHEIGHT, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, postYPos);
        }
        //carport with 8 posts
        if (c.getPost() >= MAXPOSTS) {

            res += square(POSTHEIGHT, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, postYPos);
            res += square(POSTHEIGHT, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos - POSTWIDTH), postYPos);

        }
        return res;
    }

    private String beamSVG(int outerFrameWidth) {
        String res = "";
        //Beam
        res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, OUTERFRAMEYPOS);
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
