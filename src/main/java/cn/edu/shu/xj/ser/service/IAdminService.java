package cn.edu.shu.xj.ser.service;

import cn.edu.shu.xj.ser.entity.AdminEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

public interface IAdminService extends IService<AdminEntity> {

    /**
     * 查找对应管理员
     * @param name
     * @return
     */
    AdminEntity findAdmin(String name);


}
