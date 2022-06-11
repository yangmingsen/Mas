package top.yms.mas.compont;

import org.springframework.web.multipart.MultipartFile;
import top.yms.mas.entity.RestOut;

import java.io.InputStream;

public interface RecordHandler {
    RestOut doRecordHandler(MultipartFile file);
}
