package io.renren.config;

import io.renren.common.utils.R;
import io.renren.common.utils.RetCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理配置
 *
 * @author zes
 * @email 779732613@qq.com
 * @date 2021-01-21 09:30:54
 */
@RestControllerAdvice
@Slf4j
public class ExceptionConfig {

    @Resource
    private HttpServletRequest request;

    /**
     * 处理类型转换的异常
     */
    @ExceptionHandler(value = NumberFormatException.class)
    public R handleNumberFormatException(NumberFormatException exception) {
        log.error("系统出现异常: " + exception + ",请求路径:" + request.getServletPath()
                + ",请求方法:" + request.getMethod());
        log.error("系统出现异常：", exception);
        RetCode.Code code = RetCode.NUMBER_FORMAT;

        return R.error(code.getCode(), code.getMessage());
    }

    /**
     * 处理其余的异常
     */
    @ExceptionHandler(value = Exception.class)
    public R handleOthersException(Exception exception) {
        log.error("系统出现异常: " + exception + ",请求路径:" + request.getServletPath()
                + ",请求方法:" + request.getMethod());
        log.error("系统出现异常：", exception);
        return R.error(999999, "系统未知异常");
    }


}
