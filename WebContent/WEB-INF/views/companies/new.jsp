<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>

        <h2>取引先 新規登録ページ</h2>

        <h3>既存取引先一覧</h3>
        <table id="company_list">
            <tbody>
                <tr>
                    <th class="company_name">取引先名</th>
                    <th class="company_action">操作</th>
                </tr>
                <c:forEach var="company" items="${companies}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="company_name">
                            <c:choose>
                                <c:when test="${company.delete_flag == 1}">
                                    (削除済み)
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${company.name}" />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="company_action">
                            <c:choose>
                                <c:when test="${company.delete_flag == 1}">
                                     <c:out value="" />
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/companies/show?id=${company.id}' />">詳細表示</a><br />
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br />

        <form method="POST" action="<c:url value='/companies/create' />">
            <c:import url="_form.jsp" />
        </form>

    </c:param>
</c:import>