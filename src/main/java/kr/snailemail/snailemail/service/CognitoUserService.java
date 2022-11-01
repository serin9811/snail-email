package kr.snailemail.snailemail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.snailemail.snailemail.entity.GeneralResponse;
import kr.snailemail.snailemail.repository.UserRepository;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;

@Service
public class CognitoUserService {

    private UserRepository userRepository;
    
    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    public CognitoUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
        .region(Region.AP_NORTHEAST_2)
        .credentialsProvider(ProfileCredentialsProvider.create())
        .build();

    public GeneralResponse<?> getUsers() {
        try {
             ListUsersRequest request = ListUsersRequest.builder().userPoolId(userPoolId).build();
             ListUsersResponse response = cognitoClient.listUsers(request);
             response.users().forEach(user -> {System.out.println("user : " + user.username());
             });
             return GeneralResponse.builder()
                 .status(true)
                 .message("success")
                 .data(response.users())
                 .build();
        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        return GeneralResponse.builder()
            .status(false)
            .message("fail")
            .data(null)
            .build();
    }

}
