package cn.edu.shu.xj.ser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.util.Objects;

@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @TableId
    private Long userId;
    private String userName;
    private String userPhone;
    private String userPwd;
    private String userCity;
    private String userAddress;
    private String userPicture;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp userTime;
    private Integer userRemind;
    private Double userLongitude;
    private Double userLatitude;
    private Integer userNow;

    @TableField(exist = false)
    private String times;
    @TableField(exist = false)
    private String alltimes;
    @TableField(exist = false)
    private Long reminded;
    @TableField(exist = false)
    private String carPlates;
    @TableField(exist = false)
    private String tiredSituation;
}
