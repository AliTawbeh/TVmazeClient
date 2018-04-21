package com.brightcove.tvmazeclient.utils;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by Ali on 16-Apr-18.
 */

public final class StringUtil {
    public static Spanned toHTMLFormat(String htmlString){
        Spanned spanned=null;
        if(htmlString!=null)
            spanned = Html.fromHtml(htmlString);
        return spanned;
    }

    public static boolean isNullOrEmpty(String date) {
        return date.isEmpty() || date.length()==0;
    }
}
