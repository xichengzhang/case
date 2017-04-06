<%--
  Created by IntelliJ IDEA.
  User: bjzhangxicheng
  Date: 2016/12/15
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>萝卜直播流日志监控1</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.1.1.js"></script>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <form class="form-search form-inline">
                <label>直播地址</label>
                <input size="50"  type="text" />
                起<input type="text" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});">
                终<input type="text" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'});">
                卡顿秒数
                <select>
                    <option value ="1">0</option>
                    <option value ="2">1~5</option>
                    <option value="3">>5</option>
                </select>
                <button type="submit" class="btn">查找</button>
            </form>
            <table class="table table-condensed table-hover table-striped">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <th>
                        直播地址
                    </th>
                    <th>
                        时间
                    </th>
                    <th>
                        状态
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Default
                    </td>
                </tr>
                <tr class="success">
                    <td>
                        1
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        01/04/2012
                    </td>
                    <td>
                        Approved
                    </td>
                </tr>
                <tr class="error">
                    <td>
                        2
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        02/04/2012
                    </td>
                    <td>
                        Declined
                    </td>
                </tr>
                <tr class="warning">
                    <td>
                        3
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        03/04/2012
                    </td>
                    <td>
                        Pending
                    </td>
                </tr>
                <tr class="info">
                    <td>
                        4
                    </td>
                    <td>
                        TB - Monthly
                    </td>
                    <td>
                        04/04/2012
                    </td>
                    <td>
                        Call in to confirm
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<input type="button" value="导出数据" onclick="download()"/>
<script>
    function download(){
        var url="/search/download_project.do";
        window.open(url);
        var date = new Date(1489131792000);
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        console.log(Y+M+D+h+m+s);
    }
</script>

</body>
</html>
