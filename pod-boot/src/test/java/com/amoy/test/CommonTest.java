package com.amoy.test;

import com.amoy.pod.support.utils.SequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/11
 */
@Slf4j
public class CommonTest {

    @Test
    public void test01(){

        String redisKey = String.format("jwt-%d-%d", SequenceUtil.nextId(), "wulvping".hashCode());
        log.info(redisKey);
    }
}
