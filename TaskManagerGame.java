import java.util.ArrayList;
import java.util.Scanner;

public class TaskManagerGame {

    static class Task {
        String description;
        boolean completed;

        public Task(String description) {
            this.description = description;
            this.completed = false;
        }

        @Override
        public String toString() {
            return (completed ? "[✓] " : "[ ] ") + description;
        }
    }

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int score = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Task Manager Game!");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1: addTask(); break;
                case 2: showTasks(); break;
                case 3: completeTask(); break;
                case 4: deleteTask(); break;
                case 5: 
                    System.out.println("Thanks for playing! Your final score: " + score);
                    running = false; 
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Task Manager Game Menu ===");
        System.out.println("1. Add New Task");
        System.out.println("2. Show Tasks");
        System.out.println("3. Complete a Task");
        System.out.println("4. Delete a Task");
        System.out.println("5. Exit");
        System.out.print("Choose an ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
            System.out.print("Choose an option: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return choice;
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String desc = scanner.nextLine().trim();
        if (desc.isEmpty()) {
            System.out.println("Task description cannot be empty.");
            return;
        }
        tasks.add(new Task(desc));
        System.out.println("Task added!");
    }

    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Your Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
        System.out.println("Current score: " + score);
    }

    private static void completeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to complete.");
            return;
        }
        showTasks();
        System.out.print("Enter task number to complete: ");
        int num = getUserChoice();
        if (num < 1 || num > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        Task t = tasks.get(num - 1);
        if (t.completed) {
            System.out.println("Task is already completed.");
        } else {
            t.completed = true;
            score += 10;
            System.out.println("Congratulations! You completed: " + t.description);
            System.out.println("You earned 10 points. Total score: " + score);
            if (score >= 50) {
                System.out.println("*** You've reached 50 points! Great job! Keep going! ***");
            }
        }
    }

    private static void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }
        showTasks();
        System.out.print("Enter task number to delete: ");
        int num = getUserChoice();
        if (num < 1 || num > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        Task t = tasks.remove(num - 1);
        System.out.println("Task deleted: " + t.description);
    }
}
