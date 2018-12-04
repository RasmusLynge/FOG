/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import DBAccess.DataMapper;
import FunctionLayer.Entity.Carport;
import FunctionLayer.Entity.Material;
import static FunctionLayer.Rule.Rules.*;
import java.util.ArrayList;
/**
 *
 * @author Mathias
 */
public class CoverCalculator {
    DataMapper db = new DataMapper();
    Carport c;

    public CoverCalculator(Carport c) {
        this.c = c;
    }
    public void setShedCover(){
        int width = c.getWidth();
        int shedLength = c.getShedLength();
        
        int amountOfPlanks = 0;
        amountOfPlanks +=calcPlankShedCover(width)*BOTHSIDES/PLANKSFROMONEPIECE;
        amountOfPlanks += calcPlankShedCover(shedLength)*BOTHSIDES/PLANKSFROMONEPIECE;
        c.setPlanks(c.getPlanks()+amountOfPlanks);
    }
    public void setRoofCover(){
        int width = c.getWidth();
        int roofHeight = (int) c.getRoofPostHeight();
        int amountOfPlanks = calcPlankRoofCover(width, roofHeight)*BOTHSIDES;
        c.setCoverPlanks(c.getCoverPlanks()+amountOfPlanks);
    }

    private int calcPlankRoofCover(int width, int height) {
        int result = 0;
        result += PLANKSWITHONEOVERLAP;
        
        int restWidth = width - PLANKSWITHONEOVERLAP * (PLANKWIDTH -PLANKOVERLAP); 
        
        double amount = (double) restWidth / (double)(PLANKWIDTH - (BOTHSIDES * PLANKOVERLAP));
        result += Math.ceil(amount);
        
        //TODO tilføj hvis taget er højere end de brædder de får leveret (480) så find ud ad hvor mange ekstra der skal bruges 
        if (height >0 &&PLANKLENGTH/height >= 2) result /= 2;
        return result;
    }
    private int calcPlankShedCover(int width) {
        
        
        int result = 0;
        result += PLANKSWITHONEOVERLAP;
        
        int restWidth = width - PLANKSWITHONEOVERLAP * (PLANKWIDTH -PLANKOVERLAP); 
        
        double amount = (double) restWidth / (double)(PLANKWIDTH - (BOTHSIDES * PLANKOVERLAP));
        result += Math.ceil(amount);
        
        //TODO tilføj hvis taget er højere end de brædder de får leveret (480) så find ud ad hvor mange ekstra der skal bruges 
        return result;
    }
}
