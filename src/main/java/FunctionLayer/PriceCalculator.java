package FunctionLayer;

import DBAccess.DataMapper;
import java.util.HashMap;

public class PriceCalculator {

    DataMapper db = new DataMapper();
    CarportCalculator carportcalculator = new CarportCalculator();

    public double priceCalculator(int length, int width) throws GeneralException {
        HashMap<String, Integer> mapCarport = carportcalculator.calculateAll(length, width);
        HashMap<String, Double> mapPrice = db.getPrices();
        
        double totalLengthOfBeams = mapCarport.get("beamLength") * mapCarport.get("totalBeams");
        double totalLengthOfPosts = mapCarport.get("totalPosts") * mapCarport.get("postsLength");
        double totalLengthOfRafters = mapCarport.get("rafterLength") * mapCarport.get("totalRafters");
        double totalLengthOfCover = (mapCarport.get("sideCoverLength") + mapCarport.get("sideCoversWidth"));

        double totalPriceForCarport = totalLengthOfRafters * mapPrice.get("Rafter");
        totalPriceForCarport += totalLengthOfBeams * mapPrice.get("Beam");
        totalPriceForCarport += totalLengthOfPosts * mapPrice.get("Post");
        totalPriceForCarport += totalLengthOfCover * mapPrice.get("Cover");
        totalPriceForCarport += mapCarport.get("totalLHinges") * mapPrice.get("Hinge");
        totalPriceForCarport += mapCarport.get("totalScrews") * mapPrice.get("Screws");
        return totalPriceForCarport;
    }
}
