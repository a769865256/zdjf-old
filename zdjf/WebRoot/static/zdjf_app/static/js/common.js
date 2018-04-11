(function(global) {
	var AP = Array.prototype,
		OP = Object.prototype,
		APS = Array.prototype.slice;
	var hostUrl =  window.location.host;
    if(hostUrl == 'www.zdjfu.com' || hostUrl == 'pctest.zdjfu.com'){
    	var rootUrl = '';
	}else{
		var rootUrl = '/zdjf';
	}
    var hostRootUrl = hostUrl + rootUrl;
	//空方法
	function _EMPTY() {}

	//反柯理化
	Function.prototype.uncurring = function() {
		var __this = this;
		return function() {
			return Function.prototype.call.apply(__this, arguments);
		};
	};

	function each(data, fn) {
		var cb = fn || function() {};
		for (var i = 0, len = data.length; i < len; i++) {
			if (cb(data[i], i, data) === false) {
				return false;
			}
		}
	}

	function assign(o1, o2) {
		for (var k in o2) {
			if (o2.hasOwnProperty(k)) {
				o1[k] = o2[k];
			}
		}
		return o1;
	}

	var typeStr = OP.toString.uncurring(),
		util = {};

	each("Array,Object,String,Function,Date,RegExp,Boolean,Number".split(","), function(type) {
		util['is' + type] = function(s) {
			return typeStr(s) === '[object ' + type + ']';
		};
	});

	// JSON
	function stringify(O) {
		if (window.JSON && JSON.stringify) return JSON.stringify(O);
		var S = [],
			J = "";
		if (util.isArray(O)) {
			for (var i = 0; i < O.length; i++)
				S.push(stringify(O[i]));
			J = '[' + S.join(',') + ']';
		} else if (util.isDate(O)) {
			J = "new Date(" + O.getTime() + ")";
		} else if (util.isRegexp(O) || util.isFunction(O)) {
			J = O.toString();
		} else if (util.isObject(O)) {
			for (var i in O) {
				O[i] = typeof(O[i]) == 'string' ? '"' + O[i] + '"' : (typeof(O[i]) === 'object' ? stringify(O[i]) : O[i]);
				S.push(i + ':' + O[i]);
			}
			J = '{' + S.join(',') + '}';
		}

		return J;
	}

	var com_cookie = {

		set: function(name, val, days) {
			var times = new Date(),
				expires = ";expires=";

			if (days) {
				times.setTime(times.getTime() + (60000 * 60 * 24 * days));
				expires += times.toGMTString();
			} else {
				expires += "";
			}

			document.cookie = name + "=" + val + expires + ";path=/";
		},

		//delete cookie
		del: function(name) {
			document.cookie = name + "=;expires=" + (new Date(0)).toGMTString() + ";path=/";
		},

		//get cookie
		get: function(name) {
			var arrCookie = document.cookie.split(";");
			for (var i = 0; i < arrCookie.length; i++) {
				var arr = arrCookie[i].replace(/(^\s+)|(\s+$)/g, "");
				arr = arr.split("=");
				if (arr[0] == name) {
					return arr.slice(1).join("=");
				}
			}
			return "";
		}
	};

	var com_uri = {
		/*
		 * 获取URL查询参数，组装成对象返回
		 * @method getUrlQuery 获取查询参数
		 * @returns {object} 返回组装后对象
		 */
		getUrlQuery: function(str) {

			var search = str || window.location.search;

			if (search === '') return {};

			var str = search.charAt(0) === '?' ? search.substring(1) : search,
				temp = str.split('&'),
				ret = {};

			//生成对象
			for (var i = 0, len = temp.length; i < len; i++) {

				var arg = temp[i].split('=');
				var key = arg[0];
				var value = arg.slice(1).join("=");
				ret[key] = decodeURIComponent(value);
			}

			return ret;
		},

		/*
		 * 组装url地址
		 * @method setUrlQuery 获取查询参数
		 * @param {object} o 需要转化的对象
		 * @returns {string} 返回组装后对象
		 */
		setUrlQuery: function(o) {
			var str = '';

			if (Object.prototype.toString(o) !== "[object Object]") {
				return '';
			}

			for (var x in o) {
				if (o.hasOwnProperty(x)) {
					str = str + x + '=' + String(o[x] == null || o[x] == undefined ? '' : o[x]) + '&';
				}
			}

			str = str.substring(0, str.length - 1);

			return encodeURI(str);
		}
	};

	var com_util = assign(util, {

		boolean : function(str) {
			if (str === "true" || str === true) {
				return true;
			} else if (str === "false" || str === false) {
				return false;
			} else {
				return undefined;
			}
		},

		each: each,

		trim: function(s) {
			return s.replace(/(^\s*)|(\s*$)/g, '');
		},

		indexOf: function(ret, val) {
			for (var i = 0, len = ret.length; i < len; i++) {
				if (ret[i] == val) {
					return i;
				}
			}
			return -1;
		},

		//模版生成
		template: function(d, h) {

			var str = '';

			if (!util.isArray(d)) {
				d = [d];
			}

			if (!h) {
				throw new Error('cann\'t find template string!');
			}

			each(d, function(l, i) {
				l.__INDEX = i + 1;
				str += h.replace(/\{\{\s*([a-zA-Z0-9\_\.\-\|\s]+)\s*\}\}/igm, function($1, $2) {
					var ret, value, tv;
					
					if ($2.indexOf('||') > -1) {
						ret = $2.split('||');
					} else {
						ret = [$2];
					}

					// 命令检测
					// 根据优先级执行相应命令
					// 检测最终数据

					for (var i = 0, len = ret.length; i < len; i++) {
						tv = l[util.trim(ret[i])];
						if (tv !== '' && tv != undefined && tv != null) {
							return tv;
						}
					}
					return '';
				});
			});

			return str;
		},

		//JSON to string
		stringify: stringify,

		parse: function(str) {
			try {
				if (str === "") {
					return [];
				}
				if (window.JSON && JSON.parse) {
					return JSON.parse(str);
				} else {
					return eval("(" + str + ")");
				}
			} catch (e) {
				throw new Error('not valid string');
			}
		},

		format: function(format, time) {
			var o, now;

			now = new Date(time || 0);

			o = {
				"M+": now.getMonth() + 1, //month
				"d+": now.getDate(), //day
				"h+": now.getHours(), //hour
				"m+": now.getMinutes(), //minute
				"s+": now.getSeconds(), //second
				"q+": Math.floor((now.getMonth() + 3) / 3), //quarter
				"S": now.getMilliseconds() //millisecond
			};

			if (/(y+)/.test(format)) {
				format = format.replace(RegExp.$1, (now.getFullYear() + "").substr(4 - RegExp.$1.length));
			}

			for (var k in o) {
				if (new RegExp("(" + k + ")").test(format)) {
					format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] :
						("00" + o[k]).substr(("" + o[k]).length));
				}
			}
			return format;
		},

		/*
		 * 获取命名空间
		 * @method getNamespace 获取命名空间对象
		 * @param {object} ns 命名空间起点对象
		 * @param {string} sns 空间字符串
		 * @returns 返回命名空间内容
		 */
		getNamespace: function(ns, sns) {
			var root = ns,
				ret = util.isArray(sns) ? sns : sns.split('.');

			try {
				//获取服务类型
				for (var i = 0, len = ret.length; i < len; i++) {
					root = root[ret[i]];
				}
			} catch (e) {
				root = ns;
			}

			return root;
		},

		reverse : function(obj) {
			var _obj = {};
			for (var p in obj) {
				if (obj.hasOwnProperty(p)) {
					_obj[obj[p]] = p;
				}
			}

			return _obj;
		},

		uuid : function() {
			var d = new Date().getTime();
			var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
				var r = (d + Math.random()*16)%16 | 0;
				d = Math.floor(d/16);
				return (c=='x' ? r : (r&0x3|0x8)).toString(16);
			});
			return uuid;
		}
	});

	/*
	==============================
	=========== app ==============
	==============================
	*/
	var serviceCon = {
		url: rootUrl,
		loginUrl: '/login.html',
		upload : '/upload_headimage.htm',
		upload2 : '/uploadiccards.htm'
	};

	var transProp = (function whichTransitionEvent(){
		var t,
			el = document.createElement('fakeelement'),
			transitions = {
				'transition':'transitionend',
				'OTransition':'oTransitionEnd',
				'MozTransition':'transitionend',
				'WebkitTransition':'webkitTransitionEnd'
			};

			for(t in transitions){
				if( el.style[t] !== undefined ){
					return transitions[t];
				}
			}
	}());

	function showDropdown(sltElement) {

		var event;
		event = document.createEvent('MouseEvents');
		event.initMouseEvent('mousedown', true, true, window);
		sltElement.dispatchEvent(event);
	}

	var app = {
		timeRule : function(time, now) {
			var date = now || (new Date()).getTime(),
				dif = date - time,
				t = "";
			if (dif < 60000) {
				t = "1分钟前";
			} else if (dif < 60000 * 60) {
				t = Math.floor(dif / 60000) + "分钟前";
			} else if (dif < 60000 * 60 * 24) {
				t = Math.floor(dif / (60000 * 60)) + "小时前";
			} else if (dif < 60000 * 60 * 24 * 2) {
				t = "昨天";
			} else {
				t = util.format("MM-dd hh:mm", time);
			}

			return t;
		},

		percentColor : function(num) {
			var color = "#000";
			if (num < 33.3) {
				color = "#ea0909";
			} else if (num < 66.6){
				color = "#ea9809";
			} else {
				color = "#77ac19";
			}

			return "<em style='font-style:normal;color:"+ color +"'>" + num + "%</em>";
		},

		wait : function(opt) {
			var node = opt.selector,
				type = opt.type,
				fn = opt.callback,
				parent = opt.parent,
				obtn = opt.obtn || $(node).text(),
				nbtn = opt.nbtn || obtn;

			(parent || $("body")).delegate(node, type, function(e) {
				$(this).removeClass("active").html(nbtn);
				fn.call(this, e, function() {
					$(node).addClass("active").html(obtn);
				});
			});
		},

		location: function(u, s) {
			window.location = u + (s ? '?' + s : '');
		},

		HTML : {
			LOADING : ['<svg xmlns="http://www.w3.org/2000/svg" width="15px" height="15px" viewBox="0 0 50 50" style="margin-bottom:-2px;">',
						'<circle fill="none" opacity="0.10" stroke="#ffffff" stroke-width="3" cx="25" cy="25" r="20"/>',
						'<g transform="translate(25,25) rotate(-90)">',
						'<circle style="stroke: #f1f1f1; fill:none; stroke-width: 3px; stroke-linecap: round" stroke-dasharray="110" stroke-dashoffset="0" cx="0" cy="0" r="20" transform="rotate(309.85)">',
						'<animate attributeName="stroke-dashoffset" values="360;140" dur="2.2s" keyTimes="0;1" calcMode="spline" fill="freeze" keySplines="0.41,0.314,0.8,0.54" repeatCount="indefinite" begin="0"/>',
						'<animateTransform attributeName="transform" type="rotate" values="0;274;360" keyTimes="0;0.74;1" calcMode="linear" dur="2.2s" repeatCount="indefinite" begin="0"/>',
						'</circle></g></svg>'].join(""),

			LOADING2 : ['<svg xmlns="http://www.w3.org/2000/svg" width="15px" height="15px" viewBox="0 0 50 50" style="margin-bottom:-2px;">',
						'<circle fill="none" opacity="0.10" stroke="#999999" stroke-width="3" cx="25" cy="25" r="20"/>',
						'<g transform="translate(25,25) rotate(-90)">',
						'<circle style="stroke: #999999; fill:none; stroke-width: 3px; stroke-linecap: round" stroke-dasharray="110" stroke-dashoffset="0" cx="0" cy="0" r="20" transform="rotate(309.85)">',
						'<animate attributeName="stroke-dashoffset" values="360;140" dur="2.2s" keyTimes="0;1" calcMode="spline" fill="freeze" keySplines="0.41,0.314,0.8,0.54" repeatCount="indefinite" begin="0"/>',
						'<animateTransform attributeName="transform" type="rotate" values="0;274;360" keyTimes="0;0.74;1" calcMode="linear" dur="2.2s" repeatCount="indefinite" begin="0"/>',
						'</circle></g></svg>'].join("")
		},

		// code process
		processCode: function(code, msg) {

			var codeSet = {
					9999 : '服务器繁忙,请稍后再试'
				};

			switch (code) {
				case 9998:
					app.removeInfo();
					window.location.href = serviceCon.loginUrl;
					break;
				default:
					app.tip(msg || codeSet[code] || code + '未知错误类型，请补充！');
			}
		},

		tip: function(content, fn) {
            layer.open({
                content: content
                ,skin: 'msg'
                ,time: 2 //2秒后自动关闭
            });
			fn && fn();
		},

		highlight: function(val, temp) {

			temp = temp || '';
			if (val !== '' && val != null && val != undefined) {
				temp = temp.replace(new RegExp(val, 'g'), function() {
					return "<span style='color:red;'>" + val + "</span>";
				});
			}

			return temp;
		},

		loading: function(content) {
			var _html = ['<div id="loading_box" class="loading_box" style="">',
				'<div><img src="images/w_loader.gif" /></div>',
				'<span>{{content}}</span></div>'
			].join('');

			if ($('#loading_box').length > 0) {
				$('#loading_box').remove();
			} else {
				$('body').append(com_util.template({
					content: content || '发送中...'
				}, _html));
			}
		},

		ajax: function(data, fn, err, context) {
			var suc, fail, __EMPTY = function() {}, _ajax, URI, TYPE, rawData = new FormData();
				if (arguments.length < 4) {
					context = err;
					err = __EMPTY;
				}
				if (com_util.isArray(fn)) {
					suc = fn[0];
					fail = fn[1] || __EMPTY;
				} else {
					suc = fn;
					fail = __EMPTY;
				}

				URI = data.$URI || "";
				TYPE = data.$TYPE || 'get';
				delete data.$URI;
				delete data.$TYPE;
				
//				if (TYPE.toLowerCase() !== "get") {
//					for (var i in data) {
//						rawData.append(i, data[i]);
//					}
//				}
				_ajax = $.ajax({
					url: serviceCon.url + URI,
					type: TYPE,
					//data: TYPE.toLowerCase() === "get" ? data : rawData,
					data : data,
					contentType : "application/x-www-form-urlencoded",
					//processData : false,
					dataType: 'JSON',
					timeout: 45000
				});

				_ajax.then(function(res) {
					if (res.status == 100) {
						suc && suc.call(context || null, res.content);
					} else {
						app.processCode(res.status, res.content || "");
						fail && fail.call(context || null, res.content);
					}
				}, err);

				return _ajax;
		},

		removeInfo : function() {
			com_cookie.del('__OT1__');
			com_cookie.del('__ON1__');
		},

		scrollTop : function(num) {
			var cTop = $(document).scrollTop();

			if (cTop > num) {
				$(document).scrollTop(num);
			}
		},

		carousel : function() {
			var _index = 0,
				length = 3,
				time = 10, //秒
				timeZone = null;
			$("body").delegate(".banner .menu_index li", "click", function() {
				var $this = $(this),
					$target = $($this.attr("data-target")),
					index = _index = Number($this.attr("data-num"));

				if ($(this).hasClass("active")) return;

				$target.find(".active").css("zIndex", 4).fadeOut(1000, function() {
					$(this).removeClass("active").css("zIndex", 3);
				});

				$target.find("div[data-num=" + index + "]").show(0).addClass("active");


				$this.addClass("active").siblings().removeClass("active");

				stop();
				play();
			});

			function play() {
				timeZone = setTimeout(function() {
					$(".banner .menu_index li[data-num="+ (_index + 1 < length ? _index + 1 : 0) +"]").click();
				}, time * 1000);
			}

			function stop() {
				clearTimeout(timeZone);
			}

			play();
		},

		setCoupon: function(type, id, amount, name) {
			var ret = [];
			if (util.isArray(type)) {
				ret = type;
			} else {
				Array.prototype.push.apply(ret, arguments);
			}

			return ret.join("|");
		},

		getCoupon: function(str) {
			if (!str) return [];
			var arr = str.split(","),
				ret = [];

			$(arr).each(function(i, item) {
				var _arr = item.split("|");

				ret.push({
					type : Number(_arr[0]),
					id : _arr[1],
					amount : Number(_arr[2]),
					name : unescape(_arr[3])
				});
			});

			return ret;
		}
	};

	/*
	==============================
	=========== export ==============
	==============================
	*/
	global.$com = {
		uri : com_uri,
		util : com_util,
		cookie : com_cookie,
		app : app,
		API : serviceCon.url,
		UPLOAD : serviceCon.upload,
		prop : transProp,
		dispatch : showDropdown,
		IP : "117.149.16.71",
		KEY : "3QvlI7PArDBTa@4YC%efmFxU",
		hostUrl : hostUrl,
		rootUrl : rootUrl,
		hostRootUrl : hostRootUrl
	};
}(window));
