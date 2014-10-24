
package com.android.chen.lib.utils;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.android.chen.lib.R;
import com.android.chen.lib.entity.VersionInfo;
import com.android.chen.lib.listener.IVersionListener;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

public class VersionManager
{

	private VersionInfo version;
	private final Context context;
	private Dialog noticeDialog = null;
	private Dialog downloadDialog = null;
	private ProgressBar progressBar = null;
	private int progress = 0;
	private final String networkUrl;
	private IVersionListener listener = null;
	private boolean isStart = false;
	// 软件版本更新
	public static final int CHECK_NETWORK = 0;

	public static final int CHECK_VERSION = 1;

	public static final int START_APP = 2;

	public static final int DOWNLOAD_ERROR = 3;

	/* 下载中 */
	public static final int DOWNLOAD = 4;

	/* 下载结束 */
	public static final int DOWNLOAD_FINISH = 5;

	public static final int SHOW_DOWNLOAD = 6;

	public static final int UPDATE_TIP = 7;

	private Handler handler = new Handler()
	{

		public void handleMessage(android.os.Message msg)
		{

			switch (msg.what)
			{
			case CHECK_NETWORK:
				boolean connect = NetworkMonitor.wifiConnect(context);
				if (connect)
				{
					handler.sendEmptyMessage(CHECK_VERSION);
				} else
				{
					if (listener != null && (!listener.isBackground()))
					{
						ToastUtils
								.show(context, R.string.check_version_network_loading_fail);
					} else
					{
						//handler.sendEmptyMessage(START_APP);
					}
				}
				break;
			case CHECK_VERSION:
				updateNetwork(PackageUtils.getVersionName(context));
				break;
			case START_APP:
				isStart = true;
				if (listener != null && listener.isBackground())
				{
					listener.startApp();
				}
				break;
			case DOWNLOAD_ERROR:
				showErrorDialog();
				break;
			case DOWNLOAD:
				progressBar.setProgress(progress);
				break;
			case DOWNLOAD_FINISH:
				File installFile = new File(StorageUtils
						.getStorageFile(), version.savePath);
				if(installFile.exists())
				{
					installApk(context, installFile);
					if(listener!=null)
					{
						listener.backOfFinish(version.forceup);
					}
					return;
				}
				break;
			case SHOW_DOWNLOAD:
				File apkFile = new File(StorageUtils
						.getStorageFile(), version.savePath);
				if(apkFile.exists())
				{
					installApk(context, apkFile);
					if(listener!=null)
					{
						listener.backOfFinish(version.forceup);
					}
					return;
				}
				showDownload();
				break;
			case UPDATE_TIP:
				showNotice();
				break;
			}
		};
	};

	public VersionManager(Context context, String networkUrl, IVersionListener listener)
	{

		this.context = context;
		this.networkUrl = networkUrl;
		this.listener = listener;
	}

	public VersionInfo getVersion()
	{

		return version;
	}

	public void setVersion(VersionInfo version)
	{

		this.version = version;
	}

	public void checkUpdate()
	{

		if (listener != null && listener.isBackground())
		{
			handler.sendEmptyMessageDelayed(CHECK_NETWORK, 500);
			handler.sendEmptyMessageDelayed(START_APP, 2000);
		} else
		{
			handler.sendEmptyMessage(CHECK_NETWORK);
		}
	}

