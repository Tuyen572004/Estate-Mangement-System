<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:redirect url="/trang-chu"/>
<security:intercept-url pattern="/resources/**" access="permitAll" />