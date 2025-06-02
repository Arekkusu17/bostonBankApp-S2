package com.mycompany.bostonbankapp.AccountTypes;
import com.mycompany.bostonbankapp.BankPackage.BankAccount;

public class CreditAccount extends BankAccount {
    private double creditLimit;

    public CreditAccount(int accountNumber, int balance, int creditLimit) {
        super(accountNumber, balance);
        this.creditLimit = creditLimit;
    }

    public CreditAccount(int accountNumber) {
        super(accountNumber);  // Usa la sobrecarga (saldo = 0.0)
        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    @Override
    public void withdraw(int amount) {
        if (amount > 0 && balance + creditLimit >= amount) {
            balance -= amount;
        }
    }

    @Override
    public String accountType() {
        return "Cuenta de Crédito";
    }
}
