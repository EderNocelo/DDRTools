/**
 Copyright 2021 DDRTools

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 * */
package com.mx.ddrtools.components.datepicker;

import android.widget.NumberPicker;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TwoDigitFormatter implements NumberPicker.Formatter {
    final StringBuilder mBuilder = new StringBuilder();

    char mZeroDigit;
    java.util.Formatter mFmt;

    final Object[] mArgs = new Object[1];

    public TwoDigitFormatter() {
        final Locale locale = Locale.getDefault();
        init(locale);
    }

    private void init(Locale locale) {
        mFmt = createFormatter(locale);
        mZeroDigit = getZeroDigit(locale);
    }

    public String format(int value) {
        final Locale currentLocale = Locale.getDefault();
        if (mZeroDigit != getZeroDigit(currentLocale)) {
            init(currentLocale);
        }
        mArgs[0] = value;
        mBuilder.delete(0, mBuilder.length());
        mFmt.format("%02d", mArgs);
        return mFmt.toString();
    }

    private static char getZeroDigit(Locale locale) {
        // The original TwoDigitFormatter directly referenced LocaleData's value. Instead,
        // we need to use the public DecimalFormatSymbols API.
        return DecimalFormatSymbols.getInstance(locale).getZeroDigit();
    }

    private java.util.Formatter createFormatter(Locale locale) {
        return new java.util.Formatter(mBuilder, locale);
    }
}
