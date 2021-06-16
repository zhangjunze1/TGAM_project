package cn.edu.shu.xj.ser.service.impl;

import cn.edu.shu.xj.ser.entity.AdminEntity;
import cn.edu.shu.xj.ser.entity.UserEntity;
import cn.edu.shu.xj.ser.service.ITokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

/**
 * token的生成方法
 * Algorithm.HMAC256():使用HS256生成token,密钥则是商家的密码，唯一密钥的话可以保存在服务端。
 * withAudience()存入需要保存在token的信息，这里我把商家ID存入token中
 * form ZUCC_Zhangjz
 * @author 41205
 */
@Service
public class TokenService implements ITokenService {

    public String getToken(AdminEntity adminEntity) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(adminEntity.getAdminId()))
                .sign(Algorithm.HMAC256(adminEntity.getAdminPwd()));
        return token;
    }

    public String getToken(UserEntity userEntity) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(userEntity.getUserId()))
                .sign(Algorithm.HMAC256(userEntity.getUserPwd()));
        return token;
    }

}
