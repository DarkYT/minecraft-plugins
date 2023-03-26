package com.endwork.api.inventory.config;

public abstract class InventoryConfiguration {

    // The chat prefix for messages.
    private String chatPrefix = "&4[GUI]  &c";
    // TODO: The size of the inventory.
    //private int size;

    public InventoryConfiguration(String chatPrefix){
        this.chatPrefix = chatPrefix;
    }

    public InventoryConfiguration(){

    }

    public String getChatPrefix() {
        return chatPrefix;
    }

    public void setChatPrefix(String chatPrefix) {
        this.chatPrefix = chatPrefix;
    }

}
