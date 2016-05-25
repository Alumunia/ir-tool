<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="run-all" method="GET" id="formQuery">

    <c:forEach var="contentGoldenList" items="${ContentGoldenList}" varStatus="status">
        <input type="hidden" name="query[]" value="${contentGoldenList.query}">
    </c:forEach>
    <c:forEach var="contentGoldenList" begin="0" end="0" items="${ContentGoldenList}" varStatus="status">
        <input type="hidden" name="timeStamp" value="${contentGoldenList.timeStamp}">
    </c:forEach>



</form>

<form action="run-all-evaluation" method="GET" id="formQueryEvaluation">

    <c:forEach var="contentGoldenList" items="${ContentGoldenList}" varStatus="status">
        <input type="hidden" name="query[]" value="${contentGoldenList.query}">
    </c:forEach>
    <c:forEach var="contentGoldenList" begin="0" end="0" items="${ContentGoldenList}" varStatus="status">
        <input type="hidden" name="timeStamp" value="${contentGoldenList.timeStamp}">
    </c:forEach>



</form>
<button type="submit" class="btn btn-default" form="formQueryEvaluation"><i class="fa fa-fast-forward"></i> Run All-Evaluation</button>
<button type="submit" class="btn btn-default" form="formQuery"><i class="fa fa-fast-forward"></i> Run All</button>
<button class="btn btn-default" type="submit"><i class="fa fa-list"></i> Properties</button>
<a type="button" href="upload-file" class="btn btn-default" type="submit"><i class="fa fa-upload"></i> Upload File</a>
<a type="button" href="retrieve" class="btn btn-default" type="submit"><i class="fa fa-dedent"></i> Field List</a>
<a type="button" href="query-list" class="btn btn-default" type="submit"><i class="fa fa-random"></i> Query List</a>
<button class="btn btn-default" type="submit"><i class="fa fa-file-text-o"></i> View Report</button>
<button class="btn btn-default" type="submit"><i class="fa fa-save"></i> Save Report</button>
<a type="button" href="upload-history" class="btn btn-default" type="submit"><i class="fa fa-history"></i> History</a>
<button class="btn btn-default" type="submit"><i class="fa fa-gear"></i> Configuration</button>  
<a type="button" href="about" class="btn btn-default" type="submit"><i class="fa fa-users"></i> About</a>
<button class="btn btn-default" type="submit"><i class="fa fa-question-circle"></i> Help</button>  
