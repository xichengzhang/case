package com.netease.ssm.util;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjzhangxicheng on 2017/6/14.
 */
public class zhengzeTest {

    //private static String zxc ="<script>ytimg.preload(\"https:\\/\\/r2---sn-i3beln7d.googlevideo.com\\/generate_204?conn2\");ytimg.preload(\"https:\\/\\/r2---sn-i3beln7d.googlevideo.com\\/generate_204\");</script><title>今天我最美 --蘋果日報20160503 - YouTube</title><link rel=\"canonical\" href=\"https://www.youtube.com/watch?v=d1YtQ2ZC9nI\"><link rel=\"alternate\" media=\"handheld\" href=\"https://m.youtube.com/watch?v=d1YtQ2ZC9nI\"><link rel=\"alternate\" media=\"only screen and (max-width: 640px)\" href=\"https://m.youtube.com/watch?v=d1YtQ2ZC9nI\">      <meta name=\"title\" content=\"今天我最美 --蘋果日報20160503\">      <meta name=\"description\" content=\"地點：信義威秀中庭 暱稱：corinna 身高：162公分 行業：科技業 興趣：跳舞,唱歌 臉書：corinna lien https://www.facebook.com/corinna.lien ●蘋果日報FB主粉絲團 https://www.facebook.com/appledaily.tw ●蘋果日報即...\">      <meta name=\"keywords\" content=\"twappledailybeauty, beauty, 今天我最美, 台蘋, 台灣, 蘋果日報, 台灣蘋果日報, appledaily, twappledaily, appledailytw, 動新聞, 正妹, 辣妹, 美眉, 靚妹, 表特, 我最美, 最美, 今日我最美, SG, Showgirl, 火辣, 清...\"><link rel=\"manifest\" href=\"/manifest.json\"><link rel=\"shortlink\" href=\"https://youtu.be/d1YtQ2ZC9nI\"><link rel=\"search\" type=\"application/opensearchdescription+xml\" href=\"https://www.youtube.com/opensearch?locale=zh_HK\" title=\"YouTube 影片搜尋\"><link rel=\"shortcut icon\" href=\"https://s.ytimg.com/yts/img/favicon-vflz7uhzw.ico\" type=\"image/x-icon\">     <link rel=\"icon\" href=\"/yts/img/favicon_32-vfl8NGn4k.png\" sizes=\"32x32\"><link rel=\"icon\" href=\"/yts/img/favicon_48-vfl1s0rGh.png\" sizes=\"48x48\"><link rel=\"icon\" href=\"/yts/img/favicon_96-vfldSA3ca.png\" sizes=\"96x96\"><link rel=\"icon\" href=\"/yts/img/favicon_144-vflWmzoXw.png\" sizes=\"144x144\"><meta name=\"theme-color\" content=\"#e62117\">        <link rel=\"alternate\" href=\"android-app://com.google.android.youtube/http/www.youtube.com/watch?v=d1YtQ2ZC9nI\">    <link rel=\"alternate\" href=\"ios-app://544007664/vnd.youtube/www.youtube.com/watch?v=d1YtQ2ZC9nI\">      <link rel=\"alternate\" type=\"application/json+oembed\" href=\"http://www.youtube.com/oembed?format=json&amp;url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3Dd1YtQ2ZC9nI\" title=\"今天我最美 --蘋果日報20160503\">  <link rel=\"alternate\" type=\"text/xml+oembed\" href=\"http://www.youtube.com/oembed?format=xml&amp;url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3Dd1YtQ2ZC9nI\" title=\"今天我最美 --蘋果日報20160503\">        <meta property=\"og:site_name\" content=\"YouTube\">      <meta property=\"og:url\" content=\"https://www.youtube.com/watch?v=d1YtQ2ZC9nI\">    <meta property=\"og:title\" content=\"今天我最美 --蘋果日報20160503\">    <meta property=\"og:image\" content=\"https://i.ytimg.com/vi/d1YtQ2ZC9nI/hqdefault.jpg\">      <meta property=\"og:description\" content=\"地點：信義威秀中庭 暱稱：corinna 身高：162公分 行業：科技業 興趣：跳舞,唱歌 臉書：corinna lien https://www.facebook.com/corinna.lien ●蘋果日報FB主粉絲團 https://www.facebook.com/appledaily.tw ●蘋果日報即...\">    <meta property=\"al:ios:app_store_id\" content=\"544007664\">    <meta property=\"al:ios:app_name\" content=\"YouTube\">      <meta property=\"al:ios:url\" content=\"vnd.youtube://www.youtube.com/watch?v=d1YtQ2ZC9nI&amp;feature=applinks\">      <meta property=\"al:android:url\" content=\"vnd.youtube://www.youtube.com/watch?v=d1YtQ2ZC9nI&amp;feature=applinks\">    <meta property=\"al:android:app_name\" content=\"YouTube\">    <meta property=\"al:android:package\" content=\"com.google.android.youtube\">    <meta property=\"al:web:url\" content=\"https://www.youtube.com/watch?v=d1YtQ2ZC9nI&amp;feature=applinks\">    <meta property=\"og:type\" content=\"video\">        <meta property=\"og:video:url\" content=\"https://www.youtube.com/embed/d1YtQ2ZC9nI\">        <meta property=\"og:video:secure_url\" content=\"https://www.youtube.com/embed/d1YtQ2ZC9nI\">        <meta property=\"og:video:type\" content=\"text/html\">        <meta property=\"og:video:width\" content=\"640\">        <meta property=\"og:video:height\" content=\"360\">        <meta property=\"og:video:url\" content=\"http://www.youtube.com/v/d1YtQ2ZC9nI?version=3&amp;autohide=1\">        <meta property=\"og:video:secure_url\" content=\"https://www.youtube.com/v/d1YtQ2ZC9nI?version=3&amp;autohide=1\">        <meta property=\"og:video:type\" content=\"application/x-shockwave-flash\">        <meta property=\"og:video:width\" content=\"640\">        <meta property=\"og:video:height\" content=\"360\">        <meta property=\"og:video:tag\" content=\"twappledailybeauty\">        <meta property=\"og:video:tag\" content=\"beauty\">        <meta property=\"og:video:tag\" content=\"今天我最美\">        <meta property=\"og:video:tag\" content=\"台蘋\">        <meta property=\"og:video:tag\" content=\"台灣\">        <meta property=\"og:video:tag\" content=\"蘋果日報\">        <meta property=\"og:video:tag\" content=\"台灣蘋果日報\">        <meta property=\"og:video:tag\" content=\"appledaily\">        <meta property=\"og:video:tag\" content=\"twappledaily\">        <meta property=\"og:video:tag\" content=\"appledailytw\">        <meta property=\"og:video:tag\" content=\"動新聞\">        <meta property=\"og:video:tag\" content=\"正妹\">        <meta property=\"og:video:tag\" content=\"辣妹\">        <meta property=\"og:video:tag\" content=\"美眉\">        <meta property=\"og:video:tag\" content=\"靚妹\">        <meta property=\"og:video:tag\" content=\"表特\">        <meta property=\"og:video:tag\" content=\"我最美\">        <meta property=\"og:video:tag\" content=\"最美\">        <meta property=\"og:video:tag\" content=\"今日我最美\">        <meta property=\"og:video:tag\" content=\"SG\">        <meta property=\"og:video:tag\" content=\"Showgirl\">        <meta property=\"og:video:tag\" content=\"火辣\">        <meta property=\"og:video:tag\" content=\"清純\">        <meta property=\"og:video:tag\" content=\"學生妹\">        <meta property=\"og:video:tag\" content=\"蘋果動新聞\">        <meta property=\"og:video:tag\" content=\"美女\">        <meta property=\"og:video:tag\" content=\"corinna\">    <meta property=\"fb:app_id\" content=\"87741124305\">        <meta name=\"twitter:card\" content=\"player\">    <meta ";

