package com.mycompany.bostonbankapp.AccountTypes;
import com.mycompany.bostonbankapp.BankPackage.BankAccount;

public class SavingsAccount extends BankAccount {
    public SavingsAccount(int accountNumber) {
        super(accountNumber);  // Usa la sobrecarga (saldo = 0.0)
    }

    public SavingsAccount(int accountNumber, int balance) {
        super(accountNumber, balance);
    }

    @Override
    public String accountType() {
        return "Cuenta de Ahorro";
    }
}
