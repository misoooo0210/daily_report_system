<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <c:choose>
            <c:when test="${report != null}">
                <h2>未承認日報 詳細ページ</h2>

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
                            <td><c:out value="${report.progress}" /></td>
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
                    </tbody>
                </table>

                    <p><a href="<c:url value='/approvals/edit?id=${report.report_id}' />">この日報を承認する</a></p>

            </c:when>
            <c:otherwise>
                <h2>お探しのページは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/approvals/index' />">一覧に戻る</a></p>

    </c:param>
</c:import>