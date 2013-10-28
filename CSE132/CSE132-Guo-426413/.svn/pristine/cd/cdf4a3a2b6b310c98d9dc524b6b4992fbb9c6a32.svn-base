/** 
 * This class represents a Person
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				LabW: Weasley Clock 
 * File:			Person.java
 */
package weasley.model;
public class Person {

	private String name;
	
	/**
	 * Constructor of Person class
	 * @param name: store the name of this person
	 */
	public Person(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of the person
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the hashcode of Person class
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Define the rule to compare Persons. Which is comparing
	 * the name of each person, if two people have the same name,
	 * then they are equal
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
	
	/**
	 * Print the name of Person when we want to print 
	 * out the class
	 */
	public String toString() {
		return name;
	}
	
	
}
