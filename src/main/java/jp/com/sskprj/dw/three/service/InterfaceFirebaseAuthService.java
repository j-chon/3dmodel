package jp.com.sskprj.dw.three.service;

import com.google.firebase.auth.UserRecord;

public interface InterfaceFirebaseAuthService {

    UserRecord selectUserRecord(String userId);
}
