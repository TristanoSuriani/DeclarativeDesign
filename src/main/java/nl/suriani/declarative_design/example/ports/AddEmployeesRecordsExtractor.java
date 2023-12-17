package nl.suriani.declarative_design.example.ports;

import nl.suriani.declarative_design.example.domain.Employee;

import java.util.List;

public interface AddEmployeesRecordsExtractor {
    List<Employee> extract(byte[] employeesData);
}
