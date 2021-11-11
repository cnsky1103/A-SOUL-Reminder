package com.cnsky1103.service;

import com.cnsky1103.config.Path;
import com.cnsky1103.model.picture.Picture;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.*;

/**
 * 图片的获取、下载
 * @author sky
 */
public class Pictures {
    /**
     * 随机获取一张二创图片
     * @return Picture
     * @see com.cnsky1103.model.picture.Picture
     * @throws Exception
     */
    public static Picture getRandomPicture() throws Exception {
        HttpClient client = getHttpClient();

        HttpGet request = new HttpGet(Path.randomPictureUrl);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result);
        Gson pictureGson = new Gson();
        Picture picture = pictureGson.fromJson(result.toString(), Picture.class);
        System.out.println(picture.getImg());
        return picture;
    }

    /**
     * 将图片下载到本地
     * @param url 图片的源地址
     * @return 下载到本地的路径
     * @throws Exception
     */
    public static String downloadPicture(String url) throws Exception {
        HttpClient client = getHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            InputStream in = response.getEntity().getContent();
            File projectDirFile = new File(Path.pluginImgPath);
            if (!projectDirFile.exists()) {
                projectDirFile.mkdir();
            }
            String imgPath = projectDirFile + File.separator + parsePictureNameFromUrl(url);
            System.out.println(imgPath);
            File imgFile = new File(imgPath);
            if (!imgFile.exists()) {
                imgFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(imgFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fos.close();
            return imgPath;
        } else {
            throw new Exception("download error");
        }
    }

    /**
     * 绕过SSL证书
     */
    private static HttpClient getHttpClient() throws Exception {
        // use the TrustSelfSignedStrategy to allow Self Signed Certificates
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(new TrustSelfSignedStrategy())
                .build();

        // we can optionally disable hostname verification.
        // if you don't want to further weaken the security, you don't have to include this.
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        // create an SSL Socket Factory to use the SSLContext with the trust self signed certificate strategy
        // and allow all hosts verifier.
        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);

        // finally create the HttpClient using HttpClient factory methods and assign the ssl socket factory
        HttpClient client = HttpClients
                .custom()
                .setSSLSocketFactory(connectionFactory)
                .build();

        return client;
    }

    /**
     * 将https://i0.hdslb.com/bfs/album/4a3f76e27f050b543789dab8394e2479b9dd2a92.jpg
     * 转化为4a3f76e27f050b543789dab8394e2479b9dd2a92.jpg
     * @param url
     * @return
     */
    public static String parsePictureNameFromUrl(String url) {
        return url.substring(url.lastIndexOf('/') + 1);
    }
}
