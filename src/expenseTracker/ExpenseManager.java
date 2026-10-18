package expenseTracker;

import java.util.ArrayList;

public class ExpenseManager {
    private ArrayList<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public double getTotalSpending() {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    public double getTotalByCategory(Category category) {
        double total = 0;

        for (Expense expense : expenses) {
            if (expense.getCategory() == category) {
                total += expense.getAmount();
            }
        }

        return total;
    }
}