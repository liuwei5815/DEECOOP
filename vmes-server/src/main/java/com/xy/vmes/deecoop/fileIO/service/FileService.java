package com.xy.vmes.deecoop.fileIO.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by 46368 on 2018/9/20.
 */
public interface FileService {
//
    String uploadPhoto(String photoDir ,MultipartFile file) throws Exception;

    String createQRCode(String qrCodeDir ,String content) throws Exception;
}
