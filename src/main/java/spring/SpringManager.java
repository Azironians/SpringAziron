package spring;

import org.springframework.stereotype.Component;

@Component
public final class SpringManager {

    private String string;

    public final String getString() {
        return this.string;
    }

    public final void setString(final String string) {
        this.string = string;
    }
}