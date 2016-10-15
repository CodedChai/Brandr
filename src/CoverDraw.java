import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;

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

    public CoverDraw(ArrayList<Color> palette, int width, int height, String name)
    {
        namePartition = height / 4;
        this.width = width;
        this.height = height - namePartition;
        this.palette = palette;
        this.name = name;
    }

    public void DrawCover(GLAutoDrawable glAutoDrawable, TextRenderer renderer)
    {
        double[] focalPoint = FindFocalPoint();
        DrawName(glAutoDrawable, renderer);
    }

    private double[] FindFocalPoint()
    {
        return  new double[] {(randy.nextInt(2) + 1) * height/3,(randy.nextInt(2) + 1) * width/3};
    }

    public void DrawName(GLAutoDrawable glAutoDrawable, TextRenderer renderer)
    {
        GL2 gl=glAutoDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        //Set a color (redish - no other components)
        gl.glColor3f(0.3f,0.0f,0.0f);
        //Define a primitive -  A polygon in this case
        gl.glBegin(GL2.GL_POLYGON);
        {
            gl.glVertex2d(0, height);
            gl.glVertex2d(width, height);
            gl.glVertex2d(width, height + namePartition);
            gl.glVertex2d(0, height + namePartition);
        }
        gl.glEnd();

        renderer.beginRendering(width, height + namePartition);
        {
            renderer.setColor(Color.cyan);
            renderer.draw(name, 0, height);
        }
        renderer.endRendering();


    }


}
