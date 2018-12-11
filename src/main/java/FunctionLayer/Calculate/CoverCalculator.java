package FunctionLayer.Calculate;

import DBAccess.DataMapper;
import FunctionLayer.Entity.Carport;
import static FunctionLayer.Rule.Rules.*;

public class CoverCalculator {

    DataMapper db = new DataMapper();
    Carport carport;

    /**
     * This method sets the carport that needs to be used for the other calculations in this class
     * @param carport This is the carport that we need to use for the calculations of the covers 
     */
    public CoverCalculator(Carport carport) {
        this.carport = carport;
    }

    /**
     * This method calculate how many planks that should be used for the shed on the carport
     * It uses the carport width and the desired shed length to calculate this
     */
    public void setShedCover() {
        int width = carport.getWidth();
        int shedLength = carport.getShedLength();

        int amountOfPlanks = calcPlankShedCover(width) * BOTHSIDES / PLANKSFROMONEPIECE;
        amountOfPlanks += calcPlankShedCover(shedLength) * BOTHSIDES / PLANKSFROMONEPIECE;
        carport.setPlanks(carport.getPlanks() + amountOfPlanks);
    }

    /**
     * This method calculates the amount of planks that should be used for a carport with "high roof"
     * It uses the width of the carport and the roof post height for this
     */
    public void setRoofCover() {
        int width = carport.getWidth();
        int roofHeight = (int) carport.getRoofPostHeight();
        int amountOfPlanks = calcPlankRoofCover(width, roofHeight) * BOTHSIDES;
        carport.setCoverPlanks(carport.getCoverPlanks() + amountOfPlanks);
    }

    private int calcPlankRoofCover(int width, int height) {
        int result = PLANKSWITHONEOVERLAP;

        int restWidth = width - PLANKSWITHONEOVERLAP * (PLANKWIDTH - PLANKOVERLAP);

        double amount = (double) restWidth / (double) (PLANKWIDTH - (BOTHSIDES * PLANKOVERLAP));
        result += Math.ceil(amount);

        if (height > 0 && PLANKLENGTH / height >= HALFHEIGHT) {
            result /= HALFHEIGHT;
        }
        return result;
    }

    private int calcPlankShedCover(int width) {

        int result = PLANKSWITHONEOVERLAP;

        int restWidth = width - PLANKSWITHONEOVERLAP * (PLANKWIDTH - PLANKOVERLAP);

        double amount = (double) restWidth / (double) (PLANKWIDTH - (BOTHSIDES * PLANKOVERLAP));
        result += Math.ceil(amount);

        return result;
    }
}
