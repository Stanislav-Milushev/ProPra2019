package propra.grpproj.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import propra.grpproj.quiz.Socket.SocketClient;
import propra.grpproj.quiz.SocketDataObjects.Login;
import propra.grpproj.quiz.SocketDataObjects.UserType;

import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Lisa Praedel & Marius Discher
 *
 */

public class GuiUserLogin {
	
	private static GuiUserLogin instance;
	
	private static SocketClient socket_client;

	private JFrame frmUserLogin;
	private JTextField tfUserName;
	private JTextField tfPW;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiUserLogin window = new GuiUserLogin();
					window.frmUserLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiUserLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUserLogin = new JFrame();
		frmUserLogin.getContentPane().setBackground(new Color(255, 255, 255));
		frmUserLogin.getContentPane().setLayout(new BorderLayout(0, 0));
		frmUserLogin.setBounds(100, 100, 450, 300);
		frmUserLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		/**
		 * Initialize header
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
		JPanel pUserLoginInput = new JPanel();
		pUserLoginInput.setBackground(new Color(255, 255, 255));
		
		GridBagLayout gbl_pUserLoginInput = new GridBagLayout();
		gbl_pUserLoginInput.columnWidths = new int[] {0};
		gbl_pUserLoginInput.rowHeights = new int[] {0, 0, 0, 0};
		gbl_pUserLoginInput.columnWeights = new double[]{1.0, 1.0};
		gbl_pUserLoginInput.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		pUserLoginInput.setLayout(gbl_pUserLoginInput);
		
		
		JLabel lblText = new JLabel("Bitte geben Sie Ihre Logindaten ein.");
		GridBagConstraints gbc_lblText = new GridBagConstraints();
		gbc_lblText.insets = new Insets(5, 5, 5, 0);
		gbc_lblText.gridwidth = 2;
		gbc_lblText.fill = GridBagConstraints.CENTER;
		gbc_lblText.gridx = 0;
		gbc_lblText.gridy = 0;
		pUserLoginInput.add(lblText, gbc_lblText);

		JLabel lblUserName = new JLabel("Benutzername");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.insets = new Insets(5, 5, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 1;
		pUserLoginInput.add(lblUserName, gbc_lblUserName);
		
		tfUserName = new JTextField();
		GridBagConstraints gbc_tfUserName = new GridBagConstraints();
		gbc_tfUserName.anchor = GridBagConstraints.WEST;
		gbc_tfUserName.insets = new Insets(5, 5, 5, 5);
		gbc_tfUserName.gridx = 1;
		gbc_tfUserName.gridy = 1;
		pUserLoginInput.add(tfUserName, gbc_tfUserName);
		tfUserName.setColumns(10);
		
		JLabel lblPW = new JLabel("Passwort");
		GridBagConstraints gbc_lblPW = new GridBagConstraints();
		gbc_lblPW.insets = new Insets(5, 5, 5, 5);
		gbc_lblPW.anchor = GridBagConstraints.EAST;
		gbc_lblPW.gridx = 0;
		gbc_lblPW.gridy = 2;
		pUserLoginInput.add(lblPW, gbc_lblPW);
		
		tfPW = new JTextField();
		GridBagConstraints gbc_tfPW = new GridBagConstraints();
		gbc_tfPW.insets = new Insets(5, 5, 5, 5);
		gbc_tfPW.anchor = GridBagConstraints.WEST;
		gbc_tfPW.gridx = 1;
		gbc_tfPW.gridy = 2;
		pUserLoginInput.add(tfPW, gbc_tfPW);
		tfPW.setColumns(10);
		
		
		JButton bLogin = new JButton("Login");
		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfUserName.getText();
				String pw = tfPW.getText();
				handleLogin(username, pw);
			}
		});
		
		GridBagConstraints gbc_bLogin = new GridBagConstraints();
		gbc_bLogin.gridwidth = 2;
		gbc_bLogin.insets = new Insets(5, 5, 5, 5);
		gbc_bLogin.gridx = 0;
		gbc_bLogin.gridy = 3;
		pUserLoginInput.add(bLogin,gbc_bLogin);
		
		JButton bRegister = new JButton("Register");
		bRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiRegister register = new GuiRegister();
				register.getFrame().setVisible(true);
				frmUserLogin.dispose();
			}
		});
		
		GridBagConstraints gbc_bRegister = new GridBagConstraints();
		gbc_bRegister.gridwidth = 2;
		gbc_bRegister.insets = new Insets(5, 5, 5, 5);
		gbc_bRegister.gridx = 1;
		gbc_bRegister.gridy = 3;
		pUserLoginInput.add(bRegister,gbc_bRegister);
		

		frmUserLogin.getContentPane().add(pHeader, BorderLayout.NORTH);
		frmUserLogin.getContentPane().add(pUserLoginInput, BorderLayout.CENTER);
		
		frmUserLogin.pack();
		frmUserLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	
	/**
	 * Handle the user type of a successful login
	 * @param usertype
	 * @author Marius
	 */
	public void login_Return(UserType usertype) {
		
		JFrame parent = new JFrame();
		
		switch (usertype) {
		
		case DEFAULT:
			
			JOptionPane.showMessageDialog(parent, "Login successfull. Currently logged in as user.");
			
			GuiMenu menu = new GuiMenu();
			menu.getFrame().setVisible(true);
			frmUserLogin.dispose();
			break;
			
		case ADMIN:
			
			JOptionPane.showMessageDialog(parent, "Login successfull. Currently logged in as admin.");
			
			GuiAdmin admin = new GuiAdmin();
			admin.getFrmAdmin().setVisible(true);
			frmUserLogin.dispose();
			break;
			
		case PUBOWNER:
			
			JOptionPane.showMessageDialog(parent, "Login successfull. Currently logged in as pubowner.");
			
			GuiPubOwner pubowner = new GuiPubOwner();
			pubowner.getFrame().setVisible(true);
			frmUserLogin.dispose();
			break;
			
		case ADMINPUBOWNER:
			
			JOptionPane.showMessageDialog(parent, "Login successfull. Currently logged in as admin & pubowner.");
			
			GuiOptions option = new GuiOptions();
			option.getFrame().setVisible(true);
			frmUserLogin.dispose();
			break;
			
		case ERROR:
			
			JOptionPane.showMessageDialog(parent, "Cannot login. Please check your login data and try again.");
			break;
		}
	}
	
	/**
	 * 
	 * Give the login credentials to the socket 
	 * @param userName
	 * @param pw
	 * @author Marius
	 */
	public void handleLogin(String userName, String pw) {
		
		Login login = new Login(userName, pw);
		socket_client.sendObject(login);
	}
	
	
	public JFrame getFrame () {
		
		return frmUserLogin;
	}
	
	public static GuiUserLogin getInstance(){
		
		return instance;
	}
}
