package cn.edu.shu.xj.ser.mapper;

import cn.edu.shu.xj.ser.entity.CarEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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
}
