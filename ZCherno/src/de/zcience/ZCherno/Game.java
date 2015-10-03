package de.zcience.ZCherno;

public class Game implements Runnable {

	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3; // how much the Game will be upscaled

	private Thread thread;

	/**
	 * Creates new Thread object and starts it. Attaches Current Game Object to
	 * Thread.
	 */
	public synchronized void start() {
		thread = new Thread(this, "Display"); // attaching Thread to Game Object
		thread.start();
	}
	
	/**
	 * Stops the thread.
	 */
	public synchronized void stop(){
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
