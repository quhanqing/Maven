package myself.utils.lianxi;

import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class OutputExcel {
    public static void main(String[] args){
   String str0="{ \"_id\" : \"a164081b9a351db884334a6d\", \"appointment\" : { \"_id\" : \"enJAEDouEiALid6Fv\", \"createdAt\" : { \"$date\" : 1505705585388 }, " +
           "\"status\" : 1, \"isOutPatient\" : false, \"patientId\" : \"a164081b9a351db884334a6d\", \"nickname\" : \"张连生\", \"mobile\" : \"13520364531\", " +
           "\"source\" : \"outpatientFollowup\", \"isTelephoneFollowUp\" : false, \"blood\" : true, \"insulinAt\" : true, \"type\" : \"quarter\", " +
           "\"appointmentTime\" : { \"$date\" : 1515369600000 }, \"treatmentStateId\" : \"zziPAbRaBMKXzLaQ2\", \"hisNumber\" : \"102124342000\", " +
           "\"note\" : \"原12月25日改期到1月8日；12月22日拨打显示空号；12月20日拨打是空号；眼底有出血，在同仁打激光。8.23；8.30已停机。9.6停机\", \"idCard\" : null," +
           " \"updatedAt\" : { \"$date\" : 1515835764563 }, \"healthCareTeamId\" : \"healthCareTeam1\", \"institutionId\" : \"BEIDAYIYUAN\", " +
           "\"confirmStatus\" : \"weChatConfirm\", \"expectedTime\" : { \"$date\" : 1513741052874 }, \"patientState\" : \"ARCHIVED\", \"timeSegments\" : \"morning\" } }";
   String str="{ _id : a164081b9a351db884334a6d, appointment: { _id : enJAEDouEiALid6Fv, createdAt: { $date : 1505705585388 }" +
           ", status : 1, isOutPatient: false, patientId: a164081b9a351db884334a6d, nickname : 张连生" +
           ", mobile : 13520364531, source : outpatientFollowup, isTelephoneFollowUp : false, blood : true, insulinAt : true" +
           ", type : quarter, appointmentTime : { $date : 1515369600000 }, treatmentStateId : zziPAbRaBMKXzLaQ2, hisNumber : 102124342000" +
           ", note : 原12月25日改期到1月8日；12月22日拨打显示空号；12月20日拨打是空号；眼底有出血，在同仁打激光。8.23；8.30已停机。9.6停机" +
           ", idCard: null, updatedAt : { $date : 1515835764563 }, healthCareTeamId: healthCareTeam1, institutionId: BEIDAYIYUAN" +
           ", confirmStatus : weChatConfirm, expectedTime : { $date: 1513741052874 }, patientState : ARCHIVED" +
           ", timeSegments : morning } }";
   String desfilePath="/Users/cuikai/IdeaProjects/GuoMeiProject/TestCase/网贷概览测试用例.xlsx";
   String sheetname="Test";
   JsonToExcel.output(desfilePath,sheetname,str0);
    }
}
class JsonToExcel{
    public static void output(String filePath,String sheetName,String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        String appointment=jsonObject.getString("appointment");
        JSONObject jsonObject1=JSONObject.fromObject(appointment);
        String nickname=jsonObject1.getString("nickname");
        String mobile=jsonObject1.getString("mobile");

        Workbook wb=null;
        int index=filePath.lastIndexOf(".");
        String fileSuffix=filePath.substring(index,filePath.length());
        if(fileSuffix.equals(".xlsx")){
            try {
                wb=new XSSFWorkbook(new FileInputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if(fileSuffix.equals(".xls")){
            try {
                wb=new HSSFWorkbook(new FileInputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("所提供的Excel文件有误");
        }


        FileOutputStream fileOutputStream=null;
        Sheet sheet=wb.getSheet(sheetName);
        Row row=sheet.createRow(1);
        Cell cell1=row.createCell(1);
        Cell cell2=row.createCell(2);

        cell1.setCellValue(nickname);
        cell2.setCellValue(mobile);
        try {
            fileOutputStream=new FileOutputStream(filePath);
            wb.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
       finally {
            if(fileOutputStream!=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
