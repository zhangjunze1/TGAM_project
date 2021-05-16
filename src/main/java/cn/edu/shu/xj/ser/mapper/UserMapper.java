package cn.edu.shu.xj.ser.mapper;

import cn.edu.shu.xj.ser.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<UserEntity>{

    UserEntity findUserById(Long userId);

    List<UserEntity> findUserListTotal();

    List<UserEntity> findUserList(Integer Myvalue,Integer size);

    UserEntity getUserById(Long userId);

    void editUser(Long userId,String userCity,String userAddress);

    void deleteUser(Long userId);

    UserEntity findUserByPhone(String phone);

    void editUserTitude(Long userId,Long carId,Double longitude,Double latitude);

    Long getUserIdByI(Integer i);

    void warningAllTiredUser();

    void warningtiredUserById(Long userId);

}
