package com.limnstage.laundry.services;

import com.limnstage.laundry.Repository.UserRepository;
import com.limnstage.laundry.model.Users;
import com.limnstage.laundry.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final GoogleTokenVerifier verifier;
    private final UserRepository userRepository;

    public Map<String, Object> loginWithGoogle(String idToken) throws Exception {

        GoogleIdToken.Payload payload = verifier.verify(idToken);
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String picture = (String) payload.get("picture");


        Users user = userRepository.findByEmail(email)
                .orElseGet(() -> {

                    Users newUser = Users.builder()
                            .name(name)
                            .email(email)
                            .isActive(true)
                            .build();

                    return userRepository.save(newUser);
                });


        String jwt = JwtUtil.generateToken(email);

        return Map.of(
                "token", jwt,
                "email", user.getEmail(),
                "name", user.getName(),
                "uid", user.getId(),
                "picture", picture,
                "isNewUser", user.getId() != null // optional flag
        );
    }
}