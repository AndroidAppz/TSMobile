package com.depakto.classes.help.api;

public enum CodecEncryptionMode {
    CODEC_CRYPT_INDIVIDUAL(0),
    CODEC_CRYPT_DISABLED(1),
    CODEC_CRYPT_ENABLED(2),
    UNKNOWN(-1);
    
    private int i;

    private CodecEncryptionMode(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
