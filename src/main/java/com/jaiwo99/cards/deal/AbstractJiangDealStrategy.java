package com.jaiwo99.cards.deal;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.JiangPicking;
import com.jaiwo99.cards.repository.JiangPickingRepository;
import com.jaiwo99.cards.repository.JiangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author liang - jaiwo99@gmail.com
 */
public abstract class AbstractJiangDealStrategy implements JiangDealStrategy {

    @Autowired
    private JiangRepository jiangRepository;

    @Autowired
    private JiangPickingRepository jiangPickingRepository;

    @Value("${jiang.picking.count}")
    private int jiangPickingCount;

    @Override
    public void reset() {
        jiangPickingRepository.deleteAll();
    }

    @Override
    public Jiang pick(String id) {
        jiangPickingRepository.save(new JiangPicking(id));
        return jiangRepository.findOne(id);
    }

    protected int getJiangPickingCount() {
        return jiangPickingCount;
    }

}
