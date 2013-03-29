package cn.yi18.util;

import net.sourceforge.pinyin4j.PinyinHelper;


/**
 * 把汉字转变成拼音
 * @author 陈磊
 *
 */
public class Pinyin4j {

	
	/**
	 * 
	 * @param sourceStr 需要转换的字符串  如   陈love磊
	 * @return   最后返回 chenlovelei
	 */
    public static String ToHanyuPinyin(String sourceStr) {  
        int sourceLen = sourceStr == null ? 0 : sourceStr.length();  
        if (sourceLen < 1)  
            return "";  
        StringBuffer pinyinStrBuf = new StringBuffer();  
        for (int idx = 0; idx < sourceLen; idx++) {  
            String[] tmpData = PinyinHelper.toHanyuPinyinStringArray(sourceStr  
                    .charAt(idx));  
            if (tmpData != null && tmpData.length > 0  
                    && tmpData[0].length() > 0) {  
                    pinyinStrBuf.append(tmpData[0].substring(0, tmpData[0]  
                            .length() - 1));  
            } 
            else {
            	pinyinStrBuf.append(sourceStr.toCharArray()[idx]);//如果是英文就持续是英文
			}
        }  
        return pinyinStrBuf.toString();  
    }  
      
    /* 
     * 汉语转成拼音的首字母 
     */  
//    public static String ToHanyuPinyinHead(String sourceStr, boolean isUpperCase) {  
//        int sourceLen = sourceStr == null ? 0 : sourceStr.length();  
//        if (sourceLen < 1)  
//            return "";  
//        StringBuffer pinyinStrBuf = new StringBuffer();  
//        for (int idx = 0; idx < sourceLen; idx++) {  
//            String[] tmpData = PinyinHelper.toHanyuPinyinStringArray(sourceStr  
//                    .charAt(idx));  
//            if (tmpData != null && tmpData.length > 0  
//                    && tmpData[0].length() > 0) {  
//                if (isUpperCase) {  
//                    pinyinStrBuf.append(tmpData[0].substring(0,  
//                            1).toUpperCase());  
//                } else {  
//                    pinyinStrBuf.append(tmpData[0].substring(0, 1));  
//                }  
//            }  
//            //汉语中不能转换成拼音的部分(英数)会原样保存   
//            else{  
//                pinyinStrBuf.append(sourceStr.charAt(idx));  
//            }  
//        }  
//        return pinyinStrBuf.toString();  
//    }  

    
    	public static void main(String[] args) {
    		
    		System.out.println(ToHanyuPinyin("陈love磊"));
		}
}
