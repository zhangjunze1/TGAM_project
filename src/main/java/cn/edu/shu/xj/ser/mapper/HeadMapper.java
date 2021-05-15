package cn.edu.shu.xj.ser.mapper;

import cn.edu.shu.xj.ser.entity.HeadEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HeadMapper extends BaseMapper<HeadEntity> {

    Long getAllMyrecordTotal(Long userId);

    Long getAllMyrecordAllTime(Long userId);

    Long getUserRemindTimes(Long userId);

    HeadEntity getdataFromMachine0();

    HeadEntity getdataFromMachine1();

    void addData(Long userId,Long headAtt,Long headMed,Long headDelta,Long headTheta,Long headLowAlpha,
                 Long headHighAlpha,Long headLowBeta,Long headHighBeta,Long headLowGamma,Long headHighGamma
            ,String dataTime,Integer headStatus);

    HeadEntity getNewData(Long userId);
}