    private static String p1 = "/browse_ajax?action_continuation=1&amp;continuation=4qmFsgJAEhhVQy0zaklBbG5RbWJiVk1WNmdSN0s4YVEaJEVnWjJhV1JsYjNNZ0FEZ0JZQUZxQUhvQk1yZ0JBQSUzRCUzRA%253D%253D&amp;direct_render=1\" data-uix-load-more-targe";
    private static String p2 = "data-uix-load-more-href=\\\"\\/browse_ajax?action_continuation=1\\u0026amp;continuation=4qmFsgI-EhhVQy0zaklBbG5RbWJiVk1WNmdSN0s4YVEaIkVnWjJhV1JsYjNNZ0FEZ0JZQUZxQUhvQ01UQzRBUUElM0Q%253D\\\" data-uix-load-more-target-id";
    private static String p3 = "/browse_ajax?action_continuation=1&amp;continuation=4qmFsgJAEhhVQy0zaklBbG5RbWJiVk1WNmdSN0s4YVEaJEVnWjJhV1JsYjNNZ0FEZ0JZQUZxQUhvQk1yZ0JBQSUzRCUzRA%253D%253D\"&amp;direct_render=1\" data-uix-load-more-targe";
    private static String p4 = "ata-original-aspect-ratio=\"1.7777777777778\" id=\"u_0_h\"></video><div class=\"_m54 _1jto _3htz\" id=\"u_0_a\"><img class=\"_1445 _2sy9 img\" src=\"https://static.xx.fbcdn.net/rsrc.php/v3/y4/r/-PAXP-deijE.gif\" alt=\"\" style=\"background-image: url(https://fb-s-c-a.akamaihd.net/h-ak-fbx/v/t15.0-10/p526x395/19311842_10155390609984798_2056488602556170240_n.jpg?oh=6d301a699b2bfc9277e77436aedb6291&amp;oe=59D72F72&amp;__gda__=1507071103_18bb59c7cdcf3f21d147f66cb022e249);\" id=\"u_0_b\" /></div><div class=\"_4ubd _3tsq _11q2 _3htz\" id=\"u_0_c\"></div><div class=\"_567v _3bw _4ubd _28dy _3htz\" id=\"u_0_d\"><div class=\"_567_\"><div class=\"_2za- _2vd- _28dz\" id=\"u_0_g\"><div class=\"_1vx9\"><a class=\"_2za_\" href=\"/technologyreview/videos/10155390607934798/\"><span class=\"_50f7\">Q&amp;A with ";

