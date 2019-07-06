package com.netease.ssm.service;

import com.netease.ssm.pojo.User;
import com.netease.ssm.pojo.Video;

import java.util.List;

/**
 * Created by bjzhangxicheng on 2016/12/5.
 */
public interface UserService {

    /**
     * 查找所有用户
     * @return
     * @throws Exception
     */
    List<User> findUser()throws Exception;

    public Video getOneVideo (String vid)throws Exception;

}
