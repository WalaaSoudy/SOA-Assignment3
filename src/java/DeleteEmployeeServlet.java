import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {

    // File path for the JSON file
    private static final String JSON_FILE_PATH = "C:\\Users\\ALkamel\\Documents\\NetBeansProjects\\WebApplication3\\src\\java\\employee.json";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the employee ID to delete from the request parameters
        int employeeIDToDelete = Integer.parseInt(request.getParameter("employeeID"));

        // Call the deleteEmployee method to remove the employee from the JSON file
        JsonUtility.deleteEmployee(employeeIDToDelete, JSON_FILE_PATH);

        // Redirect back to the HTML form or any other page
        response.sendRedirect("delete_item_success.html");
    }
}
