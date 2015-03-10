package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Page;
import pojo.PageInfo;

import com.konka.dhtsearch.db.luncene.LuceneSearchResult;
import com.konka.dhtsearch.db.luncene.LuceneUtils;
import com.konka.dhtsearch.db.models.DhtInfo_MongoDbPojo;
import com.konka.dhtsearch.util.ArrayUtils;
import com.konka.dhtsearch.util.TextUtils;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 5355659034287728426L;
	public static final String PAGE = "page", SEARCHKEYWORDS = "searchkeywords";
	public static final String TOTAL = "total";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// super.doPost(request, response);
		// setCharacterEncoding('GBK');

		// request.setContentType("text/html;charset=utf-8");
		response.setContentType("text/html;charset=utf-8");
		// System.out.println(request.getCharacterEncoding());
		String page = request.getParameter(PAGE);
		System.out.println("是多少=" + page);

		String searchkeywords = request.getParameter(SEARCHKEYWORDS);
		int currentPage = TextUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
		// URLEncoder.encode("searchkeywords", enc)
		// String name2 = URLEncoder.encode(你的参数);
		// 在接受的页面，进行解码，示例如下：
		String company = URLDecoder.decode(request.getParameter("searchkeywords"), "utf-8");
		String queryString = new String(request.getParameter("searchkeywords").getBytes("ISO8859-1"), "utf-8");
		// System.out.println(queryString);
		// System.out.println(company);
		// System.out.println(searchkeywords);
		// response.sendRedirect(request.getContextPath() + "/SearchResult.jsp");
		LuceneSearchResult luceneSearchResult = null;
		int total = 0;
		try {
			luceneSearchResult = LuceneUtils.search(company, currentPage);
			total = luceneSearchResult.getTotal();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()//
				+ path + request.getServletPath();
		System.out.println(path);
		System.out.println(basePath);
		if (!ArrayUtils.isEmpty(luceneSearchResult.getLists())) {
			int pagecount = Math.round((float) total / 10f);
			PageInfo pageInfo = new PageInfo(currentPage, pagecount,basePath,request);
			pageInfo.addParam(SEARCHKEYWORDS, null);
			pageInfo.generate();
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("luceneSearchResult", luceneSearchResult);
			request.getRequestDispatcher("/SearchResult.jsp").forward(request, response);

		} else {
			PrintWriter out = response.getWriter();
			out.println("没有找到");
			out.flush();
			out.close();
		}

	}

	@Override
	public void init() throws ServletException {
		super.init();
	}
}
