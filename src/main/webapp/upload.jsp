<%-- 
    Document   : upload4
    Created on : 22-Nov-2017, 10:22:45 PM
    Author     : chris
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="auto.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<link href="layout.css" rel="stylesheet" type="text/css" />
<title>Phototrest-Upload Pictures</title>
<script type="text/javascript">
	//faded
$(function(){
		$("#faded").faded({
			bigtarget: true,
			sequentialloading: false,
			loadingimg: "img/loading.gif",
			autoplay: 5000,
			autorestart: 0,
			random: false,
			autopagination:false
		});
});
</script>
<script src="js/vue2.2.2.js"></script>
<style>
	.upload_warp_img_div_del {
      position: absolute;
      top: 6px;
      width: 16px;
      right: 4px;
    }

    .upload_warp_img_div_top {
      position: absolute;
      top: 0;
      width: 100%;
      height: 30px;
      background-color: rgba(0, 0, 0, 0.4);
      line-height: 30px;
      text-align: left;
      color: #fff;
      font-size: 12px;
      text-indent: 4px;
    }

    .upload_warp_img_div_text {
      white-space: nowrap;
      width: 80%;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .upload_warp_img_div img {
      max-width: 100%;
      max-height: 100%;
      vertical-align: middle;
    }

    .upload_warp_img_div {
      position: relative;
      height: 100px;
      width: 120px;
      border: 1px solid #ccc;
      margin: 0px 30px 10px 0px;
      float: left;
      line-height: 100px;
      display: table-cell;
      text-align: center;
      background-color: #eee;
      cursor: pointer;
    }

    .upload_warp_img {
      border-top: 1px solid #D2D2D2;
      padding: 14px 0 0 14px;
      overflow: hidden
    }

    .upload_warp_text {
      text-align: left;
      margin-bottom: 15px;
      padding-top: 15px;
      text-indent: 14px;
      border-top: 1px solid #ccc;
      font-size: 14px;
    }

    .upload_warp_right {
      float: left;
      width: 67%;
      margin-left: 2%;
      height: 100%;
      border: 1px dashed #999;
      border-radius: 4px;
      line-height: 410px;
      color: #999;
    }

    .upload_warp_left img {
      margin-top: 175px;
    }

    .upload_warp_left {
      float: left;
      width: 30%;
      height: 100%;
      border: 1px dashed #999;
      border-radius: 4px;
      cursor: pointer;
    }

    .upload_warp {
      margin: 14px;
      height: 420px;
    }

    .upload {
      border: 1px solid #ccc;
      background-color: #fff;
      width: 800px;
      box-shadow: 0px 1px 0px #ccc;
      border-radius: 4px;
    }

    .hello {
      width: 800px;
      margin-left: 9%;
      text-align: center;
    }
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
                  <li class="m1"><a href="usermain.jsp">Front Page<span>Page d'accueil</span></a></li>
                  <li class="m3"><a href="#">News<span>Actualités</span></a></li>
                  <li class="m4"><a href="#">Entertainment<span>Divertissement</span></a></li>
                  <li class="m5"><a href="#">Scenery<span>Beauté</span></a></li>
                  <li class="m2 "><a href="#">Technology<span>Technologie</span></a></li>          
		  <li class="m6 last"><a href="#">About<span>à propos</span></a></li>
               </ul>
            </div>
            <div class="row-3 wrapper">
                <div class="fleft"><font color="#2e2e2e"><span>Upload</span></font><font color="yellowgreen">Hello,${FullName }</font></div>              
               <form action="" id="search-form">                  
                  <fieldset>                   
                  <div class="fright">                       
                     <label> Search</label>                    
                     <span>
                     <input type="text" />
                     </span><a href="showsearchpictures.jsp" onclick="document.getElementById('search-form').submit()"><img src="img/button.gif" alt="" /></a></div>
                  </fieldset>
               </form>                          
            </div>
         </div>
      </div>
       <!--<div class="clearfix"></div><!-- 清除浮动 -->
<!-- wrap -->
<!--<div class="wrap">-->
<!-- Login -->
<!--<div class="Login">-->
    <!--<div class="clearfix">-->
</div>
   <br><br>
<form name="pictureupload" action="landing" method="POST">
   <div id="app">
  <div class="hello">
    <div class="upload">
      <div class="upload_warp">
        <div class="upload_warp_left" @click="fileClick" style="border:1px solid red">
          <img src="./upload.png">
        </div>
        <div class="upload_warp_right" @drop="drop($event)" @dragenter="dragenter($event)" @dragover="dragover($event)">
          Why not drag your pictures over here?
        </div>
      </div>      
      <input @change="fileChange($event)" accept="image/*" type="file" id="upload_file" multiple style="display: none">
      <input type="text" style="display: none" name="number_of_files" value= imgList.length v-model="imgList"/>
      <div v-for="(item,index) of imgList">
      <input  style="display: none" type="text" name="photos" v-model="item.file.src">
      </div>
      <div v-for="(item,index) of imgList">
      <input  style="display: none" type="text" name="sizes" v-model="item.file.size">
      </div>
      <div class="upload_warp_text">
          <table><tr><td width='45%'>
          <font color='pink'>You have chosen {{imgList.length}} pictures，it's about {{bytesToSize(this.size)}}.</font></td>
                  <td width='30%'><font color='red'>Add a tag : </font><input type="text" name="tags" value="" /></td>
                  <td width="10%"><input name="isPrivatePhoto" type="radio" value="false">  Private</td><td width="9%"><input type="radio" name="isPrivatePhoto" value="true" checked="true">  Public</td>
                  <td width='11%'>
                      <input type="submit" onmouseover="this.style.backgroundColor='#f2f3f3';" onmouseout="this.style.backgroundColor='#2e2e2e';" style="border-radius:4px;width:60px;height:30px;border:2px blue none;background:#2e2e2e;color:yellowgreen;font-size:13px;cursor:pointer;" value="Upload" name="upload"/></td></tr></table>
      </div>
      <div class="upload_warp_img" v-show="imgList.length!=0">
        <div class="upload_warp_img_div" v-for="(item,index) of imgList">
          <div class="upload_warp_img_div_top">
            <div class="upload_warp_img_div_text">
              {{item.file.name}}
            </div>
            <img src="./del.png" class="upload_warp_img_div_del" @click="fileDel(index)">
          </div>
          <img :src="item.file.src">
        </div>
      </div>
    </div>
  </div>
</div>
</form>
<script>
  //  import up from  './src/components/Hello'
  var app = new Vue({
    el: '#app',
    data () {
      return {
        imgList: [],
        size: 0
      }
    },
    methods: {
      submit() {
        var data = new FormData();
        data.append('message', this.imgList.length);
        
        this.$http.post('/uploadPhotos', formData, {emulateJSON: true});
      },
      fileClick(){
        document.getElementById('upload_file').click()
      },
      fileChange(el){
        if (!el.target.files[0].size) return;
        this.fileList(el.target.files);
        el.target.value = ''
      },
      fileList(files){
        for (let i = 0; i < files.length; i++) {
          this.fileAdd(files[i]);
        }
      },
      fileAdd(file){
        this.size = this.size + file.size;//总大小
        let reader = new FileReader();
        reader.vue = this;
        reader.readAsDataURL(file);
        reader.onload = function () {
          file.src = this.result;
          //alert(file.src);
          this.vue.imgList. push({
            file
          });
        }
      },
      fileDel(index){
        this.size = this.size - this.imgList[index].file.size;//总大小
        this.imgList.splice(index, 1);
      },
      bytesToSize(bytes){
        if (bytes === 0) return '0 B';
        let k = 1000, // or 1024
          sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
          i = Math.floor(Math.log(bytes) / Math.log(k));
        return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
      },
      dragenter(el){
        el.stopPropagation();
        el.preventDefault();
      },
      dragover(el){
        el.stopPropagation();
        el.preventDefault();
      },
      drop(el){
        el.stopPropagation();
        el.preventDefault();
        this.fileList(el.dataTransfer.files);
      }
    }
  })
</script>
</div>
<br>
<div style='width:950px; height: 25px; line-height: 22px; margin-top: 15px; margin-left: 220px; background-color: #f2f3f3; text-align: center'>
<a href="usermain.jsp"><font size='1'>Share Our Beautiful Memories in </font><font color="yellowgreen" size='1'>Photo</font></font><font size='1'>trest @ECE658 Course Group Team #1</font></a>
</div>	
</body>
</html>
