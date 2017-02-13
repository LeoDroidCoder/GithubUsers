package com.leo.genesistask.presenters;

import com.leo.genesistask.helpers.SynchronousSchedulers;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.MockitoAnnotations;

/**
 * Created by leonid on 2/12/17.
 */

public class BaseUnitTest {

    @Rule
    public SynchronousSchedulers schedulers = new SynchronousSchedulers();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
