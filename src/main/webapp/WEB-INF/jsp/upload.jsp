<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

    function showSecondForm() {
        $("#additionalImageForm").show();
        $("#additionalImageFormButton").hide();
    }

</script>
<form:form method="POST" enctype="multipart/form-data"
      action="/upload" modelAttribute="form">

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>



    <div class="form-group" style="border-color: #2e6da4 !important;border-style: dotted;padding: 10px; ">
        <form:label path="file"><spring:message key="upload.image.label.file"/>:</form:label>
        <form:input  type="file" id="file" path="file" class="form-control"/>
        <form:errors path="file" cssStyle="color: red !important;"/>

         <br/>

        <form:label path="caption"> <spring:message key="upload.image.label.caption"/>: </form:label>
        <form:input  type="text" path="caption" id="caption" class="form-control"/>
        <form:errors path="caption" cssStyle="color: red !important;"/>

        <br/>


        <form:label path="altTag"> <spring:message key="upload.image.label.alttag"/>: </form:label>
        <form:input  type="text" path="altTag" id="altTag" class="form-control"/>
        <form:errors path="altTag" cssStyle="color: red !important;"/>

        <div class="checkbox">
            <label>
                <form:checkbox  path="defaultName" id="defaultName"/><spring:message key="upload.image.label.defaultname"/>
            </label>
        </div>

        <br/>
    </div>

    <!--additional image -->
    <div id="additionalImageForm" class="form-group" style="display: none; border-color: #2e6da4 !important;border-style: dotted;padding: 10px; ">
        <form:label path="additionalFile"><spring:message key="upload.image.label.file"/></form:label>
        <form:input type="file" id="file" path="additionalFile" class="form-control"/>
        <form:errors path="additionalFile" cssStyle="color: red !important;"/><br/>

        <form:label path="captionForAdditionalImage"><spring:message key="upload.image.label.caption"/>: </form:label>
        <form:input type="text" path="captionForAdditionalImage" id="caption" class="form-control"/>
        <form:errors path="captionForAdditionalImage" cssStyle="color: red !important;"/><br/>

        <form:label path="altTagForAdditionalImage"> <spring:message key="upload.image.label.alttag"/>: </form:label>
        <form:input type="text" path="altTagForAdditionalImage" id="altTag" class="form-control"/>
        <form:errors path="altTagForAdditionalImage" cssStyle="color: red !important;"/>

        <div class="checkbox">
            <label>
                <form:checkbox  path="defaultNameForAdditionalImage" id="defaultName"/> <spring:message key="upload.image.label.defaultname"/>
            </label>
        </div>

        <br/>
    </div>

    <div class="form-group" id="additionalImageFormButton"><input type="button" class="btn btn-lg btn-primary btn-block" style="width: 180px !important;height: 40px"
                                    onclick="showSecondForm()" value="Add another image"></div>

    <div class="form-group" style="float: right">
        <div style="margin-bottom: 5px">
                <a href="/images"> ${success}</a>
        </div>

        <div style="color: red">
                ${error}
        </div>

        <c:if test="${permitted}">
         <button class="btn btn-lg btn-primary btn-block" style="width: 90px !important;float: right" type="submit"> Upload</button>
         </c:if>
        <c:if test="${!permitted}">
            <span style="color: red">
            <spring:message key="upload.image.errors.limit.reached"/>
                </span>

                </c:if>
    </div>
</form:form>
