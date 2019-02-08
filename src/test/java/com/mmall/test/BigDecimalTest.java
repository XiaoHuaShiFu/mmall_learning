package com.mmall.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by lenovo on 2019/2/4.
 */
public class BigDecimalTest {

    @Test
    public void test1() {
        BigDecimal b1 = new BigDecimal("0.1");
        BigDecimal b2 = new BigDecimal("0.5");
        System.out.println(b1.add(b2));
    }

}
