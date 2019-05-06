<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${company != null}">
                <h2>${company.name}の詳細ページ</h2>

                <table id=>
                    <tbody>
                        <tr>
                            <th>取引先名</th>
                            <td><c:out value="${company.name}" /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${company.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/companies/edit?id=${company.id}' />">取引先名を編集する</a></p>
                <p><a href="#" onclick="confirmDestroy();">この取引先を削除する</a></p>
                <form method="POST" action="<c:url value='/companies/destroy' />">
                    <input type="hidden" name="_token" value="${_token}" />
                </form>
                <script>
                    function confirmDestroy() {
                        if(confirm("本当に削除してよろしいですか？")) {
                           document.forms[0].submit();
                           }
                        }
                </script>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/companies/new' />">一覧に戻る</a></p>

    </c:param>
</c:import>