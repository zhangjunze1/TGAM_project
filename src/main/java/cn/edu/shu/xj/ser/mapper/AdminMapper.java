package cn.edu.shu.xj.ser.mapper;

import cn.edu.shu.xj.ser.entity.AdminEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

@Component
public interface AdminMapper extends BaseMapper<AdminEntity> {

    AdminEntity findAdmin(String name);



}
