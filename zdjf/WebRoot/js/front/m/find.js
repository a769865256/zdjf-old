$(function(){
	console.log('初始化');
})
$('.tab .btn').on('click',function(){
	var index=$(this).index();
	$(this).find('span').addClass('active');
	$(this).siblings().find('span').removeClass('active');
	$('.tabbox .box').eq(index).show().siblings().hide();
})
$('.ask ul li h4').on('click',function(){
    var abtn=$(this).find('.a_h4');
	var txt=$(this).next('.txt');
	if(abtn.hasClass('active')){
		abtn.removeClass('active');
		txt.hide();
	}else{
		abtn.addClass('active');
		txt.show();
		/*$(this).siblings().find('a').removeClass('active');*/
		$(this).parents('li').siblings().find('a').removeClass('active');
		$(this).parents('li').siblings().find('.txt').hide();
	}
})
var u = navigator.userAgent;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
function getUrlVal(para){  
    var search=location.search; //页面URL的查询部分字符串  
    var arrPara=new Array(); //参数数组。数组单项为包含参数名和参数值的字符串，如“para=value”  
    var arrVal=new Array(); //参数值数组。用于存储查找到的参数值  
  
    if(search!=""){  
        var index=0;  
        search=search.substr(1); //去除开头的“?”  
        arrPara=search.split("&");  
  
        for(i in arrPara){  
            var paraPre=para+"="; //参数前缀。即参数名+“=”，如“para=”  
            if(arrPara[i].indexOf(paraPre)==0&& paraPre.length<arrPara[i].length){  
                arrVal[index]=decodeURI(arrPara[i].substr(paraPre.length)); //顺带URI解码避免出现乱码  
                index++;  
            }  
        }  
    }  
  
    if(arrVal.length==1){  
        return arrVal[0];  
    }else if(arrVal.length==0){  
        return null;  
    }else{  
        return arrVal;  
    }  
}
if(isiOS){
	if(getUrlVal('isBack') == 1){
        $('.find .header').show().next().addClass('body_content');
        $('.a_isBack').each(function(index,ele){
            var a_href = $(this).attr('href');
            $(this).attr('href',a_href+'?isBack=1');
        })
	}
}