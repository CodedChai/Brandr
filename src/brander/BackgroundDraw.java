package brander;

import java.awt.*;
import java.util.ArrayList;

public class BackgroundDraw {

    ArrayList<Color> palette;

    int width;
    int height;

    public BackgroundDraw(ArrayList<Color> palette, int width, int height) {
        this.palette = palette;
        this.width = width;
        this.height = height;
    }

    public float[] getClearColor() {
        float[] colors = new float[4];

        colors[3] = 1;
        colors[0] = palette.get(0).getRed();
        colors[1] = palette.get(1).getGreen();
        colors[2] = palette.get(2).getBlue();

        return colors;
    }
    
}
