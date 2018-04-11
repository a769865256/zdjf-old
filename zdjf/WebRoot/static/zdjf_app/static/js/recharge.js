/*$('.money').bind('input propertychange', function() {
    if($(this).val() != ''){
    	$('.re_addbtn a').addClass('active');
    	$('.re_addbtn a').removeClass('active').attr('href','#');
    }else{
    	$('.re_addbtn a').removeClass('active').attr('href','#');
    }
});*/
$('.jm_password_pay_close').click(function () {
    $(this).parents('.jm_password_pay_alert').hide();
})