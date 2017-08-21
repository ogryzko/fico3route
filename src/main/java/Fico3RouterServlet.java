import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class Fico3RouterServlet extends HttpServlet {
    private HttpClient client;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        HttpPost request = new HttpPost();

        Enumeration<String> headersNames = req.getHeaderNames();
        if (headersNames != null) {
            while (headersNames.hasMoreElements()) {
                String headerName = headersNames.nextElement();
                String headerValue = req.getHeader(headerName);
                request.setHeader(headerName, headerValue);
            }
        }

        InputStream body = req.getInputStream();
        HttpEntity entity = new InputStreamEntity(body);
        request.setEntity(entity);
        HttpResponse response = client.execute(request);

        for (Header header : response.getAllHeaders()) {
            resp.setHeader(header.getName(), header.getValue());
        }

        HttpEntity respEntity = response.getEntity();
        OutputStream respOut = resp.getOutputStream();
        byte[] buffer = new byte[1024]; // Adjust if you want
        int bytesRead;
        while ((bytesRead = respEntity.getContent().read(buffer)) != -1) {
            respOut.write(buffer, 0, bytesRead);
        }
        respOut.flush();
        respOut.close();

    }
}
