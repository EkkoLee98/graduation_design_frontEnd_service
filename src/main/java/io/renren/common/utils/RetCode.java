package io.renren.common.utils;

public enum RetCode {
    ;

    public static final Code TOKEN_INVALID = new Code(010001, "Token已失效, 请重新登录！");
    
    public static final Code NUMBER_FORMAT = new Code(100001, "请检查输入类型是否错误");


    /**
     * 编码model类
     */
    static public class Code {
        /**
         * 编码
         */
        private final int code;
        /**
         * 描述信息
         */
        private final String message;

        public Code(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}
