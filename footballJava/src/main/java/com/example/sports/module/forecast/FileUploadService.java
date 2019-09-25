package com.example.sports.module.forecast;

import com.example.sports.module.baseData.Const;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class FileUploadService {




    /**
     * 通过上传的文件名，缓冲到本地，后面才能解压、验证
     * @param file
     */
    public boolean createLocalFile(MultipartFile file) {
        String filePath = Const.File_PATH;
        File localFile = new File(filePath);
        //先创建目录
        localFile.mkdirs();

        String originalFilename = file.getOriginalFilename();
        String path = filePath+"/"+originalFilename;

        localFile = new File(path);
        FileOutputStream fos = null;
        InputStream in = null;
        try {

            if(localFile.exists()){
                //如果文件存在删除文件
                boolean delete = localFile.delete();
                if (delete == false){
                    System.out.println("delete false");
                }
            }
            //创建文件
            if(!localFile.exists()){
                //如果文件不存在，则创建新的文件
                localFile.createNewFile();
            }

            //创建文件成功后，写入内容到文件里
            fos = new FileOutputStream(localFile);
            in = file.getInputStream();
            byte[] bytes = new byte[1024];
            int len = -1;
            while((len = in.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(fos != null) {
                    fos.close();
                }
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }


}
