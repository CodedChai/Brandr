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

    public void DrawCover(GLAutoDrawable glAutoDrawable, TextRenderer renderer, GL2 gl)
    {
        int[] focalPoint = FindFocalPoint();
        DrawArea(focalPoint, glAutoDrawable, gl);
        DrawName(glAutoDrawable, renderer, gl);

    }

    private int[] FindFocalPoint()
    {
        return  new int[] {(randy.nextInt(2) + 1) * height / 3,(randy.nextInt(2) + 1) * width / 3};
    }

    public void DrawName(GLAutoDrawable glAutoDrawable, TextRenderer renderer, GL2 gl)
    {


        DrawRectangle(0,height, width, namePartition, palette.get(0), glAutoDrawable, gl);


        //draw text
        renderer.beginRendering(width, height + namePartition);
        {
            renderer.setColor(palette.get(1));
            renderer.draw(name, 0, height + namePartition / 2);
        }
        renderer.endRendering();
    }

    public void DrawArea(int[] focalPoint, GLAutoDrawable glAutoDrawable, GL2 gl)
    {
        for(int x = 2; (x < palette.size()) ; x++)
        {
            DrawRectangle(randy.nextInt(50 * x) + focalPoint[0], randy.nextInt(50 * x) + focalPoint[1],
                    randy.nextInt(500 * (palette.size() - x % palette.size())), randy.nextInt(500 * (palette.size() - x% palette.size())),
                    palette.get(x % palette.size()), glAutoDrawable, gl);
        }
    }

    private void DrawRectangle(int x, int y, int width, int height, Color color, GLAutoDrawable glAutoDrawable, GL2 gl)
    {
        if(x < 0)
        {
            x = 0;
        }
        if(y < 0)
        {
            y = 0;
        }
        //Set a color (redish - no other components)

        gl.glColor3ui(color.getRed(), color.getGreen(), color.getBlue());
        //Define a primitive -  A polygon in this case
        System.out.println("ok");
        //draw bounding polygon.
        gl.glBegin(GL2.GL_POLYGON);
        {
            gl.glVertex2d(x, y);
            gl.glVertex2d(x + width, y);
            gl.glVertex2d(x + width, y + height);
            gl.glVertex2d(x, height + y);
        }
        gl.glEnd();


    }

}
