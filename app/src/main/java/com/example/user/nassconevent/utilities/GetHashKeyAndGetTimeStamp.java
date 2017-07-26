package com.example.user.nassconevent.utilities;

import com.example.user.nassconevent.interfaces.Constants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by user on 26/5/17.
 */

public class GetHashKeyAndGetTimeStamp {
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 111111    * @return hash string
     */
    public String getHash() {
        String hash = "";
        try {
            Long tsLong = System.currentTimeMillis() / 1000;
            String timeStamp = tsLong.toString();
            String params = Constants.APP_KEY + Constants.APP_SECURITY_KEY + timeStamp;
            hash = md5(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * return timestamp string     *     * @return
     */

    public String getTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }
}
