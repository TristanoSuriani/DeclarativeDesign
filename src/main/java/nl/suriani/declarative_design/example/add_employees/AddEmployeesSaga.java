package nl.suriani.declarative_design.example.add_employees;

public class AddEmployeesSaga {
    final private ExtractBulkAddEmployeesRequestUseCase extractBulkAddEmployeesRequestUseCase;
    final private AddEmployeeUseCase addEmployeeUseCase;

    public AddEmployeesSaga(ExtractBulkAddEmployeesRequestUseCase extractBulkAddEmployeesRequestUseCase, AddEmployeeUseCase addEmployeeUseCase) {
        this.extractBulkAddEmployeesRequestUseCase = extractBulkAddEmployeesRequestUseCase;
        this.addEmployeeUseCase = addEmployeeUseCase;
    }
}
