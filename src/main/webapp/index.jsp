<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <style>
        body {
            text-align: center;
        }

        .wrapper {
            max-width: 900px;
            margin: auto;
        }

        table {
            margin:auto;
        }

        table.stored {
            border-collapse: collapse;
            width: 100%;
        }

        .stored th, td {
            text-align: left;
            padding: 8px;
        }

        .stored tr:nth-child(even){background-color: #f2f2f2}
    </style>
</head>
<body>
    <div class="wrapper">
	<h2>Elektronická evidence kosmonautů</h2>

        <c:if test="${not_empty}">
            <table class="stored">
                <tr>
                    <th>Jméno</th>
                    <th>Příjmení</th>
                    <th>Datum narození</th>
                    <th>Superschopnost</th>
                    <th><th>
                </tr>
                <c:forEach items="${list}" var="cosmonaut" varStatus="status">
                    <tr>
                        <td>${cosmonaut.name}</td>
                        <td>${cosmonaut.surname}</td>
                        <td>${cosmonaut.birthday}</td>
                        <td>${cosmonaut.superpower}</td>
                        <td>
                            <c:url var="deleteUrl" value="/${status.index}/delete"/>
                            <form id="${cosmonautFormId}" action="${deleteUrl}" method="POST">
                                  <input id="cosmonaut" name="cosmonaut" type="hidden" value="${status.index}"/>
                                  <input type="submit" value="delete" onClick="return confirm('sure?')"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
		</c:if>

    <h3>Zaevidovat novýho kosmonauta</h3>

    <form:form method="post" action="/" modelAttribute="cosmonautForm">
         <table>
            <tr>
                <spring:bind path="name">
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name"/></td>
                    <td><form:errors path="name"/></td>
                </spring:bind>
            </tr>
            <tr>
                <spring:bind path="surname">
                    <td><form:label path="surname">Surname</form:label></td>
                    <td><form:input path="surname"/></td>
                    <td><form:errors path="surname"/></td>
                </spring:bind>
            </tr>
            <tr>
                <spring:bind path="birthday">
                    <td><form:label path="birthday">Birthday</form:label></td>
                    <td><form:input type="date" path="birthday"/></td>
                    <td><form:errors path="birthday"/></td>
                </spring:bind>
            </tr>
            <tr>
                <spring:bind path="superpower">
                    <td><form:label path="superpower">Superpower</form:label></td>
                    <td><form:input path="superpower"/></td>
                    <td><form:errors path="superpower"/></td>
                </spring:bind>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"/></td>
            </tr>
         </table>
    </form:form>

    <c:if test="${error}">
        <span>Invalid data</span>
    </c:if>
    </div>
</body>
</html>