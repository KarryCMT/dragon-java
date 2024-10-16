package com.dragon;

import com.dragon.client.UserFeignClient;
import com.dragon.vo.user.RbMember;
import com.google.common.collect.Lists;
import com.monkey.common.bean.ResponseBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserFeignClient userFeignClient;

    @Test
    public void contextLoads() {

        ResponseBean<Map<String, RbMember>> memberByIds = userFeignClient.getMemberByIds(Lists.newArrayList(1L));
        System.out.println("用户数据：start");
        System.out.println(memberByIds);
        System.out.println("用户数据：end");
    }
}
