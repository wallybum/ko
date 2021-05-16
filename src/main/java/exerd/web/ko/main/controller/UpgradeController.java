package exerd.web.ko.main.controller;

import exerd.web.ko.main.constants.UpgradeConstants;
import exerd.web.ko.main.service.UpgradeService;
import exerd.web.ko.main.vo.UpgradeVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UpgradeController {
    @Autowired
    UpgradeService upgradeService;

    @GetMapping(value="/upgrade")
    public String main(){
        return "upgrade.html";
    }

    @GetMapping(value="/upgradeCheck/{licenseKey}")
    public ResponseEntity<UpgradeVO> check(@PathVariable("licenseKey") String licenseKey){
        return ResponseEntity.ok(upgradeService.licenseCheck(licenseKey));
    }

    /**
     * 조회한 License가 무료 업그레이드 대상인지 확인하는데 필요한 정보를 담는 Map을 JSON Object로 변환하여 반환합니다.
     * @return upgradeConstantJson
     */
    @GetMapping(value="/upgrade/info")
    @ResponseBody
    public JSONObject upgradeMap(){
        JSONObject upgradeConstantJson =  new JSONObject(UpgradeConstants.upgradeMap);
        return upgradeConstantJson;
    }
}
