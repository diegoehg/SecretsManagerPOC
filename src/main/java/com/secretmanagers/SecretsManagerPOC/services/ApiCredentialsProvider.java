package com.secretmanagers.SecretsManagerPOC.services;

import com.secretmanagers.SecretsManagerPOC.models.ApiCredentials;
import java.util.Map;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
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
        ApiCredentials apiCredentials;

        try(SecretsManagerClient secretsManagerClient = getSecretsManagerClient()) {
            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId("ApiCredentials")
                    .build();

            GetSecretValueResponse response = secretsManagerClient.getSecretValue(request);
            JsonParser parser = JsonParserFactory.getJsonParser();

            Map<String, Object> map = parser.parseMap(response.secretString());
            apiCredentials = new ApiCredentials(
                  map.get("ApiClientId").toString(),
                  map.get("ApiJsonUserId").toString(),
                  map.get("ApiPassword").toString(),
                  map.get("ApiDomain").toString(),
                  map.get("ApiPcc").toString(),
                  map.get("ApiJsonDomain").toString()
            );
        }

        return apiCredentials;
    }
}
