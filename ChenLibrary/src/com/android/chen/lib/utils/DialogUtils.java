package com.android.chen.lib.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.widget.ListAdapter;

/*
 * Define kind of Dialog.
 */
/**
 * @author WinChen
 * @date 2014-4-1 下午9:04:24
 * @version 1.01
 */
public class DialogUtils {
	// =====================prompt dialog===========================
	/**
	 * prompt dialog
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param positiveName
	 */
	public static Dialog alertInfo(Context context, int title, int message,
			int positiveName) {

		return alertInfo(context, context.getString(title),
				context.getString(message), context.getString(positiveName),
				null);
	}

	public static Dialog alertInfo(Context context, int title, int message,
			int positiveName, DialogInterface.OnClickListener listener) {

		return alertInfo(context, context.getString(title),
				context.getString(message), context.getString(positiveName),
				listener);
	}

	/**
	 * prompt dialog
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param positiveName
	 */
	public static Dialog alertInfo(Context context, CharSequence title,
			CharSequence message, CharSequence positiveName) {

		return alertInfo(context, title, message, positiveName, null);
	}

	public static Dialog alertInfo(Context context, CharSequence title,
			CharSequence message, CharSequence positiveName,
			DialogInterface.OnClickListener listener) {

		return new AlertDialog.Builder(context).setTitle(title)
				.setMessage(message).setPositiveButton(positiveName, listener)
				.create();
	}

	/**
	 * custom prompt dialog
	 * 
	 * @param context
	 * @param layout
	 */
	public static Dialog alertInfo(Context context, View layout) {
		Dialog dialog = new AlertDialog.Builder(context).setView(layout)
				.create();
		return dialog;
	}

	// =====================confirm dialog===========================
	/**
	 * alert confirm dialog
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param btnPositiveName
	 * @param btnNegativeName
	 */
	public static Dialog alertConfirm(Context context, int title, int message,
			int btnPositiveName, int btnNegativeName) {
		return alertConfirm(context, title, message, btnPositiveName,
				btnNegativeName, null, null);
	}

	/**
	 * alert confirm dialog
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param btnPositiveName
	 * @param btnNegativeName
	 */
	public static Dialog alertConfirm(Context context, CharSequence title,
			CharSequence message, CharSequence btnPositiveName,
			CharSequence btnNegativeName) {
		return alertConfirm(context, title, message, btnPositiveName,
				btnNegativeName, null, null);
	}

	/**
	 * alert confirm dialog
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param btnPositiveName
	 * @param btnNegativeName
	 * @param positiveListener
	 * @param negativeListener
	 */
	public static Dialog alertConfirm(Context context, int title, int message,
			int btnPositiveName, int btnNegativeName,
			DialogInterface.OnClickListener positiveListener,
			DialogInterface.OnClickListener negativeListener) {
		return alertConfirm(context, context.getResources().getString(title),
				context.getResources().getString(message), context
						.getResources().getString(btnPositiveName), context
						.getResources().getString(btnNegativeName),
				positiveListener, negativeListener);
	}

	/**
	 * alert confirm dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param icon
	 * @param btnPositiveName
	 * @param btnNegativeName
	 * @param positiveListener
	 * @param negativeListener
	 */
	public static Dialog alertConfirm(Context context, int title, int message,
			int icon, int btnPositiveName, int btnNegativeName,
			DialogInterface.OnClickListener positiveListener,
			DialogInterface.OnClickListener negativeListener) {
		return alertConfirm(context, context.getResources().getString(title),
				icon, context.getResources().getString(message), context
						.getResources().getString(btnPositiveName), context
						.getResources().getString(btnNegativeName),
				positiveListener, negativeListener);
	}

