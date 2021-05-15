package cn.edu.shu.xj.ser.controller.head;


import cn.edu.shu.xj.ser.entity.CarEntity;
import cn.edu.shu.xj.ser.entity.HeadEntity;
import cn.edu.shu.xj.ser.handler.BusinessException;
import cn.edu.shu.xj.ser.response.Result;
import cn.edu.shu.xj.ser.response.ResultCode;
import cn.edu.shu.xj.ser.service.ICarService;
import cn.edu.shu.xj.ser.service.IHeadService;
import cn.edu.shu.xj.ser.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "数据接口")
@RestController
@RequestMapping("/Head")
public class HeadContorller {

    @Autowired
    IHeadService headService;

    @Autowired
    IUserService userService;

    @Autowired
    ICarService carService;

    @ApiOperation(value = "获取历史疲劳记录")
    @GetMapping("/getAllMyrecord")
    public Result getAllMyrecord(@RequestParam(required = true)Long userId){
        // 疲劳的时间
        Long times = headService.getAllMyrecordTotal(userId);
        // 总时长
        Long alltimes = headService.getAllMyrecordAllTime(userId);
        // 被检测中心提醒的次数
        Long reminded = 5 * headService.getUserRemindTimes(userId);
        return  Result.ok().data("times",times).data("alltimes",alltimes).data("reminded",reminded);
    }

    @ApiOperation(value = "获取用户经纬度并将得到的数据返还")
    @PostMapping("/getTitudeAndReturn")
    public Result getTitudeAndReturn(@RequestParam(required = true)Long userId,
                                     @RequestParam(required = true)Double longitude,
                                     @RequestParam(required = true)Double latitude){
        //判定该用户是否驾车
        if(carService.ifStartedCarByUser(userId)==0){
            throw new BusinessException(ResultCode.NO_CAR_USER_USED.getCode(),
                    ResultCode.NO_CAR_USER_USED.getMessage());
        }
        //获取正在驾驶车辆的编号
        Long carId = carService.getStartedCarByUser(userId);
        // 修改用户和车辆所在的经纬度
        carService.editCarTitude(userId,carId,longitude,latitude);
        userService.editUserTitude(userId,carId,longitude,latitude);
        // 对当前的疲劳状态进行判定
        java.util.Random r=new java.util.Random();
        int state = r.nextInt(100);
        HeadEntity headEntity=null;
        if(state <= 4){
            headEntity = headService.getdataFromMachine0();
        }else {
            headEntity = headService.getdataFromMachine1();
        }
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataTime = sdf.format(dt);
        // 增加当前疲劳数据
        Long headAtt = headEntity.getHeadAtt();
        Long headMed = headEntity.getHeadMed();
        Long headDelta = headEntity.getHeadDelta();
        Long headTheta = headEntity.getHeadTheta();
        Long headLowAlpha = headEntity.getHeadLowAlpha();
        Long headHighAlpha =  headEntity.getHeadHighAlpha();
        Long headLowBeta = headEntity.getHeadLowBeta();
        Long headHighBeta = headEntity.getHeadHighBeta();
        Long headLowGamma = headEntity.getHeadLowGamma();
        Long headHighGamma = headEntity.getHeadHighGamma();
        Integer headStatus = headEntity.getHeadStatus();
        headService.addData(userId,headAtt,headMed,headDelta,headTheta,headLowAlpha,
                headHighAlpha,headLowBeta,headHighBeta,headLowGamma,headHighGamma
                ,dataTime,headStatus);
        HeadEntity newheadEntity = headService.getNewData(userId);
        return  Result.ok().data("data",newheadEntity);
    }

}
