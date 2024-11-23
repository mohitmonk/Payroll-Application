import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Mohit@1603";

    // Method to establish a database connection
    public static Connection getConnection() throws Exception {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish and return a connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to fetch employee details from the database
    public static Employee fetchEmployeeDetails(int empId) {
        String query = "SELECT * FROM employees WHERE emp_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the employee ID in the query
            stmt.setInt(1, empId);

            // Execute the query and process the result
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Create and return an Employee object from the result
                    return new Employee(
                            rs.getInt("emp_id"), 
                            rs.getString("emp_name"), 
                            rs.getDouble("basic_salary")
                    );
                } else {
                    System.out.println("No employee found with ID: " + empId);
                }
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
        }
        // Return null if no employee is found or an error occurs
        return null;
    }
}
