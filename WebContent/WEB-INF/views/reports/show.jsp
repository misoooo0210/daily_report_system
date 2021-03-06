<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${report != null}">
                <h2>日報 詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>氏名</th>
                            <td><c:out value="${report.employee.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td>
                                <fmt:formatDate value="${report.report_date}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>取引先名</th>
                            <td><c:out value="${report.company}" /></td>
                        </tr>
                        <tr>
                            <th>商談日程</th>
                            <td>
                                <fmt:formatDate value="${report.meet_time}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>商談場所</th>
                            <td><c:out value="${report.meet_at}" /></td>
                        </tr>
                        <tr>
                            <th>内容</th>
                            <td>
                                <pre><c:out value="${report.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>進捗状況</th>
                            <td>
                                <c:if test="${report.progress == 0}">予定前倒し</c:if>
                                <c:if test="${report.progress == 1}">予定通り</c:if>
                                <c:if test="${report.progress == 2}">予定より遅延</c:if>
                                <c:if test="${report.progress == 3}">予定より大幅に遅延</c:if>
                                <c:if test="${report.progress == 4}">完了</c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>次回商談予定日</th>
                            <td>
                                <fmt:formatDate value="${report.next_time}" pattern="yyyy-MM-dd" />
                            </td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${report.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${report.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>承認状況</th>
                            <td>
                                <c:if test="${report.approval.approval_result == 0}">未承認</c:if>
                                <c:if test="${report.approval.approval_result == 1}">承認</c:if>
                                <c:if test="${report.approval.approval_result == 2}">否認</c:if>
                            </td>
                        </tr>
                        <c:if test="${report.approval.approval_result == 2}">
                            <th>(否認の場合)コメント</th>
                            <td>
                                <c:out value="${report.approval.approval_comment}" />
                            </td>
                        </c:if>
                        <tr>
                    </tbody>
                </table>

                <p><c:out value="${votes}" /> いいね！</p>
                <c:if test="${sessionScope.login_employee.id != report.employee.id}">
                    <form method="POST" action="<c:url value='/votes/add' />">
                        <p><button type= submit onclick="alert('いいねしました！')">いいね！</button></p>
                            <input type="hidden" name="_token" value="${_token}" />
                    </form>
                </c:if>

                <c:if test="${sessionScope.login_employee.id == report.employee.id}">
                    <p><a href="<c:url value='/reports/edit?id=${report.report_id}' />">この日報を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのページは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/reports/index' />">一覧に戻る</a></p>

    </c:param>
</c:import>