/** 
 * This class represents the game controller which
 * guarantees that the game is running correctly
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game 
 * File:			GameController.java
 */

package lab5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import lab5.model.BlockingQueue;
import lab5.model.Card;
import lab5.model.Player;
import lab5.model.SetGame;
import lab5.view.PlayerHandler;

public class GameController extends Thread {

	private BlockingQueue<Runnable> runqueue;
	private SetGame game;
	private Set<PlayerHandler> playerHandlers;
	private Map<Integer, Card> currentCards;
	private byte playerCallingSet;
	private ArrayList<Integer> setCardsNums;
	private boolean gameOver;
	private byte bestPlayerNum;
	private static int numCardsOnTable = 12;
	private Timer hintTimer;
	private Timer gameTimer;
	private boolean firstHintGiven;
	private boolean secondHintGiven;
	public static final int DELAY = 1000; // nominal sleep delay (in ms)

	/**
	 * Constructor of GameController
	 */
	public GameController() {
		runqueue = new BlockingQueue<Runnable>(10);
		game = new SetGame();
		playerHandlers = new HashSet<PlayerHandler>();
		currentCards = new HashMap<Integer, Card>();
		playerCallingSet = -1;
		setCardsNums = new ArrayList<Integer>();
		gameOver = false;
		bestPlayerNum = 1;
		firstHintGiven = false;
		secondHintGiven = false;
	}

	/**
	 * in the while(true) loop, it will keep running
	 * the runnables in the blockingqueue in order
	 */
	public void run() {
		new Thread(new Server(this)).start();
		while (true) {
			Runnable r = runqueue.dequeue();
			r.run();
		}
	}

	/**
	 * get the numbers of the set cards
	 * @return
	 */
	public ArrayList<Integer> getSetCardsNums() {
		return setCardsNums;
	}

	/**
	 * denote the id of the player who is calling a set
	 * @return
	 */
	public byte getPlayeCallingSet() {
		return playerCallingSet;
	}

	/**
	 * set the id of the current player who is calling a set
	 * @param id
	 */
	public void setPlayerCallingSet(byte id) {
		playerCallingSet = id;
	}

	/**
	 * return the set game object
	 * @return
	 */
	public SetGame getGame() {
		return game;
	}

	/**
	 * add a Runnable r into the blocking queue
	 * @param r
	 */
	public void addRunnable(Runnable r) {
		runqueue.enqueue(r);
	}

	/**
	 * send a message to the java console
	 * @param s
	 */
	public void message(final String s) {
		addRunnable(
				new Runnable() {
					public void run() {
						System.out.println("message: " + s);
					}
				}
				);
	}

	/**
	 * add a player who has just joined the game, also add the playerhandler
	 * for that player
	 * @param playerHandler
	 * @param player
	 */
	public void addPlayer(final PlayerHandler playerHandler, final String player) {
		addRunnable(new Runnable() {
			@Override
			public void run() {
				playerHandlers.add(playerHandler);
				game.addPlayer(player);
				message("Player joined: " + playerHandler);
				for (PlayerHandler p : playerHandlers) {
					p.getSos().gamePlayerUpdateMessage(game.getPlayers().size(), game.getPlayers());
				}
			}
		});
	}