	/**
	 * alert confirm dialog
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param btnPositiveName
	 * @param btnNegativeName
	 * @param positiveListener
	 * @param negativeListener
	 */
	public static Dialog alertConfirm(Context context, CharSequence title,
			CharSequence message, CharSequence btnPositiveName,
			CharSequence btnNegativeName,
			DialogInterface.OnClickListener positiveListener,
			DialogInterface.OnClickListener negativeListener) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setMessage(message)
				.setPositiveButton(btnPositiveName, positiveListener)
				.setNegativeButton(btnNegativeName, negativeListener).create();
	}

	/**
	 * alert confirm dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param message
	 * @param btnPositiveName
	 * @param btnNegativeName
	 * @param positiveListener
	 * @param negativeListener
	 */
	public static Dialog alertConfirm(Context context, CharSequence title,
			int icon, CharSequence message, CharSequence btnPositiveName,
			CharSequence btnNegativeName,
			DialogInterface.OnClickListener positiveListener,
			DialogInterface.OnClickListener negativeListener) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setMessage(message).setIcon(icon)
				.setPositiveButton(btnPositiveName, positiveListener)
				.setNegativeButton(btnNegativeName, negativeListener).create();
	}

	/**
	 * show custom confirm dialog
	 * 
	 * @param context
	 * @param layout
	 * @return
	 */
	public static Dialog alertConfirm(Context context, View layout) {
		return new AlertDialog.Builder(context).setView(layout).create();
	}

	// =====================single choice dialog===========================
	/**
	 * show single choice dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param items
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context, int title,
			int icon, CharSequence[] items, int checkItem,
			DialogInterface.OnClickListener listener, int negativeName) {
		return showSingleChoiceDialog(context, context.getResources()
				.getString(title), icon, items, checkItem, listener, context
				.getResources().getString(negativeName));
	}

	/**
	 * @param context
	 * @param title
	 * @param icon
	 * @param items
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context,
			CharSequence title, int icon, CharSequence[] items, int checkItem,
			DialogInterface.OnClickListener listener, CharSequence negativeName) {
		return new AlertDialog.Builder(context).setTitle(title).setIcon(icon)
				.setSingleChoiceItems(items, checkItem, listener)
				.setNegativeButton(negativeName, null).show();
	}

	/**
	 * show single choice dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param items
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context, int title,
			CharSequence[] items, int checkItem,
			DialogInterface.OnClickListener listener, int negativeName) {
		return showSingleChoiceDialog(context, context.getResources()
				.getString(title), items, checkItem, listener, context
				.getResources().getString(negativeName));
	}

	/**
	 * @param context
	 * @param title
	 * @param items
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context,
			CharSequence title, CharSequence[] items, int checkItem,
			DialogInterface.OnClickListener listener, CharSequence negativeName) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setSingleChoiceItems(items, checkItem, listener)
				.setNegativeButton(negativeName, null).show();
	}

	/**
	 * show single choice dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param itemsId
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context, int title,
			int icon, int itemsId, int checkItem,
			DialogInterface.OnClickListener listener, int negativeName) {
		return showSingleChoiceDialog(context, context.getResources()
				.getString(title), icon, itemsId, checkItem, listener, context
				.getResources().getString(negativeName));
	}

	/**
	 * @param context
	 * @param title
	 * @param icon
	 * @param itemsId
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context,
			CharSequence title, int icon, int itemsId, int checkItem,
			DialogInterface.OnClickListener listener, CharSequence negativeName) {
		return new AlertDialog.Builder(context).setTitle(title).setIcon(icon)
				.setSingleChoiceItems(itemsId, checkItem, listener)
				.setNegativeButton(negativeName, null).show();
	}

	/**
	 * show single choice dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param itemsId
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context, int title,
			int itemsId, int checkItem,
			DialogInterface.OnClickListener listener, int negativeName) {
		return showSingleChoiceDialog(context, context.getResources()
				.getString(title), itemsId, checkItem, listener, context
				.getResources().getString(negativeName));
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param itemsId
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context,
			CharSequence title, int itemsId, int checkItem,
			DialogInterface.OnClickListener listener, CharSequence negativeName) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setSingleChoiceItems(itemsId, checkItem, listener)
				.setNegativeButton(negativeName, null).show();
	}

	/**
	 * show single choice dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param adapter
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context, int title,
			int icon, ListAdapter adapter, int checkItem,
			DialogInterface.OnClickListener listener, int negativeName) {
		return showSingleChoiceDialog(context, context.getResources()
				.getString(title), icon, adapter, checkItem, listener, context
				.getResources().getString(negativeName));
	}

	/**
	 * @param context
	 * @param title
	 * @param icon
	 * @param adapter
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context,
			CharSequence title, int icon, ListAdapter adapter, int checkItem,
			DialogInterface.OnClickListener listener, CharSequence negativeName) {
		return new AlertDialog.Builder(context).setTitle(title).setIcon(icon)
				.setSingleChoiceItems(adapter, checkItem, listener)
				.setNegativeButton(negativeName, null).show();
	}

	/**
	 * show single choice dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param adapter
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context, int title,
			ListAdapter adapter, int checkItem,
			DialogInterface.OnClickListener listener, int negativeName) {
		return showSingleChoiceDialog(context, context.getResources()
				.getString(title), adapter, checkItem, listener, context
				.getResources().getString(negativeName));
	}

	/**
	 * show single choice dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param adapter
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showSingleChoiceDialog(Context context,
			CharSequence title, ListAdapter adapter, int checkItem,
			DialogInterface.OnClickListener listener, CharSequence negativeName) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setSingleChoiceItems(adapter, checkItem, listener)
				.setNegativeButton(negativeName, null).show();
	}

	// =====================list dialog===========================
	/**
	 * show list dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param items
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, int title, int icon,
			CharSequence[] items, DialogInterface.OnClickListener listener) {
		return showListDialog(context, context.getResources().getString(title),
				icon, items, listener);
	}

	/**
	 * @param context
	 * @param title
	 * @param icon
	 * @param items
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, CharSequence title,
			int icon, CharSequence[] items,
			DialogInterface.OnClickListener listener) {
		return new AlertDialog.Builder(context).setTitle(title).setIcon(icon)
				.setItems(items, listener)
				.show();
	}

	/**
	 * show list dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param items
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, int title,
			CharSequence[] items, DialogInterface.OnClickListener listener) {
		return showListDialog(context, context.getResources().getString(title),
				items, listener);
	}

	/**
	 * @param context
	 * @param title
	 * @param items
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, CharSequence title,
			CharSequence[] items, DialogInterface.OnClickListener listener
			) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setItems(items, listener)
				.show();
	}

	/**
	 * show list dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param itemsId
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, int title, int icon,
			int itemsId, DialogInterface.OnClickListener listener) {
		return showListDialog(context, context.getResources().getString(title),
				icon, itemsId, listener);
	}

	/**
	 * @param context
	 * @param title
	 * @param icon
	 * @param itemsId
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, CharSequence title,
			int icon, int itemsId, DialogInterface.OnClickListener listener) {
		return new AlertDialog.Builder(context).setTitle(title).setIcon(icon)
				.setItems(itemsId, listener)
				.show();
	}

	/**
	 * show list dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param itemsId
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, int title,
			int itemsId, DialogInterface.OnClickListener listener) {
		return showListDialog(context, context.getResources().getString(title),
				itemsId, listener);
	}

	/**
	 * 
	 * @param context
	 * @param title
	 * @param itemsId
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, CharSequence title,
			int itemsId, DialogInterface.OnClickListener listener) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setItems(itemsId, listener)
				.show();
	}

	/**
	 * show list dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param adapter
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, int title, int icon,
			ListAdapter adapter, DialogInterface.OnClickListener listener) {
		return showListDialog(context, context.getResources().getString(title),
				icon, adapter, listener);
	}

	/**
	 * @param context
	 * @param title
	 * @param icon
	 * @param adapter
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, CharSequence title,
			int icon, ListAdapter adapter,
			DialogInterface.OnClickListener listener) {
		return new AlertDialog.Builder(context).setTitle(title).setIcon(icon)
				.setAdapter(adapter, listener)
				.show();
	}

	/**
	 * show list dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param adapter
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, int title,
			ListAdapter adapter, DialogInterface.OnClickListener listener,
			int negativeName) {
		return showListDialog(context, context.getResources().getString(title),
				adapter, listener,
				context.getResources().getString(negativeName));
	}

	/**
	 * show list dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param adapter
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showListDialog(Context context, CharSequence title,
			ListAdapter adapter, DialogInterface.OnClickListener listener,
			CharSequence negativeName) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setAdapter(adapter, listener)
				.setNegativeButton(negativeName, null).show();
	}

	// =====================multi choice dialog===========================
	/**
	 * show multi choice dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param items
	 * @param listener
	 * @param negativeName
	 * @return
	 */
	public static Dialog showMultiChoiceDialog(Context context, int title,
			int icon, CharSequence[] items, boolean[] checkItem,
			OnMultiChoiceClickListener listener, int negativeName,
			DialogInterface.OnClickListener negativeListener, int positiveName,
			DialogInterface.OnClickListener positveListener) {
		return showMultiChoiceDialog(context,
				context.getResources().getString(title), icon, items,
				checkItem, listener,
				context.getResources().getString(negativeName),
				negativeListener, context.getResources()
						.getString(positiveName), positveListener);
	}

	/**
	 * @param context
	 * @param title
	 * @param icon
	 * @param items
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @param negativeListener
	 * @param positiveName
	 * @param positveListener
	 * @return
	 */
	public static Dialog showMultiChoiceDialog(Context context,
			CharSequence title, int icon, CharSequence[] items,
			boolean[] checkItem, OnMultiChoiceClickListener listener,
			CharSequence negativeName,
			DialogInterface.OnClickListener negativeListener,
			CharSequence positiveName,
			DialogInterface.OnClickListener positveListener) {
		return new AlertDialog.Builder(context).setTitle(title).setIcon(icon)
				.setMultiChoiceItems(items, checkItem, listener)
				.setNegativeButton(negativeName, negativeListener)
				.setPositiveButton(positiveName, positveListener).show();
	}

	/**
	 * show multi choice dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param items
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @param negativeListener
	 * @param positiveName
	 * @param positveListener
	 * @return
	 */
	public static Dialog showMultiChoiceDialog(Context context, int title,
			CharSequence[] items, boolean[] checkItem,
			OnMultiChoiceClickListener listener, int negativeName,
			DialogInterface.OnClickListener negativeListener, int positiveName,
			DialogInterface.OnClickListener positveListener) {
		return showMultiChoiceDialog(context,
				context.getResources().getString(title), items, checkItem,
				listener, context.getResources().getString(negativeName),
				negativeListener, context.getResources()
						.getString(positiveName), positveListener);
	}

	/**
	 * @param context
	 * @param title
	 * @param items
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @param negativeListener
	 * @param positiveName
	 * @param positveListener
	 * @return
	 */
	public static Dialog showMultiChoiceDialog(Context context,
			CharSequence title, CharSequence[] items, boolean[] checkItem,
			OnMultiChoiceClickListener listener, CharSequence negativeName,
			DialogInterface.OnClickListener negativeListener,
			CharSequence positiveName,
			DialogInterface.OnClickListener positveListener) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setMultiChoiceItems(items, checkItem, listener)
				.setNegativeButton(negativeName, negativeListener)
				.setPositiveButton(positiveName, positveListener).show();
	}

	/**
	 * show mutli choice dialog that have icon.
	 * 
	 * @param context
	 * @param title
	 * @param icon
	 * @param itemsId
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @param negativeListener
	 * @param positiveName
	 * @param positveListener
	 * @return
	 */
	public static Dialog showMultiChoiceDialog(Context context, int title,
			int icon, int itemsId, boolean[] checkItem,
			OnMultiChoiceClickListener listener, int negativeName,
			DialogInterface.OnClickListener negativeListener, int positiveName,
			DialogInterface.OnClickListener positveListener) {
		return showMultiChoiceDialog(context,
				context.getResources().getString(title), icon, itemsId,
				checkItem, listener,
				context.getResources().getString(negativeName),
				negativeListener, context.getResources()
						.getString(positiveName), positveListener);
	}

	/**
	 * @param context
	 * @param title
	 * @param icon
	 * @param itemsId
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @param negativeListener
	 * @param positiveName
	 * @param positveListener
	 * @return
	 */
	public static Dialog showMultiChoiceDialog(Context context,
			CharSequence title, int icon, int itemsId, boolean[] checkItem,
			OnMultiChoiceClickListener listener, CharSequence negativeName,
			DialogInterface.OnClickListener negativeListener,
			CharSequence positiveName,
			DialogInterface.OnClickListener positveListener) {
		return new AlertDialog.Builder(context).setTitle(title).setIcon(icon)
				.setMultiChoiceItems(itemsId, checkItem, listener)
				.setNegativeButton(negativeName, negativeListener)
				.setPositiveButton(positiveName, positveListener).show();
	}

	/**
	 * show multi choice dialog that haven't icon.
	 * 
	 * @param context
	 * @param title
	 * @param itemsId
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @param negativeListener
	 * @param positiveName
	 * @param positveListener
	 * @return
	 */
	public static Dialog showMultiChoiceDialog(Context context, int title,
			int itemsId, boolean[] checkItem,
			OnMultiChoiceClickListener listener, int negativeName,
			DialogInterface.OnClickListener negativeListener, int positiveName,
			DialogInterface.OnClickListener positveListener) {
		return showMultiChoiceDialog(context,
				context.getResources().getString(title), itemsId, checkItem,
				listener, context.getResources().getString(negativeName),
				negativeListener, context.getResources()
						.getString(positiveName), positveListener);
	}

	/**
	 * @param context
	 * @param title
	 * @param itemsId
	 * @param checkItem
	 * @param listener
	 * @param negativeName
	 * @param negativeListener
	 * @param positiveName
	 * @param positveListener
	 * @return
	 */
	public static Dialog showMultiChoiceDialog(Context context,
			CharSequence title, int itemsId, boolean[] checkItem,
			OnMultiChoiceClickListener listener, CharSequence negativeName,
			DialogInterface.OnClickListener negativeListener,
			CharSequence positiveName,
			DialogInterface.OnClickListener positveListener) {
		return new AlertDialog.Builder(context).setTitle(title)
				.setMultiChoiceItems(itemsId, checkItem, listener)
				.setNegativeButton(negativeName, negativeListener)
				.setPositiveButton(positiveName, positveListener).show();
	}

	// =====================progress dialog===========================
	/**
	 * show loading progress dialog.
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param theme
	 * @param defStyle
	 * @param isCancel
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			CharSequence title, CharSequence message, int theme, int defStyle,
			boolean isCancel) {
		ProgressDialog progressDialog = new ProgressDialog(context, theme);
		progressDialog.setProgressStyle(defStyle);
		progressDialog.setMessage(message);
		progressDialog.setCancelable(isCancel);
		progressDialog.setTitle(title);
		progressDialog.show();
		return progressDialog;
	}

	/**
	 * @param context
	 * @param titleId
	 * @param messageId
	 * @param theme
	 * @param defStyle
	 * @param isCancel
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			int titleId, int messageId, int theme, int defStyle,
			boolean isCancel) {
		return showLoadingDialog(context,
				context.getResources().getString(titleId), context
						.getResources().getString(messageId), theme, defStyle,
				isCancel);
	}

	/**
	 * @param context
	 * @param titleId
	 * @param messageId
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			int titleId, int messageId) {
		return showLoadingDialog(context,
				context.getResources().getString(titleId), context
						.getResources().getString(messageId));
	}

	/**
	 * @param context
	 * @param title
	 * @param message
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			CharSequence title, CharSequence message) {
		return showLoadingDialog(context, title, message, 0,
				ProgressDialog.STYLE_SPINNER, false);
	}

	/**
	 * show loading progress dialog of not title.
	 * 
	 * @param context
	 * @param message
	 * @param theme
	 * @param defStyle
	 * @param isCancel
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			CharSequence message, int theme, int defStyle, boolean isCancel) {
		ProgressDialog progressDialog = new ProgressDialog(context, theme);
		progressDialog.setProgressStyle(defStyle);
		progressDialog.setMessage(message);
		progressDialog.setCancelable(isCancel);
		progressDialog.show();
		return progressDialog;
	}

	/**
	 * @param context
	 * @param messageId
	 * @param theme
	 * @param defStyle
	 * @param isCancel
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			int messageId, int theme, int defStyle, boolean isCancel) {
		return showLoadingDialog(context,
				context.getResources().getString(messageId), theme, defStyle,
				isCancel);
	}

	/**
	 * @param context
	 * @param messageId
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			int messageId) {
		return showLoadingDialog(context,
				context.getResources().getString(messageId));
	}

	/**
	 * @param context
	 * @param message
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			CharSequence message) {
		return showLoadingDialog(context, message, 0,
				ProgressDialog.STYLE_SPINNER, false);
	}

	/**
	 * show a custom progress dialog
	 * 
	 * @param context
	 * @param layout
	 */
	public static ProgressDialog showLoadingDialog(Context context, View layout) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setView(layout);
		progressDialog.show();
		return progressDialog;
	}

	/**
	 * @param context
	 * @param layout
	 * @param viewSpacingLeft
	 * @param viewSpacingTop
	 * @param viewSpacingRight
	 * @param viewSpacingBottom
	 */
	public static ProgressDialog showLoadingDialog(Context context,
			View layout, int viewSpacingLeft, int viewSpacingTop,
			int viewSpacingRight, int viewSpacingBottom) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setView(layout, viewSpacingLeft, viewSpacingTop,
				viewSpacingRight, viewSpacingBottom);
		progressDialog.show();
		return progressDialog;
	}

}
