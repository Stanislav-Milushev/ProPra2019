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
import javax.swing.Timer;
import javax.swing.JTextArea;

public class GuiQuiz {
	
	public static int sec = 20;						//Hier noch Zeit vom KneipenAbend holen
	public static Timer myTimer;
	public JLabel lblCountdown;
	public JPanel pQuestion;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiQuiz window = new GuiQuiz();
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
	public GuiQuiz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
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
		 * Initialize question panel
		 */
		JPanel pQuestion = new JPanel();
		pQuestion.setBackground(new Color(255, 255, 255));
		
		GridBagLayout gbl_pQuestion = new GridBagLayout();
		gbl_pQuestion.columnWidths = new int[] {};
		gbl_pQuestion.rowHeights = new int[] {};
		gbl_pQuestion.columnWeights = new double[]{0.0};
		gbl_pQuestion.rowWeights = new double[]{0.0, 0.0, 0.0};
		pQuestion.setLayout(gbl_pQuestion);
		
		JTextArea taQuestionText = new JTextArea();
		taQuestionText.setEditable(false);
		taQuestionText.setText("Hier kann dann später die Frage stehen");
		taQuestionText.setForeground(Color.BLACK);
		GridBagConstraints gbc_taQuestionText = new GridBagConstraints();
		gbc_taQuestionText.insets = new Insets(5, 5, 5, 5);
		gbc_taQuestionText.fill = GridBagConstraints.HORIZONTAL;
		gbc_taQuestionText.gridx = 0;
		gbc_taQuestionText.gridy = 0;
		pQuestion.add(taQuestionText, gbc_taQuestionText);
		
		JLabel lblCountdown = new JLabel();
		lblCountdown.setText(String.valueOf(sec));
		lblCountdown.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCountdown = new GridBagConstraints();
		gbc_lblCountdown.insets = new Insets(35, 5, 35, 5);
		gbc_lblCountdown.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCountdown.gridx = 0;
		gbc_lblCountdown.gridy = 1;
		pQuestion.add(lblCountdown, gbc_lblCountdown);
		
		JPanel pButtons = new JPanel();
		pButtons.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_pButtons = new GridBagConstraints();
		gbc_pButtons.insets = new Insets(5, 5, 5, 5);
		gbc_pButtons.fill = GridBagConstraints.BOTH;
		gbc_pButtons.gridx = 0;
		gbc_pButtons.gridy = 2;
		gbc_pButtons.gridwidth = 2;
		pQuestion.add(pButtons, gbc_pButtons);
		
		GridBagLayout gbl_pButtons = new GridBagLayout();
		gbl_pButtons.columnWidths = new int[]{};
		gbl_pButtons.rowHeights = new int[]{};
		gbl_pButtons.columnWeights = new double[]{0.0, 0.0};
		gbl_pButtons.rowWeights = new double[]{0.0, 0.0};
		pButtons.setLayout(gbl_pButtons);
		
		JButton bA = new JButton();
		bA.setText("A");
		GridBagConstraints gbc_bA = new GridBagConstraints();
		gbc_bA.anchor = GridBagConstraints.CENTER;
		gbc_bA.insets = new Insets (15, 15, 15, 15);
		gbc_bA.fill = GridBagConstraints.HORIZONTAL;
		gbc_bA.gridx = 0;
		gbc_bA.gridy = 0;
		pButtons.add(bA, gbc_bA);
		
		JButton bB = new JButton();
		bB.setText("B");
		GridBagConstraints gbc_bB = new GridBagConstraints();
		gbc_bB.anchor = GridBagConstraints.CENTER;
		gbc_bB.insets = new Insets(15, 15, 15, 15);
		gbc_bB.fill = GridBagConstraints.HORIZONTAL;
		gbc_bB.gridx = 1;
		gbc_bB.gridy = 0;
		pButtons.add(bB, gbc_bB);
		
		JButton bC = new JButton();
		bC.setText("C");
		GridBagConstraints gbc_bC = new GridBagConstraints();
		gbc_bC.anchor = GridBagConstraints.CENTER;
		gbc_bC.insets = new Insets (15, 15, 15, 15);
		gbc_bC.fill = GridBagConstraints.HORIZONTAL;
		gbc_bC.gridx = 0;
		gbc_bC.gridy = 1;
		pButtons.add(bC, gbc_bC);
		
		JButton bD = new JButton();
		bD.setText("D");
		GridBagConstraints gbc_bD = new GridBagConstraints();
		gbc_bD.anchor = GridBagConstraints.CENTER;
		gbc_bD.insets = new Insets(15, 15, 15, 15);
		gbc_bD.fill = GridBagConstraints.HORIZONTAL;
		gbc_bD.gridx = 1;
		gbc_bD.gridy = 1;
		pButtons.add(bD, gbc_bD);

		myTimer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sec--;
				lblCountdown.setText(String.valueOf(sec));
				if (sec == 0) {
					lblCountdown.setText("Zeit abgelaufen");
					myTimer.stop();
					bA.setEnabled(false);
					bB.setEnabled(false);
					bC.setEnabled(false);
					bD.setEnabled(false);
				} 
			}
		});
		myTimer.start();
		
		frame.getContentPane().add(pHeader, BorderLayout.NORTH);
		frame.getContentPane().add(pQuestion, BorderLayout.CENTER);
		
		
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
		return frame;
	}
	
	/**
	 * load question and options into guielements
	 */
	public void loadQuestion() {
		
	}
	

	
}

