<%@page import="com.zdjf.domain.fund.CoinGoods"%>
<%@page import="com.zdjf.domain.user.UserCoupon"%>
<%@page import="com.zdjf.domain.user.UserGift"%>
<%@page import="com.zdjf.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String type = (String) request.getAttribute("type");
	String discount_type = (String) request.getAttribute("discount_type");
	String coin = (String) request.getAttribute("coin");
	String gift_num = (String) request.getAttribute("gift_num");
	String coupon_num = (String) request.getAttribute("coupon_num");
	String dis_type=(String) request.getAttribute("dis_type");
	List list = (List) request.getAttribute("myDiscountList");
	List goodsList = (List) request.getAttribute("myGoodsList");
	String goods_type=(String) request.getAttribute("goods_type");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>正道金服</title>
<!-- reset/iconfont -->
<link rel="stylesheet" href="<%=path%>/css/front/reset.css">
<link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/css/front/index.css">
<link rel="stylesheet" href="<%=path%>/css/front/wealth.css">
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<style>
#pro_page {
	text-align: center;
}
#pro_page2 {
	text-align: center;
}
</style>
</head>
<body>
	<!-- header -->
	<div class="header">
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="open hide">
		<div class="tlt">
			<p>
				为了确保您的正常投资和资金安全，请开通新浪支付存钱罐。<a href="javascript:">立即开通</a>
			</p>
		</div>
	</div>
	<div class="wealth">
		<jsp:include page="./left.jsp"></jsp:include>
		<div class="we_rt">
			<!-- 我的优惠 -->

			<div class="discount we_rtbox">
				<%-- <form action="<%=path %>/myCou.action" id="myCou" method="get"> --%>
				<div class="dis_tav">
					<p>
						<span 
						<%if(""==dis_type||dis_type.equals("1")){ %>
						class="dis_active"
						<%} %>
						>优惠券管理</span> 
						<span
						<%if(""!=dis_type&&dis_type.equals("2")){ %>
						class="dis_active"
						<%} %>
						>兑换中心</span>
					</p>
					<div class="dis_tav_txt">
						<span>正经值余额: <i><%=coin%></i></span> <a
							href="javascript:;">查看</a>
					</div>
				</div>

				<div class="dis_tab">
					<form action="<%=path%>/myCou.action" method="get" id="myCou">
						<input class="hide" id="type" name="type" value="<%=type %>">
						<input class="hide" id="discount_type" name="discount_type" value="<%=discount_type %>">
						<input class="hide" id="dis_type" name="dis_type" value="<%=dis_type %>">
						<div class="dis_tab_box manage<%if("".equals(dis_type)||dis_type.equals("1")){%><%}else{ %> hide<%}%>"
						>
							<div class="dis_cher_ticket">
								<a href="javascript:typeSubmit('1')"
									<%if (type == "" || "1".equals(type)) {%> class="active" <%}%>>红包券(<i><%=gift_num%></i>)
								</a> <a href="javascript:typeSubmit('2')"
									<%if (type != null && "2".equals(type)) {%> class="active" <%}%>>加息券(<i><%=coupon_num%></i>)
								</a>
								<!-- 	<a href="javascript:" class="active">提现券(<i>3</i>)</a>  -->
							</div>
							<div class="dis_cher_list">
								<div class="dis_cher_overdue">
									<a href="javascript:disTypeSubmit('1')"
										<%if (discount_type == "" || "1".equals(discount_type)) {%>
										class="active" <%}%>>未使用</a> <a
										href="javascript:disTypeSubmit('2')"
										<%if (discount_type != "" && "2".equals(discount_type)) {%>
										class="active" <%}%>>已使用</a> <a
										href="javascript:disTypeSubmit('3')"
										<%if (discount_type != "" && "3".equals(discount_type)) {%>
										class="active" <%}%>>已过期</a>
									<!-- <a href="javascript:">即将过期</a> -->
								</div>
								<ul>
									<%if(type == "" || "1".equals(type)){
										if (list != null && list.size() > 0) {
											for (int i = 0; i < list.size(); i++) {
											UserGift useGift=(UserGift)list.get(i);	
									%>
									<li>
										<div class="listbox
											<%if(("1".equals(type)||""==type)&&discount_type.equals("1")){ %>
												state1
											<%}else if(("1".equals(type)||""==type)&&discount_type.equals("2")){%>
												state2
											<%}else if(("1".equals(type)||""==type)&&discount_type.equals("3")){%>
												state3
											<%}%>
										">
											<div class="list_state">
												<p><%=useGift.getGift_name() %></p>
											<p><small>￥</small><span><%=useGift.getAmount() %></span></p>
											</div>
											<div class="list_stlt">
												<p>投资≥<%=useGift.getMax_amount() %>元 收益天数≥<%=useGift.getMax_days() %>天</p>
												<%if(useGift.getUse_type()==3){%>
												<p>限非新手标</p>
												<%}else if(useGift.getUse_type()==2){ %>
												<p>限新手标</p>
												<%}else if(useGift.getUse_type()==1){ %>
												<p>全部</p>
												<%} %>
												<p>有效时间：<%=DateUtil.formatDate(useGift.getStart_date())%>-<%=DateUtil.formatDate(useGift.getEnd_date())%></p>
											</div>
										</div>
									</li>
									<%
										}
										}
										}else{
										if (list != null && list.size() > 0) {
											for (int i = 0; i < list.size(); i++) {
												UserCoupon userCoupon=(UserCoupon)list.get(i);
									%>
									<li>
										<div class="listbox 
											<%if(("2".equals(type)&&"1".equals(discount_type))||("2".equals(type)&&userCoupon.getStatus()==1)){ %>
												state4
											<%}else if("2".equals(type)&&userCoupon.getStatus()==2){%>
												state2
											<%}else if("2".equals(type)&&userCoupon.getStatus()==3){%>
												state3
											<%}%>
										">
											<div class="list_state">
												<p><%=userCoupon.getCoupon_name() %></p>
												<p><small>+</small><span><%=userCoupon.getInterest() %>%</span></p>
											</div>
											<div class="list_stlt">
												<p>
												<%-- <%if(userCoupon.getMin_amount()!=null){%>
												投资≥
												<%=userCoupon.getMin_amount() %>
												元 
												<%} %> --%>
												<%if(userCoupon.getMin_days()>0){%>
												收益天数≥
												<%=userCoupon.getMin_days() %>
												天
												<%} %>
												</p>
												<%if(userCoupon.getUse_type()==3){%>
												<p>限非新手标</p>
												<%}else if(userCoupon.getUse_type()==2){ %>
												<p>限新手标</p>
												<%}else if(userCoupon.getUse_type()==1){ %>
												<p>全部</p>
												<%} %>
												<p>有效时间：<%=DateUtil.formatDate(userCoupon.getStart_date())%>-<%=DateUtil.formatDate(userCoupon.getEnd_date())%></p>
											</div>
										</div>
									</li>
									<%}
									}} %>
								</ul>
								<!-- 无红包时显示 -->
								<div class="no_listbox no_hb" style="display: none;"></div>
								<!-- 无加息券时显示 -->
								<div class="no_listbox jxq" style="display: none;"></div>
								<!-- 分页 -->
								<div id="pro_page"></div>
							</div>
						</div>
					</form>
					<form action="<%=path%>/myCou.action" method="get" id="myGoods">
						<input class="hide" id="dis_type2" name="dis_type" value="<%=dis_type %>">
						<input class="hide" id="goods_type" name="goods_type" value="<%=goods_type %>">
						<div class="dis_tab_box exchange<%if(!"".equals(dis_type)&&dis_type.equals("2")){%><%}else{%> hide<%}%>" 
						>
							<div class="dis_cher_ticket">
								<a href="javascript:goodsTypeSubmit('1')" <%if (goods_type == "" || "1".equals(goods_type)) {%> class="active" <%}%>>红包券</a>
								<a href="javascript:goodsTypeSubmit('2')" <%if (goods_type != "" && "2".equals(goods_type)) {%> class="active" <%}%>>加息券</a> 
									<!-- <a href="javascript:">提现券</a> -->
							</div>
							<div class="dis_cher_list">
								<ul>
									<%if(goodsList.size()>0){
										for(int i=0;i<goodsList.size();i++){
											CoinGoods goods=(CoinGoods)goodsList.get(i);
											if(goods_type == "" || "1".equals(goods_type)){
									%>
												<input class="hide getCoin_cost" name="getCoin_cost" value="<%=goods.getCoin_cost() %>">
												<li>
													<div class="listtent tent1">
														<h3><%=goods.getAmount() %>元红包</h3>
														<p>
															投资≥<%=goods.getInvestment_quota() %>元 收益天数≥<%=goods.getMin_day() %>天
															<br><%if(goods.getUse_type()==1){%>
															全部
															<%}else if(goods.getUse_type()==2){%>
															限新手标
															<%} else if(goods.getUse_type()==3){%>
															限非新手标
															<%}%>
															<br>领取后<%=goods.getEffective_day() %>天有效
														</p>
														<a href="javascript:exchange(<%=goods.getId()%>,<%=goods.getAmount() %>,<%=goods.getCoin_cost() %>,<%=goods.getGoods_type() %>)" ><%=goods.getCoin_cost() %>正经值兑换</a>
													</div>
												</li>
											<%}else{%>
												<input class="hide getCoin_cost" name="getCoin_cost" value="<%=goods.getCoin_cost() %>">
												<li>
													<div class="listtent tent2">
														<h3><%=goods.getAmount() %>%加息券</h3>
														<p>
															<%-- 投资≥<%=goods.getInvestment_quota() %>元 --%> 收益天数≥<%=goods.getMin_day() %>天
															<br><%if(goods.getUse_type()==1){%>
															全部
															<%}else if(goods.getUse_type()==2){%>
															限新手标
															<%} else if(goods.getUse_type()==3){%>
															限非新手标
															<%}%>
															<br>领取后<%=goods.getEffective_day() %>天有效
														</p>
														<a href="javascript:exchange(<%=goods.getId()%>,<%=goods.getAmount() %>,<%=goods.getCoin_cost() %>,<%=goods.getGoods_type() %>)"  ><%=goods.getCoin_cost() %>正经值兑换</a>
													</div>
												</li>
											<%} %>
										<% }%>
									<%}%>
								</ul>
								<!-- 无红包时显示 -->
								<div class="no_listbox no_hb" style="display: none;"></div>
								<!-- 无加息券时显示 -->
								<div class="no_listbox jxq" style="display: none;"></div>
								<!-- 分页 -->
								<div id="pro_page2"></div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- content end-->


	<!-- footer -->
	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- footer end -->
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
	<script src="<%=path%>/module/sticky-header.js"></script>
	<script src="<%=path%>/module/layui/layui.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/layer/2.4/layer.js"></script>
	<script src="<%=path%>/js/front/wealth.js"></script>
	<script type="text/javascript">
        /*点击查看弹出正经值明细表*/
        $(".dis_tav_txt a").click(function(){
            $.ajax({
                type: 'POST',
                url: '<%=path%>/myCoinList.action',
                dataType: 'json',
                success: function(data){
                    if(data.status==100){
                        zjzmx(data.content);
					}
                },
                error:function(data) {
                    alert(data.msg);
                },
            });

        });
        function zjzmx(data){
            var head = '<table><thead><tr><th>日期</th><th>类型</th><th>变动金额(元)</th><th>当前金额(元)</th></tr></thead><tbody>';
            var tr = '';
            $.each(data,function(i, n){
                tr += "<tr><td>"+n.create_time+"</td><td>"+n.type+"</td><td>"+n.amount+"</td><td>"+n.balance+"</td></tr>"
            });
            var content = head+tr+"</tbody></table>";
            layer.open({
                type: 1,
                skin: 'zjzmxb',
                title: ['正经值明细','background:#ffffff;text-align:center;font-size: 16px;color: #000000;padding: 0;height:60px;line-height: 60px;'],
                area: ['800px','auto'],
                content:content,
                success: function(){
                }
            })
        }
		function typeSubmit(data){
			$("#type").val(data);
			$("#dis_type").val(1);
			$("#dis_type2").val(1);
			$("#discount_type").val();
			$("#myCou").submit();
		}
		function disTypeSubmit(data){
			$("#discount_type").val(data);
			$("#dis_type").val(1);
			$("#dis_type2").val(1);
			$("#myCou").submit();
		}
		function goodsTypeSubmit(data){
			$("#goods_type").val(data);
			$("#dis_type").val(2);
			$("#dis_type2").val(2);
			$("#myGoods").submit();
		}
		
		/* 兑换红包 */
		var arr = [];
		for(var i=0; i<$(".getCoin_cost").length; i++){
			arr.push($(".getCoin_cost")[i].value);
		}
		var min = Math.min.apply(null, arr);
		function exchange(coin_good_id,money,cost,goods_type){
			var descript='';
			if(goods_type==1){
				descript='元红包券';
			}else if(goods_type==2){
				descript='%加息券';
			}
			if(Number($(".dis_tav_txt span i").html()) < min){
				layer.open({
		        	type: 1,
		        	title: ['提示','background:#ffffff;text-align:center;font-size: 16px;color: #000000;padding: 0;height:60px;line-height: 60px;'],
		        	area: ['320px','220px'],
		        	content:'<div class="ex"><p>正经值不足!</p><a href="javascript:" class="exbtn">确认</a></div>',
		        	success: function(){
		        		/*确认兑换*/
			        	$('.exbtn').click(function(){
			        		layer.closeAll()
			        	})
			        }
		    	})
			} else {
				layer.open({
		        	type: 1,
		        	title: ['正经值兑换确认','background:#ffffff;text-align:center;font-size: 16px;color: #000000;padding: 0;height:60px;line-height: 60px;'],
		        	area: ['320px','220px'],
		        	content:'<div class="ex"><p>您确认使用'+cost+'正经值兑换'+money+descript+'？</p><a href="javascript:" class="exbtn">确认兑换</a></div>',
		        	success: function(){
		        		/*确认兑换*/
			        	$('.exbtn').click(function(){
			        		var url='<%=path%>'+'/coinStream/exchange.action';
			        		$.post(url,{"coin_good_id":coin_good_id},function(data){
	        					layer.open({
						        	type: 1,
						        	title: ['提示','background:#ffffff;text-align:center;font-size: 16px;color: #000000;padding: 0;height:60px;line-height: 60px;'],
						        	area: ['320px','220px'],
						        	content:'<div class="ex"><p>'+data.content+'</p><a href="javascript:" class="exbtn">确认</a></div>',
						        	success: function(){
						        		/*确认兑换*/
							        	$('.exbtn').click(function(){
							        		window.parent.location.reload();
							        	})
							        }
						    	});
			        		});
			        		layer.closeAll()
			        	})
			        }
		    	})
			}
		}
		var path='<%=path%>';
		var total="${page1.total}";

		layui.use(['layer','laypage','element','carousel'], function(){
			var laypage=layui.laypage;
			laypage.render({  /*参考链接http://www.layui.com/doc/modules/laypage.html*/
				elem: 'pro_page', 	//这里是 ID，不用加 # 号
				limit:6,
				groups:5,
				first:'首页',
				last:'尾页',
				theme:'#1187f1',
				count: total,		//数据总数，从服务端得到
				curr: function(){
					  var page = location.search.match(/currentPage=(\d+)/);  
	                   return page ? page[1] : 1;  
				}(),
				jump:function(obj,first){
                    var type=$("#type").val();
                    var dis_type=$("#dis_type").val();
                    var discount_type=$("#discount_type").val();
                    var goods_type=$("#goods_type").val();
					if(!first){
						window.location=path+'/myCou.action?limit=6&currentPage='+obj.curr
						+'&type='+type+'&dis_type='+dis_type+'&discount_type='+discount_type+'&goods_type='+goods_type;
					}
				}
			});
		})
		var total2="${page2.total}";
		layui.use(['layer','laypage','element','carousel'], function(){
			var laypage=layui.laypage;
			laypage.render({  /*参考链接http://www.layui.com/doc/modules/laypage.html*/
				elem: 'pro_page2', 	//这里是 ID，不用加 # 号
				limit:6,
				groups:5,
				first:'首页',
				last:'尾页',
				theme:'#1187f1',
				count: total2,		//数据总数，从服务端得到
				curr: function(){
					  var page = location.search.match(/goodscurrentPage=(\d+)/);  
	                   return page ? page[1] : 1;  
				}(),
				jump:function(obj,first){
                    var dis_type=$("#dis_type").val();
                    var discount_type=$("#discount_type").val();
                    var goods_type=$("#goods_type").val();
					if(!first){
						window.location=path+'/myCou.action?limit=6&goodscurrentPage='+obj.curr
						+'&type='+type+'&dis_type='+dis_type+'&discount_type='+discount_type+'&goods_type='+goods_type;
					}
				}
			});
		})
	</script>
</body>
</html>
