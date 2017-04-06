package com.netease.ssm.pojo;

import java.util.regex.Pattern;

/**
 * Created by bjzhangxicheng on 2017/2/17.
 */
public class HtmlFilter {
    private static final String REGEX_UNICODE = "[\r\t\f\\x0b\\xa0\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u200b\u2028\u2029\u3000]";

    public static String abbreviate(String str, int width, String ellipsis) {
        if (str == null || "".equals(str)) {
            return "";
        }
        int d = 0;
        int n = 0;
        for (; n < str.length(); n++) {
            d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
            if (d > width) {
                break;
            }
        }
        if (d > width) {
            n = n - ellipsis.length() / 2;
            return str.substring(0, n > 0 ? n : 0) + ellipsis;
        }
        return str = str.substring(0, n);
    }

    public static String Html2Text(String inputString) {
        if (inputString == null) {
            return null;
        }
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        java.util.regex.Pattern p_html1;
        java.util.regex.Matcher m_html1;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s
            // \\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<
            // \\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("default"); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll("default"); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll("default"); // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll("default"); // 过滤html标签

            //			textStr = htmlStr;
            textStr = escapeHTMLTag(htmlStr);
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
            return null;
        }

        return textStr;// 返回文本字符串
    }

    public static final String escapeHTMLTag(String input) {
        if (input == null)
            return "";
        input = input.trim().replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll("\"", "&quot;");
        input = input.replaceAll("'", "&apos");
        //input = input.replaceAll(REGEX_UNICODE, " ");
        return input.trim();
    }

    public static final String escapefromHTMLTag(String input){
        if (input == null)
            return "";
        input = input.trim().replaceAll("&amp;","&");
		/*input = input.replaceAll("<", "&lt;");
		input = input.replaceAll(">", "&gt;");*/
        input = input.replaceAll("&quot;","\"");
        input = input.replaceAll("&apos","'");
        //input = input.replaceAll(" ",REGEX_UNICODE);
        return input.trim();
    }


    public static void main(String[] args) {
        String xx = "分享到 \uE639 \uE638 \uE63A  \uE6BD\t3  0 \t收藏\n" +
                "管理应具有外行的心态\n" +
                "2017/02/10\t\uE641举报\n" +
                "管理者应具有外行的心态，缺乏直接经验实际上可以作为一个优势，因为较少的历史经验不会使他们的愿景发生混乱，他们可能以新的方式、新的视角看问题。这个全新的视角可能使他们产生很多新的理念和创新，这无疑也将导致新员工质疑现行惯例，而这些质疑可能导致产生新的方法、理念和创新。";

        System.out.println(escapefromHTMLTag(xx));
    }
}
