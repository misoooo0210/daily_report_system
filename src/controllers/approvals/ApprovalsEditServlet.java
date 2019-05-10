package controllers.approvals;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Approval;
import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ApprovalsEditServlet
 */
@WebServlet("/approvals/edit")
public class ApprovalsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalsEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        Approval a = em.find(Approval.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        request.setAttribute("login_employee", login_employee);
        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("report_id", r.getReport_id());
        request.getSession().setAttribute("approval_id", a.getApproval_id());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/approvals/edit.jsp");
        rd.forward(request, response);
    }

}
