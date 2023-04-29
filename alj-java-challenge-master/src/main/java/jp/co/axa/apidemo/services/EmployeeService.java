package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.controllers.dto.request.EmployeeRequest;
import jp.co.axa.apidemo.domain.model.Employee;
import lombok.NonNull;

import java.util.List;

/**
 *
 */
public interface EmployeeService {

    /**
     * Retrieves a list of all employees.
     *
     * @return A list of Employee objects
     */
    List<Employee> retrieveEmployees();

    /**
     * Retrieves an employee by id.
     *
     * @param employeeId The id of the employee to retrieve.
     * @return A ResponseEntity containing the Employee object and an HTTP status code.
     */
    Employee getEmployee(@NonNull final Long employeeId);

    /**
     * Add a new employee.
     *
     * @param employee {@link Employee}
     */
    void saveEmployee(@NonNull final Employee employee);

    /**
     * Delete an employee by id.
     *
     * @param employeeId The id of the employee to delete.
     */
    void deleteEmployee(@NonNull final Long employeeId);

    /**
     * Update existing employee information.
     *
     * @param employee {@link Employee}
     */
    void updateEmployee(
            @NonNull final Employee employee
    );
}