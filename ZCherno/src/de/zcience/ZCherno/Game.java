package de.zcience.ZCherno;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3; // how much the Game will be upscaled

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		frame = new JFrame();
	}

	/**
	 * Creates new Thread object and starts it. Attaches Current Game Object to
	 * Thread.
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display"); // attaching Thread to Game Object
		thread.start();
	}

	/**
	 * Stops the thread.
	 */
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * called, after thread was started, basically the main loop
	 */
	@Override
	public void run() {
		while (running) {
			update();
			render();
		}
	}

	public void update() {

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // triple buffering :D
			return;
		}else{
			Graphics g = bs.getDrawGraphics();
			// all graphics being drawn on the screen will be drawn here

			g.setColor(Color.red);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.dispose(); // has to be done! if not, game will crash after some time.
							// disposes of the allready drawn graphics
			bs.show(); // makes next available buffer appear
		}

	
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false); // don't make the window resizable
		game.frame.setTitle("ZCherno");
		game.frame.add(game);
		game.frame.pack(); // size up the frame to the wanted size
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null); // centers our window
		game.frame.setVisible(true); // shows the window

		game.start();

	}
}
