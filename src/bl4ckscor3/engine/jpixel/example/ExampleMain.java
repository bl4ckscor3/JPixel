package bl4ckscor3.engine.jpixel.example;

import javax.swing.JFrame;

import bl4ckscor3.engine.jpixel.Window;
import bl4ckscor3.engine.jpixel.gfx.Display;

public class ExampleMain
{
	public static void main(String[] args)
	{
		new ExampleMain();
	}
	
	public ExampleMain()
	{
		Window w = new Window(640, 480, "This is a window :O", true, false, JFrame.EXIT_ON_CLOSE, true);
		
		w.setDisplay(new Display(w));
		w.show();
	}
}
