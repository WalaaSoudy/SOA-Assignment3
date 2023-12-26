import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtility {

    public static List<Employee> readEmployees(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, Employee.class);

        return objectMapper.readValue(file, collectionType);
    }

    public static void writeEmployees(List<Employee> employees, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(filePath), employees);
    }

    public static void deleteEmployee(int employeeIDToDelete, String filePath) throws IOException {
        List<Employee> employees = readEmployees(filePath);

        // Remove the employee with the specified ID
        employees.removeIf(employee -> employee.getEmployeeID() == employeeIDToDelete);

        // Write the updated list back to the JSON file
        writeEmployees(employees, filePath);
    }
     public static boolean employeeExists(int employeeID, String filePath) throws IOException {
        List<Employee> employees = readEmployees(filePath);

        // Check if the employee with the specified ID exists
        return employees.stream().anyMatch(employee -> employee.getEmployeeID() == employeeID);
    }

    public static void updateEmployeeDesignation(int employeeID, String newDesignation, String filePath) throws IOException {
        List<Employee> employees = readEmployees(filePath);

        // Update the designation of the employee with the specified ID
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == employeeID) {
                employee.setDesignation(newDesignation);
                break;
            }
        }

        // Write the updated list back to the JSON file
        writeEmployees(employees, filePath);
    }
}
