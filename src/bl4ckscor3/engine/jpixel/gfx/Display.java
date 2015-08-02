package bl4ckscor3.engine.jpixel.gfx;

import java.awt.Canvas;

import bl4ckscor3.engine.jpixel.Window;

/**
 * The display in which everything gets rendered
 */
public class Display extends Canvas
{
	private static final long serialVersionUID = 6138305616621720049L;

	/**
	 * Sets up the display
	 * @param w The window the display is displayed in
	 */
	public Display(Window w)
	{
		setBounds(0, 0, w.getWidth(), w.getHeight());
	}
}
