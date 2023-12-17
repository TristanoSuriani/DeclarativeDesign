package nl.suriani.declarative_design.examples.add_employees;

public class AddEmployeesSaga {
    final private ExtractBulkAddEmployeesRequestUseCase extractBulkAddEmployeesRequestUseCase;
    final private AddEmployeeUseCase addEmployeeUseCase;

    public AddEmployeesSaga(ExtractBulkAddEmployeesRequestUseCase extractBulkAddEmployeesRequestUseCase, AddEmployeeUseCase addEmployeeUseCase) {
        this.extractBulkAddEmployeesRequestUseCase = extractBulkAddEmployeesRequestUseCase;
        this.addEmployeeUseCase = addEmployeeUseCase;
    }
}
