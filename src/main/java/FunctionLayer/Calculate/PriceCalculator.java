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
