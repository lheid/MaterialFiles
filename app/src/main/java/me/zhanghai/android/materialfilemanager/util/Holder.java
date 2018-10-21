/*
 * Copyright (c) 2018 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialfilemanager.util;

import android.support.annotation.Nullable;

public class Holder<T> {

    @Nullable
    public T value;

    public Holder() {}

    public Holder(@Nullable T value) {
        this.value = value;
    }
}
