package com.jaiwo99.cards.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author liang - jaiwo99@gmail.com
 */
public abstract class AbstractCommand {

    @NotNull
    @Size(min = 32, max = 32)
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
