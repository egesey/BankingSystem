package SecondComp1112Project;

import java.util.Date;
import java.util.Scanner;

public class TestClass {

    FinancialPortfolio a = new FinancialPortfolio();

    public static void main(String[] args) {
        FinancialPortfolio a = new FinancialPortfolio();
        Transaction b = new Transaction();
        Scanner scan = new Scanner(System.in);
        IndividualCustomer customerEge = new IndividualCustomer("Ege Seymen ", "061orthTR", "555-011", "Male", b.generateTransactionID(), "Engineer ");
        IndividualCustomer customerEren = new IndividualCustomer("Eren Kara", "058çfkTR", "555-042", "Male", b.generateTransactionID(), "Engineer");

        Account Ege = new Account(Account.accountNum(), 11400, customerEge, "616161") {
        };
        Account Eren = new Account(Account.accountNum(), 10900, customerEren, "585858") {
        };

        a.addFirstAccount(Ege);
        a.addFirstAccount(Eren);

        CheckingAccount checkingAccountEge = new CheckingAccount(Account.accountNum(), 11400, customerEge, false, false, false, "616161");
        SavingsAccount savingsAccountEgeTL = new SavingsAccount(Account.accountNum(), 11400, customerEge, 0.03, "active", "616161");
        SavingsAccount savingsAccountEgeUSD = new SavingsAccount(Account.accountNum(), 11400, customerEge, 0.03, "active", "616161");
        CheckingAccount checkingAccountEren = new CheckingAccount(Account.accountNum(), 10900, customerEren, false, false, false, "585858");
        SavingsAccount savingsAccountErenTL = new SavingsAccount(Account.accountNum(), 10900, customerEren, 0.03, "active", "585858");
        SavingsAccount savingsAccountErenUSD = new SavingsAccount(Account.accountNum(), 10900, customerEren, 0.03, "active", "585858");
        a.checkingAccounts.add(checkingAccountEge);
        a.savingsAccountsTL.add(savingsAccountEgeTL);
        a.savingsAccountsUSD.add(savingsAccountEgeUSD);
        a.checkingAccounts.add(checkingAccountEren);
        a.savingsAccountsTL.add(savingsAccountErenTL);
        a.savingsAccountsUSD.add(savingsAccountErenUSD);

        Transaction transfer1 = new Transaction(b.generateTransactionID(), 2800, new Date(), "Deposit");
        Transaction transfer2 = new Transaction(b.generateTransactionID(), 3584, new Date(), "Deposit");
        Transaction transfer3 = new Transaction(b.generateTransactionID(), 6547, new Date(), "Deposit");
        b.transactionHistory.add(transfer1);
        b.transactionHistory.add(transfer2);
        b.transactionHistory.add(transfer3);

        System.out.println("Press [1] for open banking system: ");
        int x = scan.nextInt();
        while (x != 1) {
            System.out.println("Press [1] to open the banking system: ");
            x = scan.nextInt();
        }

        boolean exit1 = false;
        while (!exit1) {
            int chooseAccount = 0;
            boolean exit2 = false;
            while (!exit2) {
                System.out.println("[1]Adding Customer");
                System.out.println("[2]Deleting account");
                System.out.println("[3]Open the recorded accounts");
                System.out.println("[4]Compare recorded accounts");
                System.out.println("[5]EXİT");
                int selected = scan.nextInt();
                switch (selected) {
                    case 1:
                        a.addNewAccount();
                        break;
                    case 2:
                        a.printAccounts(a.accounts);
                        a.deleteAccount();
                        break;
                    case 3:
                        exit2 = true;
                        break;
                    case 4:
                        System.out.println("-Accounts Compare-\n");
                        System.out.println(a.accounts.get(0) + "\n" + a.accounts.get(1));
                        System.out.println("\n-Saving Account Compare-\n");
                        System.out.println("-TL Accounts\n");
                        System.out.println(a.savingsAccountsTL.get(0) + "\n");
                        System.out.println("-USD Accounts\n");
                        System.out.println(a.savingsAccountsUSD.get(0) + "\n");
                        System.out.println("-TL Accounts\n");
                        System.out.println(a.savingsAccountsTL.get(1) + "\n");
                        System.out.println("-USD Accounts\n");
                        System.out.println(a.savingsAccountsUSD.get(1) + "\n");
                        System.out.println("-Checking Account Compare-\n");
                        System.out.println(a.checkingAccounts.get(0) + "\n");
                        System.out.println(a.checkingAccounts.get(1) + "\n");
                        break;
                    case 5:
                        System.exit(0);

                        break;
                }
            }
            System.out.println("\t-First Accounts-\n");
            if (a.accounts.isEmpty()) {
                System.out.println("There are no accounts");
            } else {
                System.out.println("Enter the number next to the account you want: ");
                for (int i = 0; i < a.accounts.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + a.accounts.get(i).getOwner().getname() + " - " + a.accounts.get(i).getAccountNumber());
                }
                chooseAccount = scan.nextInt() - 1;
                if (chooseAccount < 0 || chooseAccount >= a.accounts.size()) {
                    System.out.println("Invalid account selection.");
                    System.out.println("");
                } else {
                    Account selectedAccount = a.accounts.get(chooseAccount);
                    System.out.println("Selected account: " + selectedAccount.getOwner().getname() + " - Account Number: " + selectedAccount.getAccountNumber());

                    int attemptCount = 0;
                    while (attemptCount < 3) {
                        System.out.println("Enter your password:");
                        String password = scan.next();
                        if (selectedAccount.correctPassword(password)) {
                            System.out.println("Password verified. Access granted.");
                            System.out.println("");
                            exit2 = true;
                            break;
                        } else {
                            attemptCount++;
                            if (attemptCount == 3) {
                                System.out.println("Incorrect password entered three times. Exiting system.");
                                System.exit(0);
                            } else {
                                System.out.println("Incorrect password. Try again." + "You have " + (3 - attemptCount) + " rights left");
                                System.out.println("");
                            }
                        }
                    }
                }
            }
            System.out.println("-> Select what you want");
            System.out.println("[1]Portfolio");
            System.out.println("[2]Money transfer");
            System.out.println("[3]Deposit - Withdraw");
            System.out.println("[4]Open the transaction history");
            System.out.println("[5]Debitcard / Online Banking Access / ATM Access");
            System.out.println("[6]To pay interest");
            System.out.println("[7]Adding Institutional Customer");
            System.out.println("[8]Total value of the portfolio");
            System.out.println("[9]EXİT");
            int choose1 = scan.nextInt();
            switch (choose1) {

                case 1:

                    System.out.println("[1]Checking Accounts  [2]Saving Accounts  [3]Institutional Customer Account [4]Accounts Info");
                    int choosec = scan.nextInt();
                    switch (choosec) {
                        case 1:
                            System.out.println("-Checking Accounts-\n");
                            System.out.println(a.checkingAccounts.get(chooseAccount) + "\n");
                            break;
                        case 2:
                            System.out.println("-Saving Accounts-\n");
                            System.out.println("-TL Accounts\n");
                            System.out.println(a.savingsAccountsTL.get(chooseAccount) + "\n");
                            System.out.println("-USD Accounts\n");
                            System.out.println(a.savingsAccountsUSD.get(chooseAccount) + "\n");

                            break;
                        case 3:
                            System.out.println("-Institutional Customer Account-\n");
                            System.out.println(a.InstitutionalCustomer.get(chooseAccount) + "\n");
                            break;
                        case 4:
                            System.out.println("-Info Account-");
                            System.out.println(a.accounts.get(chooseAccount));
                            
                    }
                    break;
                case 2:
                    if (a.accounts.isEmpty() || a.accounts.size() < 2) {
                        System.out.println("Not enough accounts available for transfer.");
                    } else {

                        for (int i = 0; i < a.accounts.size(); i++) {
                            if (i != chooseAccount) {
                                System.out.println("[" + (i) + "] " + a.accounts.get(i).getOwner().getname() + " - " + a.accounts.get(i).getAccountNumber());
                            }
                        }
                    }
                    Account main = a.accounts.get(chooseAccount);

                    System.out.println("Select the account to transfer money to: ");
                    int recipientAccount = scan.nextInt();
                    
                    System.out.println("");
                    if (recipientAccount < 0 || recipientAccount >= a.accounts.size()) {
                        System.out.println("Invalid account selection.");
                        System.out.println("");
                    } else {
                        Account recipient = a.accounts.get(recipientAccount);
                        System.out.println("Enter the amount of money to be sent: ");
                        double amount = scan.nextDouble();
                        System.out.println("");
                        main.transfer(recipient, amount);
                        System.out.println("Transfer completed.");
                        System.out.println("");

                    }
 
                    break;

                case 3:

                    if (a.accounts.isEmpty()) {
                        System.out.println("There are no accounts");
                    } else {

                        System.out.println("Selected account:\n" + a.accounts.get(chooseAccount).getOwner() + " - " + a.accounts.get(chooseAccount).getAccountNumber());
                        System.out.println("");
                        System.out.println("Select operation: [1] Deposit [2] Withdraw");
                        int choose = scan.nextInt();
                        System.out.println("Enter amount:");
                        double amount = scan.nextDouble();
                        switch (choose) {
                            case 1:
                                System.out.println("Select account that you want to deposit: ");
                                System.out.println("[1]Checking Account [2]Saving Account");
                                int chooseAc2 = scan.nextInt();

                                switch (chooseAc2) {
                                    case 1:
                                        a.checkingAccounts.get(chooseAccount).deposit(amount);
                                        break;
                                    case 2:
                                        System.out.println("Select account that you want to deposit: ");
                                        System.out.println("[1]TL Account [2]USD Account");
                                        int chooseTlOrUsd = scan.nextInt();
                                        switch (chooseTlOrUsd) {
                                            case 1:
                                                a.savingsAccountsTL.get(chooseAccount).deposit(amount);

                                                break;
                                            case 2:
                                                a.savingsAccountsUSD.get(chooseAccount).deposit(amount);

                                                break;
                                        }
                                        break;

                                }
                                a.accounts.get(chooseAccount).deposit(amount);
                                break;
                            case 2:
                                System.out.println("Select account that you want to withdraw: ");
                                System.out.println("[1]Checking Account [2]Saving Account");
                                int chooseAc1 = scan.nextInt();

                                switch (chooseAc1) {
                                    case 1:
                                        a.checkingAccounts.get(chooseAccount).withdraw(amount);
                                        break;
                                    case 2:
                                        System.out.println("Select account that you want to withdraw: ");
                                        System.out.println("[1]TL Account [2]USD Account");
                                        int chooseTlOrUsd1 = scan.nextInt();
                                        switch (chooseTlOrUsd1) {
                                            case 1:
                                                a.savingsAccountsTL.get(chooseAccount).withdraw(amount);

                                                break;
                                            case 2:
                                                a.savingsAccountsUSD.get(chooseAccount).withdraw(amount);

                                                break;
                                        }
                                        break;

                                }
                                a.accounts.get(chooseAccount).withdraw(amount);
                                break;
                        }
                        System.out.println("Updated balance: " + a.accounts.get(chooseAccount).getBalance() + "");
                        System.out.println("");

                    }

                case 4:
                    System.out.println("[1] " + b.transactionHistory.get(0));
                    System.out.println("[2] " + b.transactionHistory.get(1));
                    System.out.println("[3] " + b.transactionHistory.get(2));
                    a.printAllTransactions();
                    break;
                case 5:
                    System.out.println("[1]ATM Access  [2]Online Banking Access  [3]Debitcard ");
                    int choosex = scan.nextInt();
                    switch (choosex) {
                        case 1:
                            boolean generalAtmAccess = true;
                            System.out.println("ATM access [1]ON  [2]OFF");
                            int chooseATM = scan.nextInt();

                            switch (chooseATM) {
                                case 1:
                                    a.checkingAccounts.get(chooseAccount).setAtmAccess(generalAtmAccess);
                                    System.out.println("ATM Access turn ON");
                                    break;
                                case 2:
                                    a.checkingAccounts.get(chooseAccount).setAtmAccess(!generalAtmAccess);
                                    System.out.println("ATM Access turn OFF");
                                    break;
                                default:
                                    System.out.println("Wrong choose");
                                    break;
                            }

                            break;
                        case 2:
                            boolean generalOnlineBankingAccess = true;
                            System.out.println("Online Banking Access [1]ON  [2]OFF");
                            int chooseOnline = scan.nextInt();

                            switch (chooseOnline) {
                                case 1:
                                    a.checkingAccounts.get(chooseAccount).setOnlineBankingAccess(generalOnlineBankingAccess);
                                    System.out.println("Online Banking Access turn ON");
                                    break;
                                case 2:
                                    a.checkingAccounts.get(chooseAccount).setOnlineBankingAccess(!generalOnlineBankingAccess);
                                    System.out.println("Online Banking Access turn OFF");
                                    break;
                                default:
                                    System.out.println("Wrong choose");
                                    break;
                            }

                            break;
                        case 3:
                            boolean generalDebitCard = true;
                            System.out.println("Debitcard [1]ON  [2]OFF");
                            int chooseDebit = scan.nextInt();
                            if (generalDebitCard = !a.checkingAccounts.get(chooseAccount).hasDebitCard()) {
                                switch (chooseDebit) {
                                    case 1:
                                        a.checkingAccounts.get(chooseAccount).setHasdebitCard(generalDebitCard);
                                        System.out.println("Debitcard turn ON");
                                        break;
                                    case 2:
                                        a.checkingAccounts.get(chooseAccount).setHasdebitCard(!generalDebitCard);
                                        System.out.println("Debitcard turn OFF");
                                        break;
                                    default:
                                        System.out.println("Wrong choose");
                                        break;
                                }
                            }
                            break;

                    }

                    break;
                case 6:
                    System.out.println("Which account would you want to apply interest to?");
                    System.out.println("[1]TL  [2]USD");
                    int selectAccount = scan.nextInt();
                    switch (selectAccount) {
                        case 1:

                            System.out.println("Updated balance: " + a.savingsAccountsTL.get(chooseAccount).setbalance(a.savingsAccountsTL.get(chooseAccount).getBalance() * (1.03)) + " TL");

                            break;
                        case 2:
                            System.out.println("Updated balance: " + a.savingsAccountsUSD.get(chooseAccount).setbalance(a.savingsAccountsUSD.get(chooseAccount).getBalance() * (1.03)) + " USD");

                            break;
                    }
                    break;
                case 7:
                    a.addInstitutionalCustomer(chooseAccount);

                    break;
                case 8:

                    double checkAc = a.checkingAccounts.get(chooseAccount).balance;
                    double savingTl = a.savingsAccountsTL.get(chooseAccount).balance;
                    double savingUSD = a.savingsAccountsUSD.get(chooseAccount).balance;

                    System.out.println("Name: " + a.accounts.get(chooseAccount).owner.name);
                    System.out.println("Checking account balance: " + checkAc + "TL");
                    System.out.println("Saving TL account balance: " + savingTl + "TL");
                    System.out.println("Saving USD account balance: " + savingUSD + "USD (1USD = 30TL)");

                    System.out.println("Total value of portfolio: " + (checkAc + savingTl + (savingUSD * 30)) + "Tl\n");

                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        }

    }

}
