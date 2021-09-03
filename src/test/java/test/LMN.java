package test;

import com.sun.org.apache.xml.internal.resolver.readers.SAXParserHandler;
import leetcode.ListNode;
import leetcode.TLog;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.swing.text.rtf.RTFEditorKit;
import javax.xml.parsers.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * 约瑟夫问题
 */
public class LMN {
    private interface Solution {
        int find(int n, int m);
    }

    @Test
    public void test() throws ParserConfigurationException, SAXException, IOException {
        Set<String> set = new HashSet<>();
        NodeList enNode = read("C:\\Users\\zack\\Desktop\\string\\en/strings.xml");
        for (int i = 0; i < enNode.getLength(); i++) {
            Node node = enNode.item(i);
            set.add(node.getAttributes().getNamedItem("name").getNodeValue());
        }
        enNode = null;
        NodeList zhNode = read("C:\\Users\\zack\\Desktop\\string\\zh/strings.xml");
        for (int i = 0; i < zhNode.getLength(); i++) {
            Node node = zhNode.item(i);
            set.remove(node.getAttributes().getNamedItem("name").getNodeValue());
        }
        zhNode = null;
//
        NodeList zhOldNode = read("C:\\Users\\zack\\Desktop\\string\\zh-old/strings.xml");
        System.out.println(set);
        MNL array = new MNL();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zhOldNode.getLength(); i++) {
            Node node = zhOldNode.item(i);

            if (set.contains(node.getAttributes().getNamedItem("name").getNodeValue())){
                array.add(node);
                toNodeString(sb,node);

            }
        }
        System.out.println(sb);


    }
    static void toNodeString(StringBuilder sb,Node node){
        sb.append('<').append(node.getNodeName()).append(' ');
        NamedNodeMap attributes = node.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node item = attributes.item(i);
            sb.append(item.getNodeName()).append('=').append('"').append(item.getTextContent()).append('"').append(' ');
        }
        if (node.getTextContent()==null){
            sb.append("/>").append('\n');
            return;
        }
        sb.append('>').append(node.getTextContent());
        sb.append("</").append(node.getNodeName()).append('>').append('\n');
    }
    static class MNL implements NodeList {
        List<Node> data = new ArrayList<>();

        public void add(Node node){
            data.add(node);
        }
        @Override
        public Node item(int index) {
            return data.get(index);
        }

        @Override
        public int getLength() {
            return data.size();
        }
    }
    NodeList read(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document d = builder.parse(path);
        return  d.getElementsByTagName("string");
    }

}