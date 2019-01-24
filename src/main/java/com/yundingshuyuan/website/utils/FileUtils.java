package com.yundingshuyuan.website.utils;

import com.yundingshuyuan.website.wrapper.ResultWrapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author leeyf
 * IO_API
 */
public class FileUtils extends IOException {
    /**
     * @author leeyf
     * 图片上传
     */

    public static ResultWrapper saveImage(HttpServletRequest request, MultipartFile imageFile, String imageRealPath) throws IOException {
        //调用suffix方法获得文件后缀
        String suffix = suffix(imageFile);


        // 生成随机文件名
        String uuid = UUID
                .randomUUID()
                .toString()
                .toLowerCase()
                .replace("-", "");
        String filename = uuid + "." + suffix;

        //判断是否为图片
        if (!isImage(suffix)) {
            return ResultWrapper.failure("可上传图片类型：jpg,png,gif,tif,JPEG,bmp");
        } else {
            //用户名下
            imageRealPath = imageRealPath+"/"+request.getAttribute("UserID")+"/";
            String fileRealPath =request.getServletContext().getRealPath(imageRealPath);
            //System.out.println(fileRealPath+"-----------1");
            String filePath =fileRealPath+filename;
            //System.out.println(filePath+"------------2");
            File dest =new File(filePath);
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }

            imageFile.transferTo(dest);

            return ResultWrapper.success(imageRealPath+filename);
        }
    }


    //判断后缀是否为图片后缀
    private static boolean isImage(String suffix) {
        if (    suffix.equals("jpg") ||
                suffix.equals("png") ||
                suffix.equals("gif") ||
                suffix.equals("tif") ||
                suffix.equals("jpeg") ||
                suffix.equals("JPEG") ||
                suffix.equals("bmp")) {
            return true;
        } else {
            return false;
        }
    }

    //获得文件后缀
    private static String suffix(MultipartFile file) {
        // 获取原始文件的后缀

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename
                .substring(originalFilename.lastIndexOf(".") + 1);
        return suffix;
    }

}
