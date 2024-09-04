import java.util.Scanner;

public class TodoListApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();

        while (true) {
            System.out.println("Choose option: 1. Add Task 2. List Tasks 3. Mark Task as Completed 4. Delete Task 5. Exit");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter task description: ");
                    String description = input.nextLine();
                    taskManager.addTask(description);
                    System.out.println("Task added.");
                    break;
                case 2:
                    taskManager.listTasks();
                    break;
                case 3:
                    System.out.println("Eneter task id to mark as comleted: ");
                    int completedId = input.nextInt();
                    taskManager.markTaskCompleted(completedId);
                    System.out.println("Task marked as completed");
                    break;
                case 4:
                    System.out.println("Enter task id to delete it: ");
                    int deleteId = input.nextInt();
                    taskManager.deleteTask(deleteId);
                    System.out.println(deleteId + " id task deleted");
                    break;
                case 5:
                    System.out.println("Exciting...");
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}
