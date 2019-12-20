package com.yvan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by pzj on 2018-05-18.
 */
public class FileUploadUtils {

  private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

  /***
   * 上传图片
   * @param multipartFile
   * @param absolutePath
   * @param relativePath
   * @param maxSize
   * @param includesuffixs
   * @return
   * @throws Exception
   */
  public static String uploadFile(MultipartFile multipartFile, String absolutePath,
                                                                String relativePath, Long maxSize, String[] includesuffixs)
        throws Exception {

    String filePrefix =
            new Long(System.currentTimeMillis()).toString() + UUID.randomUUID().toString()
                    .substring(0, 3);
    return uploadFile(multipartFile,absolutePath,relativePath,filePrefix,maxSize,includesuffixs);
}


  /***
   * 上传图片
   * @param multipartFile
   * @param absolutePath
   * @param relativePath
   * @param filePrefix
   * @param maxSize
   * @param includesuffixs
   * @return
   * @throws Exception
   */
  public static String uploadFile(MultipartFile multipartFile, String absolutePath,
                                  String relativePath,String  filePrefix,Long maxSize, String[] includesuffixs)
          throws Exception {
      String today = new SimpleDateFormat("yyyyMM").format(new Date());
      relativePath = relativePath + today;
      String realAbsolutePath = absolutePath + relativePath;

      String fileName = multipartFile.getOriginalFilename();
    if (StringUtils.isEmpty(fileName)) {
      return null;
    }
    String[] split = fileName.split("\\.");
    String suffix = split.length >= 2 ? split[split.length - 1] : "temp";
    canUpload(multipartFile, suffix, maxSize, includesuffixs);

    //获取文件相对路径
    String filePath = relativePath + "/" + filePrefix + "." + suffix;

    //文件上传绝对路径
    String destFileName = realAbsolutePath + "/" + filePrefix + "." + suffix;

    File destFile = new File(destFileName);
    if(!destFile.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
      destFile.getParentFile().mkdirs();				//不存在就全部创建
    }
    InputStream stream = null;
    try {
      stream = multipartFile.getInputStream();
      saveFileFromInputStream(stream, destFile);
      logger.info("上传文件:" + destFileName);
    } catch (Exception e) {
      logger.error("上传文件出错", e);
      return null;
    } finally {
      if (stream != null) {
        stream.close();
      }
    }
    return filePath;
  }





public static String uploadByteFile(byte[] bytes, String absolutePath,
                                        String relativePath, long maxSize, String[] includesuffixs){
  String realAbsolutePath = initPath(absolutePath + relativePath);
// 生成文件名
  String files = new SimpleDateFormat("yyyyMMddHHmmssSSS")
          .format(new Date())
          + (new Random().nextInt(9000) % (9000 - 1000 + 1) + 1000)
          + ".png";
  // 生成文件路径
  String filename = realAbsolutePath +"/"+ files;
  OutputStream imageStream = null;
  try {
//    if(bytes!=null&&bytes.length/8/1024/1024>2){
//      throw new RuntimeException("图片大于2M");
//    }
    // 生成文件
    File imageFile = new File(filename);
    imageFile.createNewFile();
    if(!imageFile.exists()){
      imageFile.createNewFile();
    }
    imageStream = new FileOutputStream(imageFile);
    imageStream.write(bytes);
    imageStream.flush();
    imageStream.close();
  } catch (Exception e) {
    e.printStackTrace();
    return null;
  }finally {
    IOUtils.closeQuietly(imageStream);
  }
  return filename.replace(absolutePath, "");
}

  /***
   * 处理缩略图
   * @param absolutePath
   * @param picUrl
   * @return
   * @throws Exception
   */
  public static String uploadByteFileSmall( String absolutePath, String picUrl)
          throws Exception {
    String[] split = picUrl.split("\\.");
    String suffix = split[0] + "_s." + split[1];
    String smallFileName = absolutePath + suffix;
    FileInputStream fis = null;
    try {
      File file = new File(absolutePath+picUrl);
      if(!file.exists()){
        throw new RuntimeException("生成缩略图失败");
      }
      fis = new FileInputStream(file);
      Thumbnails.of(fis).size(180, 180).toFile(smallFileName);
      logger.info("上传文件:" + smallFileName);
    } catch (Exception e) {
      logger.error("上传文件出错", e);
    }finally {
      IOUtils.closeQuietly(fis);
    }
    return smallFileName.replace(absolutePath, "");
  }

  /***
   * 处理缩略图
   * @param multipartFile
   * @param absolutePath
   * @param picUrl
   * @return
   * @throws Exception
   */
  public static String uploadFileSmall(MultipartFile multipartFile, String absolutePath, String picUrl)
          throws Exception {
    String[] split = picUrl.split("\\.");
    String suffix = split[0] + "_s." + split[1];
    String smallFileName = absolutePath + suffix;
    try {
      Thumbnails.of(multipartFile.getInputStream()).size(180, 180).toFile(smallFileName);
      logger.info("上传文件:" + smallFileName);
    } catch (Exception e) {
      logger.error("上传文件出错", e);
    }
    return smallFileName.replace(absolutePath, "");
  }

