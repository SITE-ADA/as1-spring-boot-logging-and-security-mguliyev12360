package az.edu.ada.wm2.empcrudsecurity.repository;
import az.edu.ada.wm2.empcrudsecurity.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    @Query("select e from Employee e")
    Iterable<Employee> getAllEmployeesUsingJPAQuery();

}
