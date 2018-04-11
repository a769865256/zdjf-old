layui.use(['carousel','element'], function(){
	var carousel = layui.carousel;
	var element = layui.element;

	/* 获取相隔天数*/
	var offsetDay = getOffsetDays(Date.now(), (new Date(2016, 11, 8)).getTime());//累计运营时间2016/12/8,js中的月份从0开始;
	/*获取数字的各个位数*/
	var ge = Math.floor(offsetDay%10); 
	var x2 = Math.floor(offsetDay%100);
	var a2 = x2/10;
	var sw = Math.floor(a2%10);
	var x1 = Math.floor(offsetDay%1000); 
	var a1 = x1/100;
	var bw = Math.floor(a1%10);
	/*把获取的给个位数的数字赋值给html*/
	var one = $(".time li:nth-of-type(1)").html(bw);
	var tow = $(".time li:nth-of-type(2)").html(sw);
	var three = $(".time li:nth-of-type(3)").html(ge);

	/*轮播图*/
	$('#hezuo_id').cxScroll({
		auto: false,//阻止自动轮播
	});

	/*地图*/
	var map = null,
      	geochina = 'https://data.jianshukeji.com/jsonp?filename=geochina/';
	$.getJSON(geochina + 'china.json&callback=?', function(mapdata) {
		// 数据
		var data = [];
		// 随机数据
		Highcharts.each(mapdata.features, function(md, index) {
			var tmp = {
				name: md.properties.name,
              	value: Math.floor((Math.random() * 1000) + 1) // 生成 1 ~ 100 随机值
			};
			if(md.properties.drilldown) {
				tmp.drilldown = md.properties.drilldown;
			}
			data.push(tmp);
			//console.log(md);
			//console.log(index);
			//console.log(tmp);
			//console.log("名字："+tmp.name +"," + "数值：" + tmp.value);
		});
		map = new Highcharts.Map('ChinaMap', {
			chart: {
              
			},
			title: {
				text: ''
			},
			credits: {
				enabled: false // 禁用版权信息
			},
			tooltip: {  // 每个省份的信息
				useHTML: true,
				headerFormat: '<table><tr><td>{point.name}</td></tr>',
				pointFormat: '<tr><td>省份：</td><td>{point.properties.fullname}</td></tr>' +
				'<tr><td>投资人数：</td><td>{point.value}</td></tr>' ,
				footerFormat: '</table>'
			},
			colorAxis: {
				min: 0,
				minColor: '#fff',
				maxColor: '#000077',
				labels:{
					style:{
						"color":"red","fontWeight":"bold"
					}
				}
			},
			series: [{
				data: data,
				mapData: mapdata,
				joinBy: 'name', // 根据 name 属性进行关联
				name: '投资人数',
				states: {
					hover: {  // 鼠标移入地图的颜色
						color: '#a4edba'
                  	}
				}
    		}]
		});
	});


	// 注1：指定时间到现在时间的时间差天数
	// 注2：这里time1是当前的时间，time2是指定的时间
    function getOffsetDays(time1, time2) {
        var offsetTime = Math.abs(time1 - time2);
        return Math.floor(offsetTime / (3600 * 24 * 1e3));
    }
});