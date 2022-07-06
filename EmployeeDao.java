package net.javaguides.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import net.javaguides.registration.model.Employee;


	//@WebServlet("/EmployeeDao")
	public class EmployeeDao {     
		public boolean login(Employee employee) throws ClassNotFoundException {
	        boolean status = false;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://127.0.0.1:3306/project?"
	            		+"allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");

	            // Create a statement using connection object
	            PreparedStatement preparedStatement = connection
	            .prepareStatement("select * from Users where username = ? and password = ? ")) {
	            preparedStatement.setString(1, employee.getUsername());
	            preparedStatement.setString(2, employee.getPassword());

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            status = rs.next();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return status;
	    }
		
		public boolean validate(Employee employee) throws ClassNotFoundException {
	        boolean status = false;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://127.0.0.1:3306/project?"
	            		+"allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");

	            // Create a statement using connection object
	            PreparedStatement preparedStatement = connection
	            .prepareStatement("select * from Users where username = ?")) {
	            preparedStatement.setString(1, employee.getUsername());
	           

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            status = rs.next();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return status;
	    }


		public int registerUser(Employee employee) throws ClassNotFoundException {
	        String INSERT_USERS_SQL = "INSERT INTO Users" +
	            "  (username, firstName, lastName, password, age) VALUES " +
	            " (?, ?, ?, ?, ?);";

	        int result = 0;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (
	        		Connection connection = DriverManager
		            .getConnection("jdbc:mysql://127.0.0.1:3306/project?"
		            		+"allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
	            //Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL))
	            {
	        	preparedStatement.setString(1, employee.getUsername());
	            preparedStatement.setString(2, employee.getFirstName());
	            preparedStatement.setString(3, employee.getLastName());
	            preparedStatement.setString(4, employee.getPassword());
	            preparedStatement.setInt(5, employee.getAge());
	            System.out.println(preparedStatement);
	           
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
	    }

		public void initialize() throws SQLException {
			try {
				Connection connection = DriverManager
		            .getConnection("jdbc:mysql://127.0.0.1:3306/project?"
		            		+"allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
			
			   Statement statement = connection.createStatement();

			      
			      statement.executeUpdate("DROP TABLE IF EXISTS Follow");
			      statement.executeUpdate("DROP TABLE IF EXISTS PPS");
			      statement.executeUpdate("DROP TABLE IF EXISTS Transaction");
			      statement.executeUpdate("DROP TABLE IF EXISTS Users");
			      
			      

			      
			      statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users " +
			      		"(username VARCHAR(50) not NULL, " +
			      		" password VARCHAR(50) not NULL, "+
			      		" firstName VARCHAR(20), " +
			      		" lastname VARCHAR(20), " +
			      		" address VARCHAR(50), " +
			      		" birthday date, " +
			      		" age int, " +
			      		" PPSbalance DECIMAL(15,2) NOT NULL DEFAULT 0.00, " +
			      		" Dollarbal DECIMAL(15,2) NOT NULL DEFAULT 0.00, " +
			      		" PRIMARY KEY ( username ));");
			      
			      statement.executeUpdate("UPDATE Users SET PPSbalance = 1000000000000.00 WHERE username = \"root\";");
			      ResultSet resultSet = statement.executeQuery("select * from Users");
			      System.out.println(resultSet);
			      
			      statement.executeUpdate("CREATE TABLE IF NOT EXISTS Follow "+
			    		  "(username VARCHAR(50) not NULL, " +
			    		  " followingUsername VARCHAR(50), " +
			    		  " followdate datetime, " +
			    		  " FOREIGN KEY (username) REFERENCES Users(username),"
			    		  + "FOREIGN KEY (followingUsername) REFERENCES Users(username));");
			      
			      
			      statement.executeUpdate("CREATE TABLE IF NOT EXISTS Transaction " +
			      "(transid VARCHAR(50) not NULL, " +
			      " transname VARCHAR(50), " +
			      " dollaramt DECIMAL(15,2), " +
			      " PPSamt INT, " +
			      " PPSbal DECIMAL(15,2), " +
			      " fromuser VARCHAR(50), " +
			      " touser VARCHAR(50), "+
			      " PRIMARY KEY (transid), " +
			      " FOREIGN KEY (fromuser) REFERENCES Users(username), "+
			      " FOREIGN KEY (touser) REFERENCES Users(username));");
			      
			      statement.executeUpdate("CREATE TABLE IF NOT EXISTS PPS " +
			    		  "(ppsprice INTEGER);");
			      
			      
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName,  PPSbalance, Dollarbal) values (\"root\",\"pass1234\",\"root\",\"user\", 900000000000.00, 100000.00);");
			      
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address,  age, PPSbalance, Dollarbal) values (\"ameen\",\"abc1234\",\"ameen\" ,\"Noah\" ,\"151,second street\" , 21, 90000.00, 1000.00);");
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address, birthday, age, PPSbalance, Dollarbal) values (\"olivia\",\"oli1234\",\"olivia\" ,\"ava\" ,\"2201,Grand street\" ,\"1994-05-20\" , 27, 900.00, 100.00);");
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address, birthday,  PPSbalance, Dollarbal) values (\"emma\",\"emm1234\",\"emma\" ,\"liam\" ,\"198,henry street\" ,\"2004-06-25\" , 8200000.00, 0.00);");
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address, birthday,  PPSbalance, Dollarbal) values (\"amelia\",\"ame1234\",\"amelia\" ,\"oliver\" ,\"2821,avenue road\" ,\"2015-09-11\" , 150.00, 2000.00);");
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address, birthday,  PPSbalance, Dollarbal) values (\"ava\",\"ava1234\",\"ava\" ,\"emma\" ,\"201,scott road\" ,\"2011-07-07\" , 0.00, 0.00);");
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address, birthday,  PPSbalance, Dollarbal) values (\"james\",\"jam1234\",\"james\" ,\"chris\" ,\"123,troy\" ,\"2009-05-11\" , 5000.00, 300.00);");
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address, birthday,  PPSbalance, Dollarbal) values (\"mary\",\"mar1234\",\"mary\" ,\"particia\" ,\"145,good hope road\" ,\"2008-01-15\" , 6000.00, 150.00);");
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address, birthday,  PPSbalance, Dollarbal) values (\"rob\",\"rob1234\",\"robert\" ,\"wright\" ,\"111,green street\" ,\"2017-08-17\" , 80000.00, 1000.00);");
			      statement.executeUpdate("INSERT INTO USERS(username, password,  firstName, lastName, address, birthday,  PPSbalance, Dollarbal) values (\"david\",\"dav1234\",\"david\" ,\"joe\" ,\"98,old main\" ,\"2014-02-11\" , 0.00, 100.00);");


			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"david\",\"rob\",\"2020-01-01\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"ameen\",\"emma\",\"2020-02-02\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"olivia\",\"emma\",\"2020-03-03\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"emma\",\"olivia\",\"2020-04-04\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"amelia\",\"david\",\"2020-05-05\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"ava\",\"rob\",\"2020-06-06\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"james\",\"emma\",\"2020-07-07\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"mary\",\"rob\",\"2020-08-08\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"rob\",\"david\",\"2020-09-09\");");
			      statement.executeUpdate("Insert into Follow(username, followingUsername, followdate) values (\"david\",\"emma\",\"2020-10-10\");");


			      statement.executeUpdate("Insert into PPS(ppsprice) values (\"1000000\")");

			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"111\",\"buy\",152369.00,563248 ,695874 ,\"root\" ,\"emma\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"222\",\"withdraw\", 14589,124587 ,325687 ,\"olivia\" ,\"olivia\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"333\",\"deposit\",135367,213248 ,995874 ,\"ameen\" ,\"emma\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"444\",\"transfer\",159874,25478 ,324789 ,\"amelia\" ,\"ava\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"555\",\"buy\",112258,225478 ,756321 ,\"root\" ,\"james\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"666\",\"transfer\",145879,456948 ,214584,\"james\" ,\"mary\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"777\",\"withdraw\",10000,562489 ,458963 ,\"david\" ,\"david\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"888\",\"deposit\",124875,245796 ,523784 ,\"rob\" ,\"james\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"999\",\"buy\",157569,564328 ,667894 ,\"root\" ,\"mary\");");
			      statement.executeUpdate("Insert into transaction(transid,transname,dollaramt,PPSamt,PPSbal,fromuser,touser) values (\"1010\",\"transfer\",364656,667686 ,998877 ,\"ava\" ,\"ameen\");");
			      
		} catch (Exception e) {
	         System.out.println(e);
	    } 
		
		}
		
		
	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	    

	}