package FunctionLayer;

import java.util.HashMap;
import static javafx.beans.binding.Bindings.length;

/**
 *
 * @author Magnus
 */
public class SVGUtilCarportTop {

//    int width = 430;
//    int height = 430;
    private static final int OUTERFRAMEXPOS = 100;
    private static final int OUTERFRAMEYPOS = 100;
    private static final int HANGOUTONESIDE = 35;
    private static final int ENTRANCEHANGOUT = 65;
    private static final int BOTHSIDES = 2;
    private static final int WOODWIDTH = 5;
    private static final int POSTWIDTH = 15;
    private static final int EXTRAPOSTSPACING = 10;
    private static final int MINIMUMPOSTS = 4;
    private static final int MAXPOSTS = 8;
    private static final double POSTPOSITIONONEHALF = 1.5;
    private static final int POSTPOSITIONTWO = 2;
    private static final int POSTPOSITIONTHREE = 3;
    private static final int LINESPACINGOUTERLAYER = 35;
    private static final int LINESPACINGINNERLAYER = 10;
    private static final int TEXTSPACINGOUTERLAYER = 40;
    private static final int TEXTSPACINGINNERLAYER = 15;
    private static final int TEXTBOTTOMLAYER = 30;
    CarportCalculator carportcalculator = new CarportCalculator();
    
    public String printCarportTop(int width, int height) {
        String res = "<SVG width=\"1000\" height=\"1000\">" + caportFromAbove(width, height) + "</SVG>";
        return res;
    }

    public String caportFromAbove(int width, int height) {
        int outerFrameHeight = height + HANGOUTONESIDE * BOTHSIDES;
        int outerFrameWidth = width + HANGOUTONESIDE * BOTHSIDES + ENTRANCEHANGOUT;
        int innerFrameXPos = OUTERFRAMEXPOS + HANGOUTONESIDE;
        int innerFrameYPos = OUTERFRAMEYPOS + HANGOUTONESIDE;
        int innerLayerBottomYPos = innerFrameYPos + height - WOODWIDTH;
        int innerLayerEntranceCornorYPosForPost = innerLayerBottomYPos - EXTRAPOSTSPACING;
        int innerLayerEntranceCornorXPosForPost = innerFrameXPos + width - POSTWIDTH;
        int rafterSpaceing = OUTERFRAMEXPOS;
        HashMap<String, Integer> mapCarport = carportcalculator.calculateAll(width, height);
        
        
        
        String res = framesSVG(width, height, outerFrameHeight, outerFrameWidth, innerFrameXPos, innerFrameYPos);
        res = textSVG(res, width, height, outerFrameWidth, innerFrameXPos, outerFrameHeight, innerFrameYPos, mapCarport);
        res = linesSVG(res, width, height, outerFrameWidth, innerFrameXPos, innerFrameYPos, outerFrameHeight, mapCarport);
        res = beamsSVG(res, outerFrameWidth, innerFrameYPos, innerLayerBottomYPos);
        res = postsSVG(res, width, height, innerFrameXPos, innerFrameYPos, innerLayerEntranceCornorXPosForPost, innerLayerEntranceCornorYPosForPost, mapCarport);
        res = raftersSVG(res, width, height, outerFrameHeight, rafterSpaceing, outerFrameWidth, mapCarport);

        return res;
    }

    private String raftersSVG(String res, int width, int height, int outerFrameHeight, int rafterSpaceing, int outerFrameWidth, HashMap<String, Integer> mapCarport) {
        //rafter
        for (int i = 0; i < mapCarport.get("totalRafters"); i++) {
            res += square(outerFrameHeight, WOODWIDTH, rafterSpaceing, OUTERFRAMEYPOS);
            rafterSpaceing += mapCarport.get("newRafterSpacing");
        }
        res += square(outerFrameHeight, WOODWIDTH, OUTERFRAMEXPOS + outerFrameWidth - WOODWIDTH, OUTERFRAMEYPOS);
        return res;
    }

