package realtimeprog;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelDisplay
{
    private static CompareList compareList = new CompareList();
    private static final String FILE_NAME = "C:/Users/vissa/A191 RealTime/Assigment1.xlsx";
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    private static XSSFSheet sheet1 = workbook.createSheet("Students Submit");
    private static XSSFSheet sheet2 = workbook.createSheet("Students Not Submit");
    private static XSSFSheet sheet3 = workbook.createSheet("Unknown List");

    public static void main(String[] args )
    {
        compareList.main(args);

        Object[][] d1 = CompareList.studDet;
        Object[][] d2 = CompareList.studNum;
        Object[][] d3 = CompareList.studlin;

        int cRow =0;
        System.out.println("Creating Excel File");
        rowList(sheet1, d1, cRow);
        rowList(sheet2, d2, cRow);
        rowList(sheet3, d3, cRow);

        try
        {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("DONE");
    }
    static void rowList(XSSFSheet sheet, Object[][] data, int cRow) {
        for (Object[] datatype : data) {
            Row row = sheet.createRow(cRow++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                sheet.autoSizeColumn(cRow);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        for (int x = 0; x < sheet.getRow(0).getPhysicalNumberOfCells(); x++) {
            sheet.autoSizeColumn(x);
        }
    }



}
