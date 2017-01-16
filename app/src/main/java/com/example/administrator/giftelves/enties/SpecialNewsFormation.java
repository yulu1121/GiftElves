package com.example.administrator.giftelves.enties;

import java.util.List;

/**
 * 新游周刊的详情界面
 * Created by Administrator on 2017/1/8.
 */

public class SpecialNewsFormation {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 963
         * fid : 20161209
         * appid : 1478246559
         * appname : 大圣之怒
         * typename : 角色扮演
         * appsize : 未知
         * adimg : /allimgs/img_iapp/201612/_1481098121882.jpg
         * appkfs : 天拓游戏
         * iconurl : /allimgs/img_iapp/201611/_1478501209871.png
         * addtime : 2016-12-07
         * descs : 《大圣之怒》是由天拓游戏自主研发的西游后传动作手游，在这里寻找取经背后的真相。精致唯美的3D画面，殿堂级原声配乐，缔造震撼视听盛宴；野外自由激战，怒气无双连招，尽享畅快战斗体验；一怒扫四方，热血战八荒，美人相伴万人国战；地府时装，齐天大圣，新手即赠丰厚福利；荣耀巅峰，酣畅竞技，大圣为正义与荣耀而战！
         * critique : 《大圣之怒》对于喜欢西游和国战的游戏玩家是一个不错的选择。《大圣之怒》在画面表现与PVP方面做得非常完美，养成方面很有特色，同时剧情也极富有吸引力。玩家将在游戏中体验万人同屏的激烈国战，唯美精致的3D画面，爽快酣畅的PK对战!重度角色养成，神将组合策略，多元玩法打造更为精彩的动作游戏体验!对这样一款游戏感兴趣的小伙伴千万不能错过哦!
         * iszq : 0
         * typeid : 0
         * istop : 0
         */

        private int id;
        private int fid;
        private String appid;
        private String appname;
        private String typename;
        private String appsize;
        private String adimg;
        private String appkfs;
        private String iconurl;
        private String addtime;
        private String descs;
        private String critique;
        private int iszq;
        private int typeid;
        private int istop;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
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

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getAppsize() {
            return appsize;
        }

        public void setAppsize(String appsize) {
            this.appsize = appsize;
        }

        public String getAdimg() {
            return adimg;
        }

        public void setAdimg(String adimg) {
            this.adimg = adimg;
        }

        public String getAppkfs() {
            return appkfs;
        }

        public void setAppkfs(String appkfs) {
            this.appkfs = appkfs;
        }

        public String getIconurl() {
            return iconurl;
        }

        public void setIconurl(String iconurl) {
            this.iconurl = iconurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public String getCritique() {
            return critique;
        }

        public void setCritique(String critique) {
            this.critique = critique;
        }

        public int getIszq() {
            return iszq;
        }

        public void setIszq(int iszq) {
            this.iszq = iszq;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getIstop() {
            return istop;
        }

        public void setIstop(int istop) {
            this.istop = istop;
        }
    }
}
