<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
    <!--head-->
    <jsp:include page="../layout/head.jsp" />
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!--/head-->

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
                            <h3><a href="#">Compare Results:</a> demo golden answer.txt </h3>
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
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12">
                                <h4>IR TEAM</h4>
                                <div class="row marketing" >
                                    <c:forEach items="${members}"  var="member">
                                        <div class="col-lg-3" style="padding-top: 50px">
                                            <img  class="img-responsive center-block img-circle img-center" width="70px" height="70px"src="images/${member.img_url}.png" alt="..." class="img-circle">
                                            <h4 align="center">${member.userName}</h4>
                                            <p align="center">${member.nim}</p>
                                        </div>
                                    </c:forEach>
                                </div> <!-- /container -->
                            </div>
                            <div class="col-md-12">
                                <c:if test="${not empty day}">
                                    <h5> ${day}% hari menuju seminar</h5>
                                    <div class="progress" style="height:50px">
                                        <div class="progress-bar progress-bar-danger active progress-bar-striped" role="progressbar" aria-valuenow="${day}%" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">${day}% Complete (success)</span>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div><!-- /container -->
                </div>
            </div>
        </div><!-- /container -->

        <!--footer-->
        <jsp:include page="../layout/footer.jsp"/>
        <!--/footer-->

        <script>
            window.setTimeout(function () {
                $(".alert").fadeTo(400, 0).slideUp(400, function () {
                    $(this).remove();
                });
            }, 4000);
        </script>
    </body>
</html>
