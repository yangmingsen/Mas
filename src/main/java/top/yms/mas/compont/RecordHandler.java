package top.yms.mas.compont;

import org.springframework.web.multipart.MultipartFile;
import top.yms.mas.entity.RestOut;


public interface RecordHandler {
    RestOut doRecordHandler(MultipartFile file);
}
