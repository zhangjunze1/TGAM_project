package cn.edu.shu.xj.ser.controller.user;


import cn.edu.shu.xj.ser.entity.AdminEntity;
import cn.edu.shu.xj.ser.entity.UserEntity;
import cn.edu.shu.xj.ser.handler.BusinessException;
import cn.edu.shu.xj.ser.response.Result;
import cn.edu.shu.xj.ser.response.ResultCode;
import cn.edu.shu.xj.ser.service.IUserService;
import cn.edu.shu.xj.ser.service.impl.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */
@Api(tags = "用户登录模块")
@RestController
@RequestMapping("/User/login")
public class UserLoginController {

    @Autowired
    IUserService userService;

    @Autowired
    TokenService tokenService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/userLogin")
    public Result AdminLogin(@RequestParam("userPhone") String phone,
                             @RequestParam("password") String pwd){
        UserEntity userEntity = userService.findUserByPhone(phone);
        // 用户不存在
        if (userEntity==null){
            throw new BusinessException(ResultCode.NO_USER.getCode(),
                    ResultCode.NO_USER.getMessage());
        }
        String password = userEntity.getUserPwd();
        // 用户密码错误
        if(!password.equals(pwd)){
            throw new BusinessException(ResultCode.ERROR_USER_PWD.getCode(),
                    ResultCode.ERROR_USER_PWD.getMessage());
        }
        if(password.equals(pwd)){
            String token = tokenService.getToken(userEntity);
            return Result.ok().data("user",userEntity).data("token",token);
        }else {
            return Result.error();
        }
    }
}
