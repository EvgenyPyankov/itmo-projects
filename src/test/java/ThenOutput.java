import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenOutput extends Stage<ThenOutput> {
    @ExpectedScenarioState
    private boolean result;

    public ThenOutput true_is_returned() {
        assertThat(result).isEqualTo(true);
        return self();
    }

    public ThenOutput false_is_returned() {
        assertThat(result).isEqualTo(false);
        return self();
    }
}
