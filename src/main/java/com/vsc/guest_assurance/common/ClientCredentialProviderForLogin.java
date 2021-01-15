package com.vsc.guest_assurance.common;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.GraphServiceClient;

public class ClientCredentialProviderForLogin implements IAuthenticationProvider {


    // 租户 ID
    private static final String tenentId = "c1eb5112-7946-4c9d-bc57-40040cfe3a91";

    // ChinaEmployeeDataGetter 应用 ID、秘钥
    private static final String clientId = "1f2d61d4-7db9-4b32-85bd-a2d9788a4524";
//    private static String clientSecret = "trezoFVONKI4992(()otG2$";

    // 申请 token 时的 scope
    protected static final String MSGraphScope = "https://graph.microsoft.com/.default";
    // 调用 Graph API 时的 scope
    protected static final String[] scope = {"Directory.Read.All", "User.Read.All"};

    // 授权方式
    protected static final String grantType = "client_credentials";

    // Group ID
    //static public string ChinaAllStaffGroup = "b47811de-833b-45e1-9fcd-fd50e0bd283b";
    //static public string[] GroupId = new string[]
    //{
    //    "6b61d482-235f-4dd1-991f-13eb8147dd79",
    //    "2a970d1c-92c8-44a9-a421-908300d45774",
    //    "d3cc358a-2125-4d48-a43f-141b3d43ffa3",
    //    "4d4bd92c-6cc5-47cb-96f9-bbda71a3cc74",
    //    "fa830dab-4e43-441d-9c0b-d0c9af27ef99",
    //    "04bc3ea3-d55d-4962-8452-62a293a4cbee",
    //    "cdfb215b-ca3c-4994-8dc3-2390c2b31e19",
    //    "60d6a7fe-63ed-49cf-9044-02c7ad72ce66",
    //    "5b249544-8e71-431a-acac-e9a81dd0929f",
    //    "2b4ad2b4-4aef-42ca-839a-122d16af56c4",
    //    "83f30719-f1d7-4338-adac-55afa9cf5c9d",
    //    "a965173e-4579-42a0-8783-14966d0b241a"
    //};

    // 单独用 “902...” 这个组获取即可，包含了大中华区所有员工信息
    private static final String[] regionGC = new String[]{
                    "902cf6ed-7469-4fda-bcfe-e565bcf6672c",
                    "5c0aa92c-8461-462f-b23e-5b1bf18486d1"
            };

    private String token;

    public ClientCredentialProviderForLogin(String token){
        this.token = token;
    }

    // Redirect URI
    protected static final String redirectUri = "msal75db9ef1-7571-4fb6-b2ca-78b6b11cb72c://auth";

    @Override
    public void authenticateRequest(IHttpRequest iHttpRequest) {
        iHttpRequest.addHeader("Authorization", token);
    }


    public static User getUser(String token){
        ClientCredentialProviderForLogin authProvider = new ClientCredentialProviderForLogin(token);
        IGraphServiceClient graphClient = GraphServiceClient
                .builder()
                .authenticationProvider(authProvider)
                .buildClient();
        User user = graphClient.me()
                .buildRequest()
                .get();
        System.out.println(user.toString());
        return user;
    }

}
