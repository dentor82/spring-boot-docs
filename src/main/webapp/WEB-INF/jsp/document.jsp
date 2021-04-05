<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>

   <body>
      <h2>Новый документ</h2>
      <form:form method = "POST" modelAttribute="document" enctype="multipart/form-data" action = "/addDocument">
         <table>
            <tr>
               <td><form:label path = "name">Наименование</form:label></td>
               <td><form:input path = "name" /></td>
            </tr>
            <tr>
               <td><form:label path = "date">Дата</form:label></td>
               <td><form:input type="date" path = "date" /></td>
            </tr>
            <tr>
                <td><form:label path = "file">Файл:</form:label></td>
                <td><form:input type = "file" path = "file" /></td>
            </tr>
            <tr>
                <td><form:label path = "description">Описание</form:label></td>
                <td><form:input path = "description" /></td>
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