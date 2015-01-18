<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="incGlobalTagLib.jsp"%>
<div class="form-group">

   <c:forEach items="${images}" var="image">


        <div id="caption">
           <label>File Name = ${image.originalName}</label>

           <form action="/delete/image/${image.fileName}" method="POST">
               <input type="hidden"
                      name="${_csrf.parameterName}"
                      value="${_csrf.token}"/>
               <button class=" btn btn-primary btn-block" style="width: 120px;" type="submit">Delete this Image </button>
           </form>

       </div>

       <figure>
           <img src='image/${image.fileName}' alt='${image.altTag}' />
           <figcaption>${image.caption}</figcaption>
       </figure>


   </c:forEach>

    <c:if test="${fn:length(images) eq 0}">
        <span style="color: red;">No images to display</span>
    </c:if>



</div>
