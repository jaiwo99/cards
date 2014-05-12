package com.jaiwo99.cards.view;

import com.jaiwo99.cards.domain.Jiang;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public final class ChooseView implements Serializable {

    private static final long serialVersionUID = 1l;
    private final List<Jiang> selection;

    public ChooseView(final List<Jiang> selection) {
        Validate.notNull(selection, "Choose view must have a non-null selection list");
        this.selection = selection;
    }

    public List<Jiang> getSelection() {
        return Collections.unmodifiableList(selection);
    }
}
