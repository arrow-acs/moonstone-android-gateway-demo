/*
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors: Arrow Electronics, Inc.
 */

package com.arrow.jmyiotgateway.miramonti.common;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;

import com.arrow.acn.api.models.ApiError;
import com.arrow.jmyiotgateway.miramonti.acn.EventIotConnectApiService;

import retrofit2.Response;

@Keep
public interface EventRetrofitHolder {
    void setDefaultApiKey(String apiKey);

    void setDefaultApiSecret(String apiSecret);

    void setSecretKey(String secretKey);

    void setApiKey(String apiKey);

    EventIotConnectApiService getIotConnectAPIService(@NonNull String endpoint);

    ApiError convertToApiError(@NonNull Response<?> response);
}
