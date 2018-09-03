package com.example.demo;

import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Profile("default")
	public CommandLineRunner seedData(EmployeeRepository employeeRepository) {
		return (args) -> {
			employeeRepository.deleteAll();

			Employee employee = new Employee();
			employee.setName("Employee");
			employee.setSalary(24);
			employee.setUsername("employee");
			employee.setPassword("{noop}my-employee-password");
			employee.setRole("EMPLOYEE");
			employeeRepository.save(employee);

			Employee boss = new Employee();
			boss.setName("Boss");
			boss.setSalary(240);
			boss.setUsername("boss");
			boss.setPassword("{noop}my-boss-password");
			boss.setRole("MANAGER");
			employeeRepository.save(boss);

		};
	}
}
