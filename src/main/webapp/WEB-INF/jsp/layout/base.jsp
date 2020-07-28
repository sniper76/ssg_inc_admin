<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="Keywords" content="셀픽" />
	<meta name="description" content="쇼핑몰과 매장 판매자를 위한 통합판매재고관리 서비스" />
	<meta name="Author" content="신세계I&C" />
	<meta name="Copyright" content="2020 SHINSEGAE I&C Inc. All Rights Reserved" />
	<meta name="reply-to" content="sellpick@shinsegae.com" />
	<!-- 검색엔진 허가 설정 -->
	<meta name="robots" content="all" >
	<meta name="title" content="타이틀명" />

	<title>신세계아이앤씨 관리자</title>

	<!-- style -->
	<link rel="stylesheet" href="/css/connect.css">
	<link rel="stylesheet" href="/css/main.css">

	<!-- script -->
	<script src="/js/libs/jquery.min.js"></script>
	<script src="/js/libs/jquery.easing.1.3.js"></script>
	<script src="/js/libs/modernizr-3.2.0.base.js"></script>
	<script src="/js/jsrender/jsrender.min.js"></script>
	<script src="/js/libs/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="/js/common.js"></script>
</head>
<body>

<tiles:insertAttribute name="skipNav" />


<section id="wrap">
	<h1 class="blind">관리자 페이지</h1>

	<div id="container" class="conr_main">
		<div class="conr_in">
			<div class="flt_clr">
				<tiles:insertAttribute name="header" />

				<div class="content flt_l">
					<tiles:insertAttribute name="top" />

					<tiles:insertAttribute name="body" />

					<tiles:insertAttribute name="footer" />
				</div>
			</div>
		</div>
	</div>
</section>

</body>
</html>