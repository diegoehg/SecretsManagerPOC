package com.secretmanagers.SecretsManagerPOC.services;

import com.secretmanagers.SecretsManagerPOC.models.ApiCredentials;

public class ApiCredentialsProvider {
    ApiCredentials getCredentials() {


        return new ApiCredentials();
    }
}