    private String postsSVG(String res, int width, int height, int innerFrameXPos, int innerFrameYPos, int innerLayerEntranceCornorXPosForPost, int innerLayerEntranceCornorYPosForPost, HashMap<String, Integer> mapCarport) {
        //post
        res += square(POSTWIDTH, POSTWIDTH, innerFrameXPos, innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, innerLayerEntranceCornorXPosForPost, innerFrameYPos);
        res += square(POSTWIDTH, POSTWIDTH, innerFrameXPos, innerLayerEntranceCornorYPosForPost);
        res += square(POSTWIDTH, POSTWIDTH, innerLayerEntranceCornorXPosForPost, innerLayerEntranceCornorYPosForPost);
        //carport with 6 posts
        if (mapCarport.get("totalPosts") > MINIMUMPOSTS && mapCarport.get("totalPosts") < 8) {
            res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, innerFrameYPos);
            res += square(POSTWIDTH, POSTWIDTH, width / POSTPOSITIONTWO + innerFrameXPos, innerLayerEntranceCornorYPosForPost);
        }
        //carport with 8 posts
        if (mapCarport.get("totalPosts") >= MAXPOSTS) {

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

    private String linesSVG(String res, int width, int height, int outerFrameWidth, int innerFrameXPos, int innerFrameYPos, int outerFrameHeight, HashMap<String, Integer> mapCarport) {
        //width lines
        res += line(OUTERFRAMEXPOS, OUTERFRAMEYPOS - LINESPACINGOUTERLAYER, outerFrameWidth + OUTERFRAMEXPOS, OUTERFRAMEYPOS - LINESPACINGOUTERLAYER);
        res += line(innerFrameXPos, OUTERFRAMEYPOS - LINESPACINGINNERLAYER, width + innerFrameXPos, OUTERFRAMEYPOS - LINESPACINGINNERLAYER);
        //height lines
        res += line(OUTERFRAMEXPOS - LINESPACINGINNERLAYER, innerFrameYPos, OUTERFRAMEXPOS - LINESPACINGINNERLAYER, innerFrameYPos + height);
        res += line(OUTERFRAMEXPOS - LINESPACINGOUTERLAYER, OUTERFRAMEYPOS, OUTERFRAMEXPOS - LINESPACINGOUTERLAYER, OUTERFRAMEYPOS + outerFrameHeight);
        //rafter line
        res += line(OUTERFRAMEXPOS, OUTERFRAMEYPOS + outerFrameHeight + LINESPACINGINNERLAYER, mapCarport.get("newRafterSpacing") + OUTERFRAMEXPOS, OUTERFRAMEYPOS + outerFrameHeight + 10);
        return res;
    }

    private String framesSVG(int width, int height, int outerFrameHeight, int outerFrameWidth, int innerFrameXPos, int innerFrameYPos) {
        //outer inner background
        String res = "";
        res += background();
        //outerframe
        res += square(outerFrameHeight, outerFrameWidth, OUTERFRAMEXPOS, OUTERFRAMEYPOS);
        //innerframe
        res += square(height, width, innerFrameXPos, innerFrameYPos);
        return res;
    }

    private String textSVG(String res, int width, int height, int outerFrameWidth, int innerFrameXPos, int outerFrameHeight, int innerFrameYPos, HashMap<String, Integer> mapCarport) {
        //width text
        res += text(OUTERFRAMEXPOS, OUTERFRAMEYPOS - TEXTSPACINGOUTERLAYER, outerFrameWidth);
        res += text(innerFrameXPos, OUTERFRAMEYPOS - TEXTSPACINGINNERLAYER, width);
        //rafter text
        res += text(OUTERFRAMEXPOS, OUTERFRAMEYPOS + outerFrameHeight + TEXTBOTTOMLAYER, mapCarport.get("newRafterSpacing"));
        //height text
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGINNERLAYER, innerFrameYPos + height, height);
        res += textRotated(OUTERFRAMEXPOS - TEXTSPACINGOUTERLAYER, OUTERFRAMEXPOS + outerFrameHeight, outerFrameHeight);
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

