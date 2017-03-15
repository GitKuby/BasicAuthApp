<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Basic AuthSystem</title>

<link rel="stylesheet" type="text/css" href="css/generalStyles.css">
<link rel="stylesheet" type="text/css" href="css/topBar.css">

</head>

<body>
    <div id="content">
<header>
    <h1> Basic auth application </h1>
</header>
    
<%@include file="topBar.jsp" %>    
   
<div id="mainContainer">
        
<p style="color:red">${errText}</p>        
    
