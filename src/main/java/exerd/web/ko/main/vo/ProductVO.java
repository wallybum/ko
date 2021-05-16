package exerd.web.ko.main.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -5177843770811870313L;

    /** 제품번호 */
    private int productSeq;
    /** 제품명 */
    private String productName;
    /** 제품버전 */
    private String productVersion;
    /** 서비스여부 */
    private String serviceIn;
    /** 배포시작일 */
    private Date deployDate;
    /** 등록일 */
    private Date insertDate;
    /** 수정일 */
    private Date updateDate;
    /** 비고 */
    private String remark;
    /** 볼륨 라이선스 단가 */
    private int productVolPrice;
    /** 프로젝트 라이선스 단가 */
    private int productProPrice;



}
