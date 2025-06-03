/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bostonbankapp;
import com.mycompany.bostonbankapp.BankPackage.BankServices;
import com.mycompany.bostonbankapp.Helpers.InputHelper;

import java.util.Scanner;

/**
 *
 * @author Alex Fernández
 */

public class BostonBankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an instance of BankServices to manage bank operations
        // This instance will be used to register clients, deposit, withdraw, and check balances.
        BankServices bank = new BankServices();
        int option;

        //Menu to interact with the bank services
        do {
            System.out.println("\n--- Bienvenido a Boston Bank ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos del cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Girar");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            option = InputHelper.getValidNumber("Selecciona una opción: ", scanner);

            switch (option) {
                case 1 -> bank.newClient(scanner);
                case 2 -> bank.showClientInfo(scanner);
                case 3 -> bank.depositAmount(scanner);
                case 4 -> bank.withdrawAmount(scanner);
                case 5 -> bank.checkBalance(scanner);
                case 6 -> System.out.println("Hasta pronto.");
                default -> System.out.println("Opción inválida.");
            }
        } while (option != 6);
    }
}
