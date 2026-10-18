package expenseTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private static final Path FILE_PATH = Paths.get("expenses.txt");

    public static void saveExpenses(ArrayList<Expense> expenses) {
        List<String> lines = new ArrayList<>();

        for (Expense expense : expenses) {
            String line = expense.getDate() + ","
                    + expense.getCategory() + ","
                    + expense.getAmount() + ","
                    + expense.getDescription();

            lines.add(line);
        }

        try {
            Files.write(FILE_PATH, lines);
            System.out.println("Expenses saved.");
        } catch (IOException e) {
            System.out.println("Could not save expenses.");
        }
    }

    public static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        if (!Files.exists(FILE_PATH)) {
            return expenses;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);

            for (String line : lines) {
                String[] parts = line.split(",", 4);

                if (parts.length == 4) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    Category category = Category.valueOf(parts[1]);
                    double amount = Double.parseDouble(parts[2]);
                    String description = parts[3];

                    Expense expense = new Expense(
                            amount, category, description, date);
                    expenses.add(expense);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load expenses.");
        }

        return expenses;
    }
}