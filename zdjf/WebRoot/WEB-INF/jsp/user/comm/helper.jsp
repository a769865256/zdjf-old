<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- box -->
<div class="fix-box" id="fixBox">
	<div class="lump content-kefu">
		<a href="javascript:void(0)" class="iconfont">&#xe60c;</a>
		<a href="javascript:void(0)" class="show-keft"><span class="qq"><i class="iconfont">&#xe608;</i>在线客服</span>
            <!-- WPA Button Begin -->
			<script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODA3MDgyMV80NjAzMjRfNDAwNjkwOTg5OF8"></script>
			<!-- WPA Button End -->
            </a>
	</div>
	<div class="lump content-kefu">
		<a href="javascript:void(0);" class="iconfont J_count-alert">&#xe63c;</a>
		<a href="javascript:void(0);" class="show-keft cursor J_count-alert">　收益计算器</a>
	</div>
	<div class="lump weixin-code">
		<i class="iconfont">&#xe60d;</i>
		<span class="code-show"></span>
	</div>
	<div class="lump app-code">
		<i class="iconfont">&#xe60b;</i>
		<span class="code-show"></span>
	</div>
	<div class="lump back-top cursor" id="toTop"><i class="iconfont">&#xe607;</i></div>
</div>


<!-- calculator -->
<div id="countLay" class="hide">
	<div class="count-darkbg J_CloseCountLay"></div>
	<div class="count-lay">
		<div class="title">收益计算器<a href="javascript:void(0);" class="J_CloseCountLay">×</a></div>
		<div class="cl-input">
			<div class="cl-line">
				<span class="t">投资本金</span><input type="text" id="clCapital" value="10000">元
			</div>
			<div class="cl-line">
				<span class="t">年化收益</span><input type="text" id="clYield" value="12.88">%
			</div>
			<div class="cl-line">
				<span class="t">收益天数</span><input type="text" id="clDay" value="60">天
			</div>
			<div class="cl-line">
				<span class="t">还款方式</span>
				<select name="" id="">
					<option id="1" value="一次性还本付息">一次性还本付息</option>
					<option id="2" value="按日计息，按月付息，到期还本">按日计息，按月付息，到期还本</option>
				</select>
			</div>
			<div class="cl-line cl-btn">
				<a href="javascript:void(0);" class="btn-red J_count-submit">计算</a>
				<a href="javascript:void(0);" class="btn-dark J_count-reset">重置</a>
			</div>
			<div class="cl-result">收益总计<span class="J_recSxcfProfit">0.00</span>元，本息总计<span>0.00</span>元</div>
			<div class="cl-refer">*计算结果仅作参考，最终以实际收益为准</div>
		</div>
		<div class="cl-progress">
			<div class="tit">投资在不同平台的收益对比</div>
			<div class="clp-lay">
				<ul>
					<li class="h1"><b>0.00</b><span class="rec-sxcf J_recSxcf" style="height: 80px;"></span><span>正道金服</span></li>
					<li class="h2"><b>0.00</b><span class="rec-bao J_recBao"></span><span>某宝</span></li>
					<li class="h3"><b>0.00</b><span class="rec-bank J_recBank"></span><span>银行</span></li>
				</ul>
			</div>
		</div>
	</div>
</div>