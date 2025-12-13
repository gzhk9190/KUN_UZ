package kun.uz.service;

import kun.uz.dto.AttachDTO;
import kun.uz.entities.AttachEntity;
import kun.uz.repository.AttachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor()
public class AttachService {
    private final AttachRepository attachRepository;

    @Value("${server.url}")
    private String serverUrl;
    @Value("${attach.upload.folder}")
    private String attachFolder;

    public List<AttachDTO> upload(Map<String, MultipartFile> files) {
        String ymd = getYMDString();

        File folder = new File(attachFolder + ymd);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        List<AttachDTO> list = new LinkedList<>();
        files.forEach((s,file)->{
            String extension =getExtension(Objects.requireNonNull(file.getOriginalFilename()));
            AttachEntity attachEntity = saveAttach(ymd,extension,file);
            AttachDTO attachDTO = toDTO(attachEntity);

            try {
                byte [] bytes = file.getBytes();
                Path path = Paths.get(attachFolder + ymd+"/"+attachEntity.getId()+"."+extension);
                Files.write(path, bytes);
                list.add(attachDTO);

            } catch (IOException e) {
                log.warn("Upload Attach Exception = {}",e.getMessage());

            }

        });
        return list;
    }
    public byte[] open_general(String key){
        byte[]data;
        AttachEntity entity = get(key);
        if (entity ==null){
            return new byte[0];
        }
        String path = entity.getPath() + "/" + key + "."+ entity.getExtension();
        Path file = Paths.get(attachFolder + path);
        try {
            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            System.out.println("Error");
            return new byte[0];
        }
    }
    public AttachEntity get(String id){
        return attachRepository.findById(id).orElseThrow(()-> new RuntimeException("Attach Not Found"));
    }
    private String getYMDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DATE);
        return year+"/"+month+"/"+day;
    }
    private String getExtension(String originName){
        int lastIndex = originName.lastIndexOf("\\.");
        return originName.substring(lastIndex+1);
    }
    public AttachDTO toDTO(AttachEntity entity){
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setOriginName(entity.getOriginName());
        dto.setCreatedDate(entity.getCreateDate());
        dto.setExtension(entity.getExtension());
        dto.setUrl(toOpenUrl(entity.getId()));
        return dto;
    }
    public AttachEntity saveAttach(String path, String ext ,MultipartFile file){
        AttachEntity entity = new AttachEntity();
        entity.setPath(path);
        entity.setExtension(ext);
        entity.setOriginName(file.getOriginalFilename());
        entity.setSize(file.getSize());
        return attachRepository.save(entity);
    }
    private String toOpenUrl(String id){
        return serverUrl+"/api/v1/attach/open"+id;
    }
    public Object delete(String fileName){
        AttachEntity entity = get(fileName);
        if (entity ==null){
            return "Attach wasn't found";
        }
        File file = new File(attachFolder + entity.getPath()+"/"+entity.getId()+"."+entity.getExtension());
        if (file.delete()){
            attachRepository.deleteCascade(fileName);
            return "Attach was deleted";
        }else  {
            return "Deletion failed";
        }
    }
    public PageImpl<AttachDTO>getPaginationList(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<AttachEntity>pagination = attachRepository.findAll(pageable);
        List<AttachDTO> list = new ArrayList<>();
        pagination.stream().forEach(entity ->  list.add(toDTO(entity)));
        return new PageImpl<>(list,pageable,pagination.getTotalElements());
    }
}
