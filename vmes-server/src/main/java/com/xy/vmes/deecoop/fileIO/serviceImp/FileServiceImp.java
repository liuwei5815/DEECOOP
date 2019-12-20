package com.xy.vmes.deecoop.fileIO.serviceImp;

import com.yvan.common.util.Common;
import com.xy.vmes.deecoop.fileIO.service.FileService;
import com.yvan.FileUploadUtils;
import com.yvan.QRCodeUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by 46368 on 2018/9/20.
 */
@Service
public class FileServiceImp implements FileService {


    @Override
    public String uploadPhoto(String photoDir,MultipartFile file) throws Exception {
        //上传文件的绝对路径
        String absolutePath = "";

        String os = System.getProperty("os.name");
        if(os != null && os.indexOf("Windows") >= 0) {
            String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();
            if(path.indexOf(":") != 1){
                path = File.separator + path;
            }
            //本地window环境:文件根路径
            absolutePath = path+"vmes-file/";
        } else {
            //真实linux环境:文件根路径
            absolutePath = Common.SYS_LINUX_FILE_ROOT;
        }

        String relativePath = "fileUpload/Photo/"+photoDir;//上传文件的相对路径
        String[] includesuffixs = new String[3];//上传的文件类型，包括jpg、png、jpeg
        includesuffixs[0]="jpg";
        includesuffixs[1]="png";
        includesuffixs[2]="jpeg";
        String photoUrl = FileUploadUtils.uploadFile(file,absolutePath,relativePath, Long.valueOf(5),includesuffixs);

        return photoUrl;
    }

    @Override
    public String createQRCode(String qrCodeDir, String content) throws Exception {
        //上传文件的绝对路径
        String absolutePath = "";

        String os = System.getProperty("os.name");
        if(os != null && os.indexOf("Windows") >= 0) {
            String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();
            if(path.indexOf(":") != 1){
                path = File.separator + path;
            }
            //本地window环境:文件根路径
            absolutePath = path+"vmes-file/";
        } else {
            //真实linux环境:文件根路径
            absolutePath = Common.SYS_LINUX_FILE_ROOT;
        }

        String relativePath = "fileUpload/QRCode/"+qrCodeDir+"/";//上传文件的相对路径
        String fileName = new Long(System.currentTimeMillis()).toString() + UUID.randomUUID().toString().substring(0, 3)+"."+QRCodeUtils.CODE_FORMAT;
        File QRCode = new File(absolutePath+relativePath+fileName);
        if(!QRCode.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
            QRCode.getParentFile().mkdirs();				//不存在就全部创建
        }
        QRCodeUtils.create(new FileOutputStream(QRCode),content);
        return relativePath+fileName;
    }


//    public static void main(String[] args) throws Exception {
//
//    }

//    public static void main(String[] args) throws Exception {
////        File destFile = new File("D://test111/test222/test333/test.txt");
////        if(!destFile.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
////            destFile.getParentFile().mkdirs();				//不存在就全部创建
////        }
//        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();
//        if(path.indexOf(":") != 1){
//            path = File.separator + path;
//        }
//
//        String absolutePath = path+"vmes-file/";//上传文件的绝对路径
//        String relativePath = "fileUpload/QRCode/equipment/";//上传文件的相对路径
//        String fileName = new Long(System.currentTimeMillis()).toString() + UUID.randomUUID().toString().substring(0, 3)+"."+QRCodeUtils.CODE_FORMAT;
//        File QRCode = new File(absolutePath+relativePath+fileName);
//        if(!QRCode.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
//            QRCode.getParentFile().mkdirs();				//不存在就全部创建
//        }
//        QRCodeUtils.create(new FileOutputStream(QRCode),"123");
//
//    }
}
