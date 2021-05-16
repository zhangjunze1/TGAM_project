package cn.edu.shu.xj.ser.service;

import cn.edu.shu.xj.ser.entity.HeadEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IHeadService extends IService<HeadEntity> {

    /**
     * 疲劳驾驶的次数
     * @param userId
     * @return
     */
    Long getAllMyrecordTotal(Long userId);

    /**
     * 被监测中心提醒的次数
     * @param userId
     * @return
     */
    Long getUserRemindTimes(Long userId);

    /**
     * 总驾驶时长
     * @param userId
     * @return
     */
    Long getAllMyrecordAllTime(Long userId);

    /**
     * 获取本次疲劳驾驶数据
     * @return
     */
    HeadEntity getdataFromMachine0();

    /**
     * 获取疲劳驾驶数据
     * @return
     */
    HeadEntity getdataFromMachine1();

    /**
     * 疲劳驾驶数据增加
     * @param userId
     * @param headAtt
     * @param headMed
     * @param headDelta
     * @param headTheta
     * @param headLowAlpha
     * @param headHighAlpha
     * @param headLowBeta
     * @param headHighBeta
     * @param headLowGamma
     * @param headHighGamma
     * @param dataTime
     * @param headStatus
     */
    void addData(Long userId,Long headAtt,Long headMed,Long headDelta,Long headTheta,Long headLowAlpha,
                 Long headHighAlpha,Long headLowBeta,Long headHighBeta,Long headLowGamma,Long headHighGamma
            ,String dataTime,Integer headStatus);

    /**
     * 疲劳数据
     * @param userId
     * @return
     */
    HeadEntity getNewData(Long userId);

    /**
     * 十分钟（600s）内疲劳情况
     * @param userId
     * @param nowTime_10
     * @return
     */
    List<HeadEntity> getTenMinData(Long userId ,String nowTime_10);
}
