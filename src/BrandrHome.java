import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

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

    CoverDraw coverDraw;

    public BrandrHome()
    {
        super("Brandr");

        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.black);
        colors.add(Color.white);
        coverDraw = new CoverDraw(colors, 640, 480, "PM me ur dankest memes gurl");

        glProfile = GLProfile.getDefault();
        glCapabilities = new GLCapabilities( glProfile );
        glCanvas = new GLCanvas( glCapabilities );

        glCanvas.addGLEventListener( this);

        add( glCanvas );
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                remove( glCanvas );
                dispose();
                System.exit( 0 );
            }
        });

        setSize( 640, 480 );
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new BrandrHome();
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable)
    {
        System.out.println("Entering init();");
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(.8f, .8f, .8f, 0f); //set to non-transparent black

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable)
    {
        System.out.println("Entering display");

//push?
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height)
    {
        System.out.println("Entering reshape(); x="+x+" y="+y+" width="+width+" height="+height);
        //Get the context
        GL2 gl=glAutoDrawable.getGL().getGL2();
        //Set up projection
        gl.glMatrixMode( GL2.GL_PROJECTION );
        gl.glLoadIdentity();
        //this glOrtho call sets up a 640x480 unit plane with a parallel projection.
        gl.glOrtho(0,640,0,480,0,10);
        //Handle aspect ratio
        double AR= 640.0/480.0;
        if (AR*height<width) gl.glViewport(x, y, (int) (AR*height), height);
        else gl.glViewport(x, y, width, (int) (width/AR));
        gl.glViewport(x, y, (int) (AR*height), height);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();


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
