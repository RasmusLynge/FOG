package PresentatinoLayer.SVG;

import FunctionLayer.Calculate.CarportCalculator;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import static FunctionLayer.Rule.Rules.*;

public class SVGUtilCarportTop {

    /**
     * Creates the SVG canvas and appends the string created in the method carportFromAbove
     * 
     * @param length
     * @param width
     * @param roof
     * @param shed
     * @param shedLength
     * @param shedWidth
     * @return
     * @throws DMException
     * @throws MakeOrderException
     */
    public String printCarportTop(int length, int width, boolean roof, boolean shed, int shedLength, int shedWidth) throws DMException, MakeOrderException {
        int canvasX = length + 300;
        int canvasY = width + 300;
        // String res = "<SVG width=\"" + canvasX + "\" height=\"" + canvasY + "\">" + caportFromAbove(length, width, roof, shed, shedLength, shedWidth) + "</SVG>";
        String res = "<SVG width=\"" + 500 + "\" height=\"" + 500 + "\" viewBox=\"0 0 " + canvasX + " " + canvasY + "\">" + caportFromAbove(length, width, roof, shed, shedLength, shedWidth) + "</SVG>";
        return res;
    }

    /**
     * Calls methods to generate the code for each square and line needed in the full drawing, 
     * calculated from the carport measurements. 
     * Appends it all to a string as html that can be viewed in the JSP.
     * @param length
     * @param width
     * @param roof
     * @param shed
     * @param shedLength
     * @param shedWidth
     * @return
     * @throws DMException
     * @throws MakeOrderException
     */
    public String caportFromAbove(int length, int width, boolean roof, boolean shed, int shedLength, int shedWidth) throws DMException, MakeOrderException {
        Carport c = new CarportCalculator().calculateAll(length, width, roof, shed);
        int outerFrameWidth = width + HANGOUTONESIDE * BOTHSIDES;
        int outerFrameLength = length + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int innerFrameYPos = OUTERFRAMEYPOS + HANGOUTONESIDE;
        int innerLayerBottomYPos = innerFrameYPos + width - WOODWIDTH;
        int innerLayerEntranceCornorYPosForPost = innerLayerBottomYPos - EXTRAPOSTSPACING;
        int innerLayerEntranceCornorXPosForPost = innerFrameXPos + length - POSTWIDTH;
        double rafterSpacing = OUTERFRAMEXPOS;

        String res = framesSVG(length, width, outerFrameWidth, outerFrameLength, innerFrameXPos, innerFrameYPos);
        res = textSVG(res, length, width, outerFrameLength, innerFrameXPos, outerFrameWidth, innerFrameYPos, c);
        res = linesSVG(res, length, width, outerFrameLength, innerFrameXPos, innerFrameYPos, outerFrameWidth, c);
        res = beamsSVG(res, outerFrameLength, innerFrameYPos, innerLayerBottomYPos);
        res = raftersSVG(res, outerFrameWidth, rafterSpacing, outerFrameLength, c);
        if (roof) {
            res = roofMiddleBeamSVG(res, outerFrameWidth, OUTERFRAMEYPOS, outerFrameLength);

            //Middle beam
            res = roofMiddleBeamSVG(res, outerFrameWidth, OUTERFRAMEYPOS, outerFrameLength);
            //Beams to carry tiles
            res = roofBeamSVG(outerFrameWidth, c, res, outerFrameLength);
        }
        res = postsSVG(res, length, width, innerFrameXPos, innerFrameYPos, innerLayerEntranceCornorXPosForPost, innerLayerEntranceCornorYPosForPost, c, shedLength, shed);
        if (shed) {
            res = shedPostsAndDoor(res, innerFrameXPos, shedLength, innerFrameYPos, innerLayerEntranceCornorYPosForPost);
            res = res += transSquare(shedWidth, shedLength, innerFrameXPos, innerFrameYPos);
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
        //height, width, xPos, yPos
        int roofBeamSVGSPacing = (outerFrameWidth / BOTHSIDES) / c.getRoofBeams();
        int yPosSpacingTop = OUTERFRAMEYPOS;
        //Hardcode top roofBeam
        res += square(WOODWIDTH, outerFrameLength, OUTERFRAMEXPOS, OUTERFRAMEYPOS);
        for (int i = 0; i < c.getRoofBeams(); i++) {
            res += square(WOODWIDTH, outerFrameLength, OUTERFRAMEXPOS, yPosSpacingTop);
            yPosSpacingTop += roofBeamSVGSPacing;
        }
        //Hardcode bottom roofBeam
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
        //rafter
        for (int i = 0; i < c.getRafter(); i++) {
            res += square(outerFrameWidth, WOODWIDTH, (int) rafterSpacing, OUTERFRAMEYPOS);
            rafterSpacing += c.getRafterSpacing();
        }
        res += square(outerFrameWidth, WOODWIDTH, OUTERFRAMEXPOS + outerFrameLength - WOODWIDTH, OUTERFRAMEYPOS);

        return res;
    }

    private String postsSVG(String res, int width, int length, int innerFrameXPos, int innerFrameYPos, int innerLayerEntranceCornorXPosForPost, int innerLayerEntranceCornorYPosForPost, Carport c, int shedLength, boolean shed) {
        //post
        res += square(POSTWIDTH, POSTWIDTH, innerFrameXPos, innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, innerLayerEntranceCornorXPosForPost, innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, innerFrameXPos, innerLayerEntranceCornorYPosForPost);
        res += square(POSTWIDTH, POSTWIDTH, innerLayerEntranceCornorXPosForPost, innerLayerEntranceCornorYPosForPost);

        if (shed) {

            //carport with 8 posts
            if (c.getPost() >= MAXPOSTS && (shedLength + POSTWIDTH < width / POSTPOSITIONTHREE)) {
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
            }

            System.out.println("1 : " + (shedLength + POSTWIDTH) + " 2 : " + ((int) (width / POSTPOSITIONONEHALF + innerFrameXPos) - c.getPostSpacing()));
            if (c.getPost() >= MAXPOSTS && shedLength + POSTWIDTH > (int) (width / POSTPOSITIONONEHALF + innerFrameXPos) - c.getPostSpacing()) {
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
            }

            if (shedLength + POSTWIDTH < width / POSTPOSITIONONEHALF) {
                res += square(POSTWIDTH, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos), innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos), innerLayerEntranceCornorYPosForPost);
            }

        } else {
//                carport with 6 posts
            if (c.getPost() > MINIMUMPOSTS && c.getPost() < 8) {
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, innerFrameYPos);
                res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
            }
//                carport with 8 posts
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
        //beam
        res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, innerFrameYPos);
        res += square(WOODWIDTH, outerFrameWidth, OUTERFRAMEXPOS, innerLayerBottomYPos);
        return res;
    }

    private String linesSVG(String res, int width, int height, int outerFrameWidth, int innerFrameXPos, int innerFrameYPos, int outerFrameHeight, Carport c) {
        //width lines
        res += line(OUTERFRAMEXPOS, OUTERFRAMEYPOS - LINESPACINGOUTERLAYER, outerFrameWidth + OUTERFRAMEXPOS, OUTERFRAMEYPOS - LINESPACINGOUTERLAYER);
        res += line(innerFrameXPos, OUTERFRAMEYPOS - LINESPACINGINNERLAYER, width + innerFrameXPos, OUTERFRAMEYPOS - LINESPACINGINNERLAYER);
        //height lines
        res += line(OUTERFRAMEXPOS - LINESPACINGINNERLAYER, innerFrameYPos, OUTERFRAMEXPOS - LINESPACINGINNERLAYER, innerFrameYPos + height);
        res += line(OUTERFRAMEXPOS - LINESPACINGOUTERLAYER, OUTERFRAMEYPOS, OUTERFRAMEXPOS - LINESPACINGOUTERLAYER, OUTERFRAMEYPOS + outerFrameHeight);
        //rafter line
        res += line(OUTERFRAMEXPOS, OUTERFRAMEYPOS + outerFrameHeight + LINESPACINGINNERLAYER, (int) (c.getRafterSpacing() + OUTERFRAMEXPOS), OUTERFRAMEYPOS + outerFrameHeight + 10);
        return res;
    }

    private String framesSVG(int width, int height, int outerFrameHeight, int outerFrameWidth, int innerFrameXPos, int innerFrameYPos) {
        //outer inner background
        String res = "";
        //outerframe
        res += square(outerFrameHeight, outerFrameWidth, OUTERFRAMEXPOS, OUTERFRAMEYPOS);
        //innerframe
        res += square(height, width, innerFrameXPos, innerFrameYPos);
        return res;
    }

    private String textSVG(String res, int width, int height, int outerFrameWidth, int innerFrameXPos, int outerFrameHeight, int innerFrameYPos, Carport c) {
        //width text
        res += text((OUTERFRAMEXPOS + outerFrameWidth) / 2, OUTERFRAMEYPOS - TEXTSPACINGOUTERLAYER, outerFrameWidth);
        res += text((OUTERFRAMEXPOS + outerFrameWidth) / 2, OUTERFRAMEYPOS - TEXTSPACINGINNERLAYER, width);
        //rafter text
        res += text(TEXTBOTTOMLAYER + (OUTERFRAMEXPOS + (int) c.getRafterSpacing()) / 2, OUTERFRAMEYPOS + outerFrameHeight + TEXTBOTTOMLAYER, (int) c.getRafterSpacing());
        //height text
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
