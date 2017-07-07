package com.lnly.common.utils;

import com.lnly.user.manager.UserManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CsvParser
 * 此类参考了网上方案，在此表示感谢
 * 2013-12-10 21:43:48
 */
public class CsvParser{
    // Saved input CSV file pathname
    private String inputCsvFile;
    
    // Space mark , ; : etc.
    private String spaceMark=",";
    
    /**
     * Contructor
     * @param inputCsvFile
     */
    public CsvParser(String inputCsvFile,String spaceMark){
        this.inputCsvFile=inputCsvFile;
        this.spaceMark=spaceMark;
    }
    
    /**
     * Contructor
     * @param inputCsvFile
     */
    public CsvParser(String inputCsvFile){
        this.inputCsvFile=inputCsvFile;
        this.spaceMark=",";
    }
    
    /**
     * Get parsed array from CSV file
     * @return
     */
    public Object[] getParsedArray() throws Exception{
        List<List<String>> retval=new ArrayList<List<String>>();
        
        String regExp = getRegExp();
        BufferedReader in = new BufferedReader(new FileReader(this.inputCsvFile));
        String strLine;
        String str = "";
        
        while ((strLine = in.readLine()) != null) {
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(strLine);
            List<String> listTemp = new ArrayList<String>();
            while (matcher.find())
            {
                str = matcher.group();
                str = str.trim();
                
                if (str.endsWith(spaceMark))
                {
                    str = str.substring(0, str.length() - 1);
                    str = str.trim();
                }
                
                if (str.startsWith("\"") && str.endsWith("\""))
                {
                    str = str.substring(1, str.length() - 1);
                    if (CsvParser.isExisted("\"\"", str))
                    {
                        str = str.replaceAll("\"\"", "\"");
                    }
                }
                
                if (!"".equals(str))
                {
                    listTemp.add(str);
                }
            }
            
            // Add to retval
            retval.add(listTemp);     
        }
        in.close();
        
        return retval.toArray();
    }
    
    /**
     * Regular Expression for CSV parse
     * @return
     */
    private String getRegExp()
    {
        final String SPECIAL_CHAR_A = "[^\",\\n 　]";
        final String SPECIAL_CHAR_B = "[^\""+spaceMark+"\\n]";
        
        StringBuffer strRegExps = new StringBuffer();
        strRegExps.append("\"((");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*["+spaceMark+"\\n 　])*(");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"{2})*)*");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"[ 　]*"+spaceMark+"[ 　]*");
        strRegExps.append("|");
        strRegExps.append(SPECIAL_CHAR_B);
        strRegExps.append("*[ 　]*"+spaceMark+"[ 　]*");
        strRegExps.append("|\"((");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*["+spaceMark+"\\n 　])*(");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"{2})*)*");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"[ 　]*");
        strRegExps.append("|");
        strRegExps.append(SPECIAL_CHAR_B);
        strRegExps.append("*[ 　]*");
        return strRegExps.toString();
    }
    
    /**
     * If argChar is exist in argStr
     * @param argChar
     * @param argStr
     * @return
     */
    private static boolean isExisted(String argChar, String argStr)
    {
        
        boolean blnReturnValue = false;
        if ((argStr.indexOf(argChar) >= 0)
                && (argStr.indexOf(argChar) <= argStr.length()))
        {
            blnReturnValue = true;
        }
        return blnReturnValue;
    }

    /**
     * Test
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)  throws Exception{
       // CsvParser parser=new CsvParser("E:\\lngyl\\国家补偿标准.csv");

        CsvParser parser=new CsvParser("E:\\lngyl\\user.csv");
        
        Object[] arr=parser.getParsedArray();

        StringBuffer buff = new StringBuffer();

        for(Object obj:arr){
           // buff = proAdminDictSql(buff, (List<String>) obj);
            buff = proUserSql(buff, (List<String>) obj);
            System.out.println(buff.toString());
        }
    }


    private static StringBuffer proUserSql(StringBuffer buff, List<String> obj){
        buff.setLength(0);
        buff.append("INSERT INTO `u_user` (`nickname`, `email`, `pswd`, `create_time`, `status`) VALUES (");
        List<String> ls= obj;
        buff.append("'").append(ls.get(4)).append("',");
        String emai = ls.get(3);
        String pswd = ls.get(5);
        buff.append("'").append(ls.get(3)).append("',");
        buff.append("'").append(UserManager.md5Pswd(emai,pswd)).append("',");

        // str_to_date('2017-06-22 21:31:02','%Y-%m-%d %H:%i:%s');
        buff.append("").append("'2017-07-02 21:31:02'").append(",");
        buff.append("'").append("1").append("'");
        buff.append(");");

        return buff;
    }

    // INSERT INTO tb_admin_dict ( dict_code,dict_name,high_dict,comment) VALUES ('2','2','2','2');
    private static StringBuffer proAdminDictSql(StringBuffer buff, List<String> obj) {
        buff.setLength(0);
        buff.append("INSERT INTO tb_admin_dict ( dict_code,dict_name,high_dict,comment) VALUES (");
        List<String> ls= obj;
        buff.append("'").append(ls.get(3)).append("',");
        buff.append("'").append(ls.get(3)).append("',");
        buff.append("'").append(ls.get(2)).append("',");
        buff.append("'").append("").append("'");
        buff.append(");");

        return buff;
    }
}