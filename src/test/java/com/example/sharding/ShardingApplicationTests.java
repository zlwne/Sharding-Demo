package com.example.sharding;

import com.example.sharding.mapper.LogDividendChangeMapper;
import com.example.sharding.model.LogDividendChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingApplicationTests {

    @Autowired
    private LogDividendChangeMapper logDividendChangeMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        Example example = new Example(LogDividendChange.class);
        example.createCriteria().andEqualTo("userInfoId",2L);
        List<LogDividendChange> list = logDividendChangeMapper.selectByExample(example);
        System.out.println(list.size());
    }

}
