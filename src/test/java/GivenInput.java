import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class GivenInput extends Stage<GivenInput>{
    @ProvidedScenarioState
    private String input;

    @ProvidedScenarioState
    private DFSM dfsm;

    public GivenInput $_input(String input){
        this.input = input;
        return this;
    }

    public GivenInput dfsm(){
        dfsm = new DFSM();
        return this;
    }
}
