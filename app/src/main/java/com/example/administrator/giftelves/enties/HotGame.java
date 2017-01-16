package com.example.administrator.giftelves.enties;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/12/28.
 */

public class HotGame {

    /**
     * flag : true
     * info : {"push1":[{"id":10,"appid":"1443491252","type":1,"clicks":15523,"flag":1,"platform":0,"name":"王者荣耀","typename":"动作格斗","logo":"/allimgs/img_iapp/201509/_1443491274999.png","size":"360M","addtime":"2016-12-26 11:39:03.0"},{"id":8,"appid":"1482309257","type":1,"clicks":12734,"flag":1,"platform":0,"name":"仙剑问情","typename":"角色扮演","logo":"/allimgs/img_iapp/201612/_1482309235360.jpg","size":"105M","addtime":"2016-12-22 17:23:54.0"},{"id":37,"appid":"1421918699","type":1,"clicks":12262,"flag":0,"platform":0,"name":"熹妃传","typename":"角色扮演","logo":"/allimgs/img_iapp/201612/_1482820854974.png","size":"158 MB","addtime":"2016-11-05 10:26:18.0"}],"push2":[{"id":27,"appid":"1482476777","type":0,"clicks":100,"flag":1,"platform":0,"name":"夜夜德州","typename":"棋牌桌游","logo":"/allimgs/img_iapp/201612/_1482476751755.png","size":"27M","addtime":"2016-12-26 18:04:00.0"},{"id":28,"appid":"1482139226","type":0,"clicks":2468,"flag":1,"platform":0,"name":"谁是大主公","typename":"角色扮演","logo":"/allimgs/img_iapp/201612/_1482139204338.png","size":"15M","addtime":"2016-12-23 15:27:28.0"},{"id":11,"appid":"1438084072","type":0,"clicks":4522,"flag":1,"platform":0,"name":"全民超神","typename":"角色扮演","logo":"/allimgs/img_iapp/201507/_1438084023090.jpg","size":"401 MB","addtime":"2016-03-24 13:41:41.0"},{"id":23,"appid":"1421918699","type":0,"clicks":100,"flag":1,"platform":0,"name":"熹妃传","typename":"角色扮演","logo":"/allimgs/img_iapp/201612/_1482820854974.png","size":"158 MB","addtime":"2016-03-23 20:02:16.0"},{"id":9,"appid":"1420564361","type":0,"clicks":3533,"flag":1,"platform":0,"name":"全民突击","typename":"射击战争","logo":"/userfiles/applogo/_1420610936640.jpg","size":"223M","addtime":"2016-01-09 18:12:19.0"},{"id":12,"appid":"1421467036","type":0,"clicks":2532,"flag":1,"platform":0,"name":"热血传奇手机版","typename":"角色扮演","logo":"/allimgs/img_iapp/201603/_1459327042485.png","size":" 516 MB","addtime":"2016-01-09 18:08:27.0"}]}
     */

    private boolean flag;
    private InfoBean info;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private List<Push1Bean> push1;
        private List<Push2Bean> push2;

        public List<Push1Bean> getPush1() {
            return push1;
        }

        public void setPush1(List<Push1Bean> push1) {
            this.push1 = push1;
        }

        public List<Push2Bean> getPush2() {
            return push2;
        }

        public void setPush2(List<Push2Bean> push2) {
            this.push2 = push2;
        }

        public static class Push1Bean {
            /**
             * id : 10
             * appid : 1443491252
             * type : 1
             * clicks : 15523
             * flag : 1
             * platform : 0
             * name : 王者荣耀
             * typename : 动作格斗
             * logo : /allimgs/img_iapp/201509/_1443491274999.png
             * size : 360M
             * addtime : 2016-12-26 11:39:03.0
             */

            private int id;
            private String appid;
            private int type;
            private int clicks;
            private int flag;
            private int platform;
            private String name;
            private String typename;
            private String logo;
            private String size;
            private String addtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getClicks() {
                return clicks;
            }

            public void setClicks(int clicks) {
                this.clicks = clicks;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }

        public static class Push2Bean {
            /**
             * id : 27
             * appid : 1482476777
             * type : 0
             * clicks : 100
             * flag : 1
             * platform : 0
             * name : 夜夜德州
             * typename : 棋牌桌游
             * logo : /allimgs/img_iapp/201612/_1482476751755.png
             * size : 27M
             * addtime : 2016-12-26 18:04:00.0
             */

            private int id;
            private String appid;
            private int type;
            private int clicks;
            private int flag;
            private int platform;
            private String name;
            private String typename;
            private String logo;
            private String size;
            private String addtime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getClicks() {
                return clicks;
            }

            public void setClicks(int clicks) {
                this.clicks = clicks;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }
        }
    }
}
