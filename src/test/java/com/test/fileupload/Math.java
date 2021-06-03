package com.test.fileupload;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Math {

    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("张飞", "23"));
        users.add(new User("关羽", "27"));
        users.add(new User("刘备", "32"));
        users.add(new User("马超", "31"));
        users.add(new User("黄忠", "75"));
        users.add(new User("孙权", "18"));
        users.add(new User("周瑜", "20"));
        users.add(new User("貂蝉", "17"));


        List<User> math = new ArrayList<>();
        math.add(new User("武松", "27"));
        math.add(new User("宋江", "32"));
        math.add(new User("林冲", "31"));
        math.add(new User("罩盖", "47"));
        math.add(new User("方腊", "57"));

        users.stream().forEach(u -> math.stream().forEach(m -> {
            if (u.getAge().equals(m.getAge())) {
                u.setRemark(m.getUserName() + "," + m.getAge());
            }
        }));

        users.stream().forEach(u -> System.out.println(u.getUserName() + "  ,  " + u.getAge() + "  ,  " + u.getRemark()));


//        users.stream().collect(Collectors.toSet()).forEach((u)->System.out.println("姓名 "+u.getUserName()));
        //list转换为map
//        Map<String, User> collect = users.stream().collect(Collectors.toMap((u) -> u.getUserName(),(u2)->u2));
//        collect.forEach((key,User) -> System.out.println(key+","+User));

        //求和
//        Optional<User> sum = users.stream().reduce((u1, u2) -> new User("sum", "" + (Integer.parseInt(u1.getAge()) + Integer.parseInt(u2.getAge()))));
//        System.out.println(sum.get().getUserName() + "  " +sum.get().getAge());

        //最大值,最小值
//        Optional<User> max = users.stream().min((u1, u2) -> Integer.parseInt(u1.getAge()) - Integer.parseInt(u2.getAge()));
//        System.out.println(max.get().getUserName() + " " + max.get().getAge()  );

        //匹配
//        boolean flag = users.stream().anyMatch(u -> u.getUserName().equals("张飞"));
//        System.out.println(flag);

        //过滤器
//        users.stream().filter(u -> Integer.parseInt(u.getAge())>20).forEach(u->System.out.println(u.getUserName()+","+u.getAge()));

        //排序和limit
//        users.stream().sorted((u1,u2)->Integer.parseInt(u1.getAge())-Integer.parseInt(u2.getAge())).skip(2).limit(2).forEach(user -> System.out.println(user.getUserName() + "," +user.getAge()));

    }

}
