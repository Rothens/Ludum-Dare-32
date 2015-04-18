package hu.afi.ld32.entities.minds;

/**
 * Created by Attila on 2015.04.18..
 */
public class ExecuteContext {

    private ExecuteStrategy strategy;

    public ExecuteContext(ExecuteStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(Object[] args) {
        return this.strategy.execute(args);
    }
}
