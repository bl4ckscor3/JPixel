package bl4ckscor3.engine.jpixel.gfx;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.imageio.ImageIO;

import bl4ckscor3.engine.jpixel.Window;

/**
 * Holds the image to render
 */
public class Bitmap
{
	private int[] pixels;
	private int width;
	private int height;

	/**
	 * Sets up the Bitmap
	 * @param w The width of the image
	 * @param h The height of the image
	 */
	public Bitmap(int w, int h)
	{
		width = w;
		height = h;
		pixels = new int[width * height];
	}
	
	/**
	 * Fills the whole bitmap with this color
	 * @param color The color to use
	 */
	public void fill(int color)
	{
		Arrays.fill(pixels, color);
	}
	
	/**
	 * Sets the pixels array
	 * @param p The pixel array to set
	 */
	public void setPixels(int[] p)
	{
		pixels = p;
	}
	
	/**
	 * Returns the pixel's position in the array from the specified x/y position
	 * @param x The x position of the pixel
	 * @param y The y position of the pixel
	 * @return The pixel's position in the array
	 */
	private int getPixelPosition(int x, int y)
	{
		return y * width + x;
	}
	
	/**
	 * Gets the pixels of the Bitmap
	 * @return The pixels
	 */
	public int[] getPixels()
	{
		return pixels;
	}
	
	/**
	 * Gets the width of the Bitmap
	 * @return The width
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Gets the height of the Bitmap
	 * @return The height
	 */
	public int getHeight()
	{
		return height;
	}
	
	public static Bitmap getBitmap(String path)
	{
		try
		{
			BufferedImage image = ImageIO.read(Window.class.getResourceAsStream(path));
			Bitmap bitmap = new Bitmap(image.getWidth(), image.getHeight());
			
			image.getRGB(0, 0, bitmap.getWidth(), bitmap.getHeight(), bitmap.getPixels(), 0, bitmap.getWidth());
			return bitmap;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Renders a bitmap on the screen
	 * @param bitmap The bitmap to render
	 * @param x The x position to start rendering
	 * @param y The y position to start rendering
	 */
	public void renderBitmap(Bitmap bitmap, int x, int y)
	{
		for(int wx = 0; wx < width; wx++)
		{
			for(int hy = 0; hy < height; hy++)
			{
				pixels[getPixelPosition(x + wx, y + hy)] = bitmap.pixels[bitmap.getPixelPosition(wx, hy)];
			}
		}
	}
}
