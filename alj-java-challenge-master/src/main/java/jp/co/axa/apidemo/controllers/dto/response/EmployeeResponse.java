package jp.co.axa.apidemo.controllers.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jp.co.axa.apidemo.domain.model.Employee;
import lombok.Getter;
import lombok.NonNull;

/**
 * Employee Response DTO
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {

    @JsonProperty("id")
    private final Long employeeId;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("salary")
    private final Integer salary;

    @JsonProperty("department")
    private final String department;

    public EmployeeResponse(@NonNull final Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.name = employee.getName();
        this.salary = employee.getSalary();
        this.department = employee.getDepartment();
    }
}
