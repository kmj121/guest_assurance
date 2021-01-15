package com.vsc.guest_assurance.common;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.models.extensions.DirectoryObject;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.IDirectoryObjectCollectionWithReferencesPage;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientCredentialProvider implements IAuthenticationProvider {


    // 租户 ID
    private static final String tenentId = "c1eb5112-7946-4c9d-bc57-40040cfe3a91";

    // ChinaEmployeeDataGetter 应用 ID、秘钥
    private static final String clientId = "75db9ef1-7571-4fb6-b2ca-78b6b11cb72c";
    private static final String clientSecret = "trezoFVONKI4992(()otG2$";

    // 申请 token 时的 scope
    private static final String MSGraphScope = "https://graph.microsoft.com/.default";
    // 调用 Graph API 时的 scope
    private static final String[] scope = {"Directory.Read.All", "User.Read.All"};

    // 授权方式
    private static final String grantType = "client_credentials";

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

    public ClientCredentialProvider(){
        this.token = getTokenForApplication();
    }

    public ClientCredentialProvider(String token){
        this.token = token;
    }

    // Redirect URI
    private static final String redirectUri = "msal75db9ef1-7571-4fb6-b2ca-78b6b11cb72c://auth";

    @Override
    public void authenticateRequest(IHttpRequest iHttpRequest) {
        iHttpRequest.addHeader("Authorization", token);
    }

    public static String getTokenForApplication() {
        String postURL = "https://login.microsoftonline.com/" + tenentId + "/oauth2/v2.0/token";
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("scope", MSGraphScope);
        params.put("client_secret", clientSecret);
        params.put("grant_type", grantType);
        String msg = HttpRequestUtil.sendPost(postURL, params);

        // 从返回信息中提取 token，储存
        JSONObject jsonObject = JSONObject.fromObject(msg);
        String token = jsonObject.getString("access_token");
        return token;
    }


    public static List<DirectoryObject> getUsers() {

        List<DirectoryObject> result = new ArrayList<>();
        ClientCredentialProvider authProvider = new ClientCredentialProvider();
        IGraphServiceClient graphClient = GraphServiceClient
                .builder()
                .authenticationProvider(authProvider)
                .buildClient();

        IDirectoryObjectCollectionWithReferencesPage members = graphClient
                .groups("902cf6ed-7469-4fda-bcfe-e565bcf6672c")
                .members()
                .buildRequest()
                .get();
        while (true){
            result.addAll(members.getCurrentPage());
            if(members.getNextPage() == null){
                break;
            }
            members = members.getNextPage().buildRequest()
                    .get();
        }

        IDirectoryObjectCollectionWithReferencesPage members1 = graphClient
                .groups("5c0aa92c-8461-462f-b23e-5b1bf18486d1")
                .members()
                .buildRequest()
                .get();
        while (true){
            result.addAll(members1.getCurrentPage());
            if(members1.getNextPage() == null){
                break;
            }
            members1 = members1.getNextPage().buildRequest()
                    .get();
        }

        System.out.println(result.size());
        return result;
    }

    public static User getUser(String token){
        ClientCredentialProvider authProvider = new ClientCredentialProvider(token);
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


    public static void main(String[] argv) {

//        File file = new File("https://cninsessstor001d.blob.core.chinacloudapi.cn/test/"+ "112358.jpg");
//        System.out.println(file.getName());
//        getUsers();
//        User user = getUser("Bearer eyJ0eXAiOiJKV1QiLCJub25jZSI6Imd2UFdESjNQMkdLaUUtRnRiVFpqblhlVGVjQ1Y2X3ktTjdGNW4yT0RXNlkiLCJhbGciOiJSUzI1NiIsIng1dCI6ImFQY3R3X29kdlJPb0VOZzNWb09sSWgydGlFcyIsImtpZCI6ImFQY3R3X29kdlJPb0VOZzNWb09sSWgydGlFcyJ9.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTAwMDAtYzAwMC0wMDAwMDAwMDAwMDAiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC9jMWViNTExMi03OTQ2LTRjOWQtYmM1Ny00MDA0MGNmZTNhOTEvIiwiaWF0IjoxNTcxMDM1NjA3LCJuYmYiOjE1NzEwMzU2MDcsImV4cCI6MTU3MTAzOTUwNywiYWNjdCI6MCwiYWNyIjoiMSIsImFpbyI6IjQyVmdZR0FMMW44NjdhWTliOFZhbjBKWkJlOHoxOWFLNUFaTlN2TzRtUlkrL2VRZjJWMEEiLCJhbXIiOlsicHdkIl0sImFwcF9kaXNwbGF5bmFtZSI6IkNOLUlOU1QtRVNTLVdFQiIsImFwcGlkIjoiYThkNWYzZDEtMDk3ZS00MDM4LTg1MGEtODNmN2E2ZDljNjFmIiwiYXBwaWRhY3IiOiIwIiwiaXBhZGRyIjoiMjEwLjIyLjExMS4yMDIiLCJuYW1lIjoiQXp1cmUgQ2hpbmEgVGVzdCIsIm9pZCI6ImM2MzE4MzdiLTYzZmUtNGRmNC04ZDQxLTcwN2MwNTZlOGVlNCIsInBsYXRmIjoiMyIsInB1aWQiOiIxMDAzQkZGREFCNzlGRUU5Iiwic2NwIjoib3BlbmlkIHByb2ZpbGUgVXNlci5SZWFkIGVtYWlsIiwic3ViIjoib0RCNUZ2X2xET2FNNElPRVlBVk5WakNSV2JidkhxVFFQc1VxclNMNUpuOCIsInRpZCI6ImMxZWI1MTEyLTc5NDYtNGM5ZC1iYzU3LTQwMDQwY2ZlM2E5MSIsInVuaXF1ZV9uYW1lIjoiYXp1cmUuY2hpbmF0ZXN0QG5hbGNvLm1pY3Jvc29mdG9ubGluZS5jb20iLCJ1cG4iOiJhenVyZS5jaGluYXRlc3RAbmFsY28ubWljcm9zb2Z0b25saW5lLmNvbSIsInV0aSI6IlFpZXJkeGc1NWtlTEFRVVVvQzJoQUEiLCJ2ZXIiOiIxLjAiLCJ4bXNfc3QiOnsic3ViIjoiYVNnaWdQZFdUVDlrN2NmZ1l0MktrOVhVdmxuRFRtWUZXeV9sVzJZVHM1dyJ9LCJ4bXNfdGNkdCI6MTMxNTA1NDQ3M30.JYddFQq-iSg8NOK6bJpEmks27AVskm5vU2fVExHTRZFF0Ltmzk9eaS-_TCG9gDmo_TT9726vBrG-f2DUid_ra2imJaxi4w41WDqL5vRILbaetltXg0gPGb-iJM5xdy-jqS-SLRPnPsmdbDH_20zbKN-zji6ETR6yfxZnsQqQt-KmYf8hYBxFoFhQTADCtyDwQsO5U9E6hn_lEBr-1AAAdSwWiAZklbZG7WwdrwWtQZluhfkfQVKmFdqAZf0LWZtT4StsaEm-x8iS1t2i9Ru5snQbHJAOwAP3JOuEMmIhS31FHzieS5x1xPu6-0ghhsvw2B6MdowZ6xBv1Cm81_1cFA");
//        System.out.println(user.displayName);
//        System.out.println(user.userPrincipalName);
    }
}
