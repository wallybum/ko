package exerd.web.ko.main.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 조회한 라이센스가 무료 업그레이드 대상인지를 판단하는데 필요한 항목을 정의해놓은 클래스입니다.
 * 무료 업그레이드 대상 조건은 2016년 1월 1일 이전의 구매한 사용자에 해당되며,
 * 무료 업그레이드 대상자가 아닐 경우 업그레이드 비용은 300,000원 입니다.
 */
public class UpgradeConstants {
    public static final Map<String, Integer> upgradeMap = new HashMap<String, Integer>(){{
        put("UPGRADE_PRICE", 300000);
        put("FREE_UPGRADE_LIMIT", 20160101);
    }};
}
