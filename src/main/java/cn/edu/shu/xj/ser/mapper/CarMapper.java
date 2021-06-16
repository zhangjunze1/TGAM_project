package cn.edu.shu.xj.ser.mapper;

import cn.edu.shu.xj.ser.entity.CarEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

@Component
public interface CarMapper extends BaseMapper<CarEntity> {

    List<CarEntity> findCarCityAndCount();

    Integer findCarListTotal(String carCity,String carPlates,String carStyle);

    List<CarEntity> queryCarList(String carCity,String carPlates,String carStyle, Integer Myvalue,Integer size);

    List<CarEntity> findStoppedCars();

    List<CarEntity> findStartedCars();

    List<CarEntity> findUserCarsById(Long userId);

    void addUserCar(Long userId,String carPlates,String carStyle,String carCity,String url,double userLongtitude,double userLatitude);

    void addUserCarNoPic(Long userId,String carPlates,String carStyle,String carCity,double userLongtitude,double userLatitude);

    void stopAllotherCars(Long userId);

    void startMyUsedCar(Long userId,Long carId);

    void startCarByIndex(Long userId,Integer index);

    long ifStartedCarByUser(Long userId);

    long getStartedCarByUser(Long userId);

    void editCarTitude(Long userId,Long carId,Double longitude,Double latitude);

    CarEntity getNowUserStartByid(Long userId);
}
