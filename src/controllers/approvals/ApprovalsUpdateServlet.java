package controllers.approvals;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
import models.validators.ApprovalValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ApprovalsUpdateServlet
 */
@WebServlet("/approvals/update")
public class ApprovalsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovalsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Report r = em.find(Report.class, (Integer)(request.getSession().getAttribute("report_id")));
            Approval a = em.find(Approval.class, (Integer)(request.getSession().getAttribute("approval_id")));

            a.setApprover((Employee)request.getSession().getAttribute("login_employee"));
            Date approved_date = new Date(System.currentTimeMillis());
            String ad_str = request.getParameter("approved_date");
            if(ad_str != null && !ad_str.equals("")) {
                approved_date = Date.valueOf(request.getParameter("approved_date"));
            }
            a.setApproved_date(approved_date);
            a.setApproval_result(Integer.parseInt(request.getParameter("approval_result")));
            a.setApproval_comment(request.getParameter("approval_comment"));

            List<String> errors = ApprovalValidator.validate(a);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("report", r);
                request.setAttribute("approval", a);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/approvals/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("approval_id");

                response.sendRedirect(request.getContextPath() + "/approvals/index");
            }

        }
    }

}
