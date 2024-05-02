package com.leoliu.train.filter;

import com.leoliu.train.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoginMemberFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.拿到请求的路径
        String path = exchange.getRequest().getURI().getPath();
        //2.排除不需要被访问的路径
        if (path.contains("/admin")
                || path.contains("/business/redis")
                || path.contains("/hello")
                || path.contains("/member/login")
                || path.contains("/member/send-code")
                || path.contains("/member/member/send-code")
                || path.contains("/business/kaptcha")
        ) {
            log.info("不需要登录验证：{}", path);
            return chain.filter(exchange);
        }else {
            log.info("需要登录验证：{}", path);
        }
        //3.获取header的token参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
        log.info("会员登录验证开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            log.warn("token为空，请求被拦截" );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        //4.校验token是否合法,有效期是否过期等
        boolean validate = JwtUtil.validate(token);
        if (validate){
            log.info("token有效，放行该请求");
            return chain.filter(exchange);
        }
        else {
            log.warn("token无效，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

    }

    @Override
    public int getOrder() {
        return 0;

    }
}
