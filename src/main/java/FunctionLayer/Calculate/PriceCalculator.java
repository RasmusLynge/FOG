package FunctionLayer.Calculate;

import FunctionLayer.Calculate.CarportCalculator;
import FunctionLayer.Entity.Carport;
import DBAccess.DataMapper;
import FunctionLayer.Exception.GeneralException;
import java.util.HashMap;
import static FunctionLayer.Rule.Rules.*;

public class PriceCalculator {

    DataMapper db = new DataMapper();
    CarportCalculator carportcalculator = new CarportCalculator();

    public double priceCalculator(int length, int width, boolean roof, boolean shed) throws GeneralException {
        Carport c = carportcalculator.calculateAll(length, width, false, false);
        HashMap<String, Double> mapPrice = db.getPrices();
        System.out.println("keys .. ++++" + mapPrice.keySet());

        double totalLengthOfBeams = c.getBeam() * c.getBeamLength() / CMTOMETER;
        double totalLengthOfPosts = c.getPost() * c.getPostLength() / CMTOMETER;
        double totalLengthOfRafters = c.getRafter() * c.getRafterLength() / CMTOMETER;
        double totalLHinges = c.getLHinges();
        double totalScrewBoxes = c.getScrewBoxes();

        double totalPrice = priceForRoofs(c, mapPrice);
        totalPrice += priceForCarportSkeleton(totalLengthOfRafters, mapPrice, totalLengthOfBeams, totalLengthOfPosts, totalLHinges, totalScrewBoxes);
        return totalPrice;
    }

    private double priceForRoofs(Carport c, HashMap<String, Double> mapPrice) {
        double totalPrice = c.getPlastmoSmall() * mapPrice.get("PlastmoSmall") + c.getPlastmoLong() * mapPrice.get("PlastmoLong");
        totalPrice += (c.getFlatHinges() / CMTOMETER) * mapPrice.get("FlatHinge");
        System.out.println("Plastmo pris " + c.getPlastmoSmall() * mapPrice.get("PlastmoSmall") + c.getPlastmoLong() * mapPrice.get("PlastmoLong"));
        totalPrice += (c.getRoofBeams() / CMTOMETER) * mapPrice.get("Rafter");
        totalPrice += (c.getRoofPost() / CMTOMETER) * mapPrice.get("Post");
        totalPrice += (c.getRoofRafter() / CMTOMETER) * mapPrice.get("Rafter");
        totalPrice += c.getRoofTiles() * mapPrice.get("Tile");
        totalPrice += c.getCoverPlanks() * mapPrice.get("Plank19x100");
        
        System.out.println("price for plast small +++++++++" + mapPrice.get("PlastmoSmall"));
        System.out.println("price for plast long +++++++++" + mapPrice.get("PlastmoLong"));
        System.out.println("number of plast long +++++++++" + c.getPlastmoLong());
        System.out.println("number of plast small +++++++++" + c.getPlastmoSmall());
        return totalPrice;
    }

    private double priceForCarportSkeleton(double totalLengthOfRafters, HashMap<String, Double> mapPrice, double totalLengthOfBeams, double totalLengthOfPosts, double totalLHinges, double totalScrewBoxes) {
        double totalPriceForCarportSkeleton = totalLengthOfRafters * mapPrice.get("Rafter");
        totalPriceForCarportSkeleton += totalLengthOfBeams * mapPrice.get("Beam");
        totalPriceForCarportSkeleton += totalLengthOfPosts * mapPrice.get("Post");
        totalPriceForCarportSkeleton += totalLHinges * mapPrice.get("Hinge");
        totalPriceForCarportSkeleton += totalScrewBoxes * mapPrice.get("Screws (200)");
        return totalPriceForCarportSkeleton;
    }
}
