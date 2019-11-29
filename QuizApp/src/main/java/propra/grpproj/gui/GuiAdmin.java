package propra.grpproj.gui;

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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GuiAdmin {

	private JFrame frmAdmin;
	private JTable tablePubs, tableQuestions, tableScore;
	private JTextField tfQuestion, tfAnswerA, tfAnswerB, tfAnswerC, tfAnswerD, tfExplanation;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAdmin window = new GuiAdmin();
					window.frmAdmin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiAdmin() {
		

	/**
	 * Initialize the contents of the frame.
	 */
		frmAdmin = new JFrame();
		frmAdmin.getContentPane().setEnabled(false);
		frmAdmin.setTitle("KROMBACHER Kneipenquiz");
		frmAdmin.setForeground(new Color(255, 255, 255));
		frmAdmin.getContentPane().setBackground(Color.WHITE);
		frmAdmin.setBackground(new Color(255, 255, 255));
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		
		/**
		 * Inititalize header
		 */
		JPanel pHeader = new JPanel();
		pHeader.setBackground(new Color(255, 255, 255));
		GridBagLayout gbl_pHeader = new GridBagLayout();
		gbl_pHeader.columnWidths = new int[] {0};
		gbl_pHeader.rowHeights = new int[] {0};
		gbl_pHeader.columnWeights = new double[]{0.0, 0.0};
		gbl_pHeader.rowWeights = new double[]{0.0};
		pHeader.setLayout(gbl_pHeader);
		
		JLabel lblHeader = new JLabel(showLogo());
		lblHeader.setLabelFor(lblHeader);
						
		GridBagConstraints gbc_lblHeader = new GridBagConstraints();
		gbc_lblHeader.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblHeader.gridx = 1;
		gbc_lblHeader.gridy = 0;
		pHeader.add(lblHeader, gbc_lblHeader);
		
		
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
		gbl_pContent.rowWeights = new double[]{1.0, 0.0};
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
		 * Panel for pubs
		 */
		JPanel pPubList = new JPanel();
		pPubList.setBackground(new Color(255, 255, 255));
		pCardLayoutList.add(pPubList, "pPubList");
		GridBagLayout gbl_pPubList = new GridBagLayout();
		gbl_pPubList.columnWidths = new int[] {0};
		gbl_pPubList.rowHeights = new int[] {0};
		gbl_pPubList.columnWeights = new double[]{1.0};
		gbl_pPubList.rowWeights = new double[]{0.0,1.0};
		pPubList.setLayout(gbl_pPubList);
		
		JLabel lblOverviewPubs = new JLabel("Übersicht der registrierten Kneipen");
		lblOverviewPubs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOverviewPubs.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblOverviewPubs = new GridBagConstraints();
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
		tablePubs.setModel(new DefaultTableModel(
			new Object[][] {
				{1, "Golfe", Boolean.TRUE, "bliblablubb"},
				{2, "Willis Eck", Boolean.FALSE, "Adresse"},
				{3, "Dorfkneipe Krombach", Boolean.TRUE, "Hinten links"}
			},
			new String[] {
				"Id", "Name", "freigegeben", "Adresse"
			}
		));
		
		JScrollPane scrollPanePubs = new JScrollPane(tablePubs);
		GridBagConstraints gbc_scrollPanePubs = new GridBagConstraints();
		gbc_scrollPanePubs.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPanePubs.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePubs.gridx = 0;
		gbc_scrollPanePubs.gridy = 1;
		pPubList.add(scrollPanePubs, gbc_scrollPanePubs);
		
		/**
		 * Panel for scoreboard
		 */
		JPanel pScoreboard = new JPanel();
		pScoreboard.setBackground(new Color(255, 255, 255));
		pCardLayoutList.add(pScoreboard, "pScoreboard");
		GridBagLayout gbl_pScoreboard = new GridBagLayout();
		gbl_pScoreboard.columnWidths = new int[] {0};
		gbl_pScoreboard.rowHeights = new int[] {0};
		gbl_pScoreboard.columnWeights = new double[]{1.0};
		gbl_pScoreboard.rowWeights = new double[]{0.0,1.0};
		pScoreboard.setLayout(gbl_pScoreboard);
		
		JLabel lblScoreboard = new JLabel("Scoreboard");
		lblScoreboard.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScoreboard.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblScoreboard = new GridBagConstraints();
		gbc_lblScoreboard.insets = new Insets(5, 5, 5, 5);
		gbc_lblScoreboard.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblScoreboard.gridx = 0;
		gbc_lblScoreboard.gridy = 0;
		pScoreboard.add(lblScoreboard, gbc_lblScoreboard);
		
		tableScore = new JTable(){
			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		tableScore.setShowVerticalLines(false);
		tableScore.setColumnSelectionAllowed(true);
		tableScore.setAutoCreateRowSorter(true);
		tableScore.setModel(new DefaultTableModel(
			new Object[][] {
				{1, "Hannelore Schlafnase", 5522245},
				{2, "Rainer von Meckernicht", 12},
			},
			new String[] {
				"Id", "Name", "Punkte"
			}
		));
		
		JScrollPane scrollPaneScore = new JScrollPane(tableScore);
		GridBagConstraints gbc_scrollPaneScore = new GridBagConstraints();
		gbc_scrollPaneScore.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPaneScore.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneScore.gridx = 0;
		gbc_scrollPaneScore.gridy = 1;
		pScoreboard.add(scrollPaneScore, gbc_scrollPaneScore);
		
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
		gbl_pQuestionsList.rowWeights = new double[]{0.0,1.0};
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
		
		tableQuestions = new JTable(){
			public boolean isCellEditable(int x, int y) {
				return false;
			}
		};
		tableQuestions.setBackground(new Color(255, 255, 255));
		tableQuestions.setShowVerticalLines(false);
		tableQuestions.setColumnSelectionAllowed(true);
		tableQuestions.setAutoCreateRowSorter(true);
		tableQuestions.setModel(new DefaultTableModel(
			new Object[][] {
				{1, "Golfe", Boolean.TRUE, "bliblablubb"},
				{2, "Willis Eck", Boolean.FALSE, "Adresse"}
			},
			new String[] {
				"Id", "Frage", "Antwort A", "Antwort B", "Antwort C", "Antwort D", "Korrekte Antwort", "Erklärung"
			}
		));
		
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
		 * panel for adding a question
		 */
		JPanel pQuestionAdd = new JPanel();
		pCardLayoutInput.add(pQuestionAdd, "pQuestionAdd");
		pQuestionAdd.setBackground(new Color(255, 255, 255));
		GridBagLayout gbl_pQuestionAdd = new GridBagLayout();
		gbl_pQuestionAdd.rowHeights = new int[] {0};
		gbl_pQuestionAdd.columnWidths = new int[] {0};
		gbl_pQuestionAdd.columnWeights = new double[]{0.0, 1.0};
		gbl_pQuestionAdd.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		pQuestionAdd.setLayout(gbl_pQuestionAdd);
		
		JLabel lblQuestionAdd = new JLabel("Frage hinzufügen");
		GridBagConstraints gbc_lblQuestionAdd = new GridBagConstraints();
		gbc_lblQuestionAdd.anchor = GridBagConstraints.EAST;
		gbc_lblQuestionAdd.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuestionAdd.gridx = 0;
		gbc_lblQuestionAdd.gridy = 0;
		pQuestionAdd.add(lblQuestionAdd, gbc_lblQuestionAdd);
		
		JComboBox<String> cbCorrectAnswer = new JComboBox<String>();
		cbCorrectAnswer.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D"}));
		cbCorrectAnswer.setSelectedIndex(0);
		GridBagConstraints gbc_cbCorrectAnswer = new GridBagConstraints();
		gbc_cbCorrectAnswer.insets = new Insets(5, 5, 5, 5);
		gbc_cbCorrectAnswer.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCorrectAnswer.gridx = 1;
		gbc_cbCorrectAnswer.gridy = 6;
		pQuestionAdd.add(cbCorrectAnswer, gbc_cbCorrectAnswer);
		
		JLabel lblQAQuestion = new JLabel("Frage");
		GridBagConstraints gbc_lblQAQuestion = new GridBagConstraints();
		gbc_lblQAQuestion.anchor = GridBagConstraints.WEST;
		gbc_lblQAQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_lblQAQuestion.gridx = 0;
		gbc_lblQAQuestion.gridy = 1;
		pQuestionAdd.add(lblQAQuestion, gbc_lblQAQuestion);
		
		tfQuestion = new JTextField();
		GridBagConstraints gbc_tfQuestion = new GridBagConstraints();
		gbc_tfQuestion.insets = new Insets(5, 5, 5, 5);
		gbc_tfQuestion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfQuestion.gridx = 1;
		gbc_tfQuestion.gridy = 1;
		pQuestionAdd.add(tfQuestion, gbc_tfQuestion);
		tfQuestion.setColumns(10);
		
		JLabel lblAnswerA = new JLabel("Antwort A");
		GridBagConstraints gbc_lblAnswerA = new GridBagConstraints();
		gbc_lblAnswerA.anchor = GridBagConstraints.WEST;
		gbc_lblAnswerA.insets = new Insets(5, 5, 5, 5);
		gbc_lblAnswerA.gridx = 0;
		gbc_lblAnswerA.gridy = 2;
		pQuestionAdd.add(lblAnswerA, gbc_lblAnswerA);
		
		tfAnswerA = new JTextField();
		GridBagConstraints gbc_tfAnswerA = new GridBagConstraints();
		gbc_tfAnswerA.insets = new Insets(5, 5, 5, 5);
		gbc_tfAnswerA.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAnswerA.gridx = 1;
		gbc_tfAnswerA.gridy = 2;
		pQuestionAdd.add(tfAnswerA, gbc_tfAnswerA);
		tfAnswerA.setColumns(10);
		
		JLabel lblAnswerB = new JLabel("Antwort B");
		GridBagConstraints gbc_lblAnswerB = new GridBagConstraints();
		gbc_lblAnswerB.anchor = GridBagConstraints.WEST;
		gbc_lblAnswerB.insets = new Insets(5, 5, 5, 5);
		gbc_lblAnswerB.gridx = 0;
		gbc_lblAnswerB.gridy = 3;
		pQuestionAdd.add(lblAnswerB, gbc_lblAnswerB);
		
		tfAnswerB = new JTextField();
		GridBagConstraints gbc_tfAnswerB = new GridBagConstraints();
		gbc_tfAnswerB.insets = new Insets(5, 5, 5, 5);
		gbc_tfAnswerB.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAnswerB.gridx = 1;
		gbc_tfAnswerB.gridy = 3;
		pQuestionAdd.add(tfAnswerB, gbc_tfAnswerB);
		tfAnswerB.setColumns(10);
		
		JLabel lblAnswerC = new JLabel("Antwort C");
		GridBagConstraints gbc_lblAnswerC = new GridBagConstraints();
		gbc_lblAnswerC.anchor = GridBagConstraints.WEST;
		gbc_lblAnswerC.insets = new Insets(5, 5, 5, 5);
		gbc_lblAnswerC.gridx = 0;
		gbc_lblAnswerC.gridy = 4;
		pQuestionAdd.add(lblAnswerC, gbc_lblAnswerC);
		
		tfAnswerC = new JTextField();
		GridBagConstraints gbc_tfAnswerC = new GridBagConstraints();
		gbc_tfAnswerC.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAnswerC.insets = new Insets(5, 5, 5, 5);
		gbc_tfAnswerC.gridx = 1;
		gbc_tfAnswerC.gridy = 4;
		pQuestionAdd.add(tfAnswerC, gbc_tfAnswerC);
		tfAnswerC.setColumns(10);
		
		JLabel lblAnswerD = new JLabel("Antwort D");
		GridBagConstraints gbc_lblAnswerD = new GridBagConstraints();
		gbc_lblAnswerD.anchor = GridBagConstraints.WEST;
		gbc_lblAnswerD.insets = new Insets(5, 5, 5, 5);
		gbc_lblAnswerD.gridx = 0;
		gbc_lblAnswerD.gridy = 5;
		pQuestionAdd.add(lblAnswerD, gbc_lblAnswerD);
		
		tfAnswerD = new JTextField();
		GridBagConstraints gbc_tfAnswerD = new GridBagConstraints();
		gbc_tfAnswerD.insets = new Insets(5, 5, 5, 5);
		gbc_tfAnswerD.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAnswerD.gridx = 1;
		gbc_tfAnswerD.gridy = 5;
		pQuestionAdd.add(tfAnswerD, gbc_tfAnswerD);
		tfAnswerD.setColumns(10);
		
		JLabel lblCorrectAnswer = new JLabel("richtige Antwort");
		GridBagConstraints gbc_lblCorrectAnswer = new GridBagConstraints();
		gbc_lblCorrectAnswer.anchor = GridBagConstraints.WEST;
		gbc_lblCorrectAnswer.insets = new Insets(5, 5, 5, 5);
		gbc_lblCorrectAnswer.gridx = 0;
		gbc_lblCorrectAnswer.gridy = 6;
		pQuestionAdd.add(lblCorrectAnswer, gbc_lblCorrectAnswer);
		
		JLabel lblExplanation = new JLabel("Erläuterung");
		GridBagConstraints gbc_lblExplanation = new GridBagConstraints();
		gbc_lblExplanation.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblExplanation.insets = new Insets(5, 5, 5, 5);
		gbc_lblExplanation.gridx = 0;
		gbc_lblExplanation.gridy = 7;
		pQuestionAdd.add(lblExplanation, gbc_lblExplanation);
		
		tfExplanation = new JTextField();
		GridBagConstraints gbc_tfExplanation = new GridBagConstraints();
		gbc_tfExplanation.insets = new Insets(5, 5, 5, 5);
		gbc_tfExplanation.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfExplanation.gridx = 1;
		gbc_tfExplanation.gridy = 7;
		pQuestionAdd.add(tfExplanation, gbc_tfExplanation);
		tfExplanation.setColumns(10);
		
		JButton bQuestionAddNow = new JButton("Frage hinzufügen");
		bQuestionAddNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Speichern bzw. Einlesen der Eingaben !!!!
				
				
				tfQuestion.setText("");
				tfAnswerA.setText("");
				tfAnswerB.setText("");
				tfAnswerC.setText("");
				tfAnswerD.setText("");
				tfExplanation.setText("");
				cbCorrectAnswer.setSelectedIndex(0);
			}
		});
		GridBagConstraints gbc_bQuestionAddNow = new GridBagConstraints();
		gbc_bQuestionAddNow.anchor = GridBagConstraints.EAST;
		gbc_bQuestionAddNow.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionAddNow.gridx = 1;
		gbc_bQuestionAddNow.gridy = 8;
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
		gbl_pQuestionEdit.rowWeights = new double[]{0.0, 0.0, 0.0};
		pQuestionEdit.setLayout(gbl_pQuestionEdit);
		
		JLabel lblQuestionEdit = new JLabel("Frage bearbeiten");
		GridBagConstraints gbc_lblQuestionEdit = new GridBagConstraints();
		gbc_lblQuestionEdit.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuestionEdit.anchor = GridBagConstraints.WEST;
		gbc_lblQuestionEdit.gridx = 0;
		gbc_lblQuestionEdit.gridy = 0;
		pQuestionEdit.add(lblQuestionEdit, gbc_lblQuestionEdit);
		
		JLabel lblID = new JLabel("ID");
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.insets = new Insets(5, 5, 5, 5);
		gbc_lblID.anchor = GridBagConstraints.WEST;
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 1;
		pQuestionEdit.add(lblID, gbc_lblID);
		
		JComboBox<Integer> cbIDEdit = new JComboBox<Integer>();
		GridBagConstraints gbc_cbIDEdit = new GridBagConstraints();
		gbc_cbIDEdit.insets = new Insets(5, 5, 5, 5);
		gbc_cbIDEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbIDEdit.gridx = 1;
		gbc_cbIDEdit.gridy = 1;
		pQuestionEdit.add(cbIDEdit, gbc_cbIDEdit);
		
		JLabel lblQuestion = new JLabel("Frage");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		pQuestionEdit.add(lblNewLabel, gbc_lblNewLabel);
		
		
		/**
		 * clear panel
		 */
		JPanel pClear = new JPanel();
		pClear.setBackground(new Color(255, 255, 255));
		pCardLayoutInput.add(pClear, "pClear");
		
		
		
		
		
		
		/**
		 * Initialize menu
		 */
		JPanel pMenu = new JPanel();
		pMenu.setBackground(new Color(255, 255, 255));
		
		GridBagLayout gbl_pMenu = new GridBagLayout();
		gbl_pMenu.columnWidths = new int[] {0};
		gbl_pMenu.rowHeights = new int[] {0};
		gbl_pMenu.columnWeights = new double[]{1.0};
		gbl_pMenu.rowWeights = new double[]{1.0, 1.0, 1.0};
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
		gbl_pMenuPub.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
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
		
		JButton bPubAdd = new JButton("Kneipe hinzufügen");
		bPubAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 cardLayout1.show(pCardLayoutList, "pPubList");
			}
		});
		GridBagConstraints gbc_bPubAdd = new GridBagConstraints();
		gbc_bPubAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubAdd.insets = new Insets(5, 5, 5, 5);
		gbc_bPubAdd.gridx = 0;
		gbc_bPubAdd.gridy = 1;
		pMenuPub.add(bPubAdd, gbc_bPubAdd);
		
		JButton bPubSearch = new JButton("Kneipe suchen");
		bPubSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 cardLayout1.show(pCardLayoutList, "pPubList");
			}
		});
		GridBagConstraints gbc_bPubSearch = new GridBagConstraints();
		gbc_bPubSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubSearch.insets = new Insets(5, 5, 5, 5);
		gbc_bPubSearch.gridx = 0;
		gbc_bPubSearch.gridy = 2;
		pMenuPub.add(bPubSearch, gbc_bPubSearch);
		
		JButton bPubEdit = new JButton("Kneipe bearbeiten");
		bPubEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 cardLayout1.show(pCardLayoutList, "pPubList");
			}
		});
		GridBagConstraints gbc_bPubEdit = new GridBagConstraints();
		gbc_bPubEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubEdit.insets = new Insets(5, 5, 5, 5);
		gbc_bPubEdit.gridx = 0;
		gbc_bPubEdit.gridy = 3;
		pMenuPub.add(bPubEdit, gbc_bPubEdit);
		
		JButton bPubDelete = new JButton("Kneipe löschen");
		bPubDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 cardLayout1.show(pCardLayoutList, "pPubList");
			}
		});
		GridBagConstraints gbc_bPubDelete = new GridBagConstraints();
		gbc_bPubDelete.insets = new Insets(5, 5, 5, 5);
		gbc_bPubDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubDelete.gridx = 0;
		gbc_bPubDelete.gridy = 4;
		pMenuPub.add(bPubDelete, gbc_bPubDelete);
		
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
		gbl_pMenuQuestions.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
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
		
		JButton bQuestionSearch = new JButton("Frage suchen");
		bQuestionSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 cardLayout1.show(pCardLayoutList, "pQuestionsList");
			}
		});
		GridBagConstraints gbc_bQuestionSearch = new GridBagConstraints();
		gbc_bQuestionSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionSearch.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionSearch.gridx = 0;
		gbc_bQuestionSearch.gridy = 2;
		pMenuQuestions.add(bQuestionSearch, gbc_bQuestionSearch);
		
		JButton bQuestionEdit = new JButton("Frage bearbeiten");
		bQuestionEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 cardLayout1.show(pCardLayoutList, "pQuestionsList");
				 cardLayout2.show(pCardLayoutInput, "pQuestionEdit");
			}
		});
		GridBagConstraints gbc_bQuestionEdit = new GridBagConstraints();
		gbc_bQuestionEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionEdit.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionEdit.gridx = 0;
		gbc_bQuestionEdit.gridy = 3;
		pMenuQuestions.add(bQuestionEdit, gbc_bQuestionEdit);
		
		JButton bQuestionDelete = new JButton("Frage löschen");
		bQuestionDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout1.show(pCardLayoutList, "pQuestionsList");
			}
		});
		GridBagConstraints gbc_bQuestionDelete = new GridBagConstraints();
		gbc_bQuestionDelete.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionDelete.gridx = 0;
		gbc_bQuestionDelete.gridy = 4;
		pMenuQuestions.add(bQuestionDelete, gbc_bQuestionDelete);
		
		
		/**
		 * panel for score
		 */
		JPanel pMenuScore = new JPanel();
		GridBagConstraints gbc_pMenuScore = new GridBagConstraints();
		gbc_pMenuScore.insets = new Insets(5, 5, 5, 5);
		gbc_pMenuScore.fill = GridBagConstraints.BOTH;
		gbc_pMenuScore.gridx = 0;
		gbc_pMenuScore.gridy = 2;
		pMenu.add(pMenuScore, gbc_pMenuScore);
		GridBagLayout gbl_pMenuScore = new GridBagLayout();
		gbl_pMenuScore.columnWidths = new int[] {0};
		gbl_pMenuScore.rowHeights = new int[] {0};
		gbl_pMenuScore.columnWeights = new double[]{0.0};
		gbl_pMenuScore.rowWeights = new double[]{0.0, 0.0};
		pMenuScore.setLayout(gbl_pMenuScore);
		
		JLabel lblScoreverwaltung = new JLabel("Scoreverwaltung");
		lblScoreverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreverwaltung.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblScoreverwaltung = new GridBagConstraints();
		gbc_lblScoreverwaltung.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblScoreverwaltung.insets = new Insets(5, 5, 5, 5);
		gbc_lblScoreverwaltung.gridx = 0;
		gbc_lblScoreverwaltung.gridy = 0;
		pMenuScore.add(lblScoreverwaltung, gbc_lblScoreverwaltung);
		
		JButton bScoreShow = new JButton("Score anzeigen");
		bScoreShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout1.show(pCardLayoutList, "pScoreboard");
				cardLayout2.show(pCardLayoutInput, "pClear");
			}
		});
		GridBagConstraints gbc_bScoreShow = new GridBagConstraints();
		gbc_bScoreShow.fill = GridBagConstraints.HORIZONTAL;
		gbc_bScoreShow.insets = new Insets(5, 5, 5, 5);
		gbc_bScoreShow.gridx = 0;
		gbc_bScoreShow.gridy = 1;
		pMenuScore.add(bScoreShow, gbc_bScoreShow);
		
		
		
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


}
