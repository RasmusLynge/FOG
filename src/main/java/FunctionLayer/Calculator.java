/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import java.util.HashMap;

/**
 *
 * @author Mathias
 */
public class Calculator {

    private static final int MINLENGTH = 240; // minimum length for a carport
    private static final int MINWIDTH = 240;
    private static final int MAXLENGTH = 780;
    private static final int MAXWIDTH = 750;
    private static final int NEWPOSTLENGTH = 200; //how many centimeters to make a new post necessary
    private static final int TWOPOSTLENGTH = 200; //subtracted from side length because corner posts are shared
    private static final int MINPOSTS = 4; // minimum amounts of posts in a carport
    
    public HashMap<String, Integer> calculateAll(int length, int width){
        HashMap<String, Integer> totalMap = new HashMap<>();
        HashMap<String, Integer> mapposts = calculatePosts(length, width);
//        HashMap<String, Integer> mapPlanks = calculatePlanks(length, width);
        
        
        totalMap.putAll(mapposts);
        return totalMap;
    }
    
    public HashMap<String, Integer> calculatePosts(int length, int width) {
        HashMap<String, Integer> map = new HashMap<>();
        int restLength = length - TWOPOSTLENGTH;
        int restWidth = width - TWOPOSTLENGTH;

        int widthPosts = calcWidthPosts(restWidth);
        int lengthPosts = calcLengthPosts(restLength);
        int totalPosts = MINPOSTS + 2 * lengthPosts + widthPosts;
        
        map.put("totalPosts", totalPosts);
        return map;
        
    }

    private int calcWidthPosts(int restWidth) {
        int amount = restWidth / NEWPOSTLENGTH;
        return amount;

    }
    private int calcLengthPosts(int restLength) {
        int amount = restLength / NEWPOSTLENGTH;
        return amount;

    }

}
