import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {

    // File path for the JSON file
    private static final String JSON_FILE_PATH = "C:\\Users\\ALkamel\\Documents\\NetBeansProjects\\WebApplication3\\src\\java\\employee.json";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the employee ID and new designation from the request parameters
        int employeeIDToUpdate = Integer.parseInt(request.getParameter("employeeID"));
        String newDesignation = request.getParameter("newDesignation");

        // Check if the employee exists before attempting to update
        if (JsonUtility.employeeExists(employeeIDToUpdate, JSON_FILE_PATH)) {
            // Call the updateEmployee method to change the designation
            JsonUtility.updateEmployeeDesignation(employeeIDToUpdate, newDesignation, JSON_FILE_PATH);

            // Redirect back to the HTML form with a success message
            response.sendRedirect("updateEmployee.html");
        } else {
            // Redirect back to the HTML form with a message indicating that the ID was not found
            response.sendRedirect("updateEmployee.html");
        }
    }
}