	/**
	 * start the set game when desired number of players have
	 * joined the game. Inside this method, a gameTimer will
	 * be set up to end the game after 10 minutes passed. 
	 * Besides, a hint timer will also be set up to schedule
	 * the time when a hint should be given
	 */
	public void startSetGame() {
		addRunnable(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < numCardsOnTable; i++) {
					Card card = getGame().dealCard();
					currentCards.put(i, card);
				}
				for (PlayerHandler p : playerHandlers) {
					p.getSos().gameFieldUpdateMessage((byte)game.getCards().size(), (byte)numCardsOnTable, currentCards);
				}
				gameTimer = new Timer();
				gameTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						addRunnable(new Runnable() {
							@Override
							public void run() {
								gameOver = true;
								short score = 0;
								for (Player p : game.getPlayers()) {
									if (p.getScore() > score) {
										bestPlayerNum = (byte) p.getID();
										score = (short) p.getScore();
									}
								}
								for (PlayerHandler p : playerHandlers) {
									p.endGame();
									p.getSos().gameOverMessage(bestPlayerNum);
								}
							}
						});
					}
				}, 10 * 60 * 1000);
				hintTimer = new Timer();
				hintTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						giveHint();
					}
				}, 60 * 1000);
				hintTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						giveHint();
					}
				}, 2 * 60 * 1000);
			}
		});
	}

	/**
	 * respond the specific player when he/she asks for set checking
	 * @param num
	 */
	public void handlePlayerCallingSet(final byte num) {
		addRunnable(new Runnable() {
			@Override
			public void run() {
				setPlayerCallingSet(num);
				for (PlayerHandler p : playerHandlers) {
					if (p.getPlayerId() == num) {
						p.getSos().selectCardsMessage(num);
						break;
					}
				}
			}
		});
	}

	/**
	 * identify a 3-card set given by the specific player,
	 * if that set is a valid that, then the hint timer will refresh,
	 * then the server will update the cards on the player tables
	 * and update the player scores
	 * @param num
	 * @param cardsNum
	 */
	public void identifySet(final byte num, final int[] cardsNum) {
		addRunnable(new Runnable() {
			@Override
			public void run() {
				System.out.println("identifying........");
				setCardsNums.add(cardsNum[0]);
				setCardsNums.add(cardsNum[1]);
				setCardsNums.add(cardsNum[2]);
				Card card1 = currentCards.get(cardsNum[0]);
				System.out.println(card1);
				Card card2 = currentCards.get(cardsNum[1]);
				System.out.println(card2);
				Card card3 = currentCards.get(cardsNum[2]);
				System.out.println(card3);
				if ( ( card1.getNumber() == card2.getNumber() 
						&& card1.getNumber() == card3.getNumber() 
						&& card2.getNumber() == card3.getNumber() )
						|| 
						( card1.getNumber() != card2.getNumber()
						&& card1.getNumber() != card3.getNumber()
						&& card2.getNumber() != card3.getNumber() ) )
					if ( ( card1.getShading() == card2.getShading() 
					&& card1.getShading() == card3.getShading()
					&& card2.getShading() == card3.getShading() )
					||
					( card1.getShading() != card2.getShading()
					&& card1.getShading() != card3.getShading()
					&& card2.getShading() != card3.getShading() ) ) 
						if ( ( card1.getColor() == card2.getColor()
						&& card1.getColor() == card3.getColor()
						&& card2.getColor() == card3.getColor() )
						|| 
						( card1.getColor() != card2.getColor()
						&& card1.getColor() != card3.getColor()
						&& card2.getColor() != card3.getColor() ) )
							if ( ( card1.getShape() == card2.getShape()
							&& card1.getShape() == card3.getShape()
							&& card2.getShape() == card3.getShape() ) 
							|| 
							( card1.getShape() != card2.getShape()
							&& card1.getShape() != card3.getShape()
							&& card2.getShape() != card3.getShape() ) ) {

								hintTimer.cancel();
								hintTimer = new Timer();
								if (!firstHintGiven) {
									hintTimer.schedule(new TimerTask() {
										@Override
										public void run() {
											giveHint();
										}
									}, 30 * 1000);
									hintTimer.schedule(new TimerTask() {
										@Override
										public void run() {
											giveHint();
										}
									}, 60 * 1000);
								} else {
									if (!secondHintGiven) {
										hintTimer.schedule(new TimerTask() {
											@Override
											public void run() {
												giveHint();
											}
										}, 60 * 1000);
									}
								}
								for (int i = 0; i < 3; i++) {
									Card card = getGame().dealCard();
									currentCards.put(setCardsNums.get(i), card);
								}

								for (Player p : game.getPlayers()) {
									if (p.getID() == num) {
										p.setScore(p.getScore() + 10);
										break;
									}
								}

								if (game.getCards().size() == 0) {
									gameOver = true;
									short score = 0;
									for (Player p : game.getPlayers()) {
										if (p.getScore() > score) {
											bestPlayerNum = (byte) p.getID();
											score = (short) p.getScore();
										}
									}
								}

								for (PlayerHandler p : playerHandlers) {
									p.getSos().validSet(num);
									p.getSos().gameFieldUpdateMessage((byte)game.getCards().size(), (byte)numCardsOnTable, currentCards);
									p.getSos().gameScoreUpdate(game.getPlayers());
									if (gameOver) {
										p.endGame();
										p.getSos().gameOverMessage(bestPlayerNum);
									}
								}
								setPlayerCallingSet((byte) -1);
								setCardsNums.clear();
								return;
							}
				for (PlayerHandler p : playerHandlers) {
					p.getSos().invalidSet(num);
				}	
				setPlayerCallingSet((byte) -1);
				setCardsNums.clear();
			}
		});
	}

	/**
	 * when the hint time is coming, the server will give all the players
	 * a hint by displaying another column of cards
	 */
	public void giveHint() {
		addRunnable(new Runnable() {
			@Override
			public void run() {
				if (!firstHintGiven) {
					firstHintGiven = true;
					numCardsOnTable += 3;
					for (int i = 12; i < 15; i++) {
						Card hintCard = game.dealCard();
						currentCards.put(i, hintCard);
					}
					for (PlayerHandler p : playerHandlers) {
						p.getSos().gameFieldUpdateMessage((byte)game.getCards().size(), (byte)numCardsOnTable, currentCards);
						p.getSos().hintMessage((byte) 1);
					}
				} else {
					if (!secondHintGiven) {
						secondHintGiven = true;
						numCardsOnTable += 3;
						for (int i = 15; i < 18; i++) {
							Card hintCard = game.dealCard();
							currentCards.put(i, hintCard);
						}
						for (PlayerHandler p : playerHandlers) {
							p.getSos().gameFieldUpdateMessage((byte)game.getCards().size(), (byte)numCardsOnTable, currentCards);
							p.getSos().hintMessage((byte) 2);
						}
					}
				}
			}
		});
	}


	/**
	 * The sleep that is observed nominally.
	 * Change DELAY to speed things up or slow things down.
	 */
	public static void sleep() {
		sleep(DELAY);
	}

	/**
	 * Delay for an arbitrary amount of time.
	 * @param ms delay amount (in ms)
	 */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		}
		catch (Exception e) {
			throw new Error("ServerMain.sleep: should not happen " + e);
		}
	}

	/**
	 * start the server
	 * @param args
	 */
	public static void main(String[] args) {
		GameController game = new GameController();
		game.start();
	}
}
