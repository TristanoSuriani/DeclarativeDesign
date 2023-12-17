package nl.suriani.declarative_design.example.domain;

import java.time.LocalDate;
import java.util.UUID;

public record Employee(UUID id, String firstName, LocalDate birthDate, Integer staffNumber) {
}
