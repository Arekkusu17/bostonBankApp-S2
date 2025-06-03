/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bostonbankapp.BankPackage;

/**
 *
 * @author Alex Fernández
 */


// This class represents a bank account with basic functionalities such as deposit and withdrawal.
// It includes methods to get the account number and balance, as well as to deposit and withdraw funds.
public abstract class BankAccount {
    protected int accountNumber;
    protected int balance;

    // Base Constructor to initialize a BankAccount object with an account number and an initial balance.
    public BankAccount(int accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Sobrecarga de constructor
    public BankAccount(int accountNumber) {
        this(accountNumber, 0);
    }

    // GETTER Y SETTERS
    public int getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("El saldo actual de la cuenta es: $" + balance);
        } else {
            System.out.println("El monto a depositar debe ser positivo.");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("El saldo actual de la cuenta es: $" + balance);
        } else if (amount > balance) {
            System.out.println("Fondos insuficientes para girar.");
        } else {
            System.out.println("El monto a girar debe ser positivo.");
        }
    }

    public abstract String accountType();
}
