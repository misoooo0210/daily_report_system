<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>
        <h2>未承認日報 一覧</h2>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                </tr>
                <c:forEach var="approval" items="${NotApproved}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${approval.report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value="${approval.report.report_date}" pattern="yyyy-MM-dd" /></td>
                        <td class="report_title">${approval.report.title}</td>
                        <td class="report_action"><a href="<c:url value='/approvals/show?id=${approval.approval_id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table><br /><br />

        <h2>再提出日報 一覧</h2>
        <table id="report_list">
            <tbody>
                <tr>
                    <th class="report_name">氏名</th>
                    <th class="report_date">日付</th>
                    <th class="report_title">タイトル</th>
                    <th class="report_action">操作</th>
                </tr>
                <c:forEach var="approval" items="${Denied}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${approval.report.employee.name}" /></td>
                        <td class="report_date"><fmt:formatDate value="${approval.report.report_date}" pattern="yyyy-MM-dd" /></td>
                        <td class="report_title">${approval.report.title}</td>
                        <td class="report_action"><a href="<c:url value='/approvals/show?id=${approval.approval_id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table><br />

            (全 ${approvals_count} 件) <br />

    </c:param>
</c:import>