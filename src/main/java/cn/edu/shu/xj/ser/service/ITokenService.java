package cn.edu.shu.xj.ser.service;


import cn.edu.shu.xj.ser.entity.AdminEntity;
import cn.edu.shu.xj.ser.entity.UserEntity;

public interface ITokenService {

    public String getToken(AdminEntity adminEntity);

    public String getToken(UserEntity userEntity);



}
