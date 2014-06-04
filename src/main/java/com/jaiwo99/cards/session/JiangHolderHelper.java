package com.jaiwo99.cards.session;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.JiangType;
import org.thymeleaf.util.Validate;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Named
public class JiangHolderHelper {

    @Inject
    private JiangHolder jiangHolder;

    public void updateJiang(Jiang jiang, JiangType type) {
        Validate.notNull(jiang, "Jiang cannot be null");
        Validate.notNull(type, "JiangType cannot be null");

        switch (type) {
            case MAJOR:
                jiangHolder.setMajor(jiang);
                if(jiang.equals(jiangHolder.getMinor())) {
                    jiangHolder.setMinor(null);
                }
                return;
            case MINOR:
                jiangHolder.setMinor(jiang);
                if(jiang.equals(jiangHolder.getMajor())) {
                    jiangHolder.setMajor(null);
                }
                return;
            default:
                throw new IllegalStateException("Jiang type: " + type + " is not supported yet!");
        }
    }

    public void swapJiangPosition() {
        final Jiang jiang = jiangHolder.getMajor();
        jiangHolder.setMajor(jiangHolder.getMinor());
        jiangHolder.setMinor(jiang);
    }
}
