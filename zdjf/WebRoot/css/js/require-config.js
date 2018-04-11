require.config({
	paths:{
		'jquery':'../../module/jquery/jquery.min',
        'jslide':'../../module/slides/jquery.flexslider',
        'slideeasy':'../../module/slides/jquery.easing',
        'layer':'../../module/layer/layer',
        'zclip':'../../module/zclip/jquery.zclip',
        'validata':'../../module/validata/jquery.validate.min',
        'validMethod':'../../module/validata/validMethod',        
        'layer':'../../module/layer/layer',
        'WdatePicker':'../../module/DatePicker/WdatePicker',
        'layercss': '../../module/layer/skin/layer',               //异步请求layer插件需要的layer.css文件
	    'slidecss':'../../module/slides/flexslider'
    },
	map: {
        '*': { 
            'cssjs': '../../module/requirejs/css',
        }
    },    
    shim:{
        'layer':{
            deps:['jquery'],
            exports:'layer'
        },
        'slideeasy':{
            deps:['jquery'],
            exports:'slideeasy'
        },
        'jslide':{
            deps:['jquery'],
            exports:'jslide'
        },
        'zclip':{
            deps:['jquery'],
            exports:'zclip'
        },
        'WdatePicker':{
            deps:['jquery'],
            exports:'WdatePicker'
        }
    }
});











