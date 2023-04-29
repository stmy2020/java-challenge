package jp.co.axa.apidemo.services.impl;

import jp.co.axa.apidemo.domain.exceptions.DataInsertionException;
import jp.co.axa.apidemo.domain.exceptions.ResourceNotFoundException;
import jp.co.axa.apidemo.domain.model.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Retrieves a list of all employees.
     *
     * @return A list of Employee objects
     */
    @Override
    public List<Employee> retrieveEmployees() {

        return employeeRepository.findAll();
    }

    /**
     * Retrieves an employee by id.
     *
     * @param employeeId The id of the employee to retrieve.
     * @return A ResponseEntity containing the Employee object and an HTTP status code.
     */
    @Override
    public Employee getEmployee(
            @NonNull final Long employeeId
    ) {

        final Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (ObjectUtils.isEmpty(employee)) {
            throw new ResourceNotFoundException("Employee is not found. EmployeeId : " + employeeId);
        }

        return employee;
    }

    /**
     * Add a new employee.
     *
     * @param employee {@link Employee}
     */
    @Override
    public void saveEmployee(
            @NonNull final Employee employee
    ) {

        try {
            employeeRepository.save(employee);
        } catch (DataIntegrityViolationException exception) {
            throw new DataInsertionException("Employee already exists. id: " + employee.getEmployeeId());
        }

        log.info("Employee saved successfully. employeeId={}, name={}, salary={}, department={}.",
                employee.getEmployeeId(), employee.getName(), employee.getSalary(), employee.getDepartment());
    }

    /**
     * Delete an employee by id.
     *
     * @param employeeId The id of the employee to delete.
     */
    @Override
    public void deleteEmployee(
            @NonNull final Long employeeId
    ) {

        if (!employeeRepository.existsByEmployeeId(employeeId)) {
            throw new ResourceNotFoundException("Employee is not found. EmployeeId : " + employeeId);
        }

        employeeRepository.deleteByEmployeeId(employeeId);

        log.info("Employee deleted successfully. employeeId={}.", employeeId);
    }

    /**
     * Update existing employee information.
     *
     * @param employee {@link Employee}
     */
    @Override
    public void updateEmployee(
            @NonNull final Employee employee
    ) {

        if (!employeeRepository.existsByEmployeeId(employee.getEmployeeId())) {
            throw new ResourceNotFoundException("Employee is not found. EmployeeId: " + employee.getEmployeeId());
        }

        employeeRepository.updateEmployee(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getSalary(),
                employee.getDepartment()
        );

        log.info("Employee updated successfully. employeeId={}.", employee.getEmployeeId());
    }
}