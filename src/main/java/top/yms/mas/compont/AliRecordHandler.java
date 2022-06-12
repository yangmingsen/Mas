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
import org.springframework.transaction.annotation.Transactional;
import top.yms.mas.entity.*;
import top.yms.mas.mapper.AliExpendMapper;
import top.yms.mas.mapper.AliExpendOtherMapper;
import top.yms.mas.mapper.AliExpendTmpMapper;
import top.yms.mas.mapper.AliIncomeMapper;
import top.yms.mas.utils.POIExcelUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class AliRecordHandler extends AbstractRecordHandler{

    private static final Logger logger = LoggerFactory.getLogger(AliRecordHandler.class);

    private static final String PAY_OUT = "支出";
    private static final String PAY_OHER = "其他";
    private static final String WAIT_FOR_RECV = "等待确认收货";
    private static final String TX_CLOSE = "交易关闭";
    private static final String TX_SUCCESS = "交易成功";
    private static final String PAYMENT_SUCCESS = "支付成功";
    private static final String RETURN_SUCCESS = "退款成功";
    private static final String AUTO_RETURN = "自动还款";
    private static final String UNFREEZE_SUCCESS = "解冻成功";
    private static final String CREDIT_SERVICE_USE_SUCCESS = "信用服务使用成功";

    @Autowired
    private AliExpendMapper aliExpendMapper;

    @Autowired
    private AliExpendOtherMapper aliExpendOtherMapper;

    @Autowired
    private AliExpendTmpMapper aliExpendTmpMapper;

    @Autowired
    private AliIncomeMapper aliIncomeMapper;


    private AliExpend parseAliCells(Cell[] cells, AliExpend aliExpend) throws Exception {
        String cell0Str = cells[0].getStringCellValue();

        Cell cell1 = cells[1];
        //Cell cell2 = row.getCell(2);
        Cell cell3 = cells[3];
        Cell cell4 = cells[4];
        Cell cell5 = cells[5];
        Cell cell6 = cells[6];
        Cell cell7 = cells[7];
        Cell cell8 = cells[8];
        //Cell cell9 = row.getCell(9);
        Cell cell10 = cells[10];


        //AliExpend aliExpend = new AliExpend();
        aliExpend.setId(getCellStr(cell8)); //订单id
        aliExpend.setCounterparty(getCellStr(cell1)); //交易对方
        aliExpend.setTitle(getCellStr(cell3)); //交易名称
        aliExpend.setPaymentMethod(getCellStr(cell4)); //交易方式

        //交易金额判断
        String amountStr = getCellStr(cell5);
        if (StringUtils.isBlank(amountStr)) {
            throw new Exception("订单号[" + aliExpend.getId() + "] 金额为空");
        }
        try {
            new Double(amountStr);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        aliExpend.setAmount(new BigDecimal(amountStr)); //交易金额

        aliExpend.setCategory(getCellStr(cell7)); //交易分类

        Date expendTime = getDateTime(POIExcelUtil.getCellValueByCell(cell10));
        aliExpend.setTxTime(expendTime); // 交易时间

        return aliExpend;
    }

    public void toIncomeSuccess(Cell [] cells, Map<String, Object> map) throws Exception {
        AliIncome aliIncome = new AliIncome();
        parseAliCells(cells, aliIncome);
        map.put(aliIncome.getId(), aliIncome);
    }

    public  void toExpendSuccess(Cell[] cells, Map<String, Object> map) throws Exception {
        AliExpend aliExpend = new AliExpend();
        parseAliCells(cells, aliExpend);
        //add
        map.put(aliExpend.getId(), aliExpend);

    }

    public void toTmpExpend(Cell[] cells, Map<String, Object> map) throws Exception {
        //0 => 收/支(支出,其他) , 1=> 交易对方, 2 => 对方账号, 3 => 商品说明, 4=> 收/付款方式,
        //5=> 金额, 6=> 交易状态, 7=> 交易分类, 8=> 订单号, 9=>商家订单号, 10=> 交易时间
        AliExpendTmp aliExpendTmp = new AliExpendTmp();
        //解析
        parseAliCells(cells, aliExpendTmp);

        //交易状态
        aliExpendTmp.setTxStatus(getCellStr(cells[6]));

        map.put(aliExpendTmp.getId(), aliExpendTmp);

    }

    public void toOtherExpend(Cell[] cells, Map<String, Object> map) throws Exception {
        AliExpendOther aliExpendOther = new AliExpendOther();

        parseAliCells(cells, aliExpendOther);

        //交易状态
        aliExpendOther.setTxStatus(getCellStr(cells[6]));

        map.put(aliExpendOther.getId(), aliExpendOther);
    }


    @Override
    public RestOut doParseRecord(Workbook workbook, String fileName) throws Exception{
        String sheetName = getSheetName(fileName);
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            String errInfo = sheetName + " 未找到!!!";
            logger.error(errInfo);
            return RestOut.error(errInfo);
        }

        Map<String, Object> incomeMap = new HashMap<>(); //收入表
        Map<String, Object> expendMap = new HashMap<>(); //支出表
        Map<String, Object> otherExpendMap = new HashMap<>(); //其他交易数据
        Map<String, Object> tmpExpendMap = new HashMap<>(); //零时不重要的数据

        int totalRows = sheet.getPhysicalNumberOfRows();
        logger.info("开始执行Ali解析...总行数["+totalRows+"]");
        // ali的从第3行开始
        for (int i = 2; i < totalRows; i++) {
            Row row = sheet.getRow(i);//拿到某一行

            //0 => 收/支(支出,其他) , 1=> 交易对方, 2 => 对方账号, 3 => 商品说明, 4=> 收/付款方式,
            //5=> 金额, 6=> 交易状态, 7=> 交易分类, 8=> 订单号, 9=>商家订单号, 10=> 交易时间

            Cell cell0 = row.getCell(0);
            if (cell0 == null || StringUtils.isBlank(cell0.getStringCellValue()) || cell0.getStringCellValue().startsWith("---------"))
                break;
            //收入/支出
            String cell0Str = cell0.getStringCellValue();

            Cell[] cells = new Cell[11];
            for (int j = 0; j < 11; j++) {
                cells[j] = row.getCell(j);
            }

            //交易状态
            String cell6Str = getCellStr(cells[6]);
            if (cell0Str.contains(PAY_OUT)) {
                if (cell6Str.contains(TX_SUCCESS) || cell6Str.contains(PAYMENT_SUCCESS)) {
                    toExpendSuccess(cells, expendMap);
                } else if (cell6Str.contains(WAIT_FOR_RECV) || cell6Str.contains(TX_CLOSE)) {
                    toOtherExpend(cells, otherExpendMap);
                } else {
                    throw new Exception("支出异常交易状态[" + cell6Str + "] 订单号[" + getCellStr(cells[8]) + "]");
                }

            } else if (cell0Str.contains(PAY_OHER)) {
                if (cell6Str.contains(TX_SUCCESS)) {
                    //只有是投资支出计入支出，其他出了收益都入零时表
                    String txTarget = getCellStr(cells[1]); //交易对象
                    String txTitle = getCellStr(cells[3]); //交易名称
                    if(txTarget.contains("蚂蚁财富") && txTitle.contains("买入")) {
                        toExpendSuccess(cells, expendMap);
                    } else if ((txTarget.contains("天弘基金管理有限公司") && txTitle.contains("收益发放")) ||
                            (txTarget.contains("蚂蚁财富") && txTitle.contains("卖出至余额宝"))
                    ) {
                        //入收益表
                        toIncomeSuccess(cells, incomeMap);
                    } else {
                        toTmpExpend(cells, tmpExpendMap);
                    }
                } else if (cell6Str.contains(UNFREEZE_SUCCESS) || cell6Str.contains(TX_CLOSE)) {
                    toOtherExpend(cells, otherExpendMap);
                } else if (cell6Str.contains(CREDIT_SERVICE_USE_SUCCESS)){
                    toOtherExpend(cells, otherExpendMap);
                } else {
                    toTmpExpend(cells, tmpExpendMap);
                }

            } else {
                throw new Exception(row.toString());
            }

        }
        logger.info("所有数据解析完毕...");

        //mapPrint(incomeMap);
        logger.info("开始写入到Db");
        writeExpend(expendMap);
        writeIncome(incomeMap);
        writeExpendTmp(tmpExpendMap);
        writeExpendOther(otherExpendMap);
        logger.info("写入到Db完毕....");

        //输出到Db

        return RestOut.success("Ok");
    }

    private void writeExpendOther(Map<String, Object> otherExpendMap) {
        for(Map.Entry<String, Object> map : otherExpendMap.entrySet()) {
            String id = map.getKey();
            AliExpendOther newValue = (AliExpendOther)map.getValue();
            AliExpendOther oldValue = aliExpendOtherMapper.selectByPrimaryKey(id);
            if (oldValue == null) {
                aliExpendOtherMapper.insert((newValue));
            }
        }
    }

    private void writeExpendTmp(Map<String, Object> tmpExpendMap) {
        for(Map.Entry<String, Object> map : tmpExpendMap.entrySet()) {
            String id = map.getKey();
            AliExpendTmp newValue = (AliExpendTmp)map.getValue();
            AliExpendTmp oldValue = aliExpendTmpMapper.selectByPrimaryKey(id);
            if (oldValue == null) {
                aliExpendTmpMapper.insert((newValue));
            }
        }
    }

    private void writeIncome(Map<String, Object> incomeMap) {
        for(Map.Entry<String, Object> map : incomeMap.entrySet()) {
            String id = map.getKey();
            AliIncome newValue = (AliIncome)map.getValue();
            AliIncome oldValue = aliIncomeMapper.selectByPrimaryKey(id);
            if (oldValue == null) {
                aliIncomeMapper.insert((newValue));
            }
        }
    }


    public void writeExpend(Map<String, Object> expendMap) {
        for(Map.Entry<String, Object> map : expendMap.entrySet()) {
            String id = map.getKey();
            AliExpend newValue = (AliExpend)map.getValue();
            AliExpend oldValue = aliExpendMapper.selectByPrimaryKey(id);
            if (oldValue == null) {
                aliExpendMapper.insert((newValue));
            }
        }
    }




}
