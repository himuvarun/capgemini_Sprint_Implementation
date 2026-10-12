package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    public String getDataByTestId(String sheetName, String testId, String columnName)
            throws IOException {

        FileInputStream file = new FileInputStream("src/test/resources/TestData.xlsx");
        Workbook workbook = WorkbookFactory.create(file);

        Sheet sheet = workbook.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();

        Row header = sheet.getRow(0);
        int idColumn = -1;
        int dataColumn = -1;

        for (int i = 0; i < header.getLastCellNum(); i++) {

            String headerName = formatter.formatCellValue(header.getCell(i));

            if (headerName.equalsIgnoreCase("TC_ID")) {
                idColumn = i;
            }

            if (headerName.equalsIgnoreCase(columnName)) {
                dataColumn = i;
            }
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            String currentId = formatter.formatCellValue(row.getCell(idColumn));

            if (currentId.equalsIgnoreCase(testId)) {
                String value = formatter.formatCellValue(row.getCell(dataColumn));

                workbook.close();
                file.close();

                return value.trim();
            }
        }

        workbook.close();
        file.close();

        throw new IOException("Test ID not found: " + testId);
    }
}