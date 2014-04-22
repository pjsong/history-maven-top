package tools.validate;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adedayo Ominiyi
 */
public class MemberTest {

    public MemberTest() {
    }

    @Test
    public void testMemberWithNoValues() {
        Member member = new Member();
        // validate the input
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        if(violations.size()>0){
            for(ConstraintViolation<Member> v:violations){
                System.out.println(v.getMessage());
                System.out.println(v.getMessageTemplate());
                System.out.println(v.getInvalidValue());
            }
        }
        assertEquals(violations.size(), 5);
    }
}
