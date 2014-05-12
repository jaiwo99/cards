package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Jiang;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SimpleJiangHolder implements JiangHolder, Serializable {

    private static final long serialVersionUID = 1l;

    private List<Jiang> selection;

    private Jiang major;

    private Jiang minor;

    @Override
    public Jiang getMajor() {
        return major;
    }

    @Override
    public Jiang getMinor() {
        return minor;
    }

    @Override
    public List<Jiang> getSelection() {
        return selection;
    }

    public void setSelection(List<Jiang> selection) {
        this.selection = selection;
    }

    public void setMajor(Jiang major) {
        this.major = major;
    }

    public void setMinor(Jiang minor) {
        this.minor = minor;
    }
}
