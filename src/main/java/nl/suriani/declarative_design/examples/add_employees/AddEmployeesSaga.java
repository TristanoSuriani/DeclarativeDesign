package nl.suriani.declarative_design.examples.add_employees;

public class AddEmployeesSaga {
    final private BulkAddEmployeesUseCase bulkAddEmployeesUseCase;
    final private AddEmployeeUseCase addEmployeeUseCase;

    public AddEmployeesSaga(BulkAddEmployeesUseCase bulkAddEmployeesUseCase, AddEmployeeUseCase addEmployeeUseCase) {
        this.bulkAddEmployeesUseCase = bulkAddEmployeesUseCase;
        this.addEmployeeUseCase = addEmployeeUseCase;
    }
}
