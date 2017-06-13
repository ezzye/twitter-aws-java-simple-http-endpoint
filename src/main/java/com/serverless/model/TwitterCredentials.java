package com.serverless.model;

public class TwitterCredentials {
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessSecret;

    public String getConsumerKey() {
        return consumerKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitterCredentials that = (TwitterCredentials) o;

        if (getConsumerKey() != null ? !getConsumerKey().equals(that.getConsumerKey()) : that.getConsumerKey() != null)
            return false;
        if (getConsumerSecret() != null ? !getConsumerSecret().equals(that.getConsumerSecret()) : that.getConsumerSecret() != null)
            return false;
        if (getAccessToken() != null ? !getAccessToken().equals(that.getAccessToken()) : that.getAccessToken() != null)
            return false;
        return getAccessSecret() != null ? getAccessSecret().equals(that.getAccessSecret()) : that.getAccessSecret() == null;
    }

    @Override
    public int hashCode() {
        int result = getConsumerKey() != null ? getConsumerKey().hashCode() : 0;
        result = 31 * result + (getConsumerSecret() != null ? getConsumerSecret().hashCode() : 0);
        result = 31 * result + (getAccessToken() != null ? getAccessToken().hashCode() : 0);
        result = 31 * result + (getAccessSecret() != null ? getAccessSecret().hashCode() : 0);
        return result;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}
