package goldloganalysis;

import loganalysis.model.GoldLogParamsModel;

public class GoldLogModel {
    private String account;
    private String gid;
    private String pid;
    private String plat;
    private String playerId;
    private String sid;
    private String time;
    private String type;
    private GoldLogParamsModel prop;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GoldLogParamsModel getProp() {
        return prop;
    }

    public void setProp(GoldLogParamsModel prop) {
        this.prop = prop;
    }
}
