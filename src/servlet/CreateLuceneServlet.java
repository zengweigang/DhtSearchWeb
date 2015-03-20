package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.konka.dhtsearch.db.luncene.LuceneManager;

public class CreateLuceneServlet extends HttpServlet {
	private static final long serialVersionUID = 5355659034287728426L;
	private static final String CREATE = "createIndex";
	private static final AtomicBoolean starting = new AtomicBoolean(false);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()//
				+ path + request.getServletPath();
		System.out.println(basePath);
		if (action == null || action.trim().length() == 0) {
			out.print("启动：" + basePath + "?action=" + CREATE);
			out.println("<br>");
			out.print("停止：" + basePath + "?action=stop");
			out.println("<br>");
			out.print("查看状态：" + basePath + "?action=state");
		} else if (action.equals(CREATE)) {// 启动
			if (!starting.get()) {
				try {
					starting.set(true);
					out.print("开始创建：--");
					LuceneManager.getInstance().createIndex();
					out.print("创建成功：--");
				} catch (Exception e) {
					out.print("创建失败：请重试--");
					e.printStackTrace();
				}
				starting.set(false);
			}
		}
		out.flush();
		out.close();
	}
}
