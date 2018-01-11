package org.galsang.util;

import java.util.Random;

/**
 * Description：随机数工具类
 * <br /> Author： galsang
 */
public class RandomUtil {


    /**
     * 获取指定范围的随机数
     * @param min 最小数
     * @param max 最大数
     * @return 范围内的随机数
     */
    public static int getRandomIntWithIn(int min, int max) {

        Random random = new Random();
        int randomInt = random.nextInt(max) % (max - min + 1) + min;
//        System.out.println(randomInt);
        return randomInt;
    }


}
