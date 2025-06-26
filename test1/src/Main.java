import Model.Account;


public class Main {
    public static void main(String[] args) {
        Account myAccount = new Account();

        try {

            myAccount.withdraw(500);
            myAccount.deposit(1000);


            myAccount.deposit(2000);




            myAccount.printStatement();

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Transaction failed: " + e.getMessage());
        }
    }
    }
