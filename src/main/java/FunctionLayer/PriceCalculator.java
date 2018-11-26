
package FunctionLayer;

import DBAccess.DataMapper;
import java.util.HashMap;

public class PriceCalculator {

    DataMapper db = new DataMapper();
    CarportCalculator carportcalculator = new CarportCalculator();
    RoofCalculator roofCalculator = new RoofCalculator();
    final private static int CMTOMETER = 100;

    public double priceCalculator(int length, int width) throws GeneralException {
        Carport c = carportcalculator.calculateAll(length, width);
        HashMap<String, Double> mapPrice = db.getPrices();

        double totalLengthOfBeams = c.getBeam()*c.getBeamLength() / CMTOMETER;
        double totalLengthOfPosts = c.getPost()*c.getPostLength() / CMTOMETER;
        double totalLengthOfRafters = c.getRafter() * c.getRafterLength() / CMTOMETER;
        double totalLHinges = c.getHinges();
        double totalScrewBoxes = c.getScrewBoxes();

        double totalPrice = priceForCarportSekelton(totalLengthOfRafters, mapPrice, totalLengthOfBeams, totalLengthOfPosts, totalLHinges, totalScrewBoxes);
        return totalPrice;
    }

    private double priceForCarportSekelton(double totalLengthOfRafters, HashMap<String, Double> mapPrice, double totalLengthOfBeams, double totalLengthOfPosts, double totalLHinges, double totalScrewBoxes) {
        double totalPriceForCarportSkeleton = totalLengthOfRafters * mapPrice.get("Rafter");
        totalPriceForCarportSkeleton += totalLengthOfBeams * mapPrice.get("Beam");
        totalPriceForCarportSkeleton += totalLengthOfPosts * mapPrice.get("Post");
        totalPriceForCarportSkeleton += totalLHinges * mapPrice.get("Hinge");
        totalPriceForCarportSkeleton += totalScrewBoxes* mapPrice.get("Screws (200)");
        return totalPriceForCarportSkeleton;
    }
}
