package com.example.contacts.contactss.utils;

import java.util.ArrayList;

/**
 * Created by Admin on 28.02.2016.
 */
public class Constants {
    public static final String TusRss = "http://news.tut.by/rss/all.rss";

    public final ArrayList<ModulesClass> ModulesArray = new ArrayList<ModulesClass>(){{
        add(new ModulesClass(){{name = "Главные новости"; url = "http://news.tut.by/rss/index.rss"; }});
        add(new ModulesClass(){{name = "Политика"; url = "http://news.tut.by/rss/politics.rss"; }});
        add(new ModulesClass(){{name = "Выборы"; url = "http://news.tut.by/rss/elections.rss"; }});
        add(new ModulesClass(){{name = "Экономика и бизнес"; url = "http://news.tut.by/rss/economics.rss"; }});
        add(new ModulesClass(){{name = "Финансы"; url = "http://news.tut.by/rss/finance.rss"; }});
        add(new ModulesClass(){{name = "Общество"; url = "http://news.tut.by/rss/society.rss"; }});
    }};


    public class ModulesClass {
        public String name;
        public String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
