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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JScrollPane;

public class GuiAdmin {

	private JFrame frmAdmin;
	private JTable tablePubs;
	private JTable tableScore;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdmin = new JFrame();
		frmAdmin.setTitle("KROMBACHER Kneipenquiz");
		frmAdmin.setForeground(new Color(255, 255, 255));
		frmAdmin.getContentPane().setBackground(Color.WHITE);
		frmAdmin.setBackground(new Color(255, 255, 255));
		frmAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		frmAdmin.getContentPane().add(loadHeader(), BorderLayout.NORTH);
		frmAdmin.getContentPane().add(loadMenu(), BorderLayout.WEST);
		frmAdmin.getContentPane().add(loadCards(), BorderLayout.CENTER);
		
	}
	
	/**
	 * load panel for header
	 */
	private JPanel loadHeader() {
		JPanel pHeader = new JPanel();
		pHeader.setBackground(new Color(255, 255, 255));
				
		JLabel lblHeader = new JLabel(showLogo());
		lblHeader.setLabelFor(lblHeader);
//		lblHeader.setSize(25,10);
				
		pHeader.add(lblHeader);
//		pHeader.setSize(100, 80);
		
		return pHeader;		
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
	 * load panel for menu
	 */
	private JPanel loadMenu() {
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
		JPanel pMenuKneipe = new JPanel();
		GridBagConstraints gbc_pMenuKneipe = new GridBagConstraints();
		gbc_pMenuKneipe.insets = new Insets(5, 5, 5, 5);
		gbc_pMenuKneipe.fill = GridBagConstraints.BOTH;
		gbc_pMenuKneipe.gridx = 0;
		gbc_pMenuKneipe.gridy = 0;
		pMenu.add(pMenuKneipe, gbc_pMenuKneipe);
		GridBagLayout gbl_pMenuKneipe = new GridBagLayout();
		gbl_pMenuKneipe.columnWidths = new int[] {0};
		gbl_pMenuKneipe.rowHeights = new int[] {0};
		gbl_pMenuKneipe.columnWeights = new double[]{0.0};
		gbl_pMenuKneipe.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		pMenuKneipe.setLayout(gbl_pMenuKneipe);
		
		JLabel lblKneipenverwaltung = new JLabel("Kneipenverwaltung");
		lblKneipenverwaltung.setHorizontalAlignment(SwingConstants.CENTER);
		lblKneipenverwaltung.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblKneipenverwaltung = new GridBagConstraints();
		gbc_lblKneipenverwaltung.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKneipenverwaltung.insets = new Insets(5, 5, 5, 5);
		gbc_lblKneipenverwaltung.gridx = 0;
		gbc_lblKneipenverwaltung.gridy = 0;
		pMenuKneipe.add(lblKneipenverwaltung, gbc_lblKneipenverwaltung);
		
		JButton bPubAdd = new JButton("Kneipe hinzufügen");
		GridBagConstraints gbc_bPubAdd = new GridBagConstraints();
		gbc_bPubAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubAdd.insets = new Insets(5, 5, 5, 5);
		gbc_bPubAdd.gridx = 0;
		gbc_bPubAdd.gridy = 1;
		pMenuKneipe.add(bPubAdd, gbc_bPubAdd);
		
		JButton bPubSearch = new JButton("Kneipe suchen");
		GridBagConstraints gbc_bPubSearch = new GridBagConstraints();
		gbc_bPubSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubSearch.insets = new Insets(5, 5, 5, 5);
		gbc_bPubSearch.gridx = 0;
		gbc_bPubSearch.gridy = 2;
		pMenuKneipe.add(bPubSearch, gbc_bPubSearch);
		
		JButton bPubEdit = new JButton("Kneipe bearbeiten");
		GridBagConstraints gbc_bPubEdit = new GridBagConstraints();
		gbc_bPubEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubEdit.insets = new Insets(5, 5, 5, 5);
		gbc_bPubEdit.gridx = 0;
		gbc_bPubEdit.gridy = 3;
		pMenuKneipe.add(bPubEdit, gbc_bPubEdit);
		
		JButton bPubDelete = new JButton("Kneipe löschen");
		GridBagConstraints gbc_bPubDelete = new GridBagConstraints();
		gbc_bPubDelete.insets = new Insets(5, 5, 5, 5);
		gbc_bPubDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPubDelete.gridx = 0;
		gbc_bPubDelete.gridy = 4;
		pMenuKneipe.add(bPubDelete, gbc_bPubDelete);
		
		/*
		 * panel for Questions
		 */
		JPanel pMenuQuizfragen = new JPanel();
		GridBagConstraints gbc_pMenuQuizfragen = new GridBagConstraints();
		gbc_pMenuQuizfragen.insets = new Insets(5, 5, 5, 5);
		gbc_pMenuQuizfragen.fill = GridBagConstraints.BOTH;
		gbc_pMenuQuizfragen.gridx = 0;
		gbc_pMenuQuizfragen.gridy = 1;
		pMenu.add(pMenuQuizfragen, gbc_pMenuQuizfragen);
		
		GridBagLayout gbl_pMenuQuizfragen = new GridBagLayout();
		gbl_pMenuQuizfragen.columnWidths = new int[] {0};
		gbl_pMenuQuizfragen.rowHeights = new int[] {0};
		gbl_pMenuQuizfragen.columnWeights = new double[]{1.0};
		gbl_pMenuQuizfragen.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		pMenuQuizfragen.setLayout(gbl_pMenuQuizfragen);
		
		JLabel lblQuizfragen = new JLabel("Quizfragen");
		lblQuizfragen.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuizfragen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblQuizfragen = new GridBagConstraints();
		gbc_lblQuizfragen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuizfragen.insets = new Insets(5, 5, 5, 5);
		gbc_lblQuizfragen.gridx = 0;
		gbc_lblQuizfragen.gridy = 0;
		pMenuQuizfragen.add(lblQuizfragen, gbc_lblQuizfragen);
		
		JButton bQuestionAdd = new JButton("Frage hinzufügen");
		GridBagConstraints gbc_bQuestionAdd = new GridBagConstraints();
		gbc_bQuestionAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionAdd.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionAdd.gridx = 0;
		gbc_bQuestionAdd.gridy = 1;
		pMenuQuizfragen.add(bQuestionAdd, gbc_bQuestionAdd);
		
		JButton bQuestionSearch = new JButton("Frage suchen");
		GridBagConstraints gbc_bQuestionSearch = new GridBagConstraints();
		gbc_bQuestionSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionSearch.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionSearch.gridx = 0;
		gbc_bQuestionSearch.gridy = 2;
		pMenuQuizfragen.add(bQuestionSearch, gbc_bQuestionSearch);
		
		JButton bQuestionEdit = new JButton("Frage bearbeiten");
		GridBagConstraints gbc_bQuestionEdit = new GridBagConstraints();
		gbc_bQuestionEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionEdit.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionEdit.gridx = 0;
		gbc_bQuestionEdit.gridy = 3;
		pMenuQuizfragen.add(bQuestionEdit, gbc_bQuestionEdit);
		
		JButton bQuestionDelete = new JButton("Frage löschen");
		GridBagConstraints gbc_bQuestionDelete = new GridBagConstraints();
		gbc_bQuestionDelete.insets = new Insets(5, 5, 5, 5);
		gbc_bQuestionDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_bQuestionDelete.gridx = 0;
		gbc_bQuestionDelete.gridy = 4;
		pMenuQuizfragen.add(bQuestionDelete, gbc_bQuestionDelete);
		
		/**
		 * panel for score
		 */
		JPanel pMenuScore = new JPanel();
		GridBagConstraints gbc_pMenuScore = new GridBagConstraints();
		gbc_pMenuScore.insets = new Insets(5, 5, 0, 5);
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
		
		JButton bScoreShow = new JButton("Score anzeigen / aktualisieren");
		GridBagConstraints gbc_bScoreShow = new GridBagConstraints();
		gbc_bScoreShow.fill = GridBagConstraints.HORIZONTAL;
		gbc_bScoreShow.insets = new Insets(5, 5, 5, 5);
		gbc_bScoreShow.gridx = 0;
		gbc_bScoreShow.gridy = 1;
		pMenuScore.add(bScoreShow, gbc_bScoreShow);
		
		return pMenu;		
	}
	
	/**
	 * load CardLayoutContent
	 */
	private JPanel loadCards() {
		JPanel pCardLayout = new JPanel();
		pCardLayout.setBackground(new Color(255, 255, 255));
		pCardLayout.setLayout(new CardLayout(0, 0));
		
		JPanel pPubList = new JPanel();
		pPubList.setBackground(new Color(255, 255, 255));
		pCardLayout.add(pPubList, "pPublist");
		GridBagLayout gbl_pPubList = new GridBagLayout();
		gbl_pPubList.columnWidths = new int[] {0};
		gbl_pPubList.rowHeights = new int[] {0};
		gbl_pPubList.columnWeights = new double[]{1.0};
		gbl_pPubList.rowWeights = new double[]{0.0, 1.0};
		pPubList.setLayout(gbl_pPubList);
		
		JLabel lblOverviewPubs = new JLabel("Übersicht der registrierten Kneipen");
		lblOverviewPubs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOverviewPubs.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblOverviewPubs = new GridBagConstraints();
		gbc_lblOverviewPubs.insets = new Insets(5, 5, 5, 0);
		gbc_lblOverviewPubs.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOverviewPubs.gridx = 0;
		gbc_lblOverviewPubs.gridy = 0;
		pPubList.add(lblOverviewPubs, gbc_lblOverviewPubs);
		
		tablePubs = new JTable();
		tablePubs.setShowVerticalLines(false);
		tablePubs.setColumnSelectionAllowed(true);
		tablePubs.setModel(new DefaultTableModel(
			new Object[][] {
				{1, "Golfe", Boolean.TRUE, "bliblablubb"},
				{2, "Willis Eck", Boolean.FALSE, "Adresse"},
			},
			new String[] {
				"Id", "Name", "freigegeben", "Adresse"
			}
		));
/*		GridBagConstraints gbc_tablePubs = new GridBagConstraints();
		gbc_tablePubs.insets = new Insets(5, 5, 5, 0);
		gbc_tablePubs.fill = GridBagConstraints.BOTH;
		gbc_tablePubs.gridx = 0;
		gbc_tablePubs.gridy = 1;
		
		pPubList.add(tablePubs, gbc_tablePubs);
		*/
		
		JScrollPane scrollPanePubs = new JScrollPane(tablePubs);
		GridBagConstraints gbc_scrollPanePubs = new GridBagConstraints();
		gbc_scrollPanePubs.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPanePubs.fill = GridBagConstraints.BOTH;
		gbc_scrollPanePubs.gridx = 0;
		gbc_scrollPanePubs.gridy = 1;
		pPubList.add(scrollPanePubs, gbc_scrollPanePubs);
		
		JPanel pScoreboard = new JPanel();
		pScoreboard.setBackground(new Color(255, 255, 255));
		pCardLayout.add(pScoreboard, "pScoreboard");
		GridBagLayout gbl_pScoreboard = new GridBagLayout();
		gbl_pScoreboard.columnWidths = new int[] {0};
		gbl_pScoreboard.rowHeights = new int[] {0};
		gbl_pScoreboard.columnWeights = new double[]{1.0};
		gbl_pScoreboard.rowWeights = new double[]{0.0, 1.0};
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
		
		tableScore = new JTable();
		GridBagConstraints gbc_tableScore = new GridBagConstraints();
		gbc_tableScore.insets = new Insets(5, 5, 5, 5);
		gbc_tableScore.fill = GridBagConstraints.BOTH;
		gbc_tableScore.gridx = 0;
		gbc_tableScore.gridy = 1;
		pScoreboard.add(tableScore, gbc_tableScore);
		
		return pCardLayout;
	}
	

}
