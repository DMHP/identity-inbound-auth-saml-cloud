/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.identity.sso.saml.cloud.processor;

import org.wso2.carbon.identity.application.authentication.framework.exception.FrameworkException;
import org.wso2.carbon.identity.application.authentication.framework.inbound.IdentityRequest;
import org.wso2.carbon.identity.application.authentication.framework.inbound.IdentityResponse;
import org.wso2.carbon.identity.sso.saml.cloud.context.SAMLMessageContext;
import org.wso2.carbon.identity.sso.saml.cloud.handler.HandlerManager;
import org.wso2.carbon.identity.sso.saml.cloud.request.SAMLSpInitRequest;
import org.wso2.carbon.identity.sso.saml.cloud.util.SAMLSSOUtil;

import java.util.HashMap;

public class SPInitSSOAuthnRequestProcessor extends AuthnRequestProcessor {

    private String relyingParty;

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean canHandle(IdentityRequest identityRequest) {
        if (identityRequest instanceof SAMLSpInitRequest && ((SAMLSpInitRequest) identityRequest).getSamlRequest
                () != null) {
            return true;
        }
        return false;
    }

    @Override
    public IdentityResponse.IdentityResponseBuilder process(IdentityRequest identityRequest) throws
            FrameworkException {
        SAMLMessageContext messageContext = new SAMLMessageContext((SAMLSpInitRequest) identityRequest, new
                HashMap<String, String>());
        HandlerManager.getInstance().validateRequest(messageContext);
        boolean isLogoutRequest = SAMLSSOUtil.isLogoutRequest();
        if (!isLogoutRequest) {
            return buildResponseForFrameworkLogin(messageContext);
        } else {
            return buildResponseForCloudLogout(messageContext);
        }
    }

}
