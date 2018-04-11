/*我的理财》右侧切换*/
	$('.we_lf ul li').click(function(){
		var index=$(this).index();
		if(index==0){
			$('.we_rt').load(path+'/wealth.action');
		}
		if(index==1){
			$('.we_rt').load(path+'/capital/list.action');
		}
		if(index==2){
			$('.we_rt').load(path+'/conduct.action');
		}
		if(index==3){
			$('.we_rt').load(path+'/discount.action');
		}
		if(index==4){
			$('.we_rt').load(path+'/account.action');
		}
		if(index==5){
			$('.we_rt').load(path+'/invitation.action');
		}
		if(index==6){
			$('.we_rt').load(path+'/message_center.action');
		}
		$(this).addClass('active').siblings().removeClass('active');
		
	})