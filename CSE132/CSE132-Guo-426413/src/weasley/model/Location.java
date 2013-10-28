package weasley.model;
/** 
 * This class represents a location
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				LabW: Weasley Clock 
 * File:			Location.java
 */
public class Location {

	private String locationName;
	
	/**
	 * Constructor of Location class
	 * @param locationName: store the name of this location
	 */
	public Location(String locationName) {
		this.locationName = locationName;
	}
	
	/**
	 * Get the name of this location
	 * @return
	 */
	public String getName() {
		return locationName;
	}
	
	
	/**
	 * Return the hashcode of this location
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((locationName == null) ? 0 : locationName.hashCode());
		return result;
	}

	/**
	 * Define the rule to compare Location class, which is 
	 * based on the name of that location
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
		if (locationName == null) {
			if (other.locationName != null)
				return false;
		} else if (!locationName.equals(other.locationName))
			return false;
		return true;
	}
	
	/**
	 * Print the name of location when we want to print 
	 * out the class
	 */
	public String toString() {
		return locationName;
	}
}
