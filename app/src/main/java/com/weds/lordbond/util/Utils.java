package com.weds.lordbond.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;

import androidx.fragment.app.Fragment;

import com.weds.lordbond.R;

import java.io.ByteArrayOutputStream;

public class Utils {
	
	public static String encodeTobase64(Bitmap image) {
		Bitmap bitmap = image;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
		
		return imageEncoded;
	}
}
