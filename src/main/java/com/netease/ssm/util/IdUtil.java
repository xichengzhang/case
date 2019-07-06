package com.netease.ssm.util;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @author xjyin id generate
 */
public class IdUtil {

    public static final String seed = "http://love.163.com/";

    public static long generateUserActivityScoreId(long userId,String scoreType,long createTime) {
        return genID("/"+userId+"/" + scoreType+"/" + createTime);
    }

    public static long generateAdminPostDoInfoId(long postId,int operateType) {
        return genID("/"+postId+"/" + operateType);
    }

    public static long generateAdminPostDoId(long postId,int operateType,long processTime) {
        return genID("/"+postId+"/" + operateType + "/" + processTime);
    }

    public static long generateAdminPostDoingCommentId(long postId,long commentId,long commentCreateTime) {
        return genID("/"+postId+"/" + commentId + "/" + commentCreateTime);
    }

    /**
     * Generator a user id by user`s email.
     *
     * @param email
     * @return
     */
    public static long generateUserId(String email) {
        return genID(email + Calendar.getInstance().getTimeInMillis());
    }

    /**
     * 生成
     *
     * @param postId
     * @param userId
     * @param grade
     * @param createTime
     * @return
     */
    public static long generateOauthUserId(String type, String openId) {
        return genID("/oauth/" + type + "/" + openId);
    }

    public static long generateNickId(String nick) {
        return genID(nick);
    }

    /**
     * 生成topic的话题动态ID
     *
     * @param topicId
     * @return
     */
    public static long generateTopicTrendId(long topicId) {
        return genID(seed + "/trend/" + topicId);
    }

    public static long generatePostAppendId(long postId, long createTime) {
        return genID(seed + "/postAppend/" + postId + "/" + createTime);
    }

    public static long generateRecommendRelationId(long userId1, long userId2) {
        if (userId1 > userId2) {
            userId1 ^= userId2;
            userId2 ^= userId1;
            userId1 ^= userId2;
        }
        return genID(seed + userId1 + "/RecommendRelation/" + userId2);
    }

    /***
     * 生成帖子id
     *
     * @param userId
     * @param groupId
     * @param createTime
     * @return
     */
    public static long generatePostId(long userId, long groupId, long createTime) {
        return genID("/post/" + userId + "/" + groupId + "/" + createTime);
    }

    /****
     * 生成帖子用户顶踩关系id
     *
     * @param postId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generatePostUserId(long postId, long userId,
            long createTime) {
        return genID("/post/" + postId + "/" + userId + "/" + createTime);
    }

    /**
     * device id
     *
     * @return
     */
    public static long generateDeviceId(String uniqueId) {
        return genID("/device_id/" + uniqueId);
    }

    /***
     * 生成帖子举报id
     *
     * @param postId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generatePostReportId(long postId, long userId,
            long createTime) {
        return genID("/post/report/" + postId + "/" + userId + "/" + createTime);
    }

    /****
     * 生成评论id
     *
     * @param postId
     * @param userId
     * @param grade
     * @param createTime
     * @return
     */
    public static long generateCommentId(long postId, long userId, int grade,
            long createTime) {
        return genID("/comment/" + postId + "/" + userId + "/" + grade + "/"
                + createTime);
    }

