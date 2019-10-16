package com.example.sharding.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "log_user_wallet_change")
@Builder
public class LogUserWalletChange {

    @Tolerate
    public LogUserWalletChange(){

    }

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_info_id")
    private Long userInfoId;

    /**
     * 虚拟货币ID
     */
    @Column(name = "virtual_coin_id")
    private Long virtualCoinId;

    /**
     * 变动金额
     */
    private BigDecimal amount;

    /**
     * 变动之后金额
     */
    @Column(name = "after_balance")
    private BigDecimal afterBalance;

    /**
     * 冻结金额
     */
    @Column(name = "frozen_amount")
    private BigDecimal frozenAmount;

    /**
     * 手续费
     */
    @Column(name = "fee")
    private BigDecimal fee;

    /**
     * 总冻结金额
     */
    @Column(name = "total_frozen_amount")
    private BigDecimal totalFrozenAmount;

    /**
     * 转出地址
     */
    @Column(name = "from_address")
    private String fromAddress;

    /**
     * 转入地址
     */
    @Column(name = "to_address")
    private String toAddress;

    /**
     * 备注
     */
    private String remark;

    /**
     * 变动类型(1-充值;2-提现;3-转入静态资产;4-注册奖励;5-推广奖励;6-购买GCNT消耗;7-卖出GCNT收入;8-划转增加;9-划转减少)
     */
    private String type;

    /**
     * 状态（0-审核中，1-处理中，2-已完成，3-失败）不可以加默认值
     */
    private Integer status;

    /**
     * 交易哈希ID
     */
    @Column(name = "tx_id")
    private String txId;

    /**
     * 交易在公链Transaction的Index
     */
    @Column(name = "vout_index")
    private Integer voutIndex;

    /**
     * 充值、提现任务id
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 广告id
     */
    @Column(name = "advertisement_id")
    private Long advertisementId;
    /**
     * 钱包金额变动时间
     */
    @Column(name = "create_time")
    private Date createTime;
}