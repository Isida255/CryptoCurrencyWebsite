package net.javaguides.registration.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javaguides.registration.dao.EmployeeDao;
import net.javaguides.registration.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EmployeeDao employeeDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    /*
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    */
    
    public void init() {
    	employeeDao = new EmployeeDao();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
	doGet(request, response);
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 String action = request.getServletPath();
	        System.out.println(action);
	        
	       switch (action) {
		case "/login": 
		    System.out.println("The action is: login");
		    login(request, response);           	
		    break;
		case "/initialize":
		    System.out.println("The action is: initialize");
		    initialize(request, response);
		    break;
		case "/register":
			register(request, response);
			System.out.println("The action is: register");
			break;
		default:
		    System.out.println("Not sure which action, we will treat it as the login action");
		     	
		    break;
		}
	        System.out.println("doGet finished: 111111111111111111111111111111111111");
	    
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
	      String password = request.getParameter("password");
	      Employee employee = new Employee();
	      employee.setUsername(username);
	      employee.setPassword(password);

	      try {
	    	  
	         if (employeeDao.login(employee)) {
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			String usernameValue = (String) session.getAttribute("username");
			System.out.println(usernameValue);
			   String root = "root";
			if (usernameValue.equals(root))
			   {
			response.sendRedirect("views/SuccessFile.jsp");
			   }
			   else
				   response.sendRedirect("views/SuccessLogin_MainPage.jsp");
			//RequestDispatcher dispatcher = request.getRequestDispatcher("SuccessFile.jsp");
	         } else {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			request.setAttribute("url", "");
      	    request.setAttribute("message", "Incorrect credentials. Please try again");
      	    RequestDispatcher dispatcher = request.getRequestDispatcher("views/ErrorPage.jsp");
            dispatcher.forward(request, response);
			//request.setAttribute("url", "");
      	    //request.setAttribute("message", "Incorrect credentials. Please try again");
			//response.sendRedirect("views/Login.jsp");
	         }
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    int age = Integer.parseInt(request.getParameter("age"));


    Employee employee = new Employee();
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setUsername(username);
    employee.setPassword(password);
    employee.setAge(age);
  

    try {if (employeeDao.validate(employee)) {
        
        
        HttpSession session = request.getSession();
		session.setAttribute("username", username);
		request.setAttribute("url", "");
  	    request.setAttribute("message", "User already exists. Please login.");
  	    RequestDispatcher dispatcher = request.getRequestDispatcher("views/ErrorPage.jsp");
        dispatcher.forward(request, response);
        } 
        
        	else {
        		employeeDao.registerUser(employee);
                response.sendRedirect("views/Login.jsp");
        }
    }
    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    
}
	
	
	public void initialize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			employeeDao.initialize();
			response.sendRedirect("views/SuccessInitialize.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	
	/*
String username = request.getParameter("username");
      String password = request.getParameter("password");
      Employee employee = new Employee();
      employee.setUsername(username);
      employee.setPassword(password);

      try {
         if (employeeDao.login(employee)) {
		HttpSession session = request.getSession();
		session.setAttribute("username",username);
		response.sendRedirect("views/SuccessFile.jsp");
		//RequestDispatcher dispatcher = request.getRequestDispatcher("SuccessFile.jsp");
         } else {
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		response.sendRedirect("views/Login.jsp");
         }
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
     
    }
}
		
*/