<%@ page language="java" import="java.util.*,java.net.URLDecoder"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt " %> --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.print(path);
	System.out.print(basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=URLDecoder.decode(request.getParameter("searchkeywords"),
					"utf-8")%> 磁力链接 - 磁力搜索</title>
<meta name="keywords" content="磁力链接, 磁力链, 磁力搜索, 磁力链接搜索, BT搜索, 纸牌屋">
<meta name="description" content="纸牌屋的磁力链接">
<meta name="viewport" content="width=device-width">
<script type="text/javascript" src="./js/jqPaginator.js"></script>

<link type="text/css" rel="stylesheet" href="../css/default.css">
<link rel="shortcut icon" type="image/x-icon"
	href="http://www.btbook.net/static/img/favicon.ico">
<script src="../js/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="../js/jqPaginator.js" type="text/javascript"></script>
<script type="text/javascript">
	document.getElementById("search").focus();
	document.forms[0].onsubmit = function(e) {
		e.preventDefault();
		var query = document.getElementById("search").value;
		if (!query) {
			document.getElementById("search").focus();
			return false;
		}
		var url = '/DhtSearch/servlet/SearchServlet?searchkeywords='
				+ encodeURIComponent(encodeURIComponent(query));
		window.location = url;
		return false;
	};
</script>
</head>
<body>
	<script type="text/javascript">
		$.jqPaginator('#pagination1', {
			totalPages : 100,
			visiblePages : 10,
			currentPage : 3,
			onPageChange : function(num, type) {
				$('#p1').text(type + '：' + num);
			}
		});
		$.jqPaginator('#pagination2', {
			totalPages : 100,
			visiblePages : 10,
			currentPage : 3,
			prev : '<li class="prev"><a href="javascript:;">Previous</a></li>',
			next : '<li class="next"><a href="javascript:;">Next</a></li>',
			page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
			onPageChange : function(num, type) {
				$('#p2').text(type + '：' + num);
			}
		});
	</script>
	<div id="wrapper">

		<div class="header-div">
			<form class="search-form" action="http://www.btbook.net/search"
				method="get">
				<a href="http://www.btbook.net/" title="Btbook home"> <img
					src="../assets/logo_40.png" alt="Btbook" class="nav-logo"> </a> <input
					type="text" id="search" title="Search"
					value="<%=URLDecoder.decode(request.getParameter("searchkeywords"), "utf-8")%>"
					autocomplete="off" name="q"> <input type="submit"
					id="btnSearch" value="搜 索" class="blue">
			</form>

		</div>

		<div id="content">
			<div id="wall">

				<div class="search-statu">
					<span>大约 <%=request.getAttribute("total")%> 条结果。</span>
				</div>
				<c:forEach items="${dhtInfo_MongoDbPojos}" var="user" varStatus="vs">

					<div class="search-item">
						<div class="item-title">
							<a href="magnet:?xt=urn:btih:<c:out value="${user.info_hash}"/>"
								target="_blank"><c:out value="${user.torrentInfo.name}" />
							</a>
						</div>
						<div class="item-list">
							<c:choose>
								<c:when test="${user.torrentInfo.singerFile!=true}">
									<c:forEach items="${user.torrentInfo.multiFiles}"
										var="multiFiles" varStatus="vs" end="2">
										<p>
											<c:out value="${multiFiles.path}" />
											<c:out value="${multiFiles.formatSize}" />
										</p>
									</c:forEach>

									<c:if test="${fn:length(user.torrentInfo.multiFiles)>3}">
										<p>......</p>
									</c:if>
								</c:when>
								<c:otherwise>
									<p>
										<c:out value="${user.torrentInfo.name}" />
										<c:out value="${user.torrentInfo.filelenth}" />
									</p>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="item-bar">
							<span class="cpill fileType1">视频</span> <span>创建时间： <b>
									<c:out value="${user.torrentInfo.formatCreatTime}" /> </b> </span><span>文件大小：
								<b class="cpill yellow-pill"> <!-- 								formatSize --> <c:out
										value="${user.torrentInfo.formatSize}" /> </b> </span><span>文件数：${fn:length(user.torrentInfo.multiFiles)}<b>
							</b> </span> <a
								href="magnet:?xt=urn:btih:<c:out value="${user.info_hash}"/>">磁力链接</a>
						</div>
					</div>
				</c:forEach>
				<div class="bottom-pager">

					<c:forEach items="${dhtInfo_MongoDbPojos}" var="user" 
						varStatus="vs">
						<a
							href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/2-1.html">&lt;</a>

					</c:forEach>


					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/2-1.html">&lt;</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/1-1.html">1</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/2-1.html">2</a>
					<span>3</span> <a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/4-1.html">4</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/5-1.html">5</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/6-1.html">6</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/7-1.html">7</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/8-1.html">8</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/9-1.html">9</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/10-1.html">10</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/11-1.html">11</a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/11-1.html"><%=request.getAttribute("page")%></a>
					<a
						href="http://www.btbook.net/search/%e7%ba%b8%e7%89%8c%e5%b1%8b/4-1.html">&gt;</a>


				</div>
			</div>
		</div>
	</div>



	<p id="p1"></p>
	<ul class="pagination" id="pagination1"></ul>
	<p id="p2"></p>
	<ul class="pagination" id="pagination2"></ul>

	<div class="push"></div>
	<div class="footer">
		<span>©2016 konka.com</span> <span><a
			href="http://www.konka.com/mobile">移动版</a> | <a
			href="http://www.konka.com/online">在线播放</a> | <a
			href="http://www.konka.com/about">关于</a> </span>
	</div>
	<script charset="gbk" type="text/javascript" src="../js/opensug.js"></script>
</body>
</html>
