package com.example.contacts.contactss.api;

import android.os.AsyncTask;

import com.example.contacts.contactss.model.NewsItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Admin on 01.03.2016.
 */
public class Tut {
    public static abstract class CallBackGetNews {
        public abstract void onSuccess(ArrayList<NewsItem> model);

        public abstract void onError(String error);
    }

    public static void asyncGetNews(String query, final CallBackGetNews callback) {
        new AsyncTask<String, Void, ArrayList<NewsItem>>() {
            @Override
            protected ArrayList<NewsItem> doInBackground(String... params) {
                /*String result = "";
                String responseLine = "";*/

                ArrayList<NewsItem> Array = new ArrayList<>();

//                Uri.Builder builder = new Uri.Builder();
//                builder.scheme("https")
//                        .authority("api.vk.com")
//                        .appendPath("method")
//                        .appendPath("users.search")
//                        .appendQueryParameter("q", params[0])
//                        .appendQueryParameter("fields", "photo_100,last_seen,online")
//                        .appendQueryParameter("count", "50");
//                String myUrl = params[0];

                try {
                    /*HttpURLConnection connection = (HttpURLConnection) new URL(myUrl).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Length", "0");
                    connection.setConnectTimeout(10000);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((responseLine = bufferedReader.readLine()) != null) {
                        result += responseLine;
                    }
                    bufferedReader.close();*/

                    DocumentBuilderFactory f =
                            DocumentBuilderFactory.newInstance();
                    DocumentBuilder b = f.newDocumentBuilder();
                    Document doc = b.parse(params[0]);

                    doc.getDocumentElement().normalize();
                    System.out.println("Root element: " +
                            doc.getDocumentElement().getNodeName());

                    // loop through each item
                    NodeList items = doc.getElementsByTagName("item");
                    for (int i = 0; i < items.getLength(); i++)
                    {
                        NewsItem item = new NewsItem();

                        Node n = items.item(i);
                        if (n.getNodeType() != Node.ELEMENT_NODE)
                            continue;
                        Element e = (Element) n;

                        item.setTitle((String) e.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue().toString());
                        item.setLink((String) e.getElementsByTagName("link").item(0).getChildNodes().item(0).getNodeValue().toString());
                        item.setDescription((String) e.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue().toString());
                        item.setCategory((String) e.getElementsByTagName("category").item(0).getChildNodes().item(0).getNodeValue().toString());
                        item.setPubDate((String) e.getElementsByTagName("pubDate").item(0).getChildNodes().item(0).getNodeValue().toString());
                        item.setImage((String) e.getElementsByTagName("media:content").item(0).getAttributes().item(0).getNodeValue().toString());
                        Array.add(item);
                    }

                } catch ( ParserConfigurationException | IOException | SAXException e) {
                    e.printStackTrace();
                }
                return Array;
            }

            @Override
            protected void onPostExecute(ArrayList<NewsItem> result) {
                callback.onSuccess(result);
            }
        }.execute(query);
    }
}
