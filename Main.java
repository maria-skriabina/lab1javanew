package com.bank;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankService bankService = new BankService();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Банковская система ===");
            System.out.println("1. Открыть счет");
            System.out.println("2. Пополнить счет");
            System.out.println("3. Снять деньги");
            System.out.println("4. Показать баланс");
            System.out.println("5. Показать историю операций");
            System.out.println("6. Найти счет по параметрам");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Введите номер счета: ");
                        String accNum = scanner.nextLine();
                        System.out.print("Введите БИК: ");
                        String bik = scanner.nextLine();
                        System.out.print("Введите КПП: ");
                        String kpp = scanner.nextLine();
                        bankService.openAccount(accNum, bik, kpp);
                    }
                    case "2" -> {
                        System.out.print("Введите номер счета: ");
                        String accNum = scanner.nextLine();
                        System.out.print("Введите сумму: ");
                        double deposit = Double.parseDouble(scanner.nextLine());
                        bankService.deposit(accNum, deposit);
                    }
                    case "3" -> {
                        System.out.print("Введите номер счета: ");
                        String accNum = scanner.nextLine();
                        System.out.print("Введите сумму: ");
                        double withdraw = Double.parseDouble(scanner.nextLine());
                        bankService.withdraw(accNum, withdraw);
                    }
                    case "4" -> {
                        System.out.print("Введите номер счета: ");
                        String accNum = scanner.nextLine();
                        bankService.showBalance(accNum);
                    }
                    case "5" -> {
                        System.out.print("Введите номер счета: ");
                        String accNum = scanner.nextLine();
                        bankService.showTransactions(accNum);
                    }
                    case "6" -> {
                        System.out.print("Введите поисковый запрос (номер счета/БИК/КПП): ");
                        String query = scanner.nextLine();
                        bankService.searchAccount(query);
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Неверный выбор.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        System.out.println("Программа завершена.");
        scanner.close();
    }
}
