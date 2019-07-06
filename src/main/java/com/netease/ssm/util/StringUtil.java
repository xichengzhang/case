/*
 * Created on 2004-5-23 To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


package com.netease.ssm.util;


import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import com.netease.security.*;


/**
 * @author test To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Generation - Code and Comments
 */
public class StringUtil
{
	/**
	 * �����������ַ�����html��ǩɾ��
	 * 
	 * @param str
	 * @return
	 */
	public static String stripTags(String str)
	{
		try
		{
			//str = str.replaceAll("<\\p{Alnum}+?>", "");
			str = str.replaceAll("<[^>]*>","");
			return str;
		}
		catch (Exception e)
		{
			return str;
		}
	}


	/**
	 * �����������ַ����Ļ��з���� <br/>
	 * 
	 * @param str
	 * @return
	 */
	public static String nl2br(String str)
	{
		try
		{
			return str.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>");
		}
		catch (Exception e)
		{
			return str;
		}
	}


	public static boolean containCNWords(String body)
	{
		if (body == null)
		{
			return false;
		}
		for (int i = 0; i < body.length(); i++)
		{
			if (body.charAt(i) > 255)
			{
				return true;
			}
		}
		return false;
	}


	/**
	 * �ַ�������
	 * 
	 * @param str
	 * @param s1
	 * @return
	 */
	public static final int find(String str, String s1)
	{
		try
		{
			int findx = 0;
			Pattern pt = Pattern.compile("(" + s1 + ")", Pattern.CASE_INSENSITIVE);
			Matcher m = pt.matcher(str);
			while (m.find())
			{
				findx++;
			}
			return findx;
		}
		catch (Exception e)
		{
			return 0;
		}
	}


	/**
	 * �õ�ĳ�ַ�����md5��ϣ
	 * 
	 * @param str
	 * @return
	 */
	/*public static String md5(String str)
	{
		try
		{
			MD5 md5 = new MD5();
			return md5.getMD5(str);
		}
		catch (Exception e)
		{
			return "";
		}
	}
*/

	/**
	 * ���ַ���str��splitlit�з֣�1.4�Ѿ��з���
	 * 
	 * @param str
	 * @param splitlit
	 * @return һ������
	 */
	public static String[] split(String str, String splitlit)
	{
		try
		{
			StringTokenizer stringtokenizer = new StringTokenizer(str, splitlit);
			String[] out = new String[stringtokenizer.countTokens()];
			int i = 0;
			while (stringtokenizer.hasMoreTokens())
			{
				out[i] = stringtokenizer.nextToken();
				i++;
			}
			return out;
		}
		catch (Exception e)
		{
			return null;
		}
	}


	/**
	 * ת��html�����ַ�Ϊhtml��
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlSpecialChars(String str)
	{
		try
		{
			if (str.trim() == null)
			{
				return "";
			}
			StringBuffer sb = new StringBuffer();
			char ch = ' ';
			for (int i = 0; i < str.length(); i++)
			{
				ch = str.charAt(i);
				if (ch == '&')
				{
					sb.append("&amp;");
				}
				else if (ch == '<')
				{
					sb.append("&lt;");
				}
				else if (ch == '>')
				{
					sb.append("&gt;");
				}
				else if (ch == '"')
				{
					sb.append("&quot;");
				}
				else
				{
					sb.append(ch);
				}
			}
			if (sb.toString().replaceAll("&nbsp;", "").replaceAll("��", "").trim().length() == 0)
			{
				return "";
			}
			return sb.toString();
		}
		catch (Exception e)
		{
			return "";
		}
	}

	/**
	 * ת�������ַ�
	 * 
	 * @param str
	 * @return
	 */
	public static String changeChar(String str)
	{
		try
		{
			if (str.trim() == null)
			{
				return "";
			}
			str="_" + str;
			StringBuffer sb = new StringBuffer();
			char ch = ' ';
			for (int i = 0; i < str.length(); i++)
			{
				ch = str.charAt(i);
				if (ch == '#' && str.charAt(i-1)=='-' && str.charAt(i+1)=='i')
				{
					sb.append("\\#");
				}
				else
				{
					sb.append(ch);
				}
			}
			return sb.toString().substring(1);
		}
		catch (Exception e)
		{
			return "";
		}
	}


	/**
	 * ɾ���ַ����е����пո�ͻ���
	 * 
	 * @param str
	 * @return
	 */
	public static String stripSpace(String str)
	{
		try
		{
			str = str.replaceAll("&nbsp;", "");
			str = str.replaceAll(" ", "");
			str = str.replaceAll("\r", "");
			str = str.replaceAll("\n", "");
			return str;
		}
		catch (Exception e)
		{
			return null;
		}
	}


	/**
	 * �ڳ�����ǰ����
	 * 
	 * @param num
	 *            ����
	 * @param length
	 *            ���λ��
	 */
	public static String addzero(long num, int length)
	{
		String str = "";
		if (num < Math.pow(10, length - 1))
		{
			for (int i = 0; i < (length - (num + "").length()); i++)
			{
				str += "0";
			}
		}
		str = str + num;
		return str;
	}


