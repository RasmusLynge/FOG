/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculate;

import DBAccess.DataMapper;
import FunctionLayer.Entity.Carport;
import static FunctionLayer.Rule.Rules.*;
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
    public void setRoofCover(){
        int width = c.getWidth();
        int roofHeight = (int) c.getRoofPostHeight();
        int amountOfPlanks = calcPlankRoofCover(width, roofHeight);
        c.setCoverPlanks(amountOfPlanks);
    }

    private int calcPlankRoofCover(int width, int height) {
        int result = 0;
        result += PLANKSWITHONEOVERLAP;
        
        int restWidth = width - PLANKSWITHONEOVERLAP * (PLANKWIDTH -PLANKOVERLAP); 
        
        double amount = (double) restWidth / (double)(PLANKWIDTH - (BOTHSIDES * PLANKOVERLAP));
        result += Math.ceil(amount);
        
        //TODO tilføj hvis taget er højere end de brædder de får leveret (480) så find ud ad hvor mange ekstra der skal bruges 
        return result;
    }
    private int calcPlankShedCover(int width, int height) {
        int result = 0;
        result += PLANKSWITHONEOVERLAP;
        
        int restWidth = width - PLANKSWITHONEOVERLAP * (PLANKWIDTH -PLANKOVERLAP); 
        
        double amount = (double) restWidth / (double)(PLANKWIDTH - (BOTHSIDES * PLANKOVERLAP));
        result += Math.ceil(amount);
        
        //TODO tilføj hvis taget er højere end de brædder de får leveret (480) så find ud ad hvor mange ekstra der skal bruges 
        return result;
    }
}
