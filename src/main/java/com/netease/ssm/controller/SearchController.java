package com.netease.ssm.controller;

import com.netease.ssm.pojo.Project;
import com.netease.ssm.util.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjzhangxicheng on 2016/12/15.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @RequestMapping("/index")
    public String toIndex(){
        System.out.println();
        return "index";
    }

    @RequestMapping(value="/download_project.do")
    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName="excel文件";
        //填充projects数据
        List<Project> projects=createData();
        List<Map<String,Object>> list=createExcelRecord(projects);
        String columnNames[]={"ID","项目名","所用技术","备注"};//列名
        String keys[] = {"id","name","technology","remarks"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }

    private List<Project> createData() {
        List<Project> data = new ArrayList<Project>();
        Project p1 = new Project();
        p1.setId(1);
        p1.setName("是的是的所");
        p1.setTechnology("安安神多");
        p1.setRemarks("顶顶顶顶");
        Project p2 = new Project();
        p2.setId(2);
        p2.setName("zxc2");
        p2.setTechnology("123444444eeeeee");
        p2.setRemarks("dsdsds2");
        data.add(p1);
        data.add(p2);
        return data;

    }


    private List<Map<String, Object>> createExcelRecord(List<Project> projects) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        Project project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("id", project.getId());
            mapValue.put("name", project.getName());
            mapValue.put("technology", project.getTechnology());
            mapValue.put("remarks", project.getRemarks());
            listmap.add(mapValue);
        }
        return listmap;
    }
}
