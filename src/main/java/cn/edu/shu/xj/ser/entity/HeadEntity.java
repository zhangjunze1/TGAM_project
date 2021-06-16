package cn.edu.shu.xj.ser.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * form ZUCC_Zhangjz
 * @author 41205
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("head")
public class HeadEntity {
    @TableId
    private Long headId;
    private Long userId;
    private Long headAtt;
    private Long headMed;
    private Long headDelta;
    private Long headTheta;
    private Long headLowAlpha;
    private Long headHighAlpha;
    private Long headLowBeta;
    private Long headHighBeta;
    private Long headLowGamma;
    private Long headHighGamma;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp headTime;
    private Integer headStatus;

}
