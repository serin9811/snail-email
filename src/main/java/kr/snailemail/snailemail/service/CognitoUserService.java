package kr.snailemail.snailemail.service;

import kr.snailemail.snailemail.dto.ConfirmCodeDto;
import kr.snailemail.snailemail.dto.SignInUserDto;
import kr.snailemail.snailemail.exception.CognitoUserException;
import kr.snailemail.snailemail.exception.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.snailemail.snailemail.dto.GeneralResponse;
import kr.snailemail.snailemail.dto.SignUpUserDto;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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

            cognitoClient.signUp(req);

            return GeneralResponse.builder()
                .status(true)
                .message("success")
                .data(request.getUserEmail())
                .build();
        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return GeneralResponse.builder()
         .status(false)
         .message("fail to create user")
         .data(null)
         .build();
    }

    public ResponseEntity<GeneralResponse> signInUser(SignInUserDto request) {
        try {
            Map<String, String> authParams = new HashMap<>();
            authParams.put("USERNAME", request.getUserEmail());
            authParams.put("PASSWORD", request.getPassword());

            AdminInitiateAuthRequest req = AdminInitiateAuthRequest.builder()
                    .userPoolId(userPoolId)
                    .authFlow("ADMIN_NO_SRP_AUTH")
                    .authParameters(authParams)
                    .clientId(appClientId)
                    .build();

            AdminInitiateAuthResponse res = cognitoClient.adminInitiateAuth(req);
            log.info("accessToken => " + res.authenticationResult().accessToken());

            return ResponseEntity.ok().body(
                    GeneralResponse.builder()
                            .status(true)
                            .message("success")
                            .data(res.authenticationResult().accessToken())
                            .build()
            );
        } catch (CognitoIdentityProviderException e) {
            throw new CognitoUserException(new CustomError(e.statusCode(), e.awsErrorDetails().errorMessage(), null));
        }
    }

    public GeneralResponse<?> signOutUser(String accessToken) {
        try {
            GlobalSignOutRequest req = GlobalSignOutRequest.builder()
                    .accessToken(accessToken)
                    .build();

            GlobalSignOutResponse res = cognitoClient.globalSignOut(req);
            log.info(res.sdkHttpResponse().toString());
            return GeneralResponse.builder()
                    .status(true)
                    .message("success")
                    .data(null)
                    .build();

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return GeneralResponse.builder()
                    .status(false)
                    .message(e.awsErrorDetails().errorMessage())
                    .data(null)
                    .build();
        }
    }

    public GeneralResponse<?> confirmSignUp(ConfirmCodeDto request) {
        try {
            ConfirmSignUpRequest req = ConfirmSignUpRequest.builder()
                    .clientId(appClientId)
                    .username(request.getUserEmail())
                    .confirmationCode(request.getCode())
                    .build();

            ConfirmSignUpResponse res = cognitoClient.confirmSignUp(req);
            log.info(res.sdkHttpResponse().toString());
            return GeneralResponse.builder()
                    .status(true)
                    .message("success")
                    .data(null)
                    .build();
        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return GeneralResponse.builder()
                    .status(false)
                    .message(e.awsErrorDetails().errorMessage())
                    .data(null)
                    .build();
        }
    }
}
