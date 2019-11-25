package quizGui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Gui {

	private JFrame frmKneipenquiz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmKneipenquiz.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKneipenquiz = new JFrame();
		frmKneipenquiz.setTitle("KROMBACHER Kneipenquiz");
		frmKneipenquiz.setForeground(new Color(255, 255, 255));
		frmKneipenquiz.getContentPane().setBackground(Color.WHITE);
		frmKneipenquiz.setBackground(new Color(255, 255, 255));
		frmKneipenquiz.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmKneipenquiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pCardLayout = new JPanel();
		pCardLayout.setBackground(new Color(255, 255, 255));
		frmKneipenquiz.getContentPane().add(pCardLayout, BorderLayout.CENTER);
		pCardLayout.setLayout(new CardLayout(0, 0));
		
		JPanel pMenu = new JPanel();
		pMenu.setBackground(new Color(255, 255, 255));
		pCardLayout.add(pMenu, "name_265045741908699");
		
		GridBagLayout gbl_pMenu = new GridBagLayout();
		gbl_pMenu.columnWidths = new int[]{0, 0, 0};
		gbl_pMenu.rowHeights = new int[]{0, 0, 0};
		gbl_pMenu.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pMenu.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pMenu.setLayout(gbl_pMenu);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		pMenu.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		pMenu.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 1;
		pMenu.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(5, 5, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 1;
		pMenu.add(btnNewButton_3, gbc_btnNewButton_3);
				
		JPanel pHeader = new JPanel();
		pHeader.setBackground(new Color(255, 255, 255));
		frmKneipenquiz.getContentPane().add(pHeader, BorderLayout.NORTH);
				
		JLabel lblHeader = new JLabel(showLogo());
		lblHeader.setLabelFor(lblHeader);
		lblHeader.setSize(25,10);
				
		pHeader.add(lblHeader);
		pHeader.setSize(100, 80);
	}
	
	/**
	 * load picture into header.
	 */
	private ImageIcon showLogo() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResource("iconKrombacher.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(img);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(555, 110, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		return imageIcon;
	}

}
