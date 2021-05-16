package exerd.web.ko.main.service;

import exerd.web.ko.main.vo.CertificationVO;
import exerd.web.ko.main.vo.ProductVO;
import exerd.web.ko.main.vo.UpgradeVO;
import org.springframework.stereotype.Service;

@Service
public class UpgradeService {

    /**
     * @param licenseKey
     * @return
     * 입력한 License에 대한 체크를 진행합니다.
     */
    public UpgradeVO licenseCheck(String licenseKey){
        CertificationService certificationService = new CertificationService();
        CertificationVO certificationVO = certificationService.getCertificationCustomerToLicenseKey(licenseKey);
        ProductService productService = new ProductService();
        ProductVO productVO = productService.searchServiceProductInfo();
        UpgradeVO upgradeVO = new UpgradeVO();

        if(certificationVO != null){
            if(productVO != null) {
                upgradeVO.setProduct(productVO);
            }
            upgradeVO.setCertification(certificationVO);
            upgradeVO.setSuccess(true);
            upgradeVO.setMessages("");
        }

        return upgradeVO;
    }
}
