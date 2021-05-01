<-- TestBeanScope3.jsp -->
<%@ page import="Chapter38.Count"%>
<!-- 
If scope="application" is changed to scope="session", the scope of the bean is
limited to the session from the same browser. The count will increase only if the page is
requested from the same browser. If scope="application" is changed to scope="page",

the scope of the bean is limited to the page, and any other page cannot access this bean. The page
will always display count 1. If scope="application" is changed to scope="request",
the scope of the bean is limited to the clientâs request, and any other request on the page will
always display count 1. -->
<!--  the following is equivalent to Count count = new Count(); -->

<jsp:useBean id="count" scope="session" class="Chapter38.Count">
</jsp:useBean>
<html>
<head>
<title>TestBeanScope</title>
</head>
<body>
	<h3>Testing Bean Scope in JSP (Application)</h3>
	<%
		count.increaseCount();
	%>
	You are visitor number
	<%=count.getCount()%><br /> From host:
	<%=request.getRemoteHost()%>
	and session:
	<%=session.getId()%>
</body>
</html>
