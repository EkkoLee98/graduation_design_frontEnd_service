package io.renren.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileUtils {
    private final static String PREFFIX = "img_";
    //随机数
    private final static Random random = new Random();
    //图片类型
    private final static HashMap<String,String> img = new HashMap<>();

    static {
        img.put(".jpe", "image/jpeg");
        img.put(".jpeg", "image/jpeg");
        img.put(".jpg", "image/jpeg");
        img.put(".bmp", "image/bmp");
        img.put(".png", "image/png");
        img.put(".gif", "image/gif");
    }

    public static ArrayList<String> saveMultiUploadFile(String savePath,MultipartFile[] files){
        ArrayList<String> namelist = new ArrayList<>(files.length);
        for (MultipartFile file : files) {
          namelist.add(saveSingleFile(savePath,file));
        }
        return namelist;
    }

    public static String saveSingleFile(String savePath,MultipartFile file){
        //获取文件原始名 文件后缀
        String originaName = null,suffix =null;
        if (null != file && !file.isEmpty() && (!StringUtils.isEmpty(img.get("."+ (suffix = FilenameUtils.getExtension(originaName = file.getOriginalFilename())))))) {
            System.out.println(originaName);

//             String suffix = FilenameUtils.getExtension(originaName);
             //时间戳
             String timestamp = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss", TimeZone.getDefault());

             //文件前面的名字
             String prename = PREFFIX+timestamp+random.nextInt(9999);

             //文件名
             String filename =prename+"."+suffix;

             File savefile = new File(savePath+filename);

             if (!savefile.getParentFile().exists()) {
                 savefile.getParentFile().mkdir();
             }

             try {
                 System.out.println("==========================");
                 System.out.println(savefile);
                 System.out.println("==========================");
                 //保存文件
                 file.transferTo(savefile);
             } catch (IOException e) {
                 e.printStackTrace();

             }

             return filename;
        }else {
            throw new IllegalArgumentException("文件为空或者文件类型不正确");
        }
    }

//    public static void main(String[] args) {
//        HashMap<Object, Object> map = new HashMap<>();
//    }
}
