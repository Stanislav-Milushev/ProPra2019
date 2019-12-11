package propra.grpproj.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class GuiOptions {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main3(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiOptions window = new GuiOptions();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiOptions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
