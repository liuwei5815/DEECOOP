package com.yvan;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Created by 46368 on 2018/9/20.
 */
public class QRCodeUtils {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int CODE_HEIGHT=300;//默认二维码高度
    private static final int CODE_WIDTH=300;//默认二维码宽度
    public static final String CODE_FORMAT="png";
    public static final  Integer MARGIN = 4;

    public static void create(File file,String content){
        try{
            file.getParentFile().mkdirs();
            create(new FileOutputStream(file), content);
        }
        catch(Exception e){
            throw new RuntimeException("create qrcode exception",e);
        }
    }
    public static void create(OutputStream outputStream,String content){
        create(CODE_WIDTH,CODE_HEIGHT,outputStream, content);
    }

    public static void create(int width,int height,OutputStream outputStream,String content){
        //定义二维码参数
        HashMap hints =  new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//设置编码
        //设置容错等级，等级越高，容量越小
        hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN,1);//设置边距
        //生成矩阵
        BitMatrix bitMatrix = null;
        try{
            bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE, width, height);
            bitMatrix = deleteWhite(bitMatrix);//删除白边
            writeToStream(bitMatrix, CODE_FORMAT, outputStream);
        }
        catch(Exception e){
            throw new RuntimeException("create qrcode exception",e);
        }
    }
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }



    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " +
                    format + " to " + file);
        }
        System.out.println("二维码图片生成成功");
    }

    public static void writeToStream(BitMatrix matrix, String format,
                                     OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            throw new IOException("Could not write an image of format "+format);
        }
    }


    public static void main(String[] args) throws IOException {
        File file=new File("E:/qrcode/"+1+"Q"+"."+QRCodeUtils.CODE_FORMAT);
        file.getParentFile().mkdirs();
        create(new FileOutputStream(file),"1");
    }


}
