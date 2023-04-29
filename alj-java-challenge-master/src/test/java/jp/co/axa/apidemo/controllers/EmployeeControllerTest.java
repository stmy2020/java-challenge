package jp.co.axa.apidemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.domain.model.Employee;
import jp.co.axa.apidemo.helper.FileUtils;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Nested
    public class GetEmployees {

        @Test
        public void getEmployees_success() throws Exception {

            final Employee employee1 = new Employee(
                    1L, "name1", 1000, "department1");
            final Employee employee2 = new Employee(
                    2L, "name2", 2000, "department2");

            final List<Employee> employeeList = new ArrayList<>();
            employeeList.add(employee1);
            employeeList.add(employee2);

            final String expected = FileUtils.readJsonFile("json/controllers/getEmployees_success_response.json");

            Mockito.doReturn(employeeList).when(employeeService).retrieveEmployees();

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expected));
        }
    }

    @Nested
    public class GetEmployee {

        @Test
        public void getEmployee_success() throws Exception {

            final Employee employee = new Employee(
                    1L, "name1", 1000, "department1");

            final String expected = FileUtils.readJsonFile("json/controllers/getEmployee_success_response.json");

            Mockito.doReturn(employee).when(employeeService).getEmployee(1L);

            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expected));
        }
    }

    @Nested
    public class SaveEmployee {

        @Test
        public void saveEmployee_success() throws Exception {

            final Employee employee = new Employee(
                    1L, "name1", 1000, "department1");

            final String requestBody = FileUtils.readJsonFile("json/controllers/saveEmployee_success_request.json");

            Mockito.doNothing().when(employeeService).saveEmployee(employee);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    public class DeleteEmployee {

        @Test
        public void deleteEmployee_success() throws Exception {

            Mockito.doNothing().when(employeeService).deleteEmployee(1L);

            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employees/1"))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    public class UpdateEmployee {

        @Test
        public void updateEmployee_success() throws Exception {

            final Employee employee = new Employee(
                    1L, "name1", 1000, "department1");

            final String requestBody = FileUtils.readJsonFile("json/controllers/updateEmployee_success_request.json");

            Mockito.doNothing().when(employeeService).updateEmployee(employee);

            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/1")
                            .content(requestBody)
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isNoContent());
        }
    }
}
