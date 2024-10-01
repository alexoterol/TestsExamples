package main;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Employee {
    // Constante RMU
    private static final float RMU = 386.0F;
    // Salario del empleado
    private float salary;
    private String currency;
    // Porcentaje de bono
    private float bonusPercentage;
    // Tipo de empleado
    private EmployeeType employeeType;

    // Constructor
    public Employee(float salary, String currency, 
                    float bonusPercentage, EmployeeType employeeType) {
        this.salary = salary;
        this.currency = currency;
        this.bonusPercentage = bonusPercentage;
        this.employeeType = employeeType;
    }

    // Calcula el salario mensual, incluyendo décimo cuando corresponde
    public float calculateMonthlySalary() {
        float adjustedSalary = adjustSalaryForCurrency();
        LocalDate localDate = LocalDate.now(); // Usar LocalDate directamente
        int month = localDate.getMonthValue();
        
        // Calcular salario según tipo de empleado y si corresponde décimo
        switch (employeeType) {
            case Worker:
                return month % 2 == 0 ? adjustedSalary : adjustedSalary + RMU / 12 * 2;
            case Supervisor:
                float supervisorSalary = adjustedSalary + (bonusPercentage * 0.35F);
                return month % 2 == 0 ? supervisorSalary : supervisorSalary + RMU / 12 * 2;
            case Manager:
                float managerSalary = adjustedSalary + (bonusPercentage * 0.7F);
                return month % 2 == 0 ? managerSalary : managerSalary + RMU / 12 * 2;
            default:
                return 0.0F;
        }
    }

    // Ajusta el salario en base a la moneda
    private float adjustSalaryForCurrency() {
        if (currency.equals("USD")) {
            return salary;
        } else {
            return salary * 0.95F;  // Se resta 5% por cambio de moneda
        }
    }

    // Calcula el bono de fin de año
    public float calculateYearBonus() {
        switch (employeeType) {
            case Worker:
                return RMU;
            case Supervisor:
                return RMU * 0.5F;
            case Manager:
                return RMU * 1.0F;
            default:
                return 0.0F;
        }
    }
}
