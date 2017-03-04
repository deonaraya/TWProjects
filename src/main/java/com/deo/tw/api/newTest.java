package com.deo.tw.api;

/**
 * Created by chandrad on 4/18/16.
 */

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
public class newTest {



        String[][] tabArray = null;
        Workbook workbk;
        Sheet sheet;
        int rowCount, colCount;
        String sheetPath = "D:\\Download\\Auto_Increment.xls";

        // Excel API to read test data from excel workbook
        public String[][] getExcelData(String xlPath, String shtName)
                throws Exception {
            Workbook workbk = Workbook.getWorkbook(new File(xlPath));
            Sheet sht = workbk.getSheet(shtName);
            rowCount = sht.getRows();
            colCount = sht.getColumns();
            tabArray = new String[rowCount][colCount - 2];
            System.out.println("erow: " + rowCount);
            System.out.println("ecol: " + colCount);
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < 3; j++) {
                    tabArray[i][j] = sht.getCell(j, i).getContents();
                }
            }
            return (tabArray);
        }

        @DataProvider
        public Object[][] getLoginData() throws Exception {
            Object[][] retObjArr = getExcelData(sheetPath, "Sheet1");
            System.out.println("getData function executed!!");
            return retObjArr;
        }

        @Test(dataProvider = "getLoginData")
        public void LoginData(String distID, String asmtId, String studID)
                throws InterruptedException, BiffException, IOException {
            Administartion(distID, asmtId, studID);
        }

        public void Administartion(String distID, String asmtId, String studID)
                throws BiffException, IOException {
            Workbook workbk = Workbook.getWorkbook(new File(sheetPath));
            Sheet sht = workbk.getSheet("Sheet1");
            int currRow = sht.findCell(studID).getRow();
            System.out.println(sht.getCell(3, currRow).getContents() + " Index ");
            System.out.println(sht.getCell(4, currRow).getContents() + " Answer selection");
        }
    }


