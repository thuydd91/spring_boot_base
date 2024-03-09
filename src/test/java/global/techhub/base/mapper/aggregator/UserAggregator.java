package global.techhub.base.mapper.aggregator;

import global.techhub.base.entity.user.User;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class UserAggregator implements ArgumentsAggregator {

    @Override
    public User aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) throws ArgumentsAggregationException {
        User user = new User();
        user.setUsername(arguments.getString(0));
        user.setPassword(arguments.getString(1));
        user.setEmail(arguments.getString(2));
        user.setPhone(arguments.getString(3));

        return user;
    }
}
