package com.jzfblog.store.utils;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanFactory {
	
	/**
	 * ����xml
	 * @param name
	 * @return
	 * @throws DocumentException 
	 */
	public static Object createObject(String name) {
		
		try {
			// ͨ�����ݹ�����name��ȡapplication.xml��name��Ӧ��classֵ 
			
			// ��ȡ��document����
			SAXReader saxReader = new SAXReader();
			// ��ȡxml�ļ�����������application.xml������λ��src��
			InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("application.xml");
			Document read = saxReader.read(is);
			// ͨ��document�����ȡ���ڵ� beans
			Element rootElement = read.getRootElement();
			// ͨ�����ڵ��ȡ�����ڵ������е��ӽڵ� bean�����ؼ���
			List<Element> elements = rootElement.elements();
			// �������ϣ��ж�ÿ��Ԫ���ϵ�idֵ�Ƿ�͵�ǰ��nameһ�� 
			for (Element element : elements) {
				// ��ȡ���ڵ��id����ֵ
				String id = element.attributeValue("id");
				// ���һ�� ��ȡ����ǰԪ����class������ֵ
				if(id.equals(name)) {
					String str = element.attributeValue("class");
					// ��ȡ�ֽ������
					Class clazz = Class.forName(str);
					// ͨ�����䴴�����󣬲��ҷ���
					return clazz.newInstance();
				}
			}

			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
