package com.jaiwo99.cards.session;

import com.jaiwo99.cards.domain.Jiang;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.jaiwo99.cards.domain.JiangType.MAJOR;
import static com.jaiwo99.cards.domain.JiangType.MINOR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JiangHolderUtilTest {

    private JiangHolderUtil instance;

    private JiangHolder jiangHolder;
    private Jiang major;
    private Jiang minor;

    @Before
    public void setUp() {
        jiangHolder = new SimpleJiangHolder();
        major = new Jiang("major");
        minor = new Jiang("minor");
        jiangHolder.setMajor(major);
        jiangHolder.setMinor(minor);
        instance = new JiangHolderUtil();
        ReflectionTestUtils.setField(instance, "jiangHolder", jiangHolder);
        assertThat(jiangHolder.getMajor(), hasProperty("name", is("major")));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is("minor")));
    }

    @Test
    public void swapJiangPosition_should_swap_major_and_minor() throws Exception {
        instance.swapJiangPosition();
        assertThat(jiangHolder.getMajor(), hasProperty("name", is("minor")));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is("major")));
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
        instance.updateJiang(new Jiang("new_major"), MAJOR);
        assertThat(jiangHolder.getMajor(), hasProperty("name", is("new_major")));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is("minor")));
    }

    @Test
    public void updateJiang_should_update_minor() throws Exception {
        instance.updateJiang(new Jiang("new_minor"), MINOR);
        assertThat(jiangHolder.getMajor(), hasProperty("name", is("major")));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is("new_minor")));
    }

    @Test
    public void updateJiang_should_remove_minor_when_setting_major_to_minor() throws Exception {
        instance.updateJiang(minor, MAJOR);
        assertThat(jiangHolder.getMajor(), hasProperty("name", is("minor")));
        assertThat(jiangHolder.getMinor(), is(nullValue()));
    }

    @Test
    public void updateJiang_should_remove_major_when_setting_minor_to_major() throws Exception {
        instance.updateJiang(major, MINOR);
        assertThat(jiangHolder.getMajor(), is(nullValue()));
        assertThat(jiangHolder.getMinor(), hasProperty("name", is("major")));
    }

}