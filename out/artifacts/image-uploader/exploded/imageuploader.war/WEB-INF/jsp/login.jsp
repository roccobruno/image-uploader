<%@ include file="incGlobalTagLib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Sign In</title>

    <link href="/webjars/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin .checkbox {
            font-weight: normal;
        }
        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

    </style>

</head>

<body>

<div class="container">
    <c:url value="/login" var="loginUrl"/>
    <form action="${loginUrl}" method="post" class="form-signin" >
        <c:if test="${param.error != null}">
            <p>
                Invalid username and password.
            </p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p>
                You have been logged out.
            </p>
        </c:if>
        <p>
            <label for="username"  class="sr-only">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus />
        </p>
        <p>
            <label for="password"  class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required/>
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>

        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>


</div> <!--

 table>
    <tr><td>User:</td><td><input type='text' name='username' value=''></td></tr>
    <tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
    <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
    <input name="_csrf" type="hidden" value="77c3639b-d9ea-469c-a77b-a88f5968933a" />
  </table>
 /container -->

</body>
</html>

