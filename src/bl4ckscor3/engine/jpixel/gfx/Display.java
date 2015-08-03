package bl4ckscor3.engine.jpixel.gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import bl4ckscor3.engine.jpixel.Window;

/**
 * The display in which everything gets rendered
 */
public class Display extends Canvas
{
	private static final long serialVersionUID = 6138305616621720049L;
	/**
	 * The window of the display
	 */
	private Window window;
	/**
	 * The screen to draw on
	 */
	private Bitmap screen;
	/**
	 * BufferedImage representative of the bitmap
	 */
	private BufferedImage image;
	private Bitmap home;
	
	/**
	 * Sets up the display
	 * @param w The window the display is displayed in
	 */
	public Display(Window w)
	{
		setBounds(0, 0, w.getWidth(), w.getHeight());
		
		window = w;
		screen = new Bitmap(w.getWidth(), w.getHeight());
		image = new BufferedImage(w.getWidth(), w.getHeight(), BufferedImage.TYPE_INT_ARGB);
		screen.setPixels(((DataBufferInt)image.getRaster().getDataBuffer()).getData());
		home = Bitmap.getBitmap("/home.png");
	}
	
	/**
	 * Renders the game all the time
	 */
	public void render(Graphics g)
	{
		screen.fill(Color.CYAN.getRGB());
		screen.renderBitmap(home, 0, 0);
		g.drawImage(image, 0, 0, null);
	}
	
	/**
	 * Ticks the game every gametick
	 */
	public void tick(){}
	
	/**
	 * Gets the window of this display
	 * @return The window
	 */
	public Window getWindow()
	{
		return window;
	}
}
