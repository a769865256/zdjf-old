package com.zdjf.util;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zdjf.domain.dto.UserXmlNode;

/*权限在xml文件中*/


public class XmlToJurisdiction {
	
	@SuppressWarnings("unchecked")
	public static List<UserXmlNode> getExtName(String fileName) throws DocumentException{
		
		SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(fileName).getDocument();
        
        Element root = document.getRootElement();//获得根节点
        
        
        List<Element> childElements = root.elements();
        List<UserXmlNode> nodes = new ArrayList<UserXmlNode>();
        
        for (Element child : childElements) {//循环输出全部book的相关信息  
      
			List<Element> books = child.elements(); 
			UserXmlNode node = new UserXmlNode();
            for (Element book : books) {  
            	
            	
                String name = book.getName();//获取当前元素名
                if(name.equalsIgnoreCase("id")){
                	node.setId(Integer.valueOf(book.getTextTrim()).intValue());
                }else if(name.equalsIgnoreCase("grade")){
                	node.setGrade(Integer.valueOf(book.getTextTrim()).intValue());
                }else if(name.equalsIgnoreCase("name")){
                	node.setName(book.getTextTrim());
                }else if(name.equalsIgnoreCase("parent_id")){
                	node.setParent_id(Integer.valueOf(book.getTextTrim()).intValue());
                }else if(name.equalsIgnoreCase("url")){
                	node.setUrl(book.getTextTrim());
                }
               
            }  
            
            nodes.add(node);
        }
        
        return nodes;
	}

}
