package cn.edu.shu.xj.ser.service.impl;

import cn.edu.shu.xj.ser.entity.HeadEntity;
import cn.edu.shu.xj.ser.mapper.HeadMapper;
import cn.edu.shu.xj.ser.service.IHeadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("headService")
public class HeadService extends ServiceImpl<HeadMapper, HeadEntity> implements IHeadService {

    @Autowired
    HeadMapper headMapper;

    @Override
    public Long getAllMyrecordTotal(Long userId) {
        return headMapper.getAllMyrecordTotal(userId);
    }

    @Override
    public Long getUserRemindTimes(Long userId) {
        return headMapper.getUserRemindTimes(userId);
    }

    @Override
    public Long getAllMyrecordAllTime(Long userId) {
        return headMapper.getAllMyrecordAllTime(userId);
    }

    @Override
    public HeadEntity getdataFromMachine0() {
        return headMapper.getdataFromMachine0();
    }

    @Override
    public HeadEntity getdataFromMachine1() {
        return headMapper.getdataFromMachine1();
    }

    @Override
    public void addData(Long userId, Long headAtt, Long headMed, Long headDelta, Long headTheta, Long headLowAlpha, Long headHighAlpha, Long headLowBeta, Long headHighBeta, Long headLowGamma, Long headHighGamma, String dataTime, Integer headStatus) {
        headMapper.addData(userId, headAtt, headMed, headDelta, headTheta, headLowAlpha, headHighAlpha, headLowBeta, headHighBeta, headLowGamma, headHighGamma, dataTime, headStatus);
    }

    @Override
    public HeadEntity getNewData(Long userId) {
        return headMapper.getNewData(userId);
    }



}
