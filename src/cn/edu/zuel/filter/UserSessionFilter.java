package cn.edu.zuel.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class UserSessionFilter implements Filter {
    private String []excludeUrls=new String[]{"/login","/register","/verifyCodeServlet","/existUserName","/userAjaxRegister","/ajaxRegister.jsp"};
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        HttpServletResponse servletResponse1 = (HttpServletResponse) servletResponse;
        String contextPath = servletRequest1.getContextPath();
        for(int i=0;i<excludeUrls.length;i++){
            String excludeUrl=contextPath+excludeUrls[i];
            String URI=servletRequest1.getRequestURI();
            if(excludeUrl.equals(URI)){
                filterChain.doFilter(servletRequest1,servletResponse1);
                return;
            }
        }
        HttpSession session = servletRequest1.getSession();
        Object user=session.getAttribute("user");
        if(user==null){

            servletResponse1.sendRedirect("login");
            return;
        }
        filterChain.doFilter(servletRequest1,servletResponse1);
    }

    @Override
    public void destroy() {

    }
}
