package net.iwangtbuyer.auth0;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.facebook.FacebookAuthHandler;
import com.auth0.android.facebook.FacebookAuthProvider;
import com.auth0.android.google.GoogleAuthHandler;
import com.auth0.android.google.GoogleAuthProvider;

/**
 * Created by yangshuyu on 2017/5/15.
 */
public class Lock {
    public Lock lock;
    AuthenticationAPIClient client;
    Auth0 auth0;

    public void Login(String client_id,String domain){
        auth0 = new Auth0(client_id, domain);

        client = new AuthenticationAPIClient(auth0);
        FacebookAuthProvider provider = new FacebookAuthProvider(client);
//        provider.setPermissions(Arrays.asList("public_profile", "user_photos"));
        FacebookAuthHandler handler = new FacebookAuthHandler(provider);

        GoogleAuthProvider provide = new GoogleAuthProvider("945656636977-9ag21vtt9k6do5f6nlptn0u96q10v2lh.apps.googleusercontent.com", client);
//        provide.setScopes(new Scope(DriveScopes.DRIVE_METADATA_READONLY));
//        provide.setRequiredPermissions(new String[]{"android.permission.GET_ACCOUNTS"});

        GoogleAuthHandler handle = new GoogleAuthHandler(provide);


//        lock = com.auth0.android.lock.Lock.newBuilder(auth0, callback)
//                .withAuthHandlers(handler, handle)
//                .closable(true)
////                .withTheme(Theme.newBuilder().withDarkPrimaryColor(R.color.text_black).withHeaderColor(R.color.auth0_header).withHeaderLogo(R.mipmap.ic_launcher).withHeaderTitle(R.string.app_name).withHeaderTitleColor(R.color.text_black).withPrimaryColor(R.color.bg_ff4f3c).build())
//                .withAuthButtonSize(AuthButtonSize.BIG)
////                // Add parameters to the Lock Builder
//                .useBrowser(false)
//                .build(this);
    }

}
