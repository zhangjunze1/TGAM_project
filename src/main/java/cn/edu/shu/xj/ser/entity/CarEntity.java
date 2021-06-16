package cn.edu.shu.xj.ser.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("car")
public class CarEntity {
    @TableId
    private Long carId;
    private Long userId;
    private String carPlates;
    private String carPic;
    private String carStyle;
    private String carCity;
    private Double carLongitude;
    private Double carLatitude;
    private String carNow;

    @TableField(exist = false)
    private Integer cityCount;
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String userPhone;
    @TableField(exist = false)
    private String userAddress;
    @TableField(exist = false)
    private String carTude;
    @TableField(exist = false)
    private Integer userNow;
}
