import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static com.jogamp.opengl.GL.GL_BYTE;
import static com.jogamp.opengl.GL.GL_RGB;

/**
 * Created by alvarpq on 10/15/2016.
 */
public class BrandrHome extends Frame implements GLEventListener
{
    static {
        GLProfile.getDefault();
    }

    GLProfile glProfile = null;
    GLCapabilities glCapabilities = null;
    GLCanvas glCanvas = null;

    GL2 gl;

    public BrandrHome()
    {
        super("Brandr");

    }

    public static void main(String[] args)
    {
        new BrandrHome();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable)
    {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable)
    {
//push?
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height)
    {

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable)
    {

    }

    public BufferedImage makeScreenshot(int width, int height) {
        BufferedImage screenshot = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = screenshot.getGraphics();

        ByteBuffer buffer = Buffers.newDirectByteBuffer(width * height * 3);

        gl.glReadPixels(0, 0, width, height, GL_RGB, GL_BYTE, buffer);


        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                // The color are the three consecutive bytes, it's like referencing
                // to the next consecutive array elements, so we got red, green, blue..
                // red, green, blue, and so on..
                graphics.setColor(new Color( buffer.get()*2, buffer.get()*2, buffer.get()*2 ));
                graphics.drawRect(w,height - h, 1, 1); // height - h is for flipping the image
            }
        }
        return screenshot;
    }

    public void sendScreenshotToFile(String filepath) {
        try {

            BufferedImage screenshot = makeScreenshot(getWidth(), getHeight());

            ImageIO.write(screenshot, "png", new File(filepath));
        } catch (IOException ex) {

        }
    }


}
