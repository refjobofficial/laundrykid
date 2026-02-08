package com.limnstage.laundry.services;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleTokenVerifier {
    private static final String CLIENT_ID =
            "801031480676-6sgn6bha0osdajsvsbp49bji8dodpt9v.apps.googleusercontent.com";

    public GoogleIdToken.Payload verify(String idTokenString)
            throws Exception {

        GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(
                        new NetHttpTransport(),
                        new JacksonFactory())
                        .setAudience(Collections.singletonList(CLIENT_ID))
                        .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken == null) {
            throw new RuntimeException("Invalid Google ID token");
        }

        return idToken.getPayload();
    }
}
