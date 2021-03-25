<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Документы</title>
    <link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap.min.css") %>'>
    <link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap-glyphicons.css") %>'>
    <script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("jquery.min.js") %>'></script>
    <script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("js/bootstrap.min.js") %>'></script>
</head>
<body>
<table class="table table-striped">
    <thead>
        <tr>
            <th>Название</th>
            <th>Дата</th>
            <th>Автор</th>
            <th>Описание</th>
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
                <td></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>