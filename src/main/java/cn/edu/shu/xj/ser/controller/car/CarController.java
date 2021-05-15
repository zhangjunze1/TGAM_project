package cn.edu.shu.xj.ser.controller.car;

import cn.edu.shu.xj.ser.entity.CarEntity;
import cn.edu.shu.xj.ser.handler.BusinessException;
import cn.edu.shu.xj.ser.response.Result;
import cn.edu.shu.xj.ser.response.ResultCode;
import cn.edu.shu.xj.ser.service.ICarService;
import cn.edu.shu.xj.ser.service.IUserService;
import com.aliyun.oss.model.OSSObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Api(tags = "车辆接口")
@RestController
@RequestMapping("/Car")
public class CarController {

    @Autowired
    ICarService carService;

    @Autowired
    IUserService userService;

    @ApiOperation(value = "返回车子对应的城市与对应数量")
    @GetMapping("/findCarCityAndCount")
    public Result findCarCityAndCount(){
        List<CarEntity> carCityAndCount = carService.findCarCityAndCount();
        if(carCityAndCount.size()==0){
            throw new BusinessException(ResultCode.NO_CAR_CITY.getCode(),
                    ResultCode.NO_CAR_CITY.getMessage());
        }
        return  Result.ok().data("carCityAndCount",carCityAndCount);
    }

    @ApiOperation(value = "条件查询分页查询车辆列表")
    @GetMapping("/queryCarList")
    public Result queryCarList(@RequestParam(required = true,defaultValue = "1")Integer current,
                                @RequestParam(required = true,defaultValue = "10")Integer size,
                                @RequestParam(required = true)String carCity,
                                @RequestParam(required = true)String carPlates,
                                @RequestParam(required = true)String carStyle){
        //对车辆进行分页，泛型中注入的就是车辆实体类
        Integer Total = carService.findCarListTotal(carCity,carPlates,carStyle);
        Integer Myvalue = (current-1)*size;
        List<CarEntity> carList = carService.queryCarList(carCity,carPlates,carStyle,Myvalue,size);
        return Result.ok().data("total",Total).data("records",carList);
    }

    @ApiOperation(value = "查询当前未使用的车辆")
    @GetMapping("/findStoppedCars")
    public Result findStoppedCars(){
        List<CarEntity> stoppedCarList = carService.findStoppedCars();
        Integer Account = stoppedCarList.size();
        int i=0;
        while(!stoppedCarList.isEmpty() && i !=Account ){
            stoppedCarList.get(i).setCarTude(stoppedCarList.get(i).getCarLongitude()  +","+ stoppedCarList.get(i).getCarLatitude() );
            i++;
        }
        return Result.ok().data("Account",Account).data("stoppedCarList",stoppedCarList);
    }

    @ApiOperation(value = "查询当前正在使用的车辆")
    @GetMapping("/findStartedCars")
    public Result findStartedCars(){
        List<CarEntity> startedCarList = carService.findStartedCars();
        Integer Account = startedCarList.size();
        int i=0;
        while(!startedCarList.isEmpty() && i !=Account ){
            startedCarList.get(i).setCarTude( startedCarList.get(i).getCarLongitude()  +","+ startedCarList.get(i).getCarLatitude()  );
            i++;
        }
        return Result.ok().data("Account",Account).data("startedCarList",startedCarList);
    }

    @ApiOperation(value = "获取对应用户所有车辆列表接口")
    @PostMapping("/findUserCarsById")
    public Result findUserCarsById(@RequestParam(required = true)Long userId){
        List<CarEntity> Carslist = carService.findUserCarsById(userId);
        return Result.ok().data("Carslist",Carslist);
    }

    @ApiOperation(value = "获取添加用户车辆")
    @PostMapping(value = "/addUserCars")
    public Result addUserCars(@RequestParam(required = true)Long userId,
                              @RequestParam(required = true)String carPlates,
                              @RequestParam(required = true)String carStyle,
                              @RequestParam(required = true)String carCity) throws IOException {

        double userLongtitude = userService.findUserById(userId).getUserLongitude();
        double userLatitude = userService.findUserById(userId).getUserLatitude();
        carService.addUserCarNoPic(userId,carPlates,carStyle,carCity,userLongtitude,userLatitude);
        return Result.ok();
    }

    @ApiOperation(value = "驾驶汽车")
    @PostMapping(value = "/startCar")
    public Result addUserCars(@RequestParam(required = true)Long userId,
                              @RequestParam(required = true)Long carId) throws IOException {
        // 停用该用户所有车辆使用
        carService.stopAllotherCars(userId);
        // 启用对应车辆 进行用户车辆同步
        carService.startMyUsedCar(userId,carId);
        return Result.ok();
    }

    @ApiOperation(value = "驾驶汽车通过编号")
    @PostMapping(value = "/startCarByIndex")
    public Result startCarByIndex(@RequestParam(required = true)Long userId,
                              @RequestParam(required = true)Integer index) throws IOException {
        // 停用该用户所有车辆使用
        carService.stopAllotherCars(userId);
        // 启用对应车辆 进行用户车辆同步
        carService.startCarByIndex(userId,index);
        CarEntity carEntity = carService.getNowUserStartByid(userId);
        return Result.ok().data("car",carEntity);
    }

}
