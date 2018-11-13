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
public class CarportCalculator {

//    private static final int MINLENGTH = 240;
//    private static final int MINWIDTH = 240;
//    private static final int MAXLENGTH = 780;
//    private static final int MAXWIDTH = 750;
    private static final int NEWPOSTLENGTH = 200; //how many centimeters to make a new post necessary
    private static final int TWOPOSTLENGTH = 200; //subtracted from side length because corner posts are shared
    private static final int MINPOSTS = 4; // minimum amounts of posts in a carport
    private static final int ROOFHANGOUT = 70; // amount of centimeters the roof "hangs" out over the width and length of the carport.
    private static final int ENTRANCEHANGOUT = 50; // the hang out at the entrance
    private static final int RAFTERSPACING = 60; // spacing between the middle of each rafter in cm. 
    private static final int COVERWIDTH = 2; // the covers around the rafters
    private static final int HINGESPERPOST = 2; // the total amount of hinges each posts
    private static final int HINGESPRRAFTER = 4; // the total amount of hinges for each rafter
    private static final int SCREWSPERLHINGES = 4; // thetotal amount of screws each L formed hinge
    private static final int SCREWSPERCOVER = 4; // the total amount of screw each cover
    private static final int BOTHSIDES = 2; // for each side of the carport
    private static final int TOTALCOVERS = 4; // the total amount of covers needed for the carport

    public HashMap<String, Integer> calculateAll(int length, int width) {
        HashMap<String, Integer> totalMap = new HashMap<>();
        HashMap<String, Integer> mapPosts = calculatePosts(length, width);
        HashMap<String, Integer> mapRafters = calculateRafters(length, width);
        HashMap<String, Integer> mapBeams = beamLengthCalculator(length);
        HashMap<String, Integer> mapCovers = sideCovers(length, width);

        totalMap.putAll(mapCovers);
        totalMap.putAll(mapBeams);
        totalMap.putAll(mapRafters);
        totalMap.putAll(mapPosts);
        int lHinges = totalLHinges(totalMap.get("totalRafters"), totalMap.get("totalPosts"));
        int screws = totalScrews(lHinges);
        totalMap.put("totalLHinges", lHinges);
        totalMap.put("totalScrews", screws);
        return totalMap;
    }

    public HashMap<String, Integer> calculatePosts(int length, int width) {
        HashMap<String, Integer> map = new HashMap<>();
        int restLength = length - TWOPOSTLENGTH;
        int restWidth = width - TWOPOSTLENGTH;

        int widthPosts = calcWidthPosts(restWidth);
        int lengthPosts = calcLengthPosts(restLength);
        //widthposts til skur
        int totalPosts = MINPOSTS + BOTHSIDES * lengthPosts + widthPosts;

        map.put("totalPosts", totalPosts);
        return map;

    }

    public HashMap<String, Integer> beamLengthCalculator(int length) {
        HashMap<String, Integer> map = new HashMap<>();
        int toalBeamLength = length + ENTRANCEHANGOUT + ROOFHANGOUT;
        map.put("beamLength", toalBeamLength);
        return map;
    }

    public HashMap<String, Integer> calculateRafters(int length, int width) {
        HashMap<String, Integer> map = new HashMap<>();
        int roofLength = length + ROOFHANGOUT + ENTRANCEHANGOUT;
        int rafterLength = width + ROOFHANGOUT;

        int totalRafters = roofLength / RAFTERSPACING;
        int newRafterSpacing = roofLength / totalRafters;

        map.put("rafterLength", rafterLength);
        map.put("totalRafters", totalRafters);
        map.put("newRafterSpacing", newRafterSpacing);
        return map;
    }

    public HashMap<String, Integer> sideCovers(int length, int width) {
        HashMap<String, Integer> map = new HashMap<>();
        int sideCoverLength = length + ENTRANCEHANGOUT + ROOFHANGOUT;
        int sideCoverWidth = width + ROOFHANGOUT + COVERWIDTH * BOTHSIDES;

        map.put("sideCoversWidth", sideCoverWidth);
        map.put("sideCoverLength", sideCoverLength);
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

    private int totalLHinges(int rafters, int posts) {
        int totalLHinges = rafters * HINGESPRRAFTER;
        totalLHinges += posts * HINGESPERPOST;
        return totalLHinges;
    }

    private int totalScrews(int hinges) {
        int totalScrews = hinges * SCREWSPERLHINGES;
        totalScrews += TOTALCOVERS * SCREWSPERCOVER;
        return totalScrews;
    }

}
