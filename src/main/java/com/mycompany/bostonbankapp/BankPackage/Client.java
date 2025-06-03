/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bostonbankapp.BankPackage;

import com.mycompany.bostonbankapp.AccountTypes.CreditAccount;
import com.mycompany.bostonbankapp.interfaces.Showable;

/**
 *
 * @author Alex Fernández
 */

// This class represents a Client in the banking system.
// It contains personal information such as RUT, name, last names, address, city, phone number, and a bank account.
public class Client implements Showable {
    private String rut;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String address;
    private String city;
    private String phone;
    private BankAccount bankAccount;

    // Constructor to initialize a Client object with all necessary attributes.
    public Client(String rut, String name,String firstLastName, String secondLastName, String address, String city, String phone, BankAccount bankAccount) {
        this.rut = rut;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.bankAccount = bankAccount;
    }

    // Getters for the Client attributes
    public String getRut() { return rut; }
    public BankAccount getAccount() {return bankAccount;}

    // Getters for the personal information
    @Override
    public String showInfo() {
        String info = "\n--- DATOS DEL CLIENTE ---\n" +
                "RUT: " + rut + "\n" +
                "Nombre: " + name + "\n" +
                "Apellido Paterno: " + firstLastName + "\n" +
                "Apellido Materno: " + secondLastName + "\n" +
                "Dirección: " + address + "\n" +
                "Comuna/Ciudad: " + city + "\n" +
                "Teléfono: " + phone + "\n" +
                "Tipo de Cuenta: " + bankAccount.accountType() + "\n" +
                "Cuenta N°: " + bankAccount.getAccountNumber() + "\n" +
                "Saldo: $" + bankAccount.getBalance();

        if (bankAccount instanceof CreditAccount) {
            CreditAccount creditAccount = (CreditAccount) bankAccount;
            double available = creditAccount.getCreditLimit() + creditAccount.getBalance();
            info += "\nLínea de crédito: $" + creditAccount.getCreditLimit();
            info += "\nCrédito disponible: $" + available;
        }

        return info;
    }
}
