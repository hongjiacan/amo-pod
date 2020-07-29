package com.amoy.test;

import com.amoy.pod.support.utils.SequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

        String str = "123456789";
        log.info(str.substring(1));
    }

    @Test
    public void test02(){
        List<String> seqList = new ArrayList<>();
        for(int i = 1; i < 1000; i++){
            if(i<10){
                seqList.add("00"+i);
            }else if(i<100){
                seqList.add("0"+i);
            }else{
                seqList.add(String.valueOf(i));
            }
        }
        log.info(seqList.toString());
    }

    @Test
    public void test03(){

        int a = 17;
        int b = 16;

        log.info(Integer.toBinaryString(a)
                + " & " +
                Integer.toBinaryString(b)
                + " = " + String.valueOf(a&b));
    }
}
