package exerd.web.ko.main.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
public class DownloadMailVO {
    public DownloadMailVO() {
    }

    public DownloadMailVO(String mail, String name, String company) {
        super();
        this.mail = mail;
        this.name = name;
        this.company = company;
    }

    public final static int TRUE = 1;
    public final static int FALSE = 0;
    private int idx;
    private String mail;
    private String name;
    private String company;
    private int guideMailCount;
    private Date recentlyGuideMail;
    private int relayingDenied;
    private String version;
    private Date regist;
    private int requestButtonCount;
    private int requestLinkCount;
    private int purchased;
    private String uid;


}
