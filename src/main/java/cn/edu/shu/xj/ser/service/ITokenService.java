package cn.edu.shu.xj.ser.service;


import cn.edu.shu.xj.ser.entity.AdminEntity;
import cn.edu.shu.xj.ser.entity.UserEntity;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

public interface ITokenService {

    public String getToken(AdminEntity adminEntity);

    public String getToken(UserEntity userEntity);



}
