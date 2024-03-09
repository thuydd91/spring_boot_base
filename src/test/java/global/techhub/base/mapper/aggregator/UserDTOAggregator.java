package global.techhub.base.mapper.aggregator;

import global.techhub.base.dto.user.UserDTO;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class UserDTOAggregator implements ArgumentsAggregator {

    @Override
    public UserDTO aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) throws ArgumentsAggregationException {
        UserDTO user = new UserDTO();
        user.setUsername(arguments.getString(0));
        user.setPassword(arguments.getString(1));
        user.setEmail(arguments.getString(2));
        user.setPhone(arguments.getString(3));

        return user;
    }
}
