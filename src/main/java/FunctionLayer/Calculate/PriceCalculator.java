package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import DBAccess.DataMapper;
import FunctionLayer.Entity.Material;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.Exception.MakeOrderException;
import java.util.HashMap;
import static FunctionLayer.Rule.Rules.*;
import java.util.ArrayList;

public class PriceCalculator {

    DataMapper db = new DataMapper();
    CarportCalculator carportcalculator = new CarportCalculator();
    Carport c; 


    private double priceForRoofs(Carport c, HashMap<String, Double> mapPrice) {
        double totalPrice = c.getPlastmoSmall() * mapPrice.get("PlastmoSmall") + c.getPlastmoLong() * mapPrice.get("PlastmoLong");
        totalPrice += (c.getFlatHinges() / CMTOMETER) * mapPrice.get("FlatHinge");
        totalPrice += (c.getRoofBeams() / CMTOMETER) * mapPrice.get("Rafter");
        totalPrice += (c.getRoofPost() / CMTOMETER) * mapPrice.get("Post");
        totalPrice += (c.getRoofRafter() / CMTOMETER) * mapPrice.get("Rafter");
        totalPrice += c.getRoofTiles() * mapPrice.get("Tile");
        totalPrice += c.getCoverPlanks() * mapPrice.get("Plank19x100");
        
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
    public double priceCalculator(int length, int width, boolean roof, boolean shed) throws GeneralException, MakeOrderException{
        double result = 0;
        MaterialCalculator mc = new MaterialCalculator();
        
        Carport c = carportcalculator.calculateAll(length, width, roof, shed);
        c.setList(mc.materialList(c));
        ArrayList<Material> list = c.getList();
        for (int i = 0; i<list.size(); i++){
            System.out.println("price "+ list.get(i).getPrice()+" amount "+ list.get(i).getAmount()+" name " + list.get(i).getName() + " length = "+list.get(i).getLength()+ "\n");
            result += list.get(i).getAmount() * list.get(i).getPrice();
        }
        this.c = c;
        return result;
    }
    public Carport getCarport(){
        return this.c;
    }
}
