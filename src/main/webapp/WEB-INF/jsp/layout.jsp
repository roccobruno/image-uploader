<%@ include file="incGlobalTagLib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="/webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* Space out content a bit */
        body {
            padding-top: 20px;
            padding-bottom: 20px;
        }

        /* Everything but the jumbotron gets side spacing for mobile first views */
        .header,
        .footer {
            padding-right: 15px;
            padding-left: 15px;
        }

        /* Custom page header */
        .header {
            border-bottom: 1px solid #e5e5e5;
        }
        /* Make the masthead heading the same height as the navigation */
        .header h3 {
            padding-bottom: 19px;
            margin-top: 0;
            margin-bottom: 0;
            line-height: 40px;
        }

        /* Custom page footer */
        .footer {
            padding-top: 19px;
            color: #777;
            border-top: 1px solid #e5e5e5;
        }

        /* Customize container */
            .container {
                max-width: 730px;
            }

        .container-narrow > hr {
            margin: 30px 0;
        }








        .container-narrow > hr {
            margin: 30px 0;
        }


        .error {
            color: red;
        }
        input[type=file].form-control {
            height: auto;
        }



    </style>

</head>
<body>

<div class="container">
    <div class="header">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="#">Home</a></li>
                <li role="presentation"><a href="/logout">Logout</a></li>

            </ul>
        </nav>
        <h3 class="text-muted">Image Uploader</h3>
    </div>

    <tiles:insertAttribute name="body" />



</div> <!-- /container -->




<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/webjars/jquery/2.1.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/webjars/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>














