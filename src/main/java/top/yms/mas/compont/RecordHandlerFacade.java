package top.yms.mas.compont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.yms.mas.entity.RestOut;

/**
 * 记录处理外观
 */
@Component
public class RecordHandlerFacade {

    @Autowired
    private RecordHandler aliRecordHandler;

    @Autowired
    private RecordHandler wxRecordHandler;

    @Autowired
    private SyncToMaster syncAliToMaster;

    @Autowired
    private SyncToMaster syncWxToMaster;


    public RestOut parseAliRecord(MultipartFile file) {
        return aliRecordHandler.doRecordHandler(file);
    }

    public RestOut parseWxRecord(MultipartFile file) {
        return wxRecordHandler.doRecordHandler(file);
    }


    public RestOut syncAli() {
        return syncAliToMaster.syncRecord();
    }

    public RestOut syncWx() {
        return syncWxToMaster.syncRecord();
    }
}
