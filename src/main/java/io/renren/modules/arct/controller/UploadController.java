package io.renren.modules.arct.controller;
import io.renren.common.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    @Value("${uploadimgpath}")
    private String dirPath ;

    @PostMapping("/api/upload")
    @ResponseBody
    public Map<String,Object> SingleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        Map<String,Object> res = null;
        System.out.println("请求了");
        try{
            String dirpath = this.dirPath;
            String fileName = FileUtils.saveSingleFile(dirpath,file);
            String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/renren-fast/images/"+fileName;
            String name = fileName;
            res = new HashMap<>(2);
            res.put("url",url);
            res.put("name",name);
            res.put("code",1);
        }catch (Exception e){
            res = new HashMap<>(2);
            res.put("code",0);
        }
        return res;
    }
}
