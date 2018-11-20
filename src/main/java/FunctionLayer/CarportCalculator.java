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
    private static final int ROOFHANGOUTTWOSIDES = 70; // amount of centimeters the roof "hangs" out over the width and length of the carport.
    private static final int ENTRANCEHANGOUT = 65; // the hang out at the entrance
    private static final int RAFTERSPACING = 60; // spacing between the middle of each rafter in cm. 
    private static final int COVERWIDTH = 2; // the covers around the rafters
    private static final int HINGESPERPOST = 2; // the total amount of hinges each posts
    private static final int HINGESPRRAFTER = 4; // the total amount of hinges for each rafter
    private static final int SCREWSPERLHINGES = 4; // thetotal amount of screws each L formed hinge
    private static final int SCREWSPERCOVER = 4; // the total amount of screw each cover
    private static final int BOTHSIDES = 2; // for each side of the carport
    private static final int TOTALCOVERS = 4; // the total amount of covers needed for the carport
    private static final int BEAMS = 2; // total amount of beams in the scrukture
    private static final int POSTSLENGTH = 300; // posts length
    private static final int SCREWSINABOX = 200; // screws in a box

    public HashMap<String, Integer> calculateAll(int length, int width) {
        HashMap<String, Integer> totalMap = new HashMap<>();
        HashMap<String, Integer> mapPosts = calculatePosts(length, width);
        HashMap<String, Integer> mapRafters = calculateRafters(length, width);
        HashMap<String, Integer> mapBeams = beamLengthCalculator(length);
        HashMap<String, Integer> mapCovers = sideCovers(length, width);

        totalMap.put("length", length);
        totalMap.put("width", width);
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
        map.put("postsLength", POSTSLENGTH);
        map.put("totalPosts", totalPosts);
        return map;

    }

    public HashMap<String, Integer> beamLengthCalculator(int length) {
        HashMap<String, Integer> map = new HashMap<>();
        int toalBeamLength = length + ENTRANCEHANGOUT + ROOFHANGOUTTWOSIDES;
        map.put("beamLength", toalBeamLength);
        map.put("totalBeams", BEAMS);
        return map;
    }

    public HashMap<String, Integer> calculateRafters(int length, int width) {
        HashMap<String, Integer> map = new HashMap<>();
        System.out.println("length " + length);
        int roofLength = length + ROOFHANGOUTTWOSIDES + ENTRANCEHANGOUT;
        System.out.println("roofLength " + roofLength);
        int rafterLength = width + ROOFHANGOUTTWOSIDES;

        double totalRaftersd = (roofLength / RAFTERSPACING)+1;
        System.out.println("DoubleRafters++++++++++++"+ totalRaftersd);
        int totalRafters = (int) totalRaftersd;
        System.out.println("totalrafterss-------------" + totalRafters);
        int newRafterSpacing = roofLength / totalRafters;
        
        map.put("rafterLength", rafterLength);
        map.put("totalRafters", totalRafters);
        map.put("newRafterSpacing", newRafterSpacing);
        return map;
    }

    public HashMap<String, Integer> sideCovers(int length, int width) {
        HashMap<String, Integer> map = new HashMap<>();
        int sideCoverLength = length + ENTRANCEHANGOUT + ROOFHANGOUTTWOSIDES;
        int sideCoverWidth = width + ROOFHANGOUTTWOSIDES + COVERWIDTH * BOTHSIDES;

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
        double totalScrews = hinges * SCREWSPERLHINGES;
        totalScrews += TOTALCOVERS * SCREWSPERCOVER;
        double boxesOfScrews = totalScrews / SCREWSINABOX;
        double boxesRoundedUp = Math.ceil(boxesOfScrews);
        int boxes = (int) boxesRoundedUp;
        return boxes;
    }

}
