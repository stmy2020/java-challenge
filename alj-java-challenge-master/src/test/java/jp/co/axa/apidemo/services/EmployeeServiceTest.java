package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.domain.model.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Nested
    public class RetrieveEmployees {

        @Test
        public void retrieveEmployees_success() {

            final Employee employee1 = new Employee(
                    1L, "name1", 1000, "department1");
            final Employee employee2 = new Employee(
                    2L, "name2", 2000, "department2");

            final List<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee1);
            employeeList.add(employee2);

            Mockito.doReturn(employeeList).when(employeeRepository).findAll();

            final List<Employee> result = employeeService.retrieveEmployees();

            Assertions.assertEquals(employeeList.get(0), result.get(0));
            Assertions.assertEquals(employeeList.get(1), result.get(1));
        }
    }

    @Nested
    public class GetEmployee {

        @Test
        public void getEmployee_success() {

            final Employee employee = new Employee(
                    1L, "name1", 1000, "department1");

            Mockito.doReturn(employee).when(employeeRepository).findByEmployeeId(1L);

            final Employee result = employeeService.getEmployee(1L);

            Assertions.assertEquals(employee, result);
        }
    }

    @Nested
    public class SaveEmployee {

        @Test
        public void saveEmployee_success() {

            final Employee employee = new Employee(
                    1L, "name1", 1000, "department1");

            Mockito.doReturn(employee).when(employeeRepository).save(employee);

            employeeService.saveEmployee(employee);

            Mockito.verify(employeeRepository, Mockito.times(1))
                    .save(employee);
        }
    }

    @Nested
    public class DeleteEmployee {

        @Test
        public void deleteEmployee_success() {

            Mockito.doReturn(true).when(employeeRepository).existsByEmployeeId(1L);
            Mockito.doNothing().when(employeeRepository).deleteByEmployeeId(1L);

            employeeService.deleteEmployee(1L);

            Mockito.verify(employeeRepository, Mockito.times(1))
                    .existsByEmployeeId(1L);
            Mockito.verify(employeeRepository, Mockito.times(1))
                    .deleteByEmployeeId(1L);
        }
    }

    @Nested
    public class UpdateEmployee {

        @Test
        public void updateEmployee_success() {

            final Employee employee = new Employee(
                    1L, "name1", 1000, "department1");

            Mockito.doReturn(true).when(employeeRepository).existsByEmployeeId(1L);
            Mockito.doNothing().when(employeeRepository).updateEmployee(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getSalary(),
                    employee.getDepartment()
            );

            employeeService.updateEmployee(employee);

            Mockito.verify(employeeRepository, Mockito.times(1))
                    .existsByEmployeeId(1L);
            Mockito.verify(employeeRepository, Mockito.times(1))
                    .updateEmployee(
                            employee.getEmployeeId(),
                            employee.getName(),
                            employee.getSalary(),
                            employee.getDepartment()
                    );
        }
    }
}
