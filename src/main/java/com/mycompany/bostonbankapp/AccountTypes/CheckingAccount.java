package com.mycompany.bostonbankapp.AccountTypes;
import com.mycompany.bostonbankapp.BankPackage.BankAccount;

/**
 *
 * @author Alex Fernández
 */

public class CheckingAccount extends BankAccount {

    public CheckingAccount(int accountNumber) {
        super(accountNumber);  // Usa la sobrecarga (balance = 0)
    }

    @Override
    public String accountType() {
        return "Cuenta Corriente";
    }
}
