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

}
