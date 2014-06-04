package com.jaiwo99.cards.session;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.Kingdom;
import com.jaiwo99.cards.domain.Skill;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.jaiwo99.cards.domain.JiangType.MAJOR;
import static com.jaiwo99.cards.domain.JiangType.MINOR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JiangHolderHelperTest {

    private JiangHolderHelper instance;

    private JiangHolder jiangHolder;
    private Jiang major;
    private Jiang minor;

    @Before
    public void setUp() {
        jiangHolder = new SimpleJiangHolder();
        major = new Jiang("major", Kingdom.QUN, 3, null, new Skill());
        major.setId("id1");
        minor = new Jiang("minor", Kingdom.QUN, 3, null, new Skill());
        minor.setId("id2");
        jiangHolder.setMajor(major);
        jiangHolder.setMinor(minor);
        instance = new JiangHolderHelper();
        ReflectionTestUtils.setField(instance, "jiangHolder", jiangHolder);
        assertThat(jiangHolder.getMajor(), hasProperty("name", is(major.getName())));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is(minor.getName())));
    }

    @Test
    public void swapJiangPosition_should_swap_major_and_minor() throws Exception {
        instance.swapJiangPosition();
        assertThat(jiangHolder.getMajor(), hasProperty("name", is(minor.getName())));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is(major.getName())));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateJiang_should_not_accept_null_value_of_jiang() throws Exception {
        instance.updateJiang(null, MINOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateJiang_should_not_accept_null_value_of_jiangType() throws Exception {
        instance.updateJiang(major, null);
    }

    @Test
    public void updateJiang_should_update_major() throws Exception {
        final Jiang jiang = new Jiang("new_major", Kingdom.SHU, 3, "picname", new Skill());
        jiang.setId("id4");
        instance.updateJiang(jiang, MAJOR);
        assertThat(jiangHolder.getMajor(), hasProperty("name", is(jiang.getName())));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is(minor.getName())));
    }

    @Test
    public void updateJiang_should_update_minor() throws Exception {
        final Jiang jiang = new Jiang("new_minor", Kingdom.SHU, 3, "picname", new Skill());
        jiang.setId("id5");
        instance.updateJiang(jiang, MINOR);
        assertThat(jiangHolder.getMajor(), hasProperty("name", is(major.getName())));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is(jiang.getName())));
    }

    @Test
    public void updateJiang_should_remove_minor_when_setting_major_to_minor() throws Exception {
        instance.updateJiang(minor, MAJOR);
        assertThat(jiangHolder.getMajor(), hasProperty("name", is(minor.getName())));
        assertThat(jiangHolder.getMinor(), is(nullValue()));
    }

    @Test
    public void updateJiang_should_remove_major_when_setting_minor_to_major() throws Exception {
        instance.updateJiang(major, MINOR);
        assertThat(jiangHolder.getMajor(), is(nullValue()));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is(major.getName())));
    }

}