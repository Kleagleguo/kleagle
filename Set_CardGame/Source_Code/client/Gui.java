/** 
 * This class represents graphical user interface
 * for the client side of Set Game
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game
 * File:			Gui.java
 */
package lab5.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lab5.model.Card;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;

/*
 * TA: MM
 * Your cards don't display, -15
 * 		That's likely an issue with your file paths
 * Hint function +5
 * 
 * 190/200
 */
public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private Client client;
	private JPanel contentPane;
	private JButton image00;
	private JButton image01;
	private JButton image02;
	private JButton image03;
	private JButton image10;
	private JButton image11;
	private JButton image12;
	private JButton image13;
	private JButton image20;
	private JButton image21;
	private JButton image22;
	private JButton image23;

	private JButton image04;
	private JButton image14;
	private JButton image24;
	private JButton image05;
	private JButton image15;
	private JButton image25;

	private JList playerScoresList;
	private JScrollPane playerScoresListScrollPane;
	private JButton btnStartGame;
	private JTextField console;
	private JTextPane hintConsole;
	private JLabel cover;
	private Map<Integer, Slot> images;
	private Map<Integer, Slot> selectedSlots;

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
	 * Create the frame.
	 */
	public Gui() {
		images = new HashMap<Integer, Slot>();
		selectedSlots = new HashMap<Integer, Slot>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1166, 701);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		image00 = new JButton("");
		image00.setBackground(Color.WHITE);
		image00.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image00.setBounds(48, 21, 114, 155);
		contentPane.add(image00);

		image01 = new JButton("");
		image01.setBackground(Color.WHITE);
		image01.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image01.setBounds(203, 21, 114, 155);
		contentPane.add(image01);

		image02 = new JButton("");
		image02.setBackground(Color.WHITE);
		image02.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image02.setBounds(354, 21, 114, 155);
		contentPane.add(image02);

		image03 = new JButton("");
		image03.setBackground(Color.WHITE);
		image03.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image03.setBounds(505, 21, 114, 155);
		contentPane.add(image03);

		image10 = new JButton("");
		image10.setBackground(Color.WHITE);
		image10.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image10.setBounds(48, 199, 114, 155);
		contentPane.add(image10);

		image11 = new JButton("");
		image11.setBackground(Color.WHITE);
		image11.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image11.setBounds(203, 199, 114, 155);
		contentPane.add(image11);

		image12 = new JButton("");
		image12.setBackground(Color.WHITE);
		image12.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image12.setBounds(354, 199, 114, 155);
		contentPane.add(image12);

		image13 = new JButton("");
		image13.setBackground(Color.WHITE);
		image13.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image13.setBounds(505, 199, 114, 155);
		contentPane.add(image13);

		image20 = new JButton("");
		image20.setBackground(Color.WHITE);
		image20.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image20.setBounds(48, 374, 114, 155);
		contentPane.add(image20);

		image21 = new JButton("");
		image21.setBackground(Color.WHITE);
		image21.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image21.setBounds(203, 375, 114, 155);
		contentPane.add(image21);

		image22 = new JButton("");
		image22.setBackground(Color.WHITE);
		image22.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image22.setBounds(354, 375, 114, 155);
		contentPane.add(image22);

		image23 = new JButton("");
		image23.setBackground(Color.WHITE);
		image23.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image23.setBounds(505, 375, 114, 155);
		contentPane.add(image23);

		btnStartGame = new JButton("Start the game");
		btnStartGame.setFont(new Font("Courier New", Font.BOLD, 13));
		btnStartGame.setForeground(Color.BLACK);
		btnStartGame.setBounds(944, 32, 168, 54);
		contentPane.add(btnStartGame);

		console = new JTextField();
		console.setBackground(Color.BLACK);
		console.setForeground(new Color(50, 205, 50));
		console.setFont(new Font("Courier New", Font.BOLD, 18));
		console.setBounds(48, 557, 718, 90);
		contentPane.add(console);
		console.setColumns(10);
		console.setText("Please click the \"Start the game\" button to join the game.");
			
		image04 = new JButton("");
		image04.setBackground(Color.WHITE);
		image04.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image04.setBounds(652, 21, 114, 155);
		contentPane.add(image04);
		image04.setVisible(false);

		image14 = new JButton("");
		image14.setBackground(Color.WHITE);
		image14.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image14.setBounds(652, 199, 114, 155);
		contentPane.add(image14);
		image14.setVisible(false);

		image24 = new JButton("");
		image24.setBackground(Color.WHITE);
		image24.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image24.setBounds(652, 374, 114, 155);
		contentPane.add(image24);
		image24.setVisible(false);

		image05 = new JButton("");
		image05.setBackground(Color.WHITE);
		image05.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image05.setBounds(799, 21, 114, 155);
		contentPane.add(image05);
		image05.setVisible(false);

		image15 = new JButton("");
		image15.setBackground(Color.WHITE);
		image15.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image15.setBounds(799, 199, 114, 155);
		contentPane.add(image15);
		image15.setVisible(false);

		image25 = new JButton("");
		image25.setBackground(Color.WHITE);
		image25.setIcon(new ImageIcon("images//BlankCard (3).jpg"));
		image25.setBounds(799, 374, 114, 155);
		contentPane.add(image25);
		image25.setVisible(false);

		playerScoresListScrollPane = new JScrollPane();
		playerScoresListScrollPane.setBounds(923, 122, 212, 232);
		contentPane.add(playerScoresListScrollPane);
		
		playerScoresList = new JList();
		playerScoresList.setForeground(new Color(50, 205, 50));
		playerScoresList.setBackground(Color.BLACK);
		playerScoresList.setFont(new Font("Courier New", Font.BOLD, 14));
		playerScoresListScrollPane.setViewportView(playerScoresList);
		
		cover = new JLabel("");
		cover.setIcon(new ImageIcon("images//cover.jpg"));
		cover.setBounds(923, 365, 212, 248);
		contentPane.add(cover);
		
		hintConsole = new JTextPane();
		hintConsole.setFont(new Font("Courier New", Font.BOLD, 20));
		hintConsole.setBackground(Color.BLACK);
		hintConsole.setEditable(false);
		hintConsole.setForeground(new Color(50, 205, 50));
		hintConsole.setBounds(923, 33, 212, 78);
		contentPane.add(hintConsole);
		hintConsole.setVisible(false);
		
		// Connect the images to the slots
		Slot slot;
		slot = new Slot(new Card(), image00);
		images.put(0, slot);
		slot = new Slot(new Card(), image01);
		images.put(1, slot);
		slot = new Slot(new Card(), image02);
		images.put(2, slot);
		slot = new Slot(new Card(), image03);
		images.put(3, slot);

		slot = new Slot(new Card(), image10);
		images.put(4, slot);
		slot = new Slot(new Card(), image11);
		images.put(5, slot);
		slot = new Slot(new Card(), image12);
		images.put(6, slot);
		slot = new Slot(new Card(), image13);
		images.put(7, slot);

		slot = new Slot(new Card(), image20);
		images.put(8, slot);
		slot = new Slot(new Card(), image21);
		images.put(9, slot);
		slot = new Slot(new Card(), image22);
		images.put(10, slot);
		slot = new Slot(new Card(), image23);
		images.put(11, slot);

		slot = new Slot(new Card(), image04);
		images.put(12, slot);
		slot = new Slot(new Card(), image14);
		images.put(13, slot);
		slot = new Slot(new Card(), image24);
		images.put(14, slot);

		slot = new Slot(new Card(), image05);
		images.put(15, slot);
		slot = new Slot(new Card(), image15);
		images.put(16, slot);
		slot = new Slot(new Card(), image25);
		images.put(17, slot);

		client = new Client(selectedSlots, images, console, playerScoresList, this, hintConsole);

		// implement actionListener for all the images, once the image is clicked,
		// the background color should be changed from white to orange.
		image00.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (image00.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image00.setBackground(Color.ORANGE);
						selectedSlots.put(0, images.get(0));
					}
				} else {
					image00.setBackground(Color.WHITE);
					selectedSlots.remove(0);
				}
			}
		});

		image01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image01.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image01.setBackground(Color.ORANGE);
						selectedSlots.put(1, images.get(1));
					}
				} else {
					image01.setBackground(Color.WHITE);
					selectedSlots.remove(1);
				}
			}
		});

		image02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image02.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image02.setBackground(Color.ORANGE);
						selectedSlots.put(2, images.get(2));
					}
				} else {
					image02.setBackground(Color.WHITE);
					selectedSlots.remove(2);
				}
			}
		});

		image03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedSlots.size() < 3) {
					if (image03.getBackground() == Color.WHITE) {
						image03.setBackground(Color.ORANGE);
						selectedSlots.put(3, images.get(3));
					} else {
						image03.setBackground(Color.WHITE);
						selectedSlots.remove(3);
					}
				}
			}
		});

		image10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image10.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image10.setBackground(Color.ORANGE);
						selectedSlots.put(4, images.get(4));
					}
				} else {
					image10.setBackground(Color.WHITE);
					selectedSlots.remove(4);
				}
			}
		});

		image11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image11.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image11.setBackground(Color.ORANGE);
						selectedSlots.put(5, images.get(5));
					}
				} else {
					image11.setBackground(Color.WHITE);
					selectedSlots.remove(5);
				}
			}
		});

		image12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image12.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image12.setBackground(Color.ORANGE);
						selectedSlots.put(6, images.get(6));
					}
				} else {
					image12.setBackground(Color.WHITE);
					selectedSlots.remove(6);
				}
			}
		});

		image13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image13.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image13.setBackground(Color.ORANGE);
						selectedSlots.put(7, images.get(7));
					}
				} else {
					image13.setBackground(Color.WHITE);
					selectedSlots.remove(7);
				}
			}
		});

		image20.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image20.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image20.setBackground(Color.ORANGE);
						selectedSlots.put(8, images.get(8));
					}
				} else {
					image20.setBackground(Color.WHITE);
					selectedSlots.remove(8);
				}
			}
		});

		image21.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image21.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image21.setBackground(Color.ORANGE);
						selectedSlots.put(9, images.get(9));
					}
				} else {
					image21.setBackground(Color.WHITE);
					selectedSlots.remove(9);
				}
			}
		});

		image22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image22.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image22.setBackground(Color.ORANGE);
						selectedSlots.put(10, images.get(10));
					}
				} else {
					image22.setBackground(Color.WHITE);
					selectedSlots.remove(10);
				}
			}
		});

		image23.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image23.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image23.setBackground(Color.ORANGE);
						selectedSlots.put(11, images.get(11));
					}
				} else {
					image23.setBackground(Color.WHITE);
					selectedSlots.remove(11);
				}
			}
		});

		image04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image04.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image04.setBackground(Color.ORANGE);
						selectedSlots.put(12, images.get(12));
					}
				} else {
					image04.setBackground(Color.WHITE);
					selectedSlots.remove(12);
				}
			}
		});

		image05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image05.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image05.setBackground(Color.ORANGE);
						selectedSlots.put(15, images.get(15));
					}
				} else {
					image05.setBackground(Color.WHITE);
					selectedSlots.remove(15);
				}
			}
		});

		image14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image14.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image14.setBackground(Color.ORANGE);
						selectedSlots.put(13, images.get(13));
					}
				} else {
					image14.setBackground(Color.WHITE);
					selectedSlots.remove(13);
				}
			}
		});

		image15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image15.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image15.setBackground(Color.ORANGE);
						selectedSlots.put(16, images.get(16));
					}
				} else {
					image15.setBackground(Color.WHITE);
					selectedSlots.remove(16);
				}
			}
		});

		image24.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image24.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image24.setBackground(Color.ORANGE);
						selectedSlots.put(14, images.get(14));
					}
				} else {
					image24.setBackground(Color.WHITE);
					selectedSlots.remove(14);
				}
			}
		});

		image25.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (image25.getBackground() == Color.WHITE) {
					if (selectedSlots.size() < 3) {
						image25.setBackground(Color.ORANGE);
						selectedSlots.put(17, images.get(17));
					}
				} else {
					image25.setBackground(Color.WHITE);
					selectedSlots.remove(17);
				}
			}
		});

		// implement the actionListener for the start game button.
		// once clicked, the client will call startGame().Besides, the Gui will 
		// keep checking whether 3 cards are selected. If 3 cards are selected, 
		// the client will call checkSet()
		btnStartGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = "";
				while (name.trim().length() == 0) {
					name = JOptionPane.showInputDialog("Please enter your name to play");
				}
				for (int key : selectedSlots.keySet()) {
					selectedSlots.get(key).getImage().setBackground(Color.WHITE);
				}
				selectedSlots.clear();
				client.startGame(images, name);
				btnStartGame.setVisible(false);
				new Thread(client).start();
				new Thread(new Runnable() {
					@Override
					public void run() {
						while (true) {
							Client.sleep(10);
							if (selectedSlots.size() == 3) {
								client.checkSet();
								Client.sleep(500);
								for (int key : selectedSlots.keySet()) {
									selectedSlots.get(key).getImage().setBackground(Color.WHITE);
								}
								selectedSlots.clear();
							}
						}
					}
				}).start();
			}
		});

	}
}
