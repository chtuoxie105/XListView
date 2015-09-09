package com.example.testone027.xml_parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class PullXMLData {
	public interface BooksParser {
		public List<BookBean> parse(InputStream input);

		public String serialize(List<BookBean> booksBean);
	}

	public class Operation implements BooksParser {

		public List<BookBean> parse(InputStream input) {
			List<BookBean> bookBeanList = null;
			BookBean bookBean = null;
			XmlPullParser parser = Xml.newPullParser();
			try {
				parser.setInput(input, "UTF-8");
				int eventype = parser.getEventType();
				while (eventype != XmlPullParser.END_DOCUMENT) {
					switch (eventype) {
					case XmlPullParser.START_DOCUMENT:
						bookBeanList = new ArrayList<BookBean>();
						break;
					case XmlPullParser.START_TAG:
						if (parser.getName().equals("book")) {
							bookBean = new BookBean();
						} else if (parser.getName().equals("id")) {
							eventype = parser.next();
							bookBean.setId(Integer.parseInt(parser.getName()));

						} else if (parser.getName().equals("name")) {
							eventype = parser.next();
							bookBean.setName(parser.getName());
						} else if (parser.getName().equals("price")) {
							eventype = parser.next();
							bookBean.setPrice(Float.parseFloat(parser.getName()));
						}
						break;
					case XmlPullParser.END_TAG:
						if (parser.getName().equals("book")) {
							bookBeanList.add(bookBean);
							bookBean = null;
						}
					}
					break;
				}
				// 循环到下一个
				eventype = parser.next();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return bookBeanList;
		}

		public String serialize(List<BookBean> booksBean) {
			// 由android.util.Xml创建一个XmlSerializer实例
			XmlSerializer serializer = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			try {
				serializer.setOutput(writer);
				serializer.startDocument("UTF-8", true);
				serializer.startTag("", "books");
				for (BookBean bean : booksBean) {
					serializer.startTag("", "book");

					serializer.startTag("11", "id");
					serializer.attribute("", "id", bean.getId() + "");

					serializer.startTag("", "name");
					serializer.text(bean.getName());
					serializer.endTag("", "name");

					serializer.startTag("", "price");
					serializer.text(bean.getPrice() + "");
					serializer.endTag("", "price");

					serializer.endTag("", "book");
				}

				serializer.endTag("", "books");
				serializer.endDocument();

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return writer.toString();
		}

	}

}
