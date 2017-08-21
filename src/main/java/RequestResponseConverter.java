
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

public class RequestResponseConverter {
    public static HttpPost httpServletPostRequestToHttpPost(HttpServletRequest httpServletRequest) throws IOException {
        HttpPost httpPost = new HttpPost();
        Enumeration<String> headersNames = httpServletRequest.getHeaderNames();
        if (headersNames != null) {
            while (headersNames.hasMoreElements()) {
                String headerName = headersNames.nextElement();
                String headerValue = httpServletRequest.getHeader(headerName);
                httpPost.setHeader(headerName, headerValue);
            }
        }
        InputStream body = httpServletRequest.getInputStream();
        HttpEntity entity = new InputStreamEntity(body);
        httpPost.setEntity(entity);
        body.close();
        return httpPost;
    }// mdaaaaaaa

    public static HttpServletResponse httpResponseToHttpServletResponse(HttpResponse httpResponse){
        for (Header header : httpResponse.getAllHeaders()) {
            resp.setHeader(header.getName(), header.getValue());
        }
    }
}
