package steps;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import hw2.DFSM;

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
