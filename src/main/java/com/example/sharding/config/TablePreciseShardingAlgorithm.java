package com.example.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 表分片算法。
 *
 * Created by wzl on 2019/8/31 17:47.
 */
public class TablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
		String logicTableName = shardingValue.getLogicTableName();
		long userInfoId = shardingValue.getValue();
		//分隔符
		String tableExt = "_";
		if (logicTableName.equals("log_user_wallet_change")) {
			tableExt += userInfoId % 20;
		} else if (
				logicTableName.equals("log_dividend_change")) {
			tableExt += userInfoId % 128;
		}
		for (String availableTableName : availableTargetNames) {
			if (availableTableName.endsWith(tableExt)) {
				return availableTableName;
			}
		}
		throw new UnsupportedOperationException();
	}
}
