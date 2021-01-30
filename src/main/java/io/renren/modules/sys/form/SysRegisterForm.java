package io.renren.modules.sys.form;

import lombok.Data;

/**
 * 注册表单
 * @author ZeS
 * @date 2021/1/30 15:48
 */
@Data
public class SysRegisterForm {
    private String username;
    private String password;
    private String email;
    private String captcha;
    private String uuid;
}
