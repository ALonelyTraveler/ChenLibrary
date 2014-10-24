package com.android.chen.lib.utils;

import org.apache.http.HttpEntity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtils {

	private static AsyncHttpClient client = new AsyncHttpClient();
	private final static int HTTP_UTILS_DEFAULT_TIME_OUT = 20 * 1000;

	static {
		// 设置链接超时，如果不设置，默认为10s
		client.setTimeout(HTTP_UTILS_DEFAULT_TIME_OUT);
	}

	public static void addHeader(String key, String value) {

		client.addHeader(key, value);
	}

	// 用一个完整url获取一个string对象
	public static void get(Context context, String urlString,
			AsyncHttpResponseHandler res) {

		client.get(context, urlString, res);
	}

	// url里面带参数
	public static void get(Context context, String urlString,
			RequestParams params, AsyncHttpResponseHandler res) {

		client.get(context, urlString, params, res);
	}

	// 不带参数，获取json对象或者数组
	public static void get(Context context, String urlString,
			JsonHttpResponseHandler res) {

		client.get(context, urlString, res);
	}

	// 带参数，获取json对象或者数组
	public static void get(Context context, String urlString,
			RequestParams params, JsonHttpResponseHandler res) {

		client.get(context, urlString, params, res);
	}

	// 下载数据使用，会返回byte数据
	public static void get(Context context, String uString,
			BinaryHttpResponseHandler bHandler) {

		client.get(context, uString, bHandler);
	}

	public static void post(Context context, String url, RequestParams params,
			JsonHttpResponseHandler jsonHttpResponseHandler) {

		client.post(context, url, params, jsonHttpResponseHandler);
	}

	public static void post(Context context, String url, HttpEntity entity,
			String contentType, JsonHttpResponseHandler jsonHttpResponseHandler) {

		client.post(context, url, entity, contentType, jsonHttpResponseHandler);
	}

	public static AsyncHttpClient getClient() {

		return client;
	}

}
