import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberCodeParser {
    public static String getMemberCode(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        Pattern pattern = Pattern.compile("<MemberCode>(.+?)</MemberCode>");
        String line = null;
        Matcher matcher = null;
        while ((line = reader.readLine()) != null) {
            matcher = pattern.matcher(line);
            if(matcher.find()){
                return matcher.group(1);
            }
        }
        return null;
    }
}
