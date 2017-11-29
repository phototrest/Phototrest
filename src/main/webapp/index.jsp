<%-- 
    Document   : index
    Created on : 23-Nov-2017, 10:52:28 PM
    Author     : chris
--%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="edu.uwaterloo.ece658.entity.User"%>
<!DOCTYPE html>
<html>
<head>
<title>Phototrest - Log In or Sign Up</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="auto.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="layout.css" rel="stylesheet" type="text/css" />
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
                  <%--<li><a href="#"><img src="img/stumble-upon.gif" alt="" /></a></li>
                  <li><a href="#"><img src="img/myspace.gif" alt="" /></a></li>
                  <li><a href="#"><img src="img/twitter.gif" alt="" /></a></li>--%>
               </ul>
                <h1><a href="index.jsp"><strong><font color="yellowgreen">Photo</font></strong><strong><font color="gray">trest</font></strong></a></h1>                            
               <ul>
                   <form name="userlogin" action="usermain" method="post">		
                        <font size=2 color="white"><b>UserName:</b></font>
                        <input type="text" name="userName" nullmsg="please enter your username!"/>&nbsp;
                        <font size=2 color="white"><b>Password:</b></font>
                        <input type="password" name="password"/>&nbsp;                       
                        <input type="submit" onmouseover="this.style.backgroundColor='gray';" onmouseout="this.style.backgroundColor='yellowgreen';" style="border-radius:2px;font-weight: bold;width:50px;height:19px;border:2px blue none;background:yellowgreen;color:whitesmoke;cursor:pointer;" name="login" value="Log In"/>            
                        <font color="red">${errormsg1 }</font>
                   </form>              
               </ul>
            </div>                
            <div class="row-2">
               <ul class="wrapper">
                  <li class="m1"><a href="index.jsp">Front Page<span>Page d'accueil</span></a></li>
                  <li class="m3"><a href="#">News<span>Actualités</span></a></li>
                  <li class="m4"><a href="#">Entertainment<span>Divertissement</span></a></li>
                  <li class="m5"><a href="#">Scenery<span>Beauté</span></a></li>
                  <li class="m2 "><a href="#">Technology<span>Technologie</span></a></li>
		  <li class="m6 last"><a href="#">About<span>à propos</span></a></li>
               </ul>
            </div>
            <div class="row-3 wrapper">
               <div class="fleft"><font color="#383838"><span>Front Page</span></font><font color="yellowgreen">Sign Up</font></div>
               <form action="" id="search-form">
                  <fieldset>
                  <div class="fright">
                      <label><a href="google.ca"></a> <font color="red"><u>Forgot your password? </u></font>&nbsp;|&nbsp;Search</label>
                     <span>
                     <input type="text" />
                     </span><a href="www.goole.ca" onclick="document.getElementById('search-form').submit()"><img src="img/button.gif" alt="" /></a></div>
                  </fieldset>
               </form>
            </div>
         </div>
      </div>
       <div class="clearfix"></div>
