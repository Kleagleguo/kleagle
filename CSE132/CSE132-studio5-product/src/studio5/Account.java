package studio5;

/**
 * Modify the methods in this class to use your RWLock
 */

// TA:  Do not use "synchronized" anywhere in this class!
//      Instead, use the RWLock instance associated with this class to
//        control concurrency

public class Account {
	// instance variables:
	private final String owner;
	private int balance;
	
	private final RWLock rwlock;

	// constructor:
	public Account(String owner, int initialBalance) {
		// initialize the instance variables:
		balance = initialBalance;
		this.owner = owner;
		rwlock = new RWLock();
	}
	
	/**
	 * The code below assumes this needs a readers' lock.
	 * Any number of threads should be able to get this Account's
	 * balance at the same time.
	 * @return current balance
	 */
	public int getBalance() {
		
		try {
			rwlock.acquireReadOnly();
			return balance;
		}
		finally { 
			// This "finally" block executes, even though the try block returns!
			// it happens just before the actual return from this method
			rwlock.release();
		}
	}

	/**
	 * What kind of lock(s) does deposit need to operate correctly?
	 *   a) A read lock to get the balance and then a write lock to change it?
	 *   b) A read lock around the whole thing, and in the middle, a write lock to change
	 *        the balance?
	 *   c) A writer's lock around the entire transaction?
	 * You can try things and see which you like better.  Discuss amongst yourselves
	 *   and with a TA.
	 *   
	 * Be sure to complete the questions on this in feedback.txt
	 * 
	 * @param amount
	 */
	public void deposit(int amount) {
		if (amount <= 0)
			throw new IllegalArgumentException("amount must be positive");
		
		
		rwlock.acquireReadWrite();
		int newBal = balance + amount;
		
		sleep();
		balance = newBal;

		rwlock.release();
	
	}

	/**
	 * Same locking issues as deposit.
	 * 
	 * @param amount to be withdrawn
	 * @return true iff the amount could be withdrawn without overdraft
	 */
	public boolean withdraw(int amount) {
		if (amount <= 0)
			throw new IllegalArgumentException("amount must be positive");
		
		rwlock.acquireReadWrite();
		
		if (amount <= balance) {

			int newBal = balance - amount;
			sleep();
			balance = newBal;
			rwlock.release();
			return true;
		} else {
			rwlock.release();
			return false;
		}
	
		
	
	}

	/**
	 * @param amount to transfer
	 * @param dest Account to receive the funds
	 * @return status of withdraw
	 */
	//
	// TA: Do not modify this method
	//     No locks are necessary here, if you do the right
	//       thing for withdraw and deposit.
	//
	public boolean transfer(int amount, Account dest) {
		if (withdraw(amount)) {
			dest.deposit(amount);
			return true;
		} else
			return false;
	}
	
	public String toString() {
		return owner + " with balance " + getBalance();
	}
	
	/**
	 * Sleep for a little bit of time
	 */
	public static void sleep() {
		try {
			Thread.sleep(5);	// 5 milliseconds
		}
		catch (Throwable t) {
			//
			// This should never happen
			//
			throw new Error("This should never happen " + t);

		}
	}

}
