package PresentatinoLayer.SVG;

import FunctionLayer.Calculate.CarportCalculator;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.*;

public class SVGUtilCarportSide {

    /**
     * This method creates a canvas for SVG to be places on, takes in the length
     * and width and an additional 300 to make the canvas adjust to the SVG
     * picture
     *
     * @param length Length of carport
     * @param width Width of carport
     * @param roof If there is a roof
     * @param shed If there is a shed
     * @param shedLength Length of the
     * @return retunere canvases til res som former SVG'en
     * @throws DMException
     * @throws MakeOrderException
     */
    public String printCarportSide(Carport car) throws DMException, MakeOrderException {
        int canvasX = car.getLength() + 300;
        int canvasY = POSTHEIGHT + 300;
        String res = "<SVG width=\"" + 500 + "\" height=\"" + 500 + "\" viewBox=\"0 0 " + canvasX + " " + canvasY + "\">" + caportFromSide(car) + "</SVG>";
        return res;
    }

    /**
     * This method creates the different parts of the SVG Checks if the roof and
     * shed and will make the SVG code to produce accordingly
     *
     * @param length Length of carport
     * @param width Width of carport
     * @param roof If there is a roof
     * @param shed If there is a shed
     * @param shedLength Length of the
     * @return returns the final string of the SVG code the produce the SVG
     * drawing
     * @throws DMException Throws when there is an exception in the SQL call to
     * the database
     * @throws MakeOrderException Throws when there is an error in the creation
     * of an order
     */
    public String caportFromSide(Carport car) throws DMException, MakeOrderException {
        int length = car.getLength();
        int width = car.getWidth();
        boolean roof = car.isRoof();
        boolean shed = car.isShed();
        int shedLength = car.getShedLength();

        int outerFrameWidth = length + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int rafterSpaceing = OUTERFRAMEXPOS;
        int postYPos = OUTERFRAMEYPOS + WOODWIDTH;
        int roofHeight = (int) car.getRoofPostHeight();

        String res = beamSVG(outerFrameWidth);
        res = postsSVG(res, length, innerFrameXPos, postYPos, car, shed, shedLength);
        res = raftersSvg(res, rafterSpaceing, outerFrameWidth, roof, roofHeight, car);
        res = linesSVG(res, length, innerFrameXPos, car);

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
        res += text(innerFrameXPos + CENTEROFPOSTMEASSURE + TEXTBOTTOMLAYER, OUTERFRAMEYPOS + POSTHEIGHT + TEXTBOTTOMLAYER, messurement);
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGINNERLAYER, OUTERFRAMEYPOS + TEXTSPACINGINNERLAYER + POSTHEIGHT / 2, POSTHEIGHT);
        return res;
    }

    private String linesSVG(String res, int length, int innerFrameXPos, Carport c) {
        if (c.getPost() == MINIMUMPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, length + innerFrameXPos - CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
            res = textSVG(res, innerFrameXPos, length);
        }

        if (c.getPost() > MINIMUMPOSTS && c.getPost() < MAXPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, length / POSTPOSITIONTWO + innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
            res = textSVG(res, innerFrameXPos, length / POSTPOSITIONTWO);
        }

        if (c.getPost() >= MAXPOSTS) {
            res += line(innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER, length / POSTPOSITIONTHREE + innerFrameXPos + CENTEROFPOSTMEASSURE, OUTERFRAMEYPOS + POSTHEIGHT + LINESPACINGINNERLAYER);
            res = textSVG(res, innerFrameXPos, length / POSTPOSITIONTHREE);
        }
        res += line(OUTERFRAMEXPOS - LINESPACINGINNERLAYER, OUTERFRAMEYPOS + WOODWIDTH, OUTERFRAMEXPOS - LINESPACINGINNERLAYER, OUTERFRAMEYPOS + POSTHEIGHT + WOODWIDTH);
        return res;
    }

    private String raftersSvg(String res, int rafterSpaceing, int outerFrameWidth, boolean roof, int roofHeight, Carport c) {
        if (roof) {
            res = roofRafterSVG(res, roofHeight, outerFrameWidth, c, rafterSpaceing);
            res = roofBeamsSVG(roofHeight, c, res, outerFrameWidth);
        } else {
            res = flatRoofRafters(c, res, rafterSpaceing, outerFrameWidth);
        }
        return res;
    }

    private String roofBeamsSVG(int roofHeight, Carport c, String res, int outerFrameWidth) {
        double spacingCalculat = (double) Math.ceil((double) roofHeight / c.getRoofBeams());
        int svgRoofBeamSpacing = (int) spacingCalculat;
        int count = OUTERFRAMEYPOS - roofHeight;

        for (int i = 0; i < c.getRoofBeams(); i++) {
            res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, count);
            count += svgRoofBeamSpacing;
        }
        return res;
    }

    private String roofRafterSVG(String res, int roofHeight, int outerFrameWidth, Carport c, int rafterSpaceing) {
        res += square(roofHeight + WOODWIDTH, WOODWIDTH, OUTERFRAMEXPOS + outerFrameWidth - WOODWIDTH, OUTERFRAMEYPOS - roofHeight);
        for (int i = 0; i < c.getRafter(); i++) {
            res += square(roofHeight + WOODWIDTH, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS - roofHeight);
            rafterSpaceing += c.getRafterSpacing();
        }
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

    private String postsSVG(String res, int length, int innerFrameXPos, int postYPos, Carport c, boolean shed, int shedLength) {
        if (!shed) {
            res += square(POSTHEIGHT, POSTWIDTH, innerFrameXPos, postYPos);
        }

        res += square(POSTHEIGHT, POSTWIDTH, innerFrameXPos + length - POSTWIDTH, postYPos);
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
        res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, OUTERFRAMEYPOS);
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
