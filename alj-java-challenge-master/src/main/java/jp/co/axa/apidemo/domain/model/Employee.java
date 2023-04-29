package jp.co.axa.apidemo.domain.model;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;

/**
 * Employee Object
 */
@Entity
@Table(name="EMPLOYEE")
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMPLOYEE_ID", unique = true)
    private Long employeeId;

    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @Column(name="DEPARTMENT")
    private String department;

    public Employee(
            @NonNull final Long employeeId,
            @NonNull final String name,
            @NonNull final Integer salary,
            @NonNull final String department
            ) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Employee() {}
}
