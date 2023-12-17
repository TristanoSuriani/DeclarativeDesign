package nl.suriani.declarative_design.example.ports;

import nl.suriani.declarative_design.example.domain.Employee;

public interface EmployeeRepository {
    void save(Employee employee);
}
