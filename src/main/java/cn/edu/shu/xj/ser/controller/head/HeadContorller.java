package cn.edu.shu.xj.ser.controller.head;


import cn.edu.shu.xj.ser.entity.CarEntity;
import cn.edu.shu.xj.ser.entity.HeadEntity;
import cn.edu.shu.xj.ser.entity.UserEntity;
import cn.edu.shu.xj.ser.handler.BusinessException;
import cn.edu.shu.xj.ser.response.Result;
import cn.edu.shu.xj.ser.response.ResultCode;
import cn.edu.shu.xj.ser.service.ICarService;
import cn.edu.shu.xj.ser.service.IHeadService;
import cn.edu.shu.xj.ser.service.IUserService;
import cn.edu.shu.xj.ser.service.impl.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Long alltimes = 5 * headService.getAllMyrecordAllTime(userId);
        // 被检测中心提醒的次数
        Long reminded = headService.getUserRemindTimes(userId);
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

        // 获取用户对应的提醒次数 已方便app用户端接收 比对 进行警报
        UserEntity nowUser = userService.getUserById(userId);
        Integer remindTimes = nowUser.getUserRemind();
        return  Result.ok().data("data",newheadEntity).data("remind",remindTimes);
    }



    @ApiOperation(value = "所有人历史疲劳记录展示")
    @GetMapping("/getAllUsersRecord")
    public Result getAllUsersRecord(@RequestParam(required = true,defaultValue = "1")Integer current,
                                    @RequestParam(required = true,defaultValue = "10")Integer size){
        Integer Myvalue = (current-1)*size;
        Integer UserNum = userService.count();
        Integer i = Myvalue+1;
        List<UserEntity> allRecord = new ArrayList<UserEntity>();
        while((i!=Myvalue+size+1)&&(i<=UserNum)){
            // 通过i的大小按照顺序找到从小到大排列的UserId
            Long userId = userService.getUserIdByI(i);
            // 疲劳的时间
            Long times = headService.getAllMyrecordTotal(userId);
            // 总时长
            Long alltimes = 5 * headService.getAllMyrecordAllTime(userId);
            // 被检测中心提醒的次数
            Long reminded =  headService.getUserRemindTimes(userId);

            //数组换算成YYYY-MM-SS形式
            String Stringtimes="";
            if (times>=3600){
                Stringtimes = times/3600+"时";
                times= times%3600;
            }else {
                Stringtimes = Stringtimes+"0时";
            }
            if (times>=60){
                Stringtimes = times/60 +"分";
                times= times%60;
            }else {
                Stringtimes = Stringtimes+"0分";
            }
            Stringtimes = Stringtimes+times+"秒";


            String Stringalltimes="";
            if (alltimes>=3600){
                Stringalltimes = alltimes/3600+"时";
                alltimes= alltimes%3600;
            }else {
                Stringalltimes = Stringalltimes+"0时";
            }
            if (alltimes>=60){
                Stringalltimes = alltimes/60 +"分";
                alltimes= alltimes%60;
            }else {
                Stringalltimes = Stringtimes+"0分";
            }
            Stringalltimes = Stringalltimes+alltimes+"秒";


            UserEntity userEntity = userService.getUserById(userId);
            userEntity.setTimes(Stringtimes);
            userEntity.setAlltimes(Stringalltimes);
            userEntity.setReminded(reminded);
            allRecord.add(userEntity);
            i++;
        }
        return  Result.ok().data("num", UserNum).data("UserAll",allRecord);
    }

    @ApiOperation(value = "获取所有人历史疲劳记录表格展示")
    @GetMapping("/findUserTired")
    public Result findUserTired(@RequestParam(required = true,defaultValue = "1")Integer current,
                                @RequestParam(required = true,defaultValue = "10")Integer size){
        Integer Myvalue = (current-1)*size;
        Integer UserNum = userService.count();
        Integer i = Myvalue+1;
        List<UserEntity> allRecord = new ArrayList<UserEntity>();
        while((i!=Myvalue+size+1)&&(i<=UserNum)){
            // 通过i的大小按照顺序找到从小到大排列的UserId
            Long userId = userService.getUserIdByI(i);
            // 疲劳的时间
            Long times = headService.getAllMyrecordTotal(userId);
            // 总时长
            Long alltimes = 5 * headService.getAllMyrecordAllTime(userId);
            // 被检测中心提醒的次数
            Long reminded =  headService.getUserRemindTimes(userId);
            // 本用户的user实体
            UserEntity userEntity = userService.getUserById(userId);
            // CarId 是当前驾驶车车辆的编号
            CarEntity UserCar= carService.getNowUserStartByid(userId);
            String carPlates = "";
            if (UserCar!=null){
                // 当前驾驶车牌
                carPlates = UserCar.getCarPlates();
            }else {
                carPlates ="该用户当前未驾驶车辆";
            }


            // 十分钟（600s）内疲劳情况
            String tiredSituation="";
            //

            String Stringtimes="";
            if (times>=3600){
                Stringtimes = times/3600+"时";
                times= times%3600;
            }else {
                Stringtimes = Stringtimes+"0时";
            }
            if (times>=60){
                Stringtimes = Stringtimes+times/60 +"分";
                times= times%60;
            }else {
                Stringtimes = Stringtimes+"0分";
            }
            Stringtimes = Stringtimes+times+"秒";


            String Stringalltimes="";
            if (alltimes>=3600){
                Stringalltimes = Stringalltimes+alltimes/3600+"时";
                alltimes= alltimes%3600;
            }else {
                Stringalltimes = Stringalltimes+"0时";
            }
            if (alltimes>=60){
                Stringalltimes = Stringalltimes+alltimes/60 +"分";
                alltimes= alltimes%60;
            }else {
                Stringalltimes = Stringalltimes+"0分";
            }
            Stringalltimes = Stringalltimes+alltimes+"秒";


            userEntity.setTimes(Stringtimes);
            userEntity.setAlltimes(Stringalltimes);
            userEntity.setReminded(reminded);
            userEntity.setCarPlates(carPlates);


            java.util.Date now = new java.util.Date();
            java.util.Date now_10 = new java.util.Date(now.getTime() - 600000); //10分钟前的时间
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
            String nowTime_10 = dateFormat.format(now_10);

            // 获取十分钟内的所有数据
            List<HeadEntity> headEntities = headService.getTenMinData(userId,nowTime_10);
            // lenth=几条数据
            Integer lenth = 0;
            lenth = headEntities.size();
            // tiredNum = 几条数据是疲劳的
            Integer tiredNum = 0;
            // nottiredNum = 几条数据是不疲劳的
            Integer nottiredNum = 0;
            int temp=0;

            while (temp!=lenth){
                // 如果数据等于1 则为不疲劳数据
                if (headEntities.get(temp).getHeadStatus()==1){
                    nottiredNum++;
                }else {
                    tiredNum++;
                }

                temp++;
            }
            tiredSituation = "十分钟内驾驶时长"+lenth*5+"秒，疲劳时间"+tiredNum*5+"秒，正常时间"+nottiredNum*5+"秒。";
            userEntity.setTiredSituation(tiredSituation);

            userEntity.setCarPlates(carPlates);
            allRecord.add(userEntity);
            i++;
        }
        return  Result.ok().data("total", UserNum).data("UserAll",allRecord);
    }



}
