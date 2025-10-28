package com.bank;



import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BankService {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    /** Открыть новый счет */
    public void openAccount(String accountNumber, String bik, String kpp) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Счет с таким номером уже существует.");
        }
        accounts.put(accountNumber, new BankAccount(accountNumber, bik, kpp));
        System.out.println("Счет успешно создан.");
    }

    /** Пополнить счет на сумму */
    public void deposit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        account.deposit(amount);
        System.out.println("Сумма успешно зачислена.");
    }

    /** Снять деньги со счета */
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        account.withdraw(amount);
        System.out.println("Сумма успешно снята.");
    }

    /** Показать баланс (для существующего счета) */
    public void showBalance(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        System.out.printf("Баланс счета %s: %.2f%n", accountNumber, account.getBalance());
    }

    /** Показать историю операций (для существующего счета) */
    public void showTransactions(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account.getTransactions().isEmpty()) {
            System.out.println("Нет операций.");
            return;
        }
        account.getTransactions().forEach(System.out::println);
    }

    /** Поиск по параметрам (для существующего счета) */
    public void searchAccount(String query) {
        boolean found = false;
        for (BankAccount acc : accounts.values()) {
            if (acc.getAccountNumber().contains(query)
                    || acc.getBik().contains(query)
                    || acc.getKpp().contains(query)) {
                System.out.println("Найден счет: " + acc);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Счет не найден.");
        }
    }

    /** Получить счет по номеру (для UI) */
    public BankAccount getAccount(String accountNumber) {
        return findAccount(accountNumber);
    }

    /** Получить все счета (для UI списка и поиска) */
    public Collection<BankAccount> getAllAccounts() {
        return accounts.values();
    }

    /** Найти счет по номеру, если существует */
    private BankAccount findAccount(String accountNumber) {
        BankAccount acc = accounts.get(accountNumber);
        if (acc == null) {
            throw new IllegalArgumentException("Счет не найден.");
        }
        return acc;
    }
}