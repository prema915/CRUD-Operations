package com.jdbc.main2;

import java.util.Scanner;

import com.jdbc.main.Employee;
import com.jdbc.service.DatabaseService;

public class MainClass {

	public static void main(String[] args) {
		
		DatabaseService databaseService= new DatabaseService();
		    
			try (Scanner scanner = new Scanner(System.in);) {
				boolean isRunning = true;
				while (isRunning) {
				System.out.println("Enter Choice");
				System.out.println(" 1.Insert");
				System.out.println("2.Select all");
				System.out.println("3.Select Employee by an id");
				System.out.println("4.Delete Employee");
				System.out.println("5.Update Employee");
				System.out.println("6.Exit");

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) 
				{
				case 1:
					System.out.println("Enter name,address,salary");			
					databaseService.insertEmployee(new Employee(scanner.nextLine(),
							scanner.nextLine(), Double.parseDouble(scanner.nextLine())));
					break;

				case 2:
					databaseService.getAllEmployees();
					System.out.println(choice);
					break;

				case 3:
					System.out.println("Enter id of an employee");
					databaseService.getEmployeeById(Integer.parseInt(scanner.nextLine()));
					break;

				case 4:
					System.out.println("Enter id of an employee");
					databaseService.deleteEmployeeById(Integer.parseInt(scanner.nextLine()));
					break;

				case 5:
					System.out.println("Enter id of an employee");
					int updateId = Integer.parseInt(scanner.nextLine());
					boolean isFound = databaseService.getEmployeeById(updateId);

					if (isFound) {
						System.out.println("Enter name,address,salary");
						Employee employee = new Employee(updateId, scanner.nextLine(),
								scanner.nextLine(),
								Double.parseDouble(scanner.nextLine()));
						databaseService.updateEmployee(employee);
					}
					break;
				case 6:
					System.out.println("Thank You Visit again");
					isRunning = false;
					break;

				default:
					System.out.println("Incorrect choice");
					break;
				}

			}
			}catch(Exception e) {
				throw new RuntimeException("Something went wrong"+e);
			}
	}
}



		
	


