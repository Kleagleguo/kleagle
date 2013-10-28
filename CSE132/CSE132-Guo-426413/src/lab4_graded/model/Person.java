package lab4_graded.model;

/**
 *  Person
 *   Model of person in Weasley Clock.
 *
 *  Author: Ron Cytron
 *  Course: CS132
 *  Lab:    Studio W
 *  
 */

public class Person extends Actor {
	private String name; // name of the Person
	
	/**
	 * @param name name of the Person
	 */
	public Person(String name) {
		super(name);
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see weasley.model.Actor#move(weasley.model.Location)
	 */
	public void move(Location loc) {
		status(loc.getName());
	}
	
	/**
	 * @return name of the Person
	 */
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}
}
