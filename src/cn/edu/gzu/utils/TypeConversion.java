package cn.edu.gzu.utils;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class TypeConversion {
       public static <T> T convert(HttpServletRequest request,Class<T> clazz)
       {
    	  
    	   
    	 try {
    		   T t=clazz.newInstance();
    		   BeanUtils.populate(t, request.getParameterMap());
    		   return t;   
    	 } catch (Exception e) {
		      throw new RuntimeException("×ª»¯Ê§°Ü");
    	 }
    
       }
}
