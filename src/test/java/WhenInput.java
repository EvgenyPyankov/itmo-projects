import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class WhenInput extends Stage<WhenInput> {
    @ExpectedScenarioState
    private DFSM dfms;

    @ExpectedScenarioState
    private String input;

    @ProvidedScenarioState
    private boolean result;

    public WhenInput input_is_checked(){
        result = dfms.check(input);
        return self();
    }
}
