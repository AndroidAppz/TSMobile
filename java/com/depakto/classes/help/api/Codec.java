package com.depakto.classes.help.api;

public enum Codec {
    CODEC_SPEEX_NARROWBAND(0),
    CODEC_SPEEX_WIDEBAND(1),
    CODEC_SPEEX_ULTRAWIDEBAND(2),
    CODEC_CELT_MONO(3),
    UNKNOWN(-1);
    
    private int i;

    private Codec(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
