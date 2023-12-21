package nl.suriani.declarative_design.examples.add_employees.ports;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeesRegistryResponse(UUID id, Integer staffNumber) {

}
