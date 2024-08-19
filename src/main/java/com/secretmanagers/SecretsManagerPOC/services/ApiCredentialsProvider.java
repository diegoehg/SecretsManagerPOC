package com.secretmanagers.SecretsManagerPOC.services;

import com.amazonaws.secretsmanager.caching.SecretCache;
import com.amazonaws.secretsmanager.caching.SecretCacheConfiguration;
import com.secretmanagers.SecretsManagerPOC.models.ApiCredentials;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    private SecretCache getSecretCache() {
        SecretCacheConfiguration configuration = new SecretCacheConfiguration()
                .withClient(getSecretsManagerClient())
                .withMaxCacheSize(1)
                .withCacheItemTTL(TimeUnit.MILLISECONDS.convert(2, TimeUnit.HOURS));

        return new SecretCache(configuration);
    }

    ApiCredentials getCredentials() {
        ApiCredentials apiCredentials;

        try(SecretsManagerClient secretsManagerClient = getSecretsManagerClient()) {
            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId("ApiCredentials")
                    .build();
            GetSecretValueResponse response = secretsManagerClient.getSecretValue(request);

            apiCredentials = parseApiCredentials(response.secretString());
        }

        return apiCredentials;
    }

    ApiCredentials getCredentialsFromCache() {
        ApiCredentials credentials;

        try(SecretCache cache = getSecretCache()) {
            String secretString = cache.getSecretString("ApiCredentials");
            credentials = parseApiCredentials(secretString);
        }

        return credentials;
    }

    private ApiCredentials parseApiCredentials(String secretString) {
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = parser.parseMap(secretString);

        return new ApiCredentials(
                map.get("ApiClientId").toString(),
                map.get("ApiJsonUserId").toString(),
                map.get("ApiPassword").toString(),
                map.get("ApiDomain").toString(),
                map.get("ApiPcc").toString(),
                map.get("ApiJsonDomain").toString()
        );
    }
}
