layui.use('carousel', function(){
  	/*最外面轮播*/
  	$(".sbnbg_2017_warp").height($(window).height());
  	var mySwiper = new Swiper('.swiper-container',{
		direction : 'vertical',
		pagination : '.swiper-pagination-h',
		paginationClickable :true,
		mousewheelControl : true,
		width : window.innerWidth,
		height : window.innerHeight,
		loop : true,
		observer:true,
		observeParents:true,
	});
  	/*轮播图5的轮播*/
  	var swiper = new Swiper('.iSlider-wrapper', {  
        loop : true,
        autoplay : 800,  
        effect: 'coverflow',  
        grabCursor: true,  
        centeredSlides: true, 
        slidesPerView: 'auto',  
        prevButton:'.swiper-button-prev',
        nextButton:'.swiper-button-next',
        coverflow: {
            rotate: 0,// 旋转的角度  
            stretch: 100,// 拉伸   图片间左右的间距和密集度  
            depth: 150,// 深度   切换图片间上下的间距和密集度  
            modifier: 4.2,// 修正值 该值越大前面的效果越明显  
            slideShadows : false// 页面阴影效果  
        }  
    });


  	/*轮播3的左饼图*/
  	var chart_left = $('#pieChart_left').highcharts({
		chart: {
			type: 'pie',
			backgroundColor: 'rgba(0,0,0,0)'
		},
		title :{
		    text:null
		},
		colors:[
	        '#1667b2',//第一个颜色，
	        '#0be1f4',//第二个颜色
	    ],
		credits: {
			enabled: false // 禁用版权信息
		},
		tooltip: {
			headerFormat: '{series.name}<br>',
			pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
			pie: {
				innerSize: '150',
				depth: 45,
				cursor: 'pointer',
				dataLabels: {
					enabled: false, //是否显示提示线
					format: '<b>{point.name}</b>: {point.percentage:.1f} %',
					style: {
						color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
					}
				},

				states: {
					hover: {
						enabled: false
					}
				},
				slicedOffset: 20, // 突出间距
				point: { // 每个扇区是数据点对象，所以事件应该写在 point 下面
					events: {
						// 鼠标滑过是，突出当前扇区
						mouseOver: function() {
							this.slice();
						},
						// 鼠标移出时，收回突出显示
						mouseOut: function() {
							this.slice();
						},
						// 默认是点击突出，这里屏蔽掉
						click: function() {
							return false;
						}
					}
				}
			}
		},
		series: [{
			name: '金额',
			data: [
				['APP', 58.9],
				['PC', 41.1]
			]
		}]
	});
	/*轮播3的右饼图*/
	var chart_right = $('#pieChart_right').highcharts({
		chart: {
			type: 'pie',
			backgroundColor: 'rgba(0,0,0,0)'
		},
		title :{
		    text:null
		},
		colors:[
	        '#0048b4',//第一个颜色，
	        '#1667b2',//第二个颜色
	        '#0be1f4',//第三个颜色
	        '#cccccc',//第四个颜色
	    ],
		credits: {
			enabled: false // 禁用版权信息
		},
		tooltip: {
			headerFormat: '{series.name}<br>',
			pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
			pie: {
				innerSize: '150',
				depth: 45,
				cursor: 'pointer',
				dataLabels: {
					enabled: false,
					format: '<b>{point.name}</b>: {point.percentage:.1f} %',
					style: {
						color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
					}
				},

				states: {
					hover: {
						enabled: false
					}
				},
				slicedOffset: 20, // 突出间距
				point: { // 每个扇区是数据点对象，所以事件应该写在 point 下面
					events: {
						// 鼠标滑过是，突出当前扇区
						mouseOver: function() {
							this.slice();
						},
						// 鼠标移出时，收回突出显示
						mouseOut: function() {
							this.slice();
						},
						// 默认是点击突出，这里屏蔽掉
						click: function() {
							return false;
						}
					}
				}
			}
		},
		series: [{
			name: '项目期限',
			data: [
				['0-1个月', 69.28],
				['1-2个月', 18.58],
				['2-3个月', 10.36],
				['3-6个月', 1.78]
			]
		}]
	});
	/*轮播4的柱状图*/
	var barGraph = $('#barGraph').highcharts({
	    chart: {
	        type: 'column',
	        backgroundColor: 'rgba(0,0,0,0)',
	        // events: { //事件
         //        load:  getForm  // 图表加载完毕后执行的回调函数
         //    }
        },
	    title :{
		    text:null
		},
	    credits: {
			enabled: false // 禁用版权信息
		},
		legend: {
			itemStyle: { cursor: 'pointer', font: 'normal 16px 微软雅黑', color: '#999' }, //图例样式
			itemHoverStyle: { color: '#fff' }, //图例鼠标划过时样式
            align: 'right', //水平方向位置
            verticalAlign: 'top', //垂直方向位置
            x: 0, //距离x轴的距离
            y: 40 //距离Y轴的距离
        },
	    xAxis: {
	    	title: {
                style: {
                    color:'#293159'
                }
            },
            labels:{                    
                style: {
                	font: 'normal 16px 微软雅黑',
                	color:'#293159'
                }
            },
	    	lineColor: '#20293e',
			tickColor: '#20293e',
	        categories: ['90后', '80后', '70后', '60后', '50后', '其他']
	    },
		yAxis: {
			gridLineWidth: 0,
			lineWidth: 1,
			lineColor: '#293159',
			tickInterval: 10, // 刻度值  
			tickLength: 10,
			tickWidth: 1,
			tickColor: '#293159',
			title: { 
				enabled: false, //去除Y轴标题
                style: {
                    color:'#293159'
                }
            },
            labels:{                    
                style: {
                	font: 'normal 16px 微软雅黑',
                	color:'#293159'
                },
                formatter: function () {  
                    return this.value + '%';//y轴加上%  
                }
            }
		},
	    plotOptions: {
	    	column: {
		        dataLabels: {
		            enabled: true,  //显示数量提示
		            style:{
	                    color: "#17508a",
			            fontFamily: "微软雅黑",
                    	textOutline:"none"
		            },
		            formatter: function () {  
	                    return this.y + '%';//y轴加上%  
	                }  
		        }
		    },
	        series: {
	            borderWidth: 0, //去边框
	            borderColor: 'transparent'
	        }
	    },

	    series: [
		    {
		    	name: '用户数占比',
		    	color: { //线性渐变
					linearGradient: { x1: 0, x2: 0, y1: 0, y2: 1 },
					stops: [
					      [0, '#17508a'],
					      [1, '#1e304c']
					]
				},
				//data: []
		        data: [22.68, 35.47, 20.14, 13.49, 5.56, 1.66]
		    },
		    {
		      	name: '累计投资额占比',
		      	color: { //线性渐变
					linearGradient: { x1: 0, x2: 0, y1: 0, y2: 1 },
					stops: [
					      [0, '#fc706e'],
					      [1, '#ed4e3e']
					]
				},
				//data: []
		      	data: [14.57, 45.33, 18.54, 12.52, 6.69, 1.08]
		   	}, 
   		]
	});
	// 与后台进行数据交互  
    // function getForm(){
    //     jQuery.getJSON("test!test.do?id=123456", null, function(data) {     
    //         //为图表设置值     
    //         barGraph.series[0].setData(data);     
    //     });       
    // }
    //定时刷新 列表数据  
   	// $(document).ready(function() {
   	//     //每隔3秒自动调用方法，实现图表的实时更新    
   	//     window.setInterval(getForm,50000);     
   	// });
});