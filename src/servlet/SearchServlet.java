package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Sets.SetView;
import com.konka.dhtsearch.db.models.DhtInfo_MongoDbPojo;
import com.konka.dhtsearch.db.mongodb.MongodbUtilProvider;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 5355659034287728426L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// super.doPost(request, response);
//		setCharacterEncoding('GBK');
//	setc
//		request.setContentType("text/html;charset=utf-8");
//		System.out.println(request.getCharacterEncoding());
		String searchkeywords = request.getParameter("searchkeywords");
		
//		URLEncoder.encode("searchkeywords", enc)
//		String name2 =  URLEncoder.encode(你的参数); 
//		在接受的页面，进行解码，示例如下： 
		String company = URLDecoder.decode(request.getParameter("searchkeywords"),"utf-8"); 
		String queryString= new String (request.getParameter("searchkeywords").getBytes("ISO8859-1"),"utf-8" );
		System.out.println(queryString);
		System.out.println(company);
		System.out.println(searchkeywords);
		response.setContentType("text/html;charset=utf-8");
//		response.sendRedirect(request.getContextPath() + "/SearchResult.jsp");
		PrintWriter out = response.getWriter();
		out.println(queryString);
		try {
			out.println(MongodbUtilProvider.getMongodbUtil().findAll(DhtInfo_MongoDbPojo.class).size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
		
		// if(u.getUsername().equals("admin")&&u.getPassword().equals("admin"))
		// {
		// response.sendRedirect(request.getContextPath()+"/login_success.jsp");
		// }
		// else
		// {
		// response.sendRedirect(request.getContextPath()+"/login_failure.jsp");
		// }
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}
}
