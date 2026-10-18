package expenseTracker;

import java.time.LocalDate;

public class Expense {
    private double amount;
    private Category category;
    private String description;
    private LocalDate date;

    public Expense(double amount, Category category, String description, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date + " | " + category + " | $" + amount + " | " + description;
    }
}