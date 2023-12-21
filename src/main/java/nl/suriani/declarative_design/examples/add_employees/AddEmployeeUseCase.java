package nl.suriani.declarative_design.examples.add_employees;

import nl.suriani.declarative_design.examples.add_employees.domain.Employee;
import nl.suriani.declarative_design.examples.add_employees.domain.Event;
import nl.suriani.declarative_design.examples.add_employees.ports.EmployeeRepository;
import nl.suriani.declarative_design.examples.add_employees.ports.EmployeesRegistryRepository;
import nl.suriani.declarative_design.examples.add_employees.ports.EmployeesRegistryResponse;

import java.util.UUID;
import java.util.function.Function;

public class AddEmployeeUseCase {
    private final AddEmployeePoliciesImpl addEmployeePolicies;
    private final EmployeeRepository employeeRepository;
    private final EmployeesRegistryRepository employeesRegistryRepository;

    private final static String EMPLOYEE_NOT_FOUND = "Employee not found";

    public AddEmployeeUseCase(AddEmployeePoliciesImpl addEmployeePolicies, EmployeeRepository employeeRepository, EmployeesRegistryRepository employeesRegistryRepository) {
        this.addEmployeePolicies = addEmployeePolicies;
        this.employeeRepository = employeeRepository;
        this.employeesRegistryRepository = employeesRegistryRepository;
    }

    public AddEmployeeEvent add(AddEmployeeCommand command) {
        var maybeEmployee = employeesRegistryRepository.findById(command.id())
                .map(getEmployeeWithStaffNumberFromEmployeesRegistry(command));

        if (maybeEmployee.isEmpty()) {
            return notAddedBecauseOf(command.id(), EMPLOYEE_NOT_FOUND);
        }

        var employee = maybeEmployee.get();

        var policyResult = addEmployeePolicies.validate(employee);
        if (policyResult.error().isPresent()) {
            return declinedBecauseOf(command.id(), policyResult.error().get());
        }

        throw new IllegalStateException("Not supposed to come here yet - work in progress");
    }

    private EmployeeNotAddedEvent notAddedBecauseOf(UUID id, String reason) {
        return new EmployeeNotAddedEvent(id, reason);
    }

    private EmployeeDeclinedByPoliciesEvent declinedBecauseOf(UUID id, String reason) {
        return new EmployeeDeclinedByPoliciesEvent(id, reason);
    }

    private EmployeeAddedEvent added(Employee employee) {
        return new EmployeeAddedEvent(employee.id());
    }

    private Function<EmployeesRegistryResponse, Employee> getEmployeeWithStaffNumberFromEmployeesRegistry(AddEmployeeCommand command) {
        return response -> new Employee(command.id(),
                command.firstName(),
                command.birthDate(),
                response.staffNumber());
    }

    private Employee validateEmployeeAgainstPolicies(Employee employee) {
        addEmployeePolicies.validate(employee);
        return employee;
    }

    public static sealed class AddEmployeeEvent extends Event {
        private final UUID id;

        public AddEmployeeEvent(UUID id) {
            this.id = id;
        }
    }

    public static final class EmployeeAddedEvent extends AddEmployeeEvent {
        public EmployeeAddedEvent(UUID id) {
            super(id);
        }
    }

    public static final class EmployeeNotAddedEvent extends AddEmployeeEvent {
        private final String reason;

        public EmployeeNotAddedEvent(UUID id, String reason) {
            super(id);
            this.reason = reason;
        }

        public String reason() {
            return reason;
        }
    }

    public static final class EmployeeDeclinedByPoliciesEvent extends AddEmployeeEvent {
        private final String reason;

        public EmployeeDeclinedByPoliciesEvent(UUID id, String reason) {
            super(id);
            this.reason = reason;
        }

        public String reason() {
            return reason;
        }
    }
}
