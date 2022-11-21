package kr.snailemail.snailemail.exception;

import java.io.Serializable;

public class CognitoUserException extends CustomException implements Serializable {

    public CognitoUserException(CustomError customError) {
        super(customError);
    }

}
