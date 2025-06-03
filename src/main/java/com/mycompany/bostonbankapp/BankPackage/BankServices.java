/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bostonbankapp.BankPackage;
import com.mycompany.bostonbankapp.AccountTypes.CheckingAccount;
import com.mycompany.bostonbankapp.AccountTypes.CreditAccount;
import com.mycompany.bostonbankapp.AccountTypes.SavingsAccount;
import com.mycompany.bostonbankapp.Helpers.InputHelper;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

/**
 *
 * @author Alex Fernández
 */

public class BankServices {
    // This type of Map is used to store clients with their RUT as the key, which allows for quick access to client information.
    private Map<String, Client> clients = new HashMap<>();
    
    private Random random = new Random();

    // This method allows the creation of a new client by collecting necessary information such as RUT, 
    //name, last names, address, city, phone number, and generating a new bank account.
    public void newClient(Scanner scanner) {
        System.out.print("RUT: ");
        String rut = scanner.nextLine().toUpperCase();

        
        if (!InputHelper.isValidRut(rut)){
            System.out.println("El formato del rut no es valido. Recuerde los puntos y guiones");
            return;
        }

        if (clients.containsKey(rut.toUpperCase())) {
            Client foundClient = clients.get(rut.toUpperCase());
            System.out.println("El cliente con rut: " + foundClient.getRut() + " ya existe. Posee una " + foundClient.getAccount().accountType());
            return;
        }

        String name = InputHelper.getNonEmptyString("Nombre: ", scanner);
        String firstLastname = InputHelper.getNonEmptyString("Apellido Paterno: ", scanner);
        String secondLastname = InputHelper.getNonEmptyString("Apellido Materno: ", scanner);
        String address = InputHelper.getNonEmptyString("Domicilio: ", scanner);
        String city = InputHelper.getNonEmptyString("Comuna: ", scanner);
        String phone = InputHelper.getValidPhoneNumber("Número de Teléfono: ", scanner);

        int accountNumber = generateNewAccountNumber();

        // Create a new BankAccount object with the generated account number and an initial balance of 0.
        BankAccount account = selectBankAccountType(scanner, accountNumber);

        // Create a new Client object with the provided information and the newly created bank account.
        Client client = new Client(rut, name, firstLastname, secondLastname, address, city, phone, account);

        // Store the new client in the clients map using their RUT as the key.
        clients.put(rut,client);

        System.out.println("El cliente se ha registrado exítosamente.");
        System.out.println(client.showInfo());
    }

    // This method allows the user to search for a client by their RUT and display their information.
    public void showClientInfo(Scanner scanner) {
        System.out.print("Ingrese RUT: ");
        String rut = scanner.nextLine();
        Client searchingClient = clients.get(rut.toUpperCase());

        // Validate the RUT format before proceeding to search for the client.
        // If the RUT is not valid, it will inform the user and not proceed with the search.
        if (!InputHelper.isValidRut(rut)){
            System.out.println("El formato del rut no es valido. Recuerde los puntos y guiones");
        } else {
            if (searchingClient != null) {
                System.out.println(searchingClient.showInfo());
            } else {
                System.out.println("Cliente no encontrado.");
            }
        }
    }

    // This method allows the user to deposit an amount into a client's account by providing their RUT.
    public void depositAmount(Scanner scanner) {
        System.out.print("Ingrese RUT: ");
        String rut = scanner.nextLine().trim();
        Client client = clients.get(rut.toUpperCase());
        if (client != null) {
            // Get a valid amount to deposit, ensuring it is greater than 0.
            // The method InputHelper.getValidAmount will prompt the user until a valid amount is entered.
            int amount = InputHelper.getValidAmount("Ingrese un monto a depositar: ", scanner, 0, false);
            System.out.println("¡Monto depositado de forma exitosa!");
            client.getAccount().deposit(amount);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // This method allows the user to withdraw an amount from a client's account by providing their RUT.
    public void withdrawAmount(Scanner scanner) {
        System.out.print("Ingrese RUT: ");
        String rut = scanner.nextLine();
        Client client = clients.get(rut.toUpperCase());
        
        if (client != null) {
            BankAccount bankAccount = client.getAccount();
            int availableFunds;

            // Calculate available funds given the account type
            if ( bankAccount instanceof CreditAccount) {
                CreditAccount creditAccount = (CreditAccount) bankAccount;
                availableFunds = creditAccount.getBalance() + creditAccount.getCreditLimit();
            } else {
                availableFunds = bankAccount.getBalance();
            }

            if (availableFunds > 0){
                // Get a valid amount to withdraw, ensuring it is less than or equal to the client's account balance.
                // The method InputHelper.getValidAmount will prompt the user until a valid amount is entered.
                int amount = InputHelper.getValidAmount("Ingrese un monto a girar: ", scanner, availableFunds, true);
                System.out.println("¡Monto girado de forma exitosa!");
                client.getAccount().withdraw(amount);
            } else{
                System.out.println("Fondos insuficientes para realizar el giro.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // This method allows the user to check the balance of a client's account by providing their RUT.
    public void checkBalance(Scanner scanner) {
        System.out.print("Ingrese RUT: ");
        String rut = scanner.nextLine();
        Client client = clients.get(rut.toUpperCase());
        if (client != null) {
            BankAccount bankAccount = client.getAccount();
            System.out.println("Saldo actual: $" + client.getAccount().getBalance());

            if (bankAccount instanceof CreditAccount) {
                CreditAccount creditAccount = (CreditAccount) bankAccount;
                int available = creditAccount.getBalance() + creditAccount.getCreditLimit();
                System.out.println("Crédito disponible: $" + available);
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // This method generates a new account number that is not already in use by any client.
    // It ensures that the new account number is a random 9-digit number.
    private int generateNewAccountNumber() {
        int newAccountNumber;
        do {
            newAccountNumber = 100000000 + random.nextInt(900000000);
        } while (isAccountNumberAvailable(newAccountNumber));
        return newAccountNumber;
    }

    // This method checks if the generated account number is already in use by any client.
    private boolean isAccountNumberAvailable(int accountNumber) {
        for (Client client : clients.values()) {
            if (client.getAccount().getAccountNumber() == accountNumber) {
                return true;
            }
        }
        return false;
    }

    public BankAccount selectBankAccountType(Scanner scanner, int accountNumber) {
        BankAccount cuenta = null;

        System.out.println("\nSeleccione el tipo de cuenta que desea abrir:");
        System.out.println("1. Cuenta Corriente");
        System.out.println("2. Cuenta de Ahorro");
        System.out.println("3. Cuenta de Crédito");

        while (cuenta == null) {
            int option = InputHelper.getValidNumber("Opcion: ", scanner);
            switch (option) {
                case 1:
                    cuenta = new CheckingAccount(accountNumber);
                    break;
                case 2:
                    cuenta = new SavingsAccount(accountNumber);
                    break;
                case 3:
                    System.out.println("La cuenta de crédito está limitada a 300.000 CLP para nuevos clientes. ");
                    int limit = 300000;
                    cuenta = new CreditAccount(accountNumber, limit);
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elegir 1, 2 o 3.");

            }
        }
        return cuenta;
    }
}

