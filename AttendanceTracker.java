import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AttendanceTracker {
    private final Map<String, Employee> employeeMap;
    private final LinkedHashSet<Employee> attendanceRecords;
    public AttendanceTracker() {
        this.employeeMap = new HashMap<>();
        this.attendanceRecords = new LinkedHashSet<>();
    }
    public void registerEmployee(String employeeId, String name) {
        Employee employee = new Employee(employeeId, name);
        employeeMap.put(employeeId, employee);
    }
    public void recordAttendance(String employeeId) throws UnknownEmployeeException {
        Employee employee = employeeMap.get(employeeId);
        if (employee == null) {
            throw new UnknownEmployeeException("Employee with ID " + employeeId + " not found");
        }
        attendanceRecords.add(employee);
    }
    public boolean checkAttendance(String employeeId) {
        Employee employee = employeeMap.get(employeeId);
        return employee != null && attendanceRecords.contains(employee);
    }
    public List<Employee> getPresentEmployees() {
        return new ArrayList<>(attendanceRecords);
    }
    public void markAttendance(String employeeId) throws UnknownEmployeeException {
        recordAttendance(employeeId);
        System.out.println("Attendance recorded for employee with ID " + employeeId);

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendanceTracker attendanceTracker = new AttendanceTracker();
// Register employees
        attendanceTracker.registerEmployee("emp1", "RAM");
        attendanceTracker.registerEmployee("emp2", "SEEMA");
        attendanceTracker.registerEmployee("emp3", "ALICE");
        attendanceTracker.registerEmployee("emp4", "SANIYA");
        attendanceTracker.registerEmployee("emp5", "VED");
// Mark attendancecdac
        while (true) {
            System.out.print("Enter your employee ID to mark attendance (or 'exit' to quit): ");
            String employeeId = scanner.nextLine();
            if (employeeId.equals("exit")) {
                break;
            }
            try {
                attendanceTracker.markAttendance(employeeId);
            } catch (UnknownEmployeeException e) {
                System.out.println(e.getMessage());
            }
        }
// Check attendance
        System.out.println("Attendance Status:");
        System.out.println("RAM: " + attendanceTracker.checkAttendance("emp1"));
        System.out.println("SEEMA: " + attendanceTracker.checkAttendance("emp2"));
        System.out.println("ALICE: " + attendanceTracker.checkAttendance("emp3"));
        System.out.println("SANIYA: " + attendanceTracker.checkAttendance("emp4")); //Corrected employee ID
        System.out.println("VED: " + attendanceTracker.checkAttendance("emp5")); // Corrected employee ID

        System.out.println("Present employees: " + attendanceTracker.getPresentEmployees());
    }
}
class Employee {
    private final String employeeId;

    private final String name;
    public Employee(String employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public String toString() {
        return name + " (" + employeeId + ")";
    }
}
class UnknownEmployeeException extends Exception {
    public UnknownEmployeeException(String message) {
        super(message);
    }
}
