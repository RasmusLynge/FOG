package FunctionLayer.Calculate;

import DBAccess.DataMapper;
import FunctionLayer.Entity.Carport;
import static FunctionLayer.Rule.Rules.*;

public class CoverCalculator {

    DataMapper db = new DataMapper();
    Carport carport;

    /**
     *
     * @param carport
     */
    public CoverCalculator(Carport carport) {
        this.carport = carport;
    }

    /**
     *
     */
    public void setShedCover() {
        int width = carport.getWidth();
        int shedLength = carport.getShedLength();

        int amountOfPlanks = calcPlankShedCover(width) * BOTHSIDES / PLANKSFROMONEPIECE;
        amountOfPlanks += calcPlankShedCover(shedLength) * BOTHSIDES / PLANKSFROMONEPIECE;
        carport.setPlanks(carport.getPlanks() + amountOfPlanks);
    }

    /**
     *
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
