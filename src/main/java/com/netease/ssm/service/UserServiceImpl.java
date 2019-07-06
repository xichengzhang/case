package com.netease.ssm.service;

import com.netease.ssm.mapper.UserMapper;
import com.netease.ssm.mapper.VideoMapper;
import com.netease.ssm.pojo.User;
import com.netease.ssm.pojo.Video;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bjzhangxicheng on 2016/12/5.
 */

@Service
public class UserServiceImpl implements UserService {
    //User接口
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    public List<User> findUser() throws Exception {
        //调用mapper类中的selectByExample方法，如果传入类型为null，则表示无条件查找
        List<User> users = userMapper.selectByExample(null);

        return users;
    }

    public Video getOneVideo (String vid){
        return videoMapper.getOneVideo(vid);
    }

    public static void main(String[] args) {
        String ss = "“大叔大婶多” 辅导辅导发地方 dsdsd";
        String a = StringEscapeUtils.escapeHtml(ss);
        System.out.println(a);
        String xx = "乐嘉4分钟视频简介_超清 4.1s “阿斯顿发送到” &quot;dddwewe问问&quot;";
        System.out.println(StringEscapeUtils.unescapeHtml(xx));
    }
}