    private static String p5 = "Spam Kimbap \n" +
            "?Follow Cookat for more awesome recipes!";

    public static void main(String[] args) {

        /*Pattern patternTitle = Pattern.compile("(.*)(<title>)(.*)(</title>)(.*)");
        Matcher matcherTitle = patternTitle.matcher(zxc);
        boolean matchedTitle = matcherTitle.find();
        if (matchedTitle) {
            System.out.println(matcherTitle.group(3));
        }

        Pattern patternDesc = Pattern.compile("(.*)(<meta name=\"description\" content=\")(.*)(\">      <meta name=\"keywords\")(.*)");
        Matcher matcherDesc = patternDesc.matcher(zxc);
        boolean matchedDesc = matcherDesc.find();
        if (matchedDesc) {
            System.out.println(matcherDesc.group(3));
        }*/

        /*Pattern patternDesc = Pattern.compile("(.*)(amp;continuation=)(.*253D)(.*)");
        Matcher matcherDesc = patternDesc.matcher(p3);
        boolean matchedDesc = matcherDesc.find();
        if (matchedDesc) {
            System.out.println(matcherDesc.group(3));
        }*/

        Pattern patternDesc = Pattern.compile("(.*)(style=\"background-image: url\\()(.*?)(\\))(.*)");
        Matcher matcherDesc = patternDesc.matcher(p4);
        boolean matchedDesc = matcherDesc.find();
        System.out.println(matchedDesc);
        if (matchedDesc) {
            System.out.println(matcherDesc.group(3).replace("amp;",""));
        }

        // 表达式对象
        /*Pattern p = Pattern.compile("DR(\\d{3})(New)R(\\d)");
        Matcher m = p.matcher("DR102NewR2");
        boolean found = m.find();
        if( found )
        {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
            System.out.println(m.group(3));

        }*/

        /*String text="s = (90) e s = (60) e";
        text=text.replaceAll(" ", "");
        Pattern p=Pattern.compile("\\((.*?)\\)");
        Matcher m=p.matcher(text);
        List<String> list = new ArrayList<String>();
        while(m.find()){
            list.add(m.group(1));
        }
        System.err.println(list.get(0));
*/


        try{
        URL url = new URL("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=30971&editionType=default&source=qcloud");
        URLConnection conn = url.openConnection();

        Map headers = conn.getHeaderFields();
        Set<String> keys = headers.keySet();
        for( String key : keys ){
            String val = conn.getHeaderField(key);
            System.out.println(key+"    "+val);
        }

        System.out.println( conn.getLastModified() );
        }catch (Exception e){

        }
    }
}
