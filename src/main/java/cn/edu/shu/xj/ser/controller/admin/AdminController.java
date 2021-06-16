package cn.edu.shu.xj.ser.controller.admin;


import cn.edu.shu.xj.ser.entity.AdminEntity;
import cn.edu.shu.xj.ser.handler.BusinessException;
import cn.edu.shu.xj.ser.response.Result;
import cn.edu.shu.xj.ser.response.ResultCode;
import cn.edu.shu.xj.ser.service.IAdminService;
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
@Api(tags = "管理员接口")
@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    IAdminService adminService;

    @Autowired
    TokenService tokenService;

    @ApiOperation(value = "管理员登录")
    @PostMapping("/AdminLogin")
    public Result AdminLogin(@RequestParam("adminName") String Name,
                            @RequestParam("adminPwd") String pwd){
        AdminEntity adminEntity = adminService.findAdmin(Name);
        // 管理员不存在
        if (adminEntity==null){
            throw new BusinessException(ResultCode.NO_ADMIN.getCode(),
                    ResultCode.NO_ADMIN.getMessage());
        }
        String password = adminEntity.getAdminPwd();
        // 管理员密码错误
        if(!password.equals(pwd)){
            throw new BusinessException(ResultCode.ERROR_ADMIN_PWD.getCode(),
                    ResultCode.ERROR_ADMIN_PWD.getMessage());
        }
        if(password.equals(pwd)){
            String token = tokenService.getToken(adminEntity);
            return Result.ok().data("admin",adminEntity).data("token",token);
        }else {
            return Result.error();
        }
    }


}
