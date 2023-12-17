package nl.suriani.declarative_design.example.ports;

import java.util.Optional;
import java.util.UUID;

public interface EmployeesRegistryRepository {
    Optional<EmployeesRegistryResponse> findById(UUID id);
}
