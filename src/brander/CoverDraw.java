package brander;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by alvarpq on 10/15/2016.
 */
public class CoverDraw
{

    private double DEG2RAD = 3.14159/180;

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

    public void DrawCover(TextRenderer renderer, GL2 gl)
    {
        int[] focalPoint = FindFocalPoint();
        DrawArea(focalPoint, gl);
        DrawName(renderer, gl);
    }

    private int[] FindFocalPoint()
    {
        return  new int[] { width / 5, height / 5};
    }

    public void DrawName(TextRenderer renderer, GL2 gl)
    {
        DrawRectangle(0,height, width, namePartition, palette.get(0), gl);
        DrawCircle(0,0, 10, Color.red, gl);

        //draw text
        renderer.beginRendering(width, height + namePartition);
        {
            renderer.setColor(palette.get(1));
            renderer.draw(name, 0, height + namePartition / 2);
        }
        renderer.endRendering();
    }

    public void DrawArea(int[] focalPoint, GL2 gl)
    {
        for(int x = 2; (x < palette.size()) ; x++)
        {
            DrawRectangle(randy.nextInt(20 * x) + focalPoint[0], randy.nextInt(20 * x) + focalPoint[1],
                    randy.nextInt(palette.size() * 15 * (palette.size() - x % palette.size())), randy.nextInt(palette.size() * 15 * (palette.size() - x% palette.size())),
                    palette.get(x), gl);
           /* DrawCircle(randy.nextInt(50 * x) * focalPoint[0], randy.nextInt(50 * x) + focalPoint[1],
                    randy.nextInt(250 * (palette.size() - x % palette.size())), palette.get(x), gl);*/
        }
    }

    void drawCircle(float radius, GL2 gl)
    {
        gl.glBegin(gl.GL_LINE_LOOP);

        for (int i=0; i < 360; i++)
        {
            double degInRad = i*DEG2RAD;
            gl.glVertex2f((float)cos(degInRad)*radius, (float)sin(degInRad)*radius);
        }

        gl.glEnd();
    }
    

    private void DrawRectangle(int x, int y, int width, int height, Color color, GL2 gl)
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
        System.out.println(color.getRed() + "," + color.getBlue() + ", " + color.getGreen());

        gl.glColor3f( (float) color.getRed() / 255f,  (float) color.getGreen() / 255f,  (float) color.getBlue() / 255f);
        //Define a primitive -  A polygon in this case
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

    private void DrawCircle(int x, int y, float radius, Color color, GL2 gl)
    {
        if(x < 0)
        {
            x = 0;
        }
        if(y < 0)
        {
            y = 0;
        }
        System.out.println(color.getRed() + "," + color.getBlue() + ", " + color.getGreen());


        gl.glColor3f( (float) color.getRed() / 255f,  (float) color.getGreen() / 255f,  (float) color.getBlue() / 255f);

        gl.glBegin(gl.GL_POLYGON);
        double degInRad = Math.PI / 180;

        for (int i=0; i < 360; i++)
        {
            gl.glVertex2f((float)Math.cos(i * degInRad) * radius, (float)Math.cos(i * degInRad)*radius);
        }

        gl.glEnd();
    }
}
