package propra.grpproj.gui; 


import propra.grpproj.quiz.Socket.SocketClient;
import propra.grpproj.quiz.SocketDataObjects.Pub;
import propra.grpproj.quiz.SocketDataObjects.PubList;
import propra.grpproj.quiz.SocketDataObjects.Question;
import propra.grpproj.quiz.SocketDataObjects.QuestionList;

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
import java.util.ArrayList;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Lisa Praedel
 *
 */
public class GuiAdmin {
	
	private static GuiAdmin instance;
	
	private JFrame frmAdmin;
	private JTable tablePubs, tableQuestions;
	private JTextField tfQAQuestion, tfQACorrectAnswer, tfQAWrongAnswer1, tfQAWrongAnswer2, tfQAWrongAnswer3, tfQAExplanation;
	private JTextField tfQEQuestion, tfQECorrectAnswer, tfQEWrongAnswer1, tfQEWrongAnswer2, tfQEWrongAnswer3, tfQEExplanation;
	private JTextField tfPEPubName, tfPEUserID, tfPEAddress;
	private JComboBox<String> cbPEUnblocking, cbQACorrectAnswer, cbQECorrectAnswer;
	private JComboBox<Long> cbPEPubID, cbQEQuestionID;
	
	private static SocketClient c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAdmin window = new GuiAdmin();
					window.frmAdmin.setVisible(true);
					instance = window;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		String ip = "127.0.0.1";
		int port = 4000;
		
