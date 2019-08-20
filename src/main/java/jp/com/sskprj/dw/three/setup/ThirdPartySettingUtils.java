package jp.com.sskprj.dw.three.setup;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jp.com.sskprj.dw.three.setup.config.ApplicationConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 外部サービス設定ツール
 * 外部サービスの設定をApplicationクラスに追加する場合はここに処理を記述する。
 */
@Slf4j
public class ThirdPartySettingUtils {

    public static void initFirebaseOption(ApplicationConfiguration configuration) throws IOException {
        FileInputStream refreshToken = new FileInputStream(configuration.getFirebaseTokenPath());
        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(
                GoogleCredentials.fromStream(refreshToken)).setDatabaseUrl(configuration.getFirebaseDBUrl()).build();
        FirebaseApp.initializeApp(options);

    }
}
