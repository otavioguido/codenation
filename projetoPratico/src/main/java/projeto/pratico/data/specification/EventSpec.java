package projeto.pratico.data.specification;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.OnTypeMismatch;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import projeto.pratico.data.model.Event;

@And({
        @Spec(path = "logLevel", spec = EqualIgnoreCase.class),
        @Spec(path = "eventDescription", spec = LikeIgnoreCase.class),
        @Spec(path = "eventLog", spec = LikeIgnoreCase.class),
        @Spec(path = "origin", spec = LikeIgnoreCase.class),
        @Spec(path = "quantity", spec = Equal.class, onTypeMismatch= OnTypeMismatch.EXCEPTION)
})
public interface EventSpec extends Specification<Event> {
}
