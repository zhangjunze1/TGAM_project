package cn.edu.shu.xj.ser.config;


import cn.edu.shu.xj.ser.entity.CarEntity;
import cn.edu.shu.xj.ser.response.Result;
import cn.edu.shu.xj.ser.service.ICarService;
import cn.edu.shu.xj.ser.service.IUserService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */
@Api(tags = "文件上传")
@RestController
@Service
public class oss {
    String endpoint = "............";
    String accessKeyId = "............";
    String accessKeySecret = "............";
    String bucketName = "............";

    String subPath = "test/";
    String carPath = "car/";
    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    String picture_name = "";
    long business_id;
    long shangping_id;

    @Autowired
    ICarService carService;

    @Autowired
    IUserService userService;

    @ApiOperation(value = "上传文件测试", notes = "请记得上传时加上参数 file !")
    @PostMapping(value = "/oss/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PutObjectResult uploadTest(@RequestParam("file") MultipartFile file ) throws IOException {
        IdWorker.getIdStr();
        return ossClient.putObject(bucketName, subPath +"1231.jpg", file.getInputStream());
    }

    @ApiOperation(value = "上传商家相关信息")
    @GetMapping(value = "/oss/uploadFile/information")
    public boolean uploadTes11(@RequestParam String picture_name, long business_id ) {
        this.picture_name = picture_name;
        this.business_id = business_id;
        return true;
    }

    @ApiOperation(value = "上传商品相关信息")
    @PostMapping(value = "/oss/uploadFile/shangping_information")
    public boolean uploadTes111(@RequestParam String picture_name, long shangping_id ) {
        this.picture_name = picture_name;
        this.shangping_id = shangping_id;
        System.out.println(shangping_id);
        return true;
    }

//    @Autowired
//    Ibusiness_Serive ibusiness_serive;
//    @ApiOperation(value = "上传商家图片", notes = "请记得上传时加上参数 file !")
//    @PostMapping(value = "/oss/business/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public boolean uploadTest1(@RequestParam("file") MultipartFile file) throws IOException {
//
//        IdWorker.getIdStr();
//        ossClient.putObject(bucketName, subPath + picture_name, file.getInputStream());//上传服务器
//
//        Date expiration = new Date(System.currentTimeMillis() +3600*24*365);//时差2000S左右
//        String url = ossClient.generatePresignedUrl(bucketName, subPath + picture_name, expiration).toString();//从服务器获取图片url
//        OSSObject ossObject = ossClient.getObject(bucketName, subPath + picture_name);
//        ossObject.getObjectContent();
//
//        business_infomation business = new business_infomation();
//        UpdateWrapper<business_infomation> a =  new UpdateWrapper<business_infomation>();
//        business.setBusinessId(business_id);
//        business.setBusinessPic(url);
//
//        ibusiness_serive.update(business,a.eq("business_id",business.getBusinessId()));//将url上传到数据库
//        return true;
//    }

//    @Autowired
//    Ishangping_Serive ishangping_serive;
//    @ApiOperation(value = "上传商品图片", notes = "请记得上传时加上参数 file !")
//    @PostMapping(value = "/oss/shangping/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public boolean uploadTest2(@RequestParam("file") MultipartFile file ) throws IOException {
//        IdWorker.getIdStr();
//        ossClient.putObject(bucketName, subPath + picture_name, file.getInputStream());//上传服务器
//
//        Date expiration = new Date(System.currentTimeMillis() +3600*24*365);//时差2000S左右
//        String url = ossClient.generatePresignedUrl(bucketName, subPath + picture_name, expiration).toString();//从服务器获取图片url
//        OSSObject ossObject = ossClient.getObject(bucketName, subPath + picture_name);
//        ossObject.getObjectContent();
//
//        shangping_information shangping = new shangping_information();
//        UpdateWrapper<shangping_information> a =  new UpdateWrapper<shangping_information>();
//        shangping.setShangpingId(shangping_id);
//        shangping.setShangpingPic(url);
//        ishangping_serive.update(shangping,a.eq("shangping_id",shangping.getShangpingId()));//将url上传到数据库
//        return true;
//    }

    @ApiOperation(value = "获取添加用户车辆", notes = "请记得上传时加上参数 file !")
    @PostMapping(value = "/oss/addUserCars", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result addUserCars(@RequestParam(required = true)Long userId,
                          @RequestParam(required = true)String carPlates,
                          @RequestParam(required = true)String carStyle,
                          @RequestParam(required = true)String carCity,
                          @RequestParam("file") MultipartFile file) throws IOException {
        this.picture_name = userId+carPlates+".jpg";
        ossClient.putObject(bucketName, carPath + picture_name, file.getInputStream());//上传服务器
        Date expiration = new Date(System.currentTimeMillis() +3600*24*365);//时差2000S左右
        String url = ossClient.generatePresignedUrl(bucketName, carPath + picture_name, expiration).toString();//从服务器获取图片url
        System.out.println(url);
        OSSObject ossObject = ossClient.getObject(bucketName, carPath + picture_name);
        ossObject.getObjectContent();

        double userLongtitude = userService.findUserById(userId).getUserLongitude();
        double userLatitude = userService.findUserById(userId).getUserLatitude();
        carService.addUserCar(userId,carPlates,carStyle,carCity,url,userLongtitude,userLatitude);
        return Result.ok();
    }

    @PostMapping(value = "/oss/downloadFile")
    public String downloadTest(@RequestParam("fileName") String fileName) {

        Date expiration = new Date(System.currentTimeMillis() +3600*24*365);//时差2000S左右
        String url = ossClient.generatePresignedUrl(bucketName, subPath + fileName, expiration).toString();
        OSSObject ossObject = ossClient.getObject(bucketName, subPath + fileName);
        ossObject.getObjectContent();
        return url;
    }
/*try {

	$ossClient = new OssClient($accessKeyId, $accessKeySecret, $endpoint);

	// 生成一个带签名的URL，有效期是3600秒，可以直接使用浏览器访问。
	$timeout = 3600;

	// $options 可以参考https://www.alibabacloud.com/help/zh/doc-detail/47735.htm?spm=a2c63.p38356.b99.530.2b124f7cdGTn1g
	// $options = array(
	//     OssClient::OSS_FILE_DOWNLOAD => $download_file,
	//     OssClient::OSS_PROCESS => "image/resize,m_fixed,h_100,w_100",
	// );

	$signedUrl = $ossClient->signUrl($bucket, $object, $timeout, "GET", $options);

	print("rtmp url: \n" . $signedUrl);
} catch (OssException $e) {
	print $e->getMessage();
}*/
// 获取文件内容
/*try {
	$ossClient = new OssClient($accessKeyId, $accessKeySecret, $endpoint);
	$content = $ossClient->getObject($bucket, $object);
    print("object content: " . $content);
} catch (OssException $e) {
	print $e->getMessage();
}*/
}
