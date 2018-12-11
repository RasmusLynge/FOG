package FunctionLayer.Entity;

import java.util.ArrayList;

public class Carport {

    int width, length, rafter, roofRafter, roofPost,
            beam, post, rafterLength, beamLength,
            postLength, screws, screwBoxes, lHinges,
            outerWidth, outerLength, roofBeams,
            flatHinges, roofTiles, plastmoSmall, plastmoLong,
            coverPlanks, coverPlankLength, shedLength, planks,
            coverStabilizerPlanksLong, coverStabilizerPlankSmall,
            doorHinge, doorKnob, shedPost, postSpacing;
    double rafterSpacing, roofRafterLength, roofPostHeight;
    boolean roof, shed;

    public Carport(int width, int length, boolean roof, boolean shed) {
        this.width = width;
        this.length = length;
        this.roof = roof;
        this.shed = shed;
    }

    public int getPostSpacing() {
        return postSpacing;
    }

    public void setPostSpacing(int postSpacing) {
        this.postSpacing = postSpacing;
    }

    public int getShedPost() {
        return shedPost;
    }

    public void setShedPost(int shedPost) {
        this.shedPost = shedPost;
    }

    public int getDoorHinge() {
        return doorHinge;
    }

    public void setDoorHinge(int doorHinge) {
        this.doorHinge = doorHinge;
    }

    public int getDoorKnob() {
        return doorKnob;
    }

    public void setDoorKnob(int doorKnob) {
        this.doorKnob = doorKnob;
    }

    public int getCoverStabilizerPlanksLong() {
        return coverStabilizerPlanksLong;
    }

    public void setCoverStabilizerPlanksLong(int coverStabilizerPlanksLong) {
        this.coverStabilizerPlanksLong = coverStabilizerPlanksLong;
    }

    public int getCoverStabilizerPlankSmall() {
        return coverStabilizerPlankSmall;
    }

    public void setCoverStabilizerPlankSmall(int coverStabilizerPlankSmall) {
        this.coverStabilizerPlankSmall = coverStabilizerPlankSmall;
    }
    ArrayList<Material> list;

    public int getPlanks() {
        return planks;
    }

    public void setPlanks(int planks) {
        this.planks = planks;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public int getlHinges() {
        return lHinges;
    }

    public void setlHinges(int lHinges) {
        this.lHinges = lHinges;
    }

    public ArrayList<Material> getList() {
        return list;
    }

    public void setList(ArrayList<Material> list) {
        this.list = list;
    }

    public int getCoverPlanks() {
        return coverPlanks;
    }

    public void setCoverPlanks(int coverPlanks) {
        this.coverPlanks = coverPlanks;
    }

    public int getCoverPlankLength() {
        return coverPlankLength;
    }

    public void setCoverPlankLength(int coverPlankLength) {
        this.coverPlankLength = coverPlankLength;
    }

    public int getPlastmoSmall() {
        return plastmoSmall;
    }

    public void setPlastmoSmall(int plastmoSmall) {
        this.plastmoSmall = plastmoSmall;
    }

    public int getPlastmoLong() {
        return plastmoLong;
    }

    public void setPlastmoLong(int plastmoLong) {
        this.plastmoLong = plastmoLong;
    }

    public int getRoofTiles() {
        return roofTiles;
    }

    public void setRoofTiles(int roofTiles) {
        this.roofTiles = roofTiles;
    }

    public int getFlatHinges() {
        return flatHinges;
    }

    public void setFlatHinges(int flatHinges) {
        this.flatHinges = flatHinges;
    }

    public int getRoofBeams() {
        return roofBeams;
    }

    public void setRoofBeams(int roofBeams) {
        this.roofBeams = roofBeams;
    }

    public int getRoofRafter() {
        return roofRafter;
    }

    public void setRoofRafter(int roofRafter) {
        this.roofRafter = roofRafter;
    }

    public int getRoofPost() {
        return roofPost;
    }

    public void setRoofPost(int roofPost) {
        this.roofPost = roofPost;
    }

    public double getRoofRafterLength() {
        return roofRafterLength;
    }

    public void setRoofRafterLength(double roofRafterLength) {
        this.roofRafterLength = roofRafterLength;
    }

    public double getRoofPostHeight() {
        return roofPostHeight;
    }

    public void setRoofPostHeight(double roofPostHeight) {
        this.roofPostHeight = roofPostHeight;
    }

    public int getOuterWidth() {
        return outerWidth;
    }

    public void setOuterWidth(int outerWidth) {
        this.outerWidth = outerWidth;
    }

    public int getOuterLength() {
        return outerLength;
    }

    public void setOuterLength(int outerLength) {
        this.outerLength = outerLength;
    }

    public int getScrewBoxes() {
        return screwBoxes;
    }

    public void setScrewBoxes(int screwBoxes) {
        this.screwBoxes = screwBoxes;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRafter() {
        return rafter;
    }

    public void setRafter(int rafter) {
        this.rafter = rafter;
    }

    public int getBeam() {
        return beam;
    }

    public void setBeam(int beam) {
        this.beam = beam;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public int getRafterLength() {
        return rafterLength;
    }

    public void setRafterLength(int postrafterLength) {
        this.rafterLength = postrafterLength;
    }

    public int getBeamLength() {
        return beamLength;
    }

    public void setBeamLength(int beamLength) {
        this.beamLength = beamLength;
    }

    public int getPostLength() {
        return postLength;
    }

    public void setPostLength(int postLength) {
        this.postLength = postLength;
    }

    public int getScrews() {
        return screws;
    }

    public void setScrews(int screws) {
        this.screws = screws;
    }

    public int getLHinges() {
        return lHinges;
    }

    public void setLHinges(int lHinges) {
        this.lHinges = lHinges;
    }

    public double getRafterSpacing() {
        return rafterSpacing;
    }

    public void setRafterSpacing(double rafterSpacing) {
        this.rafterSpacing = rafterSpacing;
    }

    public boolean isRoof() {
        return roof;
    }

    public void setRoof(boolean roof) {
        this.roof = roof;
    }

    public boolean isShed() {
        return shed;
    }

    public void setShed(boolean shed) {
        this.shed = shed;
    }

}
