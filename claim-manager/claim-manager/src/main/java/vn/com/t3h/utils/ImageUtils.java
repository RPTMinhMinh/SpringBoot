package vn.com.t3h.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import vn.com.t3h.service.dto.UserDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ImageUtils {
    @Value("${application.root.folder}")
    private String rootFolderUpload;

    @Value("${application.root.folder.image}")
    private String rootFolderImageUpload;

    public String saveImageAvatar(UserDTO userDTO){

        if (StringUtils.isEmpty(userDTO.getStringBase64Avatar())){
            return "";
        }
        String[] partsBase64 = userDTO.getStringBase64Avatar().split(",");
        String mimeType = partsBase64[0];
        String dataImage = partsBase64[1];
        byte[] decodedByte = Base64.getDecoder().decode(dataImage);
        // xác định đuôi của file
        String fileExtension = "";
        if (mimeType.contains("image/jpeg")){
            fileExtension = ".jpg";
        }else if (mimeType.contains("image/png")){
            fileExtension = ".png";
        }

        String rootFolderImage =  rootFolderUpload + rootFolderImageUpload;
        String fileName = "avatar_" + userDTO.getUsername() + fileExtension;
        String finalPathAvatar = rootFolderImage + fileName;

        File rootFolderData = new File(rootFolderImage);
        if (!rootFolderData.exists()){
            rootFolderData.mkdir();
        }

        try (FileOutputStream fos = new FileOutputStream(new File(finalPathAvatar))){
            fos.write(decodedByte);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            System.out.println("Save file success " + finalPathAvatar);
        }
        return finalPathAvatar;
    }

    public static String convertImageToBase64(String imagePath) {
        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
