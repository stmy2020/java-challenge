package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.controllers.dto.request.EmployeeRequest;
import jp.co.axa.apidemo.controllers.dto.response.EmployeeResponse;
import jp.co.axa.apidemo.domain.exceptions.ValueConflictException;
import jp.co.axa.apidemo.domain.model.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     *  Retrieves a list of all employees.
     *
     *  @return  A list of Employee objects
     */
    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> getEmployees() {
        return employeeService.retrieveEmployees().stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an employee by id.
     *
     * @param employeeId The id of the employee to retrieve.
     * @return A ResponseEntity containing the Employee object and an HTTP status code.
     */
    @GetMapping("/employees/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse getEmployee(
            @PathVariable(name="employeeId") final Long employeeId
    ) {
        return new EmployeeResponse(employeeService.getEmployee(employeeId));
    }

    /**
     * Add a new employee.
     *
     * @param employeeRequest {@link EmployeeRequest}
     */
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmployee(@RequestBody @Valid final EmployeeRequest employeeRequest) {

        final Employee employee = new Employee(
                employeeRequest.getId(),
                employeeRequest.getName(),
                employeeRequest.getSalary(),
                employeeRequest.getDepartment()
        );

        employeeService.saveEmployee(employee);
    }

    /**
     * Delete an employee by id.
     *
     * @param employeeId The id of the employee to delete.
     */
    @DeleteMapping("/employees/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(
            @PathVariable(name="employeeId") final Long employeeId
    ) {
        employeeService.deleteEmployee(employeeId);
    }

    /**
     * Update existing employee information.
     *
     * @param employeeRequest {@link EmployeeRequest}
     * @param employeeId The id of the employee to update.
     */
    @PutMapping("/employees/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(
            @RequestBody @Valid final EmployeeRequest employeeRequest,
            @PathVariable(name="employeeId") final Long employeeId
    ) {

        if (!employeeRequest.getId().equals(employeeId)) {
            throw new ValueConflictException("EmployeeId in path variable and request body are not the same. " +
                    "Path variable id: " + employeeId + ". Request body id: " + employeeRequest.getId());
        }

        final Employee employee = new Employee(
                employeeRequest.getId(),
                employeeRequest.getName(),
                employeeRequest.getSalary(),
                employeeRequest.getDepartment()
        );

        employeeService.updateEmployee(employee);

    }

}
