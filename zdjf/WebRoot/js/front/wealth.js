$('.header').stickMe({
	topOffset:100
});
layui.use(['layer','laydate','form','laypage'], function(){
	var layer = layui.layer,
	laydate= layui.laydate,
	form = layui.form,
	laypage=layui.laypage;

	var todayDate = new Date();
	var y = todayDate.getFullYear();  
    var m = todayDate.getMonth() + 1;  
    var d = todayDate.getDate();  
    var today = y + '-' + m + '-' + d; 
	laydate.render({
		elem: '#ca_date',
		position: 'static',
		showBottom: false,
		type:'date',
		format:'yyyy-MM-dd',
		istoday: true,
		min: -90, //7天前
		max: 360, //7天后
		mark:{
			'2017-12-5': '已回款',
			'2017-11-29': '待回款',
		},
		change: function(value, date, endDate){
			var arrdaymark = $(".laydate-day-mark");
			for(var i=0; i<arrdaymark.length; i++){
				if(arrdaymark[i].innerHTML == "待回款") {
					arrdaymark[i].className = "laydate-day-mark dai";
				}
				if(arrdaymark[i].innerHTML == "已回款"){
					arrdaymark[i].className = "laydate-day-mark yi";
				}
			}
			var arrTd = $(".layui-laydate-content td");
			for(var i=0; i<arrTd.length; i++){
				if(arrTd[i].getAttribute("lay-ymd") == today) {
					arrTd[i].innerHTML = "今";
					arrTd[i].className = "active";
				}
			}
		}
	});
	/*判断日历已回款和待回款*/
	var arrdaymark = $(".laydate-day-mark");
	for(var i=0; i<arrdaymark.length; i++){
		if(arrdaymark[i].innerHTML == "待回款") {
			arrdaymark[i].className = "laydate-day-mark dai";
		}
		if(arrdaymark[i].innerHTML == "已回款"){
			arrdaymark[i].className = "laydate-day-mark yi";
		}
	}
	var arrTd = $(".layui-laydate-content td");
	// var layymd = $(".layui-laydate-content td").attr("lay-ymd",today);

	for(var i=0; i<arrTd.length; i++){
		if(arrTd[i].getAttribute("lay-ymd") == today) {
			arrTd[i].innerHTML = "今";
			arrTd[i].className = "active";
		}
	}

	/*充值提交校验*/
	$('.rc_btn').on('click',function(){
		var datecz=$(this).attr('date-cz');
		if(datecz==1){
			// adv();
			fastPay();
		}else{
			wy_adv();
		}
	})
    function fastPay() {
        var re_mon=parseFloat($("#amount").val());
        if(!re_mon || re_mon<=0) {
            layer.msg('请输入充值金额', {
                time: 1000
            });
            return false;
        }else if(re_mon > 500000){
            layer.msg('您输入的金额过大', {
                time: 1000
            });
            return false;
        }else {
            layer.open({
                type: 1,
                title: ['充值遇到问题？','background:#ffffff;text-align:center;font-size: 16px;color: #000000;padding: 0;height:60px;line-height: 60px;'],
                area: ['320px','220px'],
                content:'<div class="chongzhi"><p>充值完成前请不要关闭此窗口，充值完成后请根据您的需要点击下面的按钮</p><p><a href="'+path+'/myFunds.action?type=11" class="cz_ok">查看充值记录</a><a href="'+path+'/investGuide/2/help.action">充值遇到问题</a></p></div>',
                success: function(){
                    $('.cz_ok').click(function(){
                        layer.closeAll();
                        window.location.reload();
                    })
                }
            })
            $("#kjpay").submit();
        }
    }
	/*显示余额*/
	$('.assbtn h4 i').click(function(){
		var index = $(this).index();
		$(this).hide().siblings(".iconfont").show();
		if(index == 0){
			$(".xs").hide();
	  		$(".xs1").show();
		} else {
			$(".xs").show();
	  		$(".xs1").hide();
		}	
	});


	/*------------------ 日期控件 -----------------*/
	laydate.render({
		elem: '#beginDate',
		done: function(value, date, endDate){
		    var startTime=value; 
		    var endTime=$('#to').val();
		    dateIndex=$('.chiose_date .active').index()-1;
		    typeIndex=$('.chiose_type .active').index()-1;
		    $("#dateType").val('');
		    $("#myFund").submit();
//		    $('.we_rt').load(path+'/capital/list.action?dateIndex='+dateIndex+'&typeIndex='+typeIndex+'&startTime='+startTime+'&endTime='+endTime,function(){
//		    	$('.chiose_date a').eq(0).addClass('active').siblings('a').removeClass('active');
//		    	$('.chiose_type a').eq(typeIndex).addClass('active').siblings('a').removeClass('active');
//		    	$('#to').val(endTime);
//		    	$('#from').val(startTime);
//		    });
		 }
	});
	laydate.render({
		elem: '#endDate',
		done: function(value, date, endDate){
			var endTime=value; 
		    var startTime=$('#to').val();
		    dateIndex=$('.chiose_date .active').index()-1;
		    typeIndex=$('.chiose_type .active').index()-1;
		    $("#dateType").val('');
		    $("#myFund").submit();
//		    $('.we_rt').load(path+'/capital/list.action?dateIndex='+dateIndex+'&typeIndex='+typeIndex+'&startTime='+startTime+'&endTime='+endTime);
		 }
	});
	laydate.render({
		elem: '#dufrom2',
		done: function(value, date, endDate){
		    $("#beginDate").val(value);
		    $("#form1").submit();
		 }
	});
	laydate.render({
		elem: '#duto2',
		done: function(value, date, endDate){
		    $("#endDate").val(value);
		    $("#form1").submit();
		 }
	});
	laydate.render({
		elem: '#dufrom',
		done: function(value, date, endDate){
		    $("#beginDate").val(value);
		    $("#form1").submit();
		 }
	});
	laydate.render({
		elem: '#duto',
		done: function(value, date, endDate){
		    $("#endDate").val(value);
		    $("#form1").submit();
		 }
	});
	laydate.render({
		elem: '#from',
		done: function(value, date, endDate){
		    $("#endDate").val(value);
		    $("#form1").submit();
		 }
	});
	laydate.render({
		elem: '#to',
		done: function(value, date, endDate){
		    $("#endDate").val(value);
		    $("#form1").submit();
		 }
	});
	/*资金明细开始时间*/
	$("#from").click(function(){
		var arrTd = $(".layui-laydate-content td");
		for(var i=0; i<arrTd.length; i++){
			if(arrTd[i].getAttribute("lay-ymd") == today) {
				arrTd[i].innerHTML = "今";
				arrTd[i].className = "active";
			}
		}
	});
	$("#beginDate").click(function(){
		var arrTd = $(".layui-laydate-content td");
		for(var i=0; i<arrTd.length; i++){
			if(arrTd[i].getAttribute("lay-ymd") == today) {
				arrTd[i].innerHTML = "今";
				arrTd[i].className = "active";
			}
		}
	});
	/*资金明细结束时间*/
	$("#endDate").click(function(){
		var arrTd = $(".layui-laydate-content td");
		for(var i=0; i<arrTd.length; i++){
			if(arrTd[i].getAttribute("lay-ymd") == today) {
				arrTd[i].innerHTML = "今";
				arrTd[i].className = "active";
			}
		}
	});
	$("#to").click(function(){
		var arrTd = $(".layui-laydate-content td");
		for(var i=0; i<arrTd.length; i++){
			if(arrTd[i].getAttribute("lay-ymd") == today) {
				arrTd[i].innerHTML = "今";
				arrTd[i].className = "active";
			}
		}
	});
	/*我的理财投资记录开始时间*/
	$("#dufrom").click(function(){
		var arrTd = $(".layui-laydate-content td");
		for(var i=0; i<arrTd.length; i++){
			if(arrTd[i].getAttribute("lay-ymd") == today) {
				arrTd[i].innerHTML = "今";
				arrTd[i].className = "active";
			}
		}
	});
	/*我的理财投资记录结束时间*/
	$("#duto").click(function(){
		var arrTd = $(".layui-laydate-content td");
		for(var i=0; i<arrTd.length; i++){
			if(arrTd[i].getAttribute("lay-ymd") == today) {
				arrTd[i].innerHTML = "今";
				arrTd[i].className = "active";
			}
		}
	});
	/*我的理财订单记录开始时间*/
	$("#dufrom2").click(function(){
		var arrTd = $(".layui-laydate-content td");
		for(var i=0; i<arrTd.length; i++){
			if(arrTd[i].getAttribute("lay-ymd") == today) {
				arrTd[i].innerHTML = "今";
				arrTd[i].className = "active";
			}
		}
	});
	/*我的理财订单记录结束时间*/
	$("#duto2").click(function(){
		var arrTd = $(".layui-laydate-content td");
		for(var i=0; i<arrTd.length; i++){
			if(arrTd[i].getAttribute("lay-ymd") == today) {
				arrTd[i].innerHTML = "今";
				arrTd[i].className = "active";
			}
		}
	});
	/*-------------------------提现/充值----------------------------*/
//	$('.withdrawals_btn').click(function(){
//		var status=$('.real_name_auth').text();
//		if(status==3){
//			$('.we_rt').load(path+'/withdraw.action');
//		}else{
//			$('.we_rt').load(path+'/toBind.action');
//		}
//		
//	});
//	$('.recharge_btn').click(function(){
//		var status=$('.real_name_auth').text();
//		if(status=="1"){
////			$('.we_rt').load(path+'/charge.action');
//		}else{
////			$('.we_rt').load(path+'/toBind.action');
//		}
//	});
	$('.tabbtn a').on('click',function(){
		var index=$(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$('.tabbox .tab').eq(index).show().siblings().hide();
	});

	/*提现*/
	$('.chiose').click(function(){
		if($(this).hasClass('active')){
			$(this).removeClass('active');
		}else{
			$(this).addClass('active');
		}
	});
	$('.withbtn').click(function(){
		if($("#amount").val()==null || $("#amount").val()==''){
			layer.open({
	        	type: 1,
	        	title: ['温馨提示','background:#ffffff;text-align:center;font-size: 16px;color: #000000;padding: 0;height:60px;line-height: 60px;'],
	        	area: ['320px','220px'],
	        	content:'<div class="sub_box"><p>请填写提现金额</p></div>',
	        	shadeClose:true,
	        	success: function(){
		        }
	    	});
			return ;
		}

		$.ajax({
            type: 'POST',
            url: path+'/pay/withdraw.json',
            data: 'amount='+$("#amount").val(),
            dataType: 'json',
            success: function(data){
            	if(data.status==100){
            		if($("#amount").val()!=null || $("#amount").val()!=''){
            			layer.open({
            	        	type: 1,
            	        	title: ['温馨提示','background:#ffffff;text-align:center;font-size: 16px;color: #000000;padding: 0;height:60px;line-height: 60px;'],
            	        	area: ['320px','220px'],
            	        	content:'<div class="sub_box"><p style="margin: 30px 10px 25px;">当前提现的金额为:&nbsp'+$("#amount").val()+'元<br>提现后余额为:'+$("#tx_balance").html()+'元</p></div><div class="with_btn" style="text-align: center;"><a href="'+data.content+'" target="_blank" class="with_see" style="width: 110px;height: 38px;display: inline-block;line-height: 38px;font-size: 16px;border-radius: 2px;margin:0 10px;border: 1px solid #1088F1;background: #1088F1;color: #ffffff;">确定提现</a><a href="javascript:" class="qx"style="width: 110px;height: 38px;display: inline-block;line-height: 38px;font-size: 16px;border-radius: 2px;margin:0 10px;border: 1px solid #1088F1;background: #fff;color: #1088F1;">取消</a></div>',
            	        	shadeClose:true,
            	        	success: function(){
            	        		$('.qx').click(function(){
            	        			layer.closeAll();
            	        		});
            	        		$('.with_see').click(function(){
            	        			layer.closeAll();
                					layer.open({
                            			type: 1,
                            			title:false,
                            			closeBtn:0,
                            			area:['386px','222px'],
                            			content: $('#with_box'),
                            			skin:'addpro',
                            			shadeClose:true,
                            			success: function(layero, index){
                            				$('.with_close').click(function(){
                            					layer.close(index);
                            				});
                            			}
                            		});
                				});
            		        }
            	    	});
            			return ;
            		}
            		//window.open (data.content);
            	}else{
                    alert(data.content);
            	}
            },
            error: function(){
                alert('请求错误，请重新再次请求！');
            }
		});
	});

	/*----------------------账户设置》绑定+解绑------------------------------*/
	/*解绑银行卡*/
	$('.sub').click(function(){
        var path = $("#path").val();
		var valid_code = $("#valid_code").val();
		var ticket = $("#ticket").val();
        if (valid_code == null || valid_code.length == 0) {
            $("#codeErr").html("请填写验证码！");
            return;
        }
        if (ticket == null || ticket.length == 0) {
            $("#codeErr").html("请获取验证码！");
            return;
		}
        $.ajax({
            type: 'POST',
            url: path+'/unboundBankCardAdvance.json',
            data: {
                valid_code:valid_code,
				ticket:ticket
            },
            dataType: 'json',
            success: function(data){
                if(data.status==100){
					/*解绑成功*/
                    layer.open({
                        type: 1,
                        skin: 'bank_success',
                        title: ['','background:#ffffff;text-align:center;font-size: 18px;color: #1088F1;padding: 0;height:60px;line-height: 60px;'],
                        area: ['320px','220px'],
                        content:'<div class="sub_box"><i class="iconfont" style="font-size: 50px;margin-top: -32px;display: block;color: #1088F1;">&#xe770;</i><p style="color: #1088F1;font-size: 18px;">银行卡解绑成功</p><p style="margin: 16px 10px;"><a href='+path+'/toSetBankCard.action class="qd">确定</a></p></div>',
                        success: function(){
                            $('.qd').click(function(){
                                layer.closeAll();
                            });
                        }
                    });
                } else {
					/*解绑失败*/
                    layer.open({
                        type: 1,
                        skin: 'bank_bat',
                        title: ['','background:#ffffff;text-align:center;font-size: 18px;color: #1088F1;padding: 0;height:60px;line-height: 60px;'],
                        area: ['320px','220px'],
                        content:'<div class="sub_box"><i class="iconfont" style="font-size: 50px;margin-top: -32px;display: block;color: #FF5959;">&#xe61c;</i><p style="color: #FF5959;font-size: 18px;margin: 10px 32px 0;">银行卡解绑失败<br><span style="color: #333;font-size: 14px;">'+data.content+'</span></p><p style="margin: 16px 10px;"><a href="javascript:" class="qd">确定</a></p></div>',
                        success: function(){
                            $('.qd').click(function(){
                                layer.closeAll();
                            });
                        }
                    })
                }
            },
            error: function(){
                $("#codeErr").html('请求错误，请重新再次请求！');
                // 即使加载出错，也得重置
            }
        });

	});
	/*添加银行卡*/
	$(".ljtj").click(function(){
		var valid_code = $("#valid_code").val();
		var ticket = $("#ticket").val();
        $.ajax({
            type: 'POST',
            url: path+'/addBankCardAdvance.json',
            data: {
                valid_code:valid_code,
                ticket:ticket,
                province:$('#province').val(),
                city:$('#city').val(),
                bankCard:$('#bankCard').val(),
                bank_accounts:$('#bank_accounts').val(),
                phone:$('#phone').val()
            },
            dataType: 'json',
            success: function(data){
                if(data.status==100){
					/*绑成功*/
                    layer.open({
                        type: 1,
                        skin: 'addBank_success',
                        title: ['','background:#ffffff;text-align:center;font-size: 18px;color: #1088F1;padding: 0;height:60px;line-height: 60px;'],
                        area: ['320px','220px'],
                        content:'<div class="sub_box"><i class="iconfont" style="font-size: 50px;margin-top: -32px;display: block;color: #1088F1;">&#xe770;</i><p style="color: #1088F1;font-size: 18px;">银行卡添加成功</p><p style="margin: 16px 10px;"><a href='+path+'/toSetBankCard.action class="qd">确定</a></p></div>',
                        success: function(){
                            $('.qd').click(function(){
                                layer.closeAll();
                            });
                        }
                    });
                } else {
					/*绑失败*/
                    layer.open({
                        type: 1,
                        skin: 'addBank_bat',
                        title: ['','background:#ffffff;text-align:center;font-size: 18px;color: #1088F1;padding: 0;height:60px;line-height: 60px;'],
                        area: ['320px','220px'],
                        content:'<div class="sub_box"><i class="iconfont" style="font-size: 50px;margin-top: -32px;display: block;color: #FF5959;">&#xe61c;</i><p style="color: #FF5959;font-size: 18px;margin: 10px 32px 0;">银行卡添加绑失败<br><span style="color: #333;font-size: 14px;">'+data.content+'</span></p><p style="margin: 16px 10px;"><a href="javascript:" class="qd">确定</a></p></div>',
                        success: function(){
                            $('.qd').click(function(){
                                layer.closeAll();
                            });
                        }
                    });
                }
            },
            error: function(){
                alert('请求错误，请重新再次请求！');
                // 即使加载出错，也得重置
            }
        });
	});

	//更多银行
	$('.morebank').click(function(){
		$(this).hide();
		$(this).siblings("a").show();
	});
	$('.banklist a.bankname').on('click',function(){
		if($(this).hasClass('active')){
			$(this).removeClass('active');
			$("#bank_alias").val('');
		}else{
			$(this).addClass('active').siblings('.bankname').removeClass('active');
			$("#bank_alias").val($(this).find(".bank_hidden").val());
		}	
	});
	//充值弹窗
	$('.codebtn').click(function(){
		var url=path+'/m/userFundStat/recharge.action';
		var amount=$('.money').val();
		var pay_type='1';
		$.post(url,{"amount":amount,"pay_type":pay_type},function(data){

		});
	});

	/*关闭充值成功弹窗提示*/
	$('.pro_close').click(function(){
		$("#czcg").hide();
	})

	/*关闭充值失败弹窗提示*/
	$('.pro_close').click(function(){
		$("#czsb").hide();
	})

	/*关闭申请提现弹窗*/
	$('.with_close').click(function(){
		$("#txwt").hide();
	})

	//月份加减
	$('.databtn a').on('click',function(){
		var year=parseInt($('.year').text());
		var month=parseInt($('.month').text());
		if($(this).hasClass('addyear')){
			month+=1;
			$('.month').text(month);
			if(month==13){
				year+=1;
				$('.year').text(year);
				$('.month').text(1);
			}
		}else{
			month-=1;
			$('.month').text(month);
			if(month==0){
				year-=1;
				$('.year').text(year);
				$('.month').text(12);
			}
		}
	});
	$('.tr_htxt p a').on('click',function(){
		var yearnum=parseInt($('.yearnum').text());
		var monthnum=parseInt($('.monthnum').text());
		if($(this).hasClass('addyear2')){
			monthnum+=1;
			$('.monthnum').text(monthnum);
			if(monthnum==13){
				yearnum+=1;
				$('.yearnum').text(yearnum);
				$('.monthnum').text(1);
			}
		}else{
			monthnum-=1;
			$('.monthnum').text(monthnum);
			if(monthnum==0){
				yearnum-=1;
				$('.yearnum').text(yearnum);
				$('.monthnum').text(12);
			}
		}
	});




	var dateIndex=0;
	var typeIndex=0;
	$('.chiose_date a').on('click',function(){
		dateIndex=$(this).index()-1;
		typeIndex=$('.chiose_type .active').index()-1;
		$('.we_rt').load(path+'/capital/list.action?dateIndex='+dateIndex+'&typeIndex='+typeIndex,function(){
			$('.chiose_date a').eq(dateIndex).addClass('active').siblings('a').removeClass('active');
	    	$('.chiose_type a').eq(typeIndex).addClass('active').siblings('a').removeClass('active');
		});
		
	});
	$('.chiose_type a').on('click',function(){
		dateIndex=$('.chiose_date .active').index()-1;
		typeIndex=$(this).index()-1;
		$('.we_rt').load(path+'/capital/list.action?date='+dateIndex+'&type='+typeIndex,function(){
			$('.chiose_date a').eq(dateIndex).addClass('active').siblings('a').removeClass('active');
	    	$('.chiose_type a').eq(typeIndex).addClass('active').siblings('a').removeClass('active');
		});
	});

	/*$('.ca_page span').on('click',function(){
		$(this).addClass('active').siblings('span').removeClass('active');
	});*/
	/*我的理财>tab切换*/
	$('.du_tav p span').click(function(){
		var index=$(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$('.du_tab .ca_table').eq(index).show().siblings().hide();
	});
	$('.du_datebox span').on('click',function(){
		$(this).addClass('active').siblings('span').removeClass('active');
	});
	$('.search a').on('click',function(){
		$(this).addClass('active').siblings('a').removeClass('active');
	});
	/*我的优惠-tab切换*/
	$('.dis_tav p span').click(function(){
		var index=$(this).index();
		$(this).addClass('dis_active').siblings().removeClass('dis_active');
		if(index==0){
			$("#dis_type").attr("value","1");
			$("#dis_type2").attr("value","1");
			$('.manage').removeClass('hide');
			$('.exchange').addClass('hide');
		}else if(index==1){
			$("#dis_type").attr("value","2");
			$("#dis_type2").attr("value","2");
			$('.exchange').removeClass('hide');
			$('.manage').addClass('hide');
		}
		/*优惠券管理红包券有无显示*/
		isRedEnvs($(this),"dis_active",$("#myCou .dis_cher_ticket a:first-child"),"active",$("#myCou .dis_cher_list ul li"),$("#myCou .no_hb"),$("#pro_page"));
		/*优惠券管理加息券有无显示*/
		isRedEnvs($(this),"dis_active",$("#myCou .dis_cher_ticket a:last-child"),"active",$("#myCou .dis_cher_list ul li"),$("#myCou .jxq"),$("#pro_page"));
		/*兑换中心红包券有无显示*/
		isRedEnvs($(this),"dis_active",$("#myGoods .dis_cher_ticket a:first-child"),"active",$("#myGoods .dis_cher_list ul li"),$("#myGoods .no_hb"),$("#pro_page2"));
		/*兑换中心加息券有无显示*/
		isRedEnvs($(this),"dis_active",$("#myGoods .dis_cher_ticket a:last-child"),"active",$("#myGoods .dis_cher_list ul li"),$("#myGoods .jxq"),$("#pro_page2"));
	});
	/*优惠券管理红包券有无显示*/
	isRedEnvs($(".dis_tav p span:first-child"),"dis_active",$("#myCou .dis_cher_ticket a:first-child"),"active",$("#myCou .dis_cher_list ul li"),$("#myCou .no_hb"),$("#pro_page"));
	/*优惠券管理加息券有无显示*/
	isRedEnvs($(".dis_tav p span:first-child"),"dis_active",$("#myCou .dis_cher_ticket a:last-child"),"active",$("#myCou .dis_cher_list ul li"),$("#myCou .jxq"),$("#pro_page"));
	/*兑换中心红包券有无显示*/
	isRedEnvs($(".dis_tav p span:last-child"),"dis_active",$("#myGoods .dis_cher_ticket a:first-child"),"active",$("#myGoods .dis_cher_list ul li"),$("#myGoods .no_hb"),$("#pro_page2"));
	/*兑换中心加息券有无显示*/
	isRedEnvs($(".dis_tav p span:last-child"),"dis_active",$("#myGoods .dis_cher_ticket a:last-child"),"active",$("#myGoods .dis_cher_list ul li"),$("#myGoods .jxq"),$("#pro_page2"));
	/*优惠券管理红包券有无显示*/
	function isRedEnvs(fir_tabBtn,fir_tabBtn_active,sec_tabBtn,sec_tabBtn_active,cententDom,obj,page){
		if(fir_tabBtn.hasClass(fir_tabBtn_active) && sec_tabBtn.hasClass(sec_tabBtn_active) && cententDom.length <= 0){
			obj.show();
			page.hide();
		} else if(fir_tabBtn.hasClass(fir_tabBtn_active) && sec_tabBtn.hasClass(sec_tabBtn_active) && cententDom.length > 0){
			obj.hide();
			page.show();
		}
	}
	laypage.render({
		elem: 'dis_page',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'dis_page_1',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'dis_page_11',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'dis_page2',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'dis_page_2',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'dis_page_22',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'messpagebtn_1',
		limit:6,
		groups:5,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'messpagebtn_2',
		limit:6,
		groups:5,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'ca_page',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	laypage.render({
		elem: 'ca_page2',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});

	/*红包券加息券提现券切换*/
	$('.dis_cher_ticket a').click(function(){
		var index = $(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$('.dis_cher_box1 .dis_cher_list').eq(index).show().siblings().hide();
	});
	$('._ticket a').click(function(){
		var index = $(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$('.dis_cher_box2 .dis_cher_list').eq(index).show().siblings().hide();
	});
	$('.dis_cher_overdue a').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	});
	/*邀请有礼*/
	laypage.render({
		elem: 'friend_page',
		limit:6,
		groups:2,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100
	});
	/*复制链接*/
	function copyUrl() {
		var Url = document.getElementById("url");
		Url.select(); // 选择对象
		document.execCommand("Copy"); // 执行浏览器复制命令
		alert("已复制好，可贴粘。");
	}
	$('.copy_url_btn').click(function(){
		copyUrl();
	});

	/*消息中心*/
	$('.ms_list ul li').each(function(i,k){
		if($(this).hasClass('no_active')){
			return false;
		}else{
			$('.message_center h3 span').text(i+1);
		}
	});
  	form.on('radio', function(data){
	    var index = $(this).index();
	    var lis1 = $('.ms_list.ms_list_1 ul li').length;
		var no_active1 = $('.ms_list.ms_list_1 ul li.no_active').length;
	    var lis2 = $('.ms_list.ms_list_2 ul li').length;
		var no_active2 = $('.ms_list.ms_list_2 ul li.no_active').length;
		if(index==0){
			$('.ms_list_1').show();
			$('.ms_list_2').hide();
			$('.message_center h3 span').text(lis1 - no_active1);
			$('.ms_list.ms_list_1 ul').on('click','li',function(){
				if($(this).hasClass('no_active')){
					return false;
				}else{
					$(this).addClass('no_active');
					$('.ms_list.ms_list_1 ul li.no_active').each(function(i,k){
						var length1 = i + 1;
						$('.message_center h3 span').text(lis1 - length1);
					});
				}
			});
		} else if(index==2) {
			$('.ms_list_1').hide();
			$('.ms_list_2').show();
			$('.message_center h3 span').text(lis2 - no_active2);
			$('.ms_list.ms_list_2 ul').on('click','li',function(){
				if($(this).hasClass('no_active')){
					return false;
				}else{
					$(this).addClass('no_active');
					$('.ms_list.ms_list_2 ul li.no_active').each(function(i,k){
						var length2 = i + 1;
						$('.message_center h3 span').text(lis2 - length2);
					});
				}
			});
		}
  	});
	$('.mess_sign_btn').click(function(){
		$('.message_center h3 span').text('0');
		if($('.ms_list_1').is(":hidden")){
			$('.ms_list.ms_list_2 ul li').each(function(i,k){
				if($(this).hasClass('no_active')){
					$('.ms_list.ms_list_2 ul li').addClass('no_active');
				}else{
					$('.ms_list.ms_list_2 ul li').addClass('no_active');
				}
			});
		} else {
			$('.ms_list.ms_list_1 ul li').each(function(i,k){
				if($(this).hasClass('no_active')){
					$('.ms_list.ms_list_1 ul li').addClass('no_active');
				}else{
					$('.ms_list.ms_list_1 ul li').addClass('no_active');
				}
			});
			
		}
	});
	$('.ms_list ul').on('click','li',function(){
		var lis1 = $('.ms_list.ms_list_1 ul li').length;
		var lis2 = $('.ms_list.ms_list_2 ul li').length;
		if($(this).hasClass('no_active')){
			return false;
		}else{
			$(this).addClass('no_active');
			if($('.ms_list_1').is(":hidden")){
				$('.ms_list.ms_list_2 ul li.no_active').each(function(i,k){
					var length2 = i + 1
					$('.message_center h3 span').text(lis2 - length2);
				});
			} else {
				$('.ms_list.ms_list_1 ul li.no_active').each(function(i,k){
					var length1 = i + 1
					$('.message_center h3 span').text(lis1 - length1);
				});
			}
		}
	});

	/*修改登录密码*/
	$('.pass_modify').click(function(){
        var path = $("#path").val();
        var regex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,16})$');/*密码必须6到16位，并且含字母与数字*/
		layer.open({
        	type: 1,
        	title: ['修改登录密码','background:#ffffff;border:none;text-align:center;font-size: 18px;color: #000000;margin: 15px 0 10px;padding: 0;'],
        	area: ['350px','350px'],
        	content:'<div class="pass_win"><p><input placeholder="输入当前密码" type="password" id="old_pwd" onchange="oldpwd()"/><span style="color:#FF5959;margin-top: 5px;padding: 0 36px;" id="old_pwd_err"></span></p><p><input placeholder="输入新密码，长度6-16位，至少包含数字和字母" type="password" id="new_pwd" onchange="pwdVerify()"/><span style="color:#FF5959;margin-top: 5px;padding: 0 36px;" id="new_pwd_err"></span></p><p><input id="new_pwd_again" placeholder="再次输入新密码" type="password" onchange="pwdCheck()"/><span style="color:#FF5959;margin-top: 5px;padding: 0 36px;" id="new_pwd_again_err"></span></p><p><a href="javascript:" class="mobtn">修改密码</a></p></div>',
        	success: function(){
        		/*修改密码按钮*/
	        	$('.mobtn').click(function(){
	        		var old_pwd = $("#old_pwd").val();
	        		var new_pwd = $("#new_pwd").val();
	        		var new_pwd_again = $("#new_pwd_again").val();
	        		if(old_pwd == '' || old_pwd == null || old_pwd.length == 0){
	        			$("#old_pwd_err").html('当前密码不能为空');
	        			return false;
	        		} else if(!regex.test(old_pwd)){
	        			$("#old_pwd_err").html('密码必须6到16位，并且含字母与数字');
	        			return false;
	        		}
	        		if(old_pwd == new_pwd){
	        			$("#new_pwd_err").html('当前修改密码和原密码不能相同');
	        			return false;
	        		}
	        		if(new_pwd == '' || new_pwd == null || new_pwd.length == 0){
	        			$("#new_pwd_err").html('密码不能为空');
	        			return false;
	        		} else if(!regex.test(new_pwd)){
	        			$("#new_pwd_err").html('密码必须6到16位，并且含字母与数字');
	        			return false;
	        		}
	        		if(new_pwd_again == '' || new_pwd_again == null || new_pwd_again.length == 0){
	        			$("#new_pwd_again_err").html('密码不能为空');
	        			return false;
	        		} else if(new_pwd_again != new_pwd){
	        			$("#new_pwd_again_err").html('2次输入的密码不一致！');
	        			return false;
	        		}
                    $.ajax({
                        type: 'POST',
                        url: path+'/modUserPwd.json',
                        data: {
                            old_pwd:old_pwd,
							new_pwd:new_pwd,
                            new_pwd_again:new_pwd_again
                        },
                        dataType: 'json',
                        success: function(data){
                        	if (data.status == 100) {
                                layer.closeAll();
                                layer.open({
                                    type: 1,
                                    skin: 'success_tc',
                                    title: ['','background:#ffffff;text-align:center;font-size: 18px;color: #1088F1;padding: 0;height:60px;line-height: 60px;'],
			                        area: ['320px','240px'],
			                        content:'<div class="sub_box"><i class="iconfont" style="font-size: 50px;margin-top: -32px;display: block;color: #1088F1;">&#xe770;</i><p style="color: #1088F1;font-size: 18px;">密码修改成功<br><i class="timeOut">3</i> 秒以后自动返回登录页</p><p style="margin: 16px 10px;"><a href='+path+'/toLogin.action class="go_login">确定</a></p></div>',
                                    success: function(){
										/*修改密码按钮*/
                                        $('.go_login').click(function(){
                                            layer.closeAll();
                                        })
                                        var wait = $(".timeOut").html();
                                        timeOut();
                                        function timeOut() {
                                            if (wait == 0) {
                                                window.location.href=path+"/toLogin.action";
                                            } else {
                                                setTimeout(function() {
                                                    $('.timeOut').text(--wait);
                                                    timeOut();
                                                },1000);
                                            }
                                        }
                                    }
                                })
							} else if (data.status == 101) {
                                $("#old_pwd_err").html(data.content);
                            } else if(data.status == 102) {
                                $("#new_pwd_err").html('密码必须6到16位，并且含字母与数字');
                            } else if(data.status == 103) {
                                $("#new_pwd_again_err").html(data.content);
                            } 
                        },
                        error: function(){
                            alert('请求错误，请重新再次请求！');
                        }
                    });
	        	})
	        }
    	})
	})
	
	/*优惠券-兑换中心-兑换按钮*/
	

	/*获取验证码倒计时*/
	var countdown=60;
	function sendemail(){
	    var obj = $("#chongzhi");
	    obj.addClass('noverbtn');
	    settime(obj);
	}
	function settime(obj) { //发送验证码倒计时
	    if (countdown == 0) { 
	    	obj.removeClass('noverbtn');
	        obj.attr('disabled',false);
	        obj.val("获取验证码");
	        countdown = 5; 
	        return;
	    } else { 
	        obj.attr('disabled',true);
	        obj.val("重新发送(" + countdown + ")");
	        countdown--; 
	    } 
		setTimeout(function() { 
	    	settime(obj) 
		},1000) 
	}

	$('#chongzhi').click(function(){
		var amount = $("#amount").val();
		if(amount==null || amount=='' || amount<=0){
			layer.msg('请输入充值金额', {
			    time: 1000
			});
			return false;
		}
		smsSend();
	})
	
	function smsSend(){
	  	var amount = $("#amount").val();
		if(amount==null || amount==''){
			return ;
		}else{
			$.ajax({
                type: 'POST',
                url: path+'/pay/toRecharge.json',
                data: 'pay_type=1'+"&amount="+amount,
                dataType: 'json',
                success: function(data){
                	if(data.status==100){
                		$("#fundsId").val(data.content);
                		sendemail();
                	}else{
                		alert(data.content);
                	}
                },
                error: function(){
                    alert('请求错误，请重新再次请求！');
                }
		    });
		}
	}
	
	function adv(){
		var re_mon=parseFloat($("#amount").val());
		var valid_code = ($("#valid_code").val());
		if(!re_mon || re_mon<=0){
			layer.msg('请输入充值金额', {
			    time: 1000
			});
			return false;
		}
		if(!valid_code){
			layer.msg('请输入验证码', {
			    time: 1000
			});
			return false;
		}
		
		$.ajax({
            type: 'POST',
            url: path+'/pay/recharge/advance.json',
            data: 'valid_code='+valid_code+"&fundsId="+$("#fundsId").val(),
            dataType: 'json',
            success: function(data){
            	if(data.status==100){
            		layer.open({
            			type: 1,
            			title:false,
            			closeBtn:0,
            			area:['388px','221px'],
            			content: $('#prompt'),
            			skin:'addpro',
            			shadeClose:true,
            			success: function(layero, index){
            				$('.pro_close').click(function(){
            					layer.close(index);
            				})
            			}
            		})
            	}else{
            		$("#cz_err").html(data.content);
            		layer.open({
            			type: 1,
            			title:false,
            			closeBtn:0,
            			area:['388px','221px'],
            			content: $('#charge_err'),
            			skin:'addpro',
            			shadeClose:true,
            			success: function(layero, index){
            				$('.pro_close').click(function(){
            					layer.close(index);
            				})
            			}
            		})
            	}
            },
            error: function(){
                alert('请求错误，请重新再次请求！');
            }
		});
	}
	
	function wy_adv(){
		var re_mon=parseFloat($("#wy_amount").val());
		var banklist=$('.banklist').find('a');
		if(!re_mon || re_mon<=0){
			layer.msg('请输入充值金额', {
			    time: 1000
			});
			return false;
		}else if(!banklist.hasClass('active')){
			layer.msg('请您选择充值银行', {
			    time: 1000
			});
			return false;
		}else{
			layer.open({
	        	type: 1,
	        	title: ['充值遇到问题？','background:#ffffff;text-align:center;font-size: 16px;color: #000000;padding: 0;height:60px;line-height: 60px;'],
	        	area: ['320px','220px'],
	        	content:'<div class="chongzhi"><p>充值完成前请不要关闭此窗口，充值完成后请根据您的需要点击下面的按钮</p><p><a href="'+path+'/myFunds.action?type=11" class="cz_ok">查看充值记录</a><a href="'+path+'/investGuide/2/help.action">充值遇到问题</a></p></div>',
	        	success: function(){
	        		$('.cz_ok').click(function(){
	        			layer.closeAll();
	        			window.location.reload();
	        		})
		        }
	    	})
	    	$("#wypay").submit();
		}
	}

});

/*密码验证*/
function oldpwd(){
	var regex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,16})$');
	if(regex.test($("#old_pwd").val())){
		$("#old_pwd_err").html("");
	}else{
		$("#old_pwd_err").html("密码必须6到16位，并且含字母与数字");
	}
}

function pwdVerify(){
	var regex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,16})$');
	if(regex.test($("#new_pwd").val()) && $("#new_pwd").val() != $("#old_pwd").val()){
		$("#new_pwd_err").html("");
	} else if($("#old_pwd").val() == $("#new_pwd").val()){
		$("#new_pwd_err").html("当前修改密码和原密码不能相同");
	} else {
		$("#new_pwd_err").html("密码必须6到16位，并且含字母与数字");
	} 
}

function pwdCheck(){
	if($("#new_pwd").val()!=$("#new_pwd_again").val()){
		$("#new_pwd_again_err").html("2次输入的密码不一致！");
	}else{
		$("#new_pwd_again_err").html("");
	}
}