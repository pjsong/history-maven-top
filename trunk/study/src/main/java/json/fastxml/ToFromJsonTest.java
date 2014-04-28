package json.fastxml;

//http://www.cowtowncoder.com/blog/archives/2009/01/entry_137.html
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import tools.validate.Member;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToFromJsonTest {

    @Test
    public void test() {
        ObjectMapper mapper = new ObjectMapper();
        Member member = new Member();
        member.setDateOfBirth(new Date());
        member.setEmailAddress("xxxff@123.org");
        member.setFirstName("ddd");
        File f = new File("ToFromJson.json");
        if (!f.exists()) {
            try {
                mapper.writeValue(new File("ToFromJson.json"), member);
            } catch (Exception e) {
            }
        }
        Member member1;
        try {
            member1 = mapper.readValue(new File("ToFromJson.json"), Member.class);
            System.out.println(member1.getEmailAddress());
        } catch (Exception e) {
            System.out.println("xx");
        }

    }

}
