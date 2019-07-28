package jp.com.sskprj.dw.common.service;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.UUID;

@Slf4j
public class UserSessionPoolService {

    private static Map<String, Map<String, String>> dataMap = Maps.newConcurrentMap();

    /**
     * 対象セッションの情報を取得する。
     *
     * @param targetKey 取得したい情報のキー
     * @param sessionId セッションID
     * @return 取得できない場合、null
     */
    public String selectUserSessionInfo(String targetKey, String sessionId) {
        log.info("検索 - キー : {},ID : {}", targetKey, sessionId);
        Map<String, String> sessionData = dataMap.get(sessionId);
        if (sessionData == null) {
            log.info("{}のvalue : {}", targetKey, "null");
            return null;
        } else {
            log.info("{}のvalue : {}", targetKey, sessionData.get(targetKey));
            return sessionData.get(targetKey);
        }
    }

    public boolean put(String targetKey, String sessionId, String value) {
        boolean isUpdated = false;
        if (dataMap.get(sessionId) != null && dataMap.get(sessionId).containsKey(targetKey)) {
            isUpdated = true;
        }
        Map<String, String> valueMap = Maps.newConcurrentMap();
        valueMap.put(targetKey, value);
        dataMap.put(sessionId, valueMap);
        log.info("登録 - {},{},{}", sessionId, targetKey, value);
        return isUpdated;
    }

    /**
     * 新しい一意のトークンを発行する。
     *
     * @return
     */
    public String createCsrfToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 指定したセッションデータを無効化する。(論理削除)
     *
     * @param sessionId  セッションID
     * @param sessionKey セッション情報の種類を特定するキー
     * @return true : 成功,false : データがなかった場合
     */
    public boolean removeSessionInfo(String sessionId, String sessionKey) {
        Map<String, String> sessionData = dataMap.get(sessionId);
        String value = sessionData.get(sessionKey);
        if (Strings.isNullOrEmpty(value)) {
            log.warn("データなし - キー : {}", sessionKey);
            return false;
        }
        // 削除済みマークを付ける。
        sessionData.put(sessionKey, value + "_deleted");

        return true;
    }
}
