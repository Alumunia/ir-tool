<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
    <jsp:include page="../layout/head.jsp" />
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <body ng-app="portalWebApp">
        <div id="widget-container">
            <header>
                <jsp:include page="../layout/navigation.jsp"/>
            </header>
        </div>
        <!-- Our Website Content Goes Here -->
        <div class="container-fluid">
            <div class="row-fluid column-grid">
                <div class="col-sm-12 ">
                    <div class="row sub-page-title">
                        <div class="col-sm-9">
                            <c:forEach var="FileGoldenList" items="${FileGoldenList}">
                                <h3><a href="#">Compare Results:</a> ${FileGoldenList.fileName}</h3>
                            </c:forEach> 

                            <c:if test="${not empty uploadSuccessMessage}">
                                <div class="alert-close alert-success alert" role="alert">${uploadSuccessMessage}</div>
                            </c:if>
                        </div>
                        <div class="col-sm-3 right">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <strong>Total position difference:</strong> -6299
                                    <br>
                                    <strong>Total rank quality:</strong> 24.20%
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <!--Menu bar-->
                    <div class="row menu-bar">
                        <jsp:include page="../layout/menu_bar.jsp" />
                    </div>
                    <!--/menu bar-->
                    <hr>
                    <div class="row">
                        <div class="col-sm-12">
                            <table class="table table-striped" style="margin-top: 0;">
                                <tbody>
                                    <c:forEach var="query" begin="0" items="${query}" varStatus="status">
                                    <p>${query.query}</p>
                                </c:forEach>
                                <c:forEach var="expectedId"  items="${expectedId}" varStatus="status">
                                    <p>expected</p><p>${expectedId.expectedId}</p>
                                </c:forEach>
                                </tbody>
                            </table>
                            <p>
                            <ul class="pagination pagination-sm pull-right">
                                <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                            </ul>
                            </p>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!--footer-->
        <jsp:include page="../layout/footer.jsp"/>
        <!--/footer-->

    </body>
</html>
