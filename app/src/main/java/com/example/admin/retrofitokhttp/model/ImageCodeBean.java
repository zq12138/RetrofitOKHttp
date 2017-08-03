package com.example.admin.retrofitokhttp.model;

/**
 * Created by master on 2017/4/12 0012.
 */

public class ImageCodeBean extends BaseBean {
    private BodyEntity body;

    public BodyEntity getBody() {
        return body;
    }

    public void setBody(BodyEntity body) {
        this.body = body;
    }

    public static class BodyEntity {
        /**
         * handlingFee : 3.0
         * withdrawFee : 1231.00
         */

        private String indentify;
        private String uuid;

        public String getIndentify() {
            return indentify;
        }

        public void setIndentify(String indentify) {
            this.indentify = indentify;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
