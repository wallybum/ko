package exerd.web.ko.main.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpgradeVO {
    private CertificationVO certification;
    private ProductVO product;
    private boolean isSuccess = false;
    private String messages = "no data";
}
