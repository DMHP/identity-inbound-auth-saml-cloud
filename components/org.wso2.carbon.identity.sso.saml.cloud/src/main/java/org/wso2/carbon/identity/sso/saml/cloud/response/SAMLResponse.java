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

package org.wso2.carbon.identity.sso.saml.cloud.response;

import org.opensaml.saml2.core.StatusResponseType;
import org.opensaml.saml2.core.impl.ResponseBuilder;
import org.wso2.carbon.identity.application.authentication.framework.inbound.IdentityMessageContext;
import org.wso2.carbon.identity.application.authentication.framework.inbound.IdentityResponse;
import org.wso2.carbon.identity.sso.saml.cloud.util.SAMLSSOUtil;

public class SAMLResponse extends IdentityResponse {

    private StatusResponseType response;

    protected SAMLResponse(IdentityResponseBuilder builder) {
        super(builder);
        this.response = ((SAMLResponseBuilder) builder).response;
    }

    public StatusResponseType getResponse() {
        return this.response;
    }

    public static class SAMLResponseBuilder extends IdentityResponseBuilder {

        private StatusResponseType response;

        //Do the bootstrap first
        static {
            SAMLSSOUtil.doBootstrap();
        }

        public SAMLResponseBuilder(IdentityMessageContext context) {
            super(context);
            ResponseBuilder responseBuilder = new ResponseBuilder();
            this.response = responseBuilder.buildObject();
        }

        public SAMLResponseBuilder setResponse(StatusResponseType response) {
            this.response = response;
            return this;
        }
    }

}
