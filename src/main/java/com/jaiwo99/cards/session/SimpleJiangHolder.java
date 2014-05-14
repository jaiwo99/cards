package com.jaiwo99.cards.session;

import com.google.common.collect.Lists;
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
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class SimpleJiangHolder implements JiangHolder, Serializable {

    private static final long serialVersionUID = 1l;

    private List<Jiang> selection = Lists.newArrayList();

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

    @Override
    public void setSelection(List<Jiang> selection) {
        this.selection = selection;
    }

    @Override
    public void setMajor(Jiang major) {
        this.major = major;
    }

    @Override
    public void setMinor(Jiang minor) {
        this.minor = minor;
    }
}
