package brander;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


/**
 * Created by Guard on 10/15/2016.
 */
public class ColorPickr {


    public ArrayList<Color> GenerateColors(int colorCount)
    {

        ArrayList<Color> colors = new ArrayList<Color>();

//        float goldenRatioConjugate = 0.618033988749895f;
//        float currentHue = (float)random.NextDouble();

        Random randy = new Random();
        Color baseColor = new Color(randy.nextInt(255), randy.nextInt(255), randy.nextInt(255));
        colors.add(baseColor);

        for (int i = 1; i < colorCount; i++)
        {
          colors.add(generateRandomColor(baseColor));

        }

        return colors;
    }

    private Color generateRandomColor(Color mix) {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        // mix the color
        if (mix != null) {
            red = (red + mix.getRed()) / 2;
            green = (green + mix.getGreen()) / 2;
            blue = (blue + mix.getBlue()) / 2;
        }

        Color color = new Color(red, green, blue);
        return color;
    }

}
