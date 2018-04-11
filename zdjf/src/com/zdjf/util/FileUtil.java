package com.zdjf.util;

public class FileUtil
{
    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return      
     * @since  Ver 3.0
     */
    public static String getExtName(String fileName){
        int extSeperatorIndex = fileName.lastIndexOf(".");
        if (extSeperatorIndex >= 0) {
            return fileName.substring(extSeperatorIndex).replace(",","");
        }
        return null;
    }
    
    /**
     * 获取去掉扩展名的文件名称
     *
     * @param inputFileName
     * @return      
     * @since  Ver 3.0
     */
    public static String getFileNameWithoutExtname(String inputFileName){
        //获取扩展名
        if(inputFileName.lastIndexOf(".") >= 0 ){
            return inputFileName.substring(0, inputFileName.lastIndexOf("."));
        }
        return null;
    }
}
