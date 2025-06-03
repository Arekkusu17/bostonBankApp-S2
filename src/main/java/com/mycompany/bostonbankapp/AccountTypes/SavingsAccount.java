package com.mycompany.bostonbankapp.AccountTypes;
import com.mycompany.bostonbankapp.BankPackage.BankAccount;

/**
 *
 * @author Alex Fernández
 */

public class SavingsAccount extends BankAccount {
    public SavingsAccount(int accountNumber) {
        super(accountNumber);  // Usa la sobrecarga (balance = 0)
    }

    public SavingsAccount(int accountNumber, int balance) {
        super(accountNumber, balance);
    }

    @Override
    public String accountType() {
        return "Cuenta de Ahorro";
    }
}
