package FunctionLayer;

/**
 *
 * @author Magnus
 */
public class Carport {

    int width, length, rafter,
    beam, post, postrafterLength, beamLength,
    postLength, screws, screwBoxes, hinges;
    
    double rafterSpacing;
    boolean roof, shed;
    
    public Carport(int width, int length, boolean roof, boolean shed) {
        this.width = width;
        this.length = length;
        this.roof = roof;
        this.shed = shed;
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

    public int getPostrafterLength() {
        return postrafterLength;
    }

    public void setRafterLength(int postrafterLength) {
        this.postrafterLength = postrafterLength;
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

    public int getHinges() {
        return hinges;
    }

    public void setHinges(int hinges) {
        this.hinges = hinges;
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
