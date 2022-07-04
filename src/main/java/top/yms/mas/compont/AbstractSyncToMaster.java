package top.yms.mas.compont;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.yms.mas.entity.RestOut;
import top.yms.mas.mapper.MyExpendMapper;

import java.util.List;

public abstract class AbstractSyncToMaster implements SyncToMaster {
    private static final Logger logger = LoggerFactory.getLogger(AbstractSyncToMaster.class);

    @Autowired
    protected MyExpendMapper expendMapper;

    protected static String SYNC_FLAG = "0";

    public abstract RestOut doSync();

    @Override
    public RestOut syncRecord() {
        try {
            return doSync();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
