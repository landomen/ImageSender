package si.lanisnik.imagesender.presentation.base.model;

/**
 * Created by Domen Lanišnik on 05/12/2017.
 * domen.lanisnik@gmail.com
 */
public class ScreenInformation {
    private int widthPx;
    private int heightPx;
    private float widthDp;
    private float heightDp;
    private double sizeInches;
    private float density;

    public int getWidthPx() {
        return widthPx;
    }

    public void setWidthPx(int widthPx) {
        this.widthPx = widthPx;
    }

    public int getHeightPx() {
        return heightPx;
    }

    public void setHeightPx(int heightPx) {
        this.heightPx = heightPx;
    }

    public float getWidthDp() {
        return widthDp;
    }

    public void setWidthDp(float widthDp) {
        this.widthDp = widthDp;
    }

    public float getHeightDp() {
        return heightDp;
    }

    public void setHeightDp(float heightDp) {
        this.heightDp = heightDp;
    }

    public double getSizeInches() {
        return sizeInches;
    }

    public void setSizeInches(double sizeInches) {
        this.sizeInches = sizeInches;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }
}
