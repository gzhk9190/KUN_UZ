package kun.uz.service;

import kun.uz.dto.request.auth.RegistrationRequestDTO;
import kun.uz.dto.response.AttachResponseDTO;
import kun.uz.dto.response.ProfileResponseDTO;
import kun.uz.entities.AttachEntity;
import kun.uz.entities.EmailSMSEntity;
import kun.uz.entities.ProfileEntity;
import kun.uz.enums.ProfileRole;
import kun.uz.enums.ProfileStatus;
import kun.uz.exp.AppForbiddenException;
import kun.uz.exp.EmailAlreadyExistsException;
import kun.uz.exp.ItemNotFoundException;
import kun.uz.exp.PasswordOrEmailWrongException;
import kun.uz.repository.AttachRepository;
import kun.uz.repository.EmailSMSRepository;
import kun.uz.repository.ProfileRepository;
import kun.uz.util.JwtUtil;
import kun.uz.validation.RegistrationValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor()
public class AttachService {
    @Value("${server.url}")
    private String serverUrl;
    @Value("${attach.upload.folder}")
    private String attachFolder;

    @Autowired
    private AttachRepository attachRepository;

    @Value("${server.domain.name}")
    private String domainName;
    public AttachResponseDTO upload(MultipartFile file) {
        String pathFolder = getTmDString();
        File folder = new File(attachFolder + pathFolder);
        if (!folder.exists()){
            folder.mkdirs();
        }

        String key = UUID.randomUUID().toString();
        String extension = getExtension(file.getOriginalFilename());

        AttachEntity entity = saveAttach(key, pathFolder, extension, file);
        try {
            byte[] bytes = file.getBytes();
            Path  path = Paths.get(attachFolder + pathFolder + "/" + key + "." + extension);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toDTO(entity);
    }

    public byte[] open (String fileName){
        byte[] imageInByte;
        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File(attachFolder + fileName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);

            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /** public Resource download (String fileName){
     try {
     Path file = Paths.get("upload/" + fileName);
     Resource resource = new UrlResource(file.toUri());
     if (resource.exists() || resource.isReadable()){
     return resource;
     } else {
     throw new RuntimeException("Could not read the file!");
     }
     } catch (MalformedURLException e) {
     e.printStackTrace();
     throw new RuntimeException("ERROR: " + e.getMessage());
     }
     }*/

    public Resource download (String key){
        try {
            AttachEntity entity = get(key);
            String path = entity.getPath() + "/" + key + entity.getExtension();
            Path file = Paths.get(attachFolder + path);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return (Resource) ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + entity.getOriginName() + "\"").body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR: " + e.getMessage());
        }
    }

    public AttachEntity get(String id){
        return attachRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Attach not found"));
    }
    public byte[] open_general(String key) {
        byte[] data;
        try {
            AttachEntity entity = get(key);
            String path = entity.getPath() + "/" + key + "." + entity.getExtension();
            Path file = Paths.get(attachFolder + path);
            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public  String getTmDString(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day ;
    }

    public String getExtension(String fileName){
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public AttachResponseDTO toDTO(AttachEntity entity){


        return AttachResponseDTO.toDTO(domainName,entity);
    }

    public List<AttachResponseDTO> getList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size , Sort.by(Sort.Direction.DESC, "createdDate"));
        List<AttachResponseDTO> dtoList = new ArrayList<>();
        attachRepository.findAll(pageable).stream().forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }

    public AttachResponseDTO getById(String id) {
        AttachEntity entity = get(id);
        return toDTO(entity);
    }

    public String delete(String id) {
        AttachEntity entity = get(id);
        File file = new File(attachFolder + entity.getPath() + "/" + entity.getId() + "." + entity.getExtension());
        if (file.delete()){
            attachRepository.delete(entity);
            return "Success";
        } else {
            throw new AppForbiddenException("Could not read file!");
        }

    }

    public String toOpenURL(String id){
        return domainName + "/attach/open_general/" + id;
    }

    public AttachResponseDTO toOpenURLDTO(String id) {
        return new AttachResponseDTO(domainName + "/attach/open_general/" + id);
    }
    public AttachEntity saveAttach(String key, String pathFolder, String extension, MultipartFile file) {
        AttachEntity entity = new AttachEntity();
        entity.setId(key);
        entity.setPath(pathFolder);
        entity.setOriginName(file.getOriginalFilename());
        entity.setExtension(extension);
        entity.setSize(file.getSize());
        attachRepository.save(entity);
        return entity;
    }
}
