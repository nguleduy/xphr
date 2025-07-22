<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Work Hours Report</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <h2 class="mb-4">Work Hours Report</h2>

            <!-- Filter Form -->
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

            <!-- Page Size Selector -->
            <form method="get" class="mb-3 d-flex justify-content-end align-items-center">
                <input type="hidden" name="startDate" value="${param.startDate}" />
                <input type="hidden" name="endDate" value="${param.endDate}" />
                <input type="hidden" name="page" value="${reportPage.number}" />
                <label for="size" class="me-2">Items per page:</label>
                <select name="size" id="size" onchange="this.form.submit()" class="form-select w-auto">
                    <option value="3" ${reportPage.size == 3 ? 'selected' : ''}>3</option>
                    <option value="6" ${reportPage.size == 6 ? 'selected' : ''}>6</option>
                    <option value="9" ${reportPage.size == 9 ? 'selected' : ''}>9</option>
                </select>
            </form>

            <!-- Results Table -->
            <c:if test="${not empty reportPage.content}">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                    <tr>
                        <th>Employee Name</th>
                        <th>Project Name</th>
                        <th>Total Hours</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="row" items="${reportPage.content}">
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
            <c:if test="${empty reportPage.content}">
                <div class="alert alert-info mt-4">No data available for the selected range.</div>
            </c:if>

            <!-- Pagination Controls -->
            <c:if test="${reportPage.totalPages > 1}">
                <nav>
                    <ul class="pagination justify-content-center mt-4">
                        <c:forEach begin="0" end="${reportPage.totalPages - 1}" var="i">
                            <li class="page-item ${i == reportPage.number ? 'active' : ''}">
                                <form method="get" action="${pageContext.request.contextPath}/api/v1/report" class="d-inline">
                                    <input type="hidden" name="startDate" value="${param.startDate}" />
                                    <input type="hidden" name="endDate" value="${param.endDate}" />
                                    <input type="hidden" name="page" value="${i}" />
                                    <input type="hidden" name="size" value="${reportPage.size}" />
                                    <button type="submit" class="page-link">${i + 1}</button>
                                </form>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </div>
    </body>
</html>
