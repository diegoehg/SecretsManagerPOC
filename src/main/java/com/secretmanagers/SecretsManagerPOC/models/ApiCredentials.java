package com.secretmanagers.SecretsManagerPOC.models;

public record ApiCredentials(String apiClientId, String apiJsonUserId, String apiPassword, String apiDomain,
                             String apiPcc, String apiJsonDomain) {}