package pack1;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/employ�ControllerServlet")
public class Employ�ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/projet")
	private DataSource dataSource; 

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			new Employ�DbUtil(dataSource);
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//read command
			String theCommand = request.getParameter("command");
			if (theCommand == null) {
				listEmploy�(request, response);
			}
			switch(theCommand) {
			case "LIST":
				listEmploy�(request, response);
				break;
			case "ADD":
				addEmploy�(request, response);
				break;
			case "LOAD":
				loadEmploye(request, response);
				break;
			case "UPDATE":
				updateEmploye(request, response);
				break;
			case "DELETE":
				deleteEmploye(request, response);
				break;
			default:
				listEmploy�(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteEmploye(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("employeId"));
		Employ�DbUtil.deleteEmploye(id);
		listEmploy�(request,response);
	}

	private void updateEmploye(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read employe info
		int id = Integer.parseInt(request.getParameter("employeId"));
		String nom = request.getParameter("lastname");
		String prenom = request.getParameter("firstname");
		String email = request.getParameter("email");
		//creat a new employe
		Employ� employe  = new Employ�(id,nom,prenom,email);
		// update DB
		Employ�DbUtil.updateEmploye(employe);
		//send back to list employe
		listEmploy�(request,response);
	}

	private void loadEmploye(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read employe id
		String employeId = request.getParameter("employeId");
		//get employe from DB
		Employ� employ� = Employ�DbUtil.getEmploy�(employeId);
		
		//place employe in request
		request.setAttribute("LE_EMPLOYE", employ�);
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employe-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addEmploy�(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read employe data
		String Nom = request.getParameter("lastname");
		String Prenom = request.getParameter("firstname");
		String Email = request.getParameter("email");
		//create a new employe object
		Employ� employ� = new Employ�(Nom, Prenom, Email);
		//add employ� to DB
		Employ�DbUtil.addEmploy�(employ�);
		//sendback to main
		listEmploy�(request, response);
	}

	private void listEmploy�(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get employ� from db util
		List<Employ�> Employ�s = Employ�DbUtil.getEmploy�();
		//add employ"s to the request
		request.setAttribute("EMPLOYE_LIST", Employ�s);
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employ�.jsp");
		dispatcher.forward(request, response);
		
	}

}
