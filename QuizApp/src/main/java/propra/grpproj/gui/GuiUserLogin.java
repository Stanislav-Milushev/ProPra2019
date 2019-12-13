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
import java.util.List;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.exceptions.CsvException;

import java.util. Date;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;

import propra.grpproj.quiz.Socket.SocketClient;
import propra.grpproj.quiz.Socket.SocketServer;
import propra.grpproj.quiz.SocketDataObjects.Login;
import propra.grpproj.quiz.SocketDataObjects.RegisterUser;
import propra.grpproj.quiz.SocketDataObjects.UserType;
import propra.grpproj.quiz.dataholders.Question;
import propra.grpproj.quiz.repositories.sqlite.EveningRepository;
import propra.grpproj.quiz.repositories.sqlite.PlayerOfRoundRepository;
import propra.grpproj.quiz.repositories.sqlite.PubRepository;
import propra.grpproj.quiz.repositories.sqlite.QuestionOfRoundRepository;
import propra.grpproj.quiz.repositories.sqlite.QuestionRepository;
import propra.grpproj.quiz.repositories.sqlite.RoundsOfEveningRepository;
import propra.grpproj.quiz.repositories.sqlite.UserRepository;
import propra.grpproj.quiz.repositories.sqlite.utilities.SqliteCoreUtilities;

/**
 * 
 * @author Lisa Praedel & Marius Discher
 *
 */

public class GuiUserLogin {
	
	private static final Logger LOG = LoggerFactory.getLogger(GuiUserLogin.class);
	
	private static GuiUserLogin instance;

	private JFrame frmUserLogin;
	private JTextField tfUserName;
	private JTextField tfPW;
	

