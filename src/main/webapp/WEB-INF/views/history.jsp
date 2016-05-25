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

                    <hr>
                    <!--Menu bar-->
                    <div class="row menu-bar">
                        <jsp:include page="../layout/menu_bar.jsp" />
                    </div>
                    <!--/menu bar-->
                    <hr>
                    <div class="row">
                        <div class="col-sm-12">
                            <!-- content start here -->
                            <div class="bs-example" data-example-id="striped-table">

                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>File Name</th>
                                            <th>TimeStamp</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="fileGoldenList" items="${FileGoldenList}" varStatus="numbering" begin="0">
                                            <tr>

                                                <th scope="row">${numbering.index + 1}</th>
                                                <td>${fileGoldenList.fileName}</td>
                                                <td>${fileGoldenList.timeStamp}</td>
                                                <td><a type="button"  href="query-list?timeStamp=${fileGoldenList.timeStamp}" class="btn btn-info"><span class="glyphicon glyphicon-play" aria-hidden="true"></span></a></td>

                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>

                            </div><!-- /example -->
                            <!--/ end content -->
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
