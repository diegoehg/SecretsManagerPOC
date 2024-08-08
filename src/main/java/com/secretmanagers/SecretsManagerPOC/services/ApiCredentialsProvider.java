package com.secretmanagers.SecretsManagerPOC.services;

import com.secretmanagers.SecretsManagerPOC.models.ApiCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class ApiCredentialsProvider {
    private SecretsManagerClient getSecretsManagerClient() {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.builder()
                .profileName("development")
                .build();

        return SecretsManagerClient.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(credentialsProvider)
                .build();
    }

    ApiCredentials getCredentials() {
        try(SecretsManagerClient secretsManagerClient = getSecretsManagerClient()) {
            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId("ApiCredentials")
                    .build();

            GetSecretValueResponse response = secretsManagerClient.getSecretValue(request);
            System.out.println(response.secretString());
        }

        return new ApiCredentials();
    }
}
