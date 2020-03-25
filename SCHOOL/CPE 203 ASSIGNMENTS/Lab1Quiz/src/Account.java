import java.util.*;

public final class Account implements Comparable<Account> {

    private String firstName;
    private String lastName;
    private int accountNumber;
    private double balance;
    private boolean isNewAccount;


    public Account(
            String firstName,
            String lastName,
            int accountNumber,
            double balance,
            boolean isNewAccount
    ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isNewAccount = isNewAccount;

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isNewAccount() {
        return isNewAccount;
    }

    /**
     * TO DO: override equals
     */
    @Override
    public boolean equals(Object other) {
        if (other == null){
            return false;
        }
        if (other.getClass() != this.getClass()){
            return false;
        }

        boolean firstnameEQ, lastnameEQ, accountnumberEQ, balanceEQ, isnewaccountEQ;

        Account otheraccount = (Account)other;

        if (otheraccount.firstName == null || this.firstName == null){
            firstnameEQ = this.firstName == null && otheraccount.firstName == null;
        } else {
            firstnameEQ = this.firstName.equals(otheraccount.firstName);
        }

        if (otheraccount.lastName == null || this.lastName == null){
            lastnameEQ = this.lastName == null && otheraccount.lastName == null;
        } else {
            lastnameEQ = this.lastName.equals(otheraccount.lastName);
        }

        accountnumberEQ = this.accountNumber == otheraccount.accountNumber;

        balanceEQ = this.balance == otheraccount.balance;


        isnewaccountEQ = this.isNewAccount == otheraccount.isNewAccount;

        return firstnameEQ && lastnameEQ && accountnumberEQ && balanceEQ && isnewaccountEQ;


    }

    /**
     * TO DO: override hashCode here
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, accountNumber, balance, isNewAccount);
    }

    /**
     * TO DO:
     * Write compareTo (natural ordering of class):
     * 1. accountNumber in ascending order
     *      If same, break ties:
     * 2. new Accounts before old accounts
     *      If same, break ties
     * 3. LastName
     *      If same, break ties
     * 4. FirstName
     *      If same, break ties
     * 5. Lower Balance before higher balance
     */
    @Override
    public int compareTo(Account other) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (accountNumber > other.accountNumber){
            return AFTER;
        } else if (accountNumber < other.accountNumber){
            return BEFORE;
        } else if (accountNumber == other.accountNumber){
            if (isNewAccount && !other.isNewAccount){
                return AFTER;
            } else if (other.isNewAccount && !isNewAccount){
                return BEFORE;
            } else if (isNewAccount && other.isNewAccount){
                if (this.lastName.equals(other.lastName)){
                    if(other.lastName.equals(this.lastName)){
                        if(this.balance>other.balance){
                            return AFTER;
                        } else if (this.balance == other.balance){
                            return EQUAL;
                        } else if (this.balance < other.balance){
                            return BEFORE;
                        }
                    }
                    return this.firstName.compareTo(other.firstName);
                }
                return this.lastName.compareTo(other.lastName);
            }
        }
        return EQUAL;
    }
}
