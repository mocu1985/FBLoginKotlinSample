package epost.android.mitake.com.fbloginkotlinsample.util;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.text.Spanned;
import android.view.KeyEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import epost.android.mitake.com.fbloginkotlinsample.R;


/**
 * Central dialog-create factory
 * 
 * @author Jintin
 * 
 */
public class JDialog {

	/**
	 * show message with one button
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @return {@link android.app#Dialog}
	 */
	public static Dialog showMessage(Context context, String title, String msg) {
		return showMessage(context, title, msg, null);
	}

	/**
	 * show message with one button
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param listener
	 * @return {@link android.app#Dialog}
	 */
	public static Dialog showMessage(Context context, String title, String msg, OnClickListener listener) {

		return createDialog(context, title, msg, listener, false, null);
	}

	/**
	 * show message with two button
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param callback
	 * @return {@link android.app#Dialog}
	 */
	public static Dialog showDialog(Context context, String title, String msg, OnClickListener callback) {

		return showDialog(context, title, msg, callback, null);
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param confirmAction
	 * @param cancelAction
	 * @return
	 */
	public static Dialog showDialog(Context context, String title, String msg, OnClickListener confirmAction, OnClickListener cancelAction) {

		return createDialog(context, title, msg, confirmAction, true, cancelAction);
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param confirmAction
	 * @param cancelAction
	 * @return
	 */
	public static Dialog showDialog(Context context, String btnTitle, String title, String msg, OnClickListener confirmAction) {

		return showDialog(context, btnTitle, "", title, msg, confirmAction, null);
		// return createDialog(context, right, left, title, msg, confirmAction,
		// true, cancelAction);
	}

	/**
	 * show message with two button
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param callback
	 * @return {@link android.app#Dialog}
	 */
	public static Dialog showDialog(Context context, String rightTxt, String leftTxt, String title, String msg, OnClickListener oclLeft, OnClickListener oclRight) {

		return createDialog(context, title, msg, rightTxt, leftTxt, oclLeft,true, oclRight);
	}
	
	/**
	 * show message with one button
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param listener
	 * @return {@link android.app#Dialog}
	 */
	public static Dialog showDialog(Context context, String rightTxt, String leftTxt, String title, String msg, OnClickListener callback) {

		return createDialog(context, title, msg, rightTxt, leftTxt, callback,true, null);
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 * @param confirmAction
	 * @param showCancel
	 * @param cancelAction
	 * @return
	 */
	public static Dialog createDialog(Context context, String title, String msg, OnClickListener cancelAction) {
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton("確定", cancelAction);
		Dialog dialog = avoidDismiss(builder.create());
		return dialog;
	}

	/**
	 * 建立 AlertDialog兩個按鈕 
	 * @param context
	 * @param title
	 * @param msg
	 * @param confirmAction
	 * @param showCancel
	 * @param cancelAction
	 * @return
	 */
	private static Dialog createDialog(Context context, String title, String msg, OnClickListener confirmAction, boolean showCancel, OnClickListener cancelAction) {
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton("確定", confirmAction);
		if (showCancel)
			builder.setNegativeButton("取消", cancelAction);

		Dialog dialog = avoidDismiss(builder.create());
		if(!((Activity)context).isFinishing())
			dialog.show();

		return dialog;
	}

	/**
	 * 建立 AlertDialog兩個按鈕 按鈕名稱自訂
	 * @param context
	 * @param title
	 * @param msg
	 * @param right
	 * @param left
	 * @param confirmAction
	 * @param showCancel
	 * @param cancelAction
	 * @return
	 */
	public static Dialog createDialog(Context context, String title, String msg, String right, String left, OnClickListener confirmAction, boolean showCancel, OnClickListener cancelAction) {
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(right, confirmAction);
		if (showCancel)
			builder.setNegativeButton(left, cancelAction);

		Dialog dialog = avoidDismiss(builder.create());
		if(!((Activity)context).isFinishing())
			dialog.show();

		return dialog;
	}
	
	
	/**
	 * 建立 AlertDialog兩個按鈕 按鈕名稱自訂
	 * @param context
	 * @param title
	 * @param msg
	 * @param right
	 * @param left
	 * @param confirmAction
	 * @param showCancel
	 * @param cancelAction
	 * @return
	 */
	public static Dialog createDialog(Context context, String title, Spanned msg, String right, String left, OnClickListener confirmAction, boolean showCancel, OnClickListener cancelAction) {
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(right, confirmAction);
		if (showCancel)
			builder.setNegativeButton(left, cancelAction);

		Dialog dialog = avoidDismiss(builder.create());
		if(!((Activity)context).isFinishing())
			dialog.show();

		return dialog;
	}
	
	/**
	 * 建立 AlertDialog （三個按鈕）
	 * */
	public static Builder createAlertDialogBuilder(Context cxt,
			String strTitle, 
			String strMsg, 
			String strPositiveButton,
			OnClickListener PositiveButtonClick,
			String strNegativeButton,
			OnClickListener NegativeButtonClick,
			String strNeutralButton,
			OnClickListener NeutralButtonClick) {
		
		Builder builder = new Builder(cxt);
		builder.setTitle(strTitle);
		builder.setMessage(strMsg);

		if (null != strPositiveButton && strPositiveButton.length() > 0) {
			builder.setPositiveButton(strPositiveButton, PositiveButtonClick);
		}

		if (null != strNegativeButton && strNegativeButton.length() > 0) {
			builder.setNegativeButton(strNegativeButton, NegativeButtonClick);
		}

		if (null != strNeutralButton && strNeutralButton.length() > 0) {
			builder.setNeutralButton(strNeutralButton, NeutralButtonClick);
		}

		builder.setCancelable(false);
		builder.create();
		return builder;
	}
	
	
	/**
	 * 建立 AlertDialog （三個按鈕）
	 * */
	public static Builder createAlertDialogBuilder(Context cxt,
			String strTitle, 
			Spanned strMsg, 
			String strPositiveButton,
			OnClickListener PositiveButtonClick,
			String strNegativeButton,
			OnClickListener NegativeButtonClick,
			String strNeutralButton,
			OnClickListener NeutralButtonClick) {
		
		Builder builder = new Builder(cxt);
		builder.setTitle(strTitle);
		builder.setMessage(strMsg);

		if (null != strPositiveButton && strPositiveButton.length() > 0) {
			builder.setPositiveButton(strPositiveButton, PositiveButtonClick);
		}

		if (null != strNegativeButton && strNegativeButton.length() > 0) {
			builder.setNegativeButton(strNegativeButton, NegativeButtonClick);
		}

		if (null != strNeutralButton && strNeutralButton.length() > 0) {
			builder.setNeutralButton(strNeutralButton, NeutralButtonClick);
		}

		builder.setCancelable(false);
		builder.create();
		return builder;
	}


	/**
	 * 
	 * @param <T>
	 * @param t
	 * @return Dialog
	 */
	public static <T extends Dialog> T avoidDismiss(final T t) {
		t.setCancelable(false);
		t.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_SEARCH && event.getRepeatCount() == 0) {
					return true;
				}
				return false;
			}
		});

		return t;
	}

	public static void showDialog(@NotNull Context cxt, @Nullable String string, @Nullable String string1) {

	}
}
