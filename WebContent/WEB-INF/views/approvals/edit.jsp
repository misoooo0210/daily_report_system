<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">

        <c:if test="${errors != null}">
        <div id="flush_error">
            入力内容にエラーがあります。<br />
            <c:forEach var="error" items="${errors}">
                ・<c:out value="${error}" /><br />
            </c:forEach>
        </div>
        </c:if>

        <c:choose>
            <c:when test="${report != null}">
                <h2>日報 承認ページ</h2>

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
                    </table><br /><br />

                <form method="POST" action="<c:url value='/approvals/update' />">
                <label for="approver">承認者名</label><br />
                <c:out value="${sessionScope.login_employee.name}" />
                <br /><br />

                <label for="approved_date">承認日</label><br />
                <input type="date" name="approved_date" value="<fmt:formatDate value='${approval.approved_date}' pattern='yyyy-MM-dd' />">
                <br /><br />

                <label for="approval_result">承認結果</label><br />
                <select name="approval_result">
                    <option value="1"<c:if test="${approval.approval_result == 1}"> selected</c:if>>承認</option>
                    <option value="2"<c:if test="${approval.approval_result == 2}"> selected</c:if>>否認</option>
                </select>
                <br /><br />

                <label for="approval_comment">コメント(否認の場合入力必須)</label><br />
                <textarea name="approval_comment" rows="5" cols="50">${approval.approval_comment}</textarea>
                <br /><br />

                <input type="hidden" name="_token" value="${_token}" />
                <button type="submit">投稿</button>
                </form>
            </c:when>

            <c:otherwise>
                <h2>お探しのページは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/approvals/index' />">一覧に戻る</a></p>

    </c:param>
</c:import>