$(document).ready(function(){
	// 绑定事件
	bindEvent();
});

/**
 * 绑定事件
 */
function bindEvent(){
	
	create();
	uploader();
}
function uploader(){
    var apkUploadUrl =$('#apkUploadUrl').val();
    var apkDownloadUrl =$("#apkDownloadUrl").val();
    var id = $('#advertiseId').val();
	var baseUrl = $($('base')[0]).attr('href').trim();
	var url =  baseUrl + 'upload/uploadFile1.action';
	var dir = '/config/advertises/' + id + '/';
	var data = { dir : dir };
	$("#img_uploader").plupload({
        // General settings
        runtimes : 'html5,flash,silverlight,html4',
        url : url,
        dragdrop: true,
        // Specify what files to browse for
        filters :{
        	max_file_size : '2mb',
            // Specify what files to browse for
            mime_types: [
                {title : "Image files", extensions : "jpg,png"}
            ],
            //不允许选取重复文件
            prevent_duplicates : true
        },
        // Rename files by clicking on their titles
        rename: true,
        // Sort files
        sortable: true,
        // Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
        dragdrop: true,
        // 当值为true时会为每个上传的文件生成一个唯一的文件名，并作为额外的参数post到服务器端，参数明为name,值为生成的文件名
        unique_names: true,
        // Views to activate
        views: {
            list: true,
            thumbs: true, // Show thumbs
            active: 'thumbs'
        },
        multipart_params: data,
        // Flash settings
        flash_swf_url : '/plupload/js/Moxie.swf',
        // Silverlight settings
        silverlight_xap_url : '/plupload/js/Moxie.xap',
        // Post init events, bound after the internal events
        init : {
            BeforeUpload: function(up, file) {
                // Called right before the upload for a given file starts, can be used to cancel it if required
        		if(!id) {
        			up.stop();
        			/*showMessage('广告位ID异常请刷新页面！');*/
        		}
            },
            FilesAdded: function(up, files) {
                // Called when files are added to queue
            	if(up.files.length > 1)
            	 {
            		up.splice(1,1);
            		/*showMessage('只允许上传一张图片!');*/
            		return false;
            	 }
            },


            // UploadComplete: function(up, files) {
            // 	// Called when all files are either uploaded or failed
            // 	plupload.each(files, function(file) {
            // 		 var fileName = file.name;
            // 		 var allPath = baseUrl + 'upload' + dir + fileName;
            //         var html = '<figure data-am-widget="figure" class="am am-figure am-figure-default "   data-am-figure="{  pureview: true }">'
            // 		 		+'<img class="am-img-circle am-img-thumbnail" src="' + allPath +'" data-rel="' + allPath + '" alt="图片描述"/></figure>';
            // 		 $('#imageUrl').val(allPath);
            // 		 $('#imgs').html(html);
            //     });
            // 	$.AMUI.figure.init();
            // },
            FileUploaded: function (up, file, obj) {

                var rsp = JSON.parse(obj.response);

                var fileName = file.name;
                // var allPath ='https://120.27.232.220/' + 'upload' + dir + fileName;
                var allPath ='http://172.16.1.115:8080/img/' + 'upload_files' + dir + fileName;
               var html = '<div class="am am-figure am-figure-default "  >'
                    +'<input type="text" readonly="true" class="am-img-circle am-img-thumbnail" value="' + allPath +'"  placeholder="图片描述"/></div>';
                $('#imageUrl').val(allPath);
                $('#imgs').html(html);

//                $.AMUI.figure.init();
            },

            Error: function(up, args) {
                // Called when error occurs
                //showMessage(args.message);
            	console.log(args);
            }
        }
    });
}

function create(){
	$('.adv_create').unbind('click').on('click', function(){
		var baseUrl = $($('base')[0]).attr('href').trim();
		var url =  baseUrl + 'config/advertise/create.action';
		var redirectUrl = baseUrl + 'config/advertise/toList.action';
		var formData = $('#form').serialize();
//		var loading = layer.load(2, {shade:0.5});
		var options = {  
				url: url,   
	            type: 'POST',  
	            data: formData,
	            success: function(data) {  
	            	if(data) {
	            		if(data.status == 1) {
//	            			layer.close(loading);
//	            			showMessage('发布广告位成功！', function(){
//	            				window.location.href = redirectUrl;
//	            			});
	            			window.parent.location.reload();
	            		}else{
//	            			layer.close(loading);
//	            			showMessage(data.errorMsg);
	            		}
	            	}else{
//	            		layer.close(loading);
	            	}
	            }, 
	            error: function(data) {  
//	            	layer.close(loading);
//        			showMessage(data.responseText);
	            }  
	        };
		$.ajax(options);
		return false;
	});
}

