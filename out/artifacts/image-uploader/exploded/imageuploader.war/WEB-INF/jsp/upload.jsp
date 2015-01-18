<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: r.bruno@london.net-a-porter.com
  Date: 17/01/2015
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>

<form:form method="POST" enctype="multipart/form-data"
      action="/upload" modelAttribute="form">

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

    <div class="form-group">
        <label for="file">File to upload:</label>
        <input type="file" id="file" name="file" class="form-control">
        <form:errors path="file"/> <br/>

        <label for="caption"> Caption: </label>
        <input type="text" name="caption" id="caption" class="form-control">
        <form:errors path="caption"/><br/>

        <label for="alttag"> Alt Tag: </label>
        <input type="text" name="alttag" id="alttag" class="form-control">
        <form:errors path="alttag"/><br/>

        <div class="checkbox">
            <label>
                <input type="checkbox"  name="defaultName" id="defaultName"> Default filename as alt_tag/caption
            </label>
        </div>

        <br/> <input type="submit"
                     value="Upload"> Press here to upload the file!
    </div>
</form:form>
