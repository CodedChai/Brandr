import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alvarpq on 10/15/2016.
 */
public class CoverDraw
{
    String name;
    ArrayList<Color> palette;

    Random randy = new Random();

    int height;
    int width;
    int namePartition;

    public CoverDraw(ArrayList<Color> palette, int height, int width, String name)
    {
        namePartition = height / 4;
        this.width = width;
        this.height = height - namePartition;
        this.palette = palette;
        this.name = name;
    }

    public void DrawCover()
    {
        double[] focalPoint = FindFocalPoint();
    }

    private double[] FindFocalPoint()
    {
        return  new double[] {(randy.nextInt(2) + 1) * height/3,(randy.nextInt(2) + 1) * width/3};
    }

    public void DrawName(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl=glAutoDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //Set a color (redish - no other components)
        gl.glColor3f(0.3f,0.0f,0.0f);
        //Define a primitive -  A polygon in this case
        gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2i( 100, 20);
            gl.glVertex2i( 100,460);
            gl.glVertex2i(540,460);
            gl.glVertex2i(540, 20);
        gl.glEnd();

    }


}
