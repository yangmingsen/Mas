package top.yms.mas.compont;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import top.yms.mas.entity.RestOut;
import top.yms.mas.utils.DateHelper;
import top.yms.mas.utils.POIExcelUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public abstract class AbstractRecordHandler implements RecordHandler{

    protected  String DOT_SPLIT = "\\.";

    private static final Logger logger = LoggerFactory.getLogger(AbstractRecordHandler.class);


    public static void mapPrint(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public  String getCellStr(Cell cell) {
        return POIExcelUtil.getCellValueByCell(cell).trim();
    }


    public Date getDateTime(String dateStr) {
        return DateHelper.strToDateTime(dateStr);
    }

    public abstract RestOut doParseRecord(Workbook workbook, String fileName) throws Exception;

    @Override
    public RestOut doRecordHandler(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            logger.info("开始解析["+fileName+"]...");
            workbook = POIExcelUtil.getWorkbook(file.getInputStream(), fileName);
            return doParseRecord(workbook, fileName);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return RestOut.error(e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            logger.info("解析["+fileName+"]结束...");
        }
    }
}
