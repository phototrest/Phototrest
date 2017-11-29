// JavaScript Document
$(document).ready(function(){
	//推荐左边的效果
	$(".main_left li").each(function(index, element) {
		$(this).hover(function(){
			$(this).addClass("rem_left");
		},function(){
			$(this).removeClass("rem_left")
		}).click(function(){
			var it=$(this).attr('rel')
			$(".main_left li").removeClass("rem_left1")
			$(this).addClass("rem_left1")
			if(it!='all'){
				//alert(it)
				$(".main_right li[rel="+it+"]").stop(false,true).show(1000);
				$('.main_right li[rel!='+it+']').stop(false,true).hide(1000);
				//$('.remmain_right li').animate({'opacity':1})
			}else{
				$('.main_right li').stop(false,true).show(1000);
			}
		})
	});
	//推荐右边的效果
	
	$(".main_right li").each(function(index, element) {
		$(this).on("mouseover",function(){
			$(this).addClass("movie_scroll_hov")
		}).on("mouseout",function(){
			$(this).removeClass("movie_scroll_hov")
		});
	});
        
        $("")
})
