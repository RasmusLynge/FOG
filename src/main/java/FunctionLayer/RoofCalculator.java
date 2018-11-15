package FunctionLayer;

import DBAccess.DataMapper;
import java.util.HashMap;

public class RoofCalculator {

    DataMapper db = new DataMapper();
    CarportCalculator carportcalculator = new CarportCalculator();

    public HashMap<String, Integer> roofs(int length, int width) {
        HashMap<String, Integer> roofMap = new HashMap<>();
        HashMap<String, Integer> mapFlatRoof = flatRoofCalculator(length, width);
        
        roofMap.putAll(mapFlatRoof);
        return roofMap;
    }

    public HashMap<String, Integer> flatRoofCalculator(int length, int width) {
        //Missing screws calculated
        HashMap<String, Integer> mapCarport = carportcalculator.calculateAll(length, width);
        HashMap<String, Integer> map = new HashMap();
        int roofSquareInMeters = (int) mapCarport.get("sideCoversWidth") * mapCarport.get("sideCoverLength");
        map.put("roofSquareInMeters", roofSquareInMeters);
        return map;
    }
}
