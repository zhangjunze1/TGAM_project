package cn.edu.shu.xj.ser.service.impl;

import cn.edu.shu.xj.ser.entity.AdminEntity;
import cn.edu.shu.xj.ser.mapper.AdminMapper;
import cn.edu.shu.xj.ser.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminService extends ServiceImpl<AdminMapper, AdminEntity> implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public AdminEntity findAdmin(String name) {
        return adminMapper.findAdmin(name);
    }
}
