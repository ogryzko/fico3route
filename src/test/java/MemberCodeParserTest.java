import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MemberCodeParserTest {
    private InputStream xmlInputGood;
    private InputStream xmlInputBad;

    @Before
    public void init(){
        String xmlString = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<AMFico>\n" +
                "  <Request>\n" +
                "    <Id>Test 1</Id>\n" +
                "    <Auth>\n" +
                "      <MemberCode>XXXXBBXXXXXX</MemberCode>\n" +
                "      <User>XXXXBBXXXXXX</User>\n" +
                "      <Password>password</Password>\n" +
                "    </Auth>\n" +
                "    <Accounts>\n" +
                "\t<Acc memberCode=\"XXXXBBXXXXXX\" cflag=\"Y\" cdate=\"01.11.2014\" cexpdate=\"01.02.2015\" cpurpose=\"1\" ocpurpose=\"\" ruser=\"ООО Банк Ипотечный\" liability=\"Y\">45507810100000002124</Acc>\n" +
                "\t<Acc memberCode=\"XXXXBBXXXXXX\" cflag=\"Y\" cdate=\"30.11.2014\" cexpdate=\"21.02.2017\" cpurpose=\"4\" ocpurpose=\"Сбор просроченной задолженности\" ruser=\"ООО Веселый коллектор\" liability=\"Y\">84644854TER548465POLY</Acc>\n" +
                "    </Accounts>\n" +
                "  </Request>\n" +
                "</AMFico>";

        String xmlStringBad = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>\n" +
                "<AMFico>\n" +
                "  <Request>\n" +
                "    <Id>Test 1</Id>\n" +
                "    <Auth>\n" +
                "      <User>XXXXBBXXXXXX</User>\n" +
                "      <Password>password</Password>\n" +
                "    </Auth>\n" +
                "    <Accounts>\n" +
                "\t<Acc memberCode=\"XXXXBBXXXXXX\" cflag=\"Y\" cdate=\"01.11.2014\" cexpdate=\"01.02.2015\" cpurpose=\"1\" ocpurpose=\"\" ruser=\"ООО Банк Ипотечный\" liability=\"Y\">45507810100000002124</Acc>\n" +
                "\t<Acc memberCode=\"XXXXBBXXXXXX\" cflag=\"Y\" cdate=\"30.11.2014\" cexpdate=\"21.02.2017\" cpurpose=\"4\" ocpurpose=\"Сбор просроченной задолженности\" ruser=\"ООО Веселый коллектор\" liability=\"Y\">84644854TER548465POLY</Acc>\n" +
                "    </Accounts>\n" +
                "  </Request>\n" +
                "</AMFico>";
        xmlInputGood = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
        xmlInputBad = new ByteArrayInputStream(xmlStringBad.getBytes(StandardCharsets.UTF_8));

    }

    @Test
    public void parseMemberCodeTest() throws IOException {
        String memberCodeActual = MemberCodeParser.getMemberCode(xmlInputGood);
        String memberCodeExpected = "XXXXBBXXXXXX";
        assertEquals(memberCodeExpected, memberCodeActual);
        assertNull(MemberCodeParser.getMemberCode(xmlInputBad));
    }

}
