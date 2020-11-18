package edu.miu.cs.cs525.finalproject.framework.domain;

public abstract class Customer {
    private String customerId;
    private String name;
    private String emailAddress;
    private Address address;

    public Customer(String customerId, String name, String emailAddress, Address address) {
        this.customerId = customerId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
