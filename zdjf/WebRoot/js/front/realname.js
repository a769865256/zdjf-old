layui.use('form', function(){
	var form = layui.form;
	//监听提交
  	form.on('submit(formDemo)', function(data){
		
	});
});
	
	function getCity(province){
		$.ajax({
                type: 'POST',
                url: path+'/getCity.json',
                data: 'province='+province,
                dataType: 'json',
                success: function(data){
                	var json=eval(data);
                	$("#city").empty();
                	$.each(json,function(index,item){
						var s1=json[index];
						$("#city").append("<option value='"+s1+"'>"+s1+"</option>"); 
					});
                },
                error: function(){
                    alert('请求错误，请重新再次请求！');
                    // 即使加载出错，也得重置
                }
		});
	}
	
	function renderForm(){
		layui.use('form', function(){
			form.render();
		});
	}
	
	
	$('.sendcode').click(function(){
		var url=path+"/bind.json";
		var bank_no=$('.debit_card').val();
		var bank_alias=$(".bank_name").find("option:selected").text();
		var other_phone=$('#other_phone').val();
		var province= $(".bank_province").find("option:selected").text();
		var city=$(".bank_city").find("option:selected").text();
		var card_attribute='C';
		 $.post(url,{"card_attribute":card_attribute,
					"bank_no":bank_no,
					"bank_alias":bank_alias,
					"other_phone":other_phone,
					"province":province,
					"city":city
					},function(data){
					 if(data.status=="100"){
						 $('.sendcode').text('已发送');
					 }else{
					 	$('.error').text(data.content);
					}
				});
		});




	$('.next_two').click(function(){
		var reg = /^\d{16}$/g;   // 以19位数字开头，以19位数字结尾
		var reg2 = /^\d{19}$/g;
		var province = $("#province").val();
		var bankCard = $("#bankCard").val();
		var city = $("#city").val();
		var bank_accounts = $("#bank_accounts").val();
		var phone = $("#phone").val();
        if( bankCard==null || bankCard==""){
           $("#bankerror").html("银行卡号错误！");
           return;
        }
        if(bank_accounts==null || bank_accounts==""){
        	$("#bankerror").html("请选择开户行！");
           	return;
        }
        if(province==null || province==""){
        	$("#bankerror").html("请选择省份！");
           	return;
        }
        if(city==null || city==""){
        	$("#bankerror").html("请选择城市！");
           	return;
        }
        var pattern = /^1[34578]\d{9}$/; 
        if(!pattern.test(phone) ){
        	$("#phoneerror").html("手机号码错误！");
           	return;
        }
        if($("#valid_code").val()==null || $("#valid_code").val()==""){
        	$("#verifierror").html("手机号码错误！");
           	return;
        }
        $("#bindBankCardAdvance").submit();
	})
	
	$('.next_three').click(function(){
		window.open(path+"/pay_password.action");
	});

	var countdown=60; 
	function sendemail(){
	    var obj = $("#btn3");
	    obj.addClass('noverbtn');
	    settime(obj);
	}
	function settime(obj) { //发送验证码倒计时
	    if (countdown == 0) { 
	    	obj.removeClass('noverbtn');
	        obj.attr('disabled',false);
	        obj.val("获取验证码");
	        countdown =60; 
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

	$(".add_lyu select#province").focus(function(){
		$(".sanjiao.provincesj").css("transform","rotate(180deg)");
	});
	$(".add_lyu select#province").blur(function(){
		$(".sanjiao.provincesj").css("transform","rotate(0)");
	});
	$(".add_lyu select#city").focus(function(){
		$(".sanjiao.citysj").css("transform","rotate(180deg)");
	});
	$(".add_lyu select#city").blur(function(){
		$(".sanjiao.citysj").css("transform","rotate(0)");
	});