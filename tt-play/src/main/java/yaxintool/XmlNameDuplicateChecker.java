package yaxintool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class XmlNameDuplicateChecker {
    public static void main(String[] args) {
        String xmlFilePath = "C:\\yaxin\\panzhou\\creditctrl\\config\\service\\creditctrl.xml"; // 替换为实际 XML 文件路径
        try {
            Set<String> nameSet = new HashSet<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));

            // 获取所有 entity 节点
            NodeList entityNodes = document.getElementsByTagName("entity");
            boolean hasDuplicate = false;

            for (int i = 0; i < entityNodes.getLength(); i++) {
                Element entityElement = (Element) entityNodes.item(i);
                String name = entityElement.getAttribute("name");

                if (name.isEmpty()) {
                    System.out.println("警告：第 " + (i + 1) + " 个 entity 缺少 name 属性");
                    continue;
                }

                if (!nameSet.add(name)) {
                    System.out.println("发现重复名称: " + name + "（出现在第 " + (i + 1) + " 个 entity）");
                    hasDuplicate = true;
                }
            }

            if (!hasDuplicate) {
                System.out.println("所有 name 属性均唯一");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
