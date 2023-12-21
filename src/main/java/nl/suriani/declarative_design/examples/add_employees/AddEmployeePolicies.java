package nl.suriani.declarative_design.examples.add_employees;

import nl.suriani.declarative_design.examples.add_employees.domain.Employee;

public interface AddEmployeePolicies {
    PolicyResult validate(Employee employee);
}
