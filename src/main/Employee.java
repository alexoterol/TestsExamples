package main;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Employee
{   //remuneracion mensual unificada
    private final float rmu = (float) 386.0;
    //salario del employee
    private float salary;
    private String currency;
    //porcentaje de bonus
    private float bonusPercentage;    
    //variable de tipo employeeType
    private EmployeeType employeeType;    

    public Employee(float salary, String currency, 
        float bonusPercentage, EmployeeType employeeType){
        this.salary = salary;
        this.currency = currency;
        this.bonusPercentage = bonusPercentage;
        this.employeeType = employeeType;
    }
    //calcula el salario dependiendo del tipo de trabajador 
    //y entrega el décimo correspondiente cada 2 meses
    public float cs() {
        float salario = 0;
        Date date = new Date();
        //Obtiene la hora local
        LocalDate localDate;
        localDate = date.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        //Obtiene el mes en forma de entero
        int month = localDate.getMonthValue();
        // Si la moneda es USD, se considera todo el salario,
        // caso contrario se resta 5% por cambio de moneda
        if(currency == "USD"){salario = salary; }
        else{salario = (float) (salary * 0.95);}
        switch (employeeType)         
        {
            case Worker:
                //Si el mes es impar entonces le entrega 
                //el décimo junto con su salario
                return month%2==0?salario:salario + rmu/12*2;
            case Supervisor:
                float valueS = salario + (bonusPercentage * 0.35F);
                //Si el mes es impar entonces le entrega 
                //el décimo junto con su salario y un bono
                return month%2==0?valueS:valueS + rmu/12*2;
            case Manager:
                float valueM = salario + (bonusPercentage * 0.7F);
                //Si el mes es impar entonces le entrega 
                //el décimo junto con su salario y un bono
                return month%2==0?valueM:valueM + rmu/12*2;
        }
        return 0.0F;
    }
    //calcula el bono de fin de año
    public float CalculateYearBonus() {
        float salario = 0;
        // Si la moneda es USD, se considera todo el salario,
        // caso contrario se resta 5% por cambio de moneda
        if(currency == "USD"){salario = salary; }
        else{salario = (float) (salary * 0.95);}
        switch (employeeType) {
            case Worker:
                return rmu;
            case Supervisor:
                return salario + rmu * 0.5F;
            case Manager:
                return salario + rmu * 1.0F;
        }
        return 0.0F;
    }
}