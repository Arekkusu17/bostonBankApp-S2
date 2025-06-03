package com.mycompany.bostonbankapp.AccountTypes;
import com.mycompany.bostonbankapp.BankPackage.BankAccount;

/**
 *
 * @author Alex Fernández
 */

public class CreditAccount extends BankAccount {
    private int creditLimit;

    public CreditAccount(int accountNumber, int creditLimit) {
        super(accountNumber);  // Usa la sobrecarga (balance = 0)
        this.creditLimit = creditLimit;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("El  monto debe ser positivo.");
            return;
        }
        int availableBalance = balance + creditLimit;
        if (amount <= availableBalance) {
            balance -= amount;
            System.out.println("Retiro exitoso. Nuevo saldo: " + balance);
        } else {
            System.out.println("Fondos insuficientes. Incluyendo el crédito disponible.");
        }
    }

    @Override
    public String accountType() {
        return "Cuenta de Crédito";
    }
}
