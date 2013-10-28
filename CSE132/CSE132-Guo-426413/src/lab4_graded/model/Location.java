package lab4_graded.model;

/**
 *  Location
 *   Model of location in Weasley Clock.
 *
 *  Author: Ron Cytron
 *  Course: CS132
 *  Lab:    Studio W
 *  
 */

public class Location {
	private String name; // name of the Location
	
	/**
	 * @param name name of the Location
	 */
	public Location(String name) {
		this.name = name;
	}
	
	/**
	 * @return name of the Location
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
		Location other = (Location) obj;
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
