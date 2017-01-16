package com.example.administrator.giftelves.enties;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/6.
 */

public class HotGameInformation {


    /**
     * app : {"id":1443491252,"name":"王者荣耀","developers":"腾讯游戏","appsize":"231M","version":"","logo":"/allimgs/img_iapp/201509/_1443491274999.png","download_addr":"http://dlied5.myapp.com/myapp/1104466820/sgame/2017_SGame_App_Android_1.8.2.3.apk","upload_time":1443491220,"add_time":1443491247,"state":1,"keywords":"王者联盟，王者荣耀，MOBA","operator":"倩女幽魂","typeid":3,"orderid":1,"description":"《王者荣耀》5V5英雄公平对战 手游，腾讯最新MOBA 大作!5V5、3v3、1v1，多样模式一键体验，海量英雄 \r\n随心选择!10秒实时跨区匹配，与好友组队登顶王者之巅!一血、五杀、秒开团，实力操作公平对战 ，回归MOBA初心!10.28不限号 \r\n，全世界撸友，手机团战开黑!","good_evaluation":0,"bad_evaluation":0,"downloads":55775,"views":5563,"flag":1,"is_free":0,"freename":"免费","video_addr":"","statename":"已上架","flagname":"推荐","typename":"动作格斗","imagenum":0,"py":"WZRY","vtype":"1,2","vtypename":"[安卓]&nbsp;[IOS]&nbsp;","vtypeimgs":"<i class='android'><\/i><i class='ios'><\/i>","downs":0,"yy":0,"yyname":"中文","isnetwork":0,"isgame":0,"true_good_evaluation":0,"true_bad_evaluation":0,"true_downloads":25060,"true_views":0,"tz":"竞技,","fmoeny":0,"isintegral":0,"gflag":0,"libaoimg":"","zqshowimg":"","iszq":0,"zqurl":"http://","moulds":"0","bgimg":"","remarks":"全球首款5v5英雄公平对战手游","appscore":8.9,"appscore1":"8","appscore2":".9","trueappscore":5,"zqcode":"wzry","isnewgame":1,"packagename":"","zqflag":0,"zqscore":"5.0"}
     * img : [{"id":254647,"address":"/allimgs/img_iapp/201509/_1443491126668.jpg","orderno":4,"state":0,"appid":"1443491252"},{"id":254646,"address":"/allimgs/img_iapp/201509/_1443491125669.jpg","orderno":3,"state":0,"appid":"1443491252"},{"id":254645,"address":"/allimgs/img_iapp/201509/_1443491124676.jpg","orderno":2,"state":0,"appid":"1443491252"},{"id":254644,"address":"/allimgs/img_iapp/201509/_1443491123830.jpg","orderno":1,"state":0,"appid":"1443491252"},{"id":254643,"address":"/allimgs/img_iapp/201509/_1443491122872.jpg","orderno":0,"state":0,"appid":"1443491252"}]
     */

    private AppBean app;
    private List<ImgBean> img;

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public List<ImgBean> getImg() {
        return img;
    }

    public void setImg(List<ImgBean> img) {
        this.img = img;
    }

    public static class AppBean {
        /**
         * id : 1443491252
         * name : 王者荣耀
         * developers : 腾讯游戏
         * appsize : 231M
         * version :
         * logo : /allimgs/img_iapp/201509/_1443491274999.png
         * download_addr : http://dlied5.myapp.com/myapp/1104466820/sgame/2017_SGame_App_Android_1.8.2.3.apk
         * upload_time : 1443491220
         * add_time : 1443491247
         * state : 1
         * keywords : 王者联盟，王者荣耀，MOBA
         * operator : 倩女幽魂
         * typeid : 3
         * orderid : 1
         * description : 《王者荣耀》5V5英雄公平对战 手游，腾讯最新MOBA 大作!5V5、3v3、1v1，多样模式一键体验，海量英雄
         随心选择!10秒实时跨区匹配，与好友组队登顶王者之巅!一血、五杀、秒开团，实力操作公平对战 ，回归MOBA初心!10.28不限号
         ，全世界撸友，手机团战开黑!
         * good_evaluation : 0
         * bad_evaluation : 0
         * downloads : 55775
         * views : 5563
         * flag : 1
         * is_free : 0
         * freename : 免费
         * video_addr :
         * statename : 已上架
         * flagname : 推荐
         * typename : 动作格斗
         * imagenum : 0
         * py : WZRY
         * vtype : 1,2
         * vtypename : [安卓]&nbsp;[IOS]&nbsp;
         * vtypeimgs : <i class='android'></i><i class='ios'></i>
         * downs : 0
         * yy : 0
         * yyname : 中文
         * isnetwork : 0
         * isgame : 0
         * true_good_evaluation : 0
         * true_bad_evaluation : 0
         * true_downloads : 25060
         * true_views : 0
         * tz : 竞技,
         * fmoeny : 0
         * isintegral : 0
         * gflag : 0
         * libaoimg :
         * zqshowimg :
         * iszq : 0
         * zqurl : http://
         * moulds : 0
         * bgimg :
         * remarks : 全球首款5v5英雄公平对战手游
         * appscore : 8.9
         * appscore1 : 8
         * appscore2 : .9
         * trueappscore : 5
         * zqcode : wzry
         * isnewgame : 1
         * packagename :
         * zqflag : 0
         * zqscore : 5.0
         */

