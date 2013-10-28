package lab4_graded.view;

/**
 *  Logger
 *   Logger view in Weasley Clock.
 *
 *  Author: Ron Cytron, Roger Chamberlain
 *  Course: CS132
 *  Lab:    Studio W
 *  
 */

import lab4.model.Person;
import lab4.model.Weasley;

public class Logger {
	private Observer logger;
	
	/**
	 * Logger watches all activity.
	 * @param model Weasley clock
	 */
	public Logger(Weasley model) {
		logger = new Observer("Logger");
		for (Person p : model.getPeople()) {
			logger.addObsession(p);
		}
	}

}
