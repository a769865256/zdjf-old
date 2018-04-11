layui.use(['laydate'], function(){
	var laydate= layui.laydate;
	

	var todayDate = new Date();
	var markdata=$('.payDate').text();
	var obj=JSON.parse(markdata);
	laydate.render({
		elem: '#ca_date',
		position: 'static',
		showBottom: false,
		type:'date',
		format:'yyyy-MM-dd',
		mark:obj,
		change: function(value, date, endDate){
			console.log(value); //得到日期生成的值，如：2017-08-18
			console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
		}
	});
});