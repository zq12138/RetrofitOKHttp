package com.example.admin.retrofitokhttp.wapper;

/**
 * Created by master on 2017/6/27 0027.
 */

public class ImageLoaderOptions {
    int failedId;
    int LoadingId;
    int emptyUriId;
    private ImageLoaderOptions(Builder builder) {
        failedId = builder.failedId;
        LoadingId = builder.LoadingId;
        emptyUriId = builder.emptyUriId;
    }
    public static final class Builder {
        private int failedId;
        private int LoadingId;
        private int emptyUriId;

        public Builder() {
        }

        public Builder failedId(int val) {
            failedId = val;
            return this;
        }

        public Builder LoadingId(int val) {
            LoadingId = val;
            return this;
        }

        public Builder emptyUriId(int val) {
            emptyUriId = val;
            return this;
        }

        public ImageLoaderOptions build() {
            return new ImageLoaderOptions(this);
        }
    }
}
