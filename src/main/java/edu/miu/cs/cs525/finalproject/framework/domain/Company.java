package edu.miu.cs.cs525.finalproject.framework.domain;

public class Company extends Customer {
    private int noOfEmployees;

    public Company(String name, Address address, String emailAddress, int noOfEmployees) {
        super(name, emailAddress, address);
        this.noOfEmployees = noOfEmployees;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }
}
