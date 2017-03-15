<nav>
<ul>
  <li><a class="active" href="/">Home</a></li>
  <c:if test="${(user != null) && (user.userType!='REGULAR')}">
     <li><a href="premium.jsp">Premium</a></li>
  </c:if>
  <c:if test="${user == null}">
    <li><a href="logIn.jsp">Login</a></li>
    <li><a href="register.jsp">Register</a></li>
  </c:if>
  <c:if test="${(user != null) && (user.userType=='ADMIN')}">
    <li><a href="/adminPanel">Admin panel</a></li>
  </c:if>
  <c:if test="${user != null}">
    <li><a href="userProfile.jsp">Account</a></li>
    <li id="logout"><a href="/logout">Logout</a></li>
  </c:if>
</ul>
</nav>