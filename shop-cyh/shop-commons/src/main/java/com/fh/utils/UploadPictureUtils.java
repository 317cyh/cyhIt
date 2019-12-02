package com.fh.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.UUID;


/**
 * 图片上传
 *
 */
public class UploadPictureUtils {


    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";   // 阿里云地址
    private static String accessKeyId = "LTAI4FdGXCoF2qKGLUNvfx4M";  //阿里云信息
    private static String accessKeySecret = "8DF3Hm2pz5gidXTfSvhGi85NmTdnhL";
    private static String bucketName = "cyhupload";    //存到哪一个bucket（阿里云上的对象存储）

    /**
     * 图片上传（文件名重复不覆盖），保单号，图片类型，图片base64字符串
     *
     * @param fileName
     * @param type
     * @param pictureStr
     * @return
     * @throws IOException
     */
    public static String uploadPicture(String fileName, String type, String pictureStr, int count) throws IOException {
        String backUrl = "";
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {
            // type和fileName可以自己定义 

            fileName = fileName + "LSJ" + UUID.randomUUID().toString().replace("-", "").toLowerCase();
            String key = type + "-" + fileName + count;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, createTempFile(fileName, type, pictureStr));
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
            backUrl = "https://" + bucketName + "." + endpoint + "/" + key;

        } catch (OSSException oe) {

        } catch (ClientException ce) {

        } finally {
            client.shutdown();
        }
        return backUrl;
    }

    /**
     * 图片上传（文件名重复覆盖），保单号，图片类型，图片base64字符串
     *
     * @param fileName
     * @param type
     * @param pictureStr
     * @return
     * @throws IOException
     */
    public static String uploadPictureRepeat(String fileName, String type, String pictureStr) throws IOException {
        String backUrl = "";
        /*--------*/
        BASE64Encoder encoder = new BASE64Encoder();
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(pictureStr);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        String encode = encoder.encode(data);
        /*----------------*/
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        try {

            String key = type + "-" + fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, createTempFile(fileName, type, encode));
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
            backUrl = "https://" + bucketName + "." + endpoint + "/" + key;

        } catch (OSSException oe) {
        } catch (ClientException ce) {
        } finally {
            client.shutdown();
            File file=new File(pictureStr);
            if(file.exists()){
                file.delete();
            }
        }
        return backUrl;
    }

    private static File createTempFile(String fileName, String type, String pictureStr) throws IOException {
        File file = File.createTempFile(type + "-" + fileName, ".jpg");
        file.deleteOnExit();
        OutputStream out = new FileOutputStream(file);
        if (pictureStr == null) //图像数据为空
        {

            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(pictureStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            return null;
        }
        return file;
    }

    // 上传本地图片
    public static void main(String[] args) {
        String fileName = "white.png";
        String type = "test";

        File file = new File("E:\\知识\\第三月\\共享文件夹\\图片上传\\imgs\\]ZTSJGXLBHNVSW{A20}0`BU.gif");




        String result = null;
        try {
            result = uploadPictureRepeat(fileName, type, file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

}
