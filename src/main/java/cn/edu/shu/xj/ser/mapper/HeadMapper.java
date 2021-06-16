package cn.edu.shu.xj.ser.mapper;

import cn.edu.shu.xj.ser.entity.HeadEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

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

    List<HeadEntity> getTenMinData(Long userId,String nowTime_10);
}
