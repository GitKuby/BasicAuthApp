<%@include file="header.jsp" %>
   
    <h1>User account:</h1>
    
    <b>Username: </b>${sessionScope.user.username}<br />
    <b>Password: </b>${sessionScope.user.password}<br />
    <b>Account type:</b> ${sessionScope.user.userType}<br />
    <b>Email:</b> ${sessionScope.user.email}<br />                     
        
</div></div>
</body>
</html>