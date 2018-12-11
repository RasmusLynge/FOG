package FunctionLayer.Calculate;

import FunctionLayer.Entity.Carport;
import FunctionLayer.Entity.Material;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import java.util.ArrayList;

public class PriceCalculator {

    CarportCalculator carportcalculator = new CarportCalculator();
    Carport carport;

    /**
     *
     * @param length
     * @param width
     * @param roof
     * @param shed
     * @return
     * @throws DMException
     * @throws MakeOrderException
     */
    public double priceCalculator(int length, int width, boolean roof, boolean shed) throws DMException, MakeOrderException {
        double result = 0;
        MaterialCalculator mc = new MaterialCalculator();

        Carport carport = carportcalculator.calculateAll(length, width, roof, shed);
        carport.setList(mc.materialList(carport));
        ArrayList<Material> list = carport.getList();
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i).getAmount() * list.get(i).getPrice();
        }
        this.carport = carport;
        return result;
    }

    /**
     *
     * @return
     */
    public Carport getCarport() {
        return this.carport;
    }
}
