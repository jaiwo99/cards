package com.jaiwo99.cards.view;

import com.jaiwo99.cards.domain.Jiang;

/**
 * @author liang - jaiwo99@gmail.com
 */
public final class JiangView {

    private final Jiang major;
    private final Jiang minor;

    public JiangView(final Jiang major, final Jiang minor) {
        this.major = major;
        this.minor = minor;
    }

    public Jiang getMajor() {
        return major;
    }

    public Jiang getMinor() {
        return minor;
    }
}
