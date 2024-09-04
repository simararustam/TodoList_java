import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskManager {
    Logger LOGGER = Logger.getLogger(TaskManager.class.getName());
    // ADD TASK to db
    public void addTask(String description) {
        String sql = "INSERT INTO tasks (description) VAlUES (?)";

        try (
                // Get a connection to the database
                Connection conn = DatabaseConnection.getConnection();
                // Prepare the SQL query to be executed
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error occurred", e);
        }
    }

    // List Task
    public void listTasks () {
        String sql = "SELECT id, description, completed FROM tasks";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
                ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                boolean completed = rs.getBoolean("completed");

                System.out.println(id + ". " + description + ". " + (completed ? "Completed" : ""));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error occurred", e);
        }
    }

    // Mark as completed
    public void markTaskCompleted(int taskId) {
        String sql = "UPDATE tasks SET completed = TRUE WHERE id = ?";

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DataBase Error occurred", e);
        }
    }

    // Delete TAsk
    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (
                Connection conn= DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "DATABASE ERR", e);
        }
    }
}
