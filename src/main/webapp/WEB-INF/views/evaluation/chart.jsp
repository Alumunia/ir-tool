<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../../layout/head.jsp" />
    </head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <body class="skin-blue">

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">

            <!-- Main content -->
            <section class="content">

                <div class="row">

                    <div class="col-md-6">
                        <!-- LINE CHART -->
                        <div class="box box-info">
                            <div class="bs-example" data-example-id="striped-table">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Query</th>
                                            <th>Interpolated</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="entry" items="${listQueryAndPrecision}">
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>${entry.key}</td>
                                                <td>${entry.value}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div> 

                        </div><!-- /.box -->


                    </div><!-- /.col (RIGHT) -->
                </div><!-- /.row -->

            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->


        <!-- jQuery 2.1.3 -->
        <script src="plugins/jQuery/jQuery-2.1.3.min.js"></script>
        <!-- Bootstrap 3.3.2 JS -->
        <script src="js/bootstrap.min.js"></script>
        <!-- Morris.js charts -->
        <script src="js/evaluation/raphael-min.js"></script>
        <script src="plugins/morris/morris.min.js" type="text/javascript"></script>

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
