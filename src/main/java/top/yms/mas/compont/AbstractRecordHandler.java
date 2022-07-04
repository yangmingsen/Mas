package top.yms.mas.compont;

import com.csvreader.CsvReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import top.yms.mas.entity.RestOut;
import top.yms.mas.utils.DateHelper;
import top.yms.mas.utils.LocalThreadUtils;
import top.yms.mas.utils.POIExcelUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractRecordHandler implements RecordHandler{

    protected static String DOT_SPLIT = "\\.";
    private static String CSV_TYPE="csv";
    private static String XLSX_TYPE="xlsx";
    private static final String XSSF = "org.apache.poi.xssf.usermodel.XSSFWorkbook"; //xlsx
    private static final Logger logger = LoggerFactory.getLogger(AbstractRecordHandler.class);
    private Object lockObj = new Object();

    private String currentType;

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

    public Charset getCharset() {
        return StandardCharsets.UTF_8;
    }

    public  Workbook getWorkbookByCsv(InputStream is)  {
        Workbook workbook;
        try {
            CsvReader reader = new CsvReader(is, ',', getCharset());
            ArrayList<String[]> dataList = new ArrayList<>();
            while (reader.readRecord()) {
                dataList.add(reader.getValues());
            }

            workbook = (XSSFWorkbook) Class.forName(XSSF).newInstance();
            Sheet sheet = workbook.createSheet(Thread.currentThread().getName());//使用线程名字作为sheetName
            for (int rowNum = 0; rowNum < dataList.size(); rowNum++) {
                String[] data = dataList.get(rowNum);
                Row row = sheet.createRow(rowNum);
                for (int columnNum = 0; columnNum < data.length; columnNum++) {
                    Cell cell = row.createCell(columnNum);
                    cell.setCellValue(data[columnNum]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return workbook;
    }

    public abstract RestOut doParseRecord(Workbook workbook, String fileName) throws Exception;

    private Workbook getWorkbook(InputStream is, String fileName) throws Exception{
        if (fileName.endsWith(CSV_TYPE)) {
            currentType = CSV_TYPE;
            return getWorkbookByCsv(is);
        } else {
            currentType = XLSX_TYPE;
            return POIExcelUtil.getWorkbook(is, fileName);
        }
    }

    @Override
    public RestOut doRecordHandler(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Workbook workbook = null;
        synchronized (lockObj) {
            try {
                logger.info("开始解析["+fileName+"]...");
                workbook = getWorkbook(file.getInputStream(), fileName);
                return doParseRecord(workbook, fileName);
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new RuntimeException(e);
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

    protected void saveToDb() {

    }

    public String getSheetName(String fileName) {
        if (currentType.equals(CSV_TYPE)) {
            return Thread.currentThread().getName();
        }

        String[] strs = fileName.split(DOT_SPLIT); // xx.xlsx => [0]:xx, [1]:xlsx
        String sheetName = strs[0];

        return sheetName;
    }

}
