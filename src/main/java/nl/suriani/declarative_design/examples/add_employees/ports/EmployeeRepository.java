package nl.suriani.declarative_design.examples.add_employees.ports;

import nl.suriani.declarative_design.examples.add_employees.domain.Employee;

public interface EmployeeRepository {
    void save(Employee employee);
}
