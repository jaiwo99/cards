package com.jaiwo99.cards.command;

import javax.validation.constraints.NotNull;

/**
 * @author liang - jaiwo99@gmail.com
 */
public abstract class AbstractCommand {

    @NotNull
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
