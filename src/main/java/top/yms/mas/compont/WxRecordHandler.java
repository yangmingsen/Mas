package top.yms.mas.compont;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yms.mas.entity.*;
import top.yms.mas.mapper.WxExpendMapper;
import top.yms.mas.mapper.WxExpendOtherMapper;
import top.yms.mas.mapper.WxIncomeMapper;
import top.yms.mas.utils.POIExcelUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WxRecordHandler extends AbstractRecordHandler {

    private static final Logger logger = LoggerFactory.getLogger(WxRecordHandler.class);
    private static final String INCOME = "收入";
    private static final String EXPEND = "支出";

    @Autowired
    private WxIncomeMapper wxIncomeMapper;

    @Autowired
    private WxExpendMapper wxExpendMapper;

    @Autowired
    private WxExpendOtherMapper wxExpendOtherMapper;

    private WxExpend parseWXCells(Cell[] cells, WxExpend wxExpend) throws Exception {
        Cell cell0 = cells[0];
        Cell cell1 = cells[1];
        Cell cell2 = cells[2];
        Cell cell3 = cells[3];
        Cell cell4 = cells[4];
        Cell cell5 = cells[5];
        Cell cell6 = cells[6];
        Cell cell7 = cells[7];
        Cell cell8 = cells[8];
        //Cell cell9 = row.getCell(9);
        Cell cell10 = cells[10];

        wxExpend.setId(getCellStr(cell8));
        wxExpend.setCounterparty(getCellStr(cell2));
        wxExpend.setTitle(getCellStr(cell3));
        wxExpend.setPaymentMethod(getCellStr(cell6));
        wxExpend.setCategory(getCellStr(cell1));

        Date expendTime = getDateTime(POIExcelUtil.getCellValueByCell(cell0));
        wxExpend.setTxTime(expendTime);

        //交易金额判断
        String amountStr = getCellStr(cell5);
        if (StringUtils.isBlank(amountStr)) {
            throw new Exception("订单号[" + wxExpend.getId() + "] 金额为空");
        }
        try {
            new Double(amountStr);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        wxExpend.setAmount(new BigDecimal(amountStr));

        return wxExpend;

    }

    public void toWxExpend(Cell[] cells, Map<String, Object> map) throws Exception {
        WxExpend wxExpend = new WxExpend();
        parseWXCells(cells, wxExpend);
        map.put(wxExpend.getId(), wxExpend);
    }

    public void toWxIncome(Cell[] cells, Map<String, Object> map) throws Exception {
        WxIncome wxIncome = new WxIncome();
        parseWXCells(cells, wxIncome);
        map.put(wxIncome.getId(), wxIncome);
    }

    public void toWxExpendOther(Cell[] cells, Map<String, Object> map) throws Exception {
        WxExpendOther expendOther = new WxExpendOther();
        parseWXCells(cells, expendOther);
        map.put(expendOther.getId(), expendOther);
    }




    @Override
    public RestOut doParseRecord(Workbook workbook, String fileName) throws Exception {
        String sheetName = getSheetName(fileName);
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            String errInfo = sheetName + " 未找到!!!";
            logger.error(errInfo);
            return RestOut.error(errInfo);
        }

        Map<String, Object> expendMap = new HashMap<>();
        Map<String, Object> incomeMap = new HashMap<>();
        Map<String, Object> otherMap = new HashMap<>();

        int totalRows = sheet.getPhysicalNumberOfRows();
        logger.info("开始执行Wx解析...总行数["+totalRows+"]");
        // wx的从第17行开始
        for (int i = 17; i < totalRows; i++) {
            Row row = sheet.getRow(i);//拿到某一行

            //0=>交易时间, 1=>交易类型, 2=>交易对方, 3=>商品, 4=>收/支, 5=> 金额(元)
            //6=>支付方式, 7=>当前状态, 8=>交易单号, 9=> ...., 10=>备注

            //收支方式
            String inOrOut = getCellStr(row.getCell(4));
            if (StringUtils.isBlank(inOrOut)) break;

            Cell[] cells = new Cell[11];
            for (int j = 0; j < 11; j++) {
                cells[j] = row.getCell(j);
            }

            if (inOrOut.contains(INCOME)) {
                toWxIncome(cells, incomeMap);
            } else if (inOrOut.contains(EXPEND)) {
                toWxExpend(cells, expendMap);
            } else {
                toWxExpendOther(cells, otherMap);
            }

        }
        logger.info("所有数据解析完毕...");

        //mapPrint(expendMap);
        writeIncome(incomeMap);
        writeExpend(expendMap);
        writeExpendOther(otherMap);


        return RestOut.success("Ok");
    }

    private void writeIncome(Map<String, Object> maps) {
        for(Map.Entry<String, Object> map : maps.entrySet()) {
            String id = map.getKey();
            WxIncome newValue = (WxIncome)map.getValue();
            WxIncome oldValue = wxIncomeMapper.selectByPrimaryKey(id);
            if (oldValue == null) {
                wxIncomeMapper.insert((newValue));
            }
        }
    }

    private void writeExpend(Map<String, Object> maps) {
        for(Map.Entry<String, Object> map : maps.entrySet()) {
            String id = map.getKey();
            WxExpend newValue = (WxExpend)map.getValue();
            WxExpend oldValue = wxExpendMapper.selectByPrimaryKey(id);
            if (oldValue == null) {
                wxExpendMapper.insert((newValue));
            }
        }
    }

    private void writeExpendOther(Map<String, Object> maps) {
        for(Map.Entry<String, Object> map : maps.entrySet()) {
            String id = map.getKey();
            WxExpendOther newValue = (WxExpendOther)map.getValue();
            WxExpendOther oldValue = wxExpendOtherMapper.selectByPrimaryKey(id);
            if (oldValue == null) {
                wxExpendOtherMapper.insert((newValue));
            }
        }
    }


}