        private int id;
        private String name;
        private String developers;
        private String appsize;
        private String version;
        private String logo;
        private String download_addr;
        private int upload_time;
        private int add_time;
        private int state;
        private String keywords;
        private String operator;
        private int typeid;
        private int orderid;
        private String description;
        private int good_evaluation;
        private int bad_evaluation;
        private int downloads;
        private int views;
        private int flag;
        private int is_free;
        private String freename;
        private String video_addr;
        private String statename;
        private String flagname;
        private String typename;
        private int imagenum;
        private String py;
        private String vtype;
        private String vtypename;
        private String vtypeimgs;
        private int downs;
        private int yy;
        private String yyname;
        private int isnetwork;
        private int isgame;
        private int true_good_evaluation;
        private int true_bad_evaluation;
        private int true_downloads;
        private int true_views;
        private String tz;
        private int fmoeny;
        private int isintegral;
        private int gflag;
        private String libaoimg;
        private String zqshowimg;
        private int iszq;
        private String zqurl;
        private String moulds;
        private String bgimg;
        private String remarks;
        private double appscore;
        private String appscore1;
        private String appscore2;
        private int trueappscore;
        private String zqcode;
        private int isnewgame;
        private String packagename;
        private int zqflag;
        private String zqscore;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDevelopers() {
            return developers;
        }

        public void setDevelopers(String developers) {
            this.developers = developers;
        }

        public String getAppsize() {
            return appsize;
        }

