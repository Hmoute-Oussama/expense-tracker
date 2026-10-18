package expenseTracker;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        for (Expense expense : FileStorage.loadExpenses()) {
            manager.addExpense(expense);
        }

        boolean running = true;

        while (running) {
            System.out.println("\nExpense Tracker");
            System.out.println("1. Add an expense");
            System.out.println("2. View all expenses");
            System.out.println("3. View total spending");
            System.out.println("4. View spending by category");
            System.out.println("5. Check a budget");
            System.out.println("6. Save and exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("Amount: ");
                        double amount = Double.parseDouble(scanner.nextLine());

                        System.out.println(
                                "Categories: FOOD, TRANSPORT, BILLS, ENTERTAINMENT, OTHER");
                        System.out.print("Category: ");
                        Category category = Category.valueOf(
                                scanner.nextLine().trim().toUpperCase());

                        System.out.print("Description: ");
                        String description = scanner.nextLine();

                        Expense expense = new Expense(
                                amount, category, description, LocalDate.now());
                        manager.addExpense(expense);

                        System.out.println("Expense added.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid amount or category.");
                    }
                    break;

                case "2":
                    if (manager.getExpenses().isEmpty()) {
                        System.out.println("There are no expenses yet.");
                    } else {
                        for (Expense expense : manager.getExpenses()) {
                            System.out.println(expense);
                        }
                    }
                    break;

                case "3":
                    System.out.println(
                            "Total spending: $" + manager.getTotalSpending());
                    break;

                case "4":
                    System.out.println(
                            "Categories: FOOD, TRANSPORT, BILLS, ENTERTAINMENT, OTHER");
                    System.out.print("Category: ");

                    try {
                        Category category = Category.valueOf(
                                scanner.nextLine().trim().toUpperCase());
                        double total = manager.getTotalByCategory(category);
                        System.out.println(category + " spending: $" + total);
                    } catch (IllegalArgumentException e) {
                        System.out.println("That category is not recognized.");
                    }
                    break;

                case "5":
                    try {
                        System.out.print("Budget amount: ");
                        double limit = Double.parseDouble(scanner.nextLine());

                        Budget budget = new Budget(limit);
                        double totalSpent = manager.getTotalSpending();

                        if (budget.isOverBudget(totalSpent)) {
                            System.out.println("You are over budget by $"
                                    + (totalSpent - limit));
                        } else {
                            System.out.println("Remaining budget: $"
                                    + budget.getRemainingAmount(totalSpent));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid number for the budget.");
                    }
                    break;

                case "6":
                    FileStorage.saveExpenses(manager.getExpenses());
                    running = false;
                    break;

                default:
                    System.out.println("Choose a number from 1 to 6.");
                    break;
            }
        }

        scanner.close();
    }
}