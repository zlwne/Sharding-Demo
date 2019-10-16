package com.example.sharding.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author guan
 * 分红钱包变动记录表
 */
@Data
@Table(name = "log_dividend_change")
@Builder
public class LogDividendChange implements Serializable {
    private static final long serialVersionUID = -2229146246428112442L;

    @Tolerate
    public LogDividendChange(){

    }

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_info_id")
    private Long userInfoId;

    /**
     * 类型(1-矿池释放;2-签到;3-交易卖出;4-交易手续费)
     */
    private String type;

    /**
     * 变动数量
     */
    private BigDecimal amount;

    /**
     * 变动之后的余额
     */
    @Column(name = "after_balance")
    private BigDecimal afterBalance;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}