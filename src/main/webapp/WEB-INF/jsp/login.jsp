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
    <c:if test="${not empty errorMessge}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessge}</div></c:if>
    <div class="container">
      <form method="POST" action="/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>

<input type="hidden"
        name="${_csrf.parameterName}"
        value="${_csrf.token}"/>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="/registration">Create an account</a></h4>
        </div>
      </form>
    </div>
</body>
</html>