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

    public String printCarportSide(int lenght, int width, boolean roof, boolean shed) {
        String res = "<SVG width=\"1000\" height=\"1000\">" + caportFromSide(lenght, width, roof, shed, 100) + "</SVG>";
        return res;
    }

    public String caportFromSide(int length, int width, boolean roof, boolean shed, int shedLength) {
        Carport c = new CarportCalculator().calculateAll(length, width, roof, shed);

        int outerFrameWidth = length + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int rafterSpaceing = OUTERFRAMEXPOS;
        int postYPos = OUTERFRAMEYPOS + WOODWIDTH;
        int roofHeight = 48;

        String res = beamSVG(outerFrameWidth);
        res = postsSVG(res, length, innerFrameXPos, postYPos, c);
        res = raftersSvg(res, rafterSpaceing, outerFrameWidth, roof, roofHeight, c);
        res = linesSVG(res, length, innerFrameXPos, c);
        if (shed == true) {
            res = shedSVG(res, shedLength, innerFrameXPos, postYPos);
            res = shedLinesSVG(res, shedLength, innerFrameXPos, postYPos);
        }
        return res;

    }

    private String shedLinesSVG(String res, int shedLength, int innerFrameXPos, int startYPos) {
        for (int i = 0; i < shedLength; i+=10) {
            res += shedLine(innerFrameXPos + i, startYPos, innerFrameXPos + i, startYPos + POSTHEIGHT);
        }
        return res;
    }

    private String shedSVG(String res, int shedlength, int innerFrameXPos, int innerFrameYPos) {
        res += transSquare(POSTHEIGHT, shedlength, innerFrameXPos, innerFrameYPos);
        return res;
    }

    private String textSVG(String res, int innerFrameXPos, int messurement) {
        //Post spacing text
        res += text(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + TEXTBOTTOMLAYER, messurement);
        //Post height text
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGINNERLAYER, OUTERFRAMEYPOS + POSTHEIGHT + WOODWIDTH, POSTHEIGHT);
        return res;
    }

    private String linesSVG(String res, int length, int innerFrameXPos, Carport c) {
        //Post spacing lines with 4 posts
        if (c.getPost() == MINIMUMPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, length + innerFrameXPos - CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
            res = textSVG(res, innerFrameXPos, length);
        }
        //Post spacing lines with 6 posts
        if (c.getPost() > MINIMUMPOSTS && c.getPost() < MAXPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, length / POSTPOSITIONTWO + innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
            res = textSVG(res, innerFrameXPos, length / POSTPOSITIONTWO);
        }
        //Post spacing lines with 8 posts
        if (c.getPost() >= MAXPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, length / POSTPOSITIONTHREE + innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
            res = textSVG(res, innerFrameXPos, length / 3);
        }
        //Post height lines
        res += line(OUTERFRAMEXPOS - LINESPACINGINNERLAYER, OUTERFRAMEYPOS + WOODWIDTH, OUTERFRAMEXPOS - LINESPACINGINNERLAYER, OUTERFRAMEYPOS + POSTHEIGHT + WOODWIDTH);
        return res;
    }

    private String raftersSvg(String res, int rafterSpaceing, int outerFrameWidth, boolean roof, int roofHeight, Carport c) {
        //rafter
        if (roof == true) {
            res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, OUTERFRAMEYPOS - roofHeight);
            res += square(roofHeight, WOODWIDTH, OUTERFRAMEXPOS + outerFrameWidth - WOODWIDTH, OUTERFRAMEYPOS - roofHeight + WOODWIDTH);
            for (int i = 0; i < c.getRafter(); i++) {
                res += square(roofHeight, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS - roofHeight + WOODWIDTH);
                rafterSpaceing += c.getRafterSpacing();
            }
            for (int i = 10; i < roofHeight; i += 10) {
                res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, OUTERFRAMEYPOS - i);
            }

            return res;
        } else {
            for (int i = 0; i < c.getRafter(); i++) {
                res += square(WOODWIDTH, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS - WOODWIDTH);
                rafterSpaceing += c.getRafterSpacing();
            }
            res += square(WOODWIDTH, WOODWIDTH, OUTERFRAMEXPOS + outerFrameWidth - WOODWIDTH, OUTERFRAMEYPOS - WOODWIDTH);
            return res;
        }
    }

    private String postsSVG(String res, int length, int innerFrameXPos, int postYPos, Carport c) {
        //Post
        res += square(POSTHEIGHT, POSTWIDTH, innerFrameXPos, postYPos);
        res += square(POSTHEIGHT, POSTWIDTH, innerFrameXPos + length - POSTWIDTH, postYPos);
        //carport with 6 posts
        if (c.getPost() > MINIMUMPOSTS && c.getPost() < MAXPOSTS) {
            res += square(POSTHEIGHT, POSTWIDTH, length / POSTPOSITIONTWO + innerFrameXPos, postYPos);
        }
        //carport with 8 posts
        if (c.getPost() >= MAXPOSTS) {

            res += square(POSTHEIGHT, POSTWIDTH, length / POSTPOSITIONTHREE + innerFrameXPos, postYPos);
            res += square(POSTHEIGHT, POSTWIDTH, (int) (length / POSTPOSITIONONEHALF + innerFrameXPos - POSTWIDTH), postYPos);

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

    private String transSquare(int height, int width, int xPos, int yPos) {
        String res = "<rect x=\"" + xPos + "\" y=\"" + yPos + "\" height=\"" + height + "\" width=\"" + width + "\"\n"
                + "              style=\"stroke:#000000; fill: #c6c6c6\" fill-opacity=\"0.6\"/>";
        return res;
    }

    private String line(int xPos1, int yPos1, int xPos2, int yPos2) {
        String res = "<line x1=\"" + xPos1 + "\" y1=\"" + yPos1 + "\" x2=\"" + xPos2 + "\" y2=\"" + yPos2 + "\" "
                + "style=\"stroke:rgb(255,0,0);stroke-width:2\" />";
        return res;
    }

    private String shedLine(int xPos1, int yPos1, int xPos2, int yPos2) {
        String res = "<line x1=\"" + xPos1 + "\" y1=\"" + yPos1 + "\" x2=\"" + xPos2 + "\" y2=\"" + yPos2 + "\" "
                + "style=\"stroke:#5b5b5b;stroke-opacity=\"0.6\"stroke-width:2\" />";
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
