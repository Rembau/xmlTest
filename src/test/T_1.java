package test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class T_1 {
	private Document doc;
	T_1(){
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    	try {
			DocumentBuilder builder=factory.newDocumentBuilder();
			try {
				File f=new File("src\\test/server.xml");
				doc = builder.parse(f);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
	}
	public void test(){
		Node n=doc.getElementsByTagName("Logger").item(0);
		System.out.println(n.getAttributes().item(0).getNodeValue());
	}
	public static void main(String[] args) {
		new T_1().test();
	}
}
