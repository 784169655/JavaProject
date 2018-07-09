package com.wxdc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by  邱伟
 * 2018/4/3 11:26
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test () {

        String name = "wxdc";
        String pwd = "123";

        log.info("name: {}, password: {}", name, pwd);

        log.info("info...");
        log.debug("debug...");
        log.error("error...");
    }
}