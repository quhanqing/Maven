package myself.utils.file;

import java.io.*;
import java.util.List;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import myself.utils.date.MyDate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    public static void main(String[] args) {

        //String count = myself.utils.file.ExtelUtils.getCaseLineFromExcel("/Users/cuikai/IdeaProjects/GuoMeiProject/TestCase/网贷概览测试用例.xlsx", "debug");
        //System.out.println(count);
        String srcFilePath = System.getProperty("user.dir")+"/TestCase/网贷概览测试用例.xlsx";//System.getProperty("user.dir"),获取当前项目的路径

       // String desFilePath=System.getProperty("user.dir")+"/TestReport/"+ MyDate.getCurrentDate()+"-网贷概览测试用例.xlsx";
        //System.out.println(myself.utils.file.ExtelUtils.copyFile(srcFilePath,desFilePath));
        //myself.utils.file.ExtelUtils.readExcel(srcFilePath,"debug");
        //System.out.println(myself.utils.file.ExtelUtils.getCellValue("test002","是否执行",srcFilePath,"debug"));
        ExtelUtils.testResult(srcFilePath,"debug");
    }

}
class ExtelUtils {
    /**
     * 将测试结果写入Excel
     */
    public static void testResult(String filePath,String sheetName){

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
        int rowNum=sheet.getPhysicalNumberOfRows();
        for(int i=1;i<rowNum;i++){
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(8);
            String expectedResult = cell1.getStringCellValue();
            Cell cell2=row.getCell(12);
            String actualResult=cell2.getStringCellValue();
            String result="Fail";
            if((actualResult != null) && actualResult !="" && actualResult.equals(expectedResult)){
              result = "Pass";
            }

             row.getCell(13).setCellValue(result);
        }

        try {
            fileOutputStream = new FileOutputStream(filePath);
            wb.write(fileOutputStream);
            System.out.println("测试结果填写完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /**
     * 根据sheetName返回Excel文件的行数
     * @param filePath
     * @param sheetName
     * @return
     */
    public  static Sheet getSheetFromExcel(String filePath, String sheetName) {
       // String rowNums = "";
        Workbook wb = null;
        int index = filePath.lastIndexOf(".");
        String fileSuffix = filePath.substring(index, filePath.length());
        if (fileSuffix.equals(".xlsx")) {
            try {
                wb = new XSSFWorkbook(new FileInputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (fileSuffix.equals(".xls")) {
            try {
                wb = new HSSFWorkbook(new FileInputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("所提供的Excel文件格式有误");
        }
        Sheet sheet = wb.getSheet(sheetName);
        int rowCount = wb.getSheet(sheetName).getPhysicalNumberOfRows();
        //rowNums = rowNums + rowNum;

        return sheet;

    }

    /**
     * 复制文件
     * @param srcFilePath
     * @param desFilePath
     */
    public static boolean copyFile(String srcFilePath,String desFilePath){
        boolean status=true;
        File srcFile=new File(srcFilePath);
        File desFile=new File(desFilePath);
        if(srcFile.exists()){
           System.out.println("开始复制"+srcFilePath);
           FileInputStream fileInputStream=null;
           FileOutputStream fileOutputStream=null;

            try {
                 fileInputStream=new FileInputStream(srcFile);
                 fileOutputStream=new FileOutputStream(desFile);
                 int len;
                 byte[] bytes=new byte[1024];
                 while ((len=fileInputStream.read(bytes))!=-1){
                     fileOutputStream.write(bytes);
                 }
                System.out.println(srcFilePath+"文件成功复制");
            } catch (Exception e) {
                e.printStackTrace();
                status=false;
            }
            finally {
                if(fileInputStream!=null){
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(fileOutputStream!=null){
                    try {
                        fileOutputStream.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
        else{
            System.out.println("该文件不存在");
            status=false;
        }
        return status;

    }

    /**
     * 读取excel中某个sheet页中的所有数据
     * @param filePath
     * @param sheetName
     */
    public static void readExcel(String filePath,String sheetName){
        Sheet sheet=getSheetFromExcel(filePath,sheetName);
        int rowCount=sheet.getPhysicalNumberOfRows()-1;
        for(int i=1;i<rowCount;i++){
            Row row=sheet.getRow(i);
            int columCount=row.getPhysicalNumberOfCells();
            for(int j=0;j<columCount;j++){
                Cell cell=row.getCell(j);
                cell.setCellType(CellType.STRING);
                String string=cell.getStringCellValue();
                System.out.print(string+" ");
            }
            System.out.println("\r\n");
        }
    }
    public static String getCellValue(String rowName,String columName,String filePath,String sheetName){
        String string="";
        Sheet sheet=getSheetFromExcel(filePath,sheetName);
        int rowCount=sheet.getPhysicalNumberOfRows()-1;

        for(int i=1;i<rowCount;i++){

            Row row=sheet.getRow(i);
            int columCount=row.getPhysicalNumberOfCells();
            String s=row.getCell(0).getStringCellValue();
            if(rowName.equals(s)) {

                row = sheet.getRow(0);

                for (int j = 0; j < columCount; j++) {

                    Cell cell = row.getCell(j);
                    String b = cell.getStringCellValue();
                    if (columName.equals(b)) {
                        row = sheet.getRow(i);
                        cell = row.getCell(j);
                        string = cell.getStringCellValue();
                        break;
                    }

                }
                break;
            }

        }

        return string;
    }
    /**
     * 根据行名和列明获取Excel单元格的值
     * @param filePath
     * @param fileName
     * @param sheetName
     * @param columnName
     * @param lineName
     * @return
     * @throws Exception
     */
   /* public  String getTestData(String columnName, String lineName) {
        List<String[]> records = null;
        String value = null;
        File file = new File(filePath + "/" + fileName);
        try {
            //创建 FileInputStream 对象用于读取 Excel 文件
            FileInputStream inputStream = new FileInputStream(file);
            //声明 WorkBook 对象
            Workbook workBook = null;
            //获取文件名的扩展名，判断是.xlsx文件还是.xls 文件
            String fileExtensionName = fileName.substring(fileName.indexOf("."));
            //判断如果文件类型是.xlsx，则使用 XSSFWorkbook 对象进行实例化
            //如果是.xls，则使用 HSSFWorkbook 对象进行实例化
            if(fileExtensionName.equals(".xlsx")) {
                workBook = new XSSFWorkbook(inputStream);
            }else if(fileExtensionName.equals(".xls")) {
                workBook = new HSSFWorkbook(inputStream);
            }
            //通过 sheetName 参数，生成 Sheet 对象
            Sheet sheet = workBook.getSheet(sheetName);
            //获取Excel 数据文件 sheet1中的数据的行数，getLastRowNum 方法获取数据的最后一行行号
            //getFirstRowNum 获取数据的第一行行号
            //Excel 文件的行号和列号都是从0开始的
            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            //创建名为 records 的List 对象来存储 Excel 文件读取的数据
            records = new ArrayList<String[]>();
            //使用 for 循环遍历 Excel 文件的所有数据（第一行除外，第一行为字段名称）
            //所以 i 从1开始，而不是从0开始
            for(int i = 0; i<rowCount +1;i++) {
                //使用 getRow 方法获取行对象
                Row row = sheet.getRow(i);
                //声明一个数组，用来存储 Excel 文件每行中的数据
                //数组的大小用 getLastCellNum 办法来进行动态声明，实现测试数据与数组大小一致
                String fields[] = new String[row.getLastCellNum()];
                for(int j = 0;j < row.getLastCellNum(); j++) {
                    //调用 getCell 和 getStringCellValue 方法获取 Excel 文件中的单元格数据
                    //设置单元格类型
                    Cell cell = row.getCell(j);
                    cell.setCellType(CellType.STRING);
                    fields[j] = cell.getStringCellValue();
                }
                //将 fields 数组存储到 records 的 list 中
                records.add(fields);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        int count = 0;
        int index = 0;// 列标
        // focus the columnIndex
        for (String j : records.get(0)) {
            count++;
            if (j.equals(columnName)) {
                index = count - 1;
            }
        }
        // focus the lineName+columnIndex
        for (String[] i : records) {
            if (i[0].equals(lineName))
                value = i[index].toString();
        }

        return value;
    }

    */

}
