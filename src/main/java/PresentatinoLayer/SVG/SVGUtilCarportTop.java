package PresentatinoLayer.SVG;

import FunctionLayer.Calculate.CarportCalculator;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.*;

public class SVGUtilCarportTop {

    /**
     * Creates the SVG canvas and appends the string created in the method
     * carportFromAbove
     *
     * @param car The carport the SVG will be made from
     * @return Returns an HTML string that contains the drawing of the carport
     * from above.
     * @throws DMException If the query to the database fails
     * @throws MakeOrderException if it fails to instantiate the carport
     */
    public String printCarportTop(Carport car) throws DMException, MakeOrderException {
        int canvasX = car.getLength() + 300;
        int canvasY = car.getWidth() + 300;
        String res = "<SVG width=\"" + 500 + "\" height=\"" + 500 + "\" viewBox=\"0 0 " + canvasX + " " + canvasY + "\">" + caportFromAbove(car) + "</SVG>";
        return res;
    }

    /**
     * Calls methods to generate the code for each square and line needed in the
     * full drawing, calculated from the carport measurements. Appends it all to
     * a string as html that can be viewed in the JSP.
     *
     * @param car The carport the SVG will be made from
     * @return Returns an HTML string that contains the drawings of the carport
     * from above.
     * @throws DMException If the query to the database fails
     * @throws MakeOrderException if it fails to instantiate the carport
     */
    public String caportFromAbove(Carport car) throws DMException, MakeOrderException {
        int outerFrameWidth = car.getWidth() + HANGOUTONESIDE * BOTHSIDES;
        int outerFrameLength = car.getLength() + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int innerFrameYPos = OUTERFRAMEYPOS + HANGOUTONESIDE;
        int innerLayerBottomYPos = innerFrameYPos + car.getWidth() - WOODWIDTH;
        int innerLayerEntranceCornorYPosForPost = innerLayerBottomYPos - EXTRAPOSTSPACING;
        int innerLayerEntranceCornorXPosForPost = innerFrameXPos + car.getLength() - POSTWIDTH;
        double rafterSpacing = OUTERFRAMEXPOS;

        String res = framesSVG(car.getLength(), car.getWidth(), outerFrameWidth, outerFrameLength, innerFrameXPos, innerFrameYPos);
        res = textSVG(res, car.getLength(), car.getWidth(), outerFrameLength, innerFrameXPos, outerFrameWidth, innerFrameYPos, car);
        res = linesSVG(res, car.getLength(), car.getWidth(), outerFrameLength, innerFrameXPos, innerFrameYPos, outerFrameWidth, car);
        res = beamsSVG(res, outerFrameLength, innerFrameYPos, innerLayerBottomYPos);
        res = raftersSVG(res, outerFrameWidth, rafterSpacing, outerFrameLength, car);

        if (car.isRoof()) {
            res = roofMiddleBeamSVG(res, outerFrameWidth, OUTERFRAMEYPOS, outerFrameLength);
            res = roofMiddleBeamSVG(res, outerFrameWidth, OUTERFRAMEYPOS, outerFrameLength);
            res = roofBeamSVG(outerFrameWidth, car, res, outerFrameLength);
        }

        res = postsSVG(res, car.getLength(), car.getWidth(), innerFrameXPos, innerFrameYPos, innerLayerEntranceCornorXPosForPost, innerLayerEntranceCornorYPosForPost, car, car.getShedLength(), car.isShed());
        if (car.isShed()) {
            res = shedPostsAndDoor(res, innerFrameXPos, car.getShedLength(), innerFrameYPos, innerLayerEntranceCornorYPosForPost);
            res = res += transSquare(car.getWidth(), car.getShedLength(), innerFrameXPos, innerFrameYPos);
        }
        return res;
    }

    private String shedPostsAndDoor(String res, int innerFrameXPos, int shedLength, int innerFrameYPos, int innerLayerEntranceCornorYPosForPost) {
        res += square(POSTWIDTH, POSTWIDTH, (innerFrameXPos + shedLength - POSTWIDTH), innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, (innerFrameXPos + shedLength - POSTWIDTH), (innerFrameYPos + DOORWIDTH));
        res += square(POSTWIDTH, POSTWIDTH, (innerFrameXPos + shedLength - POSTWIDTH), innerLayerEntranceCornorYPosForPost);
        res += line(innerFrameXPos + shedLength, (innerFrameYPos + POSTWIDTH), innerFrameXPos + shedLength - DOORWIDTH + POSTWIDTH, innerFrameYPos - POSTWIDTH + DOORWIDTH);
        return res;
    }

    private String roofBeamSVG(int outerFrameWidth, Carport c, String res, int outerFrameLength) {
        int roofBeamSVGSPacing = (outerFrameWidth / BOTHSIDES) / c.getRoofBeams();
        int yPosSpacingTop = OUTERFRAMEYPOS;

        res += square(WOODWIDTH, outerFrameLength, OUTERFRAMEXPOS, OUTERFRAMEYPOS);
        for (int i = 0; i < c.getRoofBeams(); i++) {
            res += square(WOODWIDTH, outerFrameLength, OUTERFRAMEXPOS, yPosSpacingTop);
            yPosSpacingTop += roofBeamSVGSPacing;
        }

        int yPosSPacingBot = outerFrameWidth + OUTERFRAMEYPOS - WOODWIDTH;
        for (int i = 0; i < c.getRoofBeams(); i++) {
            res += square(WOODWIDTH, outerFrameLength, OUTERFRAMEXPOS, yPosSPacingBot);
            yPosSPacingBot -= roofBeamSVGSPacing;
        }
        return res;
    }

