
package FunctionLayer;

import DBAccess.DataMapper;
import java.util.HashMap;

public class PriceCalculator {

    DataMapper db = new DataMapper();
    CarportCalculator carportcalculator = new CarportCalculator();
    RoofCalculator roofCalculator = new RoofCalculator();
    final private static int CMTOMETER = 100;

    public double priceCalculator(int length, int width) throws GeneralException {
        HashMap<String, Integer> mapCarport = carportcalculator.calculateAll(length, width);
        HashMap<String, Integer> roofCalculator = this.roofCalculator.roofs(length, width);
        HashMap<String, Double> mapPrice = db.getPrices();

        double totalLengthOfBeams = (mapCarport.get("beamLength") * mapCarport.get("totalBeams")) / CMTOMETER;
        double totalLengthOfPosts = (mapCarport.get("totalPosts") * mapCarport.get("postsLength")) / CMTOMETER;
        double totalLengthOfRafters = (mapCarport.get("rafterLength") * mapCarport.get("totalRafters")) / CMTOMETER;
        double totalLengthOfCover = (mapCarport.get("sideCoverLength") + mapCarport.get("sideCoversWidth")) / CMTOMETER;

        double totalPrice = priceForCarportSekelton(totalLengthOfRafters, mapPrice, totalLengthOfBeams, totalLengthOfPosts, totalLengthOfCover, mapCarport);
        //totalPrice = priceForRoof();
        
        return totalPrice;
    }

    private double priceForCarportSekelton(double totalLengthOfRafters, HashMap<String, Double> mapPrice, double totalLengthOfBeams, double totalLengthOfPosts, double totalLengthOfCover, HashMap<String, Integer> mapCarport) {
        double totalPriceForCarportSkeleton = totalLengthOfRafters * mapPrice.get("Rafter");
        totalPriceForCarportSkeleton += totalLengthOfBeams * mapPrice.get("Beam");
        totalPriceForCarportSkeleton += totalLengthOfPosts * mapPrice.get("Post");
        totalPriceForCarportSkeleton += totalLengthOfCover * mapPrice.get("Cover");
        totalPriceForCarportSkeleton += mapCarport.get("totalLHinges") * mapPrice.get("Hinge");
        totalPriceForCarportSkeleton += mapCarport.get("totalScrews") * mapPrice.get("Screws (200)");
        return totalPriceForCarportSkeleton;
    }
}
