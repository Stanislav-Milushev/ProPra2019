package propra.grpproj.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GuiRegister {

	private JFrame frameRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiRegister window = new GuiRegister();
					window.frameRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameRegister = new JFrame();
		frameRegister.getContentPane().setBackground(new Color(255, 255, 255));
		frameRegister.getContentPane().setLayout(new BorderLayout(0, 0));
		frameRegister.setBounds(100, 100, 450, 300);
		frameRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/**
		 * Inititalize header
		 */	
		JPanel pHeader = new JPanel();
		pHeader.setBackground(new Color(255, 255, 255));
		GridBagLayout gbl_pHeader = new GridBagLayout();
		gbl_pHeader.columnWidths = new int[] {0};
		gbl_pHeader.rowHeights = new int[] {0};
		gbl_pHeader.columnWeights = new double[]{0};
		gbl_pHeader.rowWeights = new double[]{0};
		pHeader.setLayout(gbl_pHeader);

		JLabel lblHeaderDas = new JLabel("Das");
		lblHeaderDas.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblHeaderDas.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblHeaderDas = new GridBagConstraints();
		gbc_lblHeaderDas.insets = new Insets(5, 5, 5, 5);
		gbc_lblHeaderDas.gridx = 0;
		gbc_lblHeaderDas.gridy = 0;
		pHeader.add(lblHeaderDas, gbc_lblHeaderDas);
		
		JLabel lblHeaderKneipenquiz = new JLabel("- Kneipenquiz");
		lblHeaderKneipenquiz.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblHeaderKneipenquiz.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblHeaderKneipenquiz = new GridBagConstraints();
		gbc_lblHeaderKneipenquiz.gridx = 2;
		gbc_lblHeaderKneipenquiz.gridy = 0;
		pHeader.add(lblHeaderKneipenquiz, gbc_lblHeaderKneipenquiz);
								
		JLabel lblHeaderIcon = new JLabel(showLogo());
		lblHeaderIcon.setLabelFor(lblHeaderIcon);
		GridBagConstraints gbc_lblHeaderIcon = new GridBagConstraints();
		gbc_lblHeaderIcon.insets = new Insets(5, 50, 5, 50);
		gbc_lblHeaderIcon.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblHeaderIcon.gridx = 1;
		gbc_lblHeaderIcon.gridy = 0;
		pHeader.add(lblHeaderIcon, gbc_lblHeaderIcon);
		
		
		/**
		 * Initialize login panel
		 */
		JPanel pPubOwner = new JPanel();
		pPubOwner.setBackground(new Color(255, 255, 255));
		
		GridBagLayout gbl_pubOwner = new GridBagLayout();
		gbl_pubOwner.columnWidths = new int[] {0};
		gbl_pubOwner.rowHeights = new int[] {0, 0, 0, 0};
		gbl_pubOwner.columnWeights = new double[]{1.0, 1.0};
		gbl_pubOwner.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		pPubOwner.setLayout(gbl_pubOwner);
		
		
		JLabel lblText = new JLabel("Eingeloggt als Kneipen Besitzer.");
		GridBagConstraints gbc_lblText = new GridBagConstraints();
		gbc_lblText.insets = new Insets(5, 5, 5, 0);
		gbc_lblText.gridwidth = 2;
		gbc_lblText.fill = GridBagConstraints.CENTER;
		gbc_lblText.gridx = 0;
		gbc_lblText.gridy = 0;
		pPubOwner.add(lblText, gbc_lblText);
		
		JLabel lblText2 = new JLabel("Hier gibt es die Funktionen für Quiz starten und Fragenpools erstellen.");
		GridBagConstraints gbc_lblText2 = new GridBagConstraints();
		gbc_lblText2.insets = new Insets(5, 5, 5, 0);
		gbc_lblText2.gridwidth = 2;
		gbc_lblText2.fill = GridBagConstraints.CENTER;
		gbc_lblText2.gridx = 0;
		gbc_lblText2.gridy = 1;
		pPubOwner.add(lblText2, gbc_lblText2);
		
		frameRegister.getContentPane().add(pHeader, BorderLayout.NORTH);
		frameRegister.getContentPane().add(pPubOwner, BorderLayout.CENTER);
		
		JButton bLogout = new JButton("Logout Digga");
		bLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GuiUserLogin gui_user = new GuiUserLogin();
				gui_user.getFrame().setVisible(true);
				frameRegister.dispose();
			}
		});
		
		GridBagConstraints gbc_pub = new GridBagConstraints();
		gbc_pub.gridwidth = 2;
		gbc_pub.insets = new Insets(5, 5, 5, 5);
		gbc_pub.gridx = 0;
		gbc_pub.gridy = 3;
		pPubOwner.add(bLogout, gbc_pub);
		
		frameRegister.pack();
		frameRegister.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	
	
	public JFrame getFrame() {
		return frameRegister;
	}

}
