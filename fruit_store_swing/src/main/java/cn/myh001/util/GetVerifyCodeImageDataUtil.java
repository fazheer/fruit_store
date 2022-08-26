package cn.myh001.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GetVerifyCodeImageDataUtil {

   public static String verifyCodeGen;
    public static byte[] imageData() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        try {
           verifyCodeGen = VerifyCodeUtils.outputVerifyImage(100, 40, byteArrayOutputStream, 4).trim().toLowerCase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
