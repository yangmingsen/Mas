package top.yms.mas.compont;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import top.yms.mas.entity.RestOut;

@Component
public class WxRecordHandler extends AbstractRecordHandler{



    @Override
    public RestOut doParseRecord(Workbook workbook, String fileName) {
        return null;
    }
}