		c = new SocketClient(ip, port);
		Thread clientConnection = new Thread(c);
		clientConnection.start();
		/*
		//TestAnfrage
		client.sendObject(new PubList());
		*/
	}

	/**
	 * Create the application.
	 */
	public GuiAdmin() {
		

	/**
	 * Initialize the contents of the frame.
	 */
		setFrmAdmin(new JFrame());
		getFrmAdmin().getContentPane().setEnabled(false);
		getFrmAdmin().setTitle("KROMBACHER Kneipenquiz");
		getFrmAdmin().setForeground(new Color(255, 255, 255));
		getFrmAdmin().getContentPane().setBackground(Color.WHITE);
		getFrmAdmin().setBackground(new Color(255, 255, 255));
		getFrmAdmin().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		
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
		
		JLabel lblHeaderIcon = new JLabel(showLogo());
		lblHeaderIcon.setLabelFor(lblHeaderIcon);
		GridBagConstraints gbc_lblHeaderIcon = new GridBagConstraints();
		gbc_lblHeaderIcon.insets = new Insets(5, 50, 5, 50);
		gbc_lblHeaderIcon.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblHeaderIcon.gridx = 1;
		gbc_lblHeaderIcon.gridy = 0;
		pHeader.add(lblHeaderIcon, gbc_lblHeaderIcon);
		

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
						
		
		/**
		 * Initialize content
		 */
		JPanel pContent = new JPanel();
		pContent.setBackground(new Color(255, 255, 255));
		frmAdmin.getContentPane().add(pContent, BorderLayout.CENTER);
		GridBagLayout gbl_pContent = new GridBagLayout();
		gbl_pContent.columnWidths = new int[] {0};
		gbl_pContent.rowHeights = new int[] {0};
		gbl_pContent.columnWeights = new double[]{1.0};
		gbl_pContent.rowWeights = new double[]{1.0,0.0};
		pContent.setLayout(gbl_pContent);
		
		
		/**
		 * CardLayout for lists -> pCardLayoutList and cardLayout1
		 */
		JPanel pCardLayoutList = new JPanel();
		GridBagConstraints gbc_pCardLayoutList = new GridBagConstraints();
		gbc_pCardLayoutList.insets = new Insets(5, 5, 5, 5);
		gbc_pCardLayoutList.fill = GridBagConstraints.BOTH;
		gbc_pCardLayoutList.gridx = 0;
		gbc_pCardLayoutList.gridy = 0;
		pContent.add(pCardLayoutList, gbc_pCardLayoutList);
		pCardLayoutList.setBackground(new Color(255, 255, 255));
		pCardLayoutList.setLayout(new CardLayout());
		CardLayout cardLayout1 = (CardLayout) pCardLayoutList.getLayout();
		/**
		 * cards for cardLayout1
		 */
		
		/**
		 * Panel clear for start		
		 */
		JPanel pClear1 = new JPanel();
		pClear1.setBackground(new Color(255, 255, 255));
		pCardLayoutList.add(pClear1, "pClear1");
		
		/**
		 * Panel for pubs
		 */
		JPanel pPubList = new JPanel();
		pPubList.setBackground(new Color(255, 255, 255));
		pCardLayoutList.add(pPubList, "pPubList");
		GridBagLayout gbl_pPubList = new GridBagLayout();
		gbl_pPubList.columnWidths = new int[] {0};
		gbl_pPubList.rowHeights = new int[] {0};
		gbl_pPubList.columnWeights = new double[]{1.0};
		gbl_pPubList.rowWeights = new double[]{0.0};
		pPubList.setLayout(gbl_pPubList);
		
		JLabel lblOverviewPubs = new JLabel("Übersicht der registrierten Kneipen");
		lblOverviewPubs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOverviewPubs.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblOverviewPubs = new GridBagConstraints();
		gbc_lblOverviewPubs.anchor = GridBagConstraints.NORTH;
		gbc_lblOverviewPubs.insets = new Insets(5, 5, 5, 5);
		gbc_lblOverviewPubs.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOverviewPubs.gridx = 0;
		gbc_lblOverviewPubs.gridy = 0;
		pPubList.add(lblOverviewPubs, gbc_lblOverviewPubs);
		
		tablePubs = new JTable() {
			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		tablePubs.setShowVerticalLines(false);
		tablePubs.setColumnSelectionAllowed(true);
		tablePubs.setAutoCreateRowSorter(true);
		loadPubList();
		
		JScrollPane scrollPanePubs = new JScrollPane(tablePubs);
		GridBagConstraints gbc_scrollPanePubs = new GridBagConstraints();
		gbc_scrollPanePubs.anchor = GridBagConstraints.NORTH;
		gbc_scrollPanePubs.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPanePubs.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePubs.gridx = 0;
		gbc_scrollPanePubs.gridy = 1;
		pPubList.add(scrollPanePubs, gbc_scrollPanePubs);
	
		
		/**
		 * Panel for questions
		 **/ 
		JPanel pQuestionsList = new JPanel();
		pQuestionsList.setBackground(new Color(255,255,255));
		pCardLayoutList.add(pQuestionsList, "pQuestionsList");
		GridBagLayout gbl_pQuestionsList = new GridBagLayout();
		gbl_pQuestionsList.columnWidths = new int[] {0};
		gbl_pQuestionsList.rowHeights = new int[] {0};
		gbl_pQuestionsList.columnWeights = new double[]{1.0};
		gbl_pQuestionsList.rowWeights = new double[]{0.0};
		pQuestionsList.setLayout(gbl_pQuestionsList);
		
		JLabel lblQuestionsList = new JLabel("Fragen");
		lblQuestionsList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuestionsList.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblQuestionsList = new GridBagConstraints();
		gbc_lblQuestionsList.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuestionsList.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuestionsList.gridx = 0;
		gbc_lblQuestionsList.gridy = 0;
		pQuestionsList.add(lblQuestionsList, gbc_lblQuestionsList);
		
		
		tableQuestions = new JTable() {
			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		tableQuestions.setShowVerticalLines(false);
		tableQuestions.setColumnSelectionAllowed(true);
		tableQuestions.setAutoCreateRowSorter(true);
				
		JScrollPane scrollPaneQuestions = new JScrollPane(tableQuestions);
		GridBagConstraints gbc_scrollPaneQuestions = new GridBagConstraints();
		gbc_scrollPaneQuestions.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPaneQuestions.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneQuestions.gridx = 0;
		gbc_scrollPaneQuestions.gridy = 1;
		pQuestionsList.add(scrollPaneQuestions, gbc_scrollPaneQuestions);
		
		
		
		/**
		 * CardLayout for input -> pCardLayoutInput and cardLayout2
		 */
		JPanel pCardLayoutInput = new JPanel();
		GridBagConstraints gbc_pCardLayoutInput = new GridBagConstraints();
		gbc_pCardLayoutInput.insets = new Insets(5, 5, 5, 5);
		gbc_pCardLayoutInput.fill = GridBagConstraints.BOTH;
		gbc_pCardLayoutInput.gridx = 0;
		gbc_pCardLayoutInput.gridy = 1;
		pContent.add(pCardLayoutInput, gbc_pCardLayoutInput);
		pCardLayoutInput.setBackground(new Color(255, 255, 255));
		pCardLayoutInput.setLayout(new CardLayout(0, 0));
		CardLayout cardLayout2 = (CardLayout) pCardLayoutInput.getLayout();
		
		/**
		 * cards for cardLayout2
		 */
		
		/**
		 * Panel clear for start
		 */
		JPanel pClear2 = new JPanel();
		pClear2.setBackground(new Color(255, 255, 255));
		pCardLayoutInput.add(pClear2, "pClear2");
		
		/**
		 * panel for adding a question
		 */
		JPanel pQuestionAdd = new JPanel();
		pCardLayoutInput.add(pQuestionAdd, "pQuestionAdd");
		pQuestionAdd.setBackground(new Color(255, 255, 255));
		GridBagLayout gbl_pQuestionAdd = new GridBagLayout();
		gbl_pQuestionAdd.rowHeights = new int[] {0};
		gbl_pQuestionAdd.columnWidths = new int[] {0};
		gbl_pQuestionAdd.columnWeights = new double[]{0.0, 1.0};
		gbl_pQuestionAdd.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		pQuestionAdd.setLayout(gbl_pQuestionAdd);
		
		JLabel lblQuestionAdd = new JLabel("Frage hinzufügen");
		lblQuestionAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblQuestionAdd = new GridBagConstraints();
		gbc_lblQuestionAdd.anchor = GridBagConstraints.EAST;
		gbc_lblQuestionAdd.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuestionAdd.gridx = 0;
		gbc_lblQuestionAdd.gridy = 0;
		pQuestionAdd.add(lblQuestionAdd, gbc_lblQuestionAdd);
		
		JLabel lblQAQuestion = new JLabel("Frage");
		GridBagConstraints gbc_lblQAQuestion = new GridBagConstraints();
		gbc_lblQAQuestion.anchor = GridBagConstraints.WEST;
		gbc_lblQAQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_lblQAQuestion.gridx = 0;
		gbc_lblQAQuestion.gridy = 1;
		pQuestionAdd.add(lblQAQuestion, gbc_lblQAQuestion);
		
		tfQAQuestion = new JTextField();
		GridBagConstraints gbc_tfQAQuestion = new GridBagConstraints();
		gbc_tfQAQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_tfQAQuestion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQAQuestion.gridx = 1;
		gbc_tfQAQuestion.gridy = 1;
		pQuestionAdd.add(tfQAQuestion, gbc_tfQAQuestion);
		tfQAQuestion.setColumns(10);
		
		JLabel lblQACorrectAnswer = new JLabel("richtige Anwort");
		GridBagConstraints gbc_lblQACorrectAnswer = new GridBagConstraints();
		gbc_lblQACorrectAnswer.anchor = GridBagConstraints.WEST;
		gbc_lblQACorrectAnswer.insets = new Insets(5, 5, 5, 5);
		gbc_lblQACorrectAnswer.gridx = 0;
		gbc_lblQACorrectAnswer.gridy = 2;
		pQuestionAdd.add(lblQACorrectAnswer, gbc_lblQACorrectAnswer);
		
		tfQACorrectAnswer = new JTextField();
		GridBagConstraints gbc_tfQACorrectAnswer = new GridBagConstraints();
		gbc_tfQACorrectAnswer.insets = new Insets(5, 5, 5, 5);
		gbc_tfQACorrectAnswer.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQACorrectAnswer.gridx = 1;
		gbc_tfQACorrectAnswer.gridy = 2;
		pQuestionAdd.add(tfQACorrectAnswer, gbc_tfQACorrectAnswer);
		tfQACorrectAnswer.setColumns(10);
		
		JLabel lblQAWrongAnswer1 = new JLabel("1. falsche Antwort");
		GridBagConstraints gbc_lblQAWrongAnswer1 = new GridBagConstraints();
		gbc_lblQAWrongAnswer1.anchor = GridBagConstraints.WEST;
		gbc_lblQAWrongAnswer1.insets = new Insets(5, 5, 5, 5);
		gbc_lblQAWrongAnswer1.gridx = 0;
		gbc_lblQAWrongAnswer1.gridy = 3;
		pQuestionAdd.add(lblQAWrongAnswer1, gbc_lblQAWrongAnswer1);
		
		tfQAWrongAnswer1 = new JTextField();
		GridBagConstraints gbc_tfQAWrongAnswer1 = new GridBagConstraints();
		gbc_tfQAWrongAnswer1.insets = new Insets(5, 5, 5, 5);
		gbc_tfQAWrongAnswer1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQAWrongAnswer1.gridx = 1;
		gbc_tfQAWrongAnswer1.gridy = 3;
		pQuestionAdd.add(tfQAWrongAnswer1, gbc_tfQAWrongAnswer1);
		tfQAWrongAnswer1.setColumns(10);
		
		JLabel lblWrongAnswer2 = new JLabel("2. falsche Antwort");
		GridBagConstraints gbc_lblWrongAnswer2 = new GridBagConstraints();
		gbc_lblWrongAnswer2.anchor = GridBagConstraints.WEST;
		gbc_lblWrongAnswer2.insets = new Insets(5, 5, 5, 5);
		gbc_lblWrongAnswer2.gridx = 0;
		gbc_lblWrongAnswer2.gridy = 4;
		pQuestionAdd.add(lblWrongAnswer2, gbc_lblWrongAnswer2);
		
		tfQAWrongAnswer2 = new JTextField();
		GridBagConstraints gbc_tfQAWrongAnswer2 = new GridBagConstraints();
		gbc_tfQAWrongAnswer2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQAWrongAnswer2.insets = new Insets(5, 5, 5, 5);
		gbc_tfQAWrongAnswer2.gridx = 1;
		gbc_tfQAWrongAnswer2.gridy = 4;
		pQuestionAdd.add(tfQAWrongAnswer2, gbc_tfQAWrongAnswer2);
		tfQAWrongAnswer2.setColumns(10);
		
		JLabel lblQAWrongAnswer3 = new JLabel("3. falsche Antwort");
		GridBagConstraints gbc_lblQAWrongAnswer3 = new GridBagConstraints();
		gbc_lblQAWrongAnswer3.anchor = GridBagConstraints.WEST;
		gbc_lblQAWrongAnswer3.insets = new Insets(5, 5, 5, 5);
		gbc_lblQAWrongAnswer3.gridx = 0;
		gbc_lblQAWrongAnswer3.gridy = 5;
		pQuestionAdd.add(lblQAWrongAnswer3, gbc_lblQAWrongAnswer3);
		
		tfQAWrongAnswer3 = new JTextField();
		GridBagConstraints gbc_tfQAWrongAnswer3 = new GridBagConstraints();
		gbc_tfQAWrongAnswer3.insets = new Insets(5, 5, 5, 5);
		gbc_tfQAWrongAnswer3.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQAWrongAnswer3.gridx = 1;
		gbc_tfQAWrongAnswer3.gridy = 5;
		pQuestionAdd.add(tfQAWrongAnswer3, gbc_tfQAWrongAnswer3);
		tfQAWrongAnswer3.setColumns(10);
		
		JLabel lblQAExplanation = new JLabel("Erläuterung");
		GridBagConstraints gbc_lblQAExplanation = new GridBagConstraints();
		gbc_lblQAExplanation.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblQAExplanation.insets = new Insets(5, 5, 5, 5);
		gbc_lblQAExplanation.gridx = 0;
		gbc_lblQAExplanation.gridy = 6;
		pQuestionAdd.add(lblQAExplanation, gbc_lblQAExplanation);
		
		tfQAExplanation = new JTextField();
		GridBagConstraints gbc_tfQAExplanation = new GridBagConstraints();
		gbc_tfQAExplanation.insets = new Insets(5, 5, 5, 5);
		gbc_tfQAExplanation.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQAExplanation.gridx = 1;
		gbc_tfQAExplanation.gridy = 6;
		pQuestionAdd.add(tfQAExplanation, gbc_tfQAExplanation);
		tfQAExplanation.setColumns(10);
		
		JButton bQuestionAddNow = new JButton("Frage hinzufügen");
		bQuestionAddNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String qT = tfQAQuestion.getText();
				String cA = tfQACorrectAnswer.getText();
				String wA1 = tfQAWrongAnswer1.getText();
				String wA2 = tfQAWrongAnswer2.getText();
				String wA3 = tfQAWrongAnswer3.getText();
				String ex = tfQAExplanation.getText();
				
				addQuestion(qT, cA, wA1, wA2, wA3, ex);
				
				tfQAQuestion.setText("");
				tfQACorrectAnswer.setText("");
				tfQAWrongAnswer1.setText("");
				tfQAWrongAnswer2.setText("");
				tfQAWrongAnswer3.setText("");
				tfQAExplanation.setText("");
				
				loadQuestionList();
			}
		});
		GridBagConstraints gbc_bQuestionAddNow = new GridBagConstraints();
		gbc_bQuestionAddNow.anchor = GridBagConstraints.EAST;
		gbc_bQuestionAddNow.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionAddNow.gridx = 1;
		gbc_bQuestionAddNow.gridy = 7;
		pQuestionAdd.add(bQuestionAddNow, gbc_bQuestionAddNow);
		
		
		/**
		 * panel for editing question
		 */
		JPanel pQuestionEdit = new JPanel();
		pQuestionEdit.setBackground(new Color(255, 255, 255));
		pCardLayoutInput.add(pQuestionEdit, "pQuestionEdit");
		GridBagLayout gbl_pQuestionEdit = new GridBagLayout();
		gbl_pQuestionEdit.columnWidths = new int[] {0};
		gbl_pQuestionEdit.rowHeights = new int[] {0};
		gbl_pQuestionEdit.columnWeights = new double[]{0.0, 1.0};
		gbl_pQuestionEdit.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		pQuestionEdit.setLayout(gbl_pQuestionEdit);
		
		JLabel lblQuestionEdit = new JLabel("Frage bearbeiten");
		lblQuestionEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblQuestionEdit = new GridBagConstraints();
		gbc_lblQuestionEdit.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuestionEdit.anchor = GridBagConstraints.WEST;
		gbc_lblQuestionEdit.gridx = 0;
		gbc_lblQuestionEdit.gridy = 0;
		pQuestionEdit.add(lblQuestionEdit, gbc_lblQuestionEdit);
		
		JLabel lblQEID = new JLabel("ID");
		GridBagConstraints gbc_lblQEID = new GridBagConstraints();
		gbc_lblQEID.insets = new Insets(5, 5, 5, 5);
		gbc_lblQEID.anchor = GridBagConstraints.WEST;
		gbc_lblQEID.gridx = 0;
		gbc_lblQEID.gridy = 1;
		pQuestionEdit.add(lblQEID, gbc_lblQEID);
				
		JLabel lblQEQuestion = new JLabel("Frage");
		GridBagConstraints gbc_lblQEQuestion = new GridBagConstraints();
		gbc_lblQEQuestion.anchor = GridBagConstraints.WEST;
		gbc_lblQEQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_lblQEQuestion.gridx = 0;
		gbc_lblQEQuestion.gridy = 2;
		pQuestionEdit.add(lblQEQuestion, gbc_lblQEQuestion);
		
		tfQEQuestion = new JTextField();
		tfQEQuestion.setEditable(false);
		tfQEQuestion.setText("");
		GridBagConstraints gbc_tfQEQuestion = new GridBagConstraints();
		gbc_tfQEQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_tfQEQuestion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQEQuestion.gridx = 1;
		gbc_tfQEQuestion.gridy = 2;
		pQuestionEdit.add(tfQEQuestion, gbc_tfQEQuestion);
		tfQEQuestion.setColumns(10);
		
		JLabel lblQECorrectAnswer = new JLabel("richtige Antwort");
		GridBagConstraints gbc_lblQECorrectAnswer = new GridBagConstraints();
		gbc_lblQECorrectAnswer.anchor = GridBagConstraints.WEST;
		gbc_lblQECorrectAnswer.insets = new Insets(5, 5, 5, 5);
		gbc_lblQECorrectAnswer.gridx = 0;
		gbc_lblQECorrectAnswer.gridy = 3;
		pQuestionEdit.add(lblQECorrectAnswer, gbc_lblQECorrectAnswer);
		
		tfQECorrectAnswer = new JTextField();
		tfQECorrectAnswer.setEditable(false);
		GridBagConstraints gbc_tfQECorrectAnswer = new GridBagConstraints();
		gbc_tfQECorrectAnswer.insets = new Insets(5, 5, 5, 5);
		gbc_tfQECorrectAnswer.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQECorrectAnswer.gridx = 1;
		gbc_tfQECorrectAnswer.gridy = 3;
		pQuestionEdit.add(		tfQECorrectAnswer, gbc_tfQECorrectAnswer);
		tfQECorrectAnswer.setColumns(10);
		
		JLabel lblQEWrongAnswer1 = new JLabel("1. falsche Antwort");
		GridBagConstraints gbc_lblQEWrongAnswer1 = new GridBagConstraints();
		gbc_lblQEWrongAnswer1.anchor = GridBagConstraints.WEST;
		gbc_lblQEWrongAnswer1.insets = new Insets(5, 5, 5, 5);
		gbc_lblQEWrongAnswer1.gridx = 0;
		gbc_lblQEWrongAnswer1.gridy = 4;
		pQuestionEdit.add(lblQEWrongAnswer1, gbc_lblQEWrongAnswer1);
		
		tfQEWrongAnswer1 = new JTextField();
		tfQEWrongAnswer1.setEditable(false);
		GridBagConstraints gbc_tfQEWrongAnswer1 = new GridBagConstraints();
		gbc_tfQEWrongAnswer1.insets = new Insets(5, 5, 5, 5);
		gbc_tfQEWrongAnswer1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQEWrongAnswer1.gridx = 1;
		gbc_tfQEWrongAnswer1.gridy = 4;
		pQuestionEdit.add(tfQEWrongAnswer1, gbc_tfQEWrongAnswer1);
		tfQEWrongAnswer1.setColumns(10);
		
		JLabel lblQEWrongAnswer2 = new JLabel("2. falsche Antwort");
		GridBagConstraints gbc_lblQEWrongAnswer2 = new GridBagConstraints();
		gbc_lblQEWrongAnswer2.anchor = GridBagConstraints.WEST;
		gbc_lblQEWrongAnswer2.insets = new Insets(5, 5, 5, 5);
		gbc_lblQEWrongAnswer2.gridx = 0;
		gbc_lblQEWrongAnswer2.gridy = 5;
		pQuestionEdit.add(lblQEWrongAnswer2, gbc_lblQEWrongAnswer2);
		
		tfQEWrongAnswer2 = new JTextField();
		tfQEWrongAnswer2.setEditable(false);
		GridBagConstraints gbc_tfQEWrongAnswer2 = new GridBagConstraints();
		gbc_tfQEWrongAnswer2.insets = new Insets(5, 5, 5, 5);
		gbc_tfQEWrongAnswer2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQEWrongAnswer2.gridx = 1;
		gbc_tfQEWrongAnswer2.gridy = 5;
		pQuestionEdit.add(tfQEWrongAnswer2, gbc_tfQEWrongAnswer2);
		tfQEWrongAnswer2.setColumns(10);
		
		JLabel lblQEWrongAnswer3 = new JLabel("3. falsche Antwort");
		GridBagConstraints gbc_lblQEWrongAnswer3 = new GridBagConstraints();
		gbc_lblQEWrongAnswer3.anchor = GridBagConstraints.WEST;
		gbc_lblQEWrongAnswer3.insets = new Insets(5, 5, 5, 5);
		gbc_lblQEWrongAnswer3.gridx = 0;
		gbc_lblQEWrongAnswer3.gridy = 6;
		pQuestionEdit.add(lblQEWrongAnswer3, gbc_lblQEWrongAnswer3);
		
		tfQEWrongAnswer3 = new JTextField();
		tfQEWrongAnswer3.setEditable(false);
		GridBagConstraints gbc_tfQEWrongAnswer3 = new GridBagConstraints();
		gbc_tfQEWrongAnswer3.insets = new Insets(5, 5, 5, 5);
		gbc_tfQEWrongAnswer3.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQEWrongAnswer3.gridx = 1;
		gbc_tfQEWrongAnswer3.gridy = 6;
		pQuestionEdit.add(tfQEWrongAnswer3, gbc_tfQEWrongAnswer3);
		tfQEWrongAnswer3.setColumns(10);
		
		JLabel lblQEExplanastion = new JLabel("Erläuterung");
		GridBagConstraints gbc_lblQEExplanastion = new GridBagConstraints();
		gbc_lblQEExplanastion.anchor = GridBagConstraints.WEST;
		gbc_lblQEExplanastion.insets = new Insets(5, 5, 5, 5);
		gbc_lblQEExplanastion.gridx = 0;
		gbc_lblQEExplanastion.gridy = 7;
		pQuestionEdit.add(lblQEExplanastion, gbc_lblQEExplanastion);
		
		tfQEExplanation = new JTextField();
		tfQEExplanation.setEditable(false);
		GridBagConstraints gbc_tfQEExplanation = new GridBagConstraints();
		gbc_tfQEExplanation.insets = new Insets(5, 5, 5, 5);
		gbc_tfQEExplanation.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQEExplanation.gridx = 1;
		gbc_tfQEExplanation.gridy = 7;
		pQuestionEdit.add(tfQEExplanation, gbc_tfQEExplanation);
		tfQEExplanation.setColumns(10);
		
		JComboBox<Integer> cbQEID = new JComboBox<Integer>();
		cbQEID.setSelectedIndex(-1);
		cbQEID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long qID = (Long) cbQEID.getSelectedItem();
				/*
				Question q = getQuestion(qID);
				fillFieldsQE(q);
				*/
								
				tfQEQuestion.setEditable(true);
				tfQECorrectAnswer.setEditable(true);
				tfQEWrongAnswer1.setEditable(true);
				tfQEWrongAnswer2.setEditable(true);
				tfQEWrongAnswer3.setEditable(true);
				tfQEExplanation.setEditable(true);	
				
			}
		});
		GridBagConstraints gbc_cbQEID = new GridBagConstraints();
		gbc_cbQEID.insets = new Insets(5, 5, 5, 5);
		gbc_cbQEID.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbQEID.gridx = 1;
		gbc_cbQEID.gridy = 1;
		pQuestionEdit.add(cbQEID, gbc_cbQEID);
		
		JButton bQEDelete = new JButton("Frage löschen");
		bQEDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long questionID = (Long) cbQEQuestionID.getSelectedItem();
				deleteQuestion(questionID);
				
				tfQAQuestion.setText("");
				tfQACorrectAnswer.setText("");
				tfQAWrongAnswer1.setText("");
				tfQAWrongAnswer2.setText("");
				tfQAWrongAnswer3.setText("");
				tfQAExplanation.setText("");
				cbQACorrectAnswer.setSelectedIndex(0);
				
				loadQuestionList();
			}
		});
		GridBagConstraints gbc_bQEDelete = new GridBagConstraints();
		gbc_bQEDelete.anchor = GridBagConstraints.WEST;
		gbc_bQEDelete.insets = new Insets(5, 5, 5, 5);
		gbc_bQEDelete.gridx = 0;
		gbc_bQEDelete.gridy = 8;
		pQuestionEdit.add(bQEDelete, gbc_bQEDelete);
		
		JButton bQuestionSave = new JButton("Speichern");
		bQuestionSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long qID = (Long) cbQEQuestionID.getSelectedItem();
				String q = tfQEQuestion.getText();
				String cA = tfQECorrectAnswer.getText();
				String wA1 = tfQEWrongAnswer1.getText();
				String wA2 = tfQEWrongAnswer2.getText();
				String wA3 = tfQEWrongAnswer3.getText();
				String ex = tfQEExplanation.getText();
				
				changeQuestion(qID, q, cA, wA1, wA2, wA3, ex);
				
				cbQEQuestionID.setSelectedIndex(-1);
				tfQAQuestion.setText("");
				tfQACorrectAnswer.setText("");
				tfQAWrongAnswer1.setText("");
				tfQAWrongAnswer2.setText("");
				tfQAWrongAnswer3.setText("");
				tfQAExplanation.setText("");
				
				loadQuestionList();
			}
		});
		GridBagConstraints gbc_bQuestionSave = new GridBagConstraints();
		gbc_bQuestionSave.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionSave.anchor = GridBagConstraints.EAST;
		gbc_bQuestionSave.gridx = 1;
		gbc_bQuestionSave.gridy = 8;
		pQuestionEdit.add(bQuestionSave, gbc_bQuestionSave);
		
		/**
		 * Panel for editing, deleting and unblock pubs
		 */
		JPanel pPubEdit = new JPanel();
		pPubEdit.setBackground(new Color(255, 255, 255));
		pCardLayoutInput.add(pPubEdit, "pPubEdit");
		GridBagLayout gbl_pPubEdit = new GridBagLayout();
		gbl_pPubEdit.columnWidths = new int[] {0};
		gbl_pPubEdit.rowHeights = new int[] {0};
		gbl_pPubEdit.columnWeights = new double[] {0.0, 1.0};
		gbl_pPubEdit.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		pPubEdit.setLayout(gbl_pPubEdit);
		
		JLabel lblPubEdit = new JLabel("Kneipe bearbeiten");
		lblPubEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblPubEdit = new GridBagConstraints();
		gbc_lblPubEdit.insets = new Insets(5, 5, 5, 5);
		gbc_lblPubEdit.anchor = GridBagConstraints.WEST;
		gbc_lblPubEdit.gridx = 0;
		gbc_lblPubEdit.gridy = 0;
		pPubEdit.add(lblPubEdit, gbc_lblPubEdit);
		
		JComboBox<String> cbPEUnblocking = new JComboBox<String>();
		cbPEUnblocking.setModel(new DefaultComboBoxModel<String>(new String[] {"nein", "ja"}));
		cbPEUnblocking.setSelectedIndex(0);
		GridBagConstraints gbc_cbPEUnblocking = new GridBagConstraints();
		gbc_cbPEUnblocking.insets = new Insets(5, 5, 5, 5);
		gbc_cbPEUnblocking.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbPEUnblocking.gridx = 1;
		gbc_cbPEUnblocking.gridy = 3;
		pPubEdit.add(cbPEUnblocking, gbc_cbPEUnblocking);
		
		tfPEPubName = new JTextField();
		tfPEPubName.setEditable(false);
		GridBagConstraints gbc_tfPEPubName = new GridBagConstraints();
		gbc_tfPEPubName.insets = new Insets(5, 5, 5, 5);
		gbc_tfPEPubName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPEPubName.gridx = 1;
		gbc_tfPEPubName.gridy = 2;
		pPubEdit.add(tfPEPubName, gbc_tfPEPubName);
		tfPEPubName.setColumns(10);
		
		JLabel lblPEPubID = new JLabel("Kneipen-ID");
		GridBagConstraints gbc_lblPEPubID = new GridBagConstraints();
		gbc_lblPEPubID.anchor = GridBagConstraints.WEST;
		gbc_lblPEPubID.insets = new Insets(5, 5, 5, 5);
		gbc_lblPEPubID.gridx = 0;
		gbc_lblPEPubID.gridy = 1;
		pPubEdit.add(lblPEPubID, gbc_lblPEPubID);
		
		JComboBox<Long> cbPEPubID = new JComboBox<Long>();
		cbPEPubID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long pubID = (Long) cbPEPubID.getSelectedItem();
				
				/*
				Pub p = getPub(pubID);
				fillFieldsPE(p);	
				*/			
			}
		});
		GridBagConstraints gbc_cbPEPubID = new GridBagConstraints();
		gbc_cbPEPubID.insets = new Insets(5, 5, 5, 5);
		gbc_cbPEPubID.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbPEPubID.gridx = 1;
		gbc_cbPEPubID.gridy = 1;
		pPubEdit.add(cbPEPubID, gbc_cbPEPubID);
		
		JLabel lblPEPubName = new JLabel("Kneipenname");
		GridBagConstraints gbc_lblPEPubName = new GridBagConstraints();
		gbc_lblPEPubName.anchor = GridBagConstraints.WEST;
		gbc_lblPEPubName.insets = new Insets(5, 5, 5, 5);
		gbc_lblPEPubName.gridx = 0;
		gbc_lblPEPubName.gridy = 2;
		pPubEdit.add(lblPEPubName, gbc_lblPEPubName);
		
		JLabel lblPEUnblocking = new JLabel("Freigabe");
		GridBagConstraints gbc_lblPEUnblocking = new GridBagConstraints();
		gbc_lblPEUnblocking.anchor = GridBagConstraints.WEST;
		gbc_lblPEUnblocking.insets = new Insets(5, 5, 5, 5);
		gbc_lblPEUnblocking.gridx = 0;
		gbc_lblPEUnblocking.gridy = 3;
		pPubEdit.add(lblPEUnblocking, gbc_lblPEUnblocking);
		
		JLabel lblPEUserID = new JLabel("Benutzer-ID");
		GridBagConstraints gbc_lblPEUserID = new GridBagConstraints();
		gbc_lblPEUserID.anchor = GridBagConstraints.WEST;
		gbc_lblPEUserID.insets = new Insets(5, 5, 5, 5);
		gbc_lblPEUserID.gridx = 0;
		gbc_lblPEUserID.gridy = 4;
		pPubEdit.add(lblPEUserID, gbc_lblPEUserID);
		
		tfPEUserID = new JTextField();
		tfPEUserID.setEditable(false);
		GridBagConstraints gbc_tfPEUserID = new GridBagConstraints();
		gbc_tfPEUserID.insets = new Insets(5, 5, 5, 5);
		gbc_tfPEUserID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPEUserID.gridx = 1;
		gbc_tfPEUserID.gridy = 4;
		pPubEdit.add(tfPEUserID, gbc_tfPEUserID);
		tfPEUserID.setColumns(10);
		
		JLabel lblPEAddress = new JLabel("Adresse");
		GridBagConstraints gbc_lblPEAddress = new GridBagConstraints();
		gbc_lblPEAddress.insets = new Insets(5, 5, 5, 5);
		gbc_lblPEAddress.anchor = GridBagConstraints.WEST;
		gbc_lblPEAddress.gridx = 0;
		gbc_lblPEAddress.gridy = 5;
		pPubEdit.add(lblPEAddress, gbc_lblPEAddress);
		
		tfPEAddress = new JTextField();
		tfPEAddress.setEditable(false);
		GridBagConstraints gbc_tfPEAddress = new GridBagConstraints();
		gbc_tfPEAddress.insets = new Insets(5, 5, 5, 5);
		gbc_tfPEAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPEAddress.gridx = 1;
		gbc_tfPEAddress.gridy = 5;
		pPubEdit.add(tfPEAddress, gbc_tfPEAddress);
		tfPEAddress.setColumns(10);
		
		JButton bPESave = new JButton("Speichern");
		bPESave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Long pID = (Long) cbPEPubID.getSelectedItem();
				String pName = tfPEPubName.getText();
				boolean unblocking = false;
				if (cbPEUnblocking.getSelectedIndex() == 1) {
					unblocking = true;
				};
				String uID = tfPEUserID.getText();
				String address = tfPEAddress.getText();
				
				changePub();
				
				cbPEPubID.setSelectedIndex(-1);
				cbPEUnblocking.setSelectedIndex(0);
				tfPEPubName.setText("");
				tfPEUserID.setText("");
				tfPEAddress.setText("");
				
				loadPubList();
			}
		});
		GridBagConstraints gbc_bPESave = new GridBagConstraints();
		gbc_bPESave.anchor = GridBagConstraints.EAST;
		gbc_bPESave.insets = new Insets(5, 5, 5, 5);
		gbc_bPESave.gridx = 1;
		gbc_bPESave.gridy = 6;
		pPubEdit.add(bPESave, gbc_bPESave);
		
	
		
		/**
		 * Initialize menu
		 */
		JPanel pMenu = new JPanel();
		pMenu.setBackground(new Color(255, 255, 255));
		
		GridBagLayout gbl_pMenu = new GridBagLayout();
		gbl_pMenu.columnWidths = new int[] {0};
		gbl_pMenu.rowHeights = new int[] {0};
		gbl_pMenu.columnWeights = new double[]{1.0};
		gbl_pMenu.rowWeights = new double[]{1.0, 1.0, 0.0};
		pMenu.setLayout(gbl_pMenu);
		
		/**
		 * panel for pubs
		 */
		JPanel pMenuPub = new JPanel();
		GridBagConstraints gbc_pMenuPub = new GridBagConstraints();
		gbc_pMenuPub.insets = new Insets(5, 5, 5, 5);
		gbc_pMenuPub.fill = GridBagConstraints.BOTH;
		gbc_pMenuPub.gridx = 0;
		gbc_pMenuPub.gridy = 0;
		pMenu.add(pMenuPub, gbc_pMenuPub);
		GridBagLayout gbl_pMenuPub = new GridBagLayout();
		gbl_pMenuPub.columnWidths = new int[] {0};
		gbl_pMenuPub.rowHeights = new int[] {0};
		gbl_pMenuPub.columnWeights = new double[]{0.0};
		gbl_pMenuPub.rowWeights = new double[]{0.0, 0.0};
		pMenuPub.setLayout(gbl_pMenuPub);
		
		JLabel lblKneipenverwaltung = new JLabel("Kneipenverwaltung");
		lblKneipenverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblKneipenverwaltung.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblKneipenverwaltung = new GridBagConstraints();
		gbc_lblKneipenverwaltung.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKneipenverwaltung.insets = new Insets(5, 5, 5, 5);
		gbc_lblKneipenverwaltung.gridx = 0;
		gbc_lblKneipenverwaltung.gridy = 0;
		pMenuPub.add(lblKneipenverwaltung, gbc_lblKneipenverwaltung);
		
		JButton bPubEdit = new JButton("Kneipe bearbeiten");
		bPubEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadPubList();
				 cardLayout1.show(pCardLayoutList, "pPubList");
				 cardLayout2.show(pCardLayoutInput, "pPubEdit");
			}
		});
		GridBagConstraints gbc_bPubEdit = new GridBagConstraints();
		gbc_bPubEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubEdit.insets = new Insets(5, 5, 5, 5);
		gbc_bPubEdit.gridx = 0;
		gbc_bPubEdit.gridy = 1;
		pMenuPub.add(bPubEdit, gbc_bPubEdit);
		
		/*
		 * panel for Questions
		 */
		JPanel pMenuQuestions = new JPanel();
		GridBagConstraints gbc_pMenuQuestions = new GridBagConstraints();
		gbc_pMenuQuestions.insets = new Insets(5, 5, 5, 5);
		gbc_pMenuQuestions.fill = GridBagConstraints.BOTH;
		gbc_pMenuQuestions.gridx = 0;
		gbc_pMenuQuestions.gridy = 1;
		pMenu.add(pMenuQuestions, gbc_pMenuQuestions);
		
		GridBagLayout gbl_pMenuQuestions = new GridBagLayout();
		gbl_pMenuQuestions.columnWidths = new int[] {0};
		gbl_pMenuQuestions.rowHeights = new int[] {0};
		gbl_pMenuQuestions.columnWeights = new double[]{1.0};
		gbl_pMenuQuestions.rowWeights = new double[]{0.0, 0.0, 0.0};
		pMenuQuestions.setLayout(gbl_pMenuQuestions);
		
		JLabel lblQuizfragen = new JLabel("Quizfragen");
		lblQuizfragen.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuizfragen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblQuizfragen = new GridBagConstraints();
		gbc_lblQuizfragen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuizfragen.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuizfragen.gridx = 0;
		gbc_lblQuizfragen.gridy = 0;
		pMenuQuestions.add(lblQuizfragen, gbc_lblQuizfragen);
		
		JButton bQuestionAdd = new JButton("Frage hinzufügen");
		bQuestionAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadQuestionList();
				 cardLayout1.show(pCardLayoutList, "pQuestionsList");
				 cardLayout2.show(pCardLayoutInput, "pQuestionAdd");
				 
			}
		});
		GridBagConstraints gbc_bQuestionAdd = new GridBagConstraints();
		gbc_bQuestionAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionAdd.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionAdd.gridx = 0;
		gbc_bQuestionAdd.gridy = 1;
		pMenuQuestions.add(bQuestionAdd, gbc_bQuestionAdd);
		
		JButton bQuestionEdit = new JButton("Frage bearbeiten / löschen");
		bQuestionEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadQuestionList();
				cardLayout1.show(pCardLayoutList, "pQuestionsList");
				 cardLayout2.show(pCardLayoutInput, "pQuestionEdit");
			}
		});
		GridBagConstraints gbc_bQuestionEdit = new GridBagConstraints();
		gbc_bQuestionEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionEdit.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionEdit.gridx = 0;
		gbc_bQuestionEdit.gridy = 2;
		pMenuQuestions.add(bQuestionEdit, gbc_bQuestionEdit);
		
		/**
		 * Panel for closingbutton
		 */
		JPanel pMenuButtonClose = new JPanel();
		GridBagConstraints gbc_pMenuButtonClose = new GridBagConstraints();
		gbc_pMenuButtonClose.insets = new Insets(5, 5, 5, 5);
		gbc_pMenuButtonClose.fill = GridBagConstraints.BOTH;
		gbc_pMenuButtonClose.gridx = 0;
		gbc_pMenuButtonClose.gridy = 2;
		pMenu.add(pMenuButtonClose, gbc_pMenuButtonClose);
		
		GridBagLayout gbl_pMenuButtonClose = new GridBagLayout();
		gbl_pMenuButtonClose.columnWidths = new int[] {0};
		gbl_pMenuButtonClose.rowHeights = new int[] {0};
		gbl_pMenuButtonClose.columnWeights = new double[]{1.0};
		gbl_pMenuButtonClose.rowWeights = new double[]{0.0};
		pMenuButtonClose.setLayout(gbl_pMenuButtonClose);
		
		JButton bCloseManagement = new JButton("Verwaltung schließen");
		bCloseManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Fenster schließen und alle Listen speichern
				
			}
		});
		GridBagConstraints gbc_bCloseManagement = new GridBagConstraints();
		gbc_bCloseManagement.fill = GridBagConstraints.HORIZONTAL;
		gbc_bCloseManagement.insets = new Insets(5, 5, 5, 5);
		gbc_bCloseManagement.gridx = 0;
		gbc_bCloseManagement.gridy = 0;
		pMenuButtonClose.add(bCloseManagement, gbc_bCloseManagement);
		
		
		
		frmAdmin.getContentPane().add(pHeader, BorderLayout.NORTH);
		frmAdmin.getContentPane().add(pMenu, BorderLayout.WEST);
		frmAdmin.getContentPane().add(pContent, BorderLayout.CENTER);
		frmAdmin.pack();
		frmAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
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
 
	
	public void getPubListRequest() {
		ArrayList<Pub> pList = new ArrayList<Pub>();
		c.sendObject(new PubList());
	}
	
	public ArrayList<Pub> getPubListFromServer(PubList list) {
		ArrayList<Pub> pList = list.getList();
		return pList;
	}
	
	
	public ArrayList<Question> getQuestionList() {
		ArrayList<Question> qList = new ArrayList<Question>();
		c.sendObject(new QuestionList());
		return qList;		
	}

	
	/*
	public Pub getPub(Long pID) {
		PubList pList = getPubList();
		Pub p;
		for (int i=0; i<pList.size(); i++) {
			if (pList.get(i).getPID.equals(pID)) {
				p = pList.get(i);
			}
		}
		return p;
	}
	*/
	
	/*
	public Question getQuestion(LongqID) {
		QuestionList qList = getQuestionList();
		Question q;
		for (int i=0; i<qList.size(); i++) {
			if (qList.get(i).getQID.equals(qID)) {
				q = qList.get(i);
			}
		}
		return q;
	}
	*/

	public void loadPubList() {
		//ArrayList<Pub> pList = getPubList();
	
		DefaultTableModel pModel = new DefaultTableModel();
		String pHeaders[] = {
				"KneipenID", "Kneipenname", "freigegeben", 
				"BenutzerID", "Benutzername", "Adresse"};
		pModel.setColumnIdentifiers(pHeaders);
		tablePubs.setModel(pModel);
		/*
		for (int r = 0; r < pList.size(); r++) {
			String unblocking = "";
			if (pList.get(r).getUnblocking == false) {
				unblocking = "nein";
			} else {
				unblocking = "ja";
			}
			pModel.addRow(new Object[] {
					pList.get(r).getPubID,
					pList.get(r).getPubName,
					unblocking,
					pList.get(r).getUserID,
					pList.get(r).getUserName,
					pList.get(r).getAddress
			});
		}
		*/
	}
	
	public void loadQuestionList() {
		//ArrayList<Question> qList = getQuestionList();	
		
		DefaultTableModel qModel = new DefaultTableModel();
		String qHeaders[] = {
				"Fragen-ID", "Frage", "richtige Antwort", 
				"1. falsche Antwort", "2. falsche Antwort", "3. falsche Antwort"};
		qModel.setColumnIdentifiers(qHeaders);
		tableQuestions.setModel(qModel);
		/*
		for (int r = 0; r < qList.size(); r++) {
			qModel.addRow(new Object[] {
					qList.get(r).getQuestionID,
					qList.get(r).getQuestion,
					qList.get(r).getCorrectAnswer,
					qList.get(r).getWrongsAnswer1,
					qList.get(r).getWrongAnswer2,
					qList.get(r).getWrongAnswer3
			});
		}
		*/
	}
	
	/*
	 public void fillFieldsPE(Pub p){
		 String pubName = p.getName();
		 boolean unblocking = p.isAllowed();
		 Long userID = p.getOwnerID();
		 String stringUserID = Long.toString(userID);
		 String address = p.getAdresse();
		 
		 tfPEPubName.setText(pubName);
		 if (unblocking == true) {
			 cbPEUnblocking.setSelectedItem(1);
		 } else {
			 cbPEUnblocking.setSelectedIndex(0);
		 }
		 tfPEUserID.setText(stringUserID);
		 tfPEAdress.setText(address);
		 
		 tfPEPubName.setEditable(true);
		 tfPEUserID.setEditable(true);
		 tfPEAdress.setEditable(true);
	 }
	 */
	
	/*
	public void fillFieldsQE(Question q) {
		String qT = q.getQuestion();
		String cA = q.getCorrectAnswer();
		String wA1 = q.getWrongAnswer1();
		String wA2 = q.getwrongAnswer2();
		String wA3 = q.getWrongAnswer3();
		String ex = q.getExplanation();
		
		tfQEQuestion.setText(qT);
		tfQECorrectAnswer.setText(cA);
		tfQEWrongAnswer1.setText(wA1);
		tfQEWrongAnswer2.setText(wA2);
		tfQEWrongAnswer3.setText(wA3);
		tfQEExplanation.setText(ex);
		
		tfQEQuestion.setEditable(true);
		tfQECorrectAnswer.setEditable(true);
		tfQEWrongAnswer1.setEditable(true);
		tfQEWrongAnswer2.setEditable(true);
		tfQEWrongAnswer3.setEditable(true);
		cbQECorrectAnswer.setEditable(true);
		tfQEExplanation.setEditable(true);		
	}
	*/
	 
	public void addQuestion(String qT, String cA, String wA1, String wA2, String wA3, String ex) {
		//Question q = new Question(q, cA, wA1, wA2, wA3, ex);
	}
	
	public void deleteQuestion(Long questionID) {
		
	}
	
	public void changeQuestion(Long qID, String qT, String cA, String wA1, String wA2, String wA3, String ex) {
		
	}
	
	public void changePub() {
		
	}
	
	public static GuiAdmin getInstance(){
		return instance;
	}
	
	public JFrame getFrmAdmin() {
		return frmAdmin;
	}

	public void setFrmAdmin(JFrame frmAdmin) {
		this.frmAdmin = frmAdmin;
	}
}
