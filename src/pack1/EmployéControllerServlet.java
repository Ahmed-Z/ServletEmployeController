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


@WebServlet("/employéControllerServlet")
public class EmployéControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/projet")
	private DataSource dataSource; 

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			new EmployéDbUtil(dataSource);
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
				listEmployé(request, response);
			}
			switch(theCommand) {
			case "LIST":
				listEmployé(request, response);
				break;
			case "ADD":
				addEmployé(request, response);
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
				listEmployé(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteEmploye(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("employeId"));
		EmployéDbUtil.deleteEmploye(id);
		listEmployé(request,response);
	}

	private void updateEmploye(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read employe info
		int id = Integer.parseInt(request.getParameter("employeId"));
		String nom = request.getParameter("lastname");
		String prenom = request.getParameter("firstname");
		String email = request.getParameter("email");
		//creat a new employe
		Employé employe  = new Employé(id,nom,prenom,email);
		// update DB
		EmployéDbUtil.updateEmploye(employe);
		//send back to list employe
		listEmployé(request,response);
	}

	private void loadEmploye(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read employe id
		String employeId = request.getParameter("employeId");
		//get employe from DB
		Employé employé = EmployéDbUtil.getEmployé(employeId);
		
		//place employe in request
		request.setAttribute("LE_EMPLOYE", employé);
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employe-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addEmployé(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read employe data
		String Nom = request.getParameter("lastname");
		String Prenom = request.getParameter("firstname");
		String Email = request.getParameter("email");
		//create a new employe object
		Employé employé = new Employé(Nom, Prenom, Email);
		//add employé to DB
		EmployéDbUtil.addEmployé(employé);
		//sendback to main
		listEmployé(request, response);
	}

	private void listEmployé(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get employé from db util
		List<Employé> Employés = EmployéDbUtil.getEmployé();
		//add employ"s to the request
		request.setAttribute("EMPLOYE_LIST", Employés);
		//send to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employé.jsp");
		dispatcher.forward(request, response);
		
	}

}
