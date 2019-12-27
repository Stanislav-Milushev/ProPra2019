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
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import propra.grpproj.quiz.Socket.SocketClient;
import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.Login;
import propra.grpproj.quiz.SocketDataObjects.RegisterUser;
import propra.grpproj.quiz.SocketDataObjects.TerminateConnection;
import propra.grpproj.quiz.SocketDataObjects.UserType;

public class GuiRegister {

	private JFrame frameRegister;
	private JTextField tfUserName;
	private JTextField tfEmail;
	private JTextField tfPassword;
	private JTextField tfPasswordRepeat; 

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
		 * Initialize register panel
		 */
		JPanel pRegister = new JPanel();
		pRegister.setBackground(new Color(255, 255, 255));
		
		GridBagLayout gbl_Register = new GridBagLayout();
		gbl_Register.columnWidths = new int[] {0};
		gbl_Register.rowHeights = new int[] {0};
		gbl_Register.columnWeights = new double[]{0.0, 0.0};
		gbl_Register.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		pRegister.setLayout(gbl_Register);
		
		
		JLabel lblText = new JLabel("Bitte geben Sie die Daten für die Registrierung ein.");
		GridBagConstraints gbc_lblText = new GridBagConstraints();
		gbc_lblText.insets = new Insets(5, 5, 25, 5);
		gbc_lblText.gridwidth = 2;
		gbc_lblText.fill = GridBagConstraints.CENTER;
		gbc_lblText.gridx = 0;
		gbc_lblText.gridy = 0;
		pRegister.add(lblText, gbc_lblText);
		
		JLabel lblUserName = new JLabel("Benutzername");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = new Insets(5, 5, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 1;
		pRegister.add(lblUserName, gbc_lblUserName);
		
		tfUserName = new JTextField();
		GridBagConstraints gbc_tfUserName = new GridBagConstraints();
		gbc_tfUserName.anchor = GridBagConstraints.WEST;
		gbc_tfUserName.insets = new Insets(5, 5, 5, 5);
		gbc_tfUserName.gridx = 1;
		gbc_tfUserName.gridy = 1;
		pRegister.add(tfUserName, gbc_tfUserName);
		tfUserName.setColumns(15);
		
		JLabel lblPW = new JLabel("Passwort");
		GridBagConstraints gbc_lblPW = new GridBagConstraints();
		gbc_lblPW.insets = new Insets(5, 5, 5, 5);
		gbc_lblPW.anchor = GridBagConstraints.EAST;
		gbc_lblPW.gridx = 0;
		gbc_lblPW.gridy = 3;
		pRegister.add(lblPW, gbc_lblPW);
		
		tfPassword = new JTextField();
		GridBagConstraints gbc_tfPW = new GridBagConstraints();
		gbc_tfPW.insets = new Insets(5, 5, 5, 5);
		gbc_tfPW.anchor = GridBagConstraints.WEST;
		gbc_tfPW.gridx = 1;
		gbc_tfPW.gridy = 3;
		pRegister.add(tfPassword, gbc_tfPW);
		tfPassword.setColumns(15);
		
		JLabel lblMail = new JLabel("Email");
		GridBagConstraints gbc_Mail = new GridBagConstraints();
		gbc_Mail.insets = new Insets(5, 5, 5, 5);
		gbc_Mail.anchor = GridBagConstraints.EAST;
		gbc_Mail.gridx = 0;
		gbc_Mail.gridy = 2;
		pRegister.add(lblMail, gbc_Mail);
		
		tfEmail = new JTextField();
		GridBagConstraints gbc_tfEmail = new GridBagConstraints();
		gbc_tfEmail.insets = new Insets(5, 5, 5, 5);
		gbc_tfEmail.anchor = GridBagConstraints.WEST;
		gbc_tfEmail.gridx = 1;
		gbc_tfEmail.gridy = 2;
		pRegister.add(tfEmail, gbc_tfEmail);
		tfEmail.setColumns(15);
		
		JLabel lblpasswdrepeat = new JLabel("Passwort wiederholen");
		GridBagConstraints gbc_pwrepeat = new GridBagConstraints();
		gbc_pwrepeat.insets = new Insets(5, 5, 5, 5);
		gbc_pwrepeat.anchor = GridBagConstraints.EAST;
		gbc_pwrepeat.gridx = 0;
		gbc_pwrepeat.gridy = 4;
		pRegister.add(lblpasswdrepeat, gbc_pwrepeat);
		
		tfPasswordRepeat = new JTextField();
		GridBagConstraints gbc_tfPasswordrepeat = new GridBagConstraints();
		gbc_tfPasswordrepeat.insets = new Insets(5, 5, 5, 5);
		gbc_tfPasswordrepeat.anchor = GridBagConstraints.WEST;
		gbc_tfPasswordrepeat.gridx = 1;
		gbc_tfPasswordrepeat.gridy = 4;
		pRegister.add(tfPasswordRepeat, gbc_tfPasswordrepeat);
		tfPasswordRepeat.setColumns(15);
				
		JButton bRegister = new JButton("Abschicken");
		bRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfUserName.getText();
				String password = tfPassword.getText();
				String password2 = tfPasswordRepeat.getText();
				String email = tfEmail.getText();				
				boolean check = password.equals(password2);			
				if (check == true) {					
					handleRegister(username, email, password);	
				} else {
					JFrame parent = new JFrame();
					JOptionPane.showMessageDialog(parent, "Passwörter stimmen nicht überein");	
				}	
			}
		});
		GridBagConstraints gbc_Register = new GridBagConstraints();
		gbc_Register.gridwidth = 1;
		gbc_Register.insets = new Insets(25, 5, 5, 5);
		gbc_Register.gridx = 1;
		gbc_Register.gridy = 5;
		pRegister.add(bRegister, gbc_Register);
		
		JButton bBack = new JButton("Zurück zum Login");
		bBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUserLogin back = new GuiUserLogin();
				back.getFrame().setVisible(true);
				frameRegister.dispose();
			}
		});	
		GridBagConstraints gbc_Back = new GridBagConstraints();
		gbc_Back.gridwidth = 1;
		gbc_Back.insets = new Insets(25, 5, 5, 5);
		gbc_Back.gridx = 0;
		gbc_Back.gridy = 5;
		pRegister.add(bBack, gbc_Back);
		
		
		frameRegister.getContentPane().add(pHeader, BorderLayout.NORTH);
		frameRegister.getContentPane().add(pRegister, BorderLayout.CENTER);
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
	
	public void handleRegister(String username, String email, String password) {
		
		SocketServer.start(4000);
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		SocketClient.connect("127.0.0.1", 4000, username);
		

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		UserType usertype = UserType.DEFAULT;
		
		RegisterUser user = new RegisterUser(username, password, email, usertype);
		
		SocketClient.getInstance().sendObject(user);
	}

	public void register_return() {
		
		JOptionPane.showInputDialog("Registrierung erfolgreich! Es erfolgt ein automatischer Login");
	}
	
	public void autologin() {
	
		GuiMenu gu = new GuiMenu();
		gu.getFrame().setVisible(true);
		frameRegister.dispose();
	}
}
