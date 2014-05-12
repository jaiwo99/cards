package com.jaiwo99.cards.command;

import com.jaiwo99.cards.domain.JiangType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liang - jaiwo99@gmail.com
 */
public class JiangPickingCommand extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = 1l;

    @NotNull
    private JiangType jiangType;

    public JiangType getJiangType() {
        return jiangType;
    }

    public void setJiangType(JiangType jiangType) {
        this.jiangType = jiangType;
    }
}
