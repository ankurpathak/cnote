package com.ankurpathak.server.validation.constraints.groups;

import javax.validation.GroupSequence;

/**
 * Created by ankur on 04-02-2017.
 */
@GroupSequence({
        Zero.class,
        One.class,
        Two.class,
        Three.class,
        Four.class,
        Five.class,
        Six.class,
        Seven.class,
        Eight.class,
        Nine.class
})
public interface Sequence {
}
