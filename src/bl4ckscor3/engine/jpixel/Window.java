package bl4ckscor3.engine.jpixel;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import bl4ckscor3.engine.jpixel.gfx.Display;

/**
 * A simple window with a loop in it
 */
public class Window
{
	/**
	 * The actual window
	 */
	private JFrame frame;
	/**
	 * The width of inside the window
	 */
	private int width;
	/**
	 * The height of inside the window
	 */
	private int height;
	/**
	 * The title of the window
	 */
	private String title;
	/**
	 * Is this window showing in the center of the screen?
	 */
	private boolean center;
	/**
	 * Is this window resizable?
	 */
	private boolean resizable;
	/**
	 * The operation to do when the red x is pressed (see JFrame for reference)
	 */
	private int operation;
	/**
	 * Is this window the main window?
	 */
	private boolean main;
	/**
	 * The display (what gets drawn on the screen)
	 */
	private Display display;
	
	/**
	 * Sets up the window but will NOT start it. Use the setDisplay() and show() methods to let it show up
	 * @param w The width of inside the window
	 * @param h The height of inside the window
	 * @param t The title of the window
	 * @param c Should the window be opened in the center of the screen?
	 * @param r Should the window be resizable by the user?
	 * @param o The operation to do when the red x is pressed (see JFrame for reference)
	 * @param m Is this the main window (with the game loop)?
	 */
	public Window(int w, int h, String t, boolean c, boolean r, int o, boolean m)
	{
		width = w;
		height = h;
		title = t;
		center = c;
		resizable = r;
		operation = o;
		main = m;
		frame = new JFrame(t);
	}

	/**
	 * Sets up the window with the predefined values. You should never call this method twice
	 * @throws Throws a NPE when setDisplay() hasn't been called yet.
	 */
	public void show()
	{
		if(display == null)
			throw new NullPointerException("Display cannot be null.");
		
		frame.setDefaultCloseOperation(operation);
		frame.setResizable(resizable);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		
		if(center)
			frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);

		if(main)
		{
			GameLoop loop = new GameLoop(this, 60);
			
			loop.start();
		}
	}
	
	/**
	 * Sets the display
	 * @param display The display
	 */
	public void setDisplay(Display d)
	{
		if(display != null)
			return;
		
		display = d;
		frame.add(d);
	}
	
	/**
	 * Gets the width of inside the window
	 * @return The width of inside the window
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Gets the height of inside the window
	 * @return The height of inside the window
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Gets the title of the window
	 * @return The title of the window
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * If the window is resizable
	 * @return If the window is resizable
	 */
	public boolean isResizable()
	{
		return resizable;
	}
	
	/**
	 * If the window is the main window
	 * @return If the window is the main window
	 */
	public boolean isMain()
	{
		return main;
	}
	
	/**
	 * If the window is displayed in the center of the screen or not
	 * @return If the window is centered
	 */
	public boolean isCenter()
	{
		return center;
	}
	
	/**
	 * Gets the operation to do when the red x is pressed (see JFrame for reference)
	 * @return The operation
	 */
	public int getOperation()
	{
		return operation;
	}
	
	/**
	 * Gets the display where things get drawn on
	 * @return The display
	 */
	public Display getDisplay()
	{
		return display;
	}
	
	/**
	 * Gets the window
	 * @return The window
	 */
	public JFrame getFrame()
	{
		return frame;
	}
	
	/**
	 * Closes this window with the given close operation (see constructor @ operation)
	 */
	public void close()
	{
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}
