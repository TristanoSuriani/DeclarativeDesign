package nl.suriani.declarative_design.example.add_employees;

import nl.suriani.declarative_design.example.domain.Employee;
import nl.suriani.declarative_design.Validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Predicate;

public class AddEmployeePolicies {

    private final static String ERROR_MESSAGE_TEMPLATE = "%s %s";

    public PolicyResult validate(Employee employee) {
        return Validation.of(employee)
                .validate(isAtLeast18YearsOld(), employee1 -> "is younger than 18 years old")
                .validate(isMax35YearsOld(), employee1 -> "is older than 35 years old")
                .validate(hasStaffNumberGreaterThan999(), employee1 -> "has invalid staff number")
                .ifSuccessOrElseAndGet(
                        employee1 -> PolicyResult.ok(),
                        (employee1, reason) -> System.out.println(getErrorMessage(employee, reason)),
                        (employee1, reason) -> PolicyResult.error(getErrorMessage(employee, reason))
                );
    }

    private Predicate<Employee> isMax35YearsOld() {
        return employee -> {
            var currentDate = LocalDate.now();
            var birthDate = employee.birthDate();
            return Period.between(birthDate, currentDate).getYears() <= 35;
        };
    }

    private Predicate<Employee> isAtLeast18YearsOld() {
        return employee -> {
            var currentDate = LocalDate.now();
            var birthDate = employee.birthDate();
            return Period.between(birthDate, currentDate).getYears() >= 18;
        };
    }

    private Predicate<Employee> hasStaffNumberGreaterThan999() {
        return employee -> employee.staffNumber() > 999;
    }

    private String getErrorMessage(Employee employee, String reason) {
        return String.format(ERROR_MESSAGE_TEMPLATE, employee.firstName(), reason);
    }
}
