<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Work Hours Report</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <h2 class="mb-4">Work Hours Report</h2>
        </div>
        <!-- Form -->
        <form class="row g-3 mb-4" method="get" action="${pageContext.request.contextPath}/api/v1/report">
            <div class="col-md-5">
                <label for="startDate" class="form-label">Start Date</label>
                <input type="datetime-local" class="form-control" id="startDate" name="startDate"
                       value="${param.startDate}">
            </div>
            <div class="col-md-5">
                <label for="endDate" class="form-label">End Date</label>
                <input type="datetime-local" class="form-control" id="endDate" name="endDate"
                       value="${param.endDate}">
            </div>
            <div class="col-md-2 d-flex align-items-end">
                <button type="submit" class="btn btn-primary w-100">Filter</button>
            </div>
        </form>
        <!-- Results Table -->
        <c:if test="${not empty reportData}">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                <tr>
                    <th>Employee Name</th>
                    <th>Project Name</th>
                    <th>Total Hours</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${reportData}">
                    <tr>
                        <td>${row.employeeName()}</td>
                        <td>${row.projectName()}</td>
                        <td>
                            <fmt:formatNumber value="${row.totalHours()}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty reportData}">
            <div class="alert alert-info mt-4">No data available for the selected range.</div>
        </c:if>
    </body>
</html>