package com.netease.ssm.util;

import java.util.*;

/**
 * Created by bjzhangxicheng on 2017/3/13.
 */
public class ListUtil {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        List<String> list4 = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list1.add("test"+i);
        }
        list2.add("test"+2);
        list3.add("test"+3);
        list4.add("test"+4);
        //getDiffrent(list1,list2);
        Set<List<String>> s = new HashSet<List<String>>();
        s.add(list2);
        s.add(list3);
        s.add(list4);

        Iterator it = s.iterator();
        while (it.hasNext()) {
            List<String> list = (List<String>) it.next();
            list1 = getDiffrent(list1,list);
        }
        for(String xx:list1){
            System.out.println(xx);
        }
        //输出：total times 2566454675
    }

    /**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff = new ArrayList<String>();
        for(String str:list1)
        {
            if(!list2.contains(str))
            {
                diff.add(str);
            }
        }
        System.out.println("total times "+(System.nanoTime()-st));
        return diff;
    }

    private static List<String> getDiffrent2(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        list1.retainAll(list2);
        System.out.println("getDiffrent2 total times "+(System.nanoTime()-st));
        return list1;
    }

    private static List<String> getDiffrent3(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        Map<String,Integer> map = new HashMap<String,Integer>(list1.size()+list2.size());
        List<String> diff = new ArrayList<String>();
        for (String string : list1) {
            map.put(string, 1);
        }
        for (String string : list2) {
            Integer cc = map.get(string);
            if(cc!=null)
            {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for(Map.Entry<String, Integer> entry:map.entrySet())
        {
            if(entry.getValue()==1)
            {
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDiffrent3 total times "+(System.nanoTime()-st));
        return list1;
    }
}