<!-- wrap -->
<div class="wrap">
	<!-- Login -->
 <div class="Login">
 <div class="clearfix"></div>
 <br>
 <form name="register" action="registering" method="post">
		   <table width="200%"><tr><td width="17%"><dd><font size="3">UserName:</font></td>
                   <td width="83%"><input name="userName" type="text" class="log_input2">
                       <!--<font color="red"> * <div id="result"></div></font>-->
                   <!--<input  value="CheckUserExistence" name="CheckUserExistence" type="submit" style="border:2px blue none;width:120px;height:18px;background:white;color:pink;font-size:12px;cursor:pointer"/>-->    
                   <font color="red"> * </font></dd></td></tr></table><br></br>	
		   <table width="200%"><tr><td width="17%"><dd><font size="3">Password:</font></dt></td>
                            <td width="83%"><input name="password" type="password" class="log_input2" />
                                <font color="red"> * </font></dd></td></tr></table><br></br>
		   <table width="200%"><tr><td width="17%"><dd><font size="3">First Name:</font></dt></td>
                            <td width="83%"><input name="fname" type="text" class="log_input2" /> </dd></td></tr></table><br></br>			
                   <table width="200%"><tr><td width="17%"><dd><font size="3">Last Name:</font></dt></td>
                            <td width="83%"><input name="lname" type="text" class="log_input2" /> </dd></td></tr></table><br></br>
                   <table width="200%"><tr><td width="17%"><dd><font size="3">Email：</font></dt></td>
                            <td width="83%"><input name="email" type="text" class="log_input2" />
                                <font color="red"> * </font></dd></td></tr></table><br></br>
                   <table width="200%"><tr><td width="17%"><dd><font size="3">Gender：</font></dt></td>
                       <td width="11%"><input name="gender" type="radio" value="male">  Male</td><td width="14%"><input type="radio" name="gender" value="female">  Female</td>
                       <td width=58%"><input type="radio" name="gender" value="unkown" checked="true">  Unkown</dd></td></tr></table>
		<div class="server_but">
                    <table width="200%"><tr><td><input onmouseover="this.style.backgroundColor='gray';" onmouseout="this.style.backgroundColor='#2e2e2e';" type="submit" style="border-radius:2px;width:80px;height:30px;border:2px blue none;background:#2e2e2e;color:yellowgreen;font-weight:bold;font-size:15px;cursor:pointer" value="Sign Up" name="signup"/></td>
                    <td><%if(session.getAttribute("errormsg2") != null){%>
                    <font color="red"><%=session.getAttribute("errormsg2")%></font>
                    <% } %></td></tr></table>                                                       
                  
                </div>                           
</form>
 </div>
		</div>	
    </div>   
	<div class="server_box">
            <br></br>
		<h3>Share your memory on <font color="yellowgreen">Photo</font>trest</h3>
		<br></br>
		<div class="server_con">
			<div class="PPbox1">
			  <img src="img/ico_01.gif" alt="图" align="absmiddle" class="Img" />
				 <p><b>Photo Page</b><br />
			Share your interesting photos </p>
          </div>
			
			<div class="PPbox1">
			  <img src="img/ico_02.gif" alt="图" align="absmiddle" class="Img" />
				 <p><b>Subscription</b><br />
			Subscribe photos you have interests in</p>
          </div>
			
			<div class="PPbox1">
			  <img src="img/ico_04.gif" alt="图" align="absmiddle" class="Img" />
				 <p><b>Entertainment</b><br />
			Enjoy all the funs</p>
          </div>
			
			<div class="PPbox1">
			  <img src="img/ico_03.gif" alt="图" align="absmiddle" class="Img" />
				 <p><b>Friendship</b><br />
			Make friends with who you like</p>
          </div>
	</div>
	</div>
<div class="clearfix"></div>
<div class="wrap">
   <div><br></br><br></br>
      <ol><b><font color="red">Notice:</font></b>
<font size="2"><li>1: Users registered on this site must abide by the "Regulations on the Administration of Electronic Bulletin Services on the Internet," and may not publish any information such as defaming others, infringing the privacy of others, infringing others' intellectual property rights, spreading viruses, political opinions or trade secrets.</li></font>
<font size="2"><li>2: For the all pictures published in this site, the site has the final editorial rights, and retained for printing or publishing rights to third parties. If we could not keep in  touch with you, we will be entitled without your approval to use your published pictures.</li></font></ol>
   </div>   </div>
   </div>
<div style='width:950px; height: 25px; line-height: 22px; margin-top: 35px; margin-left: 220px; background-color: #f2f3f3; text-align: center'>
<a href="usermain1.jsp"><font size='1'>Share Our Beautiful Memories in </font><font color="yellowgreen" size='1'>Photo</font></font><font size='1'>trest @ECE658 Course Group Team #1</font></a>
</div>
</body>
</html>

