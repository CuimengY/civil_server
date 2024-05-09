package com.exam.civil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.sql.Array;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CivilApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("age","33");
        String city = (String) redisTemplate.opsForValue().get("name"); // 拿到key为name的值
        System.out.println(city);
        redisTemplate.opsForValue().set("code","1234",3, TimeUnit.MINUTES); // 设置过期时间为三分钟
        redisTemplate.opsForValue().setIfAbsent("lock","1"); // 设置lock为k的唯一值
        redisTemplate.opsForValue().setIfAbsent("lock","2");
    }

    @Test
    public void MergeSort(){
        int[] a = {1,4,3,7,2,5,9};
        int p = 0;
        int r = a.length-1;
        Merge(a,p,r);
        for(int i = 0; i< a.length;i++) {
            System.out.println(a[i]);
        }
    }

    public void Merge(int[] a, int p, int r) {
        int q;
        if (p < r) {
            q = (p+r)/2;
            Merge(a,p,q);
            Merge(a,q+1,r);
            MergeFun(a,p,q,r);
        }
    }

    public void MergeFun(int[] a, int p, int q, int r) {
        int n1 = q-p+1;
        int n2 = r-q;
        int[] L = new int[50];
        int[] R = new int[50];
        for (int i = 0; i< n1;i++){
            L[i] = a[p+i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = a[q+j+1];
        }
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int m = 0;
        int n = 0;
        for(int k = p; k< r+1;k++) {
            if(L[m]<R[n]){
                a[k] = L[m];
                m++;
            }else {
                a[k] = R[n];
                n++;
            }
        }

    }


}
