<%--
    Document   : landing
    Created on : 29-Nov-2017, 3:14:13 PM
    Author     : chris
--%>
<%@ page contentType="java; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Phototrest</title>
        <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
        <meta http-equiv="Content-Style-Type" content="text/css" />
        <link href="auto.css" rel="stylesheet" type="text/css" />
        <link href="style.css" rel="stylesheet" type="text/css" />
        <link href="layout.css" rel="stylesheet" type="text/css" />
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
                                <li class="m1"><a href="usermain.jsp">Front Page<span>Page d'accueil</span></a></li>
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

        </div>
        <div style='width:950px; height: 25px; line-height: 22px; margin-top: 35px; margin-left: 220px; text-align: center'>
            <table>
                <% for (Integer i = 5; i > 0; i--) {%>
                <br>
                <%}%>
                <form name="redirectmainpage" action="ShowUserPhotosServlet" method="post">
                    <input style="display: none" type="text" name="name" value=${username} />
                    <input onmouseover="this.style.backgroundColor = 'gray';" onmouseout="this.style.backgroundColor = '#2e2e2e';" type="submit" style="border-radius:2px;width:200px;height:30px;border:2px blue none;background:#2e2e2e;color:yellowgreen;font-size:15px;cursor:pointer" value="Redirect to User MainPage" name="redirectmainpage"/>
                </form>
                <% for (Integer i = 10; i > 0; i--) {%>
                <br>
                <%}%>
                <form name="redirectupload" action="upload.jsp" method="post">
                    <input onmouseover="this.style.backgroundColor = 'gray';" onmouseout="this.style.backgroundColor = '#2e2e2e';" type="submit" style="border-radius:2px;width:200px;height:30px;border:2px blue none;background:#2e2e2e;color:yellowgreen;font-size:15px;cursor:pointer" value="Redirect to Upload Page" name="redirectupload"/>
                </form>
            </table>
        </div>
        <br>

        <div style='width:950px; height: 25px; line-height: 22px; margin-top: 500px; margin-left: 220px; background-color: #f2f3f3; text-align: center'>
            <a href="usermain1.jsp"><font size='1'>Share Our Beautiful Memories in </font><font color="yellowgreen" size='1'>Photo</font></font><font size='1'>trest @ECE658 Course Group Team #1</font></a>
        </div>
    </body>
</html>









