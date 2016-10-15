import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


/**
 * Created by Guard on 10/15/2016.
 */
public class ColorPickr {


    public static ArrayList<Color> GenerateColors(int colorCount)
    {
        colorCount = 256 * 256 * 256;
        ArrayList<Color> colors = new ArrayList<Color>();

//        float goldenRatioConjugate = 0.618033988749895f;
//        float currentHue = (float)random.NextDouble();

        Random randy = new Random();

        for (int i = 0; i < colorCount; i++)
        {
//            HSL hslColor = new HSL(currentHue, saturation, luminance);

            Color newColor = new Color(randy.nextInt(255), randy.nextInt(255), randy.nextInt(255));

            colors.add(newColor);

//            currentHue += goldenRatioConjugate;
//            currentHue %= 1.0f;

        }

        return colors;
    }

}
