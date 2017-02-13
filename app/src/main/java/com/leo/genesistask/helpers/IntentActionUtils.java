package com.leo.genesistask.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by leonid on 2/12/17.
 */
public class IntentActionUtils {

    /**
     * Shows the web page in browser with the implicit intent
     *
     * @param context      - Context needed to start the Activity
     * @param url          - url
     * @param chooserTitle - chooser title
     */
    public static void openUrl(Context context, String url, String chooserTitle) {
        if (null == url) {
            return;
        }
        Intent urlIntent = new Intent(Intent.ACTION_VIEW);
        urlIntent.setData(Uri.parse(url));
        urlIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(Intent.createChooser(urlIntent, chooserTitle));
    }
}