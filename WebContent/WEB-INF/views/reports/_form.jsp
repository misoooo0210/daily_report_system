<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />

<label for="report_date">日付</label><br />
<input type="date" name="report_date" value="<fmt:formatDate value='${report.report_date}' pattern='yyyy-MM-dd' />">
<br /><br />

<label for="company">取引先名</label><br />
<select name="company" size="5">
    <c:set var="companies" value=""/>
    <c:forEach var="company" items="${companylist}">
        <option value="${company.id}">${company.name}</option>
    </c:forEach>
</select>&nbsp;<a href="<c:url value='/companies/new' />">取引先登録はここから</a>
<br /><br />

<label for="meet_time">商談日程</label><br />
<input type="date" name="meet_time" value="<fmt:formatDate value='${report.meet_time}' pattern='yyyy-MM-dd'/>">
<br /><br />

<label for="meet_at">商談場所</label><br />
<input type="text" name="meet_at" value="${report.meet_at}" />
<br /><br />

<label for="title">タイトル</label><br />
<input type="text" name="title" value="${report.title}" />
<br /><br />

<label for="content">内容</label><br />
<textarea name="content" rows="10" cols="50">${report.content}</textarea>
<br /><br />

<label for="progress">進捗状況</label><br />
<input type="text" name="progress" value="${report.progress}" />
<br /><br />

<label for="next_time">次回商談予定日</label><br />
<input type="date" name="next_time" value="<fmt:formatDate value='${report.next_time}' pattern='yyyy-MM-dd' />">
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>