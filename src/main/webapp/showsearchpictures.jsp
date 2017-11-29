<%-- 
    Document   : usermain
    Created on : 21-Nov-2017, 3:05:31 PM
    Author     : chris
--%>
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
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/jquery-1.6.js" type="text/javascript"></script>
<script src="js/lyz.delayLoading.min.js" type="text/javascript"></script>
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
                <h1><a href="index.jsp"><strong><font color="yellowgreen">Photo</font></strong><strong><font color="gray">trest</font></strong></a></h1>                                          
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
                <div class="fleft"><font color="#2e2e2e"><span>Main Page</span></font><font color="yellowgreen"><b>Hello,${Fullname}</b></font> > <font color="red">Tag:###</font>               
                </div>              
               <form action="" id="search-form">                  
                  <fieldset>                   
                  <div class="fright">                       
                     <label> Search</label>                    
                     <span>
                     <input type="text" />
                     </span><a href="" onclick="document.getElementById('search-form').submit()"><img src="img/button.gif" alt="" /></a></div>
                  </fieldset>
               </form>                          
           </div> 
             <br>
             <div style="text-align:center;">
             <input onmouseover="this.style.backgroundColor='#f2f3f3';" onmouseout="this.style.backgroundColor='#2e2e2e';" type="submit" style="width:80px;height:30px;border:2px blue none;background-color:#2e2e2e;color:yellowgreen;font-weight:bold;font-size:15px;cursor:pointer;" value="Subscribe" name="subscribe" onclick="javascript:checkSubmit(document.form2);return false;"/>
             </div>
             <br>
         </div>    
         </div>      
       <!--<div class="clearfix"></div><!-- 清除浮动 -->
<!-- wrap -->
<!--<div class="wrap">-->
<!-- Login -->
<!--<div class="Login">-->
    <!--<div class="clearfix">-->
</div>
</div>
    <div class="box">
	<ul>
        <li><img src="images/hua_8.jpg.680.510.jpg" /></li>
        <li><img src="images/_2.jpg.680.510.jpg" /></li>
        <li><img src="images/_1.jpg.680.510.jpg" /></li>
        <li><img src="images/1_030ZTF23528.jpg" /></li>

        
    </ul>
</div>
<div class="clearfix"></div>
<div class="wrap">
   <div><br>    
   </div>   </div>
<div style='width:950px; height: 25px; line-height: 22px; margin-top: 20px; margin-left: 220px; background-color: #f2f3f3; text-align: center'>
<a href="usermain.jsp"><font size='1'>Share Our Beautiful Memories in </font><font color="yellowgreen" size='1'>Photo</font></font><font size='1'>trest @ECE658 Course Group Team #1</font></a>
</div>
</body>
</html>

