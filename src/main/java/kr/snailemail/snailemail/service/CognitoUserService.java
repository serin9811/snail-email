package kr.snailemail.snailemail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.snailemail.snailemail.dto.GeneralResponse;
import kr.snailemail.snailemail.dto.SignUpUserDto;
import kr.snailemail.snailemail.repository.UserRepository;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
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
    
    public GeneralResponse<?> registerUser(SignUpUserDto request) {
     try {
        AttributeType attributeType = AttributeType.builder().name("email").value(request.getUserEmail()).build();

        AdminCreateUserRequest req = AdminCreateUserRequest.builder().userPoolId(userPoolId).username(request.getUserEmail()).userAttributes(attributeType).messageAction("SUPPRESS").build();

        AdminCreateUserResponse res = cognitoClient.adminCreateUser(req);

        System.out.println("user : " + res.user().username());
        
        return GeneralResponse.builder()
            .status(true)
            .message("success")
            .data(res.user().username())
            .build();

     } catch (CognitoIdentityProviderException e) {
         System.err.println(e.awsErrorDetails().errorMessage());}
     return GeneralResponse.builder()
         .status(false)
         .message("fail to create user")
         .data(null)
         .build();


    }
}
