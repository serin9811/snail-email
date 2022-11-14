package kr.snailemail.snailemail.service;

import kr.snailemail.snailemail.dto.SignInUserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.snailemail.snailemail.dto.GeneralResponse;
import kr.snailemail.snailemail.dto.SignUpUserDto;
import kr.snailemail.snailemail.repository.UserRepository;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CognitoUserService {

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;
    @Value("${aws.cognito.appClientId}")
    private String appClientId;
    
    CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
        .region(Region.AP_NORTHEAST_2)
        .credentialsProvider(ProfileCredentialsProvider.create())
        .build();

    public GeneralResponse<?> getUsers() {
        try {
            ListUsersRequest request = ListUsersRequest.builder().userPoolId(userPoolId).build();

            ListUsersResponse response = cognitoClient.listUsers(request);
            Map<String, String> usersAttr = new HashMap<>();
            response.users().forEach(user ->
            {System.out.println("username : " + user.username());
                usersAttr.put("username", user.username());
            });
            List<Map<String, String>> list = new ArrayList<>();
            list.add(usersAttr);


             return GeneralResponse.builder()
                 .status(true)
                 .message("success")
                 .data(list)
                 .build();

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return GeneralResponse.builder()
            .status(false)
            .message("fail to get user list")
            .data(null)
            .build();
    }
    
    public GeneralResponse<?> registerUser(SignUpUserDto request) {
        try {
            AttributeType emailAttr = AttributeType.builder()
                    .name("email")
                    .value(request.getUserEmail())
                    .build();

            AttributeType nameAttr = AttributeType.builder()
                    .name("name")
                    .value(request.getUserEmail())
                    .build();

            List<AttributeType> li = new ArrayList<>();
            li.add(emailAttr);
            li.add(nameAttr);

            SignUpRequest req = SignUpRequest.builder()
                    .clientId(appClientId)
                    .username(request.getUserEmail())
                    .password(request.getPassword())
                    .userAttributes(li)
                    .build();

            SignUpResponse res = cognitoClient.signUp(req);

            return GeneralResponse.builder()
                .status(true)
                .message("success")
                .data(null)
                .build();
        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());}

        return GeneralResponse.builder()
         .status(false)
         .message("fail to create user")
         .data(null)
         .build();
    }

    public GeneralResponse<?> signInUser(SignInUserDto request) {
        try {
            AdminInitiateAuthRequest req = AdminInitiateAuthRequest.builder()
                    .userPoolId(userPoolId)
                    .authFlow("USER_PASSWORD_AUTH")
                    .clientId(appClientId)
                    .build();

            AdminInitiateAuthResponse res = cognitoClient.adminInitiateAuth(req);
            System.out.println("accessToken => " + res.authenticationResult().accessToken());

            return GeneralResponse.builder()
                    .status(true)
                    .message("success")
                    .data(res.authenticationResult().accessToken())
                    .build();
        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }

        return GeneralResponse.builder()
                .status(false)
                .message("failed authentication")
                .data(null)
                .build();
    }

    public GeneralResponse<?> signOutUser(String accessToken) {
        try {
            GlobalSignOutRequest req = GlobalSignOutRequest.builder()
                    .accessToken(accessToken)
                    .build();

            cognitoClient.globalSignOut(req);

            return GeneralResponse.builder()
                    .status(true)
                    .message("success")
                    .data(null)
                    .build();

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return GeneralResponse.builder()
                .status(false)
                .message("failed to log out")
                .data(null)
                .build();
    }
}
