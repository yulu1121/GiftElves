package com.example.administrator.giftelves.enties;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/8.
 */

public class HitThirdInformation {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1362
         * cid : 130
         * appid : 1451971043
         * appname : 部落冲突:皇室战争
         * appicon : /allimgs/img_iapp/201601/_1451970639500.jpg
         * typename : 卡牌游戏
         * appdesc :
         * appvtype : 1,2
         */

        private int id;
        private int cid;
        private String appid;
        private String appname;
        private String appicon;
        private String typename;
        private String appdesc;
        private String appvtype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getAppicon() {
            return appicon;
        }

        public void setAppicon(String appicon) {
            this.appicon = appicon;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getAppdesc() {
            return appdesc;
        }

        public void setAppdesc(String appdesc) {
            this.appdesc = appdesc;
        }

        public String getAppvtype() {
            return appvtype;
        }

        public void setAppvtype(String appvtype) {
            this.appvtype = appvtype;
        }
    }
}
