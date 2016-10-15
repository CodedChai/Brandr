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

    public CoverDraw(ArrayList<Color> palette, int height, int width, String name)
    {
        this.width = width;
        this.height = height;
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


}
