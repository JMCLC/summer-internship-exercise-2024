package com.premiumminds.internship.teknonymy;

import java.time.LocalDateTime;

public record Person(String name,Character sex, Person[] children, LocalDateTime dateOfBirth) {

    public String getName() { return name; }

    public Character getSex() { return sex; }

    public Person[] getChildren() { return children; }

    public LocalDateTime getDateOfBirth() { return dateOfBirth; }
}
