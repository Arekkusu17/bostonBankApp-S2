/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bostonbankapp.Helpers;
import java.util.Scanner;

/**
 *
 * @author arekk
 */

/**
 *
 * @author Alex Fernández
 */

public class InputHelper {

    public static int getValidNumber(String prompt, Scanner scanner) {
        int number = -1;

        while (number <= 0) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                scanner.nextLine(); // limpia el buffer

                if (number <= 0) {
                    System.out.println("El número debe ser mayor a cero.");
                }
            } else {
                System.out.println("Entrada inválida. Ingresa un número entero positivo.");
                scanner.nextLine(); // limpia el input inválido
            }
        }

        return number;
    }

    public static String getValidPhoneNumber(String prompt, Scanner scanner) {
        String phone;

        while (true) {
            System.out.print(prompt);
            phone = scanner.nextLine().trim();

            if (phone.matches("\\d{9}")) {
                return phone; // es válido
            } else {
                System.out.println("Número inválido. Debe contener exactamente 9 dígitos numéricos.");
            }
        }
    }

    public static boolean isValidRut(String rut) {
        // "\d{1,2}" admits ruts with length of minimum 11 characters and maximum 12.
        return rut != null && rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]");
    }

    public static int getValidAmount(String prompt, Scanner scanner, int maxAmount, boolean isWithdrawal) {
        int amount = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                amount = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer

                if (amount <= 0) {
                    System.out.println("El monto debe ser mayor a cero.");
                } else if (isWithdrawal && amount > maxAmount) {
                    System.out.println("El monto excede el saldo disponible.");
                } else {
                    valid = true;
                }
            } else {
                System.out.println("Entrada inválida. Ingresa un número entero positivo.");
                scanner.nextLine(); // limpiar entrada incorrecta
            }
        }

        return amount;
    }

    public static String getNonEmptyString(String prompt, Scanner scanner) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim(); // trim elimina espacios al inicio y final
            if (input.isEmpty()) {
                System.out.println("Este campo no puede estar vacío. Intenta nuevamente.");
            }
        } while (input.isEmpty());
        return input;
    }
}