	/**
	 * Launch the application.
	 * @throws CsvException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) {
		
		setupDataBase();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiUserLogin window = new GuiUserLogin();
					window.getFrmUserLogin().setVisible(true);
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
		getFrmUserLogin().getContentPane().setBackground(new Color(255, 255, 255));
		getFrmUserLogin().getContentPane().setLayout(new BorderLayout(0, 0));
		getFrmUserLogin().setBounds(100, 100, 450, 300);
		getFrmUserLogin().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
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
		gbl_pUserLoginInput.rowHeights = new int[] {0};
		gbl_pUserLoginInput.columnWeights = new double[]{0.0, 0.0};
		gbl_pUserLoginInput.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		pUserLoginInput.setLayout(gbl_pUserLoginInput);
		
		
		JLabel lblText = new JLabel("Bitte geben Sie Ihre Logindaten ein.");
		GridBagConstraints gbc_lblText = new GridBagConstraints();
		gbc_lblText.insets = new Insets(5, 5, 5, 5);
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
		gbc_bLogin.gridwidth = 1;
		gbc_bLogin.insets = new Insets(5, 5, 5, 5);
		gbc_bLogin.gridx = 0;
		gbc_bLogin.gridy = 3;
		pUserLoginInput.add(bLogin,gbc_bLogin);
		
		JButton bRegister = new JButton("Register");
		bRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiRegister register = new GuiRegister();
				register.getFrame().setVisible(true);
				getFrmUserLogin().dispose();
			}
		});
		
		GridBagConstraints gbc_bRegister = new GridBagConstraints();
		gbc_bRegister.gridwidth = 2;
		gbc_bRegister.insets = new Insets(5, 5, 5, 5);
		gbc_bRegister.gridx = 0;
		gbc_bRegister.gridy = 4;
		pUserLoginInput.add(bRegister,gbc_bRegister); 
		
		JButton bGuest = new JButton("Als Gast anmelden");
		bGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleregister_guest();
			}
		});
		
		GridBagConstraints gbc_bGuest = new GridBagConstraints();
		gbc_bGuest.gridwidth = 1;
		gbc_bGuest.insets = new Insets(5, 5, 5, 5);
		gbc_bGuest.gridx = 1;
		gbc_bGuest.gridy = 3;
		pUserLoginInput.add(bGuest,gbc_bGuest);
		

		getFrmUserLogin().getContentPane().add(pHeader, BorderLayout.NORTH);
		getFrmUserLogin().getContentPane().add(pUserLoginInput, BorderLayout.CENTER);
		
		getFrmUserLogin().pack();
		getFrmUserLogin().setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		
		System.out.println("Ich bin da ");
		
		JFrame parent = new JFrame();
		
		switch (usertype) {
		
		case DEFAULT:
			
			JOptionPane.showMessageDialog(parent, "Login successfull. Currently logged in as user.");
			
			GuiMenu menu = new GuiMenu();
			menu.getFrame().setVisible(true);
			getFrmUserLogin().dispose();
			break;
			
		case ADMIN:
			
			JOptionPane.showMessageDialog(parent, "Login successfull. Currently logged in as admin.");
			
			GuiAdmin admin = new GuiAdmin();
			admin.getFrmAdmin().setVisible(true);
			getFrmUserLogin().dispose();
			break;
			
		case PUB_OWNER:
			
			JOptionPane.showMessageDialog(parent, "Login successfull. Currently logged in as pubowner.");
			
			GuiPubOwner pubowner = new GuiPubOwner();
			pubowner.getFrame().setVisible(true);
			getFrmUserLogin().dispose();
			break;
			
		case ADMIN_PUBOWNER:
			
			JOptionPane.showMessageDialog(parent, "Login successfull. Currently logged in as admin & pubowner.");
			
			GuiOptions option = new GuiOptions();
			option.getFrame().setVisible(true);
			getFrmUserLogin().dispose();
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
		
		SocketServer.start(4000);
		
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		SocketClient.connect("127.0.0.1", 4000, userName);
		

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		UserType usertype = UserType.DEFAULT;
		
		Login login = new Login(userName, pw);
		SocketClient.getInstance().sendObject(login);
	}
	
	/**
	 * The setup of the database.
	 * 
	 * @author Daniel
	 *
	 */
	private static void setupDataBase()
	{
		
		try
		{
			
        LOG.info("Launching the Quiz App...");

        /*
         * Setup database
         */
        LOG.info("Initialize database driver and drop database...");
        SqliteCoreUtilities.initializeDrive();
        SqliteCoreUtilities.initializeDatabase();

        // ---- setup repository instances

        LOG.info("  Create questions-table and populate with data from CSV file...");
        List<Question> questions = Question.readAllQuestionsFrom("docs/ProPra-Fragen-final.csv");
        QuestionRepository questionRepository = new QuestionRepository();
        questionRepository.createTable();
        for (Question question : questions)
        {
            questionRepository.save(question);
        }

        LOG.info("  Create users-table...");
        UserRepository userRepository = new UserRepository();
        userRepository.createTable();

        LOG.info("  Create pubs-table...");
        PubRepository pubRepository = new PubRepository();
        pubRepository.createTable();

        LOG.info("  Create evenings-table...");
        EveningRepository eveningRepository = new EveningRepository();
        eveningRepository.createTable();

        LOG.info("  Create roundsOfEvenings-table...");
        RoundsOfEveningRepository roundsOfEveningRepository = new RoundsOfEveningRepository();
        roundsOfEveningRepository.createTable();

        LOG.info("  Create questionsOfRounds-table...");
        QuestionOfRoundRepository questionOfRoundRepository = new QuestionOfRoundRepository();
        questionOfRoundRepository.createTable();

        LOG.info("  Create playersOfRounds-table...");
        PlayerOfRoundRepository playerOfRoundRepository = new PlayerOfRoundRepository();
        playerOfRoundRepository.createTable();
        
        LOG.info("Launching Server...");
        
        Connection connection = SqliteCoreUtilities.connect();
        Statement statement = connection.createStatement();

        statement.executeUpdate("insert into users values(7, 'Gerry', 'pw', 'bla@blub.de', 'admin')");
        
    } catch (Exception e)
    {
        // App crash while start-up.
        e.printStackTrace();
    }
}
	
	public void handleregister_guest() {
		
		String username = randomenough();
		
		String email = "Temp@Krombacher.de";
		
		String password = "Passwort123";
		
		UserType usertype = UserType.DEFAULT;
		
		
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
		
		
		RegisterUser register = new RegisterUser (username, email, password, usertype);
		SocketClient.getInstance().sendObject(register);
	}
	
	public String randomenough(){
		
		Date date= new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		
		System.out.println(""+ts);
		
		int username = ts.hashCode();
		
		String userName = String.valueOf(username);
		
		System.out.println("ZZ" + userName);
		
		return userName;
		
		
	}
	
	public JFrame getFrame () {
		
		return getFrmUserLogin();
	}
	 
	public static GuiUserLogin getInstance(){
		
		return instance;
	}

	public JFrame getFrmUserLogin()
	{
		return frmUserLogin;
	}
	
}
