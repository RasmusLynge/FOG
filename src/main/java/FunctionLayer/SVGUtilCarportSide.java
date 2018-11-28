/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.Carport;
import static FunctionLayer.Rules.*;

/**
 *
 * @author Magnus
 */
public class SVGUtilCarportSide {

    CarportCalculator carportcalculator = new CarportCalculator();

    public String printCarportSide(int lenght, int width, boolean roof, boolean shed) {
        String res = "<SVG width=\"1000\" height=\"1000\">" + caportFromSide(lenght, width, roof, shed) + "</SVG>";
        return res;
    }

    public String caportFromSide(int length, int width, boolean roof, boolean shed) {
        Carport c = new CarportCalculator().calculateAll(length, width, roof, shed);

        int outerFrameWidth = length + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int rafterSpaceing = OUTERFRAMEXPOS;

        int postYPos = OUTERFRAMEYPOS + WOODWIDTH;
        int postSpacing = OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGOUTERLAYER;

        String res = beamSVG(outerFrameWidth);
        res = postsSVG(res, length, innerFrameXPos, postYPos, c);
        res = raftersSvg(res, rafterSpaceing, outerFrameWidth, c);
        res = linesSVG(res, length, innerFrameXPos, c);
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

    private String raftersSvg(String res, int rafterSpaceing, int outerFrameWidth, Carport c) {
        //rafter
        for (int i = 0; i < c.getRafter(); i++) {
            res += square(WOODWIDTH, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS - WOODWIDTH);
            rafterSpaceing += c.getRafterSpacing();
        }
        res += square(WOODWIDTH, WOODWIDTH, OUTERFRAMEXPOS + outerFrameWidth - WOODWIDTH, OUTERFRAMEYPOS - WOODWIDTH);
        return res;
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
