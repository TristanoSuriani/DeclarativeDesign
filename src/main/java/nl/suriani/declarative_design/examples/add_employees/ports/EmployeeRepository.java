package nl.suriani.declarative_design.examples.ports;

import nl.suriani.declarative_design.examples.domain.Employee;

public interface EmployeeRepository {
    void save(Employee employee);
}
