package com.macro.mall.component;

import com.macro.mall.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 * Created by macro on 2018/4/26.
 */
//继承了OncePerRequestFilter，确保每个请求调度都有一次执行。该类检查授权头并验证JWT令牌并在上下文中设置验证。
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    //设置日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    //注入用户用户详情的对象
    @Autowired
    private UserDetailsService userDetailsService;
    //注入json web token Util对象
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    //设置安全传输的头
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    //设置安全传输的头
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    //重写内置过滤器方法
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    //过滤链
                                    FilterChain chain) throws ServletException, IOException {
        //获得授权头，通过将安全传输头tokenHeader传入请求的请求头
        String authHeader = request.getHeader(this.tokenHeader);
        //如果授权头不为空和授权头的前头传入tokenHead
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            //获得授权令牌，通过截取授权头，从安全传输头中截取
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            //将授权令牌放入传输令牌包中，通过获得用户名的方法，获得用户名
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            //将用户名传入日志文件
            LOGGER.info("checking username:{}", username);
            //如果用户名不为空，和 安全管理器的全文控制器的获得授权的方法为空时
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //载入用户信息
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
