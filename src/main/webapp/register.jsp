<%@include file="header.jsp" %>

    <h1>Register new user here:</h1>
            
    <form action="/signIn" method="post">
         <div class="container">
            <label><b>Username:</b></label> 
            <input type="text" id="username" name="username" placeholder="Enter your username"/>
            <label><b>Password:</b></label>
            <input type="password" id="password" name="password" placeholder="Enter password"/>
            <label><b>Confirm password:</b></label> 
            <input type="password" id="confirmedPassword" name="confirmedPassword" placeholder="Repeat password"/>
            <label><b>Email: </b></label>
            <input type="text" id="email" name="email" placeholder="Enter email"/>
            <button type="submit">Register</button>
         </div>
    </form>
</div></div>
</body>
</html>