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
}
