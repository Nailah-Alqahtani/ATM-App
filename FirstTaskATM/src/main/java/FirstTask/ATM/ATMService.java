package FirstTask.ATM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ATMService {
    // Create an initial array list of accounts
    private List<ATMAccount> atmAccounts = new ArrayList<>(
            Arrays.asList(new ATMAccount("Nailah Alqahtani", "000000", 10000),
                    new ATMAccount("Omar Alsaleh ", "000001", 1200),
                    new ATMAccount("Noura Alrteq", "000011", 4600)));

    // get all accounts
    public List<ATMAccount> getAllAccounts() {
        return atmAccounts;
    }

    // add an account to the list
    public ATMAccount addAccount(ATMAccount atmAccount) {
        ATMAccount newAccount = new ATMAccount(atmAccount.getFullName(), atmAccount.getAccountNumber(),
                atmAccount.getBalance());
        atmAccounts.add(newAccount);
        return newAccount;
    }

    // deposit money to an account
    public ATMAccount deposit(int id, double amount) throws AccountNotFoundException {
        for (int i = 0; i < atmAccounts.size(); i++) { // find account by id
            ATMAccount updatedAccount = atmAccounts.get(i);
            if (updatedAccount.getId() == id) {
                double currentBalance = updatedAccount.getBalance();
                double newBalance = currentBalance + amount; // calculate the new balance after the deposit
                updatedAccount.setBalance(newBalance);
                atmAccounts.set(i, updatedAccount);
                return updatedAccount;
            }
        }
        throw new AccountNotFoundException("Account not found!"); // if the account is not found throw an exception
    }

    // withdraw money from an account
    public ATMAccount withdraw(int id, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        for (int i = 0; i < atmAccounts.size(); i++) {
            ATMAccount updatedAccount = atmAccounts.get(i);
            if (updatedAccount.getId() == id) {
                double currentBalance = updatedAccount.getBalance();
                double newBalance = currentBalance - amount; // calculate the new balance after the withdrawal
                if (newBalance < 0) { // if the new balance is negative throw an exception
                    throw new InsufficientBalanceException("Insufficient balance!");
                }
                updatedAccount.setBalance(newBalance);
                atmAccounts.set(i, updatedAccount);
                return updatedAccount;
            }
        }
        throw new AccountNotFoundException("Account not found!");
    }

    // transfer money from one account to another
    public ATMAccount transfer(int from, int to, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        int i;
        int j;
        ATMAccount fromAccount = null;
        ATMAccount toAccount = null;
        for (i = 0; i < atmAccounts.size(); i++) { // iterate over the list of accounts to find the "from" account with
                                                   // the specified id
            if (atmAccounts.get(i).getId() == from) {
                fromAccount = atmAccounts.get(i);
                break;
            }
        }
        for (j = 0; j < atmAccounts.size(); j++) { // iterate over the list of accounts to find the "to" account with
                                                   // the specified id
            if (atmAccounts.get(j).getId() == to) {
                toAccount = atmAccounts.get(j);
                break;
            }
        }
        if (fromAccount == null || toAccount == null) {
            throw new AccountNotFoundException("Account not found!");
        } else {
            double fromCurrentBalance = fromAccount.getBalance();
            double fromNewBalance = fromCurrentBalance - amount;
            if (fromNewBalance < 0) {
                throw new InsufficientBalanceException("Insufficient balance!");
            }
            double toCurrentBalance = toAccount.getBalance();
            double toNewBalance = toCurrentBalance + amount;
            fromAccount.setBalance(fromNewBalance);
            toAccount.setBalance(toNewBalance);
            atmAccounts.set(i, fromAccount);
            atmAccounts.set(j, toAccount);
            return fromAccount;
        }

    }

}