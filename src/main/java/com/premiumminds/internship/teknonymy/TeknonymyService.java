package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.Person;

import java.lang.StringBuilder;

class TeknonymyService implements ITeknonymyService {

	/**
	 * Method to get a Person Teknonymy Name
	 * 
	 * @param Person person
	 * @return String which is the Teknonymy Name 
	*/
	public String getTeknonymy(Person person) {
		StringBuilder teknonymy = new StringBuilder();
		if (person.getChildren() == null) {
			return teknonymy.toString();
		}
		Object[] oldestDescendant = getOldestDescendant(person, 0);
		Person oldestPerson = (Person) oldestDescendant[0];
		Integer level = (Integer) oldestDescendant[1];
		while (level > 1) {
			if (level >= 3) {
				teknonymy.append("great-");
			} else if (level == 2) {
				teknonymy.append("grand");
			}
			level -= 1;
		}
		if (person.getSex() == 'M') {
			teknonymy.append("father ");
		} else {
			teknonymy.append("mother ");
		}
		teknonymy.append("of ").append(oldestPerson.getName());
		return teknonymy.toString();
	};

	/**
	 * Method to get a Person's Oldest Descendant
	 * Note: This could be improved by using a pair as the return type instead of Object[] to remove the need for castings
	 * but would need another dependency
	 * @param Person person
	 * @return Object[] which has the person as the first parameter and level as the second
	*/
	public Object[] getOldestDescendant(Person person, int level) {
		if (person.getChildren() == null) {
			return new Object[]{person, level};
		}
		Person oldestPerson = null;
		Integer oldestlevel = 0;
		for (Person child : person.getChildren()) {
			Object[] currentOldestDescendant = getOldestDescendant(child, level + 1);
			Person currentOldestPerson = (Person) currentOldestDescendant[0];
			Integer currentOldestlevel = (Integer) currentOldestDescendant[1];
			if (oldestPerson == null || oldestlevel < currentOldestlevel || (oldestlevel == currentOldestlevel && oldestPerson.getDateOfBirth().isAfter(currentOldestPerson.getDateOfBirth()))) {
				oldestPerson = currentOldestPerson;
				oldestlevel = currentOldestlevel;
			}
		}
		return new Object[]{oldestPerson, oldestlevel};
	}
}
