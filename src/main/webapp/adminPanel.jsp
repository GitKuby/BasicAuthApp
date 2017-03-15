<%@include file="header.jsp" %>
        <h1>Admin panel</h1>
         
        <form action="/adminPanel" method="post">
        <c:forEach var="record" items="${users}">
            <label><b>User: <c:out value="${record.username}" /></b></label><br/>
            <label><b>Email: <c:out value="${record.email}" /></b></label> <br/>
            <label><b>Password: <c:out value="${record.password}" /></b></label> <br/>
            <label><b>Account type: <c:out value="${record.userType}" /></b></label> <br/>
                    
                    <c:if test="${record.userType == 'REGULAR'}">
                                                                    
                         <button type="submit" name="submit" value="+${record.username}">Grant Premium</button>
                         
                    </c:if>
                    <c:if test="${record.userType == 'PREMIUM'}">
                                                                     
                        <button type="submit" name="submit" value="-${record.username}">Remove Premium</button>
                        
                    </c:if>
                    
                    <c:if test="${record.userType == 'ADMIN'}">
                        <button type="button" >Admin is admin...</button>
                    </c:if>
          
        </c:forEach>
        </form>                
      
             
             
</div>
    </body>
</html>
