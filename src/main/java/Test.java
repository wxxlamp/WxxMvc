import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wxxlamp
 * @date 2020/05/19~17:22
 */
@WebServlet(urlPatterns = "/hhh")
public class Test extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("初始化...");
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello Servlet");
        PrintWriter out = resp.getWriter();
        out.print("hello me");
    }

    @Override
    public void destroy() {
        System.out.println("销毁...");
    }
}
