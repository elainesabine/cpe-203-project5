import java.util.Comparator;
/*
* TO DO:
*This class should compare all accounts by balance only (in ascending order)
 */


public class BalanceComparator implements Comparator<Account>{
    @Override
    public int compare(Account account1, Account account2) {
        double result = account1.getBalance() - account2.getBalance();
        return (int)result;
    }
}

