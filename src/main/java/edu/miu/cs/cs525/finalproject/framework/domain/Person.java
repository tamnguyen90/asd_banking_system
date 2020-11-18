package edu.miu.cs.cs525.finalproject.framework.domain;

import java.time.LocalDate;

public class Person extends Customer {
    private LocalDate birthDate;

    public Person(String name, String emailAddress, Address address, LocalDate birthDate) {
        super(name, emailAddress, address);
        this.birthDate = birthDate;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
