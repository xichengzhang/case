package com.netease.ssm.util;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by bjzhangxicheng on 2017/6/22.
 */
public class getInfoExcel {

    public static Set<String> getFacebookSet(String excelpath){
        Set<String> hashset = new HashSet<String>();
        try {
            File file = new File(excelpath); // 创建文件对象
            Workbook wb = Workbook.getWorkbook(file); // 从文件流中获取Excel工作区对象（WorkBook）
            Sheet sheet = wb.getSheet(0); // 从工作区中取得页（Sheet）

            for (int i = 0; i < sheet.getRows(); i++) { // 循环打印Excel表中的内容
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(0, i);
                    if (cell.getContents().trim() != null && !"".equals(cell.getContents().trim())) {
                        hashset.add(cell.getContents());
                    }
                }
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashset;
    }

    public static void main(String[] args) {
        Set<String> facebookSet = getFacebookSet("C:\\Users\\bjzhangxicheng\\Desktop\\facebook1.xls");
        System.out.println(facebookSet.size());
        for(String x : facebookSet){
            System.out.println(x);
        }
    }
}
