package com.netease.ssm.service;

/**
 * Created by bjzhangxicheng on 2019/1/3.
 */
/**
 * 远程文件读写接口.
 * @author liub
 *
 */
public interface RemoteFileService {

    /**
     * 创建或者更新文件.
     * @param filepath 文件存放路径.
     * @param data 文件数据.
     * @return
     * @throws Exception
     */
    public void createOrUpdate(String filepath, String data) throws Exception;

    /**
     * 读取文件.
     * @param filepath 文件存放路径或者模版id
     * @return
     * @throws Exception
     */
    public String read(String filepath) throws Exception;

    /**
     * 读取备份文件.
     * @param filepath 文件存放路径或者模版id
     * @param bakfile 备份文件名称
     * @return
     * @throws Exception
     */
    public String read(String filepath, String bakfile) throws Exception;

    /**
     * 删除文件.
     * @param filepath 文件存放路径或者模版id
     * @return
     * @throws Exception
     */
    public boolean delete(String filepath) throws Exception;

    /**
     * 判断文件是否存在.
     * @param filepath 文件存放路径或者模版id
     * @return
     * @throws Exception
     */
    public boolean isExist(String filepath) throws Exception;

    /**
     * 列出文件列表.
     * @param filepath 文件存放路径或者模版id
     * @return
     * @throws Exception
     */
    public String[] list(String filepath) throws Exception;
}
