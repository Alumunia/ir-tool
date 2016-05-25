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
    <head>

        <!-- Meta-Information -->
        <title>Search UI - blibli.com</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="description" content="Portal Blibli.com">
        <!-- Vendor: Bootstrap Stylesheets http://getbootstrap.com -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <!--<link rel="stylesheet" href="css/bootstrap.min.css">-->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
        <!--<link href="css/fontawesome.css" rel="stylesheet">-->
        <link href="css/blistrap.css" rel="stylesheet" type="text/css" />
        <link href="http://fonts.googleapis.com/css?family=Oswald:400,300,700" rel='stylesheet' type='text/css'>
        <link rel="icon" type="image/png" href="images/ico.png">
        <!-- Our Website CSS Styles -->
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.js?version=2"></script>
        <!-- Morris charts -->
        <link href="plugins/morris/morris.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->


    </head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!--/head-->
    <body ng-app="portalWebApp">
        <div id="widget-container">
            <header>
                <jsp:include page="../../layout/navigation.jsp"/>
            </header>
        </div>
        <!-- Our Website Content Goes Here -->
        <div class="container-fluid">
            <div class="row-fluid column-grid">
                <div class="col-sm-12 ">
                    <hr>
                    <!--Menu bar-->
                    <div class="row menu-bar">
                        <jsp:include page="../../layout/menu_bar.jsp" />
                    </div>
                    <!--/menu bar-->
                    <hr>

                    <div class="row">
                        <div class="col-sm-12">
                            <div class="row marketing">
                                <div class="col-md-12">
                                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="headingOne">
                                                <h4 class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                        Precision-Recall graph
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <h4 align="center">Nilai bobot pada tiap Field</h4>
                                                        <div class="col-md-12">
                                                            <div class="bs-example" data-example-id="striped-table">
                                                                <table class="table table-striped">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>No</th>
                                                                            <th>Field</th>
                                                                            <th>Weight</th>

                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <c:forEach var="entry" items="${MapList}" varStatus="numbering">
                                                                            <tr>
                                                                                <th scope="row">${numbering.index + 1}</th>
                                                                                <td>${entry.key}</td>
                                                                                <c:forEach var="entryValue" items="${entry.value}">
                                                                                    <td>${entryValue}</td>
                                                                                </c:forEach>

                                                                            </tr>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </div><!-- /example -->
                                                        </div>
                                                        <h4 align="center">Nilai precision pada Eleven Standard Recall</h4>
                                                        <div class="col-md-12">

                                                            <div class="bs-example" data-example-id="striped-table">
                                                                <table class="table table-striped">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>No</th>
                                                                            <th>Query</th>
                                                                            <th>0.0</th>
                                                                            <th>0.1</th>
                                                                            <th>0.2</th>
                                                                            <th>0.3</th>
                                                                            <th>0.4</th>
                                                                            <th>0.5</th>
                                                                            <th>0.6</th>
                                                                            <th>0.7</th>
                                                                            <th>0.8</th>
                                                                            <th>0.9</th>
                                                                            <th>1.0</th>

                                                                        </tr>
                                                                    </thead>
                                                                    <tfoot>
                                                                        <tr class=success>
                                                                            <td colspan="2"><p align="center"><Strong>Average Interpolated Precision</strong></p></td>
                                                                                        <c:forEach items="${precisionCoordinate}" var="precisionCoordinate" varStatus="status">
                                                                                <td>${fn:substring(precisionCoordinate,0,4)}</td>
                                                                            </c:forEach>

                                                                        </tr>
                                                                        <tr class="info">
                                                                            <td colspan="2"><p align="center"><Strong>Average Precision</strong></p>
                                                                            <td colspan="11"><p align="center"><c:if test="${not empty finalAverage}" >${finalAverage}</c:if> </p></td>
                                                                            </tr>
                                                                        </tfoot>
                                                                        <tbody>

                                                                        <c:forEach var="list" items="${listQueryAndPrecision}" varStatus="numbering" >
                                                                            <tr>
                                                                                <th scope="row">${numbering.index + 1}</th>
                                                                                <td>${list.key}</td>
                                                                                <c:forEach var="listValue" items="${list.value}" varStatus="statusList">
                                                                                    <c:forEach varStatus="StatusListNumber" begin="0" end="10">
                                                                                        <td>${fn:substring(listValue[StatusListNumber.index],0,4)}</td>
                                                                                    </c:forEach>
                                                                                </c:forEach>
                                                                            </tr>
                                                                        </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </div> 
                                                        </div>
                                                        <h4 align="center">Eleven Recall Standard Graph</h4>
                                                        <div class="col-md-12">

                                                            <!-- LINE CHART -->
                                                            <c:choose>
                                                                <c:when test="${not empty precisionCoordinate}" >
                                                                    <div class="box box-info">
                                                                        <div class="box-header">
                                                                        </div>
                                                                        <div class="box-body chart-responsive">
                                                                            <div class="chart" id="line-chart" style="height: 300px;"></div>
                                                                        </div><!-- /.box-body -->
                                                                    </div><!-- /.box -->
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <h3>Table is still empty, you have to run the evaluation first :)</h3>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div><!-- /.col (RIGHT) -->
                                                        <div class="col-md-12">
                                                            <!-- LINE CHART -->

                                                        </div><!-- /.col (RIGHT) -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="headingTwo">
                                                <h4 class="panel-title">
                                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                        List Evaluation
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                                <div class="panel-body">
                                                    <div class="col-md-12">
                                                        <div class="bs-example" data-example-id="striped-table">
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <tr>
                                                                        <th>No</th>
                                                                        <th>Weight Parameter</th>
                                                                        <th>0.0</th>
                                                                        <th>0.1</th>
                                                                        <th>0.2</th>
                                                                        <th>0.3</th>
                                                                        <th>0.4</th>
                                                                        <th>0.5</th>
                                                                        <th>0.6</th>
                                                                        <th>0.7</th>
                                                                        <th>0.8</th>
                                                                        <th>0.9</th>
                                                                        <th>1.0</th>
                                                                        <th colspan="2">Mean Average Precision</th>

                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach varStatus="numbering" items="${listParameterWeight}" var="listParameterWeight" >
                                                                        <tr >

                                                                            <td>${numbering.index + 1}</td>
                                                                            <td>${listParameterWeight.weightParameter}</td>
                                                                            <c:forEach items="${listParameterWeight.averagePrecision}" var="averagePrecision">
                                                                                <td>${fn:substring(averagePrecision,0,4)}</td>
                                                                            </c:forEach>
                                                                            <td>${listParameterWeight.finalScore}</td>

                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div><!-- /example -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading" role="tab" id="headingThree">
                                                <h4 class="panel-title">
                                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                        Weight Configuration
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-md-6">

                                                            <form method="POST" action="process-retrieve">
                                                                <c:forEach var="entry" items="${MapList}">
                                                                    <div class="row">
                                                                        <div class="col-md-4">
                                                                            <div class="form-group">

                                                                                <input type="hidden" name="fieldList[]" class="form-control"  value="${entry.key}" readonly="readonly">
                                                                                <br>
                                                                                <p><strong>${entry.key}</strong></p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <label for="exampleInputEmail2">Value List</label>
                                                                                <input type="number" min="1" name="valueList[]" class="form-control" value="${entry.value}">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                                <button type="submit" class="btn btn-primary">Submit</button>
                                                            </form>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <!-- Drop Table -->
                                                            <form method="POST" action="executeParameter">
                                                                <c:forEach var="entry" items="${MapList}">
                                                                    <div class="row">
                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <input type="hidden" name="fieldList[]" class="form-control"  value="${entry.key}" readonly="readonly">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                                <div class="alert alert-danger" role="alert"><Strong>Once you click the button</Strong> There is no way to go back :(</div>
                                                                <button type="submit" class="btn btn-warning btn-lg btn-block">Process The Query</button>
                                                            </form>

                                                        </div>
                                                    </div><! -- /row -->
                                                </div>
                                            </div>
                                            <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-md-6">

                                                            <form method="POST" action="process-retrieve">
                                                                <c:forEach var="entry" items="${MapList}">
                                                                    <div class="row">
                                                                        <div class="col-md-4">
                                                                            <div class="form-group">

                                                                                <input type="hidden" name="fieldList[]" class="form-control"  value="${entry.key}" readonly="readonly">
                                                                                <br>
                                                                                <p><strong>${entry.key}</strong></p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <label for="exampleInputEmail2">Value List</label>
                                                                                <input type="number" min="1" name="valueList[]" class="form-control" value="${entry.value}">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                                <button type="submit" class="btn btn-primary">Submit</button>
                                                            </form>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <!-- Drop Table -->
                                                            <form method="POST" action="executeParameter">
                                                                <c:forEach var="entry" items="${MapList}">
                                                                    <div class="row">
                                                                        <div class="col-md-4">
                                                                            <div class="form-group">
                                                                                <input type="hidden" name="fieldList[]" class="form-control"  value="${entry.key}" readonly="readonly">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                                <div class="alert alert-danger" role="alert"><Strong>Once you click the button</Strong> There is no way to go back :(</div>
                                                                <button type="submit" class="btn btn-warning btn-lg btn-block">Process The Query</button>
                                                            </form>

                                                        </div>
                                                    </div><! -- /row -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- col-md-12 -->
                            </div><!--row marketing-->
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /container -->

        <!--footer-->
        <footer class="footer-section">
            <p>Copyright © 2011 <script>new Date().getFullYear() > 2010 && document.write("- " + new Date().getFullYear());</script> PT. Global Digital Niaga. Powered by &nbsp;&nbsp;<img class="image-logo" width="70px" src="images/logo.png"></p>
        </footer>

        <!-- Vendor: Javascripts -->
        <!-- Online Version -->
        <!--<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>-->
        <!-- Offline Version -->



        <!--- Evaluation --->

        <!-- jQuery 2.1.3 -->
        <script src="plugins/jQuery/jQuery-2.1.3.min.js"></script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Morris.js charts -->
        <script src="js/evaluation/raphael-min.js"></script>
        <script src="plugins/morris/morris.min.js" type="text/javascript"></script>
        <!--/footer-->

        <!--        <script>
                    window.setTimeout(function () {
                        $(".alert").fadeTo(900, 0).slideUp(400, function () {
                            $(this).remove();
                        });
                    }, 4000);
                </script>-->
        <script type="text/javascript">
                $(function () {
                    "use strict";
                    // LINE CHART
                    var line = new Morris.Line({
                        element: 'line-chart',
                        resize: true,
                        parseTime: false,
                        data: [
            <c:forEach items="${precisionCoordinate}" var="precisionCoordinate" varStatus="status">
                            {coordinate: '${recallCoordinate[status.index]}', item1: '${precisionCoordinate}'},
            </c:forEach>
                        ],
                        xkey: ['coordinate'],
                        ykeys: ['item1'],
                        labels: ['Item 1'],
                        lineColors: ['#3c8dbc'],
                        hideHover: 'auto'
                    });
                });
        </script>

    </body>
</html>
