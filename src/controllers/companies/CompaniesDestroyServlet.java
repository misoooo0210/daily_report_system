package controllers.companies;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Company;
import utils.DBUtil;

/**
 * Servlet implementation class CompaniesDestroyServlet
 */
@WebServlet("/companies/destroy")
public class CompaniesDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompaniesDestroyServlet() {
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

            Company c = em.find(Company.class, (Integer)(request.getSession().getAttribute("id")));
            c.setDelete_flag(1);

            em.getTransaction().begin();
            em.remove(c);       // データ削除
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "削除が完了しました。");

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("id");

            // .../companies/newページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/companies/new");
        }
    }

}