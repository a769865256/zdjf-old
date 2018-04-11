<!DOCTYPE html>
<html lang="zh-cn" class="no-js">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${selfSite}/zdjf/weixin/assets/css/reset.css">
    <style>
        body, html{
            background-color: #ffffff;
        }
        .con .title-icon img{
            width: 30%;
            margin: 40px auto 0;
            display: block;
        }
        .con .text{
            margin-top: 15px;
            font-size: 20px;
            color: #ff8d8d;
            line-height: 35px;
            min-height: 35px;
            text-align: center;
        }
        .con .tel{
            color: #909090;
            font-size: 16px;
            text-align: center;
            line-height: 50px;
        }
        .con .tel a{
            color: #f84141;
        }
    </style>
</head>
<body>
<!-- bar**public -->
<div class="bar">
    <a href="http://m.zdjfu.com/index.html" class="iconfont back">&#xe605;</a>
    <span>失败</span>
    <div class="iconfont nav J_nav">&#xe604;</div>
    <div class="bar-bg" style="display:none"></div>
    <div class="nav-con" id="barNav" style="display:none">
        <div class="icon-arrow"></div>
        <a href="http://m.zdjfu.com/index.html">首页</a>
        <a href="javascript:void(0)" class="J_appDownload">下载APP</a>
    </div>
</div>
<div class="con">
    <p class="title-icon"><img src="${selfSite}/zdjf/res/mob/img/pay/fail-icon.png" alt=""></p>
    <p class="text">${tipInfo.respDesc}</p>
    <p class="tel">如有疑问请联系客服：<a href="tel:4009991266">400-690-9898</a></p>
</div>
<a href="http://m.zdjfu.com/index.html" class="back-home">返回首页</a>
<script type="text/javascript" src="${selfSite}/zdjf/weixin/assets/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/weixin/assets/js/common.js"></script>
</body>
</html>