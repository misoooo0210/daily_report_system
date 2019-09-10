package controllers.votes;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import models.Vote;
import utils.DBUtil;

/**
 * Servlet implementation class VotesAddServlet
 */
@WebServlet("/votes/add")
public class VotesAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotesAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            try {
            EntityManager em = DBUtil.createEntityManager();

            Report r = em.find(Report.class, (Integer)(request.getSession().getAttribute("report_id")));
            Vote v = new Vote();
            v.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
            v.setReport(r);

            em.getTransaction().begin();
            em.persist(v);
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("report_id");

            response.sendRedirect(request.getContextPath() + "/reports/index");
            } catch (RuntimeException e) {
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/votes/add.jsp");
                rd.forward(request, response);
            }
    }
}
}