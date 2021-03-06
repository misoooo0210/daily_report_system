package controllers.reports;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Approval;
import models.Company;
import models.Employee;
import models.Report;
import models.validators.ReportValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCreateServlet
 */
@WebServlet("/reports/create")
public class ReportsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Report r = new Report();
            Approval a = new Approval();

            //Report側の登録
            r.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
            r.setCompany(request.getParameter("company"));
            Date report_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("report_date");
            if(rd_str != null && !rd_str.equals("")) {
                report_date = Date.valueOf(request.getParameter("report_date"));
            }
            r.setReport_date(report_date);
            Date meet_time = new Date(System.currentTimeMillis());
            r.setMeet_time(meet_time);
            Date next_time = new Date(System.currentTimeMillis());
            r.setNext_time(next_time);
            r.setTitle(request.getParameter("title"));
            r.setContent(request.getParameter("content"));
            r.setMeet_at(request.getParameter("meet_at"));
            r.setProgress(request.getParameter("progress"));
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setCreated_at(currentTime);
            r.setUpdated_at(currentTime);
            r.setApproval(a);

            //Approval側の登録
            a.setReport(r);
            a.setApprover(null);
            a.setApproved_date(null);
            a.setApproval_result(0);
            a.setApproval_comment("承認待ち");

            List<Company> companylist = em.createNamedQuery("getAllCompanies", Company.class)
                                           .getResultList();

            List<String> errors = ReportValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("report", r);
                request.setAttribute("approval", a);
                request.setAttribute("errors", errors);
                request.setAttribute("companylist", companylist);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(r);
                em.persist(a);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                response.sendRedirect(request.getContextPath() + "/reports/index");
            }
        }
    }
}