package cn.edu.shu.xj.ser.handler;

import cn.edu.shu.xj.ser.annotation.PassToken;
import cn.edu.shu.xj.ser.annotation.UserLoginToken;
import cn.edu.shu.xj.ser.entity.UserEntity;
import cn.edu.shu.xj.ser.handler.BusinessException;
import cn.edu.shu.xj.ser.response.ResultCode;
import cn.edu.shu.xj.ser.service.impl.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器：写一个拦截器去获取token并验证token
 * 实现一个拦截器就需要实现HandlerInterceptor接口
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object object) throws Exception{
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new BusinessException(ResultCode.NO_TOKEN.getCode(),
                            ResultCode.NO_TOKEN.getMessage());
                }
                // 获取 token 中的 shop id
                String shopId;
                long l;
                try {
                    shopId = JWT.decode(token).getAudience().get(0);
                    l = Long.parseLong(shopId);
                } catch (JWTDecodeException j) {
                    throw new BusinessException(ResultCode.JWT_WRONG.getCode(),
                            ResultCode.JWT_WRONG.getMessage());
                }
                UserEntity userEntity = userService.findUserById(l);
                if (userEntity == null) {
                    throw new BusinessException(ResultCode.NO_USER.getCode(),
                            ResultCode.NO_USER.getMessage());
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userEntity.getUserPwd())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new BusinessException(ResultCode.JWT_WRONG.getCode(),
                            ResultCode.JWT_WRONG.getMessage());
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}