  private static void saveFileFromInputStream(InputStream stream, File file) throws Exception {
    FileOutputStream fs = null;
    try {
      fs = new FileOutputStream(file);
      byte[] buffer = new byte[1024 * 1024];
      int byteread = 0;
      while ((byteread = stream.read(buffer)) != -1) {
        fs.write(buffer, 0, byteread);
        fs.flush();
      }
    } catch (Exception e) {
      throw e;
    } finally {
      if (fs != null) {
        fs.close();
      }
    }
  }

  private static String initPathNoDay(String absolutePath) {
    String todayPath =
            absolutePath.endsWith("/") ? absolutePath : absolutePath + "/";
    initDir(todayPath);
    return todayPath;
  }

  /***
   * 初始化路径
   * @param absolutePath
   * @return
   */
  private static String initPath(String absolutePath) {
    String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
    String todayPath =
            absolutePath.endsWith("/") ? absolutePath + today : absolutePath + "/" + today ;
    initDir(todayPath);
    return todayPath;
  }

  private static void initDir(String uploadPath) {
    File directory = new File(uploadPath);
    if (!directory.isDirectory()) {
      directory.mkdirs();
    }
  }

  private static void canUpload(MultipartFile multipartFile, String suffix, Long maxSize,
                                String[] includesuffixs) throws IOException {
    if (multipartFile == null || multipartFile.getSize() == 0) {
      throw new IOException("上传文件不存在");
    }
    //获取文件大小(单位B)字节
    long filsSize = 0;
    if (multipartFile != null) {
      filsSize = multipartFile.getSize();
    }
    //System.out.println("filsSize: " + filsSize);

    //获取最大文件大小(单位M)
    long maxFilsSize = 0;
    if (maxSize != null) {
      maxFilsSize = maxSize.longValue() * 1024 * 1024;
    }
    //System.out.println("maxFilsSize: " + maxFilsSize);

    if (maxSize != null && (filsSize > maxFilsSize) ) {
      throw new IOException("上传文件不能超过  " + maxSize + " M");
    }
    if (includesuffixs == null || includesuffixs.length == 0) {
      return;
    }
    boolean eixstSuffix = false;
    for (String includesuffix : includesuffixs) {
      if (includesuffix.toLowerCase().equals(suffix.toLowerCase())) {
        eixstSuffix = true;
        break;
      }
    }
    if (!eixstSuffix) {
      throw new IOException("上传的文件格式不正确");
    }
  }


  //校验图片上传格式
  public static String checkPictureFormatAndReturnPath(MultipartFile pictureFile,
                                                       String upload_absolute_path, String upload_relative_path) throws Exception {
    String pictureName = pictureFile.getOriginalFilename();
    if (null != pictureName && !pictureName.equals("")) {
      if ((!pictureName.toLowerCase().endsWith(".png")
              && !pictureName.toLowerCase().endsWith(".jpg"))) {
        throw new Exception("文件上传格式不正确!");
      }
      if (null != pictureFile && !pictureFile.isEmpty()) {
        return uploadFile(pictureFile, upload_absolute_path, upload_relative_path, null,
                new String[]{"jpg", "png", "bmp", "gif"});
      }
    }
    return null;
  }

  /***
   * 上传图片，主图mainUrl，缩略图smallUrl
   * @param multipartFile
   * @param absolutePath
   * @param relativePath
   * @param maxSize
   * @param includesuffixs
   * @return
   */
  public static Map upLoadMainAndThumbPicture(MultipartFile multipartFile, String absolutePath,
                                              String relativePath, long maxSize, String[] includesuffixs) throws Exception {
    String mainUrl = uploadFile(multipartFile,absolutePath,relativePath,maxSize,includesuffixs);
    String smallUrl = uploadFileSmall(multipartFile,absolutePath,mainUrl);
    Map resulrMap = new HashMap<>();
    resulrMap.put("mainUrl",mainUrl);
    resulrMap.put("smallUrl",smallUrl);
    return resulrMap;
  }
  /***
   * 上传图片，主图mainUrl，缩略图smallUrl
   * @param bytes
   * @param absolutePath
   * @param relativePath
   * @param maxSize
   * @param includesuffixs
   * @return
   */
  public static Map upLoadMainAndThumbPictureForBase64(byte[] bytes, String absolutePath,
                                              String relativePath, long maxSize, String[] includesuffixs) throws Exception {
    String mainUrl = uploadByteFile(bytes,absolutePath,relativePath,maxSize,includesuffixs);
    String smallUrl = uploadByteFileSmall(absolutePath,mainUrl);
    Map resulrMap = new HashMap<>();
    resulrMap.put("mainUrl",mainUrl);
    resulrMap.put("smallUrl",smallUrl);
    return resulrMap;
  }


}
