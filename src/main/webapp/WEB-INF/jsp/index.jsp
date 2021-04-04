<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <th><a href="/?sort=name">Название</a></th>
                <th><a href="/?sort=date">Дата</a></th>
                <th><a href="/?sort=user_userName">Автор</a></th>
                <th><a href="/?sort=description">Описание</a></th>
                <th>Файл</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="doc" items="${documents}">
                <tr>
                    <td>${doc.getName()}</td>
                    <td>${doc.getDate()}</td>
                    <td>${doc.getAuthor()}</td>
                    <td>${doc.getDescription()}</td>
                    <td>${doc.getFileName()}
                        <a href="/permission/list/${doc.getId()}"><i class="bi bi-key"></i></a>
                        <a href="/download?documentId=${doc.getId()}"><i class="bi bi-file-arrow-down"></i></a>
                        <a href="/view?documentId=${doc.getId()}"><i class="bi bi-file-play"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>