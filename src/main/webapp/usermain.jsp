<%--
    Document   : usermain
    Created on : 26-Nov-2017, 3:14:13 PM
    Author     : chris
--%>
<%@page import="edu.uwaterloo.ece658.servlet.UserMainServlet.PhotoWrapper"%>
<%@page import="java.util.Map"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Set"%>
<%@page import="edu.uwaterloo.ece658.entity.Tag"%>
<%@page import="edu.uwaterloo.ece658.entity.Photo"%>
<%@page import="edu.uwaterloo.ece658.service.S3Service"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Phototrest</title>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
        <meta http-equiv="Content-Style-Type" content="text/css" />
        <link href="auto.css" rel="stylesheet" type="text/css" />
        <link href="style.css" rel="stylesheet" type="text/css" />
        <link href="layout.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="css/styleforusermain.css">

        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/tjsp.js"></script>
        <script type="text/javascript">
            //faded
            $(function () {
                $("#faded").faded({
                    bigtarget: true,
                    sequentialloading: false,
                    loadingimg: "img/loading.gif",
                    autoplay: 5000,
                    autorestart: 0,
                    random: false,
                    autopagination: false
                });
            });
        </script>
    </head>
    <!-- HEADER -->
    <body id="page1" onload="new ElementMaxHeight()">
        <div id="main">
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
                                <li class="m1"><a href="">Front Page<span>Page d'accueil</span></a></li>
                                <li class="m3"><a href="#">News<span>Actualités</span></a></li>
                                <li class="m4"><a href="#">Entertainment<span>Divertissement</span></a></li>
                                <li class="m5"><a href="#">Scenery<span>Beauté</span></a></li>
                                <li class="m2 "><a href="#">Technology<span>Technologie</span></a></li>
                                <li class="m6 last"><a href="#">About<span>à propos</span></a></li>
                            </ul>
                        </div>
                        <div class="row-3 wrapper">
                            <div class="fleft"><font color="#2e2e2e"><span>Main Page</span></font><font color="yellowgreen"><b>Hello, ${FullName } </b></font></div>
                            <form action="showsearchpictures.jsp" id="search-form">
                                <fieldset>
                                    <div class="fright">
                                        <label> Search</label>
                                        <span>
                                            <input type="text" />
                                        </span><a href="showsearchpictures.jsp" onclick="document.getElementById('search-form').submit()"><img src="img/button.gif" alt="showsearchpictures.jsp" /></a></div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="inmain clr">
                <div class="main_left">
                    <ul>
                        <li class="rem_left1" rel="uploaded">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Uploaded Photos</li>
                            <%
                                Map<String, List<PhotoWrapper>> subscribed = (Map<String, List<PhotoWrapper>>) request.getSession().getAttribute("subscribed");
                                Set<String> tags = subscribed.keySet();
                                if (tags != null && !tags.isEmpty()) {
                                    for (String tag : tags) {%>
                        <li rel=<%= tag%> >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  →  <%= tag%></li> <%
                                }
                            }%>

                        <!--<li rel="technology">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Love Technology</li>
                        <li rel="tools">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Love Tools</li>
                        <li rel="movies">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Love Movies</li>
                        <li rel="music">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Love Music</li>
                        <li rel="game">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Love Game</li>
                        <li rel="fiction">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Love Fiction</li>
                        <li rel="other">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;→ Other</li>-->
                    </ul>
                </div>
                <div class="main_right">
                    <ul>
                        <%
                            for (PhotoWrapper wrapper : (List<PhotoWrapper>) request.getSession().getAttribute("uploaded")) {
                        %><li rel="uploaded"><a href="#"><img src=<%= wrapper.url%> /> &nbsp;&nbsp;</a><span><a class="btn btn-primary delete" href=<%= "/delete?s3Key=" + wrapper.s3Key%>>Delete</a></span></li><%
                            }
                                %>
                                <%
                                    for (String tag : tags) {
                                        for (PhotoWrapper wrapper : subscribed.get(tag)) {
                                %><li rel=<%= tag%>><a href="#"><img src=<%= wrapper.url%> /> &nbsp;&nbsp;</a><span><a class="btn btn-primary delete" href=<%= "delete?s3Key=" + wrapper.s3Key%>>Delete</a></span></li><%
                                        }
                                    }
                                %>
                        <!--
                                                <li rel="technology"><a href="#"><img src="images/port2.jpg" />#tag1</a></li>
                                                <li rel="other"><a href="#"><img src="images/port3.jpg" />#tag1</a></li>
                                                <li rel="tools"><a href="#"><img src="images/port4.jpg" />#tag1</a></li>
                                                <li rel="fiction"><a href="#"><img src="images/port4.jpg"/>#tag2</a></li>
                                                <li rel="music"><a href="#"><img src="images/port5.jpg" />#tag2</a></li>
                                                <li rel="game"><a href="#"><img src="images/port6.jpg" />#tag2</a></li>
                                                <li rel="tools"><a href="#"><img src="images/port7.jpg" />#tag2</a></li>
                                                <li rel="music"><a href="#"><img src="images/port8.jpg" />#tag3</a></li>
                                                <li rel="movies"><a href="#"><img src="images/port9.jpg" />#tag3</a></li>
                                                <li rel="fiction"><a href="#"><img src="images/port4.jpg" />#tag3</a></li>-->
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>


