package jp.co.axa.apidemo.controllers.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * Employee Request DTO
 */
@Getter
public class EmployeeRequest {

    @NotNull(message = "id is not properly set.")
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "name is not properly set.")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "salary is not properly set.")
    @JsonProperty("salary")
    private Integer salary;

    @NotNull(message = "department is not properly set.")
    @JsonProperty("department")
    private String department;
}
