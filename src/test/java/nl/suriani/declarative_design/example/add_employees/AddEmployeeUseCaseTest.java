package nl.suriani.declarative_design.example.add_employees;

import nl.suriani.declarative_design.example.domain.Employee;
import nl.suriani.declarative_design.example.ports.EmployeeRepository;
import nl.suriani.declarative_design.example.ports.EmployeesRegistryRepository;
import nl.suriani.declarative_design.example.ports.EmployeesRegistryResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static nl.suriani.declarative_design.example.add_employees.AddEmployeeUseCase.*;
import static org.junit.jupiter.api.Assertions.*;

class AddEmployeeUseCaseTest {

    private final EmployeeRepository employeeRepository = new EmployeeRepository() {
        @Override
        public void save(Employee employee) {
        }
    };

    private final EmployeeRepository employeeRepository_saveFails = new EmployeeRepository() {
        @Override
        public void save(Employee employee) {
            throw new RuntimeException("Impossible to save due to interferences");
        }
    };

    private final EmployeesRegistryRepository employeesRegistryRepository_notFound = new EmployeesRegistryRepository() {
        @Override
        public Optional<EmployeesRegistryResponse> findById(UUID id) {
            return Optional.empty();
        }
    };

    private final EmployeesRegistryRepository employeesRegistryRepository = new EmployeesRegistryRepository() {
        @Override
        public Optional<EmployeesRegistryResponse> findById(UUID id) {
            return Optional.of(new EmployeesRegistryResponse(id, 12345));
        }
    };

    private final EmployeesRegistryRepository employeesRegistryRepository_staffNumberIn0To999Range = new EmployeesRegistryRepository() {
        @Override
        public Optional<EmployeesRegistryResponse> findById(UUID id) {
            return Optional.of(new EmployeesRegistryResponse(id, 900));
        }
    };

    private final static String EMPLOYEE_NOT_FOUND = "Employee not found";

    @Test
    void employeeNotAdded_notFound() {
        var useCase = new AddEmployeeUseCase(new AddEmployeePolicies(), employeeRepository, employeesRegistryRepository_notFound);
        var command = givenCommand(40);

        var event = useCase.add(command)
                .as(EmployeeNotAddedEvent.class);

        assertEquals(EMPLOYEE_NOT_FOUND, event.reason());
    }

    @Test
    void employeeDeclined_olderThan35() {
        var useCase = new AddEmployeeUseCase(new AddEmployeePolicies(), employeeRepository, employeesRegistryRepository);
        var command = givenCommand(40);

        var event = useCase.add(command)
                .as(EmployeeDeclinedByPoliciesEvent.class);

        assertEquals("Saul is older than 35 years old", event.reason());
    }

    @Test
    void employeeDeclined_youngerThan18() {
        var useCase = new AddEmployeeUseCase(new AddEmployeePolicies(), employeeRepository, employeesRegistryRepository);
        var command = givenCommand(17);

        var event = useCase.add(command)
                .as(EmployeeDeclinedByPoliciesEvent.class);

        assertEquals("Saul is younger than 18 years old", event.reason());
    }

    @Test
    void employeeDeclined_staffNumberInThe0To999Range() {
        var useCase = new AddEmployeeUseCase(new AddEmployeePolicies(), employeeRepository, employeesRegistryRepository_staffNumberIn0To999Range);
        var command = givenCommand(35);

        var event = useCase.add(command)
                .as(EmployeeDeclinedByPoliciesEvent.class);

        assertEquals("Saul has invalid staff number", event.reason());
    }

    private static AddEmployeeCommand givenCommand(int age) {
        return new AddEmployeeCommand(UUID.randomUUID(), "Saul", LocalDate.now().minusYears(age));
    }
}