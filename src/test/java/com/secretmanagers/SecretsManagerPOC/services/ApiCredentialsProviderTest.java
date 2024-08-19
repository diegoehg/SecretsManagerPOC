package com.secretmanagers.SecretsManagerPOC.services;

import com.secretmanagers.SecretsManagerPOC.models.ApiCredentials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ApiCredentialsProviderTest {
    @Test
    void testGetCredentials() {
        ApiCredentialsProvider client = new ApiCredentialsProvider();
        ApiCredentials apiCredentials = client.getCredentials();
        assertFalse(apiCredentials.apiClientId().isBlank());
        assertFalse(apiCredentials.apiDomain().isBlank());
        assertFalse(apiCredentials.apiJsonUserId().isBlank());
        assertFalse(apiCredentials.apiPassword().isBlank());
        assertFalse(apiCredentials.apiPcc().isBlank());
        assertFalse(apiCredentials.apiJsonDomain().isBlank());
    }

    @Test
    void testGetCredentialsFromCache() {
        ApiCredentialsProvider provider = new ApiCredentialsProvider();
        ApiCredentials apiCredentials = provider.getCredentialsFromCache();
        assertFalse(apiCredentials.apiClientId().isBlank());
        assertFalse(apiCredentials.apiDomain().isBlank());
        assertFalse(apiCredentials.apiJsonUserId().isBlank());
        assertFalse(apiCredentials.apiPassword().isBlank());
        assertFalse(apiCredentials.apiPcc().isBlank());
        assertFalse(apiCredentials.apiJsonDomain().isBlank());
    }
}
