package cn.edu.shu.xj.ser.service;

import cn.edu.shu.xj.ser.entity.CarEntity;
import cn.edu.shu.xj.ser.service.impl.CarService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ICarService  extends IService<CarEntity> {

    /**
     * 查询车辆 对应城市及数量
     * @return
     */
    List<CarEntity> findCarCityAndCount();

    /**
     * 查询满足条件的车辆数目
     * @param carCity
     * @param carPlates
     * @param carStyle
     * @return
     */
    Integer findCarListTotal(String carCity,String carPlates,String carStyle);

    /**
     * 条件分页查询 车辆的信息
     * @param carCity
     * @param carPlates
     * @param carStyle
     * @param Myvalue
     * @param size
     * @return
     */
    List<CarEntity> queryCarList(String carCity,String carPlates,String carStyle, Integer Myvalue,Integer size);

    /**
     * 查询所有未启动车辆的信息
     * @return
     */
    List<CarEntity> findStoppedCars();

    /**
     * 查询所有正在使用车辆的信息
     * @return
     */
    List<CarEntity> findStartedCars();

    /**
     * 获取个人所有车辆列表接口
     * @param userId
     * @return
     */
    List<CarEntity> findUserCarsById(Long userId);

    /**
     * 添加用户车辆
     * @param userId
     * @param carPlates
     * @param carStyle
     * @param carCity
     * @param url
     * @param userLongtitude
     * @param userLatitude
     */
    void addUserCar(Long userId,String carPlates,String carStyle,String carCity,String url,double userLongtitude,double userLatitude);

    /**
     * 无照片上传 添加用户车辆
     * @param userId
     * @param carPlates
     * @param carStyle
     * @param carCity
     * @param userLongtitude
     * @param userLatitude
     */
    void addUserCarNoPic(Long userId,String carPlates,String carStyle,String carCity,double userLongtitude,double userLatitude);

    /**
     * 停止该用户的车辆，使车辆状态变为未使用
     * @param userId
     */
    void stopAllotherCars(Long userId);

    /**
     * 启用对应车辆 进行用户车辆同步
     * @param userId
     * @param carId
     */
    void startMyUsedCar(Long userId,Long carId);

    /**
     * 启用编号对应车辆 进行用户车辆同步
     * @param userId
     * @param index
     */
    void startCarByIndex(Long userId,Integer index);

    /**
     * 该用户是否驾驶车辆
     * @param userId
     * @return
     */
    long ifStartedCarByUser(Long userId);

    /**
     * 该用户驾驶车辆的id
     * @param userId
     * @return
     */
    long getStartedCarByUser(Long userId);

    /**
     * 修改汽车的经纬度
     * @param userId
     * @param carId
     * @param longitude
     * @param latitude
     */
    void editCarTitude(Long userId,Long carId,Double longitude,Double latitude);

    /**
     * 当前正在驾驶的车
     * @param userId
     * @return
     */
    CarEntity getNowUserStartByid(Long userId);
}
