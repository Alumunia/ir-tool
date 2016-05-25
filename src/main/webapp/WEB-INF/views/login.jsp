
<html lang="en">
    <head>
        <meta charset="UTF-8" />

        <title>CAS &#8211; Central Authentication Service</title>
        <link rel="stylesheet" href="https://static-uat1.gdn-app.com/blistrap-1.0.0//stylesheets/blistrap.css" />
        <link rel="stylesheet" href="https://static-uat1.gdn-app.com/blistrap-1.0.0//stylesheets/vendor/bootstrap.min.css" />
        <link href="http://fonts.googleapis.com/css?family=Oswald:400,300,700" rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://cas-uat1.gdn-app.com/cas/css/style.css" />
        <link rel="icon" href="/cas/favicon.ico" type="image/x-icon" />

        <!--[if lt IE 9]>
          <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.1/html5shiv.js" type="text/javascript"></script>
        <![endif]-->
    </head>
    <body id="cas">
        <span class='top-strip'></span>
        <div class="container">
            <div class="header-section">
                <div class='row'>
                    <div class='col-lg-12 col-md-12 col-sm-12 '>
                        <div class='inner-header'>
                            <a href="#" id='logo'></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="body-section">


                <div class='row'>
                    <div class='inner-body'>
                        <div class='col-lg-7 col-md-12 col-sm-12 '>
                            <div class='left-section'>
                                <div>

                                </div>
                            </div>
                        </div>
                        <div class='col-lg-5 col-md-12 col-sm-12 '>
                            <div class='right-section'>
                                <form class="form-signin" method="POST" action="process-login" >
                                    <h3>Enter your Username and Password</h3>
                                    <div class="message-area">
                                    </div>
                                    <label  for="username"><span class="accesskey">U</span>sername:</label>
                                    <input id="userName" name="userName" class="text-field" tabindex="1" accesskey="u" type="text" value="" size="25" autocomplete="off"/>
                                    <label for="password"><span class="accesskey">P</span>assword:</label>
                                    <input id="password" name="password" class="text-field" tabindex="2" accesskey="p" type="password" value="" size="25" autocomplete="off"/>
                                    <input id="warn" name="warn" value="true" tabindex="3" accesskey="w" type="checkbox" />
                                    <label for="warn"><span class="accesskey">W</span>arn me before logging me into other sites.</label>
                                    <input type="hidden" name="lt" value="LT-4734-NAdiK4xoqIbOGrTSOcBe7wVM5UW6Pq-cas-uat.gdn-app.com" />
                                    <input type="hidden" name="execution" value="e3s1" />
                                    <input type="hidden" name="_eventId" value="submit" />
                                    <div>
                                        <input class="button-blue" name="submit" accesskey="l" value="LOGIN" tabindex="4" type="submit" />
                                        <input class="button-grey" style="padding: 9px 38px;" name="reset" accesskey="c" value="CLEAR" tabindex="5" type="reset" />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>





            </div> <!-- END #content -->

            <div class='footer-section'>

                <p>Copyright &copy; 2011-2015 PT. Global Digital Niaga. Powered by <span id="small-logo"></span></p>
            </div>
        </div>
    </body>

</html>


