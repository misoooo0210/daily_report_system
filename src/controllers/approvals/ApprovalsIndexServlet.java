package controllers.approvals;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Approval;
import utils.DBUtil;

/**
 * Servlet implementation class ApprovalsIndexServlet
 */
@WebServlet("/approvals/index")
public class ApprovalsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalsIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        //pagination
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Approval> stillNotApproved = em.createNamedQuery("getAllNotApprovedReports", Approval.class)
                                .setFirstResult(15 * (page - 1))
                                .setMaxResults(15)
                                .getResultList();
        List<Approval> alreadyApproved = em.createNamedQuery("getAllApprovedReports", Approval.class)
                                .setFirstResult(15 * (page - 1))
                                .setMaxResults(15)
                                .getResultList();

        /*long approvals_count = (long)em.createNamedQuery("getApprovalsCount", Long.class)
                                      .getSingleResult();*/

        em.close();

        request.setAttribute("stillNotApproved", stillNotApproved);
        request.setAttribute("alreadyApproved", alreadyApproved);
        //request.setAttribute("approvals_count", approvals_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/approvals/index.jsp");
        rd.forward(request, response);
    }

}
