import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.*;

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
}
