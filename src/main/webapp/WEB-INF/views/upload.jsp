<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <div class="row">
                        <div class="col-sm-12">
                            <table class="table table-striped" style="margin-top: 0;">
                                <tbody>
                                <div class="row marketing">
                                    <div class="col-md-12">
                                        <form method="post" action="upload-file-process" enctype="multipart/form-data">
                                       
                                            <div class="form-group">
                                                <label for="exampleInputFile">File input</label>
                                                <input type="file" required name="fileUpload">
                                            </div>
                                            <button value="Upload" type="submit" class="btn btn-primary">Upload</button>
                                        </form>
                                    </div>
                                </div>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>

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
