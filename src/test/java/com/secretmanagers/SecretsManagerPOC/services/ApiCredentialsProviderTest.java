package com.secretmanagers.SecretsManagerPOC.services;

import com.secretmanagers.SecretsManagerPOC.models.ApiCredentials;
import org.junit.jupiter.api.Test;

public class ApiCredentialsProviderTest {
    @Test
    void testGetCredentials() {
        ApiCredentialsProvider client = new ApiCredentialsProvider();
        ApiCredentials apiCredentials = client.getCredentials();
    }
}
