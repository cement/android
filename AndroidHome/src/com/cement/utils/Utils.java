package com.cement.utils;

import android.content.ClipData;
import android.content.Intent;
import android.content.IntentSender;

public class Utils {

	 public static Intent startupChooser(Intent target, CharSequence title, IntentSender sender) {
	        Intent intent = new Intent(Intent.ACTION_CHOOSER);
	        intent.putExtra(Intent.EXTRA_INTENT, target);
	        if (title != null) {
	            intent.putExtra(Intent.EXTRA_TITLE, title);
	        }

	        if (sender != null) {
	            intent.putExtra(Intent.EXTRA_CHOSEN_COMPONENT_INTENT_SENDER, sender);
	        }

	        // Migrate any clip data and flags from target.
	        int permFlags = target.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION
	                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
	                | Intent.FLAG_GRANT_PREFIX_URI_PERMISSION);
	        if (permFlags != 0) {
	            ClipData targetClipData = target.getClipData();
	            if (targetClipData == null && target.getData() != null) {
	                ClipData.Item item = new ClipData.Item(target.getData());
	                String[] mimeTypes;
	                if (target.getType() != null) {
	                    mimeTypes = new String[] { target.getType() };
	                } else {
	                    mimeTypes = new String[] { };
	                }
	                targetClipData = new ClipData(null, mimeTypes, item);
	            }
	            if (targetClipData != null) {
	                intent.setClipData(targetClipData);
	                intent.addFlags(permFlags);
	            }
	        }

	        return intent;
	    }

}
