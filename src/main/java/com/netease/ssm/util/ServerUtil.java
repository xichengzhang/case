package com.netease.ssm.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import java.io.CharArrayWriter;

/**
 * @author test To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ServerUtil {
	public static final long DIFF_TIMESTAMP = 1061500000000L;
	private static long l = 0L;

	/**
	 * 用输入的字符串创建一个id号
	 * 
	 * @param str
	 * @return
	 */
	public static String getid(String str) {
		return str + getid();
	}

	/**
	 * 创建8位字符的id号
	 * 
	 * @return
	 */
	public synchronized static String getid() {
		try {
			String id = IntUtil.c10to32(getLongID() - DIFF_TIMESTAMP);
			while (id.length() < 8) {
				id = "0" + id;
			}
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 创建13位数字型的id号
	 * @return
	 */
	public static long getLongID() {
		long longtime = System.currentTimeMillis();
		if (longtime > l) {
			l = longtime;
		} else {
			l++;
		}
		return l;
	}

	/**
	 * 用输入的字符串和时间戳创建id号
	 * 
	 * @param str
	 * @param unixtime
	 * @return
	 */
	public static String getid(String str, long longtime) {
		String id = IntUtil.c10to32(longtime - DIFF_TIMESTAMP);
		while (id.length() < 8) {
			id = "0" + id;
		}
		return str + id;
	}

	/**
	 * 从id取得时间
	 * 
	 * @param id
	 * @return
	 */
	public static long getTime(String id) {
		try {
			return IntUtil.c32to10(id) + DIFF_TIMESTAMP;
		} catch (Exception e) {
			return (long) 0;
		}
	}

	/**
	 * 把id加一
	 * @param id
	 * @return
	 */
	public static String increaseID(String id) {
		id = id.toUpperCase();
		String id1 = id.substring(0, id.length() - 1);
		String id2 = id.substring(id.length() - 1);

		try {
			byte b = id2.getBytes("ISO8859-1")[0];
			java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(4);
			bb.putInt(b + 1);
			id2 = new String(bb.array()).trim();
		} catch (Exception e) {
		}
		return id1 + id2;
	}

	/**
	 * 把id减一
	 * @param id
	 * @return
	 */
	public static String decreaseID(String id) {
		id = id.toUpperCase();
		String id1 = id.substring(0, id.length() - 1);
		String id2 = id.substring(id.length() - 1);

		try {
			byte b = id2.getBytes("ISO8859-1")[0];
			java.nio.ByteBuffer bb = java.nio.ByteBuffer.allocate(4);
			bb.putInt(b - 1);
			id2 = new String(bb.array()).trim();
		} catch (Exception e) {
		}
		return id1 + id2;
	}

	public static void alert(String str, JspWriter out) {
		try {
			out.println("<script>");
			out.println("alert('" + str + "');");
			out.println("</script>");
		} catch (Exception e) {
		}
	}

	public static void closeWindow(JspWriter out) {
		try {
			out.println("<script>");
			out.println("window.close();");
			out.println("</script>");
		} catch (Exception e) {
		}
	}

	public static String decodeChars(String str) {
		String split = "_";
		int startindex = 0;
		int endindex = -1;
		int splitlength = split.length();
		int strlength = str.length();
		CharArrayWriter out = new CharArrayWriter();
		while ((endindex = str.indexOf(split, startindex)) > -1) {
			int result = IntUtil.s2i(str.substring(startindex, endindex));
			if (result > 0) {
				out.append((char) result);
			}
			startindex = endindex + splitlength;
		}
		if (!str.endsWith(split)) {
			int result = IntUtil.s2i(str.substring(startindex, strlength));
			if (result > 0) {
				out.append((char) result);
			}
		}
		return out.toString();
	}

	public static String encodeChars(String str) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			out.append("_");
			out.append((int) c);
		}
		return out.toString();
	}

	public static boolean checkHash(String md5, String movieid, String url, String image, long filesize, int result,
			String uid) {
		String str = movieid + url + image + filesize + result + uid + "56";
		str = str.replaceAll("&amp;", "&");
		byte[] bs = DigestUtils.md5((str).getBytes());
		String hash = new String(byte2hex(bs)).toLowerCase();
		if (hash.equalsIgnoreCase(md5)) {
			return true;
		}
		throw new IllegalArgumentException("56:[" + md5 + "] 163:[" + hash + "]");
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static String getIp(HttpServletRequest request) {

		String ip = request.getRemoteAddr();
		if (ip != null && (ip.startsWith("127.") || ip.startsWith("192.") || ip.startsWith("10."))) {
			ip = request.getHeader("RealIP");
			if (ip != null && ip.indexOf(",") != -1) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}

	/**
	 * 从请求对象中获取ip地址.
	 * @param req
	 * @return
	 */
	public static String getIPFormRequest(HttpServletRequest req) {
		String ip = req.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 判断ip是否为内网ip.
	 * @param ip
	 * @return
	 */
	public static boolean isIntranet(HttpServletRequest req) {
		return isIntranet(getIPFormRequest(req));
	}

	/**
	 * 判断ip是否为内网ip.
	 * * AllowUsers *@203.86.63.98
	AllowUsers *@61.135.255.83
	AllowUsers *@61.135.255.84
	AllowUsers *@61.135.255.88
	AllowUsers *@61.135.255.86
	AllowUsers *@203.86.63.98
	AllowUsers *@203.86.46.140
	AllowUsers *@203.86.46.130
	 * @param ip
	 * @return
	 */
	public static boolean isIntranet(String ip) {
		return ("61.135.255.86".equals(ip) || "61.135.255.88".equals(ip) || "203.86.63.98".equals(ip)
				|| "61.135.255.83".equals(ip) || "203.86.46.140".equals(ip) || "203.86.46.130".equals(ip)
				|| "61.135.255.84".equals(ip) || "127.0.0.1".equals(ip));
	}
}
