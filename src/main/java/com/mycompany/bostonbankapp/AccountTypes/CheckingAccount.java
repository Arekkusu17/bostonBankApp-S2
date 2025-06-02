package com.mycompany.bostonbankapp.AccountTypes;
import com.mycompany.bostonbankapp.BankPackage.BankAccount;

public class CheckingAccount extends BankAccount {

    public CheckingAccount(int accountNumber, int balance) {
        super(accountNumber, balance);
    }

    public CheckingAccount(int accountNumber) {
        super(accountNumber);  // Usa la sobrecarga (saldo = 0.0)
    }

    @Override
    public String accountType() {
        return "Cuenta Corriente";
    }
}
