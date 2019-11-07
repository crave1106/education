package com.crave.edu.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderGenerateUtil {
    private int size;
    private int length;
    private List<String> orderNoList;
    private Set<String> orderNoSet;

    /**
     * 订单号生成器
     * <p>
     * 为了保证生成性能需满足条件size<10^length/4
     *
     * @param size   保证连续不重读的数
     * @param length 生成随机数的长度
     */
    public OrderGenerateUtil(int size, int length) {
        this.size = size;
        this.length = length;
        AssertUtils.requireTrue(size < Math.pow(10, length) / 4, "参数不符合要求");
        orderNoList = new ArrayList<>();
        orderNoSet = new HashSet<>();
    }

    /**
     * 获取不重复的随机数
     *
     * @return
     */
    public synchronized String generatorOrderNo() {
        String randomNumber = RandomUtils.randomNumber(length);
        while (orderNoSet.contains(randomNumber)) {
            randomNumber = RandomUtils.randomNumber(length);
        }
        orderNoList.add(randomNumber);
        orderNoSet.add(randomNumber);
        reduce();
        return randomNumber;
    }

    private void reduce() {
        if (orderNoList.size() >= size * 2) {
            List<String> removes = orderNoList.subList(0, size);
            orderNoSet.removeAll(removes);
            removes.clear();
        }
    }

    private static int counts = 0;
    private static Object lock = 0;
    public static void main(String[] args) {
        //订单号格式为 yyyyMMddhhmmss+3位随机数
        //假设连续生成200单
        OrderGenerateUtil generator = new OrderGenerateUtil(1, 10);
        for (int i=0 ;i<10000000;i++){
            generator.generatorOrderNo();
        }
    }
}