	/**
	 * ������ǰ����
	 * 
	 * @param num
	 *            ����
	 * @param length
	 *            ���λ��
	 */
	public static String addzero(int num, int length)
	{
		String str = "";
		if (num < Math.pow(10, length - 1))
		{
			for (int i = 0; i < (length - (num + "").length()); i++)
			{
				str += "0";
			}
		}
		str = str + num;
		return str;
	}


	/**
	 * �ж��ַ����Ƿ�һ������
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str)
	{
		try
		{
			if (("" + Long.parseLong(str)).equals(str))
			{
				return true;
			}
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}


	/**
	 * �ַ����������
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String[] addStrings(String[] a, String[] b)
	{
		try
		{
			if (b == null)
			{
				return a;
			}
			if (a == null)
			{
				return b;
			}
			String[] temp = new String[a.length + b.length];
			for (int i = 0; i < a.length; i++)
			{
				temp[i] = a[i];
			}
			for (int i = 0; i < b.length; i++)
			{
				temp[i + a.length] = b[i];
			}
			return temp;
		}
		catch (Exception e)
		{
			return a;
		}
	}


	/**
	 * �ַ��������һ���ַ���
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String[] addString(String[] a, String b)
	{
		try
		{
			if (b == null)
			{
				return a;
			}
			if (a == null)
			{
				String[] s = new String[1];
				s[0] = b;
				return s;
			}
			String[] s = new String[a.length + 1];
			for (int i = 0; i < a.length; i++)
			{
				s[i] = a[i];
			}
			s[a.length] = b;
			return s;
		}
		catch (Exception e)
		{
			return a;
		}
	}


	/**
	 * �ж��ַ����Ƿ�����
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFine(String str)
	{
		try
		{
			if (str == null || str.trim().length() == 0)
			{
				return false;
			}
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}


	/**
	 * �ж�һ���ַ����Ƿ�����
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFine(String[] str)
	{
		try
		{
			for (int i = 0; i < str.length; i++)
			{
				if (!isFine(str[i]))
				{
					return false;
				}
			}
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}


	/**
	 * ���ؼ���
	 * 
	 * @param words
	 * @param str
	 * @param tostr
	 * @return
	 */
	public static boolean haveWord(String[] words, String str)
	{
		for (int i = 0; i < words.length; i++)
		{
			if (str.indexOf(words[i]) > -1)
			{
				return true;
			}
		}
		return false;
	}


	/**
	 * s�е�s1�滻��s2
	 * @param s
	 * @param s1
	 * @param s2
	 * @return
	 */
    public static final String replace(String s, String s1, String s2)
    {
        if(s == null)
            return null;
        int i = 0;
        if((i = s.indexOf(s1, i)) >= 0)
        {
            char ac[] = s.toCharArray();
            char ac1[] = s2.toCharArray();
            int j = s1.length();
            StringBuffer stringbuffer = new StringBuffer(ac.length);
            stringbuffer.append(ac, 0, i).append(ac1);
            i += j;
            int k;
            for(k = i; (i = s.indexOf(s1, i)) > 0; k = i)
            {
                stringbuffer.append(ac, k, i - k).append(ac1);
                i += j;
            }

            stringbuffer.append(ac, k, ac.length - k);
            return stringbuffer.toString();
        }
            return s;

    }


	/**
	 * �滻��һ�����ֵĴ���
	 * @param str
	 * @param from
	 * @param to
	 * @return
	 */
	public static String replaceFirst(String str, String from, String to)
	{
		char[] chars = str.toCharArray();
		char[] to_chars = to.toCharArray();
		char[] from_chars = from.toCharArray();
		StringBuffer sb = new StringBuffer();
		char from_first = from.charAt(0);
		boolean replaceok = false;
		for (int i = 0; i < chars.length; i++)
		{
			if (chars[i] == from_first && replaceok == true)
			{
				boolean isok = true;
				for (int k = 0; k < from_chars.length; k++)
				{
					if (chars[i + k] != from_chars[k])
					{
						isok = false;
						break;
					}
				}
				System.out.println(from_first);
				if (isok)
				{
					for (int j = 0; j < to_chars.length; j++)
					{
						sb.append(to_chars[j]);
					}
					replaceok = true;
				}
			}
			else
			{
				sb.append(chars[i]);
			}
		}
		return sb.toString();
	}


