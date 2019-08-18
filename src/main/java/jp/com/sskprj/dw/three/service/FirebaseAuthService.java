package jp.com.sskprj.dw.three.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirebaseAuthService implements InterfaceFirebaseAuthService {

    @Override
    public UserRecord selectUserRecord(String userId) {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(userId);
            log.info("Successfully fetched user data: {}", userRecord.getUid());
            return userRecord;
        } catch (FirebaseAuthException e) {
            log.error("Failed to fetch user data userId : {} - {}", userId, e);
            return null;
        }
    }

}