    /***
     * 生成评论用户顶踩关系id
     *
     * @param commentId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generateCommentUserId(long commentId, long userId,
            long createTime) {
        return genID("/comment/" + commentId + "/" + userId + "/" + createTime);
    }

    /***
     * 生成评论举报id
     *
     * @param commentId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generateCommentReportId(long commentId, long userId,
            long createTime) {
        return genID("/comment/report/" + commentId + "/" + userId + "/"
                + createTime);
    }

    /***
     * 生成私信举报id
     *
     * @param inboxId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generateInboxReportId(long inboxId, long userId,
            long createTime) {
        return genID("/inbox/report/" + inboxId + "/" + userId + "/"
                + createTime);
    }

    /**
     * 生成群组ID
     *
     * @param channelId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generateGroupId(long channelId, long userId,
            long createTime) {
        return genID("/group/" + channelId + "/" + userId + "/" + createTime);
    }

    /**
     * 生成群组用户关系ID
     *
     * @param groupId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generateGroupUserId(long groupId, long userId,
            long createTime, String kind) {
        return genID("/groupUser/" + groupId + "/" + userId + "/" + createTime
                + "/" + kind);
    }

    /**
     * 生成群组操作ID
     *
     * @param groupId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generateGroupOperationId(long groupId, long userId,
            long createTime, String operation) {
        return genID("/groupOper/" + groupId + "/" + userId + "/" + createTime
                + "/" + operation);
    }

    /**
     * 生成群组排名ID
     *
     * @param groupId
     * @param createTime
     * @return
     */
    public static long generateGroupRankingId(long groupId, String rankDate) {
        return genID("/groupRank/" + groupId + "/" + rankDate);
    }

    /**
     * 生成群组签到ID
     *
     * @param groupId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generateGroupSignId(long groupId, long userId,
            long createTime) {
        return genID("/groupSign" + groupId + "/" + userId + "/" + createTime);
    }

    /**
     * 生成群组信息统计ID
     *
     * @param groupId
     * @param createTime
     * @return
     */
    public static long generateGroupStatisId(long groupId, long createTime) {
        return genID("/groupStatis" + groupId + "/" + createTime);
    }

    /**
     * 生成群组复审id
     *
     * @param groupId
     * @param userId
     * @param createTime
     * @param type
     * @return
     */
    public static long generateGroupReviewId(long groupId, long userId,
            long createTime, int type) {
        return genID("/groupReview" + groupId + "/" + userId + "/" + createTime
                + "/" + type);
    }

    /**
     * 频道用户ID
     *
     * @param channelId
     * @param userId
     * @param createTime
     * @return
     */
    public static long generateChannelUserId(long channelId, long userId,
            long createTime) {
        return genID("/channelUser" + channelId + "/" + userId + "/"
                + createTime);
    }

    /**
     * 私信 ID
     *
     * @param senderId
     * @param receiverId
     * @param createTime
     * @return
     */
    public static long genInboxId(long senderId, long receiverId,
            long createTime) {
        return genID("/inbox/" + senderId + "/" + receiverId + "/" + createTime);
    }

    /**
     * 私信会话 ID
     *
     * @param aUserId
     * @param bUserId
     * @return
     */
    public static long genInboxSetId(long aUserId, long bUserId) {
        return genID("/inboxSet/" + aUserId + "/" + bUserId);
    }

    /**
     * 全员系统消息ID
     * @param userId
     * @param msgType
     * @param create_time
     * @return
     */
    public static long genSysMsgId(long userId,int msgType, long create_time) {
        return genID("/sysMsg/" +userId+"/"+msgType+"/"+create_time);
    }

    /**
     * 广播、活动支持消息ID
     *
     * @param receiveType
     * @param receiverId
     * @param create_time
     * @return
     */
    public static long genPushMsgId(int receiveType,String receiverIds,long create_time) {
        return genID("/pushOrActivity/"+receiverIds+"/"+receiveType+"/"+ create_time);
    }

    public static long genDoLotteryId(long lotteryTime, long userId,
            long deviceId) {
        return genID("/doLottery/" + userId + "/" + deviceId + "/" + lotteryTime);
    }

    public static long genLotteryId(String activityId, String lotteryType, long createTime){
        return genID("/lottery/" + activityId + "/" + lotteryType + "/" + createTime);
    }

    /**
     * 开屏广告ID
     *
     * @param create_time
     * @param create_time
     * @param create_time
     * @return
     */
    public static long genOpenScreenAdvertiseId(String channel_id,int targetPage,String platform) {
        return genID("/" + channel_id+"/" +targetPage+"/"+platform+"/"+Calendar.getInstance().getTimeInMillis());
    }

