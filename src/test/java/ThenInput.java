import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenInput extends Stage<ThenInput> {
    @ExpectedScenarioState
    private boolean result;

    public ThenInput true_is_returned() {
        assertThat(result).isEqualTo(true);
        return self();
    }

    public ThenInput false_is_returned() {
        assertThat(result).isEqualTo(false);
        return self();
    }
}
