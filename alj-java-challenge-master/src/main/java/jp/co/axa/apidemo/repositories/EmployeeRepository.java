package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    /**
     * Find the query by employeeId rather than primary key id
     *
     * @param employeeId The id to retrieve an employee
     * @return {@link Employee}
     */
    @Query("SELECT e FROM Employee e WHERE e.employeeId = :employeeId")
    Employee findByEmployeeId(Long employeeId);

    /**
     * Check if an employee exists by employeeId rather than primary key id
     *
     * @param employeeId The id to check if an employee exists
     * @return boolean
     */
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Employee e WHERE e.employeeId = :employeeId")
    boolean existsByEmployeeId(Long employeeId);

    /**
     * Delete the query by employeeId rather than primary key id
     *
     * @param employeeId {@link Employee}
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Employee WHERE employeeId = :employeeId")
    void deleteByEmployeeId(Long employeeId);

    /**
     * Update the existing employee
     *
     * @param employeeId    employee id to update
     * @param name          name to update
     * @param salary        salary to update
     * @param department    department to update
     */
    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.name = :name, e.salary = :salary, e.department = :department WHERE e.employeeId = :employeeId")
    void updateEmployee(
            Long employeeId,
            @Param("name") String name,
            @Param("salary") Integer salary,
            @Param("department") String department
    );

}
