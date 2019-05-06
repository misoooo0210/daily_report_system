package controllers.companies;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Company;
import utils.DBUtil;

/**
 * Servlet implementation class CompaniesShowServlet
 */
@WebServlet("/companies/show")
public class CompaniesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompaniesShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Company c = em.find(Company.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("company", c);
        request.setAttribute("_token", request.getSession().getId());
        //取引先IDをセッションスコープに登録
        request.getSession().setAttribute("id", c.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/companies/show.jsp");
        rd.forward(request, response);
    }

}
