/**
 * The MIT License
 * <p>
 * Copyright (c) 2012 www.myjeeva.com
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.lnly.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lnly.business.bo.PersonVO;
import com.lnly.common.model.CountryCompensationDetail;
import com.lnly.common.utils.poi.ExcelReader;
import com.lnly.common.utils.poi.ExcelSheetCallback;
import com.lnly.common.utils.poi.ExcelWorkSheetHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;



/**
 * Demonstration of Generic Excel File (XLSX) Reading using Apache POI
 *
 * @author <a href="mailto:jeeva@myjeeva.com">Jeevanandam M.</a>
 */
public class ExcelWorkSheetHandlerTest {
    private static final Log LOG = LogFactory.getLog(ExcelWorkSheetHandlerTest.class);

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String SAMPLE_PERSON_DATA_FILE_PATH = "d:/temp/导入模板.xlsx";

        // Input File initialize
        File file = new File(SAMPLE_PERSON_DATA_FILE_PATH);
        InputStream inputStream = new FileInputStream(file);

        // Excel Cell Mapping
        Map<String, String> cellMapping = new HashMap<String, String>();
        cellMapping.put("HEADER", "乡,村,林班,小班,户名,身份证号码,汇款账号,面积(亩),补偿标准,是否发放");
        cellMapping.put("A", "town");
        cellMapping.put("B", "village");
        cellMapping.put("C", "forestClass");
        cellMapping.put("D", "smallClass");
        cellMapping.put("E", "username");
        cellMapping.put("F", "identityCard");
        cellMapping.put("G", "remitNum");
        cellMapping.put("H", "area");
        cellMapping.put("I", "compensationStandard");
        cellMapping.put("J", "sendFlag");

        // The package open is instantaneous, as it should be.
        OPCPackage pkg = null;
        try {

            ExcelWorkSheetHandler<CountryCompensationDetail> workSheetHandler =
                    new ExcelWorkSheetHandler<CountryCompensationDetail>(CountryCompensationDetail.class, cellMapping);

            pkg = OPCPackage.open(inputStream);

            ExcelSheetCallback sheetCallback = new ExcelSheetCallback() {
                private int sheetNumber = 0;

                @Override
                public void startSheet(int sheetNum, String sheetName) {
                    this.sheetNumber = sheetNum;
                    System.out.println("Started processing sheet number=" + sheetNumber
                            + " and Sheet Name is '" + sheetName + "'");
                }

                @Override
                public void endSheet() {
                    System.out.println("Processing completed for sheet number=" + sheetNumber);
                }
            };

            System.out.println("Constructor: pkg, workSheetHandler, sheetCallback");
            ExcelReader example1 = new ExcelReader(pkg, workSheetHandler, sheetCallback);
            example1.process();

            if (workSheetHandler.getValueList().isEmpty()) {
                // No data present
                LOG.error("sHandler.getValueList() is empty");
            } else {

                LOG.info(workSheetHandler.getValueList().size()
                        + " no. of records read from given excel worksheet successfully.");

                // Displaying data ead from Excel file
                displayPersonList(workSheetHandler.getValueList());
            }

            System.out.println("\nConstructor: filePath, workSheetHandler, sheetCallback");
            ExcelReader example2 =
                    new ExcelReader(SAMPLE_PERSON_DATA_FILE_PATH, workSheetHandler, sheetCallback);
            example2.process();

            System.out.println("\nConstructor: file, workSheetHandler, sheetCallback");
            ExcelReader example3 = new ExcelReader(file, workSheetHandler, null);
            example3.process();

        } catch (RuntimeException are) {
            are.printStackTrace();
            LOG.error(are.getMessage(), are.getCause());
        } catch (InvalidFormatException ife) {
            ife.printStackTrace();
            LOG.error(ife.getMessage(), ife.getCause());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            LOG.error(ioe.getMessage(), ioe.getCause());
        } finally {
            IOUtils.closeQuietly(inputStream);
            try {
                if (null != pkg) {
                    pkg.close();
                }
            } catch (IOException e) {
                // just ignore IO exception
            }
        }
    }

    private static void displayPersonList(List<CountryCompensationDetail> details) {
        //乡,村,林班,小班 户名,身份证号码,汇款帐号,面积(亩),补偿标准,是否发放
        System.out.println("乡\t村\t林班\t小班\t户名\t身份证号码\t汇款帐号\t面积(亩)\t补偿标准\t是否发放");
        System.out.println("--\t----\t------\t-----\t--------\t------\t---\t------\t------");
        for (CountryCompensationDetail p : details) {
            System.out.println(String.format("%s\t%s\t%s\t%s\t%s\t%s", p.getTown(), p.getVillage(),
                    p.getForestClass(), p.getSmallClass(), p.getUsername(), p.getIdentityCard()));
        }
    }
}
