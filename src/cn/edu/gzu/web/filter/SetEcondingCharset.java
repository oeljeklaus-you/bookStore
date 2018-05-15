package cn.edu.gzu.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class SetEcondingCharset implements Filter {
    private FilterConfig filterConfig;
    @Override
	public void init(FilterConfig arg0) throws ServletException {
		   this.filterConfig=arg0;

	}
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		   HttpServletRequest request=(HttpServletRequest) arg0;
		   HttpServletResponse response=(HttpServletResponse) arg1;
		   //解决post请求乱码问题
		   String charset="UTF-8";
		   String econding=filterConfig.getInitParameter("econding");
		   if(econding==null)
		   {
			   econding=charset;
		   }
		   request.setCharacterEncoding(econding);
		   response.setContentType("text/html;charset="+econding);
		   //解决get请求的乱码问题
		   MyHttpServletRequest req=new MyHttpServletRequest(request);
		   chain.doFilter(req, response);
		   
	}

	

}

class MyHttpServletRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	public String getParameter(String name) {
		String value=request.getParameter(name);
		if(value==null)
		{
			return null;
		}
		try {
			if("get".equalsIgnoreCase(request.getMethod()))
				
				value=new String(value.getBytes("ISO-8859-1"),request.getCharacterEncoding());
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("编码错误");
			
		}
		return value;
	}	
}