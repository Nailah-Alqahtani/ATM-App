// Task No.1 at TAHAKOM (Solo Projects and Code Review)
// ATM App using Spring boot

package FirstTask.ATM;

import java.util.List;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {

    @Autowired
    private ATMService atmService;

    // List Accounts /accounts - returns a list of all accounts
    @RequestMapping("/accounts")
    public List<ATMAccount> getAllAccounts() {
        return atmService.getAllAccounts();
    }

    // Add Account /accounts - create a new account
    @RequestMapping(method = RequestMethod.POST, value = "/accounts")
    public ATMAccount addAccount(@RequestBody ATMAccount atmAccount) {
        return atmService.addAccount(atmAccount);
    }

    // Deposit Money /accounts/deposit/{id}/{amount} - deposit money into an account
    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/deposit/{id}/{amount}")
    public ResponseEntity<Object> deposit(@PathVariable int id, @PathVariable double amount) {
        try {
            ATMAccount updatedAccount = atmService.deposit(id, amount);
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Withdraw Money /accounts/Withdraw/{id}/{amount} - Withdraw money from an
    // account
    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/withdraw/{id}/{amount}")
    public ResponseEntity<Object> withdraw(@PathVariable int id, @PathVariable double amount) {
        try {
            ATMAccount updatedAccount = atmService.withdraw(id, amount);
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Transfer Money /accounts/transfer/{from}/{to}/{amount} - transfer money from
    // an account into another
    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/transfer/{from}/{to}/{amount}")
    public ResponseEntity<Object> transfer(@PathVariable int from, @PathVariable int to, @PathVariable double amount) {
        try {
            ATMAccount updatedAccount = atmService.transfer(from, to, amount);
            return ResponseEntity.ok(updatedAccount);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
