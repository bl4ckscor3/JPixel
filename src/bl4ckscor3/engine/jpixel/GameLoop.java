package bl4ckscor3.engine.jpixel;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 * The loop which constantly updates the game
 */
public class GameLoop implements Runnable
{
	/**
	 * The window the loop got called from
	 */
	private Window window;
	/**
	 * The fps we want to achieve
	 */
	private int targetTps;
	/**
	 * Is the loop running?
	 */
	private boolean isRunning;
	
	/**
	 * @param w The window the loop got called from
	 * @param ups The updates per second the game SHOULD run do
	 */
	public GameLoop(Window w, int ups)
	{
		window = w;
		targetTps = ups;
	}
	
	/**
	 * Gets the window the loop got called from
	 * @return The window
	 */
	public Window getWindow()
	{
		return window;
	}
	
	/**
	 * Start the loop
	 */
	void start()
	{
		if(isRunning)
		{
			System.out.println("!!!WARNING!!! THREAD IS ALREADY RUNNING!");
			return;
		}
			
		isRunning = true;
		new Thread(this).start();
	}
	
	/**
	 * Stop the loop
	 */
	void stop()
	{
		if(!isRunning)
		{
			System.out.println("!!!WARNING!!! THREAD IS NOT RUNNING!");
			return;
		}
		
		isRunning = false;
	}
	
	/**
	 * Renders the game all the time
	 */
	public void render()
	{
		BufferStrategy bs = window.getDisplay().getBufferStrategy();
		
		if(bs == null)
		{
			window.getDisplay().createBufferStrategy(3); //2d game
			window.getDisplay().requestFocus();
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, window.getWidth(), window.getHeight());
		//put rendering stuff between here...
		window.getDisplay().render(g);
		//...and here
		g.dispose();
		bs.show();
	}
	
	/**
	 * Ticks the game every gametick
	 */
	public void tick(){}
	
	@Override
	public void run()
	{
		int fps = 0;
		int tick = 0;
		double fpsTimer = System.currentTimeMillis();
		double secondsPerTick = 1.0D / targetTps; //how long to wait between each update
		double nanosecondsPerTick = secondsPerTick * 1_000_000_000.0D;
		double then = System.nanoTime();
		double now = System.nanoTime();
		double unprocessed = 0;
		
		while(isRunning)
		{
			now = System.nanoTime();
			unprocessed += (now - then) / nanosecondsPerTick;
			then = now;
			
			while(unprocessed >= 1)
			{
				tick();
				tick++;
				unprocessed--;
			}
			
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			render();
			fps++;
			
			if(System.currentTimeMillis() - fpsTimer >= 1000)
			{
				System.out.println("FPS: " + fps + " | TPS: " + tick);
				fps = 0;
				tick = 0;
				fpsTimer += 1000;
			}
		}
	}
}
