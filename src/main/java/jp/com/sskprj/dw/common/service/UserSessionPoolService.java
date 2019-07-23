package jp.com.sskprj.dw.common.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserSessionPoolService {

    private static Map<String, Map<String, String>> dataMap = Maps.newConcurrentMap();

    /**
     * 対象セッションの情報を取得する。
     *
     * @param targetKey        取得したい情報のキー
     * @param sessionId        セッションID
     * @param lastAccessedTime この時間より古い情報が一致しない場合他にアクセスがあったと判断(一旦未使用)
     * @return 取得できない場合、null
     */
    public String selectUserSessionInfo(String targetKey, String sessionId, long lastAccessedTime) {
        log.info("キー:{},ID : {},時間 : {}", targetKey, sessionId, lastAccessedTime);
        Map<String, String> sessionData = dataMap.get(sessionId);
        if (sessionData == null) {
            log.info("{}のvalue : {}", targetKey, "null");
            return null;
        } else {
            log.info("{}のvalue : {}", targetKey, sessionData.get(targetKey));
            return sessionData.get(targetKey);
        }
    }

    public boolean put(String targetKey, String sessionId, String value, long lastAccessedTime) {
        boolean isUpdated = false;
        if (dataMap.get(sessionId) != null && dataMap.get(sessionId).containsKey(targetKey)) {
            isUpdated = true;
        }
        Map<String, String> valueMap = Maps.newConcurrentMap();
        valueMap.put(targetKey, value);
        dataMap.put(sessionId, valueMap);
        return isUpdated;
    }

}
