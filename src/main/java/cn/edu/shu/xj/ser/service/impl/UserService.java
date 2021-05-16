package cn.edu.shu.xj.ser.service.impl;

import cn.edu.shu.xj.ser.entity.UserEntity;
import cn.edu.shu.xj.ser.mapper.UserMapper;
import cn.edu.shu.xj.ser.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserEntity findUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public List<UserEntity> findUserListTotal() {
        return userMapper.findUserListTotal();
    }

    @Override
    public List<UserEntity> findUserList(Integer Myvalue, Integer size) {
        return userMapper.findUserList(Myvalue, size);
    }

    @Override
    public UserEntity getUserById(Long userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public void editUser(Long userId, String userCity, String userAddress) {
        userMapper.editUser(userId, userCity, userAddress);
    }

    @Override
    public void deleteUser(Long userId) {
        userMapper.deleteUser(userId);
    }

    @Override
    public UserEntity findUserByPhone(String phone) {
        return userMapper.findUserByPhone(phone);
    }

    @Override
    public void editUserTitude(Long userId, Long carId, Double longitude, Double latitude) {
        userMapper.editUserTitude(userId, carId, longitude, latitude);
    }

    @Override
    public Long getUserIdByI(Integer i) {
        return userMapper.getUserIdByI(i);
    }


}
