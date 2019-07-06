/*
 * Created on 2004-5-26 To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */


package com.netease.ssm.util;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author test To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Generation - Code and Comments
 */
public class DateUtil
{
	/*
	 * ͳһʱ��
	 */
	private  Calendar cld = null;


	/**
	 * ���캯�������ɵ�ǰʱ�������
	 */
	public DateUtil()
	{
		cld = Calendar.getInstance();
	}


	/**
	 * ��ʱ�������
	 */
	public DateUtil(long unixtime)
	{
		cld = Calendar.getInstance();
		setUnixtime(unixtime);
	}


	/**
	 * �������ַ�������
	 */
	public DateUtil(String datetime)
	{
		cld = Calendar.getInstance();
		setDatetime(datetime);
	}


	/**
	 * ��ʱ���ֲ��
	 */
	public void set(long unixtime)
	{
		setUnixtime(unixtime);
	}


	/**
	 * �������ַ���ֲ��
	 */
	public void set(String datetime)
	{
		setDatetime(datetime);
	}


	/**
	 * �õ�ʱ���
	 * 
	 * @return
	 */
	public long getLongtime()
	{
		return cld.getTimeInMillis();
	}


	/**
	 * �õ�����ʱ���ַ���
	 * 
	 * @return
	 */
	public String getCurTime()
	{
		try
		{
			int dyear = cld.get(Calendar.YEAR);
			String syear = StringUtil.addzero(dyear, 2);
			int dmonth = cld.get(Calendar.MONTH) + 1;
			String smonth = StringUtil.addzero(dmonth, 2);
			int dday = cld.get(Calendar.DAY_OF_MONTH);
			String sday = StringUtil.addzero(dday, 2);
			int dhour = cld.get(Calendar.HOUR_OF_DAY);
			String shour = StringUtil.addzero(dhour, 2);
			int dminute = cld.get(Calendar.MINUTE);
			String sminute = StringUtil.addzero(dminute, 2);
			int dsecond = cld.get(Calendar.SECOND);
			String ssecond = StringUtil.addzero(dsecond, 2);
			String dtime = syear + "-" + smonth + "-" + sday + " " + shour + ":" + sminute + ":"
					+ ssecond;
			return dtime;
		}
		catch (Exception e)
		{
			return "";
		}
	}


	/**
	 * �õ������ַ���
	 * 
	 * @return
	 */
	public String getDate()
	{
		try
		{
			int dyear = cld.get(Calendar.YEAR);
			int dmonth = cld.get(Calendar.MONTH) + 1;
			int dday = cld.get(Calendar.DAY_OF_MONTH);
			String dtime = StringUtil.addzero(dyear, 4) + "-" + StringUtil.addzero(dmonth, 2) + "-"
					+ StringUtil.addzero(dday, 2);
			return dtime;
		}
		catch (Exception e)
		{
			return "";
		}
	}
	public String getDate(int field, int amount) {
		Calendar day = Calendar.getInstance();
		day.add(field, amount);

		try {
			int dyear = day.get(Calendar.YEAR);
			int dmonth = day.get(Calendar.MONTH) + 1;
			int dday = day.get(Calendar.DAY_OF_MONTH);
			String dtime = StringUtil.addzero(dyear, 4) + "-" + StringUtil.addzero(dmonth, 2) + "-"
					+ StringUtil.addzero(dday, 2);
			return dtime;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * �õ����
	 * 
	 * @return
	 */
	public int getYear()
	{
		return cld.get(Calendar.YEAR);
	}


	/**
	 * �õ��·�
	 * 
	 * @return
	 */
	public int getMonth()
	{
		return cld.get(Calendar.MONTH) + 1;
	}


	/**
	 * �õ��շ�
	 * 
	 * @return
	 */
	public int getDay()
	{
		return cld.get(Calendar.DAY_OF_MONTH);
	}


	/**
	 * �õ�Сʱ
	 * 
	 * @return
	 */
	public int getHour()
	{
		return cld.get(Calendar.HOUR_OF_DAY);
	}


	/**
	 * �õ�����
	 * 
	 * @return
	 */
	public int getMinute()
	{
		return cld.get(Calendar.MINUTE);
	}


	/**
	 * �õ���
	 * 
	 * @return
	 */
	public int getSecond()
	{
		return cld.get(Calendar.SECOND);
	}


	/**
	 * �õ�����
	 * 
	 * @return
	 */
	public int getMillisecond()
	{
		return cld.get(Calendar.MILLISECOND);
	}


	/**
	 * �õ����ڼ�
	 * 
	 * @return
	 */
	public String getWeekday()
	{
		int weekday = cld.get(Calendar.DAY_OF_WEEK);
		switch (weekday)
		{
			case 1 :
				return "��";
			case 2 :
				return "һ";
			case 3 :
				return "��";
			case 4 :
				return "��";
			case 5 :
				return "��";
			case 6 :
				return "��";
			case 7 :
				return "��";
		}
		return "";
	}


	public Calendar getCalendar()
	{
		return cld;
	}


	/////////////////////
	//
	///Private-Functions
	//
	/////////////////////
	private  void setUnixtime(long unixtime)
	{
		try
		{
			if (unixtime >= 0)
			{
				cld.setTimeInMillis(unixtime);
			}
		}
		catch (Exception e)
		{
			System.out.println("error");
			cld = Calendar.getInstance();
		}
	}


	private  void setDatetime(String datetime)
	{
		try
		{
			Pattern pt = Pattern.compile(
					"([0-9]{4})-([0-9]{2})-([0-9]{2}) ([0-9]{2}):([0-9]{2}):([0-9]{2})",
					Pattern.CASE_INSENSITIVE);
			Matcher m = pt.matcher(datetime);
			int year = 1970, month = 1, day = 1, hour = 0, minute = 0, second = 0;
			if (m.find())
			{
				year = Integer.parseInt(m.group(1));
				month = Integer.parseInt(m.group(2));
				day = Integer.parseInt(m.group(3));
				hour = Integer.parseInt(m.group(4));
				minute = Integer.parseInt(m.group(5));
				second = Integer.parseInt(m.group(6));
			}
			else
			{
				return;
			}
			cld.set(year, month - 1, day, hour, minute, second);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			cld = Calendar.getInstance();
		}
	}
	
    public  int getDayNum()
    {
        int i = 0;
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        int j = gregoriancalendar.get(1);
        int k = gregoriancalendar.get(2);
        int l = gregoriancalendar.get(5);
        i = getDayNum(j, --k, l);
        return i;
    }
    
    public  int getDayNum(int i, int j, int k)
    {
        int l = 0;
        GregorianCalendar gregoriancalendar = new GregorianCalendar(i, j, k);
        Date date = gregoriancalendar.getTime();
        GregorianCalendar gregoriancalendar1 = new GregorianCalendar(2007, 4, 24);
        Date date1 = gregoriancalendar1.getTime();
        l = (int)((date.getTime() - date1.getTime()) / 86400000L);
        return l;
    }
}