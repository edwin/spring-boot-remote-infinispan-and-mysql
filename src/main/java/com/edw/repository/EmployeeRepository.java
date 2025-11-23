package com.edw.repository;

import com.edw.bean.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * <pre>
 *  com.edw.repository.EmployeeRepository
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 23 Nov 2025 15:02
 */

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findTop10ByGenderIgnoreCase(String gender);
    List<Employee> findTop10ByFirstnameLikeAndLastnameLike(String firstname, String lastname);
}
