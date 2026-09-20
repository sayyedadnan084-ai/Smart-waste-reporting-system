package com.cleancity;
import com.google.gson.Gson; import jakarta.servlet.http.HttpServletResponse; import java.io.IOException;
public class Json { static Gson gson=new Gson(); static void send(HttpServletResponse r,Object o) throws IOException {r.setContentType("application/json");r.setCharacterEncoding("UTF-8");r.getWriter().print(gson.toJson(o));} static void error(HttpServletResponse r,int code,String msg)throws IOException{r.setStatus(code);send(r,java.util.Map.of("error",msg));}}