    public static long genReportSummaryId(long entityId, long entityType, long createTime){
    	return genID("/" + entityId + "/" + entityType + "/" + createTime);
    }

    /**
     * Generate a id from a string value using MD5 digest algorithm
     *
     * @param str
     * @return
     */
    public static long genID(String str) {
        byte[] data = encode(str);
        return halfDigest(data, 0, data.length);
    }

    /**
     * return a digest value which is half the length of a MD5 digest value. use
     * 53 length for js
     *
     * @param data
     * @param start
     * @param len
     * @return
     */
    // public static long halfDigest(byte[] data, int start, int len) {
    // byte[] digest = digest(data, start, len);
    // return (((long) digest[0] << 56) | ((long) (digest[1] & 0xFF) << 48)
    // | ((long) (digest[2] & 0xFF) << 40)
    // | ((long) (digest[3] & 0xFF) << 32)
    // | ((long) (digest[4] & 0xFF) << 24)
    // | ((long) (digest[5] & 0xFF) << 16)
    // | ((long) (digest[6] & 0xFF) << 8) | ((long) digest[7] & 0xFF));
    // }
    public static long halfDigest(byte[] data, int start, int len) {
        byte[] digest = digest(data, start, len);
        long res = (((long) (digest[0] & 0x1F) << 48)
                | ((long) (digest[1] & 0xFF) << 40)
                | ((long) (digest[2] & 0xFF) << 32)
                | ((long) (digest[3] & 0xFF) << 24)
                | ((long) (digest[4] & 0xFF) << 16)
                | ((long) (digest[5] & 0xFF) << 8) | (digest[6] & 0xFF));
        return Math.abs(res);
    }

    // test range
    public static void main(String[] args) {
        /*long res = (((long) (255 & 0x1F) << 48) | ((long) (255 & 0xFF) << 40)
                | ((long) (255 & 0xFF) << 32) | ((long) (255 & 0xFF) << 24)
                | ((long) (255 & 0xFF) << 16) | ((long) (255 & 0xFF) << 8) | (255 & 0xFF));*/
//        log.debug(255 & 15);
//        log.debug(res);
//        log.debug(genID("aa"));
        System.out.println(genID("VZ13CN1PF"));
        System.out.println(genID("VZ13CN1PF")%1000);
        //5846640870866375
        //5846640870866375
        System.out.println(cutData(2,"VZ13CN1PF"));
    }

    private static int cutData (int doneStatus,String vid){
        String percent = "400";
        if(percent == null || percent.equals("")){
            return doneStatus;
        }
        long num = IdUtil.genID(vid)%1000;
        if(num < Long.valueOf(percent)){
            String ds = "-" + doneStatus;
            return Integer.parseInt(ds);
        }
        return doneStatus;
    }

    private static final ThreadLocal<MessageDigest> DIGESTER_CONTEXT = new ThreadLocal<MessageDigest>() {
        @Override
        protected synchronized MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    };

    /**
     * Construct a hash value for a byte array.
     */
    private static byte[] digest(byte[] data, int start, int len) {
        MessageDigest digester = DIGESTER_CONTEXT.get();
        digester.update(data, start, len);
        return digester.digest();
    }

    /**
     * Convert <code>s</code> to a UTF8 Sequence.
     *
     * @param s
     * @return
     */
    public static byte[] encode(String s) {
        int strlen = s.length();
        int utflen = 0;
        char c;
        for (int i = 0; i < strlen; i++) {
            c = s.charAt(i);
            if ((c >= 0x0000) && (c <= 0x007F)) {
                utflen++;
            } else if (c > 0x07FF) {
                utflen += 3;
            } else {
                utflen += 2;
            }
        }
        byte[] buf = new byte[utflen];
        encode(s, buf, 0);
        return buf;
    }

