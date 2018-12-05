package PresentatinoLayer.SVG;

import FunctionLayer.Calculate.CarportCalculator;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Exception.GeneralException;
import static FunctionLayer.Rule.Rules.*;

/**
 * Skal rykkes til presentation
 *
 * @author Magnus
 */
public class SVGUtilCarportTop {

    public String printCarportTop(int length, int width, boolean roof, boolean shed, int shedLength, int shedWidth) throws GeneralException {
        int canvasX = length + 300;
        int canvasY = width + 300;
        String res = "<SVG width=\"" + canvasX + "\" height=\"" + canvasY + "\">" + caportFromAbove(length, width, roof, shed, shedLength, shedWidth) + "</SVG>";

        return res;
    }

    public String caportFromAbove(int length, int width, boolean roof, boolean shed, int shedLength, int shedWidth) throws GeneralException {
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
        res = postsSVG(res, length, width, innerFrameXPos, innerFrameYPos, innerLayerEntranceCornorXPosForPost, innerLayerEntranceCornorYPosForPost, c);
        res = raftersSVG(res, outerFrameWidth, rafterSpacing, outerFrameLength, c);
        if (roof == true) {
            res = roofMiddleBeamSVG(res, outerFrameWidth, OUTERFRAMEYPOS, outerFrameLength);

            //Middle beam
            res = roofMiddleBeamSVG(res, outerFrameWidth, OUTERFRAMEYPOS, outerFrameLength);
            //Beams to carry tiles
            res = roofBeamSVG(outerFrameWidth, c, res, outerFrameLength);
        }
        if (shed == true) {
            res += square(POSTWIDTH, POSTWIDTH, (innerFrameXPos + c.getShedLength()+100 ), innerFrameYPos);
            res = res += transSquare(shedWidth, shedLength, innerFrameXPos, innerFrameYPos);
        }System.out.println("-----------------------------------------       shed l  "+c.getShedLength());

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

    private String postsSVG(String res, int width, int length, int innerFrameXPos, int innerFrameYPos, int innerLayerEntranceCornorXPosForPost, int innerLayerEntranceCornorYPosForPost, Carport c) {
        //post
        res += square(POSTWIDTH, POSTWIDTH, innerFrameXPos, innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, innerLayerEntranceCornorXPosForPost, innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, innerFrameXPos, innerLayerEntranceCornorYPosForPost);
        res += square(POSTWIDTH, POSTWIDTH, innerLayerEntranceCornorXPosForPost, innerLayerEntranceCornorYPosForPost);
        //carport with 6 posts
        if (c.getPost() > MINIMUMPOSTS && c.getPost() < 8) {
            res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, innerFrameYPos);
            res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
        }
        //carport with 8 posts
        if (c.getPost() >= MAXPOSTS) {

            res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerFrameYPos);
            res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTHREE + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
            res += square(POSTWIDTH, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos), innerFrameYPos);
            res += square(POSTWIDTH, POSTWIDTH, (int) (width / POSTPOSITIONONEHALF + innerFrameXPos), innerLayerEntranceCornorYPosForPost);

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
        res += text(OUTERFRAMEXPOS, OUTERFRAMEYPOS - TEXTSPACINGOUTERLAYER, outerFrameWidth);
        res += text(innerFrameXPos, OUTERFRAMEYPOS - TEXTSPACINGINNERLAYER, width);
        //rafter text
        res += text(OUTERFRAMEXPOS, OUTERFRAMEYPOS + outerFrameHeight + TEXTBOTTOMLAYER, (int) c.getRafterSpacing());
        //height text
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGINNERLAYER, innerFrameYPos + height, height);
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGOUTERLAYER, OUTERFRAMEXPOS + outerFrameHeight, outerFrameHeight);
        return res;
    }

//    private String background() {
//        String res = "<rect x=\"0\" y=\"0\" height=\"1000\" width=\"1000\"\n"
//                + "              style=\"stroke:#000000; fill: #f9f9f9\"/>";
//        return res;
//    }
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