        public void setAppsize(String appsize) {
            this.appsize = appsize;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDownload_addr() {
            return download_addr;
        }

        public void setDownload_addr(String download_addr) {
            this.download_addr = download_addr;
        }

        public int getUpload_time() {
            return upload_time;
        }

        public void setUpload_time(int upload_time) {
            this.upload_time = upload_time;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getGood_evaluation() {
            return good_evaluation;
        }

        public void setGood_evaluation(int good_evaluation) {
            this.good_evaluation = good_evaluation;
        }

        public int getBad_evaluation() {
            return bad_evaluation;
        }

        public void setBad_evaluation(int bad_evaluation) {
            this.bad_evaluation = bad_evaluation;
        }

        public int getDownloads() {
            return downloads;
        }

        public void setDownloads(int downloads) {
            this.downloads = downloads;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public String getFreename() {
            return freename;
        }

        public void setFreename(String freename) {
            this.freename = freename;
        }

        public String getVideo_addr() {
            return video_addr;
        }

        public void setVideo_addr(String video_addr) {
            this.video_addr = video_addr;
        }

        public String getStatename() {
            return statename;
        }

        public void setStatename(String statename) {
            this.statename = statename;
        }

        public String getFlagname() {
            return flagname;
        }

        public void setFlagname(String flagname) {
            this.flagname = flagname;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getImagenum() {
            return imagenum;
        }

        public void setImagenum(int imagenum) {
            this.imagenum = imagenum;
        }

        public String getPy() {
            return py;
        }

        public void setPy(String py) {
            this.py = py;
        }

        public String getVtype() {
            return vtype;
        }

        public void setVtype(String vtype) {
            this.vtype = vtype;
        }

        public String getVtypename() {
            return vtypename;
        }

        public void setVtypename(String vtypename) {
            this.vtypename = vtypename;
        }

        public String getVtypeimgs() {
            return vtypeimgs;
        }

        public void setVtypeimgs(String vtypeimgs) {
            this.vtypeimgs = vtypeimgs;
        }

        public int getDowns() {
            return downs;
        }

        public void setDowns(int downs) {
            this.downs = downs;
        }

        public int getYy() {
            return yy;
        }

        public void setYy(int yy) {
            this.yy = yy;
        }

        public String getYyname() {
            return yyname;
        }

        public void setYyname(String yyname) {
            this.yyname = yyname;
        }

        public int getIsnetwork() {
            return isnetwork;
        }

        public void setIsnetwork(int isnetwork) {
            this.isnetwork = isnetwork;
        }

        public int getIsgame() {
            return isgame;
        }

        public void setIsgame(int isgame) {
            this.isgame = isgame;
        }

        public int getTrue_good_evaluation() {
            return true_good_evaluation;
        }

        public void setTrue_good_evaluation(int true_good_evaluation) {
            this.true_good_evaluation = true_good_evaluation;
        }

        public int getTrue_bad_evaluation() {
            return true_bad_evaluation;
        }

        public void setTrue_bad_evaluation(int true_bad_evaluation) {
            this.true_bad_evaluation = true_bad_evaluation;
        }

        public int getTrue_downloads() {
            return true_downloads;
        }

        public void setTrue_downloads(int true_downloads) {
            this.true_downloads = true_downloads;
        }

        public int getTrue_views() {
            return true_views;
        }

        public void setTrue_views(int true_views) {
            this.true_views = true_views;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public int getFmoeny() {
            return fmoeny;
        }

        public void setFmoeny(int fmoeny) {
            this.fmoeny = fmoeny;
        }

        public int getIsintegral() {
            return isintegral;
        }

        public void setIsintegral(int isintegral) {
            this.isintegral = isintegral;
        }

        public int getGflag() {
            return gflag;
        }

        public void setGflag(int gflag) {
            this.gflag = gflag;
        }

        public String getLibaoimg() {
            return libaoimg;
        }

        public void setLibaoimg(String libaoimg) {
            this.libaoimg = libaoimg;
        }

        public String getZqshowimg() {
            return zqshowimg;
        }

        public void setZqshowimg(String zqshowimg) {
            this.zqshowimg = zqshowimg;
        }

        public int getIszq() {
            return iszq;
        }

        public void setIszq(int iszq) {
            this.iszq = iszq;
        }

        public String getZqurl() {
            return zqurl;
        }

        public void setZqurl(String zqurl) {
            this.zqurl = zqurl;
        }

        public String getMoulds() {
            return moulds;
        }

        public void setMoulds(String moulds) {
            this.moulds = moulds;
        }

        public String getBgimg() {
            return bgimg;
        }

        public void setBgimg(String bgimg) {
            this.bgimg = bgimg;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public double getAppscore() {
            return appscore;
        }

        public void setAppscore(double appscore) {
            this.appscore = appscore;
        }

        public String getAppscore1() {
            return appscore1;
        }

        public void setAppscore1(String appscore1) {
            this.appscore1 = appscore1;
        }

        public String getAppscore2() {
            return appscore2;
        }

        public void setAppscore2(String appscore2) {
            this.appscore2 = appscore2;
        }

        public int getTrueappscore() {
            return trueappscore;
        }

        public void setTrueappscore(int trueappscore) {
            this.trueappscore = trueappscore;
        }

        public String getZqcode() {
            return zqcode;
        }

        public void setZqcode(String zqcode) {
            this.zqcode = zqcode;
        }

        public int getIsnewgame() {
            return isnewgame;
        }

        public void setIsnewgame(int isnewgame) {
            this.isnewgame = isnewgame;
        }

        public String getPackagename() {
            return packagename;
        }

        public void setPackagename(String packagename) {
            this.packagename = packagename;
        }

        public int getZqflag() {
            return zqflag;
        }

        public void setZqflag(int zqflag) {
            this.zqflag = zqflag;
        }

        public String getZqscore() {
            return zqscore;
        }

        public void setZqscore(String zqscore) {
            this.zqscore = zqscore;
        }
    }

    public static class ImgBean {
        /**
         * id : 254647
         * address : /allimgs/img_iapp/201509/_1443491126668.jpg
         * orderno : 4
         * state : 0
         * appid : 1443491252
         */

        private int id;
        private String address;
        private int orderno;
        private int state;
        private String appid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getOrderno() {
            return orderno;
        }

        public void setOrderno(int orderno) {
            this.orderno = orderno;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }
}
