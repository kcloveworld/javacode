package com.itheima.util;

import java.util.Random;

public class CodeUtil {

   /* public static void main(String[] args) {
        System.out.println(getCode());
    }*/
    //私有化构造方法，为了不让外界创建它的对象
    private CodeUtil() {
    }


    //5位数验证码（4个字符，1个数字）
    public static String getCode() {
        //创建a-z 和A-Z字符数组
        char[] chs = new char[52];
        //创建数字数组
        char[] numberArr = new char[10];

        //遍历字符数组chs，填充chs中的小写字符和大写字符
        for (int i = 0; i < chs.length; i++) {
            if (i < 26) {
                chs[i] = (char) (i + 97);
            } else {
                chs[i] = (char) (i + 65 - 26);
            }
        }
        //填充数字数组numberArr
        for (int i = 0; i < numberArr.length; i++) {
            numberArr[i] = (char) (i + 48);
        }
        //创建空数组arr
        char[] arr = new char[5];
        //随机4个字符
        Random r = new Random();
        //创建一个布尔数组used来标记每个字符是否被使用过
        boolean[] used = new boolean[chs.length];
        int count = 0 ;
        while (count < 4){
            int index = r.nextInt(chs.length);
            //当used[index]为false时，表示字符数组chs中索引为index的字符尚未被选取
            if (!used[index]){
                arr[count] = chs[index];
                //标记为已使用
                used[index] = true;
                count++;
            }

        }
        //随机一个数字字符
        int index = r.nextInt(numberArr.length);
        arr[arr.length - 1] = numberArr[index];

        //打乱数组arr的字符

        int randomIndex = r.nextInt(arr.length);
        for (int i = 0; i < 4; i++) {
            char temp = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temp;
        }
        //字符转换为字符串并返回
        return new String(arr);
    }


}


