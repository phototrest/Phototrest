<%-- 
    Document   : usermain
    Created on : 21-Nov-2017, 3:05:31 PM
    Author     : chris
--%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Phototrest</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="auto.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="layout.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 7]>
   <link href="ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->
<style type="text/css">
.box{ width:970px; margin:0 auto;padding-top: 52px}
.box ul li { width:220px; height:245px; float:left; margin:10px; border:1px solid #e6e6e6; list-style:none; }
.box ul li img{ width:100%; min-height:245px;}
</style>
</head>
<body id="page1" onload="new ElementMaxHeight()">
<div id="main">
   <!-- HEADER -->
   <div id="header">     
      <div class="left">        
         <div class="right">             
            <div class="row-1 wrapper">                
                <ul>                    
                    <li><a href="upload.jsp"><img src="upload1.png" alt=""/></a></li>   
                    <li><a href="#"><img src="notification.png" alt=""/></a></li>  
                   &nbsp;&nbsp;&nbsp;<span><a href="LoginOutServlet"><font color="white"><u>Log Out</u></font></a></span>
                  <!--<li><a href="#"><img src="img/stumble-upon.gif" alt="" /></a></li>
                  <li><a href="#"><img src="img/myspace.gif" alt="" /></a></li>
                  <li><a href="#"><img src="img/twitter.gif" alt="" /></a></li>-->
               </ul>              
                <h1><a href="/Phototrest/ShowUserPhotosServlet"><strong><font color="yellowgreen">Photo</font></strong><strong><font color="gray">trest</font></strong></a></h1>                                          
            </div>                
            <div class="row-2">
               <ul class="wrapper">
                  <li class="m1"><a href="landing.jsp">Landing Page<span>Page d'accueil</span></a></li>
                  <li class="m3"><a href="#">News<span>Actualités</span></a></li>
                  <li class="m4"><a href="#">Entertainment<span>Divertissement</span></a></li>
                  <li class="m5"><a href="#">Scenery<span>Beauté</span></a></li>
                  <li class="m2 "><a href="#">Technology<span>Technologie</span></a></li>          
		  <li class="m6 last"><a href="#">About<span>à propos</span></a></li>
               </ul>
            </div>
            <div class="row-3 wrapper">
                <div class="fleft"><font color="#2e2e2e"><span>Main Page</span></font><font color="yellowgreen"><b>Hello,${FullName }</b></font>               
                </div>              
                    <form action="ShowTagSearchPicturesServlet" id="search-form" method="POST">                    
                  <fieldset>                   
                  <div class="fright">  
                      <table><tr><td>
                        <input style="width:150px;height:20px;border:1px yellowgreen solid;border-radius:2px;" type="text" name="tagname"/></td>            
                     <td width="%"><input onmouseover="this.style.backgroundColor='#f2f3f3';" onmouseout="this.style.backgroundColor='#2e2e2e';" type="submit" style="border-radius:2px;width:80px;height:24px;border:2px blue none;background-color:#2e2e2e;color:yellowgreen;font-size:15px;cursor:pointer;" value="Search" name="search" /></td></tr></table>
                    <!-- </span><a href="ShowTagSearchPicturesServlet" onclick="document.getElementById('search-form').submit()"><img src="img/button.gif" alt="" /></a>--></div>
                  </fieldset>
               </form>       
           </div> 
             <br>
             <div style="text-align:center;">              
                 <% String check = (String)session.getAttribute("check");%>
                     <%if(check == "subscribed"){%>
                         <form name="check" action="SubscribeServlet" method="POST">
                             <input onmouseover="this.style.backgroundColor='#2e2e2e';" onmouseout="this.style.backgroundColor='#90EE90';" type="submit" style="border-radius:2px;width:100px;height:30px;border:2px blue none;background-color:#90EE90;font-size:15px;cursor:pointer;" value="UnSubscribe" name="check" />
                         </form>
                     <%}%>
                  
                     <%if(check == "unsubscribed"){%>
                         <form name="check" action="SubscribeServlet" method="POST">
                             <input onmouseover="this.style.backgroundColor='#2e2e2e';" onmouseout="this.style.backgroundColor='#90EE90';" type="submit" style="border-radius:2px;width:100px;height:30px;border:2px blue none;background-color:#90EE90;font-size:15px;cursor:pointer;" value="Subscribe" name="check" />
                         </form>
                     <%}%>   
                        
                    
             </div>
             <br>
         </div>    
         </div>      
</div>
</div>
    <div class="box">
	<ul>
            <%
            ArrayList<String> URLArray = (ArrayList<String>)session.getAttribute("URLArray");
            if(URLArray != null){
                for(String url: URLArray){
                    URL photourl=new URL(url);%>
                    <li><img src="<%=photourl%>" /></li>
            <%}
            }
            else {%>
                    <div style="color:red; text-align: center; "> No related pictures related to <%=request.getParameter("tagname")%></div>
            <%}%>
    </ul>
</div>
<div class="clearfix"></div>
<div class="wrap"></div>
   <br>    
   
</body>
</html>

