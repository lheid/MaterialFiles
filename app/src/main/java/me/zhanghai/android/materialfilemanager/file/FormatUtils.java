/*
 * Copyright (c) 2018 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialfilemanager.file;

import android.content.Context;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.text.format.Time;

import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

import me.zhanghai.android.materialfilemanager.R;

public class FormatUtils {

    private FormatUtils() {}

    /*
     * @see android.text.format.Formatter#formatBytes(Resources, long, int)
     */
    public static boolean isHumanReadableSizeInBytes(long size) {
        return size <= 900;
    }

    public static String formatHumanReadableSize(long size, Context context) {
        return Formatter.formatFileSize(context, size);
    }

    public static String formatSizeInBytes(long size, Context context) {
        // HACK
        int quantity = (int) size;
        return context.getResources().getQuantityString(R.plurals.size_in_bytes_format, quantity,
                size);
    }

    public static String formatShortTime(Instant instant, Context context) {
        return formatShortTime(instant.toEpochMilli(), context);
    }

    /*
     * @see com.android.documentsui.base.Shared#formatTime(Context, long)
     */
    private static String formatShortTime(long time, Context context) {
        Time then = new Time();
        then.set(time);
        Time now = new Time();
        now.setToNow();
        int flags = DateUtils.FORMAT_NO_NOON | DateUtils.FORMAT_NO_MIDNIGHT
                | DateUtils.FORMAT_ABBREV_ALL;
        if (then.year != now.year) {
            flags |= DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE;
        } else if (then.yearDay != now.yearDay) {
            flags |= DateUtils.FORMAT_SHOW_DATE;
        } else {
            flags |= DateUtils.FORMAT_SHOW_TIME;
        }
        return DateUtils.formatDateTime(context, time, flags);
    }

    public static String formatLongTime(Instant instant) {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                .withZone(ZoneId.systemDefault())
                .format(instant);
    }
}