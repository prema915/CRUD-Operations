package com.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.main.Employee;
import com.jdbc.util.DatabaseUtil;
import com.jdbc.util.QueryUtil;


public class DatabaseService {
	DatabaseUtil databaseUtil=new DatabaseUtil();
	public void insertEmployee(Employee employee) throws SQLException {
		try (Connection connection = databaseUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertEmployeeQuery());) {
             preparedStatement.setString(1,employee.getEmployeeName());
             preparedStatement.setString(2,employee.getEmployeeAddress());
             preparedStatement.setDouble(3,employee.getEmployeeSalary());
             int rows=preparedStatement.executeUpdate();
             
             if(rows>0) {
            	 System.out.println("Record created successfully.");
             }else {
            	 System.out.println("Insert record failed..");
             }
		}
}//end of insertEmployee()

public void getAllEmployees() throws SQLException {
	try (Connection connection = databaseUtil.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllEmployeeQuery());) {
		
		while (resultSet.next()) {
			printEmployee(new Employee(resultSet.getInt("EMPLOYEE_ID"), resultSet.getString("EMPLOYEE_NAME"),
					resultSet.getString("EMPLOYEE_ADDRESS"), resultSet.getDouble("EMPLOYEE_SALARY")));
		}
		}
	}//End of getALLEmployees()
	private void printEmployee(Employee employee) {
		System.out.println("Employee id:"+employee.getEmployeeId());
		System.out.println("Employee name:"+employee.getEmployeeName());
		System.out.println("Employee address:"+employee.getEmployeeAddress());
		System.out.println("Employee salary:"+employee.getEmployeeSalary());
		System.out.println("------------------------------------------");
	}
	public boolean getEmployeeById(int id) throws SQLException {
	   boolean isFound = false;
		try(Connection connection = databaseUtil.getConnection();
			Statement statement = connection.createStatement();
				ResultSet resultSet=statement.executeQuery(QueryUtil.selectEmployeeById(id));
				){
					if (resultSet.next()) {
                        isFound = true;
						printEmployee(new Employee(resultSet.getInt("EMPLOYEE_ID"),
								resultSet.getString("EMPLOYEE_NAME"), resultSet.getString("EMPLOYEE_ADDRESS"),
								resultSet.getDouble("EMPLOYEE_SALARY")));
					} else {
						System.out.println("Record not found for id" + id);
					}
				}
		   return isFound;
			}// end of getEmployeeById()
	public void deleteEmployeeById(int employeeId) throws SQLException {
		
		try(Connection connection = databaseUtil.getConnection();
			Statement statement	= connection.createStatement();){
			 int rows = statement.executeUpdate(QueryUtil.deleteEmployeeById(employeeId));
			 
			 if(rows > 0) {
				 System.out.println("Record deleted successfully");
			 }else {
				 System.out.println("Something went wrong");
			 }
		}
		
	}//end of deleteEMployeeById()
	
	public void updateEmployee(Employee employee) throws SQLException {

		try (Connection connection = databaseUtil.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement(QueryUtil.updateEmployeeQuery(employee.getEmployeeId()))) {
           preparedStatement.setString(1,employee.getEmployeeName());
           preparedStatement.setString(2,employee.getEmployeeAddress());
           preparedStatement.setDouble(3,employee.getEmployeeSalary());
           
          int rows =preparedStatement.executeUpdate();
          if(rows > 0) {
        	  System.out.println("Records updated successfully");
          }else {
        	  System.out.println("Failed to update record.");
          }
		} // end of updateEmployee()
	}
}
