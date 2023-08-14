package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private double maxAmount;
    @ElementCollection
    @Column(name="payments")// +o payment_id???
    private List<Integer> payments = new ArrayList<>(); // payments = cuotas

    @OneToMany(mappedBy = "loanId", fetch = FetchType.EAGER)
    private Set<ClientLoan> clients = new HashSet<>();

    public Loan() {
    }

    public Loan(String name, double maxAmount, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoan> getClients() {
        return clients;
    }

    public void setClients(Set<ClientLoan> clients) {
        this.clients = clients;
    }

    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setLoanId(this);
        clients.add(clientLoan); //dudo
    }
}


















