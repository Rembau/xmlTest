package dom4j_test;

import java.io.File;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;
 
public class XmlHandler {
    public static void main(String[] args) {
       SAXReader saxReader = new SAXReader();
       File file = new File("students.xml");
       try {
           // ���һ��ElementHandlerʵ����
           saxReader.addHandler("/students/student", new StudentHandler());
           saxReader.read(file);
 
       } catch (DocumentException e) {
           System.out.println(e.getMessage());
       }
    }
 
    /**
     * ����StudentHandler�������࣬��<student>Ԫ�ؽ��д���
     */
    private static class StudentHandler implements ElementHandler {
       public void onStart(ElementPath path) {
           Element elt = path.getCurrent();
           System.out.println("Found student: " + elt.attribute("sn"));
           // ��Ӷ���Ԫ��<name>�Ĵ�������
           path.addHandler("name", new NameHandler());
       }
 
       public void onEnd(ElementPath path) {
           // �Ƴ�����Ԫ��<name>�Ĵ�������
           path.removeHandler("name");
       }
    }
 
    /**
     * ����NameHandler�������࣬��<student>��<name>��Ԫ�ؽ��д���
     */
    private static class NameHandler implements ElementHandler {
       public void onStart(ElementPath path) {
           System.out.println("path : " + path.getPath());
       }
 
       public void onEnd(ElementPath path) {
           Element elt = path.getCurrent();
           // ���<name>Ԫ�ص����ֺ������ı����ݡ�
           System.out.println(elt.getName() + " : " + elt.getText());
       }
    }
}

