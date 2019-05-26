package controllers.reports;

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
import models.Company;
import models.Report;
import models.Vote;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsNewServlet
 */
@WebServlet("/reports/new")
public class ReportsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CSRF対策
        request.setAttribute("_token", request.getSession().getId());

        //おまじないとしてのインスタンス作成
        Report r = new Report();
        r.setReport_date(new Date(System.currentTimeMillis()));
        request.setAttribute("report", r);
        Approval a = new Approval();
        request.setAttribute("approval", a);
        Vote v = new Vote();
        request.setAttribute("vote", v);

        //取引先名の一覧をつくる
        EntityManager em = DBUtil.createEntityManager();
        List<Company> companylist = em.createNamedQuery("getAllCompanies", Company.class)
                                       .getResultList();
        em.close();
        request.setAttribute("companylist", companylist);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
        rd.forward(request, response);
    }
}
