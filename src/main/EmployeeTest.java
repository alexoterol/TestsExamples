package main;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;

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
	void testCsWorkerImpar() {
		LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        // Asumimos que el mes es impar
        int month = localDate.getMonthValue();
        Assertions.assertTrue(month % 2 != 0);

        float expected = 1000.0F + (386.0F / 12 * 2);
        float actual = worker.cs();
        Assertions.assertEquals(expected, actual, 0.001);
	}
	
	@Test
    void testCsSupervisorPar() {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        // Asumimos que el mes es par
        int month = localDate.getMonthValue();
        Assertions.assertTrue(month % 2 == 0);

        float expected = (2000.0F * 0.95F) + (0.2F * 0.35F);
        float actual = supervisor.cs();
        Assertions.assertEquals(expected, actual, 0.001);
    }
	
	@Test
    void testCsManagerImpar() {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        // Asumimos que el mes es impar
        int month = localDate.getMonthValue();
        Assertions.assertTrue(month % 2 != 0);

        float expected = 3000.0F + (0.3F * 0.7F) + (386.0F / 12 * 2);
        float actual = manager.cs();
        Assertions.assertEquals(expected, actual, 0.001);
    }

	@Test
    void testCYBWorker() {
        float expected = 386.0F;
        float actual = worker.CalculateYearBonus();
        Assertions.assertEquals(expected, actual, 0.001);
    }

    @Test
    void testCYBSupervisor() {
        float expected = (2000.0F * 0.95F) + (386.0F * 0.5F);
        float actual = supervisor.CalculateYearBonus();
        Assertions.assertEquals(expected, actual, 0.001);
    }

    @Test
    void testCYBManager() {
        float expected = 3000.0F + 386.0F;
        float actual = manager.CalculateYearBonus();
        Assertions.assertEquals(expected, actual, 0.001);
    }

}
