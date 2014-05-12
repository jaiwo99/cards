package com.jaiwo99.cards.session;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.JiangType;

/**
 * @author liang - jaiwo99@gmail.com
 */
public class JiangHolderUtil {

    public static void updateJiang(JiangHolder holder, Jiang jiang, JiangType type) {
        switch (type) {
            case MAJOR:
                holder.setMajor(jiang);
                return;
            case MINOR:
                holder.setMinor(jiang);
                return;
            default:
                throw new IllegalStateException("Jiang type: " + type + " is not supported yet!");
        }
    }
}