    /**
     * 将字符串转化成为UTF-8的表示，保存在bytes从offset开始的空间内. 需要注意的是，这个方法没有做range
     * check，所以希望给出的bytes留下 足够的空间.
     *
     * @param s
     *            String to be encoded
     * @param bytes
     *            Buffer to store encoded result
     * @param offset
     *            Start of the buffer to store the result
     * @return 实际编码后的字节数，
     */
    private static int encode(String s, byte[] bytes, int offset) {
        int strlen = s.length();

        int i = 0;
        char c;
        int pos = offset;
        for (i = 0; i < strlen; i++) {
            c = s.charAt(i);
            if (!((c >= 0x0000) && (c <= 0x007F))) {
                break;
            }
            bytes[pos++] = (byte) c;
        }

        for (; i < strlen; i++) {
            c = s.charAt(i);
            if ((c >= 0x0000) && (c <= 0x007F)) {
                bytes[pos++] = (byte) c;
            } else if (c > 0x07FF) {
                bytes[pos++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                bytes[pos++] = (byte) (0x80 | ((c >> 6) & 0x3F));
                bytes[pos++] = (byte) (0x80 | ((c >> 0) & 0x3F));
            } else {
                bytes[pos++] = (byte) (0xC0 | ((c >> 6) & 0x1F));
                bytes[pos++] = (byte) (0x80 | ((c >> 0) & 0x3F));
            }
        }
        return pos - offset;
    }

    private static volatile SecureRandom numberGenerator = new SecureRandom();

    /*public static String genUserUrl(int count) {
        String url = RandomStringUtils.random(1, 0, 9, false, true, new char[] {
            '1', '2', '3', '4', '5', '6', '7', '8', '9'
        }, numberGenerator)
                + RandomStringUtils.random(count - 1, 0, 9, false, true,
                        new char[] {
                            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
                        }, numberGenerator);
        return url;
    }*/

    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (byte element: b) {
            String hex = Integer.toHexString(element & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * 将16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    private static final String HEX_NUMS_STR = "0123456789ABCDEF";

    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR
                    .indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    // public static String genOrderId(int count) {
    // String url = RandomStringUtils.random(count, 0, 35, true, true, new
    // char[] { '1', '2', '3', '4', '5', '6', '7',
    // '8', '9' ,'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
    // 'm', 'n', 'o', 'p', 'q', 'r', 's',
    // 't', 'u', 'v', 'w', 'x', 'y', 'z' }, new SecureRandom());
    // // + RandomStringUtils.random(count - 1, 0, 9, true, true, new char[] {
    // '0', '1', '2', '3', '4', '5',
    // // '6', '7', '8', '9' }, numberGenerator);
    // return url;
    // }

    /**
     * 随机生成14位订单号，订单格式为年月日+六位随机字符串，随机字符串由0-9和26个英文小写字母构成
     *
     * @param length
     * @return
     */
    public static String generateOrderId(int length) {
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };
        StringBuffer sb = new StringBuffer("");
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        sb.append(df.format(Calendar.getInstance().getTime()));
        Random r = new Random();
        while (count < length) {
            i = Math.abs(r.nextInt(str.length));

            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
                count++;
            }
        }
        return sb.toString();
    }

    public static String generateCode(int length) {
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', '2', '3', '4', '5',
            '6', '7', '8', '9'
        };
        StringBuffer sb = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            i = Math.abs(r.nextInt(str.length));

            if (i >= 0 && i < str.length) {
                sb.append(str[i]);
                count++;
            }
        }
        return sb.toString();
    }

    public static long genSubChannelId(String name) {
        // TODO Auto-generated method stub
        return genID("/" + name + "/"
                + Calendar.getInstance().getTimeInMillis());
    }

    public static long genTagId(String name) {
        // TODO Auto-generated method stub
        return genID("/" + name + "/"
                + Calendar.getInstance().getTimeInMillis());
    }
}
