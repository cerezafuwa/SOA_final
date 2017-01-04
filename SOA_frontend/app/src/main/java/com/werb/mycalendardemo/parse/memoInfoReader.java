package com.werb.mycalendardemo.parse;

import com.werb.mycalendardemo.models.memo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by fengyuqing on 2017/1/4.
 */

public class memoInfoReader {
    public static memo read_XML(String string) {
        //List<Phone_info> address_infos = new ArrayList<Phone_info>();
        memo memo_info = new memo();
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document document = null;
        InputStream is = null;

        try {

            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            is = new ByteArrayInputStream(string.getBytes("utf-8"));
            document = builder.parse(is);
            Element root = document.getDocumentElement();

            Element textnode = (Element) root.getElementsByTagName("text").item(0);
            memo_info.set_text(textnode.getFirstChild().getNodeValue());


        } catch (Exception e) {

            e.printStackTrace();

        }
        return memo_info;
    }
}
