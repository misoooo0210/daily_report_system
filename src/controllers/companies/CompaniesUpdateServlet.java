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
 * Servlet implementation class CompaniesUpdateServlet
 */
@WebServlet("/companies/update")
public class CompaniesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompaniesUpdateServlet() {
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

            // セッションスコープから取引先IDを取得して
            // 該当のIDの取引先1件のみをDBから取得
            Company c = em.find(Company.class, (Integer)(request.getSession().getAttribute("id")));

            // フォームの内容を各フィールドに上書き
            String name = request.getParameter("name");
            c.setName(name);

            // DB更新
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "更新が完了しました。");

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("id");

            // .../companies/newへリダイレクト
            response.sendRedirect(request.getContextPath() + "/companies/new");
        }
    }

}