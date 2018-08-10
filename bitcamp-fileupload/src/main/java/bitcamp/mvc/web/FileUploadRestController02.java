package bitcamp.mvc.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/ajax")
public class FileUploadRestController02 {
    
    @Autowired ServletContext sc;
    
    @RequestMapping("upload01")
    public Object upload01(
            String name, 
            String age, 
            MultipartFile[] files) {
        
        System.out.println("upload01()...호출됨!");
        
        HashMap<String,Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        
        ArrayList<String> filenames = new ArrayList<>();
        result.put("filenames", filenames);
        
        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;
                String newfilename = UUID.randomUUID().toString(); 
                String path = sc.getRealPath("/files/" + newfilename);
                file.transferTo(new File(path));
                filenames.add(newfilename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    @RequestMapping("upload02")
    public Object upload02(
            String name, 
            String age, 
            MultipartFile[] files) {
        //여러개 파일을 한번에 받을때는 배열[]로 받는다.
        System.out.println("upload01()...호출됨!");
        
        HashMap<String,Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        
        ArrayList<String> filenames = new ArrayList<>();
        result.put("filenames", filenames);
        
        try {
            // 배열로 받은 파일을 하나씩 하드에 저장한다.
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue; // 파일이없으면 계속~
                String newfilename = UUID.randomUUID().toString(); 
                String path = sc.getRealPath("/files/" + newfilename);
                file.transferTo(new File(path));
                filenames.add(newfilename);
                
                System.out.println(path);
                //들어온 파일을 썸네일로 만듬 
                // 매서드에돟 순서가 있다 of() 1번째 size()2번째 outputFormat("jpg") 3번째 outputFormat() 확장자를 하나로 통일하기위해서
                // 4번째는 toFile(경로와 이름)
                Thumbnails.of(path) // 파일 저장경로
                          .size(20, 20) // 사이즈 20,20으로정해주고
                          .outputFormat("jpg") // 안넣어주면 기본 확장자로 하는데 제각기 확장자가 다르면 문제니 하나로 통일해줌
                          .toFile(path + "_20x20"); //파일을 생성  경로+이름+_20x20으로 생성
                Thumbnails.of(path)
                          .size(80, 80)
                          .outputFormat("jpg")
                          .toFile(path + "_80x80");
                Thumbnails.of(path)
                          .size(120, 120)
                          .outputFormat("jpg")
                          .toFile(path + "_120x120");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}






