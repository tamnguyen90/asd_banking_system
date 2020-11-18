package edu.miu.cs.cs525.finalproject.framework.domain;

public class Company extends Customer {
    private int noOfEmployees;

    public Company(String companyId, String name, Address address, String emailAddress, int noOfEmployees) {
        super(companyId, name, emailAddress, address);
        this.noOfEmployees = noOfEmployees;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }
}
