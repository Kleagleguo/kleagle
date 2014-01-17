/** 
 * GUI class, put all the JList, Jtextfield,
 * Jtextarea and JButton 
 *  @author 		Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab2B:KWIC Index 
 * File:			Gui.java
 */
package kwic;
/*TA: DR Grade: 90/100
 * extra credit: no
 * 
 */

/*
 * TA: MM
 * REGRADE: 100/100
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.ListSelectionModel;

import java.awt.Label;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;


public class Gui extends JFrame {

	private JPanel contentPane;
	private KWIC kwic;
	private PropertyChangeSupport pcs;
	private JTextField addPhraseTextField;
	private JTextField addandDeleteTextField;
	private JTextField forceDropWordTextField;
	private JTextField forceDropPhraseTextField;
	private JTextField searchWordTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame and load the file
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(29, 375, 572, 284);
		contentPane.add(scrollPane_2);

		JTextPane textPane = new JTextPane();
		scrollPane_2.setViewportView(textPane);
		
		// load the file and display the whole article
		kwic = new KWIC();
		pcs = kwic.getPCS();
		kwic.addPhrases(new File("fortunes.txt"));
		System.out.println("OK");
		File file = new File("fortunes.txt");
		FileInputStream fis;
		String article = "";
		try {
			fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String currentline;
			while ((currentline = br.readLine()) != null) {
				article += (currentline + " " + "\n");
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		Set wordset = kwic.getWords();
		Object[] labels   = wordset.toArray();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 51, 147, 167);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(220, 51, 381, 173);
		contentPane.add(scrollPane_1);

		addPhraseTextField = new JTextField();
		addPhraseTextField.setBounds(220, 263, 259, 20);
		contentPane.add(addPhraseTextField);
		addPhraseTextField.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(489, 262, 80, 23);
		contentPane.add(btnAdd);

		JButton btnDeleteSelectedPhrase = new JButton("Delete selected phrase");
		btnDeleteSelectedPhrase.setBounds(220, 229, 381, 23);
		contentPane.add(btnDeleteSelectedPhrase);


		JLabel lblAddPhrase = new JLabel("Add a phrase");
		lblAddPhrase.setBounds(228, 250, 80, 14);
		contentPane.add(lblAddPhrase);

		JButton btnDelete = new JButton("Del");
		btnDelete.setBounds(96, 293, 65, 23);
		contentPane.add(btnDelete);

		forceDropWordTextField = new JTextField();
		forceDropWordTextField.setBounds(29, 328, 86, 20);
		contentPane.add(forceDropWordTextField);
		forceDropWordTextField.setColumns(10);

		forceDropPhraseTextField = new JTextField();
		forceDropPhraseTextField.setBounds(146, 328, 191, 20);
		contentPane.add(forceDropPhraseTextField);
		forceDropPhraseTextField.setColumns(10);

		JButton btnForce = new JButton("Force");
		btnForce.setBounds(347, 327, 89, 23);
		contentPane.add(btnForce);

		JButton btnDrop = new JButton("Drop");
		btnDrop.setBounds(446, 327, 89, 23);
		contentPane.add(btnDrop);

		JButton btnAddWord = new JButton("Add");
		btnAddWord.setBounds(29, 293, 65, 23);
		contentPane.add(btnAddWord);

		JLabel lblWord = new JLabel("Word");
		lblWord.setBounds(29, 315, 46, 14);
		contentPane.add(lblWord);

		JLabel lblPhrase = new JLabel("Phrase");
		lblPhrase.setBounds(146, 315, 46, 14);
		contentPane.add(lblPhrase);

		searchWordTextField = new JTextField();
		searchWordTextField.setBounds(29, 229, 65, 20);
		contentPane.add(searchWordTextField);
		searchWordTextField.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(96, 229, 80, 23);
		contentPane.add(btnSearch);

		addandDeleteTextField = new JTextField();
		addandDeleteTextField.setBounds(29, 263, 132, 20);
		contentPane.add(addandDeleteTextField);
		addandDeleteTextField.setColumns(10);


		JList phrase_list = new JList();
		phrase_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(phrase_list);


		JList word_list = new JList(labels);
		scrollPane.setViewportView(word_list);
		word_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		// button action listeners
		btnAdd.addActionListener(new AddPhraseActionListener(word_list, kwic, addPhraseTextField));
		btnDelete.addActionListener(new DeleteWordActionListener(word_list,kwic, addandDeleteTextField));
		btnAddWord.addActionListener(new AddWordActionListener(word_list,kwic, addandDeleteTextField));
		btnDeleteSelectedPhrase.addActionListener(new DeletePhraseActionListener(word_list, phrase_list, kwic));
		btnForce.addActionListener(new ForceAssociation(kwic, forceDropWordTextField, forceDropPhraseTextField, word_list));
		btnDrop.addActionListener(new DropAssociation(kwic, forceDropWordTextField, forceDropPhraseTextField, word_list));
		btnSearch.addActionListener(new SearchActionListener(kwic, searchWordTextField, word_list));

		JLabel lblTitle = new JLabel("KeyWord In Context");
		lblTitle.setFont(new Font("Lucida Handwriting", Font.BOLD, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(171, 0, 223, 29);
		contentPane.add(lblTitle);

		JLabel lblWordsList = new JLabel("Words");
		lblWordsList.setBounds(29, 36, 46, 14);
		contentPane.add(lblWordsList);

		JLabel lblPhraseList = new JLabel("Phrase");
		lblPhraseList.setBounds(221, 36, 46, 14);
		contentPane.add(lblPhraseList);

		textPane.setText(article);
		word_list.getSelectionModel().addListSelectionListener(
				new SharedListSelectionHandler(kwic, word_list, phrase_list, textPane));

		JLabel lblTitle2 = new JLabel("Fortunes");
		lblTitle2.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 26));
		lblTitle2.setBounds(241, 353, 111, 23);
		contentPane.add(lblTitle2);

		JLabel lblAddDelWord = new JLabel("ADD/DEL word");
		lblAddDelWord.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAddDelWord.setBounds(29, 250, 96, 14);
		contentPane.add(lblAddDelWord);

	}
}