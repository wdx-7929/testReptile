package com.wdx.spile;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class MyProxyCredentialsProvider implements CredentialsProvider {

	public Credentials getCredentials(AuthScheme scheme, String host, int port, boolean proxy)
			throws CredentialsNotAvailableException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		
		return null;
	}

}
