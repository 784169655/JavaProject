package com.security.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;

/**
 * Created by  邱伟
 * 2018/5/17 20:09
 */

public class MockServer {

    public static void main(String[] args) {
        WireMock.configureFor(9000);
        WireMock.removeAllMappings();

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/order/1"))
                .willReturn(WireMock.aResponse().withBody("{\"id\":1}").withStatus(200)));
    }
}