<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="http" value="${pageContext.request.scheme}" />
<c:set var="localhost" value="${pageContext.request.serverName}" />
<c:set var="port" value="${pageContext.request.serverPort}" />
<c:set var="uri" value="${pageContext.request.requestURI}" />