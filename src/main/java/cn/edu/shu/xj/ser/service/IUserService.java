package cn.edu.shu.xj.ser.service;

import cn.edu.shu.xj.ser.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

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

    /**
     * 改变用户经纬度
     * @param userId
     * @param carId
     * @param longitude
     * @param latitude
     */
    void editUserTitude(Long userId,Long carId,Double longitude,Double latitude);

    /**
     * 获取顺序第i个大的Id
     * @param i
     * @return
     */
    Long getUserIdByI(Integer i);

    /**
     * 点击按钮对当前疲劳的用户进行警告
     */
    void warningAllTiredUser();

    /**
     * 点击按钮对所选择用户进行警告
     * @param userId
     */
    void warningtiredUserById(Long userId);
}
