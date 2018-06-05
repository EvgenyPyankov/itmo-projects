import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;
import steps.GivenInput;
import steps.ThenOutput;
import steps.WhenInput;

public class DFSMCheckTest extends ScenarioTest<GivenInput, WhenInput, ThenOutput> {

    @Test
    public void aabc_is_valid_input() {
        given().dfsm()
                .$_input("aabc");
        when().input_is_checked();
        then().true_is_returned();
    }

    @Test
    public void ababcaabb_is_valid_input() {
        given().dfsm()
                .$_input("ababcaabb");
        when().input_is_checked();
        then().true_is_returned();
    }

    @Test
    public void abcaabb_is_valid_input() {
        given().dfsm()
                .$_input("abcaabb");
        when().input_is_checked();
        then().true_is_returned();
    }

    @Test
    public void aaabb_is_valid_input() {
        given().dfsm()
                .$_input("aaabb");
        when().input_is_checked();
        then().true_is_returned();
    }

    @Test
    public void babaaabb_is_valid_input() {
        given().dfsm()
                .$_input("babaaabb");
        when().input_is_checked();
        then().true_is_returned();
    }

    @Test
    public void abcabcaabb_is_valid_input() {
        given().dfsm()
                .$_input("abcabcaabb");
        when().input_is_checked();
        then().true_is_returned();
    }


    @Test
    public void caabb_is_invalid_input() {
        given().dfsm()
                .$_input("caabb");
        when().input_is_checked();
        then().false_is_returned();
    }


    @Test
    public void ababaabb_is_valid_input() {
        given().dfsm()
                .$_input("ababaabb");
        when().input_is_checked();
        then().true_is_returned();
    }


    @Test
    public void abaaabbb_is_valid_input() {
        given().dfsm()
                .$_input("abaaabbb");
        when().input_is_checked();
        then().true_is_returned();
    }

    @Test
    public void caaabb_is_invalid_input() {
        given().dfsm()
                .$_input("caaabb");
        when().input_is_checked();
        then().false_is_returned();
    }

    @Test
    public void acaabb_is_invalid_input() {
        given().dfsm()
                .$_input("acaab");
        when().input_is_checked();
        then().false_is_returned();
    }


}
