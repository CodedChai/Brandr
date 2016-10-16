package brander;

import com.jogamp.opengl.GL2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

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
        colors[1] = palette.get(0).getGreen();
        colors[2] = palette.get(0).getBlue();

        return colors;
    }

    public void drawGradient(GL2 gl) {
        /*gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();*/
        gl.glBegin(GL2.GL_POLYGON);

        Random randy = new Random();


        gl.glColor3f(randy.nextFloat(), randy.nextFloat(), randy.nextFloat());
        gl.glVertex2f((float)0, (float)480.0);
        gl.glVertex2f((float)0,(float)0);
        gl.glColor3f(randy.nextFloat(), randy.nextFloat(), randy.nextFloat());
        gl.glVertex2f((float)640.0,(float)0);
        gl.glVertex2f((float)640.0, (float)480.0);
        gl.glEnd();
    }
    
}