//function validator(){
//	var options = {
//		  // 是否使用 H5 原生表单验证，不支持浏览器会自动退化到 JS 验证
//		  H5validation: false,
//
//		  // 内置规则的 H5 input type，这些 type 无需添加 pattern
//		  H5inputType: ['email', 'url', 'number'],
//
//		  // 验证正则
//		  // key1: /^...$/，包含 `js-pattern-key1` 的域会自动应用改正则
//		  patterns: {
//			  'js-pattern-percent' : /\d+(\.\d+)?/
//		  },
//
//		  // 规则 class 钩子前缀
//		  patternClassPrefix: 'js-pattern-',
//
//		  activeClass: 'am-active',
//
//		  // 验证不通过时添加到域上的 class
//		  inValidClass: 'am-field-error',
//
//		  // 验证通过时添加到域上的 class
//		  validClass: 'am-field-valid',
//
//		  // 表单提交的时候验证
//		  validateOnSubmit: true,
//
//		  // 表单提交时验证的域
//		  // Elements to validate with allValid (only validating visible elements)
//		  // :input: selects all input, textarea, select and button elements.
//		  allFields: ':input:visible:not(:button, :disabled, .am-novalidate)',
//
//		  // 调用 validate() 方法的自定义事件
//		  customEvents: 'validate',
//
//		  // 下列元素触发以下事件时会调用验证程序
//		  keyboardFields: ':input:not(:button, :disabled,.am-novalidate)',
//		  keyboardEvents: 'focusout, change', // keyup, focusin
//
//		  // 标记为 `.am-active` (发生错误以后添加此 class)的元素 keyup 时验证
//		  activeKeyup: false,
//
//		  // textarea[maxlength] 的元素 keyup 时验证
//		  textareaMaxlenthKeyup: true,
//
//		  // 鼠标点击下列元素时会调用验证程序
//		  pointerFields: '', /* 'input[type="range"]:not(:disabled, .am-novalidate), ' +
//		  'input[type="radio"]:not(:disabled, .am-novalidate), ' +
//		  'input[type="checkbox"]:not(:disabled, .am-novalidate), ' +
//		  'select:not(:disabled, .am-novalidate), ' +
//		  'option:not(:disabled, .am-novalidate)',*/
//		  pointerEvents: 'click',
//
//		  // 域通过验证时回调
//		  onValid: function(validity) {
//		  },
//
//		  // 验证出错时的回调， validity 对象包含相关信息，格式通 H5 表单元素的 validity 属性
//		  onInValid: function(validity) {
//		  },
//
//		  // 域验证通过时添加的操作，通过该接口可定义各种验证提示
//		  markValid: function(validity) {
//		    // this is Validator instance
//		    //var options = this.options;
//		   // var $field  = $(validity.field);
//		   // var $parent = $field.closest('.am-form-group');
//		  //  $field.addClass(options.validClass).
//		    //  removeClass(options.inValidClass);
//
//		    //$parent.addClass('am-form-success').removeClass('am-form-error');
//
//		    //options.onValid.call(this, validity);
//		  },
//
//		  // 域验证失败时添加的操作，通过该接口可定义各种验证提示
//		  markInValid: function(validity) {
//		   // var options = this.options;
//		    //var $field  = $(validity.field);
//		   // var $parent = $field.closest('.am-form-group');
//		    //$field.addClass(options.inValidClass + ' ' + options.activeClass).
//		     // removeClass(options.validClass);
//
//		    //$parent.addClass('am-form-error').removeClass('am-form-success');
//
//		    //options.onInValid.call(this, validity);
//		  },
//
//		  // 自定义验证程序接口，详见示例
//		  validate: function(validity) {
//		    // return validity;
//		  },
//
//		  // 定义表单提交处理程序
//		  //   - 如果没有定义且 `validateOnSubmit` 为 `true` 时，提交时会验证整个表单
//		  //   - 如果定义了表单提交处理程序，`validateOnSubmit` 将会失效
//		  //        function(e) {
//		  //          // 通过 this.isFormValid() 获取表单验证状态
//		  //          // 注意： 如果自定义验证程序而且自定义验证程序中包含异步验证的话 this.isFormValid() 返回的是 Promise，不是布尔值
//		  //          // Do something...
//		  //        }
//		  submit: null
//		}
//	$('#form').validator(options);
//}