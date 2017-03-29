package com.depakto.classes.help.api;

public enum PermissionGroupDatabaseType {
    TEMPLATE(0),
    REGULAR(1),
    QUERY(2);
    
    private int i;

    private PermissionGroupDatabaseType(int i) {
        this.i = i;
    }

    public int getIndex() {
        return this.i;
    }
}
