
/*var total=$('.total').val();
layui.use(['layer','laypage','element','carousel'], function(){
	var laypage=layui.laypage;
	laypage.render({  参考链接http://www.layui.com/doc/modules/laypage.html
		elem: 'pro_page', 	//这里是 ID，不用加 # 号
		limit:10,
		groups:5,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: total,		//数据总数，从服务端得到
		curr: function(){
			var page = location.search.match(/currentPage=(\d+)/);  
			return page ? page[1] : 1;  
		}(),
		jump:function(obj,first){
			if(!first){
				window.location=path+'/sys/product/page.action?currentPage='+obj.curr+'&limit=10';
			}
		}
	});
})*/
$(".order_save").click(function(){
	var product_id="";
	var order_no="";
	$(".trPro").each(function(){
		var productId = $(this).data("id");
		var orderNo = $("#"+productId).val();
		product_id+=productId+",";
		order_no+=orderNo+",";
	});
	product_id=product_id.substring(0, product_id.length-1);
	order_no=order_no.substring(0, order_no.length-1);
	window.location=path+'/sys/product/orderSave.action?product_id='+product_id+'&order_no='+order_no;
});
$('.product_update').click(function(){
	var product_id=$(this).attr("data-id");
	var url = path + "/sys/product/toDetail.action?product_id=" + product_id+"&update_type="+1;
	window.location=url;
});
$('.urgent_update').click(function(){
	var product_id=$(this).attr("data-id");
	var url = path + "/sys/product/toDetail.action?product_id=" + product_id+"&update_type="+2;
	window.location=url;
});