	/**
	 * ����ַ���
	 * 
	 * @param i
	 * @return
	 */
	public static final String randomString(int i)
	{
		if (i < 1)
		{
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				.toCharArray();
		char ac[] = new char[i];
		for (int j = 0; j < ac.length; j++)
		{
			ac[j] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(ac);
	}


	/**
	 * �õ���׺��
	 * 
	 * @param filename
	 * @return
	 */
	public static final String getExt(String filename)
	{
		int last_point_position = filename.lastIndexOf(".");
		if (last_point_position > 0)
		{
			return filename.substring(last_point_position);
		}
		return "";
	}


	/**
	 * URL����
	 * 
	 * @param str
	 * @return
	 */
	public static String URLEncode(String str)
	{
		try
		{
			return URLEncoder.encode(str, "GBK");
		}
		catch (Exception e)
		{
			return "";
		}
	}


	/**
	 * URL����
	 * 
	 * @param str
	 * @return
	 */
	public static String URLDecode(String str)
	{
		try
		{
			return URLDecoder.decode(str, "GBK");
		}
		catch (Exception e)
		{
			return "";
		}
	}


	/**
	 * BASE64 ����
	 */
	public static String BASE64encode(String s)
	{
		try
		{
			return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
		}
		catch (Exception e)
		{
			return s;
		}
	}


	/**
	 * BASE64 ����
	 */
	public static String BASE64decode(String s)
	{
		try
		{
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		}
		catch (Exception e)
		{
			return s;
		}
	}


	/**
	 * ͨ�ñ���
	 * 
	 * @param s
	 * @return
	 */
	public static String Encode(String s)
	{
		try
		{
			s = BASE64encode(s);
			return URLEncode(s);
		}
		catch (Exception e)
		{
			return s;
		}
	}


	/**
	 * ͨ�ý���
	 * 
	 * @param s
	 * @return
	 */
	public static String Decode(String s)
	{
		try
		{
			s = URLDecode(s);
			return BASE64decode(s);
		}
		catch (Exception e)
		{
			return s;
		}
	}


	/**
	 * �õ�һ���������ַ
	 */
	public static String removeAuthorisation(String uri)
	{
		if (uri.indexOf("@") != -1 && (uri.startsWith("ftp://") || uri.startsWith("http://")))
		{
			return uri.substring(0, uri.indexOf(":") + 2) + uri.substring(uri.indexOf("@") + 1);
		}
		return uri;
	}





	/**
	 * ת���ַ�����booleanֵ
	 * 
	 * @param s
	 * @return
	 */
	public static boolean toBoolean(String s)
	{
		if (s == null || s.length() == 0 || s.equals("false") || s.equals("0"))
		{
			return false;
		}
		return true;
	}


	/**
	 * ת�����ֳ�booleanֵ
	 * 
	 * @param i
	 * @return
	 */
	public static boolean toBoolean(int i)
	{
		if (i <= 0)
		{
			return false;
		}
		return true;
	}


	/**
	 * ��ȡ�����ַ���
	 * @param str
	 * @param start
	 * @param length
	 * @return
	 */
	public static String subString(String str, int start, int length)
	{
		try
		{
			if (str.getBytes("GBK").length<=length*2)
			{
				return str;
			}
			if (length<=0)
			{
				return "";
			}
			byte[] bytes = str.getBytes("GBK");
			int check = 1;
			for (int i = 0; i < start * 2; i++)
			{
				check = check * bytes[i];
				if (check > 1000)
				{
					check = 1;
				}
				if (check < -1000)
				{
					check = -1;
				}
			}
			if (check < 0 && bytes[start * 2] < 0)
			{
				start--;
			}
			byte[] newbytes = new byte[length * 2];
			check = 1;
			for (int i = 0; i < newbytes.length; i++)
			{
				newbytes[i] = bytes[start + i];
				check = check * bytes[start + i];
				if (check > 1000)
				{
					check = 1;
				}
				if (check < -1000)
				{
					check = -1;
				}
			}
			if (check < 0 && newbytes[newbytes.length - 1] < 0)
			{
				newbytes[newbytes.length - 1] = 32;
			}
			return new String(newbytes, "GBK");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}


	/**
	 *
	 *�õ�ȫ�������ַ���
	 *
	 */
	public static String getIntString(String str)
	{
		byte[] bytes = str.getBytes();
		StringBuffer sb = new StringBuffer();
		int iscn = 0;
		for (int i = 0; i < bytes.length; i++)
		{
			int j = bytes[i];
			if (bytes[i] < 0)
			{
				j = j * (-1);
				if (j < 10)
				{
					sb.append('0');
				}
				sb.append(j);
				iscn++;
				if (iscn == 2)
				{
					sb.append(' ');
					iscn = 0;
				}
			}
			else
			{
				sb.append(new String(new byte[]
					{ bytes[i] }));
				if (i<=bytes.length-2 && bytes[i + 1] < 0)
				{
					sb.append(' ');
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * ��������ģ��� ���� �ո� �򶺺Ž��зֲ�
	 * @param input
	 * @return
	 */
	public static String[] spliteGeneral(String input) {
		if (StringUtil.isFine(input)) {
			String rs = input.replace(",", " ");
			rs = rs.replace(":", " ");
			rs = rs.replace(";", " ");
			return rs.split(" ");
		}
		return null;
	}
}
