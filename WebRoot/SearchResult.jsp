<%@ page language="java" import="java.util.*,java.net.URLDecoder"
	contentType="text/html; charset=utf-8"%>
<%@ page language="java"
	import="com.konka.dhtsearch.db.luncene.LuceneSearchResult"%>
<%@ page language="java"
	import="com.konka.dhtsearch.db.models.DhtInfo_MongoDbPojo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String searchkeywords = URLDecoder.decode(request.getParameter("searchkeywords"), "utf-8");
	// 			request.getc
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=searchkeywords%> 磁力链接 - 磁力搜索</title>
<meta name="keywords" content="磁力链接, 磁力链, 磁力搜索, 磁力链接搜索, BT搜索, 纸牌屋">
<meta name="description" content="<%=searchkeywords%>的磁力链接">
<meta name="viewport" content="width=device-width">

<link type="text/css" rel="stylesheet" href="../css/default.css">

<script src="../js/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="../js/jqPaginator.js" type="text/javascript"></script>
<%
	LuceneSearchResult luceneSearchResult = (LuceneSearchResult) request.getAttribute("luceneSearchResult");
	List<DhtInfo_MongoDbPojo> lists = luceneSearchResult.getLists();
%>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="header.jsp" />
		<div id="content">
			<div id="wall">
				<div class="search-statu">
					<span>大约 ${luceneSearchResult.total} 条结果。</span>
				</div>
				<c:forEach items="${luceneSearchResult.lists}" var="user"
					varStatus="vs">
					<div class="search-item">
						<div class="item-title">
							<a
								href="<%=basePath%>servlet/DetailsServlet?info_hash=<c:out value="${user.info_hash}"/>"
								target="_blank"> <c:out value="${user.torrentInfo.name}" />
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
										value="${user.torrentInfo.formatSize}" /> </b> </span><span>文件数：

								<c:choose>
									<c:when test="${user.torrentInfo.singerFile==true}">
										<c:out value="1" />
									</c:when>
									<c:otherwise>
									${fn:length(user.torrentInfo.multiFiles)}
								</c:otherwise>
								</c:choose> <b> </b> </span> <a
								href="magnet:?xt=urn:btih:<c:out value="${user.info_hash}"/>">磁力链接</a>
						</div>
					</div>
				</c:forEach>

				<div class="bottom-pager">
					<c:forEach items="${pageInfo.pageList}" var="pages" varStatus="vs">
						<c:choose>
							<c:when test="${pages.pageNumber eq pageInfo.currentPage}">
								<a><font color="red">${pages.dispalyName}</font> </a>
							</c:when>
							<c:otherwise>
								<a href="${pages.uri}">${pages.dispalyName}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<p id="p1"></p>
	<ul class="pagination" id="pagination1"></ul>
	<p id="p2"></p>
	<ul class="pagination" id="pagination2"></ul>

	<div class="push"></div>
	<jsp:include page="footer.jsp" />
</body>
</html>
