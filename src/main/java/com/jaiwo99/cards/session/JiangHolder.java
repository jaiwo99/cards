package com.jaiwo99.cards.session;

import com.jaiwo99.cards.domain.Jiang;

import java.util.List;

/**
 * @author liang - jaiwo99@gmail.com
 */
public interface JiangHolder {

    Jiang getMajor();

    Jiang getMinor();

    List<Jiang> getSelection();

    void setMajor(Jiang major);

    void setMinor(Jiang minor);

    void setSelection(List<Jiang> selection);
}
