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
     * This method calculates the price of the carport.  
     * It takes the parameters of the caport and calls the calculator to calculate the carport. 
     * It then calls the material calculator to calculate the materials used for the carport just calculated.
     * finally it runs through the carport list of materials to calculate the overall price.
     * @param length this is the length of the carport that is calculated 
     * @param width this is the width of the carport that is calculated 
     * @param roof this is a boolean that shows if the roof should be flat or high 
     * @param shed this is a boolean that shows if the carport should be calculated width or widthout a shed 
     * @return This method returns the overall price for the carport as a boolean
     * @throws DMException it throws this exception if there is any problems with the data mapper method/SQL calling 
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
     * This method returns the carport set in the method PriceCalculator.
     * @return returns the carport set in the method PriceCalculator
     */
    public Carport getCarport() {
        return this.carport;
    }
}
