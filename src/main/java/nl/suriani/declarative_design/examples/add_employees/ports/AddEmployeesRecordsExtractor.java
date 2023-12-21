package nl.suriani.declarative_design.examples.ports;

import nl.suriani.declarative_design.examples.domain.Employee;

import java.util.List;

public interface AddEmployeesRecordsExtractor {
    List<Employee> extract(byte[] employeesData);
}
