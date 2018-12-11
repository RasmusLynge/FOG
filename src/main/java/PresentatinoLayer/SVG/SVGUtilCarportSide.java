/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer.SVG;

import FunctionLayer.Calculate.CarportCalculator;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.*;

/**
 * Skal rykkes til presentation
 *
 * @author Magnus
 */
public class SVGUtilCarportSide {

    public String printCarportSide(int length, int width, boolean roof, boolean shed, int shedLength) throws GeneralException, MakeOrderException {
        int canvasX = length + 300;
        int canvasY = POSTHEIGHT + 300;
        String res = "<SVG width=\"" + canvasX + "\" height=\"" + canvasY + "\">" + caportFromSide(length, width, roof, shed, shedLength) + "</SVG>";
        return res;
    }

    public String caportFromSide(int length, int width, boolean roof, boolean shed, int shedLength) throws GeneralException, MakeOrderException {
        Carport c = new CarportCalculator().calculateAll(length, width, roof, shed);

        int outerFrameWidth = length + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int rafterSpaceing = OUTERFRAMEXPOS;
        int postYPos = OUTERFRAMEYPOS + WOODWIDTH;
        int roofHeight = (int) c.getRoofPostHeight();

        String res = beamSVG(outerFrameWidth);
        res = postsSVG(res, length, innerFrameXPos, postYPos, c, shed, shedLength);
        res = raftersSvg(res, rafterSpaceing, outerFrameWidth, roof, roofHeight, c);
        res = linesSVG(res, length, innerFrameXPos, c);
        if (shed) {
            res = shedSVG(res, shedLength, innerFrameXPos, postYPos);
            res = shedLinesSVG(res, shedLength, innerFrameXPos, postYPos);
        }
        return res;

    }

    private String shedLinesSVG(String res, int shedLength, int innerFrameXPos, int startYPos) {
        for (int i = 0; i < shedLength; i += 10) {
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
        res += text(innerFrameXPos + CENTEROFPOSTMEASSURE + TEXTBOTTOMLAYER, OUTERFRAMEYPOS + POSTHEIGHT + TEXTBOTTOMLAYER, messurement);
        //Post height text
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGINNERLAYER, OUTERFRAMEYPOS + TEXTSPACINGINNERLAYER + POSTHEIGHT / 2, POSTHEIGHT);
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
        if (roof) {
            res = roofBeamsSVG(outerFrameWidth, c, res, rafterSpaceing);
        } else {
            res = flatRoofRafters(c, res, rafterSpaceing, outerFrameWidth);
        }
        return res;
    }

    private String roofBeamsSVG(int outerFrameWidth, Carport c, String res, int rafterSpaceing) {
        int yPosSpacingTop = OUTERFRAMEYPOS;
        int roofBeamSVGSPacing = (outerFrameWidth / BOTHSIDES) / c.getRoofBeams();
        for (int i = 0; i < c.getRoofBeams(); i++) {
            res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, yPosSpacingTop);
            yPosSpacingTop -= roofBeamSVGSPacing;
        }
        res = roofRaftersSVG(res, outerFrameWidth, c, rafterSpaceing, yPosSpacingTop);

        return res;
    }

    private String flatRoofRafters(Carport c, String res, int rafterSpaceing, int outerFrameWidth) {
        for (int i = 0; i < c.getRafter(); i++) {
            res += square(WOODWIDTH, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS - WOODWIDTH);
            rafterSpaceing += c.getRafterSpacing();
        }
        res += square(WOODWIDTH, WOODWIDTH, OUTERFRAMEXPOS + outerFrameWidth - WOODWIDTH, OUTERFRAMEYPOS - WOODWIDTH);
        return res;
    }

    private String roofRaftersSVG(String res, int outerFrameWidth, Carport c, int rafterSpaceing, int yPosSpacingTop) {
        int heightOfTopBeam = (yPosSpacingTop + WOODWIDTH) * c.getRoofBeams();

        res += square(heightOfTopBeam, WOODWIDTH, OUTERFRAMEXPOS + outerFrameWidth - WOODWIDTH, OUTERFRAMEYPOS - heightOfTopBeam + WOODWIDTH);
        for (int i = 0; i < c.getRafter(); i++) {
            res += square(heightOfTopBeam, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS - heightOfTopBeam + WOODWIDTH);
            rafterSpaceing += c.getRafterSpacing();
        }
        return res;
    }

    private String postsSVG(String res, int length, int innerFrameXPos, int postYPos, Carport c, boolean shed, int shedLength) {
        //Post
        if (!shed) {
            res += square(POSTHEIGHT, POSTWIDTH, innerFrameXPos, postYPos);
        }

        res += square(POSTHEIGHT, POSTWIDTH, innerFrameXPos + length - POSTWIDTH, postYPos);
        //carport with 6 posts
        if (c.getPost() > MINIMUMPOSTS && c.getPost() < MAXPOSTS) {
            // res += square(POSTHEIGHT, POSTWIDTH, length / POSTPOSITIONTWO + innerFrameXPos, postYPos);
        }

        //carport with 8 posts
        if (c.getPost() >= MAXPOSTS) {
            if (shedLength < length / POSTPOSITIONTHREE) {
                res += square(POSTHEIGHT, POSTWIDTH, length / POSTPOSITIONTHREE + innerFrameXPos, postYPos);
            }
            if (shedLength < length / POSTPOSITIONONEHALF) {
                res += square(POSTHEIGHT, POSTWIDTH, (int) (length / POSTPOSITIONONEHALF + innerFrameXPos - POSTWIDTH), postYPos);
            }
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
