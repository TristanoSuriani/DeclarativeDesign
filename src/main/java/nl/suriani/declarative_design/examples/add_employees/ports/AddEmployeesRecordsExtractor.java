package nl.suriani.declarative_design.examples.add_employees.ports;

import nl.suriani.declarative_design.examples.add_employees.domain.Employee;

import java.util.List;

public interface AddEmployeesRecordsExtractor {
    List<Employee> extract(byte[] employeesData);
}