	private void showNotice()
	{

		Resources resources = context.getResources();
		String message = resources
				.getString(R.string.check_version_dialog_update_message, version.oldVer, version.newVer) + (version.updateMsg
				.equals("") ? "" : resources
				.getString(R.string.check_version_dialog_update_message_add, version.updateMsg));
		if (!version.forceup)
		{
			noticeDialog = DialogUtils
					.alertConfirm(context, resources
							.getString(R.string.check_version_dialog_title), message, resources
							.getString(R.string.check_version_dialog_update_goto), resources
							.getString(R.string.check_version_dialog_update_negative), new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{

							// TODO Auto-generated method stub
							handler.sendEmptyMessage(SHOW_DOWNLOAD);

						}
					}, new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{

							// TODO Auto-generated method stub
							if (listener != null)
							{
								listener.failCancel(version.forceup);
							}
						}
					});
		} else
		{
			noticeDialog = DialogUtils
					.alertInfo(context, resources
							.getString(R.string.check_version_dialog_title), message, resources
							.getString(R.string.check_version_dialog_update_goto), new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{

							// TODO Auto-generated method stub
							handler.sendEmptyMessage(SHOW_DOWNLOAD);
						}
					});
		}
		noticeDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
		{
			
			@Override
			public void onCancel(DialogInterface dialog)
			{
			
				// TODO Auto-generated method stub
				if(listener!=null)
				{
					listener.failCancel(version.forceup);
				}
				
			}
		});
		noticeDialog.getWindow().setGravity(Gravity.LEFT);
		noticeDialog.show();

	}

	private void showDownload()
	{
		View view = LayoutInflater.from(context)
				.inflate(R.layout.softupdate_progress, null);
		progressBar = (ProgressBar) view
				.findViewById(R.id.update_progress);
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle(R.string.check_version_dialog_title);
		// 给下载对话框增加进度条
		builder.setView(view);
		downloadDialog = builder.create();
		downloadDialog.setCancelable(false);
		downloadDialog.show();
		downloadFile();
	}
	
	private void showErrorDialog(){
		noticeDialog = DialogUtils
				.alertConfirm(context, R.string.check_version_dialog_title, R.string.soft_version_update_fail, R.string.soft_version_update_fail_positive, version.forceup?R.string.soft_version_update_fail_exit:R.string.soft_version_update_fail_cancel, new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
					
						// TODO Auto-generated method stub
						handler.sendEmptyMessage(SHOW_DOWNLOAD);
					}
				}, new DialogInterface.OnClickListener()
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
					
						// TODO Auto-generated method stub
						if(listener!=null)
						{
							listener.failCancel(version.forceup);
						}
					}
				});
		noticeDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
		{
			
			@Override
			public void onCancel(DialogInterface dialog)
			{
			
				// TODO Auto-generated method stub
				if(listener!=null)
				{
					listener.failCancel(version.forceup);
				}
			}
		});
		noticeDialog.show();
	}

	// 与后台通信，查看是否需要更新版本
	private void updateNetwork(final String oldVer)
	{

		if (listener != null && (!listener.isBackground()))
		{
			noticeDialog = DialogUtils
					.showLoadingDialog(context, R.string.check_version_dialog_message);
			noticeDialog.show();
		}
		HttpUtils.get(context, networkUrl, new JsonHttpResponseHandler()
		{

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response)
			{

				// TODO Auto-generated method stub
				super.onSuccess(statusCode, headers, response);
				if(noticeDialog!=null)
				{
					noticeDialog.dismiss();
				}
				JsonUtils jsonUtils = new JsonUtils();
				IMap map = jsonUtils.fromJson(response.toString());
				if (map.getBoolean("success"))
				{
					ArrayList<IMap> dataArrayList = (ArrayList<IMap>) map
							.get("data");
					if (dataArrayList.size() > 0)
					{
						IMap data = dataArrayList.get(0);
						VersionInfo versionInfo = new VersionInfo();
						versionInfo.forceup = data.getBoolean("forceup");
						versionInfo.oldVer = oldVer;
						versionInfo.apkUrl = data.getString("path");
						versionInfo.newVer = data.getString("ver");
						versionInfo.updateMsg = data.getString("description");
						version = versionInfo;
						version.savePath = listener.downloadPath(version);
						version.downloadPath = listener.downloadPath(version) + "_cache";
						if (isStart)
						{
							return;
						}
						boolean isUpdate = false;
						try{
							isUpdate = Integer.parseInt(version.oldVer.replaceAll("\\.", "").trim()) < Integer.parseInt(version.newVer.replaceAll("\\.", "").trim());
						}
						catch(Exception e)
						{
							isUpdate = false;
						}
						if (isUpdate)
						{
							handler.removeMessages(START_APP);
							handler.sendEmptyMessage(UPDATE_TIP);
						} else
						{
							if (listener != null && (!listener.isBackground()))
							{
								noticeDialog = DialogUtils
										.alertInfo(context, R.string.check_version_dialog_title, R.string.check_version_dialog_message_new_ver, R.string.check_version_dialog_position_name, null);
								noticeDialog.show();
							}
						}
					} else
					{
						if (listener != null && (!listener.isBackground()))
						{
							noticeDialog = DialogUtils
									.alertInfo(context, R.string.check_version_dialog_title, R.string.check_version_dialog_message_new_ver, R.string.check_version_dialog_position_name, null);
							noticeDialog.show();
						}
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
			{

				// TODO Auto-generated method stub
				super.onFailure(statusCode, headers, responseString, throwable);
				if(noticeDialog!=null)
				{
					noticeDialog.dismiss();
				}
				if (isStart)
				{
					return;
				}
				if (listener != null && (!listener.isBackground()))
				{
					noticeDialog = DialogUtils
							.alertInfo(context, R.string.check_version_dialog_title, R.string.check_version_network_loading_fail, R.string.check_version_dialog_position_name, null);
					noticeDialog.show();
				}
			}
		});
	}

	private void downloadFile()
	{

		HttpUtils
				.get(context, version.apkUrl, new FileAsyncHttpResponseHandler(new File(StorageUtils
						.getStorageFile(), version.downloadPath))
				{

					@Override
					public void onSuccess(int statusCode, Header[] headers, File file)
					{

						// TODO Auto-generated method stub
						file.renameTo(new File(StorageUtils.getStorageFile(), version.savePath));
						handler.sendEmptyMessage(DOWNLOAD_FINISH);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file)
					{

						// TODO Auto-generated method stub
						handler.sendEmptyMessage(DOWNLOAD_ERROR);
					}
					@Override
					public void onProgress(int bytesWritten, int totalSize)
					{
					
						// TODO Auto-generated method stub
						super.onProgress(bytesWritten, totalSize);
						progress = bytesWritten*100/totalSize;
						handler.sendEmptyMessage(DOWNLOAD);
					}

					@Override
					public void onFinish()
					{

						// TODO Auto-generated method stub
						super.onFinish();
						if (downloadDialog != null)
						{
							downloadDialog.dismiss();
							downloadDialog = null;
						}
					}
				});
	}
	
	/**
	 * 安装apk
	 * 
	 * @param context
	 * @param apkfile
	 */
	public static void installApk(Context context, File apkfile)
	{

		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
		context.startActivity(i);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
