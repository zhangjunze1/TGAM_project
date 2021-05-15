package cn.edu.shu.xj.ser.response;

public enum ResultCode implements CustomizeResultCode{
    /**
     * 20000 成功
     */
    SUCCESS(20000,"成功"),
    /**
     * 20001 失败
     */
    ERROR(20001,"失败"),
    /**
     *  401:jwt出错
     */
    JWT_WRONG(401, "JWT出错"),
    /**
     *  404:没有找到对应的请求路径
     */
    PAGE_NOT_FOUND(404, "你要请求的页面好像暂时飘走了...要不试试请求其它页面?"),
    /**
     *  500:服务端异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器冒烟了...要不等它降降温后再来访问?"),
    /**
     * 3001:"算数异常"
     */
    ARITHMETIC_EXCEPTION(3001,"算数异常"),
    /**
     * 3011:"无token，请重新登录"
     */
    NO_TOKEN(3011,"无token，请重新登录"),
    /**
     * 3012:"该电话号码尚未有用户注册"
     */
    NO_USER(3012,"该电话号码尚未有用户注册"),
    /**
     * 3013:"用户输入密码错误"
     */
    ERROR_USER_PWD(3013,"用户输入密码错误"),
    /**
     * 4001:"本管理员用户名不存在"
     */
    NO_ADMIN(4001,"本管理员用户名不存在"),
    /**
     * 4002:"管理员用户名对应密码错误"
     */
    ERROR_ADMIN_PWD(4002,"管理员用户名对应密码错误"),
    /**
     * 4003:"没有任何车辆导入，所以无法进行城市与数量统计"
     */
    NO_CAR_CITY(4003,"没有任何车辆导入，所以无法进行城市与数量统计"),
    /**
     *  2001:未知异常
     */
    UNKNOW_SERVER_ERROR(2001, "未知异常,请联系管理员!");





    private Integer code;

    private String message;

    ResultCode(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
