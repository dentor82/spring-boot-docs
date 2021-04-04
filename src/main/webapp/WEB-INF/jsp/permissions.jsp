<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Документы</title>
    <link rel='stylesheet' href='/<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap.min.css") %>'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">
    <script type='text/javascript' src='/<%= org.webjars.AssetLocator.getWebJarPath("popper.js") %>'></script>
    <script type='text/javascript' src='/<%= org.webjars.AssetLocator.getWebJarPath("jquery.min.js") %>'></script>
    <script type='text/javascript' src='/<%= org.webjars.AssetLocator.getWebJarPath("js/bootstrap.min.js") %>'></script>
</head>
<body>
    <%@include file="menu.jsp" %>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Пользователь</th>
                <th>Доступ</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="permission" items="${list}">
                <tr>
                    <td>${permission.getPrincipal()}</td>
                    <td>${permission.getMask().toString()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form:form method = "POST" action = "/permission/add">
        <form:input type="hidden" path = "documentId" />
        <table>
            <tr>
                <td><form:label path = "principal">Пользователь</form:label></td>
                <td><form:select path = "principal">
                        <form:options items = "${listUsers}" itemLabel = "userName" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><form:label path = "mask">Пользователь</form:label></td>
                <td><form:select path = "mask">
                        <form:options items = "${listPermissions}" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td colspan = "2">
                    <input type = "submit" value = "Submit"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>