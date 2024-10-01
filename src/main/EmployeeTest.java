package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    private Employee worker;
    private Employee supervisor;
    private Employee manager;
    
    @BeforeEach
    void setUp() {
        worker = new Employee(1654.0F, "USD", 0.2F, EmployeeType.Worker);
        supervisor = new Employee(2564.0F, "EUR", 0.4F, EmployeeType.Supervisor);
        manager = new Employee(4150.0F, "USD", 0.8F, EmployeeType.Manager);
    }

    @Test
    void testConstructor() {
        Assertions.assertNotNull(worker);
        Assertions.assertEquals(1654.0F, worker.calculateMonthlySalary(), 0.001F);
    }

    @Test
    void testCsWorkerImpar() {
        // Simular mes impar
        int month = 3; // Marzo, mes impar
        float expected = 1654.0F + (386.0F / 12 * 2); // Sueldo + décimo acumulado
        float actual = worker.calculateMonthlySalary();
        Assertions.assertEquals(expected, actual, 0.001F);
    }

    @Test
    void testCsSupervisorPar() {
        // Simular mes par
        int month = 2; // Febrero, mes par
        float salaryAdjustedForCurrency = 2564.0F * 0.95F; // Descuento del 5% por moneda EUR
        float expected = salaryAdjustedForCurrency + (0.4F * 0.35F * salaryAdjustedForCurrency);
        float actual = supervisor.calculateMonthlySalary();
        Assertions.assertEquals(expected, actual, 0.001F);
    }

    @Test
    void testCsManagerImpar() {
        // Simular mes impar
        int month = 5; // Mayo, mes impar
        float expected = 4150.0F + (0.8F * 0.7F * 4150.0F) + (386.0F / 12 * 2); // Sueldo + bono + décimo
        float actual = manager.calculateMonthlySalary();
        Assertions.assertEquals(expected, actual, 0.001F);
    }

    @Test
    void testCYBWorker() {
        // Año Bono para Worker
        float expected = 386.0F; // RMU para Worker
        float actual = worker.calculateYearBonus();
        Assertions.assertEquals(expected, actual, 0.001F);
    }

    @Test
    void testCYBSupervisor() {
        // Año Bono para Supervisor
        float salaryAdjustedForCurrency = 2564.0F * 0.95F; // Descuento del 5% por moneda EUR
        float expected = 386.0F * 0.5F;
        float actual = supervisor.calculateYearBonus();
        Assertions.assertEquals(expected, actual, 0.001F);
    }

    @Test
    void testCYBManager() {
        // Año Bono para Manager
        float expected = 386.0F; // Bono del 100% para Manager
        float actual = manager.calculateYearBonus();
        Assertions.assertEquals(expected, actual, 0.001F);
    }
}
