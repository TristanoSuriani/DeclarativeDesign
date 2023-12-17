package nl.suriani.declarative_design.example.add_employees;

import java.time.LocalDate;
import java.util.UUID;

public record AddEmployeeCommand(UUID id, String firstName, LocalDate birthDate) {
}
