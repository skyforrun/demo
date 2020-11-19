package cn.com.kxyt.entity;

/**
 * @author zj
 * @Description: pg_byne库  表E_StandAddresss_PT
 * @date 2020/11/168:51
 */
public class StandAddress {
    private Integer id;
    private java.lang.String jjdwbm;
    private java.lang.String jjdwname;
    private java.lang.String sdbm;
    private java.lang.String sdname;
    private java.lang.String jwqbm;
    private java.lang.String jwqname;
    private java.lang.String address;
    private java.lang.String jybsname;
    private  Double zbx;
    private  Double zby;
    private java.lang.String buildno;
    private java.lang.String floorno;
    private java.lang.String houseid;
    private java.lang.String hunitno;
    private java.lang.String geom;
    private java.lang.String cno;
    private String imageSrc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJjdwbm() {
        return jjdwbm;
    }

    public void setJjdwbm(String jjdwbm) {
        this.jjdwbm = jjdwbm;
    }

    public String getJjdwname() {
        return jjdwname;
    }

    public void setJjdwname(String jjdwname) {
        this.jjdwname = jjdwname;
    }

    public String getSdbm() {
        return sdbm;
    }

    public void setSdbm(String sdbm) {
        this.sdbm = sdbm;
    }

    public String getSdname() {
        return sdname;
    }

    public void setSdname(String sdname) {
        this.sdname = sdname;
    }

    public java.lang.String getJwqbm() {
        return jwqbm;
    }

    public void setJwqbm(java.lang.String jwqbm) {
        this.jwqbm = jwqbm;
    }

    public java.lang.String getJwqname() {
        return jwqname;
    }

    public void setJwqname(java.lang.String jwqname) {
        this.jwqname = jwqname;
    }

    public java.lang.String getAddress() {
        return address;
    }

    public void setAddress(java.lang.String address) {
        this.address = address;
    }

    public java.lang.String getJybsname() {
        return jybsname;
    }

    public void setJybsname(java.lang.String jybsname) {
        this.jybsname = jybsname;
    }

    public Double getZbx() {
        return zbx;
    }

    public void setZbx(Double zbx) {
        this.zbx = zbx;
    }

    public Double getZby() {
        return zby;
    }

    public void setZby(Double zby) {
        this.zby = zby;
    }

    public java.lang.String getBuildno() {
        return buildno;
    }

    public void setBuildno(java.lang.String buildno) {
        this.buildno = buildno;
    }

    public java.lang.String getFloorno() {
        return floorno;
    }

    public void setFloorno(java.lang.String floorno) {
        this.floorno = floorno;
    }

    public java.lang.String getHouseid() {
        return houseid;
    }

    public void setHouseid(java.lang.String houseid) {
        this.houseid = houseid;
    }

    public java.lang.String getHunitno() {
        return hunitno;
    }

    public void setHunitno(java.lang.String hunitno) {
        this.hunitno = hunitno;
    }

    public java.lang.String getGeom() {
        return geom;
    }

    public void setGeom(java.lang.String geom) {
        this.geom = geom;
    }

    public java.lang.String getCno() {
        return cno;
    }

    public void setCno(java.lang.String cno) {
        this.cno = cno;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Override
    public String toString() {
        return "StandAddress{" +
                "id=" + id +
                ", jjdwbm='" + jjdwbm + '\'' +
                ", jjdwname='" + jjdwname + '\'' +
                ", sdbm='" + sdbm + '\'' +
                ", sdname='" + sdname + '\'' +
                ", jwqbm='" + jwqbm + '\'' +
                ", jwqname='" + jwqname + '\'' +
                ", address='" + address + '\'' +
                ", jybsname='" + jybsname + '\'' +
                ", zbx=" + zbx +
                ", zby=" + zby +
                ", buildno='" + buildno + '\'' +
                ", floorno='" + floorno + '\'' +
                ", houseid='" + houseid + '\'' +
                ", hunitno='" + hunitno + '\'' +
                ", geom='" + geom + '\'' +
                ", cno='" + cno + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                '}';
    }
}