    private String roofMiddleBeamSVG(String res, int outerFrameWidth, int outerFrameYPos, int outerFrameLength) {
        res += square(WOODWIDTH, outerFrameLength, OUTERFRAMEXPOS, outerFrameYPos + outerFrameWidth / BOTHSIDES);
        return res;
    }

    private String raftersSVG(String res, int outerFrameWidth, double rafterSpacing, int outerFrameLength, Carport c) {
        for (int i = 0; i < c.getRafter(); i++) {
            res += square(outerFrameWidth, WOODWIDTH, (int) rafterSpacing, OUTERFRAMEYPOS);
            rafterSpacing += c.getRafterSpacing();
        }
        res += square(outerFrameWidth, WOODWIDTH, OUTERFRAMEXPOS + outerFrameLength - WOODWIDTH, OUTERFRAMEYPOS);
        return res;
    }

    private String postsSVG(String res, int width, int length, int innerFrameXPos, int innerFrameYPos, int innerLayerEntranceCornorXPosForPost, int innerLayerEntranceCornorYPosForPost, Carport c, int shedLength, boolean shed) {
        res += square(POSTWIDTH, POSTWIDTH, innerFrameXPos, innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, innerLayerEntranceCornorXPosForPost, innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, innerFrameXPos, innerLayerEntranceCornorYPosForPost);
        res += square(POSTWIDTH, POSTWIDTH, innerLayerEntranceCornorXPosForPost, innerLayerEntranceCornorYPosForPost);

        if (shed) {

            if (c.getPost() >= MAXPOSTS && (shedLength + POSTWIDTH < width / POSTPOSITIONTHREE)) {
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
            }

            if (c.getPost() >= MAXPOSTS && shedLength + POSTWIDTH > (int) (width / POSTPOSITIONONEHALF + innerFrameXPos) - c.getPostSpacing()) {
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
            }

            if (shedLength + POSTWIDTH < width / POSTPOSITIONONEHALF) {
                res += square(POSTWIDTH, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos), innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos), innerLayerEntranceCornorYPosForPost);
            }

        } else {
            if (c.getPost() > MINIMUMPOSTS && c.getPost() < 8) {
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
            }

            if (c.getPost() >= MAXPOSTS) {

                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
                res += square(POSTWIDTH, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos), innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos), innerLayerEntranceCornorYPosForPost);
            }
        }

        return res;
    }

    private String beamsSVG(String res, int outerFrameWidth, int innerFrameYPos, int innerLayerBottomYPos) {
        res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, innerFrameYPos);
        res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, innerLayerBottomYPos);
        return res;
    }

    private String linesSVG(String res, int width, int height, int outerFrameWidth, int innerFrameXPos, int innerFrameYPos, int outerFrameHeight, Carport c) {
        res += line(OUTERFRAMEXPOS, OUTERFRAMEYPOS - LINESPACINGOUTERLAYER, outerFrameWidth + OUTERFRAMEXPOS, OUTERFRAMEYPOS - LINESPACINGOUTERLAYER);
        res += line(innerFrameXPos, OUTERFRAMEYPOS - LINESPACINGINNERLAYER, width + innerFrameXPos, OUTERFRAMEYPOS - LINESPACINGINNERLAYER);
        res += line(OUTERFRAMEXPOS - LINESPACINGINNERLAYER, innerFrameYPos, OUTERFRAMEXPOS - LINESPACINGINNERLAYER, innerFrameYPos + height);
        res += line(OUTERFRAMEXPOS - LINESPACINGOUTERLAYER, OUTERFRAMEYPOS, OUTERFRAMEXPOS - LINESPACINGOUTERLAYER, OUTERFRAMEYPOS + outerFrameHeight);
        res += line(OUTERFRAMEXPOS, OUTERFRAMEYPOS + outerFrameHeight + LINESPACINGINNERLAYER, (int) (c.getRafterSpacing() + OUTERFRAMEXPOS), OUTERFRAMEYPOS + outerFrameHeight + 10);
        return res;
    }

    private String framesSVG(int width, int height, int outerFrameHeight, int outerFrameWidth, int innerFrameXPos, int innerFrameYPos) {
        String res = "";
        res += square(outerFrameHeight, outerFrameWidth, OUTERFRAMEXPOS, OUTERFRAMEYPOS);
        res += square(height, width, innerFrameXPos, innerFrameYPos);
        return res;
    }

    private String textSVG(String res, int width, int height, int outerFrameWidth, int innerFrameXPos, int outerFrameHeight, int innerFrameYPos, Carport c) {
        res += text((OUTERFRAMEXPOS + outerFrameWidth) / 2, OUTERFRAMEYPOS - TEXTSPACINGOUTERLAYER, outerFrameWidth);
        res += text((OUTERFRAMEXPOS + outerFrameWidth) / 2, OUTERFRAMEYPOS - TEXTSPACINGINNERLAYER, width);
        res += text(TEXTBOTTOMLAYER + (OUTERFRAMEXPOS + (int) c.getRafterSpacing()) / 2, OUTERFRAMEYPOS + outerFrameHeight + TEXTBOTTOMLAYER, (int) c.getRafterSpacing());
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGINNERLAYER, innerFrameYPos + height / 2, height);
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGOUTERLAYER, OUTERFRAMEXPOS + outerFrameHeight / 2, outerFrameHeight);
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

    private String transSquare(int height, int width, int xPos, int yPos) {
        String res = "<rect x=\"" + xPos + "\" y=\"" + yPos + "\" height=\"" + height + "\" width=\"" + width + "\"\n"
                + "              style=\"stroke:#000000; fill: #c6c6c6\" fill-opacity=\"0.6\"/>";
        return res;
    }

}
