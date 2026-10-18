package expenseTracker;

public class Budget {
    private double limit;

    public Budget(double limit) {
        this.limit = limit;
    }

    public double getLimit() {
        return limit;
    }

    public double getRemainingAmount(double totalSpent) {
        return limit - totalSpent;
    }

    public boolean isOverBudget(double totalSpent) {
        return totalSpent > limit;
    }
}