<%@include file="header.jsp" %>
    
    <h1>Please enter your login and password:</h1>
  
      <form action="/logIn" method="post">
         <div class="imgcontainer">
            <img src="img/avatar.png" alt="Avatar" class="avatar">
         </div>
           <div class="container">
            <label><b>Username</b></label>
            <input type="text" id="username" placeholder="Enter username" name="username"/>
            <label><b>Password:</b></label> 
            <input type="password" id="password" placeholder="Enter password" name="password"/>
            <button type="submit">Login</button>
           </div>
           <div class="container" style="background-color:#f1f1f1">
            <p style="color:green">Default users: Regular, Premium, Admin</p>
            <p style="color:green"> password is: pass</p>
          </div>
        </form>
    </div></div>
</body>
</html>
