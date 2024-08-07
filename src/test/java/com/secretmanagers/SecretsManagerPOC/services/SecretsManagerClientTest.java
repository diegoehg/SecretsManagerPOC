package com.secretmanagers.SecretsManagerPOC.services;

import com.secretmanagers.SecretsManagerPOC.models.ApiCredentials;
import org.junit.jupiter.api.Test;

public class SecretsManagerClientTest {
    @Test
    void testGetCredentials() {
        SecretsManagerClient client = new SecretsManagerClient();
        ApiCredentials apiCredentials = client.getCredentials();
    }
}
