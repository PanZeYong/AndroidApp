package com.demo.panju.androidapp.bean;

import java.util.List;

/**
 * Author : PZY
 * Date : 2016.8.16
 */
public class Category {

    /**
     * status : true
     * tngou : [{"description":"\u2026\u2026","id":1,"keywords":"\u2026\u2026","name":"性感美女","seq":1,"title":"\u2026\u2026"},{"description":"\u2026\u2026","id":2,"keywords":"\u2026\u2026","name":"韩日美女","seq":2,"title":"\u2026\u2026"},{"description":"\u2026\u2026","id":3,"keywords":"\u2026\u2026","name":"丝袜美腿","seq":3,"title":"\u2026\u2026"},{"description":"\u2026\u2026","id":4,"keywords":"\u2026\u2026","name":"美女照片","seq":4,"title":"\u2026\u2026"},{"description":"\u2026\u2026","id":5,"keywords":"\u2026\u2026","name":"美女写真","seq":5,"title":"\u2026\u2026"},{"description":"\u2026\u2026","id":6,"keywords":"\u2026\u2026","name":"清纯美女","seq":6,"title":"\u2026\u2026"},{"description":"\u2026\u2026","id":2,"keywords":"\u2026\u2026","name":"性感车模","seq":7,"title":"\u2026\u2026"}]
     */

    private boolean status;
    /**
     * description : ……
     * id : 1
     * keywords : ……
     * name : 性感美女
     * seq : 1
     * title : ……
     */

    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        private String description;
        private int id;
        private String keywords;
        private String name;
        private int seq;
        private String title;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
