package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.konka.dhtsearch.db.luncene.LuceneUtils;
import com.konka.dhtsearch.db.models.DhtInfo_MongoDbPojo;
import com.konka.dhtsearch.parser.MultiFile;
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

		String searchkeywords = request.getParameter(SEARCHKEYWORDS);
		int pagecount = TextUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
		// URLEncoder.encode("searchkeywords", enc)
		// String name2 = URLEncoder.encode(你的参数);
		// 在接受的页面，进行解码，示例如下：
		String company = URLDecoder.decode(request.getParameter("searchkeywords"), "utf-8");
		String queryString = new String(request.getParameter("searchkeywords").getBytes("ISO8859-1"), "utf-8");
		// System.out.println(queryString);
		// System.out.println(company);
		// System.out.println(searchkeywords);
		// response.sendRedirect(request.getContextPath() + "/SearchResult.jsp");
		List<DhtInfo_MongoDbPojo> dhtInfo_MongoDbPojos = null;
		try {
			dhtInfo_MongoDbPojos = LuceneUtils.search(company, pagecount);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// PrintWriter out = response.getWriter();
		if (!ArrayUtils.isEmpty(dhtInfo_MongoDbPojos)) {
			// out.println(company);
			// try {
			// for (DhtInfo_MongoDbPojo dhtInfo_MongoDbPojo : dhtInfo_MongoDbPojos) {
			// out.println(dhtInfo_MongoDbPojo.getInfo_hash() + "--------");
			// out.println(dhtInfo_MongoDbPojo.getTorrentInfo().getName() + "--------");
			// out.println(dhtInfo_MongoDbPojo.getTorrentInfo().getFilelenth() + "--------");
			// out.println(dhtInfo_MongoDbPojo.getTorrentInfo().getCreattime() + "--------");
			// // out.println(dhtInfo_MongoDbPojo.getTorrentInfo().getMultiFiles().size() + "--------");
			// out.println(dhtInfo_MongoDbPojo.getPeerIp() + "--------");
			// out.println(dhtInfo_MongoDbPojo.getAnalysised() + "--------");
			// out.println("<br>");
			// if (!dhtInfo_MongoDbPojo.getTorrentInfo().isSingerFile()) {
			// List<MultiFile> lists = dhtInfo_MongoDbPojo.getTorrentInfo().getMultiFiles();
			// for (MultiFile multiFile : lists) {
			// out.println(multiFile.getPath());
			// }
			// }
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			request.setAttribute("dhtInfo_MongoDbPojos", dhtInfo_MongoDbPojos);
			request.setAttribute(TOTAL, "128");
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
