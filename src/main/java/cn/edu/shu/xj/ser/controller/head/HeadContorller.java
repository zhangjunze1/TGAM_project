package cn.edu.shu.xj.ser.controller.head;


import cn.edu.shu.xj.ser.service.IHeadService;
import cn.edu.shu.xj.ser.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "数据接口")
@RestController
@RequestMapping("/Head")
public class HeadContorller {

    @Autowired
    IHeadService headService;

}
