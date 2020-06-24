package com.example.demo.webhook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.mvc.RouteContext;
import com.blade.mvc.hook.WebHook;
import com.example.demo.config.DatabaseConfig;

@Bean
public class UploadCustomerUserInterceptor implements WebHook {
    @Inject
    DatabaseConfig databaseConfig;
    int result;
     /**
     * @Setter It is called from test file, it has no rollback porerty
     * @param dconfig object instance of DatabaseConfig
     */
    public void setDatabaseConfig(DatabaseConfig dconfig) {
        this.databaseConfig = dconfig;
    }
    @Override
    public boolean before(RouteContext context) {
        context.request().fileItem("upload").ifPresent(fileItem -> {
            try {
                result=0;
                
                File userFile = new File(fileItem.getFile().getAbsolutePath());
                System.out.println("uploaded file name :" + userFile);
                System.out.println("uploaded file path :"+fileItem.getPath());

                result += uploadCustomerUserListValidation(userFile);
                System.out.println("Interceptor passed ? :" + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return result > 0;
    }

    public int uploadCustomerUserListValidation(File projectCustomerList) {
        BufferedReader br = null;
        String headerRow[] = null;
        String row = null;
        String rowArr[] = null;
        int rowNo = 0;
        int result=0;
        List<String> headerRuleList = new ArrayList<>();
        List<String> valueSizeRuleList = new ArrayList<>();
        // List<String> valueTypeRuleList = new ArrayList<>();
        try {
            // br = databaseConfig.getPropFileReader().getFileName();//fetching file to
            // buffer in br, static way by initializing the file on lod of app
            FileReader fileForTesting = new FileReader(projectCustomerList);
            BufferedReader brFile = new BufferedReader(fileForTesting);
            br = brFile; // buffered File as parameter

            // below three lines are for fetching rules from.json file
            headerRuleList = databaseConfig.getPropFileReader().getRules("column_header");
            valueSizeRuleList = databaseConfig.getPropFileReader().getRules("column_value_length");
            // valueTypeRuleList=databaseConfig.getPropFileReader().getRules("column_value_datatype");
            headerRow = br.readLine().split(",");
             
            /**
             * Below if Block is to validate header of the .csv file
             */
            if (headerRow != null && headerRuleList != null) {
                result=1;
                if (headerRuleList.size() != headerRow.length) {
                    System.out.println("Number of column mismatch: required " + headerRuleList.size() + " found "
                            + headerRow.length);
                    result -= 1;
                } else {
                    for (int headerIndex = 0; headerIndex < headerRuleList.size(); headerIndex++) {
                        if (!headerRow[headerIndex].equals(headerRuleList.get(headerIndex))) {
                            System.out.println("Column Header'" + headerRow[headerIndex] + "' does not match with '"
                                    + headerRuleList.get(headerIndex) + "'");
                            result -= 1;
                        }
                    }
                }
                rowNo += 1;
            }
            /**
             * Below while loop is to validate length of values
             */
            while ((row = br.readLine()) != null) {// single row in String:row
                rowNo += 1;
                rowArr = row.split(","); // single row array in array :rowArr
                /**
                 * Below if block check numbers of column in every row
                 */
                if (rowArr.length > headerRuleList.size() || rowArr.length < headerRuleList.size()) {
                    String rowColumn = rowArr.length > headerRuleList.size() ? "more" : "less";
                    System.out
                            .println("Number of columns in row " + rowNo + " is " + rowColumn + " than header column");
                    result -= 1;
                }
                for (int valueIndex = 0; valueIndex < rowArr.length
                        && valueIndex < headerRuleList.size(); valueIndex++) {
                    if (rowArr[valueIndex].length() > Integer.parseInt(valueSizeRuleList.get(valueIndex))) {
                        System.out.println("Length of value " + rowArr[valueIndex] + " shouldn't be more than "
                                + Integer.parseInt(valueSizeRuleList.get(valueIndex)) + " r:c->" + rowNo + ":"
                                + (valueIndex + 1));
                        result -= 1;
                    }
                }
                System.out.println();

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}