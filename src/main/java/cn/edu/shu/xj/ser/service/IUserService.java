package cn.edu.shu.xj.ser.service;

import cn.edu.shu.xj.ser.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserService extends IService<UserEntity> {

    /**
     * 根据UserId查找User实体类
     * @param userId
     * @return
     */
    UserEntity findUserById(Long userId);

    /**
     * 查询所有的用户司机
     * @return
     */
    List<UserEntity> findUserListTotal();

    /**
     * 分页查询对应用户司机
     * @param Myvalue
     * @param size
     * @return
     */
    List<UserEntity> findUserList(Integer Myvalue,Integer size);

    /**
     * 获取对应UserId的用户司机信息
     * @param userId
     * @return
     */
    UserEntity getUserById(Long userId);

    /**
     * 修改用户信息数据
     * @param userId
     * @param userCity
     * @param userAddress
     */
    void editUser(Long userId,String userCity,String userAddress);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * 通过电话号码查找用户实体
     * @param phone
     * @return
     */
    UserEntity findUserByPhone(String phone);
}
