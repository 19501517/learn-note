package ipinfo;

/**
 * @Author liuyefeng
 * @Date 2018/12/27 11:36
 */
public class IpInfo {
    private String country;
    private String region;
    private String city;
    private String isp;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    @Override
    public String toString() {
        return "ipinfo.IpInfo{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", isp='" + isp + '\'' +
                '}';
    }
}
