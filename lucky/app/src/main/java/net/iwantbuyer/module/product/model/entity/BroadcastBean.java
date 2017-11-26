package net.iwantbuyer.module.product.model.entity;

/**
 * Created by yangshuyu on 2017/5/12.
 */
public class BroadcastBean {

    /**
     * content : string
     * created_at : 2017-05-12T03:32:05.031Z
     * data : {}
     * game_id : 0
     * template : string
     * type : purchase
     */

    private String content;
    private String created_at;
    private DataBean data;
    private int game_id;
    private String template;
    private String type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class DataBean {
    }
}
