package cn.edu.shu.xj.ser.service.impl;

import cn.edu.shu.xj.ser.entity.CarEntity;
import cn.edu.shu.xj.ser.mapper.CarMapper;
import cn.edu.shu.xj.ser.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarService extends ServiceImpl<CarMapper, CarEntity> implements ICarService {

    @Autowired
    CarMapper carMapper;

    @Override
    public List<CarEntity> findCarCityAndCount() {
        return carMapper.findCarCityAndCount();
    }

    @Override
    public Integer findCarListTotal(String carCity, String carPlates, String carStyle) {
        return carMapper.findCarListTotal(carCity, carPlates, carStyle);
    }

    @Override
    public List<CarEntity> queryCarList(String carCity, String carPlates, String carStyle, Integer Myvalue, Integer size) {
        return carMapper.queryCarList(carCity, carPlates, carStyle, Myvalue, size);
    }

    @Override
    public List<CarEntity> findStoppedCars() {
        return carMapper.findStoppedCars();
    }

    @Override
    public List<CarEntity> findStartedCars() {
        return carMapper.findStartedCars();
    }

    @Override
    public List<CarEntity> findUserCarsById(Long userId) {
        return carMapper.findUserCarsById(userId);
    }

    @Override
    public void addUserCar(Long userId, String carPlates, String carStyle, String carCity, String url,double userLongtitude,double userLatitude) {
        carMapper.addUserCar(userId, carPlates, carStyle, carCity, url,userLongtitude,userLatitude);
    }

    @Override
    public void addUserCarNoPic(Long userId, String carPlates, String carStyle, String carCity, double userLongtitude, double userLatitude) {
        carMapper.addUserCarNoPic(userId, carPlates, carStyle, carCity, userLongtitude, userLatitude);
    }

    @Override
    public void stopAllotherCars(Long userId) {
        carMapper.stopAllotherCars(userId);
    }

    @Override
    public void startMyUsedCar(Long userId, Long carId) {
        carMapper.startMyUsedCar(userId, carId);
    }

    @Override
    public void startCarByIndex(Long userId, Integer index) {
        carMapper.startCarByIndex(userId, index);
    }

    @Override
    public long ifStartedCarByUser(Long userId) {
        return carMapper.ifStartedCarByUser(userId);
    }

    @Override
    public long getStartedCarByUser(Long userId) {
        return carMapper.getStartedCarByUser(userId);
    }

    @Override
    public void editCarTitude(Long userId,Long carId,Double longitude, Double latitude) {
        carMapper.editCarTitude(userId,carId,longitude, latitude);
    }

    @Override
    public CarEntity getNowUserStartByid(Long userId) {
        return carMapper.getNowUserStartByid(userId);
    }

}
